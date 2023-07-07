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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancobackend.model.Cliente;
import com.banco.bancobackend.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping()
	public List<Cliente> obtenerClientes() {
		return this.clienteService.leerClientes();
	}

	@GetMapping(path = "/{id}")
	public Optional<Cliente> obtenerCliente(@PathVariable("id") Integer id) {
		return this.clienteService.leerCliente(id);
	}

	@GetMapping(path = "/correo/{email}")
	public Optional<Cliente> obtenerClientePorCorreo(@PathVariable("email") String email) {
		return this.clienteService.buscarClientePorCorreo(email);
	}

	@GetMapping(path = "/usuario/{usuario}")
	public Optional<Cliente> obtenerClientePorUsuario(@PathVariable("usuario") String usuario) {
		return this.clienteService.buscarClientePorUsuario(usuario);
	}

	@GetMapping(path = "/login")
	public Optional<Cliente> loguearCliente(@RequestParam("correo") String correo,
			@RequestParam("pass") String password) {
		return this.clienteService.buscarClientePorCorreoYPass(correo, password);
	}

	@PostMapping()
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return this.clienteService.guardarCliente(cliente);
	}

	@DeleteMapping(path = "/{id}")
	public void borrarCliente(@PathVariable("id") Integer id) {
		this.clienteService.borrarClientePorId(id);
	}

	@GetMapping(path = "/gestor/{idGestor}")
	public List<Cliente> obtenerPorGestor(@PathVariable("idGestor") Integer idGestor) {
		return this.clienteService.buscarClientePorGestorId(idGestor);
	}
}
