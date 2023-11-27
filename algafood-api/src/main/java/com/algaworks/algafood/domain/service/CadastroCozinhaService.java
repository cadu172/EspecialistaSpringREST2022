package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	public Cozinha salvar(Cozinha novaCozinha) {
		return cozinhaRepository.save(novaCozinha);
	}
	
	/*public List<Cozinha> listarPorNome(String nomeDaCozinha) {		
		return this.cozinhaRepository.listarPorNome(nomeDaCozinha);		
	}*/
	
	public Cozinha buscar(Long id) {
		
		Cozinha cozinha = cozinhaRepository
					.findById(id)
					.orElseThrow (() -> new EntidadeNaoEncontradaException(String.format("Cozinha id %d não encontrada", id)) );
		
		return cozinha;
		
	}
	
	public Cozinha incluir(Cozinha cozinha) {
		
		cozinha.setId(null);
		
		return cozinhaRepository.save(cozinha);
	}
	
	public Cozinha alterar(Cozinha cozinhaNovosDados, Long cozinhaId) {
		
		try {
			
			Cozinha cozinhaAtual = this.buscar(cozinhaId);
			
			BeanUtils.copyProperties(cozinhaNovosDados, cozinhaAtual, "id");
			
			return cozinhaRepository.save(cozinhaAtual); 
		
		}
		catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException(
					String.format("Impossivel atualizar cozinha id %d não encontrado", cozinhaId));
		
		}
		
		
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
