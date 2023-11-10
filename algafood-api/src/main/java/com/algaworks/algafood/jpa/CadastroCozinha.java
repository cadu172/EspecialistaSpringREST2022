package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar() {
		
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
		
	}
	
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}	
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	public void remover(Cozinha cozinha) {
		
		/*
		 * só é possível excluir objetos gerenciados, no caso o objeto cozinha que foi passado não é gerenciado e está "detached"
		 * Basta fazer a busca que ele se torna gerenciado e habilita a possibilidade de exclusao
		 * */
		
		cozinha = this.buscar(cozinha.getId()); // aqui o objeto passa a ser gerenciado porque foi retornado pelo método find do "EntityManager"
		
		manager.remove(cozinha);
	}	

}
