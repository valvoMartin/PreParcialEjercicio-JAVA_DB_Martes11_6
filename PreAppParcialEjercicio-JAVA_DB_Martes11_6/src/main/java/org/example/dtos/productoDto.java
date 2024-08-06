package org.example.dtos;

public class productoDto {
    private String nombre;
    private double precio;


    public productoDto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public productoDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

}
