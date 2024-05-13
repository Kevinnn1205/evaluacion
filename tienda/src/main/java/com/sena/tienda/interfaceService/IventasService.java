package com.sena.tienda.interfacesService;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.sena.tienda.models.ventas;

public interface IventasService {

    public String save (ventas ventas);

    public List<ventas> findAll();

    public List<ventas> filtroFechaVentas(Date filtroVentas);

    public List<ventas> filtroClienteVentas(String filtro);

    public Optional<ventas> findOne(String id);

    public int deleteForever(String id);

}
