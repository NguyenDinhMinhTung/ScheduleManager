package com.example.system.schedulemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.system.schedulemanager.DAO.ObjectDAO;
import com.example.system.schedulemanager.DTO.ObjectDTO;

public class ObjectDetail extends AppCompatActivity implements View.OnClickListener {
TextView txtObjectName, txtPlace, txtDayOfWeek, txtJigen, txtNumber, txtNote, txtClose, txtEdit, txtDelete;
ObjectDTO objectDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_detail);

        setTitle("Object Detail");
        Intent intent=getIntent();
        objectDTO= (ObjectDTO) intent.getSerializableExtra("objectdto");

        txtObjectName=findViewById(R.id.txtObjectNameObjectDetail);
        txtPlace=findViewById(R.id.txtPlaceObjectDetail);
        txtDayOfWeek=findViewById(R.id.txtDayOfWeekObjectDetail);
        txtJigen=findViewById(R.id.txtJigenObjectDetail);
        txtNumber=findViewById(R.id.txtNumObjectDetail);
        txtNote=findViewById(R.id.txtNoteObjectDetail);
        txtClose=findViewById(R.id.txtCloseObjectDetail);
        txtEdit=findViewById(R.id.txtEditObjectDetail);
        txtDelete=findViewById(R.id.txtDeleteObjectDetail);

        txtObjectName.setText(objectDTO.getObjectName());
        txtPlace.setText(objectDTO.getPlace());
        txtDayOfWeek.setText(Tools.getStringDayOfWeek(this,objectDTO.getDayOfWeek()));
        txtJigen.setText(Tools.getJigen(objectDTO.getJigen()));
        txtNumber.setText(String.valueOf(objectDTO.getNum()));
        txtNote.setText(objectDTO.getNote());

        txtClose.setOnClickListener(this);
        txtEdit.setOnClickListener(this);
        txtDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.txtCloseObjectDetail:
                finish();
                break;

            case R.id.txtEditObjectDetail:
                Intent intent=new Intent(ObjectDetail.this, AddObjectActivity.class);
                intent.putExtra("objectdto", objectDTO);
                startActivity(intent);
                finish();
                break;

            case R.id.txtDeleteObjectDetail:
                ObjectDAO objectDAO=new ObjectDAO(this);
                objectDAO.delete(objectDTO.getId());
                finish();
        }
    }
}
