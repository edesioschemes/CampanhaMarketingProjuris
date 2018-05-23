package com.campanhamarketing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.campanhamarketing.model.CampanhaModel;
import com.campanhamarketing.service.CampanhaService;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {

	@Autowired
	private CampanhaService campanhaService;

	@RequestMapping(value = "/incluir", method = RequestMethod.GET)
	public ModelAndView incluir(Model model) {

		model.addAttribute("campanhaModel", new CampanhaModel());
		return new ModelAndView("incluirCampanha");
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute @Valid CampanhaModel campanhaModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("campanhaModel", campanhaModel);
			return new ModelAndView("incluirCampanha");
		} else {
			campanhaService.incluirCampanha(campanhaModel);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/campanha/incluir");
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		return modelAndView;
	}

	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(Model model) {

		model.addAttribute("campanhasModel", this.campanhaService.consultarCampanhas());
		return new ModelAndView("consultarCampanha");
	}

	@RequestMapping(value = "/consultarByStatus", method = RequestMethod.GET)
	public ModelAndView consultarByStatus(@RequestParam("statusCampanha") boolean ativo, Model model) {

		model.addAttribute("campanhaModel", this.campanhaService.consultarCampanhasByStatus(ativo));
		return new ModelAndView("visualizarCampanha");
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public ModelAndView excluir(@RequestParam("codigoCampanha") Long codigoCampanha) {

		ModelAndView modelAndView = new ModelAndView("redirect:/campanha/consultar");
		this.campanhaService.excluirCampanha(codigoCampanha);
		return modelAndView;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET)
	public ModelAndView alterar(@RequestParam("codigoCampanha") Long codigoCampanha, Model model) {

		CampanhaModel campanhaModel = this.campanhaService.consultarCampanha(codigoCampanha);
		model.addAttribute("campanhaModel", campanhaModel);
		return new ModelAndView("alterarCampanha");
	}

	@RequestMapping(value = "/salvarAlteracao", method = RequestMethod.POST)
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid CampanhaModel campanhaModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		boolean isErroNullCampos = false;

		for (FieldError fieldError : result.getFieldErrors()) {
			if (!fieldError.getField().equals("senha")) {
				isErroNullCampos = true;
			}
		}

		if (isErroNullCampos) {
			model.addAttribute("campanhaModel", campanhaModel);
			return new ModelAndView("alterarCampanha");
		} else {
			campanhaService.alterarCampanha(campanhaModel);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/campanha/consultar");

		return modelAndView;
	}

}
