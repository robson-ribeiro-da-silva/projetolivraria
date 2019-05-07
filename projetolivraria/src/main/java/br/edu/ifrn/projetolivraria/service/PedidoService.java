package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.repository.PedidoRepository;

@Repository
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido save(Pedido pedido) {
		return repository.saveAndFlush(pedido);
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
