package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	List<Restaurante> queryByTaxaFreteBetween(Double taxaFreteInicial, Double taxaFreteFinal);
	
	// isso aqui é bruxaria rsrs
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nomeRestaurante, Long cozinhaId);
	
	//@Query("from Restaurante where nome like %:nomeRestaurante% and cozinha.id = :id") //outra bruxaria
	List<Restaurante> findByNomeContainingAndCozinhaId(String nomeRestaurante,@Param("id") Long cozinhaId);
	
	// procura o restaurante por nome contendo a cadeia de caracteres e retorna o primeiro registro
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nomeRestaurante);
	
	// procura o restaurante por nome contendo a cadeia de caracteres e retorna os dois primeiros registros
	List<Restaurante> findTop2ByNomeContaining(String nomeRestaurante);
	
	// contar a quantidade de restaurantes que iniciam com a cadeia de caracteres
	int countByNomeContaining(String nomeRestaurante);
	
	// contar a quantidade de restaurantes possuem a cozinha indicada
	int countByCozinhaId(Long cozinhaId);
	
}
