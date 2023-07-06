package com.banco.bancobackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Mensaje;
import com.banco.bancobackend.repository.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    // Obtener todos los mensajes
    public List<Mensaje> obtenerMensajes() {
        return mensajeRepository.findAll();
    }

    // Obtener un mensaje por su ID
    public Optional<Mensaje> obtenerMensaje(Integer id) {
        return mensajeRepository.findById(id);
    }

    // Guardar un mensaje
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    // Borrar un mensaje por su ID
    public void borrarMensaje(Integer id) {
        mensajeRepository.deleteById(id);
    }
}
