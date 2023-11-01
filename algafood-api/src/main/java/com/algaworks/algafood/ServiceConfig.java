package com.algaworks.algafood;

//@Configuration
public class ServiceConfig {
	
	/*
	 * 
	 * O Parametro InterfaceNoficador passado no método é um Bean carregado pelo Spring, funciona da mesma forma
	 * quando carregamos uma classe @Component, o Spring Ioc vai procurar a classe que se adapta com este parametro e vai injetar de 
	 * forma automática. Então a classe AtivacaoClienteService torna-se um Bean sem ter a necessidade de usar o @Component e fica "Desacoplada"
	 * */

    //@Bean
	//@Bean(initMethod = "init", destroyMethod = "destroy")
    /*AtivacaoClienteService ativacaoClienteService() {    	
    	//return new AtivacaoClienteService(notificador);
    	return new AtivacaoClienteService();    	
    }*/	
	
}
