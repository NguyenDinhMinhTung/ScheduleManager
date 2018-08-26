package com.example.system.schedulemanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.system.schedulemanager.Adapter.MainAdapter;
import com.example.system.schedulemanager.DAO.EvenDAO;
import com.example.system.schedulemanager.DTO.EvenByDayDTO;
import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.Database.Database;

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

    List<EvenByDayDTO> list;
    int[] dayOfMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.mainFAB);
        mainListView = findViewById(R.id.mainListView);

        evenDAO = new EvenDAO(this);

        updateList();

        floatingActionButton.setOnClickListener(this);
    }

    public void updateList() {
        list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        for (int i = day; i >= 1; i--) {
            Date date = new Date(year, month, i, 0, 0);
            List<EvenDTO> listEvenDTO = evenDAO.getListEvenByDate(date);

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

    @Override
    protected void onResume() {
        super.onResume();

        updateList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.mainFAB:
                Intent intent = new Intent(MainActivity.this, AddTimeTableActivity.class);
                startActivity(intent);
                break;
        }
    }
}
