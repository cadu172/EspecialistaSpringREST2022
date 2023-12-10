package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, Double taxaFreteInicial, Double taxaFreteFinal) {
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);
		
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		Predicate likeNome = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		
		Predicate taxaFreteBetweenStart = criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFreteBetweenEnd = criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		criteria.where(likeNome, taxaFreteBetweenStart, taxaFreteBetweenEnd);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
		
	}

}
