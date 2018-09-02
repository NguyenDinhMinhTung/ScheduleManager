package com.example.system.schedulemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.system.schedulemanager.DAO.ObjectDAO;
import com.example.system.schedulemanager.DTO.ObjectDTO;

public class AddObjectActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtObjectName, txtPlace, txtNote, txtNum;
    Button btnOK, btnCancel;

    ObjectDAO objectDAO;

    int timetableid, dayofweek, jigen;
    ObjectDTO objectDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);

        objectDAO = new ObjectDAO(this);

        txtObjectName = findViewById(R.id.txtObjectNameAddObject);
        txtPlace = findViewById(R.id.txtPlaceAddObject);
        txtNote = findViewById(R.id.txtNoteAddObject);
        txtNum = findViewById(R.id.txtNumberAddObject);

        btnOK = findViewById(R.id.btnOKAddObject);
        btnCancel = findViewById(R.id.btnCancelAddObject);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Intent intent = getIntent();
        objectDTO = (ObjectDTO) intent.getSerializableExtra("objectdto");

        if (objectDTO != null) {
            timetableid = objectDTO.getTimetableid();
            dayofweek = objectDTO.getDayOfWeek();
            jigen = objectDTO.getJigen();

            txtObjectName.setText(objectDTO.getObjectName().toString());
            txtNote.setText(objectDTO.getNote().toString());
            txtPlace.setText(objectDTO.getNote().toString());
            txtNum.setText(String.valueOf(objectDTO.getNum()));
        } else {
            timetableid = intent.getIntExtra("timetableid", 0);
            dayofweek = intent.getIntExtra("dayofweek", 0);
            jigen = intent.getIntExtra("jigen", 0);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnOKAddObject:
                if (objectDTO!=null){
                    objectDAO.delete(objectDTO.getId());
                }

                int num = Integer.parseInt(txtNum.getText().toString());
                String note = txtNote.getText().toString();
                String place = txtNote.getText().toString();
                String objectname = txtObjectName.getText().toString();

                ObjectDTO objectDTO = new ObjectDTO(0, timetableid, dayofweek, jigen, num, objectname, place, note);

                objectDAO.addObject(objectDTO);

                finish();
                break;

            case R.id.btnCancelAddObject:
                finish();
        }
    }
}
