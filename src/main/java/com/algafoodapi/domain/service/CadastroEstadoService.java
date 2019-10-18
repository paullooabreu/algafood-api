package com.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.model.Estado;
import com.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void remover(Long id) {
		try {
			estadoRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com o código %d", id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
