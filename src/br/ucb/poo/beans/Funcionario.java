package br.ucb.poo.beans;

import java.sql.Date;

public class Funcionario extends Pessoa {
	private Integer id_funcionario;
	private Integer id_cargo;
	private Integer id_departamento;
	private Integer id_pessoa;
	private Float salario;
	private Date dt_adimissao;
	
	public Integer getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public Integer getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}
	public Integer getId_departamento() {
		return id_departamento;
	}
	public void setId_departamento(Integer id_departamento) {
		this.id_departamento = id_departamento;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public Date getDt_adimissao() {
		return dt_adimissao;
	}
	public void setDt_adimissao(Date dt_adimissao) {
		this.dt_adimissao = dt_adimissao;
	}
	public Integer getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
}
