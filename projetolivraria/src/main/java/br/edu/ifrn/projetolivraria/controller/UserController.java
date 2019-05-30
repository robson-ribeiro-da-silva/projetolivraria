package br.edu.ifrn.projetolivraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.service.RoleService;
import br.edu.ifrn.projetolivraria.service.UserService;

@Controller
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService servicerole;
	
	@GetMapping("/add")
	public ModelAndView add(User usuario) {
		
		ModelAndView mv = new ModelAndView("usuario/form");
		mv.addObject("usuario", usuario);
		mv.addObject("roles", servicerole.buscarTodos());
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid User usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(usuario);
		}
		
		//String cep = usuario.getCep();
		
		//RestTemplate template = new RestTemplate();
		
		//Endereco endereco = template.getForObject("https://viacep.com.br/ws/"+cep+"/json",Endereco.class);
		
		/*if(endereco != null){			
			usuario.setEstado(endereco.getUf());
			usuario.setCidade(endereco.getLocalidade());
		}*/
		String senha = usuario.getPassword();
		usuario.setPassword(new BCryptPasswordEncoder().encode(senha));
		
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
