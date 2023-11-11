package com.algaworks.algafood.jpa;

import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class BuscarRestauranteMain {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante subway = restauranteRepository.buscar(1L);
		
		System.out.println("RESTAURANTE: " + subway.getNome() +
				" | ID: " + subway.getId() +
				" | TAXA DE FRETE: " + String.format("%.2f", subway.getTaxaFrete()) );		
				
	}
	
}
