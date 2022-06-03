package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Endereco;
import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.beans.Laboratorio;
import br.ucb.poo.beans.Telefones;
import br.ucb.poo.factory.Conexao;
import br.ucb.poo.views.LaboratorioView;

public class FuncionarioDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	public void addFuncionario(Funcionario funcionario){
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		try {
//			if(funcionario.getId_cargo() == -1) {
//				
//			}
//
//			if(funcionario.getId_departamento() == -1) {
//				
//			}
//
//			// Lab não encontrado...
//			while(laboratorio.getId_laboratorio() == -1) {
//
//		        Scanner sc = new Scanner(System.in);
//		        String opcaoMenuCriarLaboratorio = "";
//		        
//				System.out.println("Laboratorio não cadastrado!");
//				System.out.println("L) Listar todos laboratórios;");
//				System.out.println("I) Cadastrar novo laboratório;");
//				opcaoMenuCriarLaboratorio = sc.next();
//				
//				switch(opcaoMenuCriarLaboratorio) {
//				case "L":
//				case "l":
//					LaboratorioView laboratorioView = new LaboratorioView();
//					laboratorioView.listarTodosLaboratorios();
//
//					System.out.println("Encontrou o Laboratorio?");
//					
//				case "I":
//				case "i":
//					
//					break;
//				}
//				CRUD -> C
//			}
//

//			private Integer id_funcionario;
//			private Integer id_cargo;
//			private Integer id_departamento;
//			private Float salario;
//			private Date dt_adimissao;
			pessoaDAO.addPessoa(funcionario.getPessoa());

			funcionario.setId_pessoa(pessoaDAO.retrieveIdPessoaDeCpf(funcionario.getCpf()));
//			funcionario.setId_pessoa(pessoaDAO.retrieveIdPessoaDeCpf(funcionario.getPessoa().getCpf()));
			
			String sql = "INSERT INTO funcionario (id_funcionario, id_cargo, id_departamento, salario, dt_adimissao, id_pessoa) VALUES (?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, funcionario.getId_funcionario());
			pstmt.setInt(2, funcionario.getId_cargo());
			pstmt.setInt(3, funcionario.getId_departamento());
			pstmt.setFloat(4, funcionario.getSalario());
			pstmt.setDate(5, funcionario.getDt_adimissao());
			pstmt.setInt(6, funcionario.getId_pessoa());
			pstmt.execute();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

//	public void addFuncionario(Funcionario funcionario, Integer id){
//		try {
//			LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
//			Laboratorio laboratorio = new Laboratorio();
//
//			laboratorio = laboratorioDAO.retrieveLaboratorioDeID(id);
//
//			// Lab não encontrado...
//			while(laboratorio.getId_laboratorio() == -1) {
//
//		        Scanner sc = new Scanner(System.in);
//		        String opcaoMenuCriarLaboratorio = "";
//		        
//				System.out.println("Laboratorio não cadastrado!");
//				System.out.println("L) Listar todos laboratórios;");
//				System.out.println("I) Cadastrar novo laboratório;");
//				opcaoMenuCriarLaboratorio = sc.next();
//				
//				switch(opcaoMenuCriarLaboratorio) {
//				case "L":
//				case "l":
//					LaboratorioView laboratorioView = new LaboratorioView();
//					laboratorioView.listarTodosLaboratorios();
//
//					System.out.println("Encontrou o Laboratorio?");
//					
//				case "I":
//				case "i":
//					
//					break;
//				}
//			}
//			
//			String sql = "INSERT INTO funcionario (id_laboratorio, preco, dt_vencimento, nome_funcionario, qtd_estoque) VALUES (?,?,?,?,?)";
//			PreparedStatement pstmt = connection.prepareStatement(sql);
//			pstmt.setInt(1, laboratorio.getId_laboratorio());
//			pstmt.setFloat(2, funcionario.getPreco());
//			pstmt.setDate(3, funcionario.getDt_vencimento());
//			pstmt.setString(4, funcionario.getNome_funcionario());
//			pstmt.setInt(5, funcionario.getQtd_estoque());
//			pstmt.execute();
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public ArrayList<Funcionario> retrieveFuncionario(){

		String sql = "SELECT * FROM funcionario";
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				Funcionario funcionario = new Funcionario();

//				private Integer id_funcionario;
//				private Integer id_cargo;
//				private Integer id_departamento;
//				private Float salario;
//				private Date dt_adimissao;
				
				funcionario.setId_funcionario(rs.getInt("id_funcionario"));
				funcionario.setId_cargo(rs.getInt("id_cargo"));
				funcionario.setId_departamento(rs.getInt("id_departamento"));
				funcionario.setId_pessoa(rs.getInt("id_pessoa"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setSalario(rs.getFloat("salario"));
				funcionario.setDt_adimissao(rs.getDate("dt_adimissao"));

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
		for(Funcionario func : funcionarios) {
			if(func.getId_funcionario() == id) {
				return func;
			}
		}
		return null;
	}
	
	public void updateFuncionario(Funcionario funcionarioNovo) {
		try {
             // create the java mysql update preparedstatement
			
            String query = "UPDATE funcionario SET (salario = ?, dt_adimissao = ?) WHERE id_funcionario = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setFloat(1, funcionarioNovo.getSalario());
            preparedStmt.setDate(2, funcionarioNovo.getDt_adimissao());
            preparedStmt.setInt(3, funcionarioNovo.getId_funcionario());
            preparedStmt.executeUpdate();  
			
            query = "UPDATE pessoa SET (salario = ?, dt_adimissao = ?) WHERE id_funcionario = ?";
            preparedStmt = connection.prepareStatement(query);
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