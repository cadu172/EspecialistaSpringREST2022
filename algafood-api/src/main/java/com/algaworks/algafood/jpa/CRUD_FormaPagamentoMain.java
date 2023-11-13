package com.algaworks.algafood.jpa;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class CRUD_FormaPagamentoMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);		
		
		// MÉTODO FindById()
		FormaPagamento formaPagamento = formaPagamentoRepository.buscar(1L);
		
		System.out.println("\n" + "-".repeat(20) + "RESULTADO DA BUSCA" + "-".repeat(20));		
		System.out.printf("\n" + "%d - %s",formaPagamento.getId(), formaPagamento.getDescricao());
		System.out.println("\n" + "-".repeat(40));
		
		// METODO Create() ou Merge()
		FormaPagamento novoItem = new FormaPagamento();
		novoItem.setDescricao("DEP EM CONTA");		
		formaPagamentoRepository.salvar(novoItem);
		
		// METODO List()
		List<FormaPagamento> listaDeFormaDePagamento = formaPagamentoRepository.listar();		
		
		System.out.println("\n\n" + "-".repeat(20) + "LISTA DE MEIOS DE PAGAMENTO" + "-".repeat(20));
		for(FormaPagamento item : listaDeFormaDePagamento) {
			System.out.printf("\n%d - %s",item.getId(), item.getDescricao());
		}
		
		// METODO Delete()
		System.out.println("\n\n" + "-".repeat(20) + "METODO DELETE NOS MEIOS DE PAGAMENTO " + novoItem.getDescricao() +  "-".repeat(20));
		formaPagamentoRepository.remover(novoItem); // estou usando o mesmo Bean do método Merge()		
		
	}
	
}
