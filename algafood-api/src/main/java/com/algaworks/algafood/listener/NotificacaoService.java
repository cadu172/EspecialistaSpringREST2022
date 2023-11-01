package com.algaworks.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificacao.InterfaceNotificador;
import com.algaworks.algafood.di.notificacao.NivelPrioridade;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(NivelPrioridade.SEM_URGENCIA)
	@Autowired(required = true)
	private InterfaceNotificador notificador;	
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "NotificacaoService: SEU CADASTRO FOI ATIVADO COM SUCESSO");
	}
	
}
