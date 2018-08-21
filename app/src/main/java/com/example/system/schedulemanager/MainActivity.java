package com.example.system.schedulemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.system.schedulemanager.Database.Database;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database database=new Database(this);
        database.open();
    }
}
