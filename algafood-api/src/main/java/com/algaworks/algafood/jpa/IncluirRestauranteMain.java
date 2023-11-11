package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class IncluirRestauranteMain {
	

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante leBife = new Restaurante();
		leBife.setNome("Le Bife");
		leBife.setTaxaFrete(new BigDecimal(45.85));
		
		// persistir
		restauranteRepository.salvar(leBife);
		
		System.out.println("RESTAURANTE: " + leBife.getNome() +
				" | ID: " + leBife.getId() +
				" | TAXA DE FRETE: " + String.format("%.2f", leBife.getTaxaFrete()) );
		
		Restaurante outBack = new Restaurante();
		outBack.setNome("Outback Steakhouse");
		outBack.setTaxaFrete(new BigDecimal(35.22));

		// persistir
		restauranteRepository.salvar(outBack);
		
		System.out.println("RESTAURANTE: " + outBack.getNome() +
				" | ID: " + outBack.getId() +
				" | TAXA DE FRETE: " + String.format("%.2f", outBack.getTaxaFrete()) );	
		
	}
	
}
