package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.Categoria;
import br.edu.ifrn.projetolivraria.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> listaAll(){
		return repository.findAll();
	}
	
    public void save(Categoria categoria) {
        repository.save(categoria);
    }
	
	public void update(Categoria categoria) {
		repository.saveAndFlush(categoria);
	}
	
	public Categoria  findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
