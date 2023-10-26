package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.NotificadorSMS;
import com.algaworks.di.service.AtivacaoClienteService;
import com.algaworks.di.service.EmissaoNotaFiscalService;

public class Main {

	public static void main(String[] args) {
		
		Cliente carlos = new Cliente("Carlos","5511999999999","carlos@gmail.com");
		Cliente maria = new Cliente("Maria","5511888888888","maria@gmail.com");
		
		Produto celular = new Produto("Galaxy", 1500.65);
		Produto tv = new Produto("TV LED", 2800.00);
		
		NotificadorSMS notificacao = new NotificadorSMS();
		
		AtivacaoClienteService ativacao = new AtivacaoClienteService(notificacao);
		
		ativacao.ativar(maria);
		ativacao.ativar(carlos);
		
		EmissaoNotaFiscalService notaFiscal = new EmissaoNotaFiscalService(notificacao);
		
		notaFiscal.emitir(maria, celular);
		notaFiscal.emitir(carlos, tv);


	}

}
