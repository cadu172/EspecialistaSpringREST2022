package com.algaworks.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

@Component
public class EmitirNotaFiscalService {

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("EmitirNotaFiscalService: NOTA FISCAL EMITIDA PARA O CLIENTE - " + event.getCliente().getNome());
	}	
	
}
