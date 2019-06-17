package br.edu.ifrn.projetolivraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.ListaDesejos;

@Repository
public interface ListaDesejosRepository extends JpaRepository<ListaDesejos, Long> {

	@Query(value = "select l.* from lista_desejos l where l.usuario_id = ?1", nativeQuery = true)
	public List<ListaDesejos> findByUsuario(Long id);

}
