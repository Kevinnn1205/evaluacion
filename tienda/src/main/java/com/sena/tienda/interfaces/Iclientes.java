package com.sena.tienda.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sena.tienda.models.cliente;

public interface Icliente extends CrudRepository< cliente, String> {

    @Query("SELECT c FROM Clientes c WHERE c.nombreCliente LIKE %?1%")
    List<cliente> filtroCliente(String filtro);

    @Query("SELECT c FROM Clientes c WHERE c.Ciudad LIKE %?1%")
    List<cliente> filtroClienteCiudad(String ciudad);

    @Query("SELECT c FROM Clientes c WHERE c.Estado LIKE %?1%")
    List<cliente> filtroClienteEstado(char estado);

}
