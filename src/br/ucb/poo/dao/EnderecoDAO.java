package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.beans.Endereco;
import br.ucb.poo.factory.Conexao;

public class EnderecoDAO {

//	private Integer id_endereco;
//	private String cep;
//	private Integer numero;
	
	public void addEndereco(Endereco endereco){

		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();
		try {
			String sql = "INSERT INTO endereco (cep, numero) VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, endereco.getCep());
			pstmt.setInt(2, endereco.getNumero());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Endereco> retrieveEnderecos(){

		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();

		String sql = "SELECT * FROM endereco";
		
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Endereco endereco = new Endereco();
				
				endereco.setId_endereco(rs.getInt("id_endereco"));
				endereco.setCep(rs.getString("cep"));
				endereco.setNumero(rs.getInt("numero"));

				enderecos.add(endereco);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enderecos;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdEndereco(Endereco endereco) {
		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM endereco";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if((endereco.getCep().equals(rs.getString("cep"))) && (endereco.getNumero() == rs.getInt("numero"))) {
					return rs.getInt("id_endereco");
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
