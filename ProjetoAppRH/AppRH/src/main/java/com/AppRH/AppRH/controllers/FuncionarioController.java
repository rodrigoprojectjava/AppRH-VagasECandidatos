package com.AppRH.AppRH.controllers;

import org.hibernate.annotations.ValueGenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependentesRepository dr;

	// ADICIONAR FUNCIONARIOS - CHAMA O FORM

	@GetMapping("/cadastrarFuncionario")
	public String form() {
		return "funcionario/formFuncionario";
	}

	// SALVA NA BASE
	@PostMapping("/cadastrarFuncionario")
	public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarFuncionario";
		}
		fr.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionario cadastrado com sucesso");
		return "redirect:/cadastrarFuncionario";
	}

	// LISTAR FUNCIONARIO
	@RequestMapping("/funcionarios")
	public ModelAndView listaFuncionarios() {
		ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
		Iterable<Funcionario> funcionarios = fr.findAll();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}

	// LISTAR DEPENDENTES
	@GetMapping("/dependentes/{id}")
	public ModelAndView dependentes(@PathVariable("id") long id) {
		Funcionario funcionario = fr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/dependentes");
		mv.addObject("funcionario", funcionario);

		// LISTA DE DEPENDENTES BASEADA NO FUNCIONARIO
		Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
		mv.addObject("dependentes", dependentes);

		return mv;
	}

	// ADICIONAR DEPENDENTES
	@PostMapping("/dependentes/{id}")
	public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/dependentes/{id}";
		}

		if (dr.findByCpf(dependentes.getCpf()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
			return "redirect:/dependentes/{id}";
		}

		Funcionario funcionario = fr.findById(id);
		dependentes.setFuncionario(funcionario);
		dr.save(dependentes);
		attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso!");
		return "redirect:/dependentes{id}";
	}

	// DELETA FUNCIONARIO
	@RequestMapping("/deletarFuncionario")
	public String deletarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		fr.delete(funcionario);
		return "redirect:/funcionarios";
	}

	// ATUALIZA FUNCIONARIO
	@GetMapping("/editar-funcionario")
	public ModelAndView editarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/update-funcionario");
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	// UPDATE FUNCIONARIO
	@PostMapping("editar-funcionario")
	public String updateFuncionario(@Valid Funcionario funcionario, BindingResult result,
			RedirectAttributes attributes) {
			
		fr.save(funcionario);
		attributes.addFlashAttribute("success","Funcionario adicionado com sucesso");
		
		long idLong = funcionario.getId();
		String id = ""+idLong;
		return "redirect:/dependentes/" + id;
		}

	//DELETAR DEPENDENTES
	@RequestMapping("/deletarDependente")
	public String deletarDependente(String cpf) {
		Dependentes dependentes = dr.findByCpf(cpf);
		
		Funcionario funcionario = dependentes.getFuncionario();
		String codigo = "" + funcionario.getId();
		dr.delete(dependentes);
		return "redirect:/dependentes/" + codigo;
	}

}
