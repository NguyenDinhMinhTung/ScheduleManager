package com.example.system.schedulemanager;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.system.schedulemanager.Adapter.MainAdapter;
import com.example.system.schedulemanager.DAO.EvenDAO;
import com.example.system.schedulemanager.DAO.ObjectDAO;
import com.example.system.schedulemanager.DTO.EvenByDayDTO;
import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.DTO.ObjectDTO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;
import com.example.system.schedulemanager.Database.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    RecyclerView mainListView;
    EvenDAO evenDAO;
    ObjectDAO objectDAO;

    Button btnToolbarNow;
    TextView txtToolbarLeft, txtToolbarRight;

    List<EvenByDayDTO> list;
    int[] dayOfMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int year, month, day;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tools.setStatusBarColor(this,R.color.red);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        floatingActionButton = findViewById(R.id.mainFAB);
        mainListView = findViewById(R.id.mainListView);
        btnToolbarNow = findViewById(R.id.btnToolbarNow);
        txtToolbarLeft = findViewById(R.id.txtToolbarLeft);
        txtToolbarRight = findViewById(R.id.txtToolbarRight);

        evenDAO = new EvenDAO(this);
        objectDAO = new ObjectDAO(this);

        floatingActionButton.setOnClickListener(this);
        btnToolbarNow.setOnClickListener(this);
        txtToolbarRight.setOnClickListener(this);
        txtToolbarLeft.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DATE);

        updateList(year, month);
    }

    public void updateList(int year , int month) {
        list = new ArrayList<>();

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM");
        btnToolbarNow.setText(dateFormat1.format(new Date(year - 1900, month, 0, 0, 0, 0)));

        Date now = Calendar.getInstance().getTime();
        TimeTableDTO timeTableDTO = Tools.getAvalibeTimeTable(this, now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        for (int i = dayOfMonth[month]; i >= 1; i--) {
            Date date = new Date(year, month, i, 0, 0);
            List<EvenDTO> listEvenDTO = evenDAO.getListEvenByDate(date);

            if (timeTableDTO != null) {
                if (dateFormat.format(date).compareTo(timeTableDTO.getStart()) >= 0) {
                    List<ObjectDTO> listObjectDTO = objectDAO.getListObject(timeTableDTO.getId(), Tools.getIntDayOfWeek(this, date));
                    if (listObjectDTO != null) {
                        listEvenDTO = merge(date, listEvenDTO, listObjectDTO);
                    }
                }
            }

            Collections.sort(listEvenDTO, new Comparator<EvenDTO>() {
                @Override
                public int compare(EvenDTO even1, EvenDTO even2) {
                    int h1 = even1.getStartTime().getHours();
                    int m1 = even1.getStartTime().getMinutes();

                    int h2 = even2.getStartTime().getHours();
                    int m2 = even2.getStartTime().getMinutes();

                    if (h1 < h2) {
                        return -1;

                    } else if (h1 > h2) {
                        return 1;

                    } else {
                        if (m1 < m2) {
                            return -1;

                        } else if (m1 > m2) {
                            return 1;

                        } else {
                            return 0;
                        }
                    }
                }
            });

            if (listEvenDTO.size() == 0) {
                continue;
            }

            EvenByDayDTO evenByDayDTO = new EvenByDayDTO(date, listEvenDTO);

            list.add(evenByDayDTO);
        }

        MainAdapter mainAdapter = new MainAdapter(this, list);
        mainListView.setAdapter(mainAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainListView.setLayoutManager(linearLayoutManager);
    }

    private List<EvenDTO> merge(Date date, List<EvenDTO> lstEvenDTO, List<ObjectDTO> lstObjectDTO) {
        for (ObjectDTO objectDTO : lstObjectDTO) {

            boolean flag = false;
            for (EvenDTO even : lstEvenDTO) {
                if (objectDTO.getId() == even.getObjectID()) {
                    flag = true;
                    break;
                }
            }
            if (flag) continue;

            Date start = Tools.getStartTimeFromJigen(date, objectDTO.getJigen());
            Date end = Tools.getEndTimeFromJigen(date, objectDTO.getJigen());

            EvenDTO evenDTO = new EvenDTO(2, objectDTO.getId(), R.color.green, objectDTO.getObjectName(), "", start, end);
            lstEvenDTO.add(evenDTO);
        }

        return lstEvenDTO;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateList(year, month);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnTimeTableManager:
                Intent intent = new Intent(MainActivity.this, TimeTableManagerActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.mainFAB:
                Intent intent = new Intent(MainActivity.this, AddEvenActivityTest.class);
                startActivity(intent);
                break;

            case R.id.txtToolbarLeft:
                month -= 1;
                if (month == 0) {
                    year -= 1;
                    month = 12;
                }
                updateList(year, month);
                break;

            case R.id.txtToolbarRight:
                month+=1;

                if (month==13){
                    month=1;
                    year++;
                }

                updateList(year, month);
                break;
        }
    }
}
