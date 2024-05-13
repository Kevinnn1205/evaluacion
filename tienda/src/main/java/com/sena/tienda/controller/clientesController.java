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

import com.sena.tienda.interfacesService.IclienteService;
import com.sena.tienda.models.cliente;

@RequestMapping("/api/v1/cliente/")
@RestController
public class clienteController {

    @Autowired
    private IclienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Object> save (@ModelAttribute("cliente") cliente cliente) {
         // verificar que no exista el documento de identidad
        var listaCliente = clienteService.findAll()
                .stream().filter(Cliente -> Cliente.getIdentificacion()
                 .equals(cliente.getIdentificacion()));
        if (listaCliente.count() != 0) {
     return new ResponseEntity<>("El cliente ya existe", HttpStatus.BAD_REQUEST); 
    }

    if (cliente.getIdentificacion().equals("")) {
            
        return new ResponseEntity<>("El campo identificacion es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (cliente.getNombreCliente().equals("")) {
            
        return new ResponseEntity<>("El campo nombre es obligatorio", HttpStatus.BAD_REQUEST);
    }

    if (cliente.getApellidoCliente().equals("")) {
            
        return new ResponseEntity<>("El campo apellido es obligatorio", HttpStatus.BAD_REQUEST); 
    }

    if (cliente.getTelefono().equals("")) {
            
        return new ResponseEntity<>("El campo telefono es obligatorio", HttpStatus.BAD_REQUEST); 
    }

    if (cliente.getDireccion().equals("")) {
            
        return new ResponseEntity<>("El campo direccion es obligatorio", HttpStatus.BAD_REQUEST); 
    }

    if (cliente.getEstado().equals("")) {
            
        return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST); 
    }

        clienteService.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaCliente = clienteService.findAll();
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaCliente = clienteService.filtroCliente(filtro);
        return new ResponseEntity<>(listaCliente, HttpStatus.OK); 
    }

    @GetMapping("/busquedafiltroestado/{estado}")
    public ResponseEntity<Object> findEstado(@PathVariable char estado) {
        var listaCliente = clienteService.filtroClienteEstado(estado);
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    @GetMapping("/busquedafiltrociudad/{ciudad}")
    public ResponseEntity<Object> findCiudad(@PathVariable String ciudad) {
        var listaCliente = clienteService.filtroClienteCiudad(ciudad);
        return new ResponseEntity<>(listaCliente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var cliente = clienteService.findOne(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var cliente = clienteService.findOne(id).get();
        if (cliente != null) {
            if (cliente.getEstado().equals("A")) {

                cliente.setEstado("I");
                clienteService.save(cliente);
                return new ResponseEntity<>("inactivo correctamente", HttpStatus.OK);
            } else
                cliente.setEstado("A");
                clienteService.save(cliente);
            return new ResponseEntity<>("Se ha activado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se ha encontrado el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminarPermanente/{id}")
    public ResponseEntity<Object> deleteForever(@PathVariable String id) {
    clienteService.deleteForever(id);
    return new ResponseEntity<>("Registro eliminado Permanentemente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("cliente") cliente ClientesUpdate) {
        var cliente = clienteService.findOne(id).get();
        if (cliente != null) {

            cliente.setTipoIdentificacion(ClientesUpdate.getTipoIdentificacion());
            cliente.setIdentificacion(ClientesUpdate.getIdentificacion());
            cliente.setNombreCliente(ClientesUpdate.getNombreCliente());
            cliente.setApellidoCliente(ClientesUpdate.getApellidoCliente());
            cliente.setTelefono(ClientesUpdate.getTelefono());
            cliente.setDireccion(ClientesUpdate.getDireccion());
            cliente.setCiudad(ClientesUpdate.getCiudad());
            cliente.setEstado(ClientesUpdate.getEstado());
          
            clienteService.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error cliente NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}


