package com.campanhamarketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campanhamarketing.service.CampanhaClienteService;

@Controller
@RequestMapping("/campanhacliente")
public class CampanhaClienteController {

	@Autowired
	private CampanhaClienteService campanhaClienteService;

	@RequestMapping(value = "/visualizarCampanha", method = RequestMethod.GET)
	public ModelAndView consultar(Long idCampanha, String siglaCliente, Model model) {

		model.addAttribute("campanhaModel",
				this.campanhaClienteService.consultarCampanhaClienteByIdCampanhaSigla(idCampanha, siglaCliente));

		return new ModelAndView("visualizarCampanha");

	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public void salvar(@RequestParam Long idCampanha, @RequestParam String siglaCliente) {

		this.campanhaClienteService.incluirCampanhaCliente(idCampanha, siglaCliente);

	}

}
