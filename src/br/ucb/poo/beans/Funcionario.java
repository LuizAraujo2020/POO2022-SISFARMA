package br.ucb.poo.beans;

import java.sql.Date;

import br.ucb.poo.utils.Pessoa;

public class Funcionario extends Pessoa {
	private Integer id_funcionario;
	private Integer cargo;
	private Integer departamento;
	private Float salario;
	private Date dt_adimissao;
	s
	public Integer getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public Integer getCargo() {
		return cargo;
	}
	public void setCargo(Integer cargo) {
		this.cargo = cargo;
	}
	public Integer getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
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
}
