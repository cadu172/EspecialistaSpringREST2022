package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/Teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping("/cozinhas/listarCozinhasPorNome")
	public List<Cozinha> listarCozinhasPorNome(@RequestParam String nome) {
		return cozinhaRepository.findTodasAsCozinhaByNome(nome);
	}
	
	@GetMapping("/cozinhas/obterCozinhaPorNome")
	public Optional<Cozinha> obterCozinhaPorNome(@RequestParam String nome) {
		return cozinhaRepository.findCozinhaByNome(nome);
	}
	

}
