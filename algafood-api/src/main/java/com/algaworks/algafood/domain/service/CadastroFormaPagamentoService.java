package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}
	
	public FormaPagamento buscar(Long formaPagamentoId) {
		
		return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Forma de pagamento não encontrada"));
		
	}
	
	public FormaPagamento incluir(FormaPagamento formaPagamento) {
		
		return formaPagamentoRepository.save(formaPagamento);
		
	}
	
	public FormaPagamento alterar(FormaPagamento formaPagamentoNovosDados, Long formaPagamentoId) {
		
		try {
			
			FormaPagamento formaPagamentoAtual = this.buscar(formaPagamentoId);
			
			BeanUtils.copyProperties(formaPagamentoNovosDados, formaPagamentoAtual, "id");
			
			return formaPagamentoRepository.save(formaPagamentoAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException("Impossível atualizar Forma de Pagamento "+
					"Selecionada, id "+formaPagamentoId+" informado não localizado");
			
		}
		
	}
	
	public void excluir(Long formaPagamentoId) {
		
		try {
			
			this.buscar(formaPagamentoId);
			
			formaPagamentoRepository.deleteById(formaPagamentoId);
		
		}
		catch (EntidadeNaoEncontradaException e) {
		
			throw new EntidadeNaoEncontradaException(
					String.format("Impossível EXCLUIR Objeto, ID %d Não encontrado", formaPagamentoId));
		
		}
		catch (DataIntegrityViolationException e) {
			
			//e.printStackTrace();
			
			throw new EntidadeEmUsoException("Forma de Pagamento não pode ser excluida porque existem objetos que dependem dela");
		
		}		
		
	}	

}
