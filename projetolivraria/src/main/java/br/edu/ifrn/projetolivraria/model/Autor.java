package br.edu.ifrn.projetolivraria.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class Autor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column(nullable = false, length = 150)
	@NotBlank(message = "Email é uma informação obrigatória.")
	private String email;
	
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CPF é uma informação obrigatória.")
	private String cpf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
