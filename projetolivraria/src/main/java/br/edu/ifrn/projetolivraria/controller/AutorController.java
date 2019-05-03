package br.edu.ifrn.projetolivraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.Autor;
import br.edu.ifrn.projetolivraria.service.AutorService;



@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@GetMapping("/")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/autor");
		mv.addObject("autors", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/add")
	public ModelAndView add(Autor autor) {
		
		ModelAndView mv = new ModelAndView("/autor/form");
		mv.addObject("autor", autor);
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Autor autor, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(autor);
		}
		
		service.save(autor);
		
		return findAll();
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid Autor autor, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(autor);
		}
		
		service.update(autor);
		
		return findAll();
	}

}
