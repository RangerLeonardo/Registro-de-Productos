package com.alura.jdbc.modelo;

public class Producto {
//    Producto que va a representar a la tabla producto
    private Integer id;
    private Integer categoriaId;

    private String nombre;
    private String descripcion;
    private int cantidad;



    public Producto(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto(Integer id, String nombre, String descripcion, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto(int id, String nombre, int cantidad){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId=categoriaId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }


}
