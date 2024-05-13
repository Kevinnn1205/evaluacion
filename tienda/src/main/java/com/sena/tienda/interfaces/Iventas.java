package com.sena.tienda.interfaces;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sena.tienda.models.ventas;



public interface Iventas extends CrudRepository< ventas, String> {

    @Query ("SELECT v FROM Ventas v WHERE v.fechaVenta = ?1 ")
    List<ventas>filtroFechaVentas(Date fechaVentas);
    
    @Query("SELECT v FROM Clientes v WHERE v.idCliente LIKE %?1%")
    List<ventas> filtroClienteVentas(String filtro);

}

