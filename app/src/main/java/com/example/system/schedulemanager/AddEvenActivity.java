package com.example.system.schedulemanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.system.schedulemanager.DAO.EvenDAO;
import com.example.system.schedulemanager.DTO.EvenDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEvenActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnStartDate, btnStartTime, btnEndDate, btnEndTime, btnSave, btnCancel;
    EditText txtTitle, txtNote;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    int startHour, startMin, endHour, endMin;

    EvenDAO evenDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_even);

        btnStartDate=findViewById(R.id.btnStartDate);
        btnStartTime=findViewById(R.id.btnStartTime);
        btnEndDate=findViewById(R.id.btnEndDate);
        btnEndTime=findViewById(R.id.btnEndTime);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        txtTitle=findViewById(R.id.txtTitle);
        txtNote=findViewById(R.id.txtNote);

        btnStartDate.setOnClickListener(this);
        btnStartTime.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        evenDAO=new EvenDAO(this);

        Calendar calendar=Calendar.getInstance();
        startYear=endYear=calendar.get(Calendar.YEAR);
        startMonth=endMonth=calendar.get(Calendar.MONTH);
        startDay=endDay=calendar.get(Calendar.DAY_OF_MONTH);

        startHour=endHour=calendar.get(Calendar.HOUR);
        startMin=endMin=calendar.get(Calendar.MINUTE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.btnStartDate:
                DatePickerDialog startDatePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        btnStartDate.setText((i1+1)+"月"+i2+"日");
                        startYear=i; startMonth=i1;startDay=i2;
                    }
                },startYear,startMonth,startDay);

                startDatePickerDialog.show();
                break;

            case R.id.btnStartTime:
                TimePickerDialog startTimePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date=new Date(1,1,1,i,i1);
                        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm");

                        btnStartTime.setText(dateFormat.format(date));
                        startHour=i;startMin=i1;
                    }
                },startHour,startMin,true);

                startTimePickerDialog.show();
                break;

            case R.id.btnEndDate:
                DatePickerDialog endDatePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        btnEndDate.setText((i1+1)+"月"+i2+"日");
                        endYear=i; endMonth=i1;endDay=i2;
                    }
                },endYear,endMonth,endDay);

                endDatePickerDialog.show();
                break;

            case R.id.btnEndTime:
                TimePickerDialog endTimePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date=new Date(1,1,1,i,i1);
                        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm");

                        btnEndTime.setText(dateFormat.format(date));
                        endHour=i;endMin=i1;
                    }
                },endHour,endMin,true);

                endTimePickerDialog.show();
                break;

            case R.id.btnSave:
                Date start=new Date(startYear, startMonth, startDay, startHour, startMin);
                Date end=new Date(endYear, endMonth, endDay, endHour, endMin);

                EvenDTO evenDTO=new EvenDTO(0,0,txtTitle.getText().toString(),txtNote.getText().toString(),start, end);
                evenDAO.addEven(evenDTO);
                break;

            case R.id.btnCancel:
                break;
        }
    }
}
