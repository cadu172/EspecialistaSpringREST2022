package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

/*@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_XML_VALUE)*/

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	//@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public List<Cozinha> listar() {		
		return cadastroCozinhaService.listar();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {		
		
		try {
			
			Cozinha cozinha = cadastroCozinhaService.buscar(id);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cozinha);
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.build();
		}
		
		
	}
	
	@PostMapping
	//@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> incluir(@RequestBody Cozinha cozinha) {	
		
		try {
						
			Cozinha novaCozinha = cadastroCozinhaService.incluir(cozinha);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(novaCozinha);
		
		}
		catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());					
		}
				
	}

	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<?> atualizar(@RequestBody Cozinha cozinhaBodyPUT,
				@PathVariable("cozinhaId") Long cozinhaId) {
		
		try {
			
			Cozinha cozinha = cadastroCozinhaService.alterar(cozinhaBodyPUT, cozinhaId);
		
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cozinha);			
		
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}		
		catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());			
		}
		
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> excluir(@PathVariable("cozinhaId") Long cozinhaId) {
		
		try {
			
			cadastroCozinhaService.excluir(cozinhaId);

			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();			
		}
		catch(DataIntegrityViolationException e) {			
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Cozinha id "+cozinhaId+" nao pode ser excluida porque está em uso");
			
		}

		
	}
	
}
