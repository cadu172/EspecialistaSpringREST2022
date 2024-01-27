package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscar(Long id) {
		
		return restauranteRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException (
						String.format("Restaurante %d não encontrado", id)));
		
	}
	
	public Restaurante incluir(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		//Long formaPagamentoId = restaurante.getFormaPagamento().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Cozinha ID %d não encontrada", cozinhaId)));
		
		//System.out.println("cozinha: " + restaurante.getFormasPagamento().get(0));
		
		restaurante.setCozinha(cozinha);
		restaurante.setFormasPagamento(restaurante.getFormasPagamento());
		
		// verificar depois como é feito para incluir a lista de formas de pagamento
		
		return restauranteRepository.save(restaurante);		
	}
	
	public Restaurante alterar(Restaurante restaurante, Long restauranteId) {
		
		try {
			
			// verificar se restaurante existe
			Restaurante restauranteAtual = this.buscar(restauranteId);
			
			// copiar dados passados
			// obs: o terceiro e o quarto parâmetro são os campos que devemos ignorar ao fazer a cópia dos elementos
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco");			
		
			// caso não tenha exceção na linha de consulta, atualizar
			//return this.incluir(restaurante);
			return restauranteRepository.save(restauranteAtual);

		}
		catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException (
					String.format("Impossivel atualizar, restaurante %d nao encontrado!", restauranteId));
		}
		
	}
	
	public void excluir(Long restauranteId) {
		
		try {
			
			this.buscar(restauranteId);
			
			restauranteRepository.deleteById(restauranteId);
		
		}
		catch (EntidadeNaoEncontradaException e) {
		
			throw new EntidadeNaoEncontradaException(
					String.format("Impossível EXCLUIR Objeto, ID %d Não encontrado", restauranteId));
		
		}
		catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException("Restaurante não pode ser excluido porque existem objetos que dependem dele");
		
		}		
		
	}	
	
}
