package com.example.apimostrar.API;
import com.example.apimostrar.Models.Marcas;
import com.example.apimostrar.Models.Producto;
import com.example.apimostrar.Models.Usuario;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceAPI {
    @GET("Marcas")
    /*trae la lista de marcas que esta en el servicio web*/
    public abstract Call<List<Marcas>> listMarca();
    @POST("Marcas/agregar")
    public abstract Call<Marcas> addMarcas(@Body Marcas obj);
    @PUT("Marcas/Modificar")
    public abstract Call<Marcas> modifyMarcas(@Body Marcas obj);
    @DELETE("Marcas/Eliminar/{id}")
    public abstract Call<Marcas> removeMarcas(@Path("id") int id);

    /********************* USUARIOS *****************/
    @GET("Usuario")
    /*trae la lista de Usuario que esta en el servicio web*/
    public abstract Call<List<Usuario>> listUsuario();
    @POST("Usuario/agregar")
    public abstract Call<Usuario> addUsuario(@Body Usuario obj);
    @DELETE("Usuario/Eliminar/{id}")
    public abstract Call<Usuario> removeUsuario(@Path("id") int id);
    @PUT("Usuario/Modificar")
    public abstract Call<Usuario> modifyUsuario(@Body Usuario obj);

    /***********PRODUCTOS***********************/
    @GET("producto")
    public abstract Call<List<Producto>> listProduct();
    @GET("producto/{id}")
    public abstract Call<Producto> getProduct(@Path("id") int id);
    @GET("producto/categoria/{categoria}")
    public abstract Call<List<Producto>> listProductosByCategoria(@Path("categoria") int categoria);

    @POST("producto/agregar")
    public abstract Call<Producto> addProducto(@Body Producto obj);
    @PUT("producto/modificar")
    public abstract Call<Producto> modifyProducto(@Body Producto obj);
    @DELETE("producto/eliminar/{id}")
    public abstract Call<Producto> removeProducto(@Path("id") int id);
}
