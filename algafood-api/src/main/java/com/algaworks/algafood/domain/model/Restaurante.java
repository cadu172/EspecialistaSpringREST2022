package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
		joinColumns = {@JoinColumn(name = "restaurante_id") },
		inverseJoinColumns = { @JoinColumn(name = "forma_pagamento_id") })
	
	private List<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
	
}
