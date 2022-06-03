package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.beans.Departamento;
import br.ucb.poo.factory.Conexao;

public class DepartamentoDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	
	public void addDepartamento(Departamento departamento){

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();
		try {
			String sql = "INSERT INTO departamento (id_departamento, nome) VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, departamento.getNome());
			pstmt.setInt(2, departamento.getId_departamento());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Departamento> retrieveDepartamentos(){

//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		String sql = "SELECT * FROM departamento";
		
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Departamento departamento = new Departamento();
				
				departamento.setId_departamento(rs.getInt("id_departamento"));
				departamento.setNome(rs.getString("nome"));

				departamentos.add(departamento);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departamentos;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdDepartamento(Departamento departamento) {
//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM departamento";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if((departamento.getId_departamento().equals(rs.getInt("id_departamento"))) && (departamento.getNome() == rs.getString("nome"))) {
					return rs.getInt("id_departamento");
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
	public Integer retrieveIdDepartamentoDeNome(String nome) {
//		Conexao conexao = new Conexao();
//		Connection connection = conexao.conectar();

		//TODO: Arrumar o * depois
		String sql = "SELECT id_departamento, nome FROM departamento";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){

				if(rs.getString("nome").equals(nome)) {
					return rs.getInt("id_departamento");
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Departamento retrieveDepartamentoDeId(Integer id) {
		ArrayList<Departamento> departamentos = retrieveDepartamentos();
		for(Departamento dep : departamentos) {
			if(dep.getId_departamento() == id) {
				return dep;
			}
		}
		return null;
	}
}

