package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.di.notificacao.NotificadorEmail;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

public class AlgaConfig {
	
	/*
	 * 
	 * Quando criamos um Bean em uma classe de configuração, não podemos ter vários "Beans" 
	 * que retornam o mesmo tipo, senão o Spring não sabe qual ele vai usar e acusa um erro
	 * Fiz um teste copiando o método notificadorEmail() com outro nome e a app quebrou
	 * */

    @Bean
    NotificadorEmail notificadorEmail() {
		
		NotificadorEmail notificador = new NotificadorEmail("smtp.goole.com");
		notificador.setUpperCase(true);
		
		return notificador;
		
	}
    
    @Bean
    AtivacaoClienteService ativacaoClienteService() {
    	
    	return new AtivacaoClienteService(this.notificadorEmail());	
    	
    }
    
}
