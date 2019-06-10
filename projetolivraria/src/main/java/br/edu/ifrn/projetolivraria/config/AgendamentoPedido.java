package br.edu.ifrn.projetolivraria.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.model.StatusPedido;
import br.edu.ifrn.projetolivraria.service.PedidoService;

@Component
public class AgendamentoPedido {
	
	@Autowired
	private PedidoService service;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	private final long HORA = MINUTO * 60;
	
	@Scheduled(fixedDelay = HORA)
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
}
