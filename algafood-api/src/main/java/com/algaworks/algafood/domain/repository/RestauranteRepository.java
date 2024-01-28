package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
	RestauranteRepositoryCustom,
	JpaSpecificationExecutor<Restaurante> {
	
	/**
	 * o join é feito com as propriedades da classe Restaurante, no exemplo abaixo tem as propriedades cozinha e formasPagamento da classe Restaurante
	 * internamente o JPA/Hibernate constroi o comando SQL com base neste JPQL 
	 */
	@Override
	@Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento ") **** JPQL não está gerando o comando SQL com INNER JOIN em todas as tabelas, é como se ele não estivesse fazendo @override do método findAll()
	List<Restaurante> findAll();
	
	List<Restaurante> queryByTaxaFreteBetween(Double taxaFreteInicial, Double taxaFreteFinal);
	
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
