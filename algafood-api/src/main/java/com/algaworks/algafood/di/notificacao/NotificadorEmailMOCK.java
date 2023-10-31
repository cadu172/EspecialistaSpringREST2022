package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary
//@Qualifier("notificacao.urgente")
@Profile("dev")
@TipoDoNotificador(NivelPrioridade.SEM_URGENCIA)
@Component
public class NotificadorEmailMOCK implements InterfaceNotificador {
	
	public NotificadorEmailMOCK() {
		System.out.println("NotificadorEmailMOCK");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("MOCK ----- Cliente " + cliente.getNome() +
					" notificado por EMAIL atraves do endereço " + cliente.getEmail() +
					" - Mensagem : " + mensagem );
	}

}
