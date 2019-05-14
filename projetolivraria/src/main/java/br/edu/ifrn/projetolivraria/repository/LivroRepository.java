package br.edu.ifrn.projetolivraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Livro;
import br.edu.ifrn.projetolivraria.model.Pedido;
import br.edu.ifrn.projetolivraria.model.Usuario;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> { 
	
	@Query(value = "select l.* from livro l inner join "
			+ "item_pedido_livro ipl on(l.id = ipl.livro_id) "
			+ "inner join item_pedido ip on(ip.id = ipl.item_pedido_id) "
			+ "where ip.pedido_id = ?1", nativeQuery = true)
	public List<Livro> findByPedido(Long id);
	
	/*@Query(value = "SELECT l.* FROM time l "
		    + "cross JOIN boleiro_time bt, boleiro b "
		    + "WHERE t.id = bt.id_time "
		    + "AND bt.id_boleiro = b.id "
		    + "AND b.id_usuario = ?1 "
		    + "AND (t.id_gerente = ?1 "
		    + "  OR bt.id_boleiro IN (SELECT b2.id FROM boleiro b2 WHERE b2.id_usuario= ?1)"
		    + ")", nativeQuery = true)
    List<Time> findByIdUsuario(Long pIdUsuario);*/
	
	//"select l from livro l inner join item_pedido_livro ipl on(l.livro_id = ipl.item_pedido_id) inner join item_pedido ip on(ip.id = ipl.item_pedido_id) where ip.pedido_id = 1"
}
