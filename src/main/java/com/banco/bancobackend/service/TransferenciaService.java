package com.banco.bancobackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Cliente;
import com.banco.bancobackend.model.Transferencia;
import com.banco.bancobackend.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	ClienteService clienteService;

	// Obtener todas las transferencias
	public List<Transferencia> leerTransferencias() {
		return transferenciaRepository.findAll();
	}

	// Obtener una transferencia por su id
	public Optional<Transferencia> leerTransferencia(Integer id) {
		return transferenciaRepository.findById(id);
	}

	// Guardar una transferencia
	public Transferencia guardarTransferencia(Transferencia transferencia) {

		this.transferenciaRepository.save(transferencia);
		   // Obtener y Actualizar ordenante 
	    Cliente ordenante = transferencia.getOrdenante();

	    ordenante = clienteService.leerCliente(ordenante.getId()).orElse(null);
	    
	    Double importe = transferencia.getImporte();
	    
	    
	    Double saldoOrdenante = ordenante.getSaldo();
	    
	    ordenante.setSaldo(saldoOrdenante - importe);
	    // obtener y actualizar beneficiarios
	    
	    Cliente beneficiario = transferencia.getBeneficiario();
	    
	    beneficiario = clienteService.leerCliente(beneficiario.getId()).orElse(null);
	    
	    Double saldoBeneficiario = beneficiario.getSaldo();
	    
	    beneficiario.setSaldo(saldoBeneficiario + importe);

	    // guardamos los clientes modificados
	    clienteService.guardarClienteSinActualizarPassword(ordenante);
	    clienteService.guardarClienteSinActualizarPassword(beneficiario);
	    return transferencia;

	}

	// Eliminar una transferencia por su id
	public void borrarTransferenciaPorId(Integer id) {
		transferenciaRepository.deleteById(id);
	}

	// Buscar una transferencia por su atributo origen
	public ArrayList<Transferencia> buscarTransferenciaPorOrdenanteId(Integer ordenante) {
		return transferenciaRepository.findByOrdenanteId(ordenante);
	}

	// Buscar una transferencia por su atributo destino
	public ArrayList<Transferencia> buscarTransferenciaPorBeneficiarioId(Integer beneficiario) {
		return transferenciaRepository.findByBeneficiarioId(beneficiario);
	}

	// Buscar todas las transferencias con un origen y destino específicos
	public List<Transferencia> buscarTransferenciasPorOrigenIdYDestinoId(Integer ordenante, Integer beneficiario) {
		return transferenciaRepository.findAllByOrdenanteIdAndBeneficiarioId(ordenante, beneficiario);
	}
	
	public Transferencia realizarTransferencia(Transferencia transferencia) {
	    // Guardar la transferencia en la base de datos
	    this.transferenciaRepository.save(transferencia);

	 
	    return transferencia;
	}

}
