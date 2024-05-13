package com.sena.tienda.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.tienda.interfacesService.IventasService;
import com.sena.tienda.models.ventas;

@RequestMapping("/api/v1/ventas/")
@RestController
public class ventasController {

    @Autowired
    private IventasService ventasService; 

    @PostMapping("/")
    public ResponseEntity<Object> save (@ModelAttribute("ventas") ventas ventas) {

        if (ventas.getTotal().equals("")) {
            
          return new ResponseEntity<>("El campo total es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ventas.getFechaVenta().equals("")) {
            
            return new ResponseEntity<>("El campo fecha venta es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ventas.getEstado().equals("")) {
            
            return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST);
        }

        ventasService.save(ventas);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaVentas = ventasService.findAll();
        return new ResponseEntity<>(listaVentas, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{fechaVentas}")
    public ResponseEntity<Object> findfechaVentas(@PathVariable Date fechaVentas) {
        var listaVentas = ventasService.filtroFechaVentas(fechaVentas);
        return new ResponseEntity<>(listaVentas, HttpStatus.OK); 
    }

    @GetMapping("/busquedafiltroventas/{filtro}")
    public ResponseEntity<Object> findIdCliente(@PathVariable String filtro) {
        var listaVentas = ventasService.filtroClienteVentas(filtro);
        return new ResponseEntity<>(listaVentas, HttpStatus.OK);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var ventas = ventasService.findOne(id);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var ventas = ventasService.findOne(id).get();
        if (ventas != null) {
            if (ventas.getEstado().equals("A")) {

                ventas.setEstado("I");
                ventasService.save(ventas);
                return new ResponseEntity<>("inactivo correctamente", HttpStatus.OK);
            } else
            ventas.setEstado("A");
                ventasService.save(ventas);
            return new ResponseEntity<>("Se ha activado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarPermanente/{id}")
    public ResponseEntity<Object> deleteForever(@PathVariable String id) {
    ventasService.deleteForever(id);
    return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("ventas") ventas VentasUpdate) {
        var ventas = ventasService.findOne(id).get();
        if (ventas != null) {

            ventas.setTotal(VentasUpdate.getTotal());
            ventas.setFechaVenta(VentasUpdate.getFechaVenta());
            ventas.setEstado(VentasUpdate.getEstado());
          
            ventasService.save(ventas);
            return new ResponseEntity<>(ventas, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error venta NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
