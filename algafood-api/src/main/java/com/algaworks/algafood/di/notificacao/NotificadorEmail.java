package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary
//@Qualifier("notificacao.urgente")
@Profile("prod")
@TipoDoNotificador(NivelPrioridade.SEM_URGENCIA)
@Component
public class NotificadorEmail implements InterfaceNotificador {
	
	public NotificadorEmail() {
		System.out.println("NotificadorEmail");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("PRODUCAO ----- Cliente " + cliente.getNome() +
					" notificado por EMAIL atraves do endereço " + cliente.getEmail() +
					" - Mensagem : " + mensagem );
	}

}
