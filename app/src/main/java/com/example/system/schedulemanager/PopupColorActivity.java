package com.example.system.schedulemanager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PopupColorActivity extends AppCompatActivity implements View.OnClickListener {
    final int RESULT_CODE=112;

    Button btnRed, btnBlue, btnYellow, btnPurple, btnTeal, btnOK;
    int selectedColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_colors);

        btnRed=findViewById(R.id.btnRed);
        btnBlue=findViewById(R.id.btnBlue);
        btnYellow=findViewById(R.id.btnYellow);
        btnPurple=findViewById(R.id.btnPurple);
        btnTeal=findViewById(R.id.btnTeal);
        btnOK=findViewById(R.id.btnOK);

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnYellow.setOnClickListener(this);
        btnPurple.setOnClickListener(this);
        btnTeal.setOnClickListener(this);
        btnOK.setOnClickListener(this);

        setTitle("Color Select");
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        Resources resources = getResources();

        switch (id){
            case R.id.btnRed:
                btnOK.setBackgroundColor(resources.getColor(R.color.red));
                selectedColor=R.color.red;
                break;

            case R.id.btnBlue:
                btnOK.setBackgroundColor(resources.getColor(R.color.indigo));
                selectedColor=R.color.indigo;
                break;

            case R.id.btnYellow:
                btnOK.setBackgroundColor(resources.getColor(R.color.yellow));
                selectedColor=R.color.yellow;
                break;

            case R.id.btnPurple:
                btnOK.setBackgroundColor(resources.getColor(R.color.purple));
                selectedColor=R.color.purple;
                break;

            case R.id.btnTeal:
                btnOK.setBackgroundColor(resources.getColor(R.color.teal));
                selectedColor=R.color.teal;
                break;

            case R.id.btnOK:
                Intent intent=new Intent();
                intent.putExtra("color",selectedColor);
                setResult(RESULT_CODE,intent);
                finish();
        }
    }
}
