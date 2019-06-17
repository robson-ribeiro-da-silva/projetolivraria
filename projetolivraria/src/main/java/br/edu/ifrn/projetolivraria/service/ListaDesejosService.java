package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.ListaDesejos;
import br.edu.ifrn.projetolivraria.repository.ListaDesejosRepository;

@Service
public class ListaDesejosService {

	@Autowired
	private ListaDesejosRepository repository;
	
	public ListaDesejos save(ListaDesejos lista) {
		return repository.saveAndFlush(lista);
	}

	public List<ListaDesejos> findAll() {
		return repository.findAll();
	}
	
	public ListaDesejos findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<ListaDesejos> findByUsuario(Long id) {
		return repository.findByUsuario(id);
	}
}
