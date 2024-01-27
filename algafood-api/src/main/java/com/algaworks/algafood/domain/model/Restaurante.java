package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
	
	@JsonIgnore
	@CreationTimestamp //anotação do Hibernate: Retorna a data e hora atual quando um registro é gerado (insert)
	@Column(nullable = false, columnDefinition = "datetime") //indica que a coluna não deve ser nula e deve ser criado com data e hora sem precisão de segundos	
	private LocalDateTime dataCadastro; //indica que a coluna não deve ser nula e deve ser criado com data e hora sem precisão de segundos	
	
	@JsonIgnore
	@UpdateTimestamp //anotação do Hibernate: Retorna a data e hora atual quando um registro é atualizado (update)
	@Column(nullable = false, columnDefinition = "datetime") //indica que a coluna não deve ser nula e deve ser criado com data e hora sem precisão de segundos	
	private LocalDateTime dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false) // anotação usada para definir qual a coluna da Entity restaurante deve ser a Foreign Key da tabela Cozinha
	private Cozinha cozinha;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
		joinColumns = {@JoinColumn(name = "restaurante_id") },
		inverseJoinColumns = { @JoinColumn(name = "forma_pagamento_id") })
	
	private List<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	
}
