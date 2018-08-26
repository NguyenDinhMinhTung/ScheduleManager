package com.example.system.schedulemanager;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.system.schedulemanager.DAO.TimeTableDAO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtTitle, txtStartDay, txtEndDay;
    Button btnCancel, btnOK;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    TimeTableDAO timeTableDAO;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_table);

        Tools.setStatusBarColor(AddTimeTableActivity.this, R.color.indigo);

        timeTableDAO=new TimeTableDAO(this);

        txtTitle = findViewById(R.id.txtTitleAddTimeTable);
        txtStartDay = findViewById(R.id.txtStartDayAddTimeTable);
        txtEndDay = findViewById(R.id.txtEndDayAddTimeTable);
        btnCancel = findViewById(R.id.btnCancelAddTimeTable);
        btnOK = findViewById(R.id.btnOKAddTimeTable);

        btnCancel.setOnClickListener(this);
        btnOK.setOnClickListener(this);
        txtStartDay.setOnClickListener(this);
        txtEndDay.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DATE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnOKAddTimeTable:
                Date start = new Date(startYear, startMonth, startDay, 0, 0, 0);
                Date end = new Date(endYear, endMonth, endDay, 0, 0, 0);

                TimeTableDTO timeTableDTO = new TimeTableDTO(0, txtTitle.getText().toString(), start, end);

                timeTableDAO.addTimeTable(timeTableDTO);
                break;

            case R.id.btnCancelAddTimeTable:
                finish();
                break;

            case R.id.txtStartDayAddTimeTable:
                DatePickerDialog startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i - 1900, i1, i2, 0, 0, 0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                        txtStartDay.setText(dateFormat.format(date) + " (" + Tools.shortDayOfWeek(AddTimeTableActivity.this, date) + ")");
                        startYear = i;
                        startMonth = i1;
                        startDay = i2;
                    }
                }, startYear, startMonth, startDay);

                startDatePickerDialog.show();
                break;

            case R.id.txtEndDayAddTimeTable:
                DatePickerDialog endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i - 1900, i1, i2, 0, 0, 0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                        txtEndDay.setText(dateFormat.format(date) + " (" + Tools.shortDayOfWeek(AddTimeTableActivity.this, date) + ")");
                        endYear = i;
                        endMonth = i1;
                        endDay = i2;
                    }
                }, endYear, endMonth, endDay);

                endDatePickerDialog.show();
                break;
        }
    }
}
