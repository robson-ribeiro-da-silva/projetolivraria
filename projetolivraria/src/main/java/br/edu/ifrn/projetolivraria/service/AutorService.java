package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.Autor;
import br.edu.ifrn.projetolivraria.repository.AutorRepository;


@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;
	
	public Autor save(Autor autor) {
		return repository.saveAndFlush(autor);
	}

	public List<Autor> findAll() {
		return repository.findAll();
	}
	
	public Autor findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
