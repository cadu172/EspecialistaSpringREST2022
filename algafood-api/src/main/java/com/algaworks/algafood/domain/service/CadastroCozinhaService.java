package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha novaCozinha) {
		return cozinhaRepository.save(novaCozinha);
	}
	
	/*public List<Cozinha> listarPorNome(String nomeDaCozinha) {		
		return this.cozinhaRepository.listarPorNome(nomeDaCozinha);		
	}*/
	
	public Optional<Cozinha> buscarPorId(Long id) {
		
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		
		if ( cozinha == null ) {
			throw new EntidadeNaoEncontradaException(String.format("Cozinha id %d não encontrada", id));
		}
		
		return cozinha;
	}
	
	public void excluir(Long id) {
		
		try {
			cozinhaRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Cozinha nao pode ser removida porque ja esta sendo utilizada por um Restaurante");
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cozinha não pode ser excluída porque o id " + id + " não foi localizado");
		}
		
	}
	
}
