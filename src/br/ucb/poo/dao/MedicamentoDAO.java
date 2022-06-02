package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Medicamento;
import br.ucb.poo.beans.Laboratorio;
import br.ucb.poo.dao.LaboratorioDAO;
import br.ucb.poo.factory.Conexao;
import br.ucb.poo.views.LaboratorioView;

public class MedicamentoDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();


//private Integer id_medicamento;
//private Integer id_laboratorio;
//private Float preco;
//private Date dt_vencimento;
//private String nome_medicamento;
//private Integer qtd_estoque;
	public void addMedicamento(Medicamento medicamento, String nomeLab){
		try {
			LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
			Laboratorio laboratorio = new Laboratorio();
//			laboratorioDAO.addLaboratorio(laboratorio);

			laboratorio.setId_laboratorio(laboratorioDAO.retrieveIdLaboratorioDeNome(nomeLab));
			laboratorio.setNome(nomeLab);

			// Lab não encontrado...
			while(laboratorio.getId_laboratorio() == -1) {

		        Scanner sc = new Scanner(System.in);
		        String opcaoMenuCriarLaboratorio = "";
		        
				System.out.println("Laboratorio não cadastrado!");
				System.out.println("L) Listar todos laboratórios;");
				System.out.println("I) Cadastrar novo laboratório;");
				opcaoMenuCriarLaboratorio = sc.next();
				
				switch(opcaoMenuCriarLaboratorio) {
				case "L":
				case "l":
					LaboratorioView laboratorioView = new LaboratorioView();
					laboratorioView.listarTodosLaboratorios();

					System.out.println("Encontrou o Laboratorio?");
					
				case "I":
				case "i":
					
					break;
				}
//				CRUD -> C
			}
			
			String sql = "INSERT INTO medicamento (id_laboratorio, preco, dt_vencimento, nome_medicamento, qtd_estoque) VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, laboratorio.getId_laboratorio());
			pstmt.setFloat(2, medicamento.getPreco());
			pstmt.setDate(3, medicamento.getDt_vencimento());
			pstmt.setString(4, medicamento.getNome_medicamento());
			pstmt.setInt(5, medicamento.getQtd_estoque());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Medicamento> retrieveMedicamento(){


		String sql = "SELECT * FROM medicamento";
		
		ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				Medicamento medicamento = new Medicamento();
				

//				private Integer id_medicamento;
//				private Integer id_laboratorio;
//				private Float preco;
//				private Date dt_vencimento;
//				private String nome_medicamento;
//				private Integer qtd_estoque;

				medicamento.setId_medicamento(rs.getInt("id_medicamento"));
				medicamento.setId_laboratorio(rs.getInt("id_laboratorio"));
				medicamento.setPreco(rs.getFloat("preco"));
				medicamento.setDt_vencimento(rs.getDate("dt_vencimento"));
				medicamento.setNome_medicamento(rs.getString("nome_medicamento"));
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
	
	public void updateMedicamento(Medicamento medicamentoNovo, Integer id) {
		try {
             // create the java mysql update preparedstatement
             String query = "update medicamento set nome_medicamento = ?, preco = ?,  dt_vencimento= ?,  qtd_estoque= ? where id_medicamento = ?";
             PreparedStatement preparedStmt = connection.prepareStatement(query);
             preparedStmt.setString(1, medicamentoNovo.getNome_medicamento());
             preparedStmt.setFloat(2, medicamentoNovo.getPreco());
             preparedStmt.setDate(3, medicamentoNovo.getDt_vencimento());
             preparedStmt.setInt(4, medicamentoNovo.getQtd_estoque());
             preparedStmt.setInt(5, id);

             // execute the java preparedstatement
             preparedStmt.executeUpdate();  
             preparedStmt.close();
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
		System.out.println(medicamento.getId_medicamento() + " - " + medicamento.getNome_medicamento());
		
		
		try {

	        Scanner sc = new Scanner(System.in);
                
            System.out.println("Deseja deletar esse produto?");
            System.out.println("Digite 'D' para DELETAR:");
            
            String deletar = sc.next();
            if(deletar.equals("d") || deletar.equals("D")) {
            
              String query = "delete from medicamento where id_medicamento = ?";
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