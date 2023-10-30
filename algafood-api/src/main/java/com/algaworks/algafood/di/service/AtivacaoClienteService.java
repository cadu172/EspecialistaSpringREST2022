package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.InterfaceNotificador;

public class AtivacaoClienteService {
	
	private InterfaceNotificador notificao;
	
	public AtivacaoClienteService(InterfaceNotificador notificao) {
		this.notificao = notificao;	
		
		System.out.println("AtivacaoClienteService : " + notificao);
		
	}

	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		notificao.notificar(cliente, "Cliente ativado");
		
	}
	
}
