package com.algaworks.algafood.jpa;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		/*Restaurante subway = new Restaurante();
		subway.setId(1L);
		subway.setNome("Subway com o nome alterado");
		subway.setTaxaFrete(new BigDecimal(87.93d));*/
		
		/*
		 * IMPORTANTE: alterar de alterar um objeto existente é necessário buscar ele para que todos os dados fiquem no Entity Manager
		 * Caso o objeto seja instanciado direto e enviado para o merge ele vai apagar todos os campos que não foram "setados"
		 * Por isso no exemplo abaixo eu primeiro fiz a busca e depois alteração
		 * */
		Restaurante subway = restauranteRepository.buscar(1L);
		subway.setTaxaFrete(new BigDecimal(87.93d));
		//subway.setNome("Subway com o nome alterado");
		
		subway = restauranteRepository.salvar(subway);
		
		System.out.println("RESTAURANTE: " + subway.getNome() +
				" | ID: " + subway.getId() +
				" | TAXA DE FRETE: " + String.format("%.2f", subway.getTaxaFrete()) );		
		
	}

}
