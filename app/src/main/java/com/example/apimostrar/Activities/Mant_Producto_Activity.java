package com.example.apimostrar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apimostrar.API.ServiceAPI;
import com.example.apimostrar.Conection.ConnectionREST;
import com.example.apimostrar.Models.Producto;
import com.example.apimostrar.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mant_Producto_Activity extends AppCompatActivity {
    private EditText _etResultado;
    private EditText _etCodigoProd;
    private EditText _etCodigoCat;
    private EditText _etCodigoMarca;
    private EditText _etNombre;
    private EditText _etPrecio;
    private EditText _etStock;
    private Button _btnGrabar;
    private Button _btnModificar;
    private Button _btnEliminar;
    private Button _btnRegresar;
    private ServiceAPI serviceAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        _etCodigoProd = (EditText) findViewById(R.id.etCodigoProd);
        _etCodigoCat = (EditText) findViewById(R.id.etCodigoCat);
        _etCodigoMarca = (EditText) findViewById(R.id.etCodigoMarca);
        _etNombre = (EditText) findViewById(R.id.etNombre);
        _etPrecio = (EditText) findViewById(R.id.etPrecio);
        _etStock = (EditText) findViewById(R.id.etStock);
        _etResultado = (EditText) findViewById(R.id.etResultado);
        _btnGrabar = (Button) findViewById(R.id.btnProcesar);
        _btnModificar = (Button) findViewById(R.id.btnModificar);
        _btnEliminar = (Button) findViewById(R.id.btnEliminar);
        _btnRegresar = (Button) findViewById(R.id.btnAtras);


        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

        _btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto pObj = new Producto(Integer.parseInt(_etCodigoProd.getText().toString()),_etNombre.getText().toString(),Integer.parseInt(_etCodigoCat.getText().toString()),Integer.parseInt(_etCodigoMarca.getText().toString()),
                        Double.parseDouble(_etPrecio.getText().toString()),
                        Integer.parseInt(_etStock.getText().toString())
                );
                addProducto(pObj);
            }
        });

        _btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto(Integer.parseInt(_etCodigoProd.getText().toString()));
            }
        });

        _btnModificar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Producto pObj = new Producto(Integer.parseInt(_etCodigoProd.getText().toString()),_etNombre.getText().toString(),Integer.parseInt(_etCodigoCat.getText().toString()),Integer.parseInt(_etCodigoMarca.getText().toString()),
                        Double.parseDouble(_etPrecio.getText().toString()),
                        Integer.parseInt(_etStock.getText().toString())
                );
                modifyProducto(pObj);
            }
        });
        _btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Mant_Producto_Activity.this,PrincipalActivity.class);
                startActivity(i);
            }
        });
    }



    private void eliminarProducto(int parseInt) {
        Call<Producto> call = serviceAPI.removeProducto(parseInt);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
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
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }
    private void modifyProducto(Producto pObj) {
        Call<Producto> call = serviceAPI.modifyProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    Producto pro = response.body();

                    mensaje("Los datos se modificaron satisfactoriamente!!!");
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }

    public void addProducto(Producto pObj)
    {
        Call<Producto> call = serviceAPI.addProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    Producto pro = response.body();
                    mensaje("Registro grabado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
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