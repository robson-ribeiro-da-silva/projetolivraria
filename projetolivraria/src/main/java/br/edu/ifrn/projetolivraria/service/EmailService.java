
package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.Email;
import br.edu.ifrn.projetolivraria.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired 
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailRepository repository;
	
	
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
	
	public void sendMailEnviar(String texto, String assunto, String emailUsuario){
        
		SimpleMailMessage message = new SimpleMailMessage();
		
        message.setText(texto);
        message.setSubject(assunto);
        message.setTo("admlivrariaads@gmail.com");
        message.setFrom(emailUsuario);
        
        try {
            mailSender.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar email.");
        }
    }
	
	public Email save(Email email) {
		return repository.saveAndFlush(email);
	}

	public List<Email> findAll() {
		return repository.findAll();
	}
	
	public Email findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
