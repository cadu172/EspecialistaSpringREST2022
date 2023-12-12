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
import com.algaworks.algafood.infraestructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infraestructure.repository.spec.RestauranteNomeLikeSpec;

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
	
	@GetMapping("/restaurantes/queryByTaxaFreteBetween")
	public List<Restaurante> queryByTaxaFreteBetween (
			@RequestParam("taxaFreteInicial") Double taxaFreteInicial,
			@RequestParam("taxaFreteFinal") Double taxaFreteFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/findByNomeContainingAndCozinhaId")
	public List<Restaurante> findByNomeContainingAndCozinhaId (
			@RequestParam("nomeRestaurante") String nomeRestaurante,
			@RequestParam("cozinhaId") Long cozinhaId) {		
		return restauranteRepository.findByNomeContainingAndCozinhaId(nomeRestaurante, cozinhaId);
	}
	
	@GetMapping("/restaurantes/findFirstRestauranteByNomeContaining")
	public Optional<Restaurante> findFirstRestauranteByNomeContaining (@RequestParam("nomeRestaurante") String nomeRestaurante) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nomeRestaurante);
	}	
	
	
	@GetMapping("/restaurantes/findTop2ByNomeContaining")
	public List<Restaurante> findTop2ByNomeContaining (@RequestParam("nomeRestaurante") String nomeRestaurante) {
		return restauranteRepository.findTop2ByNomeContaining(nomeRestaurante);
	}
	
	@GetMapping("/restaurantes/countByNomeContaining")
	public int countByNomeContaining (@RequestParam("nomeRestaurante") String nomeRestaurante) {
		return restauranteRepository.countByNomeContaining(nomeRestaurante);
	}

	@GetMapping("/restaurantes/countByCozinhaId")
	public int countByCozinhaId (@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}	
	
	@GetMapping("/cozinhas/existsByNome")
	public boolean existsByNome (@RequestParam("nomeDaCozinha") String nomeDaCozinha) {
		return cozinhaRepository.existsByNome(nomeDaCozinha);
	}
	
	@GetMapping("/restaurantes/find")
	public List<Restaurante> find (
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "taxaFreteInicial", required = false) Double taxaFreteInicial,
			@RequestParam(value = "taxaFreteFinal", required = false) Double taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> find (
			@RequestParam(value = "nome", required = false) String nome) {
		
		RestauranteComFreteGratisSpec freteGratis = new RestauranteComFreteGratisSpec();
		RestauranteNomeLikeSpec likeNome = new RestauranteNomeLikeSpec(nome);
		
		return restauranteRepository.findAll(freteGratis.and(likeNome));
	}	
	
	@GetMapping("/restaurantes/buscar-primeiro-registro")
	public Optional<Restaurante> buscarPrimeiroRegistroRestaurante () {
		
		return restauranteRepository.buscarPrimeiro();
	}	
	
	@GetMapping("/cozinhas/buscar-primeiro-registro")
	public Optional<Cozinha> buscarPrimeiroRegistroCozinha () {
		
		return cozinhaRepository.buscarPrimeiro();
	}		
	
}
