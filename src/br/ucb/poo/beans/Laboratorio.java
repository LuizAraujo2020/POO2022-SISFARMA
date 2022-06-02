package br.ucb.poo.beans;

public class Laboratorio {
	private Integer id_laboratorio;
	private Endereco endereco;
	private Telefones telefones;
	private String nome;
	
	public Integer getId_laboratorio() {
		return id_laboratorio;
	}
	public void setId_laboratorio(Integer id_laboratorio) {
		this.id_laboratorio = id_laboratorio;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
