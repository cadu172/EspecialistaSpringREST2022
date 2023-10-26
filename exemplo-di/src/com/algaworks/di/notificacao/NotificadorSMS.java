package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorSMS  implements InterfaceNotificador  {
	
	public void notificar(Cliente cliente, String Mensagem){
		System.out.println("Cliente " + cliente.getNome() + 
					" notificado por SMS atraves do telefone " + cliente.getTelefone() + 
					" - Mensagem : " + Mensagem );
	}
	
}
