package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class EstadoRepositoryImplementacao implements EstadoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager
				.createQuery("from Estado", Estado.class)
				.getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		
		Estado estado = manager.find(Estado.class, id);
		
		if ( estado == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return estado;
		
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Long estadoId) {
		
		Estado estado = this.buscar(estadoId);
		
		// remover objeto usando o EntityManager do JPA
		manager.remove(estado);
		
	}

}
