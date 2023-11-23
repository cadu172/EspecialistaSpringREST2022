package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@GetMapping
	public List<Cidade> listar() {
		
		return cadastroCidadeService.listar();
		
	}
	
	@GetMapping("{cidadeId}")
	public ResponseEntity<?> buscar(@PathVariable("cidadeId") Long cidadeId) {
		
		try {
			
			Cidade cidade = cadastroCidadeService.buscar(cidadeId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cidade);
			
		}
		catch ( EntidadeNaoEncontradaException e ) {

			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		
		}
		catch ( Exception e ) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody Cidade cidade) {
		
		try {
			
			cidade = cadastroCidadeService.incluir(cidade);
		
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(cidade);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		catch ( Exception e ) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		
		}
		
	}
	
	
	@PutMapping("{cidadeId}")
	public ResponseEntity<?> alterar(@RequestBody Cidade cidade,
			@PathVariable("cidadeId") Long cidadeId) {
		
		try {
			
			cidade = cadastroCidadeService.alterar(cidade, cidadeId);
		
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cidade);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		catch ( Exception e ) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		
		}
		
	}	
	
	
	@DeleteMapping("{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable("cidadeId") Long cidadeId) {
		try {
			
			cadastroCidadeService.remover(cidadeId);
			
			// retorna o resultado da gravação
			return ResponseEntity
						.status(HttpStatus.NO_CONTENT)
						.build();
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		
	}	

}
