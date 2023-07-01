package com.example.apimostrar.Models;

public class Producto {
    private int idproducto;
    private String nombre;
    private int idmarca;
    private int idcategoria;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(int idproducto, String nombre, int idmarca, int idcategoria, double precio, int stock) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.idmarca = idmarca;
        this.idcategoria = idcategoria;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
