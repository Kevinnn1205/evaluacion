package com.sena.tienda.interfacesService;

import java.util.List;
import java.util.Optional;

import com.tienda.tienda.models.Productos;

public interface IproductosService {
    public String save (Productos Productos);

    public List<Productos> findAll();

    public List<Productos> filtroProductos(String filtro);

    public List<Productos> filtroProductosEstado(char estado); 

    public Optional<Productos> findOne(String id);

    public int deleteForever(String id);

}