package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	 
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}
	
	public Cidade buscar(Long cidadeId) {
		
		try {
			
			return cidadeRepository.buscar(cidadeId);
			
		}
		catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException (
					String.format("Cidade com ID %d não encontrada", cidadeId));			

		}
		
	}
	
	public Cidade incluir(Cidade cidade)
			throws EntidadeNaoEncontradaException, Exception {

		// retirar ID
		cidade.setId(null);
		
		return cidadeRepository.salvar(cidade);			
		
	}
	
	public Cidade alterar(Cidade cidadeNovosDados, Long cidadeId)
			throws EntidadeNaoEncontradaException, Exception {
		
		Cidade cadastroCidadeAtual = this.buscar(cidadeId);	

		// copia as propriedades de um objeto para outro
		BeanUtils.copyProperties(cidadeNovosDados, cadastroCidadeAtual, "id");
		
		// persistir objeto
		return cidadeRepository.salvar(cadastroCidadeAtual);	
		
	}
	
	public void remover(Long cidadeId) throws Exception {
		try {

			// remover objeto
			cidadeRepository.remover(cidadeId);
		
		}
		catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException (
					String.format("Não foi possível EXCLUIR objeto CIDADE porque o id %d não foi localizado!", cidadeId));			
		}
				
	}	

}
