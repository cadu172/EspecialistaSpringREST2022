package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}
	
	public Permissao buscar(Long permissaoId) {
		
		return permissaoRepository.findById(permissaoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Permissao id %d não encontrada", permissaoId)));
		
	}
	
	public Permissao incluir(Permissao permissao) {
		
		// remover id se for enviado no objeto
		permissao.setId(null);
		
		return permissaoRepository.save(permissao);
		
	}
	
	public Permissao alterar(Permissao permissaoNovosDados, Long permissaoId) {
		
		try {
			
			Permissao permissaoAtual = this.buscar(permissaoId);
			
			BeanUtils.copyProperties(permissaoNovosDados, permissaoAtual, "id");
			
			return permissaoRepository.save(permissaoAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException("Impossível atualizar Permissao "+
					"Selecionada, id "+permissaoId+" informado não localizado");
			
		}
		
	}
	
	public void excluir(Long permissaoId) {
		
		try {
			
			this.buscar(permissaoId);
			
			permissaoRepository.deleteById(permissaoId);
		
		}
		catch (EntidadeNaoEncontradaException e) {
		
			throw new EntidadeNaoEncontradaException(
					String.format("Impossível EXCLUIR Objeto, ID %d Não encontrado", permissaoId));
		
		}
		catch (DataIntegrityViolationException e) {
			
			//e.printStackTrace();
			
			throw new EntidadeEmUsoException("Permissao não pode ser excluida porque existem objetos que dependem dela");
		
		}		
		
	}	

}
