package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

/*@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/cozinhas",produces = MediaType.APPLICATION_XML_VALUE)*/

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {		
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscarPorId(@PathVariable("cozinhaId") Long id) {		
		
		try {
			
			Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
			
			if (  cozinha.isPresent() ) {
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(cozinha.get());

			}
			else {
				
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.build();
				
			}			
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.build();
		}
		
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {	
		
		try {
						
			Cozinha novaCozinha = cozinhaRepository.save(cozinha);
			
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
			
			Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId).get();
			
			if ( cozinhaAtual == null ) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body( String.format("Impossivel ATUALIZAR COZINHA, id %d Não encontrado", cozinhaId) );
						
			}
			
			/* Esta rotina copia as propriedades de uma classe para outra do mesmo tipo
			 * o terceiro parametro são as propriedades que devem ser ignoradas pela rotina.
			 * Um outra forma de fazer é copiando propriedade por propriedade
			 * Exemplo: novaCozinha.setNome(cozinhaBodyPUT.getNome())
			 */
			BeanUtils.copyProperties(cozinhaBodyPUT, cozinhaAtual, "id");
			
			// salva e retorna a cozinha com as correções no update
			cozinhaAtual = cozinhaRepository.save(cozinhaAtual);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(cozinhaAtual);			
		
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
	public ResponseEntity<?> remover(@PathVariable("cozinhaId") Long cozinhaId) {
		
		try {
			
			Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId).get();
			
			if ( cozinhaAtual == null ) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body( String.format("Impossivel EXCLUIR COZINHA, id %d Não encontrado", cozinhaId) );
						
			}
			
			cozinhaRepository.delete(cozinhaAtual);

			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();			
		}
		catch(DataIntegrityViolationException e) {			
			
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("Cozinha id "+cozinhaId+" nao pode ser excluida porque está em uso");
			
		}

		catch(EntidadeNaoEncontradaException e) {			
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
			
		}
		
		
	}
	
	
}
