package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.Frete;
import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.repository.FreteRepository;


@Service
public class FreteService {
	
	@Autowired
	private FreteRepository repository;
	
	public Frete save(Frete frete) {
		return repository.saveAndFlush(frete);
	}

	public List<Frete> findAll() {
		return repository.findAll();
	}
	
	public Frete findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Frete findByPedido(Pedido p) {
		return repository.findByPedido(p);
	}

}
