package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> listar() {		
		List<Restaurante> restaurantes = cadastroRestauranteService.listar();		
		/*System.out.println(restaurantes.get(0).getNome());		
		restaurantes.get(0).getFormasPagamento().forEach(System.out::println);*/		
		return restaurantes;
	}
	
	@GetMapping("/{restauranteId}")
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
			
			restaurante.setId(null); //forçar ficar sem ID
			restaurante = cadastroRestauranteService.incluir(restaurante);
			
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
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> alterar(@RequestBody Restaurante restaurante,
			@PathVariable("restauranteId") Long restauranteId) {
		
		try {
			
			restaurante = cadastroRestauranteService.alterar(restaurante, restauranteId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(restaurante);					
			
		}
		catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> alterarParcial(@PathVariable("restauranteId") Long restauranteId,
			@RequestBody Map<String, Object> requestBody) {
		
		
		try {
			
			Restaurante restauranteDestino = cadastroRestauranteService.buscar(restauranteId);
			
			// faz o merge somente dos campos enviados na consulta, o restante ele não altera
			merge(requestBody, restauranteDestino);
			
			restauranteDestino = cadastroRestauranteService.alterar(restauranteDestino, restauranteId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(restauranteDestino);				
			
		}
		catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		
	}

	private void merge(Map<String, Object> requestBody, Restaurante restauranteDestino) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// converte o requestBody em um tipo Restaurantes
		Restaurante restauranteOrigem = objectMapper.convertValue(requestBody, Restaurante.class);		
		
		// percorre o requestBody para "fazer um set" das requisições no objeto destino
		requestBody.forEach((chave, valor) -> {
			
			// Este Reflections procura no objeto o "atributo atual" armazenado na variável chave
			Field nomeCampo = ReflectionUtils.findField(Restaurante.class, chave);
			
			// coverte o atributo da classe de private para public em tempo de execução
			nomeCampo.setAccessible(true);
			
			// Este reflection procura o campo encontrado no requestBody e obtem o valor do campo
			Object novoValor = ReflectionUtils.getField(nomeCampo, restauranteOrigem);
			
			// Este utilitário altera no objeto final (encontrado no banco) o valor passado no requestBody no campo atual
			ReflectionUtils.setField(nomeCampo, restauranteDestino, novoValor);
			
		});
	}	

}
