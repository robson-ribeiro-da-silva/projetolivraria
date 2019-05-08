package br.edu.ifrn.projetolivraria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Frete implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Double valor;
	
	private Double peso;
	
	private Date dataEntregaCorreios;
	
	private Date dataEntregaCliente;
	
	private String cepOrigem;

	private String cepDestino;
	
	private String rua;
	
	private String bairro;
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	public Pedido pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Date getDataEntregaCorreios() {
		return dataEntregaCorreios;
	}

	public void setDataEntregaCorreios(Date dataEntregaCorreios) {
		this.dataEntregaCorreios = dataEntregaCorreios;
	}

	public Date getDataEntregaCliente() {
		return dataEntregaCliente;
	}

	public void setDataEntregaCliente(Date dataEntregaCliente) {
		this.dataEntregaCliente = dataEntregaCliente;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
