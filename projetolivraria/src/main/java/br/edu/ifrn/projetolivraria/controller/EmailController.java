
package br.edu.ifrn.projetolivraria.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.service.AutorService;

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
        message.setFrom("gestaoescolaronline1.0@gmail.com");
       
        try{
        	System.out.println("entrou");
        	mailSender.send(message);
        	System.out.println("foi enviado");
        }catch (Exception e) {
        	System.out.println("O email não foi enviado");
			e.printStackTrace();
		}
		
		/*try{
			MimeMessage mail = mailSender.createMimeMessage();
	
			MimeMessageHelper message = new MimeMessageHelper(mail);
			
		
			
			//SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Testando");
	        message.setSubject("Teste");
	        message.setTo("robsonrds72@gmail.com");
	       // message.setFrom("admlivrariaads@gmail.com");
	       
	        //mailSender.send(message);
	       
	       
	        System.out.println("controller");
			
			
	       
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Testando");
	        message.setTo("admlivrariaads@gmail.com");
	        message.setFrom("admlivrariaads@gmail.com");
	        mailSender.send(message);
	      
	        
        mailSender.send(mail);
        System.out.println("Email enviado com sucesso!");
	        
	           
	           
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("O email não foi enviado");
		}*/
        
        ModelAndView mv = new ModelAndView("autor/listar");
		mv.addObject("autors", service.findAll());
		
		return mv;
        
    }

}

