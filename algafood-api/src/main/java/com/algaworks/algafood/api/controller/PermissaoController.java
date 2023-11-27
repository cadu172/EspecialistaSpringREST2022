package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/Permissoes")
public class PermissaoController {
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@GetMapping
	public List<Permissao> listar() {
		return cadastroPermissaoService.listar();
	}
	
	@GetMapping("/{permissaoId}")
	public ResponseEntity<?> buscar(@PathVariable("permissaoId") Long permissaoId) {
		
		try {
			
			Permissao permissao = cadastroPermissaoService.buscar(permissaoId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(permissao);
			
		}
		catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
			
		}
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> incluit(@RequestBody Permissao permissao) {
		
		try {
			
			// remover ID se for enviado no JSON
			permissao.setId(null);
			
			permissao = cadastroPermissaoService.incluir(permissao);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(permissao);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
			
		}
		
	}
	
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<?> alterar(@RequestBody Permissao permissao,
			@PathVariable Long permissaoId) {
		
		try {
			
			permissao = cadastroPermissaoService.alterar(permissao, permissaoId);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(permissao);
			
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
	
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<?> excluir(@PathVariable Long permissaoId) {
		
		try {
			
			cadastroPermissaoService.excluir(permissaoId);
			
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
	
	@PatchMapping("/{permissaoId}")
	public ResponseEntity<?> alterarParcial(@PathVariable("permissaoId") Long permissaoId,
			@RequestBody Map<String, Object> requestBody) {
		
		try {
			
			Permissao permissao = cadastroPermissaoService.buscar(permissaoId);
			
			// faz o merge somente dos campos enviados na consulta, o restante ele não altera
			merge(requestBody, permissao);
			
			// reutilizar rotina de alteração que já está pronta
			permissao = cadastroPermissaoService.alterar(permissao, permissaoId);
			
			// retornar entidade atualizada
			return ResponseEntity
						.status(HttpStatus.OK)
						.body(permissao);
			
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
	
	private void merge(Map<String, Object> requestBody, Permissao permissaoDestino) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// converte o requestBody em um tipo Restaurantes
		Permissao permissaoOrigem = objectMapper.convertValue(requestBody, Permissao.class);
		
		// percorre o requestBody para "fazer um set" das requisições no objeto destino
		requestBody.forEach((chave, valor) -> {
			
			// Este Reflections procura no objeto o "atributo atual" armazenado na variável chave
			Field nomeCampo = ReflectionUtils.findField(Permissao.class, chave);
			
			// coverte o atributo da classe de private para public em tempo de execução
			nomeCampo.setAccessible(true);
			
			// Este reflection procura o campo encontrado no requestBody e obtem o valor do campo
			Object novoValor = ReflectionUtils.getField(nomeCampo, permissaoOrigem);
			
			// Este utilitário altera no objeto final (encontrado no banco) o valor passado no requestBody no campo atual
			ReflectionUtils.setField(nomeCampo, permissaoDestino, novoValor);
			
		});
	}	
	

}
