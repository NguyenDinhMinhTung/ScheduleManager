package com.example.system.schedulemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.system.schedulemanager.Adapter.ObjectAdapter;
import com.example.system.schedulemanager.DAO.ObjectDAO;
import com.example.system.schedulemanager.DTO.ObjectDTO;

import java.util.ArrayList;
import java.util.List;

public class ObjectActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ObjectDAO objectDAO;
    ObjectAdapter objectAdapter;

    int timetableid;

    List<List<ObjectDTO>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        timetableid = getIntent().getIntExtra("timetableid", 0);

        recyclerView = findViewById(R.id.lstObject);

        objectDAO = new ObjectDAO(this);

        updateList();
    }

    void updateList(){
        list = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            list.add(objectDAO.getListObject(timetableid, i));
        }

        list.add(null);

        objectAdapter=new ObjectAdapter(this,list, timetableid);
        recyclerView.setAdapter(objectAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateList();
    }
}
