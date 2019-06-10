
package br.edu.ifrn.projetolivraria.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired 
	private JavaMailSender mailSender;
	
	
	public void sendMail(String texto, String assunto, String email){
        
		SimpleMailMessage message = new SimpleMailMessage();
		
        message.setText(texto);
        message.setSubject(assunto);
        message.setTo(email);
        message.setFrom("admlivrariaads@gmail.com");
        
        try {
            mailSender.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email.");
        }
    }

}
