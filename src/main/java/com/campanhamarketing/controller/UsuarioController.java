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

import com.campanhamarketing.model.UsuarioModel;
import com.campanhamarketing.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/incluir", method = RequestMethod.GET)
	public ModelAndView incluir(Model model) {

		model.addAttribute("usuarioModel", new UsuarioModel());
		return new ModelAndView("incluirUsuario");
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute @Valid UsuarioModel usuarioModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("usuarioModel", usuarioModel);
			return new ModelAndView("incluirUsuario");
		} else {
			usuarioService.salvarUsuario(usuarioModel);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/incluir");
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		return modelAndView;
	}

	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(Model model) {

		model.addAttribute("usuariosModel", this.usuarioService.consultarUsuarios());
		return new ModelAndView("consultarUsuario");
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public ModelAndView excluir(@RequestParam("codigoUsuario") Long codigoUsuario) {

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");
		this.usuarioService.excluir(codigoUsuario);
		return modelAndView;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET)
	public ModelAndView alterar(@RequestParam("codigoUsuario") Long codigoUsuario, Model model) {

		UsuarioModel usuarioModel = this.usuarioService.consultarUsuario(codigoUsuario);
		model.addAttribute("usuarioModel", usuarioModel);
		return new ModelAndView("alterarUsuario");
	}

	@RequestMapping(value = "/salvarAlteracao", method = RequestMethod.POST)
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid UsuarioModel usuarioModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		boolean isErroNullCampos = false;

		for (FieldError fieldError : result.getFieldErrors()) {
			if (!fieldError.getField().equals("senha")) {
				isErroNullCampos = true;
			}
		}

		if (isErroNullCampos) {
			model.addAttribute("usuarioModel", usuarioModel);
			return new ModelAndView("alterarUsuario");
		} else {
			usuarioService.alterarUsuario(usuarioModel);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");

		return modelAndView;
	}
}
