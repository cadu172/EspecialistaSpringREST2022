package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@Repository
public class RestauranteRepositoryImplementacao implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		
		Restaurante restaurante = manager.find(Restaurante.class, id);
		
		if ( restaurante == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return restaurante;
	}

	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		try {
			return manager.merge(restaurante);
		}
		catch (EntityNotFoundException e) {
			
			String mensagemDeErro = e.getMessage();
			
			if ( e.getMessage().contains("Unable to find com.algaworks.algafood.domain.model.Cozinha")  ) {
				mensagemDeErro = String.format("Nao existe cozinha com o código %d não existe",
						restaurante.getCozinha().getId());
			}
			else if ( e.getMessage().contains("Unable to find com.algaworks.algafood.domain.model.FormaPagamento") ) {
				mensagemDeErro = String.format("Forma de pagamento selecionada código %d não existe",
						restaurante.getFormaPagamento().getId());
			}
			
			throw new EntidadeNaoEncontradaException (mensagemDeErro);
		}
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = this.buscar(restaurante.getId());
		manager.remove(restaurante);		
	}

}
