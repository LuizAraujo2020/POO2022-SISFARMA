package br.ucb.poo.beans;

import java.sql.Date;

public class Pessoa {
	private String cpf;
	private String nome;
	private Date dt_nascimento;
	private Endereco endereco;
	private Telefones telefones;

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefones getTelefones() {
		return telefones;
	}
	public void setTelefones(Telefones telefones) {
		this.telefones = telefones;
	}
	
//	public Integer getIdade() {
//
//	}
}
