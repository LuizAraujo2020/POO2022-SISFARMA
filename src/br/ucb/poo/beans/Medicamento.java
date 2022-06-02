package br.ucb.poo.beans;

import java.sql.Date;

public class Medicamento {
	private Integer id_medicamento;
	private Integer id_laboratorio;
	private Float preco;
	private Date dt_vencimento;
	private String nome_medicamento;
	private Integer qtd_estoque;
	
	public Integer getId_medicamento() {
		return id_medicamento;
	}
	public void setId_medicamento(Integer id_medicamento) {
		this.id_medicamento = id_medicamento;
	}
	public Integer getId_laboratorio() {
		return id_laboratorio;
	}
	public void setId_laboratorio(Integer id_laboratorio) {
		this.id_laboratorio = id_laboratorio;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public Date getDt_vencimento() {
		return dt_vencimento;
	}
	public void setDt_vencimento(Date dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}
	public String getNome_medicamento() {
		return nome_medicamento;
	}
	public void setNome_medicamento(String nome_medicamento) {
		this.nome_medicamento = nome_medicamento;
	}
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
}