package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name="taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false) // anotação usada para definir qual a coluna da Entity restaurante deve ser a Foreign Key da tabela Cozinha
	private Cozinha cozinha;
	
	/*
	 * estou deixando este relacionamento em Muitos Restaurantes aceitam a forma de pagamento "X" porém por regra isso vai definir que o restaurante
	 * vai aceitar somente uma forma de pagamento porque será criada uma coluna na tabela restaurante com a forma de pagemento.
	 * O Certo neste caso seria construir uma Entity auxiliar que possa guardar o relacionamento N para N ou seja, um Restaurante pode aceitar "Uma ou Várias" formas
	 * de pagamento como "Uma forma de pagamento pode estar presente em muitos Restaurantes"*/
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
}
