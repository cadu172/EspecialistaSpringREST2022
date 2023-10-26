package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.InterfaceNotificador;

public class AtivacaoClienteService {
	
	private InterfaceNotificador notificao;
	
	public AtivacaoClienteService(InterfaceNotificador notificao) {
		this.notificao = notificao;
	}

	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		notificao.notificar(cliente, "Cliente ativado");
		
	}
	
}
