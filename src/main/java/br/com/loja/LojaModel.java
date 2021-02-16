package br.com.loja;

public class LojaModel {

	// ATRIBUTOS
	private String produto;
	private float preco;
	private int qtd;
	private float totEstoque;

	// METODOS
	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getTotEstoque() {
		return totEstoque;
	}

	public void setTotEstoque(float totEstoque) {
		this.totEstoque = totEstoque;
	}

}
