package com.sena.tienda.interfaces;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface Iproductos extends CrudRepository< Productos, String> {

    @Query("SELECT p FROM Productos p WHERE p.nombreProducto LIKE %?1%")
    List<Productos> filtroProductos(String filtro);

    @Query("SELECT p FROM Productos p WHERE p.Estado LIKE %?1%")
    List<Productos> filtroProductosEstado(char estado);  

}

