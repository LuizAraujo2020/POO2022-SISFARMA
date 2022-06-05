package br.ucb.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.factory.Conexao;

public class FuncionarioDAO {

	Conexao conexao = new Conexao();
	Connection connection = conexao.conectar();
	

	public void addFuncionario(Funcionario funcionario){
		try {
			
			String sql = "INSERT INTO funcionario (endereco, telefone, salario, cpf, nome, dt_nascimento, cargo, departamento, dt_admissao) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, funcionario.getEndereco());
			pstmt.setString(2, funcionario.getTelefone());
			pstmt.setFloat(3, funcionario.getSalario());
			pstmt.setString(4, funcionario.getCpf());
			pstmt.setString(5, funcionario.getNome());
			pstmt.setDate(6, funcionario.getDt_nascimento());
			pstmt.setString(7, funcionario.getCargo());
			pstmt.setString(8, funcionario.getDepartamento());
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

		String sql = "SELECT id_funcionario, endereco, telefone, salario, cpf, nome, dt_nascimento, cargo, departamento, dt_admissao FROM funcionario";
		
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
	
	
	public Funcionario retrieveFuncionarioFromCpf(String cpf) {
		ArrayList<Funcionario> funcionarios = retrieveFuncionario();
		for(Funcionario func : funcionarios) {
			if(func.getCpf().equals(cpf)) {
				return func;
			}
		}
		return null;
	}
	
	public void updateFuncionario(Funcionario funcionarioNovo) {
		try {
            
            String query = "UPDATE funcionario SET endereco = ?, telefone = ?, cpf = ?, nome = ?, dt_nascimento = ?, cargo = ?, departamento = ?, salario = ?, dt_admissao = ? WHERE id_funcionario = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, funcionarioNovo.getEndereco());
            preparedStmt.setString(2, funcionarioNovo.getTelefone());
            preparedStmt.setString(3, funcionarioNovo.getCpf());
			preparedStmt.setString(4, funcionarioNovo.getNome());
			preparedStmt.setDate(5, funcionarioNovo.getDt_nascimento());
			preparedStmt.setString(6, funcionarioNovo.getCargo());
			preparedStmt.setString(7, funcionarioNovo.getDepartamento());
			preparedStmt.setFloat(8, funcionarioNovo.getSalario());
			preparedStmt.setDate(9, funcionarioNovo.getDt_admissao());
			preparedStmt.setInt(10, funcionarioNovo.getId_funcionario());
            
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
		System.out.println(funcionario.getId_funcionario() + " - " + funcionario.getNome() + "\n");
		
		try {

	        try (Scanner sc = new Scanner(System.in)) {
				System.out.println("Deseja deletar esse funcionario?");
				System.out.println("Digite 'D' para DELETAR:");
				
				String deletar = sc.next();
				if(deletar.equals("d") || deletar.equals("D")) {
				
				  String query = "DELETE FROM funcionario WHERE id_funcionario = ?";
				  PreparedStatement preparedStmt = connection.prepareStatement(query);
				  preparedStmt.setInt(1, id);

				  // execute the preparedstatement
				  preparedStmt.execute();
				  preparedStmt.close();
				}
			}

        } catch(SQLException e) {
            e.printStackTrace();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
}



/*
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('5', '1231', '1231', 'LJB231KH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('6', '1231', '1231', '1231', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('7', '1231', '1231', 'LJ3BKH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('8', '1231', '1231', 'LJB2KH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('9', '1231', '1231', 'LJB1KH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('10', '1231', '1231', 'LJB5KH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('11', '1231', '1231', 'LJ1B1KH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('12', '1231', '1231', 'LJ7BKH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('13', '1231', '1231', 'LJ6BKH', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('14', '1231', '1231', 'LJBKH9', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
INSERT INTO `farmacia`.`funcionario` (`id_funcionario`, `cpf`, `telefone`, `nome`, `endereco`, `dt_nascimento`, `cargo`, `departamento`, `dt_admissao`, `salario`) VALUES ('15', '1231', '1231', '3', 'fc', '2000-01-01', 'asca', 'k ksc', '2010-01-01', '10000');
*/