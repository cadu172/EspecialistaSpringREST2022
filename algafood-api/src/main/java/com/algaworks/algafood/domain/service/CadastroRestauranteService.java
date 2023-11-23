package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}
	
	public Restaurante buscar(Long id) {
		
		try {
			
			Restaurante restaurante = restauranteRepository.buscar(id);
			
			return restaurante;
			
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException (
					String.format("Restaurante %d não encontrado", id));
		}
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.salvar(restaurante);		
	}
	
	public Restaurante alterar(Restaurante restaurante) {
		
		if ( this.buscar(restaurante.getId()) == null ) {
			throw new EntidadeNaoEncontradaException (
					String.format("Impossivel atualizar, restaurante %d nao encontrado!", restaurante.getId()));
		}
		
		return this.salvar(restaurante);
		
	}
	
}
