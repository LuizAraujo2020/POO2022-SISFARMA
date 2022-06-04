package br.ucb.poo.beans;

import java.sql.Date;

public class Medicamento {
	private Integer id_medicamento;
	private String laboratorio;
	private Float preco;
	private Date dt_vencimento;
	private String nome;
	private Integer qtd_estoque;
	
	public Integer getId_medicamento() {
		return id_medicamento;
	}
	public void setId_medicamento(Integer id_medicamento) {
		this.id_medicamento = id_medicamento;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
}