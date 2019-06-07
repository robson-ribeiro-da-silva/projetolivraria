
package br.edu.ifrn.projetolivraria.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.service.AutorService;
import br.edu.ifrn.projetolivraria.service.EmailService;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired 
	private JavaMailSender mailSender;
	
	@Autowired
	private AutorService service;

	
	@GetMapping("/enviar")
	public ModelAndView sendMail() {
		
		
		
		
	
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Testando");
        message.setSubject("Teste");
        message.setTo("robsonrds72@gmail.com");
        message.setFrom("robsonrds72@gmail.com");
       
        //mailSender.send(message);
       
       
        System.out.println("controller");
		
		
       
		/*SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Testando");
        message.setTo("admlivrariaads@gmail.com");
        message.setFrom("admlivrariaads@gmail.com");
        mailSender.send(message);*/
      
        try {
            mailSender.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email.");
        }
        
        ModelAndView mv = new ModelAndView("autor/listar");
		mv.addObject("autors", service.findAll());
		
		return mv;
        
    }

}

