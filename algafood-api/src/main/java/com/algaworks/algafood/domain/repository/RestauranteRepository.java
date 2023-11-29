package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	List<Restaurante> findByTaxaFreteBetween(Double taxaFreteInicial, Double taxaFreteFinal);
	
	// isso aqui é bruxaria rsrs
	List<Restaurante> findByNomeContainingAndCozinhaId(String nomeCozinha, Long cozinhaId);
}
