package com.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafoodapi.domain.model.Cidade;
import com.algafoodapi.domain.model.Estado;
import com.algafoodapi.domain.repository.CidadeRepository;
import com.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId =  cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
			String.format("Não existe cadastro de estado com o código %d", estadoId));
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}

	public void deletar(Long id) throws Exception{
		try {
			cidadeRepository.remover(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com o codigo %d", id));
		} 
	}

}
