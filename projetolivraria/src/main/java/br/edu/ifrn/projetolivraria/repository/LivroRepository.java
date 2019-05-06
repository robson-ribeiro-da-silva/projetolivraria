package br.edu.ifrn.projetolivraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetolivraria.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> { }
