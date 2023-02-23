package net.youtics.models;

import java.util.Date;

public class Producto {

    private String nombre;
    private Integer id;
    private Integer precio;
    private Date Fecha_registro;
    private Categoria categoria;

    public Producto(String nombre, int id, Integer precio, Date fecha_registro) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        Fecha_registro = fecha_registro;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFecha_registro() {
        return Fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        Fecha_registro = fecha_registro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", precio=" + precio +
                ", Fecha_registro=" + Fecha_registro +
                ", categoria=" + categoria.getNombreCategoria() +
                '}';
    }
}
