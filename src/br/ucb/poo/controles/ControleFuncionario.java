package br.ucb.poo.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.dao.FuncionarioDAO;

public class ControleFuncionario {

	public void gerenciarFuncionario() {
		Leitora leitora = new Leitora();

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {
			
			imprimirMenuTelaInicialFuncionario();
			
			opcaoMenuInicial = leitora.leTexto(" ").toUpperCase();

			//MARK: - TELA INSERIR NOVO FUNCIONARIO
			switch(opcaoMenuInicial) {
			case "I":
				
				funcionario = new Funcionario();
				

				imprimirHeader();	

				System.out.println("CADASTRO DE FUNCIONARIO");

				System.out.println("\n\n");

				funcionario.setNome(leitora.leTexto("Insira o nome do Funcionario: "));
				funcionario.setCpf(leitora.leTexto("Insira o CPF do Funcionario: "));
				funcionario.setDt_nascimento(leitora.leDate("Nascimento"));

				funcionario.setEndereco(leitora.leTexto("\nInsira o Endereço completo: "));
				funcionario.setTelefone(leitora.leTexto("\nInsira um Telefone para contato: "));

				funcionario.setDepartamento(leitora.leTexto("Insira o Departamento do Funcionario: "));
				funcionario.setCargo(leitora.leTexto("Insira o Cargo do Funcionario: "));
				
				funcionario.setSalario(leitora.leFloat("Insira o Salario: "));
				
				funcionario.setDt_admissao(leitora.leDate("Insira a Data de admissao do Funcionario: "));

				
				funcionarioDAO.addFuncionario(funcionario);
				break;

				//MARK: - TELA LISTAGEM
			case "L":
				do {

					imprimirHeader();	

					System.out.println("LISTAGEM DE FUNCIONARIOS");

					System.out.println("ID - Nome");

					System.out.println("--------------------------");
					for(Funcionario func : funcionarioDAO.retrieveFuncionario() ){
						System.out.println(func.getId_funcionario() + " - " + func.getNome());
					}

					System.out.println("\n");

					System.out.println("Escolha uma opção:");
					System.out.println("(V) Voltar para tela principal");
					System.out.println("(E) Exibir um funcionario pelo - ID");
					System.out.println("(A) Atualizar funcionario - ID");
					System.out.println("(X) Apagar funcionario - ID");


					opcaoMenuListagem = leitora.leTexto("").toUpperCase();

					switch(opcaoMenuListagem) {
					case "V":
						opcaoMenuListagem = "V";
						break;

					case "E":
						Integer idParaPesquisa = 0;
						String opcaoMenuDetalheFuncionario = "";

						imprimirHeader();	

						System.out.println("DETALHES DO FUNCIONARIO");
						System.out.println("\n\n");

						do {
							idParaPesquisa = leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _");
	
							funcionario = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
							
							if(funcionario.getId_funcionario() == null) {
								System.out.println("FUNCIONARIO NAO ENCONTRADO!");
							}
							
						}while(funcionario.getId_funcionario() == null);

						
						imprimirHeader();	
						
						if(funcionario.getId_funcionario() != null) {
							System.out.println("DETALHES DO FUNCIONARIO ID: " + funcionario.getId_funcionario());
							System.out.println("\n\n");
							System.out.println("Nome: " + funcionario.getNome());
							System.out.println("Departamento: " + funcionario.getDepartamento());
							System.out.println("Cargo: " + funcionario.getCargo());
							System.out.println("\nEscolha uma opção:");
							System.out.println("(V) Voltar para tela principal");
							System.out.println("(L) Voltar para listagem de funcionarios");
						}

						opcaoMenuDetalheFuncionario = leitora.leTexto("").toUpperCase();

						if(opcaoMenuDetalheFuncionario.equals("V")) {
							opcaoMenuListagem = "V";

						}
						break;
						
					case "A":

						//TODO: FAZER MENU PARA ESCOLHER CAMPO A SER ALTERADO 

						imprimirHeader();	

						System.out.println("ATUALIZAR FUNCIONARIO");
						System.out.println("\n\n");

						idParaPesquisa = leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _");

						System.out.println("Editar");

						Funcionario funcionarioAtual = new Funcionario();
						Funcionario funcionarioNovo = new Funcionario();

						funcionarioAtual = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
						funcionarioNovo = funcionarioAtual;
						
						

						
						//Menu para escolher o que alterar
						String opcaoMenuAtualizarFuncionario = "X";
						do {

							System.out.println("Escolha uma opção:");
							System.out.println("(EN) Atualizar Endereco");
							System.out.println("(TE) Atualizar Telefone");
							System.out.println("(CP) Atualizar CPF");
							System.out.println("(NO) Atualizar Nome");
							System.out.println("(DN) Atualizar Data de Nascimento");
							System.out.println("(CA) Atualizar Cargo");
							System.out.println("(DE) Atualizar Departamento");
							System.out.println("(SA) Atualizar Salario");
							System.out.println("(DA) Atualizar Data de Admissao");
							
							
							opcaoMenuAtualizarFuncionario = leitora.leTexto("OPCAO: ").toUpperCase();
							
							switch(opcaoMenuAtualizarFuncionario) {
							case "EN":
								System.out.println("Endereco atual:\n" + funcionarioAtual.getEndereco() + "\n novo:");
								funcionarioNovo.setEndereco(leitora.leTexto());
								break;

							case "TE":
								System.out.println("Telefone atual:\n" + funcionarioAtual.getTelefone() + "\nTelefone novo:");
								funcionarioNovo.setTelefone(leitora.leTexto());
								break;

							case "CP":
								System.out.println("CPF atual:\n" + funcionarioAtual.getCpf() + "\nCPF novo:");
								funcionarioNovo.setCpf(leitora.leTexto());
								break;

							case "NO":
								System.out.println("Nome atual:\n" + funcionarioAtual.getNome() + "\nNome novo:");
								funcionarioNovo.setNome(leitora.leTexto());
								break;

							case "DN":
								System.out.println("Data de Nascimento atual:\n" + funcionarioAtual.getDt_nascimento() + "\nData de Nascimento nova:");
								funcionarioNovo.setDt_nascimento(leitora.leDate());
								
								break;

							case "CA":
								System.out.println("Cargo atual:\n" + funcionarioAtual.getCargo() + "\nCargo novo:");
								funcionarioNovo.setCargo(leitora.leTexto());
								break;

							case "DE":
								System.out.println("Departamento atual:\n" + funcionarioAtual.getDepartamento() + "\nDepartamento novo:");
								funcionarioNovo.setDepartamento(leitora.leTexto());
								break;

							case "SA":
								System.out.println("Salario atual:\n" + funcionarioAtual.getSalario() + "\nSalario novo:");
								funcionarioNovo.setSalario(leitora.leFloat());
								break;

							case "DA":
								System.out.println("Data de Admissao atual:\n" + funcionarioAtual.getDt_admissao() + "\nData de Admissao novo:");
								funcionarioNovo.setDt_admissao(leitora.leDate());
								break;
							}
						} while(!opcaoMenuAtualizarFuncionario.equals("X"));

						break;

					case "D":

						imprimirHeader();	

						System.out.println("DELETAR FUNCIONARIO");
						System.out.println("\n\n");
						funcionarioDAO.deletarFuncionario(leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _"));

						break;

					}


				} while(!opcaoMenuListagem.equals("V"));

				imprimirBottom();
				break;
			}


		} while((opcaoMenuInicial.equalsIgnoreCase("I")) || (opcaoMenuInicial.equalsIgnoreCase("L")));

		imprimirBottom();
	}


	public void imprimirHeader() {
		for (int i = 0; i < 50; ++i) System.out.println();

		System.out.println("====================================================================");
		System.out.println("                  SUPER SISFARMA PREMIUM 2022");
		System.out.println("====================================================================");
		System.out.println("\n\n");
	}


	public void imprimirBottom() {
		System.out.println("\n\n");
		System.out.println("====================================================================");
		System.out.println("\n\n\n\n");
	}


	public void imprimirMenuTelaInicialFuncionario() {

		imprimirHeader();	

		System.out.println("FUNCIONARIOS");
		System.out.println("\n\n");
		System.out.println("Escolha uma opção:");
		System.out.println("(L) Listar todos os funcionarios");
		System.out.println("(I) Inserir novo funcionario");
	}
}
