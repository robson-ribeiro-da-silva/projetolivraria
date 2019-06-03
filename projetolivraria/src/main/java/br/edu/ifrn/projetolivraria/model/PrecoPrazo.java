package br.edu.ifrn.projetolivraria.model;

public class PrecoPrazo {
	
	private String preco;
	private String prazo;
	
	
	public PrecoPrazo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PrecoPrazo(String preco, String prazo) {
		super();
		this.preco = preco;
		this.prazo = prazo;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getPrazo() {
		return prazo;
	}
	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}
	

}
