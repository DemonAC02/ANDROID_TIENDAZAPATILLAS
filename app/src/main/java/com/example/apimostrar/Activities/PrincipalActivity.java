package com.example.apimostrar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apimostrar.R;

public class PrincipalActivity extends AppCompatActivity
{
    private Button BtnProductos,BtnMarcas,BtnUsuario,BtnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        botones();
    }
    public void botones()
    {
        BtnMarcas = (Button) findViewById(R.id.btnMarcas);
        BtnMarcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PrincipalActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        BtnUsuario = (Button) findViewById(R.id.btnUsuarios);
        BtnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PrincipalActivity.this,MainActivityUsuario.class);
                startActivity(i);
            }
        });
        BtnProductos = (Button) findViewById(R.id.btnProductos);
        BtnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PrincipalActivity.this, ProductoActivity.class);
                startActivity(i);
            }
        });
        BtnSalir = (Button) findViewById(R.id.btnSalir);
        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PrincipalActivity.this,IntroActivity.class);
                startActivity(i);
            }
        });
    }
}
