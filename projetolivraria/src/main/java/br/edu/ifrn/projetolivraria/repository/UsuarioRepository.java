package br.edu.ifrn.projetolivraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ 
	
		//public Usuario findByEmail(String email);
	
}
