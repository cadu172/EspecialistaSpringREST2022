package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;


public class NotificadorEmail implements InterfaceNotificador {
	
	private boolean upperCase;
	private String smtpHostConfig;
	
	public NotificadorEmail(String smtpHostConfig) {
		System.out.println("NotificadorEmail");
		this.smtpHostConfig = smtpHostConfig;
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		String msg = mensagem;
		
		if ( this.upperCase ) {
			msg =  mensagem.toUpperCase();
		}
		
		System.out.println("Cliente " + cliente.getNome() +
					" notificado por EMAIL atraves do endereço " + cliente.getEmail() +
					" - Mensagem : " + msg + " Atraves do servidor SMTP " + this.smtpHostConfig );
	}
	
	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}	

}
