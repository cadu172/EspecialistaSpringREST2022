package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	// só de declarar asssim o Spring cria automáticamente uma rotina que faz a busca por nome (exato)
	//List<Cozinha> nome(String nomeDaCozinha);	
	
	// Desta forma também faz a busca exata, basta informar o prefixo "findBy" + "nome do campo", conforme exemplo abaixo
	// exemplo: findByNome
	//List<Cozinha> findByNome(String nomeDaCozinha);
	
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
	
	
	// obtem uma unica cozinha
	Optional<Cozinha> findCozinhaByNome(String nomeDaCozinha);
}
