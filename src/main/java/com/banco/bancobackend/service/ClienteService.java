package com.banco.bancobackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Cliente;
import com.banco.bancobackend.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<Cliente> leerClientes() {
        return clienteRepository.findAll();
    }

    // Obtener un cliente por su id
    public Optional<Cliente> leerCliente(Integer id) {
        return clienteRepository.findById(id);
    }
    
    public Optional<Cliente> buscarClientePorCorreoYPass(String correo, String password){
    	
    	Optional<Cliente> cliente = buscarClientePorCorreo (correo);
    	if (cliente.isPresent()) {
    		Cliente clienteEncontrado = cliente.get();
    		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    		if (encoder.matches(password, clienteEncontrado.getPassword())) {
    			return cliente;
    		}		
    	}
    	return null;
    }
    // Guardar un cliente
    
    public Cliente guardarCliente(Cliente cliente) {
	    String pass = cliente.getPassword();
	    if (pass != null) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        pass = encoder.encode(pass);
	        cliente.setPassword(pass);
	    } else {
	        Cliente clienteExistente = leerCliente(cliente.getId()).orElse(null);
	        if (clienteExistente != null) {
	            cliente.setPassword(clienteExistente.getPassword());
	        }
	    }
	    return this.clienteRepository.save(cliente);
	}
    
    private String obtenerPasswordActual(Cliente cliente) {
    	Cliente clienteguardado = leerCliente(cliente.getId()).orElse(null);
    	if ( clienteguardado != null) {
    		return clienteguardado.getPassword();
    	}
    	return null;
    }
    
    public Cliente guardarClienteSinActualizarPassword (Cliente cliente) {
    	String passGuardada = obtenerPasswordActual (cliente);
    	cliente.setPassword(passGuardada);
    	return this.clienteRepository.save(cliente);
    }

    // Eliminar un cliente por su id
    public void borrarClientePorId(Integer id) {
        clienteRepository.deleteById(id);
    }

    // Buscar un cliente por su atributo correo
    public Optional<Cliente> buscarClientePorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    // Buscar un cliente por su atributo usuario
    public Optional<Cliente> buscarClientePorUsuario(String usuario) {
        return clienteRepository.findByUsuario(usuario);
    }
    public List<Cliente> buscarClientePorGestorId(Integer idGestor) {
        return clienteRepository.findByGestorId(idGestor);
    }
    
}
