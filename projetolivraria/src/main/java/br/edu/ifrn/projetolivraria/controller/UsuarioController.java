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

import br.edu.ifrn.projetolivraria.model.Endereco;
import br.edu.ifrn.projetolivraria.model.Usuario;
import br.edu.ifrn.projetolivraria.model.ViaCEPClient;
import br.edu.ifrn.projetolivraria.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/add")
	public ModelAndView add(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("/usuario/form");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(usuario);
		}
		
		String cep = usuario.getCep();
		ViaCEPClient cliente = new ViaCEPClient();
		
		Endereco endereco = cliente.buscaEnderecoPor(cep);
		if(endereco != null){
			
			usuario.setEstado(endereco.getUf());
			usuario.setCidade(endereco.getLocalidade());
		}
		
		service.save(usuario);
		
		return findAll();
	}
	
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("usuario/listar");
		mv.addObject("usuarios", service.findAll());
		
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
