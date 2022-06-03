package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.beans.Pessoa;
import br.ucb.poo.factory.Conexao;

public class PessoaDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

//	private Integer id_pessoa;
//	private Integer id_endereco;
//	private Integer id_telefones;
//	private String cpf;
//	private String nome;
//	private Date dt_nascimento;
	
	public void addPessoa(Pessoa pessoa){

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();
		try {
			String sql = "INSERT INTO pessoa (id_endereco, id_telefones, cpf, nome, dt_nascimento) VALUES (?,?,?,?,?)";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, pessoa.getId_endereco());
			pstmt.setInt(2, pessoa.getId_telefones());
			pstmt.setString(3, pessoa.getCpf());
			pstmt.setString(4, pessoa.getNome());
			pstmt.setDate(5, pessoa.getDt_nascimento());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Pessoa> retrievePessoas() {

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		String sql = "SELECT * FROM pessoa";
		
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId_pessoa(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));

				pessoas.add(pessoa);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoas;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdPessoa(Pessoa pessoa) {
//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM pessoa";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if((pessoa.getId_pessoa().equals(rs.getInt("id_pessoa"))) && (pessoa.getNome() == rs.getString("nome"))) {
					return rs.getInt("id_pessoa");
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdPessoaDeNome(String nome) {
//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT id_pessoa, nome FROM pessoa";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if(rs.getString("nome").equals(nome)) {
					return rs.getInt("id_pessoa");
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Pessoa retrievePessoaDeId(Integer id) {
		ArrayList<Pessoa> pessoas = retrievePessoas();
		for(Pessoa dep : pessoas) {
			if(dep.getId_pessoa() == id) {
				return dep;
			}
		}
		return null;
	}
	
	public Pessoa retrievePessoaDeCpf(String cpf) {
		ArrayList<Pessoa> pessoas = retrievePessoas();
		for(Pessoa dep : pessoas) {
			if(dep.getCpf().equals(cpf)) {
				return dep;
			}
		}
		return null;
	}
	
	public Integer retrieveIdPessoaDeCpf(String cpf) {
		ArrayList<Pessoa> pessoas = retrievePessoas();
		for(Pessoa dep : pessoas) {
			if(dep.getCpf().equals(cpf)) {
				return dep.getId_pessoa();
			}
		}
		return null;
	}
	
}

