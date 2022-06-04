package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Medicamento;
import br.ucb.poo.factory.Conexao;

public class MedicamentoDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();


//	private Integer id_medicamento;
//	private String laboratorio;
//	private Float preco;
//	private Date dt_vencimento;
//	private String nome;
//	private Integer qtd_estoque;
	
	public void addMedicamento(Medicamento medicamento){
		try {
			
			String sql = "INSERT INTO medicamento (laboratorio, preco, dt_vencimento, nome, qtd_estoque) VALUES (?,?,?,?,?)";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, medicamento.getLaboratorio());
			pstmt.setFloat(2, medicamento.getPreco());
			pstmt.setDate(3, medicamento.getDt_vencimento());
			pstmt.setString(4, medicamento.getNome());
			pstmt.setInt(5, medicamento.getQtd_estoque());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Medicamento> retrieveMedicamento(){

		String sql = "SELECT id_medicamento, laboratorio, preco, dt_vencimento, nome, qtd_estoque FROM medicamento";
		
		ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		PreparedStatement pstmt;
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				Medicamento medicamento = new Medicamento();

				medicamento.setId_medicamento(rs.getInt("id_medicamento"));
				medicamento.setLaboratorio(rs.getString("laboratorio"));
				medicamento.setPreco(rs.getFloat("preco"));
				medicamento.setDt_vencimento(rs.getDate("dt_vencimento"));
				medicamento.setNome(rs.getString("nome"));
				medicamento.setQtd_estoque(rs.getInt("qtd_estoque"));

				medicamentos.add(medicamento);
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicamentos;
	}
	
	
	public Medicamento retrieveMedicamentoFromId(Integer id) {
		ArrayList<Medicamento> medicamentos = retrieveMedicamento();
		
		for(Medicamento med : medicamentos) {
			if(med.getId_medicamento() == id) {
				return med;
			}
		}
		return null;
	}
	
	
	public void updateMedicamento(Medicamento medicamentoNovo) {
		
		try {
             String query = "UPDATE medicamento SET nome = ?, laboratorio = ?, preco = ?,  dt_vencimento = ?,  qtd_estoque = ? WHERE id_medicamento = ?";
             PreparedStatement preparedStmt = connection.prepareStatement(query);
             preparedStmt.setString(1, medicamentoNovo.getNome());
             preparedStmt.setString(2, medicamentoNovo.getLaboratorio());
             preparedStmt.setFloat(3, medicamentoNovo.getPreco());
             preparedStmt.setDate(4, medicamentoNovo.getDt_vencimento());
             preparedStmt.setInt(5, medicamentoNovo.getQtd_estoque());
             preparedStmt.setInt(6, medicamentoNovo.getId_medicamento());

             // execute the java preparedstatement
             preparedStmt.executeUpdate();  
             preparedStmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void deletarMedicamento(Medicamento medicamento) {

		System.out.println("ID - Nome");
		
		System.out.println("--------------------------");
		System.out.println(medicamento.getId_medicamento() + " - " + medicamento.getNome());
        
	    System.out.println("Deseja deletar esse produto?");
	    System.out.println("Digite 'D' para DELETAR:");
		
		
		try {

	        Scanner sc = new Scanner(System.in);
            String deletar = sc.nextLine().toString();
            
            if(deletar.equals("d") || deletar.equals("D")) {
            
              String query = "DELETE FROM medicamento WHERE id_medicamento = ?";
              PreparedStatement preparedStmt = connection.prepareStatement(query);
              
              preparedStmt.setInt(1, medicamento.getId_medicamento());

              // execute the preparedstatement
              preparedStmt.execute();
              preparedStmt.close();
          }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void deletarMedicamento(Integer id) {

		
		Medicamento medicamento = retrieveMedicamentoFromId(id);

		System.out.println("ID - Nome");
		
		System.out.println("--------------------------");
		System.out.println(medicamento.getId_medicamento() + " - " + medicamento.getNome());
        
	    System.out.println("Deseja deletar esse produto?");
	    System.out.println("Digite 'D' para DELETAR:");
		
		
		try {

	        Scanner sc = new Scanner(System.in);
            String deletar = sc.nextLine().toString();
            
            if(deletar.equals("d") || deletar.equals("D")) {
            
              String query = "DELETE FROM medicamento WHERE id_medicamento = ?";
              PreparedStatement preparedStmt = connection.prepareStatement(query);
              preparedStmt.setInt(1, id);

              // execute the preparedstatement
              preparedStmt.execute();
              preparedStmt.close();
          }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
}