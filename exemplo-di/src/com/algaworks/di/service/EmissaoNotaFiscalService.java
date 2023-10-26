package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.InterfaceNotificador;

public class EmissaoNotaFiscalService {
	
	private InterfaceNotificador notificao;
	
	public EmissaoNotaFiscalService(InterfaceNotificador notificao) {
		this.notificao = notificao;
	}

	public void emitir(Cliente cliente, Produto produto) {
		
		notificao.notificar(cliente, "Nota fiscal do produto " + produto.getNome() + " foi emitida");
		
	}
	
}
