package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary
//@Qualifier("notificacao.urgente")
@Profile("prod")
@TipoDoNotificador(NivelPrioridade.SEM_URGENCIA)
@Component
public class NotificadorEmail implements InterfaceNotificador {
	
	/*
	 * A anotação @Value permite capturar valores do arquivo application.properties, de forma geral utilizamos este arquivo para as configurações
	 * do aplicativo, isso é necessário para evitar que seja utilizado um "Hardcode", ou seja, que seja registrado no código fonte do aplicativo configurações
	 * que podem mudar de ambiente para ambiente, por exemplo: Uma mudança na rede interna da empresa pode exigir uma recompilação da aplicação e alteração de 
	 * componentes quem não deveriam ser mexidos naquele momento.
	 * 
	 * */
	
	/*@Value("${notificador.email.smtp-server}")
	private String smtpServer;
	
	@Value("${notificador.email.smtp-port}")
	private Integer smtpServerPort;*/
	
	@Autowired
	NotificadorProperties properties;
	

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("SMTP-SERVER: " + properties.getSmtpServer());
		System.out.println("SMTP-PORT: " + properties.getSmtpServerPort());
		
		System.out.println("PRODUCAO ----- Cliente " + cliente.getNome() +
					" notificado por EMAIL atraves do endereço " + cliente.getEmail() +
					" - Mensagem : " + mensagem );
	}

}
