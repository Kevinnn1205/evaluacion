package com.sena.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.tienda.interfaces.Icliente;
import com.sena.tienda.interfacesService.IclienteService;
import com.sena.tienda.models.cliente;

@Service
public class clienteService implements IclienteService{

     @Autowired
    private Icliente data;

    @SuppressWarnings("null")
    @Override 
    public String save(cliente cliente) {
        data.save(cliente);
        return cliente.getIdCliente();
    }

    @Override
    public List<cliente> filtroCliente(String filtro) {
        List<cliente> listaCliente =data.filtroCliente(filtro);
        return listaCliente;
    }

    @Override
    public List<cliente> filtroClienteEstado(char estado) {
        List<cliente> listaClientes =data.filtroClienteEstado(estado);
        return listaClientes;
    }
    
    @Override
    public List<cliente> filtroClienteCiudad(String ciudad) {
        List<cliente> listaClientes =data.filtroClienteCiudad(ciudad);
        return listaClientes;
    } 

    @Override
    public List<cliente> findAll() {
        List<cliente> listaCliente = (List<cliente>) data.findAll();
        return listaCliente;
    }

    @Override
    public Optional<cliente> findOne(String id) {
        @SuppressWarnings("null")
        Optional<cliente> cliente = data.findById(id);
        return cliente;
    }

    @SuppressWarnings("null")
    @Override
    public int deleteForever(String id) {
        data.deleteById(id);
        return 1;
    }



}
