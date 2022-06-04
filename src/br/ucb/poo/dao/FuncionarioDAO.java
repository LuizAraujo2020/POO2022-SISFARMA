package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.factory.Conexao;
import br.ucb.poo.utils.Endereco;
import br.ucb.poo.utils.Laboratorio;
import br.ucb.poo.utils.PessoaDAO;
import br.ucb.poo.utils.Telefones;
import br.ucb.poo.views.LaboratorioView;

public class FuncionarioDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();

	public void addFuncionario(Funcionario funcionario){
		try {
			
			String sql = "INSERT INTO funcionario (endereco, telefone, salario, cpf, nome, dt_nascimento, cargo, departamento, salario, dt_admissao) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, funcionario.getEndereco());
			pstmt.setString(2, funcionario.getTelefone());
			pstmt.setString(3, funcionario.getCpf());
			pstmt.setString(4, funcionario.getNome());
			pstmt.setDate(5, funcionario.getDt_nascimento());
			pstmt.setString(6, funcionario.getCargo());
			pstmt.setString(7, funcionario.getDepartamento());
			pstmt.setFloat(8, funcionario.getSalario());
			pstmt.setDate(9, funcionario.getDt_admissao());
			pstmt.execute();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Funcionario> retrieveFuncionario(){

//		private Integer id_funcionario;
//		private String endereco;
//		private String telefone;
//		private String cpf;
//		private String nome;
//		private Date dt_nascimento;
//		private String cargo;
//		private String departamento;
//		private Float salario;
//		private Date dt_admissao;

		String sql = "SELECT (id_funcionario, endereco, telefone, salario, cpf, nome, dt_nascimento, cargo, departamento, dt_admissao) FROM funcionario";
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				Funcionario funcionario = new Funcionario();

				funcionario.setId_funcionario(rs.getInt("id_funcionario"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setDt_nascimento(rs.getDate("dt_nascimento"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setDepartamento(rs.getString("departamento"));
				funcionario.setSalario(rs.getFloat("salario"));
				funcionario.setDt_admissao(rs.getDate("dt_admissao"));

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
            
            String query = "UPDATE funcionario SET (salario = ?, dt_admissao = ?) WHERE id_funcionario = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setFloat(1, funcionarioNovo.getSalario());
            preparedStmt.setDate(2, funcionarioNovo.getDt_admissao());
            preparedStmt.setInt(3, funcionarioNovo.getId_funcionario());
            preparedStmt.executeUpdate();  
			

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
		System.out.println(funcionario.getId_funcionario() + " - " + funcionario.getNome());
		
		
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