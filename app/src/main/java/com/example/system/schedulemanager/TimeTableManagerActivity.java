package com.example.system.schedulemanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.system.schedulemanager.Adapter.TimeTableAdapter;
import com.example.system.schedulemanager.DAO.TimeTableDAO;
import com.example.system.schedulemanager.DTO.TimeTableDTO;

import java.util.List;

public class TimeTableManagerActivity extends AppCompatActivity {
    RecyclerView lstTimeTableManager;
    FloatingActionButton fab;
    TimeTableAdapter timeTableAdapter;
    List<TimeTableDTO> list;
    TimeTableDAO timeTableDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_manager);

        lstTimeTableManager=findViewById(R.id.lstTimeTableManager);
        fab=findViewById(R.id.fabTimeTableManager);

        timeTableDAO=new TimeTableDAO(this);

        updateList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TimeTableManagerActivity.this, AddTimeTableActivity.class);
                startActivity(intent);
            }
        });
    }

    void updateList(){
        list=timeTableDAO.getListTimeTable();

        timeTableAdapter=new TimeTableAdapter(this,list);
        lstTimeTableManager.setAdapter(timeTableAdapter);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lstTimeTableManager.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateList();
    }
}
