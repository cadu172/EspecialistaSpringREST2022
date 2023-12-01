package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, Double taxaFreteInicial, Double taxaFreteFinal) {
		
		var jpql = "from Restaurante where "+
				"nome like :nome and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaFreteInicial", taxaFreteInicial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	
	}

}
