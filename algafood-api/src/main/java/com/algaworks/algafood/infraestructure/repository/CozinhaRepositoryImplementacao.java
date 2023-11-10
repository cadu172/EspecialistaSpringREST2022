package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@Repository
public class CozinhaRepositoryImplementacao implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar() {
		
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
		
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}	
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		
		/*
		 * só é possível excluir objetos gerenciados, no caso o objeto cozinha que foi passado não é gerenciado e está "detached"
		 * Basta fazer a busca que ele se torna gerenciado e habilita a possibilidade de exclusao
		 * */
		
		cozinha = this.buscar(cozinha.getId()); // aqui o objeto passa a ser gerenciado porque foi retornado pelo método find do "EntityManager"
		
		manager.remove(cozinha);
	}

}
