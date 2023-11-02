package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
	
	/*
	 * 
	 * A Anotação @ConfigurationProperties é usada para gerar uma classe que representa uma informação que está no arquivo de configuração,
	 * desta forma não precisamos ficar chamando implicitamente a propriedade que for necessário fazer alguma mudança nela, 
	 * caso seja necessário mudar basta somente alterar o arquivo e a classe que tudo será modificado no sistema sem a necessidade de
	 * recompilar todas as classes que usam estas configurações
	 * 
	 * IMPORTANTE: os atributos desta classe devem ser escritos da mesma forma que foram declarados no arquivo de configurações, desta forma o Ioc consegue
	 * identificar o destino de cadas informação. por exemplo:
	 * 
	 * a propriedade: smtp-server-port    você deve criar na classe de configurações uma propriedade privada da seguinte forma:
	 * 	private String smtpServerPort
	 * */
	
	private String smtpServer;
	private Integer smtpServerPort = 25;
	
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	
	public void setSmtpServerPort(Integer smtpServerPort) {
		this.smtpServerPort = smtpServerPort;
	}
	
	public String getSmtpServer() {
		return smtpServer;
	}
	
	public Integer getSmtpServerPort() {
		return smtpServerPort;
	}

	
	
	
	
}
