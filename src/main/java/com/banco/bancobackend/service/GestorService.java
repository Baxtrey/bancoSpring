package com.banco.bancobackend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Gestor;
import com.banco.bancobackend.repository.GestorRepository;

@Service
public class GestorService {

	@Autowired
	GestorRepository gestorRepository;

	// obtener todos los gestores
	public ArrayList<Gestor> leerGestores() {
		return (ArrayList<Gestor>) this.gestorRepository.findAll();
	}

// obtener un gestor por su id
	public Optional<Gestor> leerGestor(Integer id) {
		return this.gestorRepository.findById(id);

	}

	public Gestor guardarGestor(Gestor gestor) {
	    String pass = gestor.getPassword();
	    if (pass != null) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        pass = encoder.encode(pass);
	        gestor.setPassword(pass);
	    } else {
	        Gestor gestorExistente = leerGestor(gestor.getId()).orElse(null);
	        if (gestorExistente != null) {
	            gestor.setPassword(gestorExistente.getPassword());
	        }
	    }
	    return this.gestorRepository.save(gestor);
	}


	// elimina un gestor por su id
	public void borrarGestorPorId(Integer id) {
		this.gestorRepository.deleteById(id);
	}

	// buscar un gestor por su atributo correo
	public Optional<Gestor> buscarGestorPorCorreo(String correo) {
		return this.gestorRepository.findOneByCorreo(correo);
	}

	public Optional<Gestor> buscarGestorPorCorreoYPass(String correo, String password) {
		Optional<Gestor> gestor = buscarGestorPorCorreo (correo);
		if (gestor.isPresent()) {
			Gestor gestorEncontrado = gestor.get();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password, gestorEncontrado.getPassword())) {
				return gestor;
			}

		}
		
		return null; 
	}

}