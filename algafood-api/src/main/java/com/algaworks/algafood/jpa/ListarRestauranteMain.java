package com.algaworks.algafood.jpa;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ListarRestauranteMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for(Restaurante restaurante : restaurantes) {
			System.out.println("ID: " + restaurante.getId() +
					" | NOME: " + restaurante.getNome() +
					" | TAXA DE FRETE: " + String.format("%.2f", restaurante.getTaxaFrete()) + 
					" | COZINHA: " + restaurante.getCozinha().getNome());
		}
		
	}
	
}
