package com.algaworks.algafood;

import com.algaworks.algafood.di.notificacao.InterfaceNotificador;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class ServiceConfig {
	
	/*
	 * 
	 * O Parametro InterfaceNoficador passado no método é um Bean carregado pelo Spring, funciona da mesma forma
	 * quando carregamos uma classe @Component, o Spring Ioc vai procurar a classe que se adapta com este parametro e vai injetar de 
	 * forma automática. Então a classe AtivacaoClienteService torna-se um Bean sem ter a necessidade de usar o @Component e fica "Desacoplada"
	 * */

    //@Bean
    AtivacaoClienteService ativacaoClienteService(InterfaceNotificador notificador) {
    	
    	return new AtivacaoClienteService(notificador);	
    	
    }	
	
}
