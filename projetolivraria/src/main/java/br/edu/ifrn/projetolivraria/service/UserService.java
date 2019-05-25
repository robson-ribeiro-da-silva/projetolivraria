package br.edu.ifrn.projetolivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		return repository.saveAndFlush(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User findByUsername(String username){
		return repository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByUsername(username);
		
		if(user == null){
			throw new UsernameNotFoundException("Usuario nao encontrado") ;
		}
		return user;
	}

}
