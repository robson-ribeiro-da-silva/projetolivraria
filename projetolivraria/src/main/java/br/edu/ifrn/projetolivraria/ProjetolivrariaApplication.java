package br.edu.ifrn.projetolivraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


//@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class ProjetolivrariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetolivrariaApplication.class, args);
	}
	
}
