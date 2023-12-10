package com.algaworks.algafood.infraestructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if ( StringUtils.hasText(nome)  ) {
			predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if ( taxaFreteInicial != null ) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		if ( taxaFreteFinal != null ) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
	
		criteria.where( predicates.toArray(new Predicate[predicates.size()]) );
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
		
	}

}
