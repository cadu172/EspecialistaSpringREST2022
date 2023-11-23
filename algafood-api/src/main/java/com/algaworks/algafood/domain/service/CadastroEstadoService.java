package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listar() {
		return estadoRepository.listar();
	}
	
	public Estado buscar(Long estadoId) {
		
		try {

			Estado estado = estadoRepository.buscar(estadoId);
			
			return estado;
		
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException (
					String.format("Estado com ID %d não encontrado", estadoId));			
		}
	}	
	
	public Estado incluir(Estado estado) {

		estado.setId(null);// remover id se informado
		
		// persistir objeto
		return estadoRepository.salvar(estado);
	}
	
	public Estado alterar(Estado estadoNovosDados, Long estadoId)
			throws Exception {
		
		try {

			Estado cadastroEstadoAtual = this.buscar(estadoId);	

			// copia as propriedades de um objeto para outro
			BeanUtils.copyProperties(estadoNovosDados, cadastroEstadoAtual, "id");
			
			// persistir objeto
			return estadoRepository.salvar(cadastroEstadoAtual);
		
		}
		catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException (
					String.format("Não foi possível ALTERAR objeto ESTADO porque o id %d não foi localizado!", estadoId));			
		}
		
	}
	
	public void remover(Long estadoId) throws Exception {
		try {

			// remover objeto
			estadoRepository.remover(estadoId);
		
		}
		catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException (
					String.format("Não foi possível EXCLUIR objeto ESTADO porque o id %d não foi localizado!", estadoId));			
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Objeto ESTADO nao pode ser removida porque ja esta sendo utilizada por uma CIDADE");
		}
		
				
	}
	

}
