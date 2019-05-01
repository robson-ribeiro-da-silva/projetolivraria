package br.edu.ifrn.projetolivraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{ }
