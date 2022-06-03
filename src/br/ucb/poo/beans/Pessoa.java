package br.ucb.poo.beans;

import java.sql.Date;

public class Pessoa {
	private Integer id_pessoa;
	private Integer id_endereco;
	private Integer id_telefones;
	private String cpf;
	private String nome;
	private Date dt_nascimento;
	
	public Integer getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public Integer getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}
	public Integer getId_telefones() {
		return id_telefones;
	}
	public void setId_telefones(Integer id_telefones) {
		this.id_telefones = id_telefones;
	}
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
	
	
	public Pessoa getPessoa() {
		Pessoa pessoa = new Pessoa();

		pessoa.setId_pessoa(id_pessoa);
		pessoa.setId_endereco(id_endereco);
		pessoa.setId_telefones(id_telefones);
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		pessoa.setDt_nascimento(dt_nascimento);
		
		return pessoa;
	}
	
	
	public void setPessoa(Pessoa pessoa) {
		
		this.id_pessoa = pessoa.getId_pessoa();
		this.id_endereco = pessoa.getId_endereco();
		this.id_telefones = pessoa.getId_telefones(); 
		this.cpf = pessoa.getCpf();
		this.nome = pessoa.getNome();
		this.dt_nascimento = pessoa.getDt_nascimento();
	}
	
//	public Integer getIdade() {
//
//	}
}
