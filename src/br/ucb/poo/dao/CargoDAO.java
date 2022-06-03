package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.beans.Cargo;
import br.ucb.poo.factory.Conexao;

public class CargoDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	
	public void addCargo(Cargo cargo){

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();
		try {
			String sql = "INSERT INTO cargo (id_cargo, nome) VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cargo.getNome());
			pstmt.setInt(2, cargo.getId_cargo());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cargo> retrieveCargos(){

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		String sql = "SELECT * FROM cargo";
		
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Cargo cargo = new Cargo();
				
				cargo.setId_cargo(rs.getInt("id_cargo"));
				cargo.setNome(rs.getString("nome"));

				cargos.add(cargo);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargos;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdCargo(Cargo cargo) {
//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM cargo";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if((cargo.getId_cargo().equals(rs.getInt("id_cargo"))) && (cargo.getNome() == rs.getString("nome"))) {
					return rs.getInt("id_cargo");
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
