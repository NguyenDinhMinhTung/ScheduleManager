package com.example.system.schedulemanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TimePicker;

import com.example.system.schedulemanager.DAO.EvenDAO;
import com.example.system.schedulemanager.DAO.ObjectDAO;
import com.example.system.schedulemanager.DTO.EvenDTO;
import com.example.system.schedulemanager.DTO.ObjectDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEvenActivityTest extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE = 111;
    Spinner spnObjectEven, spnJigen;
    Button btnCancelAddNormalEven, btnSaveAddNormalEven, btnCancelAddObjectEven, btnSaveAddObjectEven, btnStartDayAddObjectEven, btnStartDate, btnStartTime, btnEndDate, btnEndTime, btnColorSelect;
    TabHost tabHost;
    TabWidget tabWidget;
    Switch swtAllDay;
    EditText txtTitle, txtNote, txtObjectName;

    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    int startHour, startMin, endHour, endMin;
    int color;

    EvenDAO evenDAO;
    ObjectDAO objectDAO;

    EvenDTO intentEvenDTO;
    ObjectDTO objectDTO;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_even_test);

        spnObjectEven = findViewById(R.id.spnObjectEven2);
        spnJigen = findViewById(R.id.spnJigen);
        btnCancelAddNormalEven = findViewById(R.id.btnCancelAddNormalEven);
        btnSaveAddNormalEven = findViewById(R.id.btnSaveAddNormalEven);
        btnCancelAddObjectEven = findViewById(R.id.btnCancelAddObjectEven);
        btnSaveAddObjectEven = findViewById(R.id.btnSaveAddObjectEven);
        tabHost = findViewById(android.R.id.tabhost);
        tabWidget=findViewById(android.R.id.tabs);
        btnStartDate = findViewById(R.id.btnStartDate);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndDate = findViewById(R.id.btnEndDate);
        btnEndTime = findViewById(R.id.btnEndTime);
        txtTitle = findViewById(R.id.txtTitle);
        txtNote = findViewById(R.id.txtAddNote);
        btnColorSelect = findViewById(R.id.btnColorSelect);
        swtAllDay = findViewById(R.id.swtAllDay);
        btnStartDayAddObjectEven = findViewById(R.id.btnStartDateAddObjectEven);
        txtObjectName = findViewById(R.id.txtObjectNameAddObjectEven);

        btnStartDate.setOnClickListener(this);
        btnStartTime.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);
        btnColorSelect.setOnClickListener(this);
        btnCancelAddNormalEven.setOnClickListener(this);
        btnSaveAddNormalEven.setOnClickListener(this);
        btnCancelAddObjectEven.setOnClickListener(this);
        btnSaveAddObjectEven.setOnClickListener(this);
        btnStartDayAddObjectEven.setOnClickListener(this);

        setupTabs();

        List<String> lstSpinnerObjectEven = new ArrayList<>();
        lstSpinnerObjectEven.add("レポート");
        lstSpinnerObjectEven.add("休講");
        lstSpinnerObjectEven.add("補講");
        lstSpinnerObjectEven.add("試験");
        lstSpinnerObjectEven.add("その他");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstSpinnerObjectEven);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnObjectEven.setAdapter(adapter);

        List<String> lstJigen = new ArrayList<>();
        lstJigen.add("１・２時限");
        lstJigen.add("３・４時限");
        lstJigen.add("５・６時限");
        lstJigen.add("７・８時限");
        lstJigen.add("９・１０時限");

        ArrayAdapter<String> adapterSpnJigen = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstJigen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJigen.setAdapter(adapterSpnJigen);

        spnJigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Date date = new Date(startYear, startMonth + 1, startDay, 0, 0, 0);
                objectDTO = objectDAO.getObjectByDateAndJigen(date, position + 1);
                if (objectDTO != null) {
                    txtObjectName.setText(objectDTO.getObjectName());
                }else{
                    txtObjectName.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        swtAllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    btnEndTime.setVisibility(View.GONE);
                    btnStartTime.setVisibility(View.GONE);
                } else {
                    btnEndTime.setVisibility(View.VISIBLE);
                    btnStartTime.setVisibility(View.VISIBLE);
                }
            }
        });

        evenDAO = new EvenDAO(this);
        objectDAO = new ObjectDAO(this);

        intentEvenDTO = (EvenDTO) getIntent().getSerializableExtra("even");

        if (intentEvenDTO == null) {
            Calendar calendar = Calendar.getInstance();
            startYear = endYear = calendar.get(Calendar.YEAR);
            startMonth = endMonth = calendar.get(Calendar.MONTH);
            startDay = endDay = calendar.get(Calendar.DAY_OF_MONTH);

            startHour = endHour = calendar.get(Calendar.HOUR);
            startMin = endMin = calendar.get(Calendar.MINUTE);

            color = R.color.red;
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

            color = intentEvenDTO.getColor();

            txtTitle.setText(intentEvenDTO.getName());
            txtNote.setText(intentEvenDTO.getNote());

            if (intentEvenDTO.getType()>=2){
                tabHost.setCurrentTab(1);
                btnStartDayAddObjectEven.setEnabled(false);
                spnJigen.setEnabled(false);
                tabWidget.setVisibility(View.GONE);
                objectDTO=objectDAO.getObjectByID(intentEvenDTO.getObjectID());

                txtObjectName.setText(objectDTO.getObjectName());
                spnJigen.setSelection(objectDTO.getJigen()-1);

                if (intentEvenDTO.getType()>=3){
                    spnObjectEven.setSelection(intentEvenDTO.getType()-3);
                }
            }
        }

        Date startDate = new Date(startYear, startMonth, startDay, startHour, startMin, 0);
        Date endDate = new Date(endYear, endMonth, endDay, endHour, endMin, 0);

        setDate(btnStartDate, startDate);
        setDate(btnEndDate, endDate);
        setDate(btnStartDayAddObjectEven, startDate);

        setTime(btnStartTime, startDate);
        setTime(btnEndTime, endDate);

        btnColorSelect.setBackgroundTintList(getResources().getColorStateList(color));
    }

    private void setDate(Button button, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        button.setText(dateFormat.format(date) + " (" + Tools.shortDayOfWeek(AddEvenActivityTest.this, date) + ")");
    }

    private void setTime(Button button, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        button.setText(dateFormat.format(date));
    }

    void setupTabs() {
        tabHost.setup();

        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tabAddNormalEven);
        spec.setIndicator("ddd");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tabAddObjectEven);
        spec.setIndicator("dsfsf");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnStartDate:
                DatePickerDialog startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i, i1, i2, 0, 0, 0);
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
                        Date date = new Date(i, i1, i2, 0, 0, 0);
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

            case R.id.btnSaveAddNormalEven:
                if (intentEvenDTO != null) {
                    evenDAO.delete(intentEvenDTO.getId());
                }

                Date start = new Date(startYear, startMonth + 1, startDay, startHour, startMin);
                Date end = new Date(endYear, endMonth + 1, endDay, endHour, endMin);

                EvenDTO evenDTO = new EvenDTO(0, 0, color, txtTitle.getText().toString(), txtNote.getText().toString(), start, end);
                evenDAO.addEven(evenDTO);

                finish();
                break;

            case R.id.btnCancelAddNormalEven:
                finish();
                break;

            case R.id.btnSaveAddObjectEven:
                if (intentEvenDTO != null) {
                    evenDAO.delete(intentEvenDTO.getId());
                }

                Date start2 = new Date(startYear, startMonth + 1, startDay, startHour, startMin);
                Date end2 = new Date(endYear, endMonth + 1, endDay, endHour, endMin);

                int type = spnObjectEven.getSelectedItemPosition() + 3;
                EvenDTO ev = new EvenDTO(type, objectDTO.getId(), color, objectDTO.getObjectName(), "", start2, end2);
                evenDAO.addEven(ev);

                finish();
                break;

            case R.id.btnCancelAddObjectEven:
                finish();
                break;

            case R.id.btnStartDateAddObjectEven:
                DatePickerDialog startDatePickerDialogObject = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = new Date(i, i1, i2, 0, 0, 0);

                        setDate(btnStartDayAddObjectEven, date);
                        startYear = i;
                        startMonth = i1;
                        startDay = i2;
                    }
                }, startYear, startMonth, startDay);

                startDatePickerDialogObject.show();
                break;
        }
    }
}
