
package br.edu.ifrn.projetolivraria.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.Autor;
import br.edu.ifrn.projetolivraria.model.Email;
import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.service.AutorService;
import br.edu.ifrn.projetolivraria.service.EmailService;
import br.edu.ifrn.projetolivraria.service.UserService;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	
	@Autowired
	private AutorService service;

	@Autowired
	private EmailService serviceemail;
	
	@Autowired
	private UserService serviceusuario;
	
	@GetMapping("/enviar")
	public ModelAndView criar(Email email, @AuthenticationPrincipal UserDetails userDetails){
		
		String user = userDetails.getUsername();
		
		User usuario = serviceusuario.findByUsername(user);
		
		email.setUsuario(usuario.getEmail());
		
		
		ModelAndView mv = new ModelAndView("email/form");
		mv.addObject("email", email);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Email email, BindingResult result) {
		
		
		serviceemail.save(email);
		
		serviceemail.sendMailEnviar(email.getTexto(), "Email de De: "+ email.getUsuario()+" Titulo: "+email.getTitulo());
		
		
		
		//return criar(em, userDetails).addObject("success", "Email Enviado com sucesso! Realizar Outro Envio?");
		return findById(email.getId()).addObject("success", "Email Enviado com sucesso! Realizar Outro Envio?");
	}
	
	//@GetMapping("/list/{id}")
	public ModelAndView findById(Long id) {
		
		ModelAndView mv = new ModelAndView("email/listar");
		mv.addObject("email", serviceemail.findOne(id));
		
		return mv;
	}

}

