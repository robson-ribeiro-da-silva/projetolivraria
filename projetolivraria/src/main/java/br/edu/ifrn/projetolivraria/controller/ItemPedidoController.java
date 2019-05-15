package br.edu.ifrn.projetolivraria.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.Endereco;
import br.edu.ifrn.projetolivraria.model.Frete;
import br.edu.ifrn.projetolivraria.model.ItemPedido;
import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.model.PrecoPrazo;
import br.edu.ifrn.projetolivraria.service.FreteService;
import br.edu.ifrn.projetolivraria.service.ItemPedidoService;
import br.edu.ifrn.projetolivraria.service.LivroService;


@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService service;
	
	@Autowired
	private LivroService serviceLivro;
	
	@Autowired
	private FreteService servicefrete;
	
	@GetMapping("/add")
	public ModelAndView add(ItemPedido itemPedido) {
		
		ModelAndView mv = new ModelAndView("/itemPedido/form");
		mv.addObject("livros", serviceLivro.findAll());
		mv.addObject("itemPedido", itemPedido);
		
		return mv;
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/save")
	public ModelAndView save(@Valid ItemPedido itemPedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(itemPedido);
		}
		
		service.save(itemPedido);
		
		Frete frete = new Frete();
		String cep = itemPedido.getCep();
		
		double valor = 0.0;
		int i = 0;
		double pesolivros = 0.0;
		
		List<Livro> livros = itemPedido.getLivro();
		for(Livro l : livros){
			valor += l.getPreco();
			pesolivros += l.getPeso();
			i++;
		}
		
		String peso = Double.toString(pesolivros);
		
		
		String nCdEmpresa = "";//teclado.nextLine();
		String sDsSenha = "";//teclado.nextLine();
		String nCdServico = "41106";//teclado.nextLine(); //PAC 41106 | SEDEX 40010
		String sCepOrigem = "59910-000"; //teclado.nextLine(); //"01310909";
		String sCepDestino = cep;//teclado.nextLine(); //"59856000";
		String nVlPeso =  peso;//teclado.nextLine(); //"5,5";
		int nCdFormato = 1;//teclado.nextInt(); //1; //1 caixa/pacote | 2 rolo/prisma | 3 envelope
		BigDecimal nVlComprimento = new BigDecimal(50.0); // teclado.nextBigDecimal(); //new BigDecimal(16.0);
		BigDecimal nVlAltura = new BigDecimal(50.0);// teclado.nextBigDecimal(); //new BigDecimal(20.0);
		BigDecimal nVlLargura = new BigDecimal(50.0); //teclado.nextBigDecimal(); //new BigDecimal(20.0);
		BigDecimal nVlDiametro = new BigDecimal(50.0);// teclado.nextBigDecimal(); //new BigDecimal(50.0);
		String sCdMaoPropria = "S"; //teclado.nextLine(); //"S";
		BigDecimal nVlValorDeclarado = new BigDecimal(valor); //teclado.nextBigDecimal(); //new BigDecimal(0);
		String sCdAvisoRecebimento = "S";// teclado.nextLine(); //"S";
	
		RestTemplate template2 = new RestTemplate();
		
		PrecoPrazo precoprazo = template2.getForObject("http://localhost:8080/ServiceCorreio/servico/"+nCdServico+"/"+sCepOrigem+"/"+sCepDestino+"/"+nVlPeso+"/"+nCdFormato+"/"+nVlComprimento+"/"+nVlAltura+"/"+nVlLargura+"/"+nVlDiametro+"/"+sCdMaoPropria+"/"+nVlValorDeclarado+"/"+sCdAvisoRecebimento, PrecoPrazo.class);
		
		
		//System.out.println("Dias: " +precoprazo.getPrazo()+ "\nValor: " +precoprazo.getPreco());

		
		
		RestTemplate template = new RestTemplate();
		
		Endereco endereco = template.getForObject("https://viacep.com.br/ws/"+cep+"/json",Endereco.class);
		
		if(endereco != null){			
			frete.setUf(endereco.getUf());
			frete.setCidade(endereco.getLocalidade());
			frete.setBairro(endereco.getBairro());
			frete.setCep(cep);
			frete.setRua(endereco.getLogradouro());
		}
		
		System.out.println("Dias: " +precoprazo.getPrazo()+ "\nValor: " +precoprazo.getPreco());
		String pre = precoprazo.getPreco();
		String dias = precoprazo.getPrazo();
		
		pre = pre.replaceAll( "," , "." );
		
		
		double preco = Double.parseDouble(pre);
		int prazodias = Integer.parseInt(dias);
		
		System.out.println("Dias: " +prazodias+ "\nValor: " +preco);
		
		if(precoprazo != null){
			frete.setCepOrigem(sCepOrigem);
			frete.setPeso(pesolivros);
			frete.setValor(preco);
			frete.setTotDias(prazodias);
			
		}
		
		
		
		Date a = new Date();        
		a.setDate(a.getDate() + prazodias);        
		 
		String formato = "dd/MM/yyyy";
		SimpleDateFormat dataFormatada = new SimpleDateFormat(formato); 
		//System.out.println("Daqui há dez dias: " + dataFormatada.format(a));
		
		frete.setDataEntregaCliente(dataFormatada.format(a));
		
		servicefrete.save(frete);
		
		itemPedido.setFrete(frete);
		
		valor += preco;
		itemPedido.setValorTotal(valor);
		itemPedido.setQuantidade(i);
		
		service.save(itemPedido);
		
		return details(itemPedido.getId());
	}
	
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("itemPedido/listar");
		mv.addObject("itemPedidos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("itemPedido/details");
		mv.addObject("itemPedido", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
