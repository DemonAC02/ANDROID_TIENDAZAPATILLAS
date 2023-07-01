package com.example.apimostrar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apimostrar.API.ServiceAPI;
import com.example.apimostrar.Conection.ConnectionREST;
import com.example.apimostrar.Models.Marcas;
import com.example.apimostrar.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mant_Marca_Activity extends AppCompatActivity {
    private Button BtnGrabar,BtnAtras,BtnModificar,BtnEliminar;
    private EditText _txtCodigo,_txtDescripcion;
    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_marca);

        BtnGrabar = (Button) findViewById(R.id.btnProcesar);
        BtnAtras = (Button) findViewById(R.id.btnAtras);
        BtnModificar = (Button) findViewById(R.id.btnModificar);
        BtnEliminar = (Button) findViewById(R.id.btnEliminar);
        _txtCodigo = (EditText) findViewById(R.id.etCodigoProd);
        _txtDescripcion = (EditText) findViewById(R.id.etCodigoCat);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        BtnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marcas obj = new Marcas(Integer.parseInt(_txtCodigo.getText().toString()),
                             _txtDescripcion.getText().toString());
                addMarcas(obj);
            }
        });
        BtnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mant_Marca_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
        BtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marcas obj = new Marcas(Integer.parseInt(_txtCodigo.getText().toString()),
                        _txtDescripcion.getText().toString());
                modifyMarcas(obj);
            }
        });

        BtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarMarcas(Integer.parseInt(_txtCodigo.getText().toString()));
            }
        });

    }
    public void addMarcas(Marcas obj)
    {
        Call<Marcas> call = serviceAPI.addMarcas(obj);
        call.enqueue(new Callback<Marcas>()
        {
            @Override
            public void onResponse(Call<Marcas> call, Response<Marcas> response) {
                if(response.isSuccessful())
                {
                    Marcas marc = response.body();
                    mensaje("Registro grabado exitosamente!!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }

            @Override
            public void onFailure(Call<Marcas> call, Throwable t) {
                mensaje("Ocurrio un error desconocido" +t.getMessage());
            }
        });
    }
    public void modifyMarcas(Marcas obj)
    {
        Call<Marcas> call = serviceAPI.modifyMarcas(obj);
        call.enqueue(new Callback<Marcas>() {
            @Override
            public void onResponse(Call<Marcas> call, Response<Marcas> response) {
                if(response.isSuccessful())
                {
                    Marcas marcas = response.body();
                    mensaje("Los datos se modificaron correctamente!!");
                }
                else {
                    mensaje("Ocurrio un error desconocido");
                }
            }

            @Override
            public void onFailure(Call<Marcas> call, Throwable t) {
                mensaje("Ocurrio un error" + t.getMessage());
            }
        });
    }

    public void eliminarMarcas(int parseInt) {
        Call<Marcas> call = serviceAPI.removeMarcas(parseInt);
        call.enqueue(new Callback<Marcas>() {
            @Override
            public void onResponse(Call<Marcas> call, Response<Marcas> response) {
                if(response.isSuccessful())
                {
                    mensaje("Los datos se eliminaron satisfactoriamente!!!");
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Marcas> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

    public void mensaje(String msg)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }
}