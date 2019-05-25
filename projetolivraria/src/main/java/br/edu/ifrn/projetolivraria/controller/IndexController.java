package br.edu.ifrn.projetolivraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	

	@RequestMapping(method=RequestMethod.POST,path= {"/index"})
	public String login() {
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET,path= {"/entrar"})
	public String entrar() {
		return "login";
	}
	

}
