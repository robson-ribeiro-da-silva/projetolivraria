package br.edu.ifrn.projetolivraria.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.projetolivraria.model.Autor;
import br.edu.ifrn.projetolivraria.model.ListaDesejos;
import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.service.ListaDesejosService;
import br.edu.ifrn.projetolivraria.service.LivroService;
import br.edu.ifrn.projetolivraria.service.UserService;


@RestController
@RequestMapping("/api/listadesejos")
public class ListaDesejosResource {
	
	@Autowired
	private ListaDesejosService service;
	
	@Autowired
	private LivroService servicelivro;
	
	@Autowired
	private UserService serviceusuario;
	
	@PostMapping(value="/save/{id}")
	public ResponseEntity<Livro> save(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails){
		
		Livro livro = servicelivro.findOne(id);
		List<Livro> listaLivro = new ArrayList<Livro>();
		
		listaLivro.add(livro);
		
		String user = userDetails.getUsername();
		
		User usuario = serviceusuario.findByUsername(user);
		
		ListaDesejos listadesejo = new ListaDesejos();
		
		listadesejo = new ListaDesejos();
		listadesejo.setLivro(listaLivro);
		listadesejo.setUsuario(usuario);
		
		service.save(listadesejo);
		
		
		Livro l = new Livro();
		l.setTitulo(livro.getTitulo());
		
		return  ResponseEntity.ok(l);
		
	}

}
