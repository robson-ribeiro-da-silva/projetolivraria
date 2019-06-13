package br.edu.ifrn.projetolivraria.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Data;

import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.model.StatusPedido;
import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.service.EmailService;
import br.edu.ifrn.projetolivraria.service.PedidoService;
import br.edu.ifrn.projetolivraria.service.UserService;
import br.edu.ifrn.projetolivraria.service.UsuarioService;

@Component
public class AgendamentoTarefas {
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private UserService serviceusuario;
	
	@Autowired
	private EmailService serviceemail;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	private final long HORA = MINUTO * 60;
	private final long TEMPO = HORA * 6;
	
	@Scheduled(fixedDelay = TEMPO)
	public void verificarStatusPedido(){
		
		List<Pedido> pedidos = service.findAll();
		
		Date datatu = new Date();		
		String formato = "dd/MM/yyyy";
		SimpleDateFormat dataFormatada = new SimpleDateFormat(formato); 
		
		String dataatual = dataFormatada.format(datatu);
		
		for(Pedido p : pedidos){
			if(p.getFrete().getDataEntregaCliente() == dataatual && p.getStatuspedido() == StatusPedido.ANDAMENTO){				
				p.setStatuspedido(StatusPedido.ENTREGUE);
				service.save(p);
			}	
		}		
	}
	
	@Scheduled(fixedDelay = TEMPO)
	public void temporizadorAniversario(){
		
		List<User> usuarios = serviceusuario.findAll();
		
		Date datatu = new Date();		
		String formato = "dd/MM/yyyy";
		SimpleDateFormat dataFormatada = new SimpleDateFormat(formato); 
		
		String dataatual = dataFormatada.format(datatu);
		
		String[] dataatualformatada = dataatual.split("/");
		
		//System.out.println("dia "+dataatualformatada[0]);
		//System.out.println("mes "+dataatualformatada[1]);
		
		
		for(User u : usuarios){
			String datausuario = u.getNascimento();
			String[] datausuarioformatada = datausuario.split("-");
		
			if(dataatualformatada[0].equals(datausuarioformatada[2]) && dataatualformatada[1].equals(datausuarioformatada[1])){
				serviceemail.sendMail("A Livraria DSC deseja-lhe os Parabéns! "+u.getNomeCompleto() +" Feliz Aniversário", "Feliz Aniversário", u.getEmail());
				//System.out.println("Email Enviado!");
			}			
		}		
	}
}
