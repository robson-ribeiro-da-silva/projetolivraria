package br.edu.ifrn.projetolivraria.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.ifrn.projetolivraria.model.Role;
import br.edu.ifrn.projetolivraria.model.User;
import br.edu.ifrn.projetolivraria.service.RoleService;
import br.edu.ifrn.projetolivraria.service.UserService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		String username = "admin";
		User user = userService.findByUsername(username);
		
		if(user == null){
		
			User admin = new User();
			admin.setNomeCompleto("robin");
			admin.setCep("59910000");
			admin.setUf("RN");
			admin.setCidade("Doutor Severiano");
			admin.setBairro("Zona Rural");
			admin.setRua("Merejo");
			admin.setEmail("robin@gmail.com");
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
			
			userService.save(admin);
		
		}
	
		/*Role roleAdm = new Role();
		roleAdm.setNome("ADM");
		roleService.save(roleAdm);

		Role roleUse = new Role();
		roleUse.setNome("USER");
		roleService.save(roleUse);
		
		List<Role> rolesAdm = new ArrayList<>();
		rolesAdm.add(roleAdm);

		List<Role> rolesUse = new ArrayList<>();
		rolesUse.add(roleUse);*/
		/*
		User admin = new User();
		admin.setNomeCompleto("robin");
		admin.setUsername("robinadmin");
		admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		userService.save(admin);
		*/
		/*User user = new User();
		admin.setNomeCompleto("usuario");
		admin.setCpf("111.007.874-96");
		admin.setDataCriacao(new Date());
		admin.setEmail("user@gmail.com");
		admin.setUsername("usuario");
		admin.setPassword(new BCryptPasswordEncoder().encode("1234"));
		//admin.setRole((Set<Role>) rolesUse);
		
		userService.save(user);*/




		
	}

}

