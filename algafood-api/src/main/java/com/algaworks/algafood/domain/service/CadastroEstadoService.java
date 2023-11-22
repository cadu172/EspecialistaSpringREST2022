package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		Estado estado = estadoRepository.buscar(estadoId);
		
		if ( estado == null ) {
			throw new EntidadeNaoEncontradaException (
					String.format("Estado com ID %d não encontrado", estadoId));			
		}
		
		return estado;		
	}	
	
	public Estado incluir(Estado estado) {
		estado.setId(null);// remover id se informado
		
		// persistir objeto
		return estadoRepository.salvar(estado);
	}
	
	public Estado alterar(Estado estado, Long estadoId) {
		
		Estado estadoAlterado = this.buscar(estadoId);
		
		// copia as propriedades de um objeto para outro
		BeanUtils.copyProperties(estado, estadoAlterado, "id");
		
		// persistir objeto
		return estadoRepository.salvar(estado);
	}	
	

	

}
