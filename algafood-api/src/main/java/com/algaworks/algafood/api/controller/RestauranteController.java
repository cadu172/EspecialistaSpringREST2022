package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> listar() {		
		return cadastroRestauranteService.listar();
	}
	
	@GetMapping("{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") Long restauranteId) {		
		
		try {
			
			Restaurante restaurante = cadastroRestauranteService.buscar(restauranteId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(restaurante);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
			
		}		 
		
	}
	
	@PostMapping	
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		
		try {
			
			restaurante = cadastroRestauranteService.salvar(restaurante);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(restaurante);					
			
		}
		catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
			
		}
		
	}

}
