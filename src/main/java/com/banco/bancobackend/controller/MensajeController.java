package com.banco.bancobackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancobackend.model.Mensaje;
import com.banco.bancobackend.service.MensajeService;

@RestController
@RequestMapping("/mensaje")
@CrossOrigin(origins = "http://localhost:4200")
public class MensajeController {

    @Autowired
    MensajeService mensajeService;

    @GetMapping()
    public List<Mensaje> obtenerMensajes() {
        return this.mensajeService.obtenerMensajes();
    }

    @GetMapping("/{id}")
    public Optional<Mensaje> obtenerMensaje(@PathVariable("id") Integer id) {
        return this.mensajeService.obtenerMensaje(id);
    }

    @PostMapping()
    public Mensaje guardarMensaje(@RequestBody Mensaje mensaje) {
        return this.mensajeService.guardarMensaje(mensaje);
    }

    @DeleteMapping("/{id}")
    public void borrarMensaje(@PathVariable("id") Integer id) {
        this.mensajeService.borrarMensaje(id);
    }
}
