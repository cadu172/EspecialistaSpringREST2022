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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/FormasDePagamento")
public class FormaPagamentoController {
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@GetMapping
	public List<FormaPagamento> listar() {
		return formaPagamentoService.listar();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<?> buscar(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
		
		try {
			
			FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(formaPagamento);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> incluit(@RequestBody FormaPagamento formaPagamento) {
		
		try {
			
			// remover ID se for enviado no JSON
			formaPagamento.setId(null);
			
			formaPagamento = formaPagamentoService.incluir(formaPagamento);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(formaPagamento);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
			
		}
		
	}
	
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<?> alterar(@RequestBody FormaPagamento formaPagamento,
			@PathVariable Long formaPagamentoId) {
		
		try {
			
			formaPagamento = formaPagamentoService.alterar(formaPagamento, formaPagamentoId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(formaPagamento);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> excluir(@PathVariable Long formaPagamentoId) {
		
		try {
			
			formaPagamentoService.excluir(formaPagamentoId);
			
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT).build();
			
		}
		
		catch (EntidadeEmUsoException e) {
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
			
		}		
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
			
		}
		
	}	
	

}
