package br.ucb.poo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.factory.Conexao;

public class TelefonesDAO {

//	private Integer id_telefones;
//	private String celular;
//	private String residencial;
//	private String comercial;
//	private String whatsapp;
	
	public void addTelefones(Telefones telefones){

		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();
		
		try {
			String sql = "INSERT INTO telefones (celular, residencial, comercial) VALUES (?,?,?)";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, telefones.getCelular());
			pstmt.setString(2, telefones.getResidencial());
			pstmt.setString(3, telefones.getComercial());
			
			pstmt.execute();
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Telefones> retrieveTelefones(){

		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();

		String sql = "SELECT * FROM telefones";
		
		ArrayList<Telefones> telefones = new ArrayList<Telefones>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
//				Laboratorio laboratorio = new Laboratorio();
//				Endereco endereco = new Endereco();
				Telefones telefone = new Telefones();

				telefone.setId_telefones(rs.getInt("id_laboratorio"));
				telefone.setCelular("celular");
				telefone.setResidencial("residencial");
				telefone.setComercial("comercial");

				telefones.add(telefone);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefones;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Telefones retrieveTelefonesDeId(Integer id) {
		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();
		
		Telefones telefone = new Telefones();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM telefones WHERE id_telefones = '" + id + "'";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				telefone.setId_telefones(rs.getInt("id_laboratorio"));
				telefone.setCelular("celular");
				telefone.setResidencial("residencial");
				telefone.setComercial("comercial");
					
				return telefone;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		


		telefone.setId_telefones(-1);
		telefone.setCelular("0000-0000");
		telefone.setResidencial("0000-0000");
		telefone.setComercial("0000-0000");
		
		return telefone;
	}
	

	public Integer retrieveIdDeTelefones(Telefones telefones) {
		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM telefones";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				
				if((rs.getString("celular").equals(telefones.getCelular())) &&
						(rs.getString("residencial").equals(telefones.getResidencial())) &&
						(rs.getString("comercial").equals(telefones.getComercial()))) {
					return rs.getInt("id_telefones");
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