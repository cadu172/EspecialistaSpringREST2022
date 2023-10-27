package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;

public interface InterfaceNotificador {

	void notificar(Cliente cliente, String Mensagem);

}