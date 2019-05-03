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

import br.edu.ifrn.projetolivraria.model.Categoria;
import br.edu.ifrn.projetolivraria.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
			
	@RequestMapping("/add")
	public ModelAndView add(Categoria categoria) {
		ModelAndView mv = new ModelAndView("categoria/form");
		mv.addObject("categoria", categoria);
		return mv;
	}
		
	@GetMapping("/edit/{id}")
	private ModelAndView edit( @PathVariable("id") Long id) {
		Categoria cat = categoriaService.findOne(id);
		return add(cat);
	}
	

	@GetMapping("/delete/{id}")
	private ModelAndView delete( @PathVariable("id") Long id) {
		categoriaService.delete(id);
		return findAll();
	}


	
	@PostMapping("/save")
    public ModelAndView save(@Valid Categoria categoria, BindingResult result) {
		 
		if(result.hasErrors()) {
			return add(categoria);
	    }

		categoriaService.save(categoria);			
				
		return findAll();
    }
	
	@PostMapping("/update")
    public ModelAndView update(@Valid Categoria categoria, BindingResult result) {
		 
		if(result.hasErrors()) {
			return add(categoria);
	    }

		categoriaService.update(categoria);			
				
		return findAll();
    }

	@GetMapping("/listar")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("categoria/listar");
        mv.addObject("categorias", categoriaService.listaAll());
        
        return mv;
	}

}
