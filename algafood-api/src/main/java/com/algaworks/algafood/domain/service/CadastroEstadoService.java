package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		return estadoRepository.findAll();
	}
	
	public Estado buscar(Long estadoId) {
		
		return estadoRepository.findById(estadoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Estado com ID %d não encontrado", estadoId)));
	
	}	
	
	public Estado incluir(Estado estado) {

		estado.setId(null);// remover id se informado
		
		// persistir objeto
		return estadoRepository.save(estado);
	}
	
	public Estado alterar(Estado estadoNovosDados, Long estadoId) {
		
		try {

			Estado cadastroEstadoAtual = this.buscar(estadoId);	

			// copia as propriedades de um objeto para outro
			BeanUtils.copyProperties(estadoNovosDados, cadastroEstadoAtual, "id");
			
			// persistir objeto
			return estadoRepository.save(cadastroEstadoAtual);
		
		}
		catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException (
					String.format("Não foi possível ALTERAR objeto ESTADO porque o id %d não foi localizado!", estadoId));			
		}
		
	}
	
	public void remover(Long estadoId) {
		
		try {

			// remover objeto
			estadoRepository.deleteById(estadoId);
		
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Objeto ESTADO nao pode ser removida porque ja esta sendo utilizada por uma CIDADE");
		
		}		
				
	}	

}
