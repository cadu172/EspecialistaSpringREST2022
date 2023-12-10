package com.algaworks.algafood.infraestructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

//@AllArgsConstructor   gerar automaticamente o construtor e os parametros
public class RestauranteNomeLikeSpec implements Specification<Restaurante> {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	public RestauranteNomeLikeSpec(String nome) {
		this.nome = nome;
	}

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.like(root.get("nome"), "%" + this.nome + "%");
	}

}
