package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	// só de declarar asssim o Spring cria automáticamente uma rotina que faz a busca por nome (exato)
	List<Cozinha> nome(String nomeDaCozinha);	
	
	// Desta forma também faz a busca exata, basta informar o prefixo "findBy" + "nome do campo", conforme exemplo abaixo
	// exemplo: findByNome
	List<Cozinha> findByNome(String nomeDaCozinha);
	
	/*
	 * quais são os prefixos?
	 * 
	 * "find" e "By"
	 * 
	 * Voce pode compor um método com nome customizado, exemplo:  findTodasAsCozinhaByNome
	 * 
	 * */
	//obtem uma lista de cozinhas
	List<Cozinha> findTodasAsCozinhaByNome(String nomeDaCozinha);
	
	
	// busca por nome onde o nome contenha a string passada como parametros (equivalente ao Like do SQL)
	List<Cozinha> findByNomeContaining(String nomeDaCozinha);
	
	
	// obtem uma unica cozinha
	Optional<Cozinha> findCozinhaByNome(String nomeDaCozinha);
	
	// verificar se cozinha existe
	boolean existsByNome(String nomeDaCozinha);
}
