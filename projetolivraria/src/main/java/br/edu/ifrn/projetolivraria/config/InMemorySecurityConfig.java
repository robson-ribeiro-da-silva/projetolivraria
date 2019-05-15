/*package br.edu.ifrn.projetolivraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class InMemorySecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
			.withUser("robin").password("123").roles("AUTOR_ADD", "AUTOR_LIST", "CATEGORIA_ADD", "CATEGORIA_LIST", "EDITORA_ADD", "EDITORA_LIST", "LIVRO_LIST", "LIVRO_ADD", "USUARIO_ADD", "USUARIO_LIST")
			.and()
			.withUser("usuario").password("123").roles("ITEMPEDIDO_ADD", "PEDIDOPORUSUARIO_LIST");
	}

}*/
