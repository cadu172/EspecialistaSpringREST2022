package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public interface InterfaceNotificador {
	
	public void notificar(Cliente cliente, String Mensagem);
}
