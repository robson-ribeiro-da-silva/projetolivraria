/*package br.edu.ifrn.projetolivraria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/usuario/add").hasAnyRole("USUARIO_ADD")
		.antMatchers("/usuario/listar").hasAnyRole("USUARIO_LIST")
		.antMatchers("/livro/add").hasAnyRole("LIVRO_ADD")
		.antMatchers("/livro/listar").hasAnyRole("LIVRO_LIST")
		.antMatchers("/autor/add").hasAnyRole("AUTOR_ADD")
		.antMatchers("/autor/listar").hasAnyRole("AUTOR_LIST")
		.antMatchers("/categoria/add").hasAnyRole("CATEGORIA_ADD")
		.antMatchers("/categoria/listar").hasAnyRole("CATEGORIA_LIST")
		.antMatchers("/editora/add").hasAnyRole("EDITORA_ADD")
		.antMatchers("/editora/listar").hasAnyRole("EDITORA_LIST")
		.antMatchers("/itemPedido/add").hasAnyRole("ITEMPEDIDO_ADD")
		.antMatchers("/pedido/listarporusuario").hasAnyRole("PEDIDOPORUSUARIO_LIST")
		.anyRequest()
		.authenticated()
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll();
		
	}

}*/
