package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/Grupos")
public class GrupoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@GetMapping
	public List<Grupo> listar() {
		return cadastroGrupoService.listar();
	}

}
