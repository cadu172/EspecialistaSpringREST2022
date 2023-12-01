package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryCustom {
	List<Restaurante> find(String nome, Double taxaFreteInicial, Double taxaFreteFinal);
}