package com.example.system.schedulemanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.system.schedulemanager.DAO.EvenDAO;
import com.example.system.schedulemanager.DTO.EvenDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEvenActivity extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE = 111;

    Button btnStartDate, btnStartTime, btnEndDate, btnEndTime, btnSave, btnCancel, btnColorSelect;
    Switch swtAllDay;
    EditText txtTitle, txtNote;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    int startHour, startMin, endHour, endMin;
    int color;

    EvenDAO evenDAO;
    EvenDTO intentEvenDTO;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_even);

        btnStartDate = findViewById(R.id.btnStartDate);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndDate = findViewById(R.id.btnEndDate);
        btnEndTime = findViewById(R.id.btnEndTime);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        txtTitle = findViewById(R.id.txtTitle);
        txtNote = findViewById(R.id.txtAddNote);
        btnColorSelect = findViewById(R.id.btnColorSelect);
        swtAllDay=findViewById(R.id.swtAllDay);

        btnStartDate.setOnClickListener(this);
        btnStartTime.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnColorSelect.setOnClickListener(this);

        swtAllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    btnEndTime.setVisibility(View.GONE);
                    btnStartTime.setVisibility(View.GONE);
                }else{
                    btnEndTime.setVisibility(View.VISIBLE);
                    btnStartTime.setVisibility(View.VISIBLE);
                }
            }
        });

        evenDAO = new EvenDAO(this);

        intentEvenDTO = (EvenDTO) getIntent().getSerializableExtra("even");

        if (intentEvenDTO == null) {
            Calendar calendar = Calendar.getInstance();
            startYear = endYear = calendar.get(Calendar.YEAR);
            startMonth = endMonth = calendar.get(Calendar.MONTH);
            startDay = endDay = calendar.get(Calendar.DAY_OF_MONTH);

            startHour = endHour = calendar.get(Calendar.HOUR);
            startMin = endMin = calendar.get(Calendar.MINUTE);

            color=R.color.red;
        } else {
            Date start = intentEvenDTO.getStartTime();
            Date end = intentEvenDTO.getEndTime();

            startYear = start.getYear();
            startMonth = start.getMonth() - 1;
            startDay = start.getDate();
            startHour = start.getHours();
            startMin = start.getMinutes();

            endYear = end.getYear();
            endMonth = end.getMonth() - 1;
            endDay = end.getDate();
            endHour = end.getHours();
            endMin = end.getMinutes();

            color=intentEvenDTO.getColor();

            txtTitle.setText(intentEvenDTO.getName());
            txtNote.setText(intentEvenDTO.getNote());
        }

        Date startDate = new Date(startYear - 1900, startMonth, startDay, startHour, startMin, 0);
        Date endDate = new Date(endYear - 1900, endMonth, endDay, endHour, endMin, 0);

        setDate(btnStartDate, startDate);
        setDate(btnEndDate, endDate);

        setTime(btnStartTime, startDate);
        setTime(btnEndTime, endDate);

        btnColorSelect.setBackgroundTintList(getResources().getColorStateList(color));
    }

    private void setDate(Button button, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        button.setText(dateFormat.format(date) + " (" + Tools.shortDayOfWeek(AddEvenActivity.this, date) + ")");
    }

    private void setTime(Button button, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        button.setText(dateFormat.format(date));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnStartDate:
                DatePickerDialog startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i - 1900, i1 + 1, i2, 0, 0, 0);
                        setDate(btnStartDate, date);
                        startYear = i;
                        startMonth = i1;
                        startDay = i2;
                    }
                }, startYear, startMonth, startDay);

                startDatePickerDialog.show();
                break;

            case R.id.btnStartTime:
                TimePickerDialog startTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = new Date(1, 1, 1, i, i1);
                        setTime(btnStartTime, date);
                        startHour = i;
                        startMin = i1;
                    }
                }, startHour, startMin, true);

                startTimePickerDialog.show();
                break;

            case R.id.btnEndDate:
                DatePickerDialog endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i - 1900, i1 + 1, i2, 0, 0, 0);
                        setDate(btnEndDate, date);
                        endYear = i;
                        endMonth = i1;
                        endDay = i2;
                    }
                }, endYear, endMonth, endDay);

                endDatePickerDialog.show();
                break;

            case R.id.btnEndTime:
                TimePickerDialog endTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = new Date(1, 1, 1, i, i1);
                        setTime(btnEndTime, date);
                        endHour = i;
                        endMin = i1;
                    }
                }, endHour, endMin, true);

                endTimePickerDialog.show();
                break;

            case R.id.btnSave:
                if (intentEvenDTO != null) {
                    evenDAO.delete(intentEvenDTO.getId());
                }

                Date start = new Date(startYear, startMonth + 1, startDay, startHour, startMin);
                Date end = new Date(endYear, endMonth + 1, endDay, endHour, endMin);

                EvenDTO evenDTO = new EvenDTO(0, 0, color, txtTitle.getText().toString(), txtNote.getText().toString(), start, end);
                evenDAO.addEven(evenDTO);

                finish();
                break;

            case R.id.btnCancel:
                finish();
                break;

            case R.id.btnColorSelect:
                Intent intent = new Intent(AddEvenActivity.this, PopupColorActivity.class);
                intent.putExtra("color",color);
                startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == 112) {
                color = data.getIntExtra("color", 0);

                btnColorSelect.setBackgroundTintList(getResources().getColorStateList(color));
            }
        }
    }
}
