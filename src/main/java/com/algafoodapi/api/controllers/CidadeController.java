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

import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.repository.CidadeRepository;
import com.algafoodapi.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		Cidade cidadeSalva = cadastroCidadeService.salvar(cidade);

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> remover(@PathVariable Long id) throws Exception {
		try {
			cadastroCidadeService.deletar(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Long id) {
		Cidade cidade = cidadeRepository.buscar(id);

		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.notFound().build();
	}

}
