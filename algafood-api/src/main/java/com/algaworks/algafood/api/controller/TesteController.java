package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping("/buscarPorNome")
	public List<Cozinha> buscarPorNome(@RequestParam("nomeCozinha") String nomeCozinha) {
		
		return cadastroCozinhaService.listarPorNome(nomeCozinha);
		
	}
}
