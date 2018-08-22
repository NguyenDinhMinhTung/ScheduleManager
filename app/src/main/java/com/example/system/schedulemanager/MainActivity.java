package com.example.system.schedulemanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.system.schedulemanager.Database.Database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=findViewById(R.id.mainFAB);

        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.mainFAB:
                Intent intent =new Intent(MainActivity.this, AddEvenActivity.class);
                startActivity(intent);
                break;
        }
    }
}
