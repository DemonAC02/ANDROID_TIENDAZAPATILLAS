package com.example.apimostrar.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apimostrar.API.ServiceAPI;
import com.example.apimostrar.Conection.ConnectionREST;
import com.example.apimostrar.Models.Usuario;
import com.example.apimostrar.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mant_Usuario_Activity extends AppCompatActivity {
    private Button BtnGrabar,BtnAtras,BtnModificar,BtnEliminar;
    private EditText _txtCodigo,_txtNombre,_txtApellido,_txtCorreo,_txtClave;
    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_usuario);
        BtnGrabar = (Button) findViewById(R.id.btnProcesar);
        BtnAtras = (Button) findViewById(R.id.btnAtras);
        BtnModificar = (Button) findViewById(R.id.btnModificar);
        BtnEliminar = (Button) findViewById(R.id.btnEliminar);
        _txtCodigo = (EditText) findViewById(R.id.etCodigoProd);
        _txtNombre = (EditText) findViewById(R.id.etCodigoCat);
        _txtApellido = (EditText) findViewById(R.id.etCodigoMarca);
        _txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        _txtClave = (EditText) findViewById(R.id.txtClave);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        BtnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario obj = new Usuario(Integer.parseInt(_txtCodigo.getText().toString()),
                        _txtNombre.getText().toString(),_txtApellido.getText().toString(),
                        _txtCorreo.getText().toString(),_txtClave.getText().toString());
                addUsuario(obj);
            }
        });
        BtnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mant_Usuario_Activity.this,MainActivityUsuario.class);
                startActivity(i);
            }
        });
        BtnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario obj = new Usuario(Integer.parseInt(_txtCodigo.getText().toString()),
                        _txtNombre.getText().toString(),_txtApellido.getText().toString(),
                        _txtCorreo.getText().toString(),_txtClave.getText().toString());
                modifyUsuario(obj);
            }
        });

        BtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario(Integer.parseInt(_txtCodigo.getText().toString()));
            }
        });

    }
    public void addUsuario(Usuario obj)
    {
        Call<Usuario> call = serviceAPI.addUsuario(obj);
        call.enqueue(new Callback<Usuario>()
        {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    Usuario usu = response.body();
                    mensaje("Registro grabado exitosamente!!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensaje("Ocurrio un error desconocido" +t.getMessage());
            }
        });
    }
    public void modifyUsuario(Usuario obj)
    {
        Call<Usuario> call = serviceAPI.modifyUsuario(obj);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    Usuario usuario = response.body();
                    mensaje("Los datos se modificaron correctamente!!");
                }
                else {
                    mensaje("Ocurrio un error desconocido");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensaje("Ocurrio un error" + t.getMessage());
            }
        });
    }

    public void eliminarUsuario(int parseInt) {
        Call<Usuario> call = serviceAPI.removeUsuario(parseInt);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
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
            public void onFailure(Call<Usuario> call, Throwable t) {
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
