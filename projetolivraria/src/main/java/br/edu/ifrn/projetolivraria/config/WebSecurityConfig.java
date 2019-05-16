package br.edu.ifrn.projetolivraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()			
			.antMatchers("/autor/**").hasAnyRole("ADMIN")
			.antMatchers("/livro/**").hasAnyRole("ADMIN")
			.antMatchers("/categoria/**").hasAnyRole("ADMIN")
			.antMatchers("/editora/**").hasAnyRole("ADMIN")
			.antMatchers("/usuario/**").hasAnyRole("ADMIN")
			.antMatchers("/https://**").hasAnyRole("ADMIN")
			.antMatchers("/frete/**").hasAnyRole("USER")
			.antMatchers("/pedido/**").hasAnyRole("USER")
			.antMatchers("/itemPedido/**").hasAnyRole("USER")
			.antMatchers("http:/**").hasAnyRole("USER")
			.antMatchers("https:/**").hasAnyRole("USER")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin();
		
	}
	
	@Autowired
	public void configureGloblal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("usuario").password("{noop}1234").roles("USER")
		.and()
		.withUser("admin").password("{noop}123").roles("ADMIN", "USER");
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
		web.ignoring().antMatchers("/h2/**");
	}


}
