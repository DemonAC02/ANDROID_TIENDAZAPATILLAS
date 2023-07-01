package com.example.apimostrar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apimostrar.API.ServiceAPI;
import com.example.apimostrar.Conection.ConnectionREST;
import com.example.apimostrar.Models.Marcas;
import com.example.apimostrar.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button BtnRegresar,BtnSetting;
    private EditText etResultado;
    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etResultado = (EditText) findViewById(R.id.etResultado);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        load();
        BotonAtras();
        BtnSetting();
    }
    public void load()
    {
        Call<List<Marcas>> call = serviceAPI.listMarca();
        call.enqueue(new Callback<List<Marcas>>()
        {
            @Override
            public void onResponse(Call<List<Marcas>> call, Response<List<Marcas>> response) {
                if(response.isSuccessful())
                {
                    List<Marcas> lst = response.body();/*carga la data en el arreglo*/
                    etResultado.setText("\n");
                    for(Marcas x:lst)
                    {
                        etResultado.append(x.getIdMarca()+" "+x.getDescripcion() + "\n");
                    }
                }
                else
                {
                    Toast.makeText(null,"Error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Marcas>> call, Throwable t) {
            }
        });
    }
    public void BotonAtras()
    {
        BtnRegresar = (Button) findViewById(R.id.btnRegresar);
        BtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PrincipalActivity.class);
                startActivity(i);
            }
        });
    }
    public void BtnSetting() {
        BtnSetting = (Button) findViewById(R.id.btnSettings);
        BtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Mant_Marca_Activity.class);
                startActivity(i);
            }
        });
    }
}