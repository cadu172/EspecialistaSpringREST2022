package com.algaworks.algafood.jpa;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class CRUD_CidadeMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		// MÉTODO FindById()
		Cidade cidade = cidadeRepository.buscar(1L);
		
		System.out.println("\n\n" + "-".repeat(20) + "RESULTADO DA BUSCA" + "-".repeat(20));		
		System.out.printf("%d - %s - %s \n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());
		System.out.println("-".repeat(40));
		
		
		Estado estado = estadoRepository.buscar(1L); // buscar o estado de ID 1
		
		// METODO Create() ou Merge()
		Cidade novocidade = new Cidade();
		novocidade.setNome("Sorocaba");
		novocidade.setEstado(estado);		
		cidadeRepository.salvar(novocidade);// persitencia
		
		// METODO List()
		List<Cidade> listaDeCidades = cidadeRepository.listar();		
		
		System.out.println("\n\n" + "-".repeat(20) + "LISTA DE cidadeS CADASTRADOS" + "-".repeat(20));
		for(Cidade item : listaDeCidades) {
			System.out.printf("%d - %s - %s \n", item.getId(), item.getNome(), item.getEstado().getNome());
		}
		
		// METODO Delete()
		System.out.println("\n\n" + "-".repeat(20) + "METODO DELETE NO cidade " + novocidade.getNome() +  "-".repeat(20));
		cidadeRepository.remover(novocidade); // estou usando o mesmo Bean do método Merge()		
		
	}
	
}
