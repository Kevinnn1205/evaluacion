package com.sena.tienda.controller;

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

import com.sena.tienda.interfacesService.IproductoService;
import com.sena.tienda.models.producto;

@RequestMapping("/api/v1/producto/")
@RestController
public class productoController {
    @Autowired
    private IproductoService productoService;

    @PostMapping("/")
    public ResponseEntity<Object> save (@ModelAttribute("producto") producto producto) {

        if (producto.getNombreProducto().equals("")) {
            
          return new ResponseEntity<>("El campo nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getDescripcion().equals("")) {
            
            return new ResponseEntity<>("El campo descripcion es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getPrecio().equals("")) {
            
            return new ResponseEntity<>("El campo precio es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getCantidad().equals("")) {
            
            return new ResponseEntity<>("El campo cantidad es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getEstado().equals("")) {
            
            return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaProducto = productoService.findAll();
        return new ResponseEntity<>(listaProducto, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaProducto = productoService.filtroProducto(filtro);
        return new ResponseEntity<>(listaProducto, HttpStatus.OK); 
    }

    @GetMapping("/busquedafiltroestado/{estado}")
    public ResponseEntity<Object> findEstado(@PathVariable char estado) {
        var listaProducto = productoService.filtroProductoEstado(estado);
        return new ResponseEntity<>(listaProducto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var producto = productoService.findOne(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var producto = productoService.findOne(id).get();
        if (producto != null) {
            if (producto.getEstado().equals("A")) {

                producto.setEstado("I");
                productoService.save(producto);
                return new ResponseEntity<>("inactivo correctamente", HttpStatus.OK);
            } else
            producto.setEstado("A");
                productoService.save(producto);
            return new ResponseEntity<>("Se ha activado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarPermanente/{id}")
    public ResponseEntity<Object> deleteForever(@PathVariable String id) {
    productoService.deleteForever(id);
    return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("producto") producto ProductoUpdate) {
        var producto = productoService.findOne(id).get();
        if (producto != null) {

            producto.setNombreProducto(ProductoUpdate.getNombreProducto());
            producto.setDescripcion(ProductoUpdate.getDescripcion());
            producto.setCantidad(ProductoUpdate.getCantidad());
            producto.setPrecio(ProductoUpdate.getPrecio());
            producto.setIva(ProductoUpdate.getIva());
            producto.setDescuento(ProductoUpdate.getDescuento());
            producto.setEstado(ProductoUpdate.getEstado());
          
            productoService.save(producto);
            return new ResponseEntity<>(producto, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error producto NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
