package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CidadeRepositoryImplementacao implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {

		Cidade cidade = manager.find(Cidade.class, id);

		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return cidade;
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {

		try {

			Cidade novaCidade = manager.merge(cidade);

			// retorna o novo registro de cidade
			return novaCidade;

		}
		catch (EntityNotFoundException e) {

			String mensagemDeErro = e.getMessage();

			if (e.getMessage().contains("Unable to find com.algaworks.algafood.domain.model.Estado with id")) {
				mensagemDeErro = String.format("Impossível registrar CIDADE, ESTADO id %d NÃO EXISTE",
						cidade.getEstado().getId());
			}

			throw new EntidadeNaoEncontradaException(mensagemDeErro);
		}

	}

	@Override
	@Transactional
	public void remover(Long cidadeId) {
		Cidade cidade = this.buscar(cidadeId);
		manager.remove(cidade);
	}

}
