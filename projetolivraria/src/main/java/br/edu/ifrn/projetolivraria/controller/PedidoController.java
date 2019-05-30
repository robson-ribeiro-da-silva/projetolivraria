package br.edu.ifrn.projetolivraria.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.Frete;
import br.edu.ifrn.projetolivraria.model.ItemPedido;
import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.model.Usuario;
import br.edu.ifrn.projetolivraria.service.FreteService;
import br.edu.ifrn.projetolivraria.service.ItemPedidoService;
import br.edu.ifrn.projetolivraria.service.LivroService;
import br.edu.ifrn.projetolivraria.service.PedidoService;
import br.edu.ifrn.projetolivraria.service.UserService;
import br.edu.ifrn.projetolivraria.service.UsuarioService;



@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private ItemPedidoService  serviceitempedido;
	
	@Autowired
	private FreteService servicefrete;
	
	@Autowired
	private UserService serviceusuario;
	
	@Autowired
	private LivroService servicelivro;
	
	@GetMapping("/add")
	public ModelAndView add(Pedido pedido) {
		
		ModelAndView mv = new ModelAndView("pedido/form");
		mv.addObject("pedido", pedido);
		
		return mv;
	}
	
	@GetMapping("/addPedido/{id}")
	public ModelAndView addPedido(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		User usuario = serviceusuario.findByUsername(user);
		
		Pedido pedido = new Pedido();
		
		service.save(pedido);
		
		ItemPedido ipedido = serviceitempedido.findOne(id);
		
		ipedido.setPedido(pedido);
		
		serviceitempedido.save(ipedido);
		Frete frete = ipedido.getFrete();
		
		pedido.setValorTotal(ipedido.getValorTotal());
		pedido.setData(new Date());
		
		pedido.setUsuario(usuario);
		
		service.save(pedido);
		
		frete.setPedido(pedido);
		servicefrete.save(frete);
		
		
		
		ModelAndView mv = new ModelAndView("frete/form");
		mv.addObject("pedido", pedido);
		mv.addObject("frete", frete);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Pedido pedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(pedido);
		}
		
		service.save(pedido);
		
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("pedido/listar");
		mv.addObject("pedidos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/listarporusuario")
	public ModelAndView findByUsuario(@AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		User usuario = serviceusuario.findByUsername(user);
		
		//Usuario u = serviceusuario.findOne();
		
		List<Pedido> pedidos = service.findByUsuario(usuario);
		//List<ItemPedido> itempedidos = serviceitempedido.findByListPedido(pedidos);
		/*List<Livro> livros = servicelivro.findByPedido((long) 99);
		
		for(Livro l : livros){
			System.out.println(" AQUIIIIIIII ----->>>>> "+ l.getTitulo());
		}*/
		
		ModelAndView mv = new ModelAndView("pedido/listar");
		mv.addObject("pedidos", pedidos);
		//mv.addObject("itempedidos", itempedidos);
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	@GetMapping("/detailLivroPedido/{id}")
	public ModelAndView detailLivroByPedido(@PathVariable("id") Long id) {
		
		List<Livro> livros = servicelivro.findByPedido(id);
		
		/*for(Livro l : livros){
			System.out.println(" AQUIIIIIIII ----->>>>> "+ l.getTitulo());
		}*/
			
		ModelAndView mv = new ModelAndView("livro/listarporpedido");
		mv.addObject("livros", livros);
		
		return mv;
	}
	
	@GetMapping("/detailsByPedido/{id}")
	public ModelAndView detailsByPedido(@PathVariable("id") Long id) {
		
		Pedido p = service.findOne(id);
		
		ModelAndView mv = new ModelAndView("frete/detailsByPedido");
		mv.addObject("frete", servicefrete.findByPedido(p));
		
		return mv;
	}

}
