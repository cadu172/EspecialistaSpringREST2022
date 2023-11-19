package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhaXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_XML_VALUE)*/

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	//@GetMapping(value = "/listar")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {		
		return cozinhaRepository.listar();
	}
	
	@JsonIgnoreProperties
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhaXmlWrapper listarXML() {
		return new CozinhaXmlWrapper(cozinhaRepository.listar());
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {		
		
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		
		if (  cozinha == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(cozinha);		
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}

	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinhaBodyPUT,
				@PathVariable("cozinhaId") Long cozinhaId) {
		
		Cozinha novaCozinha = cozinhaRepository.buscar(cozinhaId);
		
		if ( novaCozinha == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		/* Esta rotina copia as propriedades de uma classe para outra do mesmo tipo
		 * o terceiro parametro são as propriedades que devem ser ignoradas pela rotina.
		 * Um outra forma de fazer é copiando propriedade por propriedade
		 * Exemplo: novaCozinha.setNome(cozinhaBodyPUT.getNome())
		 */
		BeanUtils.copyProperties(cozinhaBodyPUT, novaCozinha, "id");
		
		novaCozinha = cozinhaRepository.salvar(novaCozinha);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(novaCozinha);				
		
	}
	
	
}
