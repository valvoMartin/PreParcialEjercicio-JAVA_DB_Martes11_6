package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "descuentos")
public class descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private producto producto;

    @Column(name = "porcentaje_descuento", nullable = false)
    private double porcentaje;

    public descuento() {
    }

    public descuento(producto producto, double porcentaje) {
        this.producto = producto;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public org.example.entities.producto getProducto() {
        return producto;
    }

    public void setProducto(org.example.entities.producto producto) {
        this.producto = producto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
