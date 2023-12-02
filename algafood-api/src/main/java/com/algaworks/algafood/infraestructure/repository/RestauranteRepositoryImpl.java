package com.algaworks.algafood.infraestructure.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, Double taxaFreteInicial, Double taxaFreteFinal) {
		
		var jpql = new StringBuilder("from Restaurante where true ");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
				
		if ( StringUtils.hasLength(nome) ) {			
			jpql.append(" and nome like :nome");
			parametros.put("nome", "%"+nome+"%");
		}
		
		if (taxaFreteInicial != null ) {			
			jpql.append(" and taxaFrete >= :taxaFreteInicial");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}

		if (taxaFreteFinal != null ) {
			jpql.append(" and taxaFrete <= :taxaFreteFinal");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		
		// para somente 1 comando na expressao lambda
		//parametros.forEach((chave, valor) -> query.setParameter(chave, valor) );
		
		// caso queira mais de um comando no loop
		parametros.forEach((chave, valor) -> {			
			query.setParameter(chave, valor);
			} );

		return query.getResultList();
	
	}

}
