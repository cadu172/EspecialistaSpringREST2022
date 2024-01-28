package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}
	
	public Grupo buscar(Long grupoId) {
		
		return grupoRepository.findById(grupoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Grupo id %d não encontrada", grupoId)));
		
	}
	
	public Grupo incluir(Grupo grupo) {
		
		// remover id se for enviado no objeto
		grupo.setId(null);
		
		return grupoRepository.save(grupo);
		
	}
	
	public Grupo alterar(Grupo grupoNovosDados, Long grupoId) {
		
		try {
			
			Grupo grupoAtual = this.buscar(grupoId);
			
			BeanUtils.copyProperties(grupoNovosDados, grupoAtual, "id");
			
			return grupoRepository.save(grupoAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new EntidadeNaoEncontradaException("Impossível atualizar Grupo "+
					"Selecionada, id "+grupoId+" informado não localizado");
			
		}
		
	}
	
	public void excluir(Long id) {
		
		try {
			
			this.buscar(id);
			
			grupoRepository.deleteById(id);
		
		}
		catch (EntidadeNaoEncontradaException e) {
		
			throw new EntidadeNaoEncontradaException(
					String.format("Impossível EXCLUIR Objeto, ID %d Não encontrado", id));
		
		}
		catch (DataIntegrityViolationException e) {
			
			//e.printStackTrace();
			
			throw new EntidadeEmUsoException("Grupo não pode ser excluida porque existem objetos que dependem dela");
		
		}		
		
	}	

}
