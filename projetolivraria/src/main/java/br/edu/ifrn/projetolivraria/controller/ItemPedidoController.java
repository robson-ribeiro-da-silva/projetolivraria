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

import br.edu.ifrn.projetolivraria.model.ItemPedido;
import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.service.ItemPedidoService;
import br.edu.ifrn.projetolivraria.service.LivroService;


@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService service;
	
	@Autowired
	private LivroService serviceLivro;
	
	@GetMapping("/add")
	public ModelAndView add(ItemPedido itemPedido) {
		
		ModelAndView mv = new ModelAndView("/itemPedido/form");
		mv.addObject("livros", serviceLivro.findAll());
		mv.addObject("itemPedido", itemPedido);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid ItemPedido itemPedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(itemPedido);
		}
		
		service.save(itemPedido);
		
		return findAll();
	}
	
	@GetMapping("/addCarrinho/{id},{itemPedido}")
	public String addCarrinho(@PathVariable("id") Long id, @PathVariable("itemPedido") ItemPedido itemPedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect: /itemPedido/form";
		}
		
		Livro livro = serviceLivro.findOne(id);
		itemPedido.livro.add(livro);
		service.save(itemPedido);
		
		return "redirect: /itemPedido/form";
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("itemPedido/listar");
		mv.addObject("itemPedidos", service.findAll());
		
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

}
