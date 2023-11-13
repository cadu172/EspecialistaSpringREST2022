package com.algaworks.algafood.jpa;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class CRUD_PermissaoMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);		
		
		// MÉTODO FindById()
		Permissao permissao = permissaoRepository.buscar(1L);
		
		System.out.println("\n" + "-".repeat(20) + "RESULTADO DA BUSCA" + "-".repeat(20));		
		System.out.printf("\n" + "%d - %s",permissao.getId(), permissao.getDescricao());
		System.out.println("\n" + "-".repeat(40));
		
		// METODO Create() ou Merge()
		Permissao novoItem = new Permissao();
		novoItem.setDescricao("Permite Incluir um novo login no sistema");		
		novoItem.setNome("LOGIN");
		permissaoRepository.salvar(novoItem);
		
		// METODO List()
		List<Permissao> listaDeFormaDePagamento = permissaoRepository.listar();		
		
		System.out.println("\n\n" + "-".repeat(20) + "LISTA DE PERMISSOES" + "-".repeat(20));
		for(Permissao item : listaDeFormaDePagamento) {
			System.out.printf("\n%d - %s",item.getId(), item.getDescricao());
		}
		
		// METODO Delete()
		System.out.println("\n\n" + "-".repeat(20) + "METODO DELETE NAS PERMISSOES " + novoItem.getDescricao() +  "-".repeat(20));
		permissaoRepository.remover(novoItem); // estou usando o mesmo Bean do método Merge()		
		
	}
	
}
