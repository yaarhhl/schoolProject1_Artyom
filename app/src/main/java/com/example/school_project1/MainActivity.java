package com.example.school_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button physics, english, informatics, russian, math1, geography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        physics = findViewById(R.id.button);
        english = findViewById(R.id.button2);
        informatics = findViewById(R.id.button3);
        russian = findViewById(R.id.button4);
        math1= findViewById(R.id.button5);
        geography= findViewById(R.id.button6);
        //
        physics.setOnClickListener(this);
        english.setOnClickListener(this);
        informatics.setOnClickListener(this);
        russian.setOnClickListener(this);
        math1.setOnClickListener(this);
        geography.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent int1 = new Intent(this, Physics_activity.class);
                startActivity(int1);
                break;
            default:
                break;
        }

    }

}