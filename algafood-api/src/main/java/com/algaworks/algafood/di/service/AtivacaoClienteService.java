package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.InterfaceNotificador;

@Component
public class AtivacaoClienteService {
	
	/*
	 * Podemos incluir uma parâmetro "required em @Autowired", desta forma dizemos ao Spring se ele é obrigatório ou não, o valor padrão é true
	 * Caso seja definido como true é obrigatório que um Bean deste tipo seja carregado no IoC Container
	 * */
	@Qualifier("notificacao.urgente")
	@Autowired(required = true)
	private InterfaceNotificador notificador;
	
	
	/*
	 * É recomendado manter a anotação @Autowired no construtor da classe, desta forma você consegue fazer a injeção manual se for necessário ou quando for realizar algum teste unitário
	 * Caso você mantenha a injeção no atributo private você vai deixar a responsabilidade total da injeção para seu framework.
	 * */
	
	//@Autowired
	/*public AtivacaoClienteService(InterfaceNotificador notificao) {
		this.notificao = notificao;	
		
	}
	
	public AtivacaoClienteService(String qulquerParametro) {		
	}*/

	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		if ( notificador == null ) {
			System.out.println("Cliente ativado, porém não foi notificado da ativação");
		}
		else {			
	
			notificador.notificar(cliente, "Cliente ativado");
			
		}
		
	}

	/*@Autowired
	public void setNotificao(InterfaceNotificador notificao) {
		this.notificao = notificao;
	}*/


	
	
	
}
