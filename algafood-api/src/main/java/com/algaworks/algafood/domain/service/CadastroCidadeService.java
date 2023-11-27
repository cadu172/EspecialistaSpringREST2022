package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	 
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	public Cidade buscar(Long cidadeId) {
		
		return cidadeRepository.findById(cidadeId).orElseThrow (() -> 
			new EntidadeNaoEncontradaException (String.format("Cidade não encontrada", cidadeId)));
	
	}
	
	public Cidade incluir(Cidade cidade) {
		
		// remover id "se enviado" no json
		cidade.setId(null);
		
		// buscar estado com base no ID informado no JSON
		Estado estado = estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Impossível atualizar cidade, ESTADO informado não consta no cadastro"));
		
		// associar os dados do estado
		cidade.setEstado(estado);		
		
		// persistir no bd
		return cidadeRepository.save(cidade);
		
	}
	
	public Cidade alterar(Cidade cidadeNovosDados, Long cidadeId)
			throws EntidadeNaoEncontradaException {
		
		// verificar se id informado existe na base, caso não exista será lançada uma exceção "EntidadeNaoEncontradaException"
		Cidade cadastroCidadeAtual = this.buscar(cidadeId);
		
		// buscar estado com base no ID informado no JSON
		Estado estado = estadoRepository.findById(cidadeNovosDados.getEstado().getId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Impossível atualizar cidade, ESTADO informado não consta no cadastro"));
		
		// associar os dados do estado
		cidadeNovosDados.setEstado(estado);
		
		// copia as propriedades de um objeto para outro
		BeanUtils.copyProperties(cidadeNovosDados, cadastroCidadeAtual, "id");
		
		// persistir objeto
		return cidadeRepository.save(cadastroCidadeAtual);

	}
	
	public void remover(Long cidadeId) throws Exception {

		// remover objeto
		cidadeRepository.deleteById(cidadeId);		
				
	}	

}
