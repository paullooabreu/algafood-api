package com.algafoodapi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.model.Estado;
import com.algafoodapi.domain.repository.EstadoRepository;
import com.algafoodapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Estado estado) {
		try {
			Estado estadoSalvo = cadastroEstadoService.salvar(estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> remover(@PathVariable Long id) {
		try {
			cadastroEstadoService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarId(@PathVariable Long id) {
		Estado estado = estadoRepository.buscar(id);

		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();
	}

}
