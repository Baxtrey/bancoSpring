package com.banco.bancobackend.controller;

import java.util.ArrayList;
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

import com.banco.bancobackend.model.Transferencia;
import com.banco.bancobackend.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciaController {

	@Autowired
	TransferenciaService transferenciaService;

	@GetMapping()
	public List<Transferencia> obtenerTransferencias() {
		return this.transferenciaService.leerTransferencias();
	}

	@GetMapping("/{id}")
	public Optional<Transferencia> obtenerTransferencia(@PathVariable("id") Integer id) {
		return this.transferenciaService.leerTransferencia(id);
	}

	@PostMapping()
	public Transferencia guardarTransferencia(@RequestBody Transferencia transferencia) {
		return this.transferenciaService.guardarTransferencia(transferencia);
	}

	@DeleteMapping("/{id}")
	public void borrarTransferencia(@PathVariable("id") Integer id) {
		this.transferenciaService.borrarTransferenciaPorId(id);
	}

	@GetMapping("/ordenante/{id}")
	public ArrayList<Transferencia> obtenerTransferenciaporOrdenante(@PathVariable("id") Integer id) {
		return this.transferenciaService.buscarTransferenciaPorOrdenanteId(id);
	}

	@GetMapping("/beneficiario/{id}")
	public ArrayList<Transferencia> obtenerTransferenciaporBeneficiario(@PathVariable("id") Integer id) {
		return this.transferenciaService.buscarTransferenciaPorBeneficiarioId(id);
	}

	@PostMapping("/realizar")
	public Transferencia realizarTransferencia(@RequestBody Transferencia transferencia) {
		return this.transferenciaService.realizarTransferencia(transferencia);
	}

}
