package br.edu.ifrn.projetolivraria.job;


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
		
			Role roleA = new Role();
			roleA.setNome("ADM");
			roleService.save(roleA);
			
			Role roleU = new Role();
			roleU.setNome("USER");
			roleService.save(roleU);
			
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
			
			Role roleadm = roleService.findByUsername("ADM");
			admin.getRole().add(roleadm);
			
			userService.save(admin);
		}
		
	}

}

