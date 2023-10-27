package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorEmail implements InterfaceNotificador {
	
	@Override
	public void notificar(Cliente cliente, String Mensagem) {
		System.out.println("Cliente " + cliente.getNome() +
					" notificado por EMAIL atraves do endereço " + cliente.getEmail() +
					" - Mensagem : " + Mensagem );
	}

}
