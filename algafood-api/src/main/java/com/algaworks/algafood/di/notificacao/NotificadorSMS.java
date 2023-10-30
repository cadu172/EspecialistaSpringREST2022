package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Primary
@Component
public class NotificadorSMS implements InterfaceNotificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("Cliente " + cliente.getNome() +
					" notificado por SMS atraves do telefone " + cliente.getTelefone() +
					" - Mensagem : " + mensagem );
	}

}
