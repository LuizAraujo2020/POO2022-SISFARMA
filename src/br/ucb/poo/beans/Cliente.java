package br.ucb.poo.beans;

public class Cliente extends Pessoa {
	private Integer id_cliente;
	private Integer id_pessoa;
	private Integer qtd_compras;
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public Integer getQtd_compras() {
		return qtd_compras;
	}
	public void setQtd_compras(Integer qtd_compras) {
		this.qtd_compras = qtd_compras;
	}
}
