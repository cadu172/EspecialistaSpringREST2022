package com.algaworks.algafood.jpa;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class CRUD_EstadoMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);		
		
		// MÉTODO FindById()
		Estado estado = estadoRepository.buscar(1L);
		
		System.out.println("\n\n" + "-".repeat(20) + "RESULTADO DA BUSCA" + "-".repeat(20));		
		System.out.println("ID: " + estado.getId() +
				" | ESTADO: " + estado.getNome());
		System.out.println("-".repeat(40));
		
		// METODO Create() ou Merge()
		Estado novoEstado = new Estado();
		novoEstado.setNome("AM");		
		estadoRepository.salvar(novoEstado);
		
		// METODO List()
		List<Estado> listaDeEstados = estadoRepository.listar();		
		
		System.out.println("\n\n" + "-".repeat(20) + "LISTA DE ESTADOS CADASTRADOS" + "-".repeat(20));
		for(Estado item : listaDeEstados) {
			System.out.println("ID: " + item.getId() +
					" | ESTADO: " + item.getNome());
		}
		
		// METODO Delete()
		System.out.println("\n\n" + "-".repeat(20) + "METODO DELETE NO ESTADO " + novoEstado.getNome() +  "-".repeat(20));
		estadoRepository.remover(novoEstado); // estou usando o mesmo Bean do método Merge()		
		
	}
	
}
