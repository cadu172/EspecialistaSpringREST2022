package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/Teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	
	@GetMapping("/cozinhas/listarCozinhasPorNome")
	public List<Cozinha> listarCozinhasPorNome(@RequestParam String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/obterCozinhaPorNome")
	public Optional<Cozinha> obterCozinhaPorNome(@RequestParam String nome) {
		return cozinhaRepository.findCozinhaByNome(nome);
	}
	
	@GetMapping("/restaurantes/TaxaFreteEntre")
	public List<Restaurante> findByTaxaFreteBetween (
			@RequestParam("taxaFreteInicial") Double taxaFreteInicial,
			@RequestParam("taxaFreteFinal") Double taxaFreteFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/PorNomeRestauranteIdCozinha")
	public List<Restaurante> findByTaxaFreteBetween (
			@RequestParam("nomeCozinha") String nomeCozinha,
			@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nomeCozinha, cozinhaId);
	}	
	

}
