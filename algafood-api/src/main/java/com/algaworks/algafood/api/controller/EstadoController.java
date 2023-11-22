package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return cadastroEstadoService.listar();
	}
	
	@GetMapping("{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
		
		try {
			
			Estado estado = cadastroEstadoService.buscar(estadoId);
			
			return ResponseEntity.ok(estado);					
			
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		
		
	}	
	
	
	@PostMapping
	public ResponseEntity<?> gravar(@RequestBody Estado estado) {
		try {
			
			// executa a gravação
			estado = cadastroEstadoService.incluir(estado);
			
			// retorna o resultado da gravação
			return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(estado);
		}
		catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}
	
	@PutMapping("{estadoId}")
	public ResponseEntity<?> alterar(@RequestBody Estado estado,
				@PathVariable("estadoId") Long estadoId) {
		try {
			
			estado = cadastroEstadoService.alterar(estado, estadoId);
			
			// retorna o resultado da gravação
			return ResponseEntity
						.status(HttpStatus.OK)
						.body(estado);
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
