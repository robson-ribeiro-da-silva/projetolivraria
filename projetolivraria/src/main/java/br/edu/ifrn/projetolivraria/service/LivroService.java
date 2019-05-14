package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.repository.LivroRepository;


@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;

	public Livro save(Livro livro) {
		return repository.saveAndFlush(livro);
	}

	public List<Livro> findAll() {
		return repository.findAll();
	}
	
	public Livro findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Livro> findByPedido(Long id) {
		return repository.findByPedido(id);
	}

}
