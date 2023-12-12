package com.algaworks.algafood.infraestructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

public class RestauranteFactorySpecs {
	
	public static Specification<Restaurante> comFreteGratis() {
		
		return ( root, query, criteriaBuilder  ) -> (
			criteriaBuilder.equal(root.get("taxaFrete"), 0.00d)
		);
		
	}
	
	
	public static Specification<Restaurante> likeNome(String nome) {
		
		return ( root, query, criteriaBuilder  ) -> (
				criteriaBuilder.like(root.get("nome"), "%" + nome + "%")
		);
		
	}	

}
