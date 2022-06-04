package br.ucb.poo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.poo.factory.Conexao;

public class LaboratorioDAO {

//	private Integer id_laboratorio;
//	private Endereco endereco;
//	private Telefones telefones;
//	private String nome;
	
	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	public void addLaboratorio(Laboratorio laboratorio){
		try {
			String sql = "INSERT INTO laboratorio (id_endereco, id_telefones, nome) VALUES (?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, laboratorio.getEndereco().getId_endereco());
			pstmt.setInt(2, laboratorio.getTelefones().getId_telefones());
			pstmt.setString(3, laboratorio.getNome());
			pstmt.execute();
			
			
			System.out.println("PRINT ID FROM NOME: " + retrieveIdLaboratorioDeNome(laboratorio.getNome()));
			
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Laboratorio> retrieveLaboratorio(){

		String sql = "SELECT * FROM laboratorio";
		
		ArrayList<Laboratorio> laboratorios = new ArrayList<Laboratorio>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Laboratorio laboratorio = new Laboratorio();
				Endereco endereco = new Endereco();
				Telefones telefones = new Telefones();

				
				//TODO: PEGAR ENDERECO
				rs.getInt("id_endereco");
				
				//TODO: PEGAR TELEFONES
				rs.getInt("id_telefones");
				
				laboratorio.setId_laboratorio(rs.getInt("id_laboratorio"));
				laboratorio.setEndereco(endereco);
				laboratorio.setTelefones(telefones);
				laboratorio.setNome(rs.getString("nome"));

				laboratorios.add(laboratorio);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return laboratorios;
	}
	
	//TODO: Fazer um throw de não encontrado
	public Integer retrieveIdLaboratorioDeNome(String nome) {
		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();
		
		String resultado = "Nao encontrado!";

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM farmacia.laboratorio WHERE nome = '" + nome + "';";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				return rs.getInt("id_laboratorio");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	//TODO: Fazer um throw de não encontrado
	public Laboratorio retrieveLaboratorioDeID(Integer id) {
		Conexao conexao = new Conexao();
		Connection connection = conexao.conectar();
		
		Laboratorio laboratorio = new Laboratorio();

		//TODO: Arrumar o * depois
		String sql = "SELECT * FROM laboratorio WHERE id_laboratorio = '" + id + "'";
		
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				
				if(rs.getInt("id_laboratorio") == id) {
					
					laboratorio.setId_laboratorio(rs.getInt("id_laboratorio"));
					laboratorio.setNome(rs.getString("nome"));
				}
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return laboratorio;
	}
}