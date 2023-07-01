package com.example.apimostrar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apimostrar.R;

public class IntroActivity extends AppCompatActivity {
    private Button BtnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        BtnStart = (Button) findViewById(R.id.btnStart);
        BtnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(IntroActivity.this,"Ingreso satisfactorio", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(IntroActivity.this,PrincipalActivity.class);
                startActivity(i);
            }
        });
    }
}