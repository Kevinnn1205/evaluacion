package com.sena.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.tienda.interfaces.Iproducto;
import com.sena.tienda.interfacesService.IproductoService;
import com.sena.tienda.models.producto;


@Service
public class productoService implements IproductoService {

    @Autowired
    private Iproducto data;

    @SuppressWarnings("null")
    @Override 
    public String save(producto producto) {
        data.save(producto);
        return producto.getIdProducto();
    }

     @Override
    public List<producto> filtroProducto(String filtro) {
        List<producto> listaProducto =data.filtroProducto(filtro);
        return listaProducto;
    }

    @Override
    public List<producto> filtroProductoEstado(char estado) {
        List<producto> listaProducto =data.filtroProductoEstado(estado);
        return listaProducto;
    }

    @Override
    public List<producto> findAll() {
        List<producto> listaProducto = (List<producto>) data.findAll();
        return listaProducto;
    }

    @Override
    public Optional<producto> findOne(String id) {
        @SuppressWarnings("null")
        Optional<producto> Producto = data.findById(id);
        return Producto;
    }
    @SuppressWarnings("null")
    @Override
    public int deleteForever(String id) {
        data.deleteById(id);
        return 1;
    }
}
