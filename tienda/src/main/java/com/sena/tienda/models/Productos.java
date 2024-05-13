package com.sena.tienda.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "producto") 
public class producto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idProducto", nullable = false, length = 36)
    private String idProducto;

    @Column(name = "nombreProducto", nullable = false, length = 36)
    private String nombreProducto;

    @Column(name = "Descripcion", nullable = false, length = 36)
    private String Descripcion; 

    @Column(name = "Cantidad", nullable = false, length = 100)
    private String Cantidad;

    @Column(name = "Precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal Precio;

    @Column(name = "Iva", nullable = false, length = 2)
    private String Iva;

    @Column(name = "Descuento", nullable = false, length = 2)
    private String Descuento;

    @Column(name = "Estado", nullable = false, length = 1)
    private String Estado;

    public producto() {
    }

    public producto(String idProductos, String nombreProducto, String descripcion, String cantidad, BigDecimal precio,
            String iva, String descuento, String estado) {
        this.idProducto = idProductos;
        this.nombreProducto = nombreProducto;
        Descripcion = descripcion;
        Cantidad = cantidad;
        Precio = precio;
        Iva = iva;
        Descuento = descuento;
        Estado = estado;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String iva) {
        Iva = iva;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDescuento(String descuento) {
        Descuento = descuento;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
