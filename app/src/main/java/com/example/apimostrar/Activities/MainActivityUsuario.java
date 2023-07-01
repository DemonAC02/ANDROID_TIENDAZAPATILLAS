package com.example.apimostrar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apimostrar.API.ServiceAPI;
import com.example.apimostrar.Conection.ConnectionREST;
import com.example.apimostrar.Models.Usuario;
import com.example.apimostrar.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityUsuario extends AppCompatActivity {
    private Button BtnRegresar,BtnSetting;
    private EditText etResultado;
    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);
        etResultado = (EditText) findViewById(R.id.etResultado);
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        load();
        BotonAtras();
        BtnSetting();
    }
    public void load()
    {
        Call<List<Usuario>> call = serviceAPI.listUsuario();
        call.enqueue(new Callback<List<Usuario>>()
        {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful())
                {
                    List<Usuario> lst = response.body();/*carga la data en el arreglo*/
                    etResultado.setText("\n");
                    for(Usuario x:lst)
                    {
                        etResultado.append(x.getIdCliente() + " " + x.getNombre() +" " +
                                x.getApellidos()+ " "+ x.getCorreo()+" "+x.getClave()+ "\n");
                    }
                }
                else
                {
                    Toast.makeText(null,"Error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
            }
        });
    }
    public void BotonAtras()
    {
        BtnRegresar = (Button) findViewById(R.id.btnRegresar);
        BtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityUsuario.this,PrincipalActivity.class);
                startActivity(i);
            }
        });
    }
    public void BtnSetting() {
        BtnSetting = (Button) findViewById(R.id.btnSettings);
        BtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityUsuario.this, Mant_Usuario_Activity.class);
                startActivity(i);
            }
        });
    }
}