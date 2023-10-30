package com.algaworks.algafood;

//@Configuration
public class NotificacaoConfig {
	
	/*
	 * 
	 * Quando criamos um Bean em uma classe de configuração, não podemos ter vários "Beans" 
	 * que retornam o mesmo tipo, senão o Spring não sabe qual ele vai usar e acusa um erro
	 * Fiz um teste copiando o método notificadorEmail() com outro nome e a app quebrou
	 * */

    //@Bean
    /*NotificadorEmail notificadorEmail() {
		
		NotificadorEmail notificador = new NotificadorEmail("smtp.goole.com");
		notificador.setUpperCase(true);
		
		return notificador;
		
	}*/	

}
