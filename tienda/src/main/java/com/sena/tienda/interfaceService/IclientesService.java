package com.sena.tienda.interfacesService;

import java.util.List;
import java.util.Optional;

import com.sena.tienda.models.cliente;

public interface IclienteService {
    public String save (cliente cliente);

    public List<cliente> findAll();

    public List<cliente> filtroCliente(String filtro);

    public List<cliente> filtroClienteEstado(char estado); 

    public List<cliente> filtroClienteCiudad(String ciudad);

    public Optional<cliente> findOne(String id);

    public int deleteForever(String id);

}
