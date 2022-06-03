package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.beans.Laboratorio;
import br.ucb.poo.factory.Conexao;
import br.ucb.poo.views.LaboratorioView;

public class FuncionarioDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	public void addFuncionario(Funcionario funcionario){
		try {
			if(funcionario.getId_cargo() == -1) {
				
			}

			if(funcionario.getId_departamento() == -1) {
				
			}

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
			
			String sql = "INSERT INTO funcionario (id_laboratorio, preco, dt_vencimento, nome_funcionario, qtd_estoque) VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, laboratorio.getId_laboratorio());
			pstmt.setFloat(2, funcionario.getPreco());
			pstmt.setDate(3, funcionario.getDt_vencimento());
			pstmt.setString(4, funcionario.getNome_funcionario());
			pstmt.setInt(5, funcionario.getQtd_estoque());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public void addFuncionario(Funcionario funcionario, Integer id){
		try {
			LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
			Laboratorio laboratorio = new Laboratorio();

			laboratorio = laboratorioDAO.retrieveLaboratorioDeID(id);

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
			}
			
			String sql = "INSERT INTO funcionario (id_laboratorio, preco, dt_vencimento, nome_funcionario, qtd_estoque) VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, laboratorio.getId_laboratorio());
			pstmt.setFloat(2, funcionario.getPreco());
			pstmt.setDate(3, funcionario.getDt_vencimento());
			pstmt.setString(4, funcionario.getNome_funcionario());
			pstmt.setInt(5, funcionario.getQtd_estoque());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Funcionario> retrieveFuncionario(){

		String sql = "SELECT * FROM funcionario";
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				Funcionario funcionario = new Funcionario();

				funcionario.setId_funcionario(rs.getInt("id_funcionario"));
				funcionario.setId_laboratorio(rs.getInt("id_laboratorio"));
				funcionario.setPreco(rs.getFloat("preco"));
				funcionario.setDt_vencimento(rs.getDate("dt_vencimento"));
				funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
				funcionario.setQtd_estoque(rs.getInt("qtd_estoque"));

				funcionarios.add(funcionario);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	
	public Funcionario retrieveFuncionarioFromId(Integer id) {
		ArrayList<Funcionario> funcionarios = retrieveFuncionario();
		for(Funcionario med : funcionarios) {
			if(med.getId_funcionario() == id) {
				return med;
			}
		}
		return null;
	}
	
	public void updateFuncionario(Funcionario funcionarioNovo, Integer id) {
		try {
             // create the java mysql update preparedstatement
             String query = "update funcionario set nome_funcionario = ?, preco = ?,  dt_vencimento= ?,  qtd_estoque= ? where id_funcionario = ?";
             PreparedStatement preparedStmt = connection.prepareStatement(query);
             preparedStmt.setString(1, funcionarioNovo.getNome_funcionario());
             preparedStmt.setFloat(2, funcionarioNovo.getPreco());
             preparedStmt.setDate(3, funcionarioNovo.getDt_vencimento());
             preparedStmt.setInt(4, funcionarioNovo.getQtd_estoque());
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
	
	public void deletarFuncionario(Integer id) {
		
		Funcionario funcionario = retrieveFuncionarioFromId(id);

		System.out.println("ID - Nome");
		
		System.out.println("--------------------------");
		System.out.println(funcionario.getId_funcionario() + " - " + funcionario.getNome_funcionario());
		
		
		try {

	        Scanner sc = new Scanner(System.in);
                
            System.out.println("Deseja deletar esse produto?");
            System.out.println("Digite 'D' para DELETAR:");
            
            String deletar = sc.next();
            if(deletar.equals("d") || deletar.equals("D")) {
            
              String query = "delete from funcionario where id_funcionario = ?";
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