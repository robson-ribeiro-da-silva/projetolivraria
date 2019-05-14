package br.edu.ifrn.projetolivraria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Frete;
import br.edu.ifrn.projetolivraria.model.Pedido;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{ 
	
	@Query("select f from Frete f where f.pedido = ?1")
	public Frete findByPedido(Pedido p);
}
