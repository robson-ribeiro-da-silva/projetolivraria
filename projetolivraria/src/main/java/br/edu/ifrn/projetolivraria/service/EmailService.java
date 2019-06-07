
package br.edu.ifrn.projetolivraria.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired 
	private JavaMailSender mailSender;
	
	
	public void sendMail() throws MessagingException{
        
		SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Testando");
        message.setSubject("Teste");
        message.setTo("admlivrariaads@gmail.com");
        message.setFrom("admlivrariaads@gmail.com");
       
        
       
       
        System.out.println(" service");
        
        try {
            mailSender.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email.");
        }
    }

}
