package br.edu.ifrn.projetolivraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/autor/**").hasAnyAuthority("ADM")
				.antMatchers("/categoria/**").hasAnyAuthority("ADM")
				.antMatchers("/editora/**").hasAnyAuthority("ADM")
				.antMatchers("/livro/**").hasAnyAuthority("ADM")
				.antMatchers("/usuario/**").hasAnyAuthority("ADM")
				.antMatchers("/frete/**").hasAnyAuthority("ADM", "USER")
				.antMatchers("/pedido/**").hasAnyAuthority("ADM", "USER")
				.antMatchers("/itemPedido/**").hasAnyAuthority("ADM", "USER")
				.antMatchers("/http://**").hasAnyAuthority("ADM", "USER")
				.antMatchers("/https://**").hasAnyAuthority("ADM", "USER")
				.antMatchers("/usuario/**").hasAnyAuthority("ADM")
				.antMatchers("${JDBC_DATASOURCE_URL}").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	
	}
	
	
	
	 @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	  }
	 
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/css/**",  "/vendor/**",  "/img/**",  "/js/**",  "/scss/**", "/h2/**");
		web.ignoring().antMatchers("${JDBC_DATASOURCE_URL}", "http::/**", "https::/**", "/http::/**", "/https::/**");
	}
}
