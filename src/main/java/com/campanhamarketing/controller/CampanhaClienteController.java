package com.campanhamarketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campanhamarketing.entity.CampanhaClienteEntity;
import com.campanhamarketing.model.CampanhaModel;
import com.campanhamarketing.service.CampanhaClienteService;
import com.campanhamarketing.service.CampanhaService;

@Controller
@RequestMapping("/campanhacliente")
public class CampanhaClienteController {

	@Autowired
	private CampanhaClienteService campanhaClienteService;

	@Autowired
	private CampanhaService campanhaService;

	@RequestMapping(value = "/visualizarCampanha", method = RequestMethod.GET)
	public ModelAndView consultar(Long idUsuario, String siglaCliente, Model model) {

		CampanhaModel campanhaModel = this.campanhaService.consultarPrimeiraCampanhaAtiva();

		if (idUsuario == null) {
			model.addAttribute("campanhaModel", campanhaModel);
		} else {
			if (campanhaModel.getCodigo() != null) {
				CampanhaClienteEntity campanhaClienteEntity = this.campanhaClienteService
						.consultarCampanhaClienteByIdCampanhaSigla(campanhaModel.getCodigo(), idUsuario, siglaCliente);
				if (campanhaClienteEntity.getCodigo() == null) {
					model.addAttribute("campanhaModel", campanhaModel);
				}
			}
		}
		return new ModelAndView("visualizarCampanha");

	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@RequestParam("idCampanha") Long idCampanha, @RequestParam("idUsuario") Long idUsuario,
			@RequestParam("siglaCliente") String siglaCliente) {

		ModelAndView modelAndView = new ModelAndView("redirect:/home");
		try {
			this.campanhaClienteService.incluirCampanhaCliente(idCampanha, idUsuario, siglaCliente);
		} catch (DataIntegrityViolationException e) {
			modelAndView = new ModelAndView("home");
			modelAndView.addObject("msg_resultado", "Registro duplicado");
			return modelAndView;
		}
		modelAndView = new ModelAndView("redirect:/home");
		return modelAndView;

	}

}
