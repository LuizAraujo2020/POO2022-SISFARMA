package br.ucb.poo.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.dao.FuncionarioDAO;

public class ControleFuncionario {

	public static void gerenciarFuncionario() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();

		Scanner sc = new Scanner(System.in);

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {
			
			imprimirMenuTelaInicialFuncionario();
			System.out.println(" ");
			
			opcaoMenuInicial = sc.nextLine().toString();

			//MARK: - TELA INSERIR NOVO FUNCIONARIO
			switch(opcaoMenuInicial) {
			case "I":
				
				funcionario = new Funcionario();
				

				imprimirHeader();	

				System.out.println("CADASTRO DE FUNCIONARIO");

				System.out.println("\n\n");


				System.out.println("Insira o nome do Funcionario: ");
				funcionario.setNome(sc.nextLine().toString());
				
				System.out.println("Insira o CPF do Funcionario: ");
				funcionario.setCpf(sc.nextLine().toString());
				
				Integer parseDateContadorDeErros = 0;

				do {
					System.out.println("Nascimento (yyyy-MM-dd): ");
					String stringDate = sc.nextLine().toString();

					try {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date myDate = formatter.parse(stringDate);
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						sqlDate = new java.sql.Date(myDate.getTime());

						funcionario.setDt_nascimento(sqlDate);

						parseDateContadorDeErros = 0;

					} catch (ParseException e1) {
						//						e1.printStackTrace();
						parseDateContadorDeErros += 1;
						System.out.println("Data inválida!");
					}
				}while(parseDateContadorDeErros > 0);

				System.out.println("\nEndereço");
				System.out.println("Insira o Endereço completo: ");
				funcionario.setEndereco(sc.nextLine().toString());

				System.out.println("\nInsira um Telefone para contato: ");
				funcionario.setTelefone(sc.nextLine().toString());
				
				System.out.println("Insira o Departamento do Funcionario: ");
				funcionario.setDepartamento(sc.nextLine().toString());
				
				System.out.println("Insira o Cargo do Funcionario: ");
				funcionario.setCargo(sc.nextLine().toString());
				
				System.out.println("Insira o Salario: ");
				funcionario.setSalario(Float.parseFloat(sc.nextLine()));
				
				System.out.println("Insira a Data de admissao do Funcionario: ");
				parseDateContadorDeErros = 0;

				do {
					 
					System.out.println("Admissao (dd-MM-yyyy): ");
					String stringDate = sc.nextLine().toString();

					try {
						DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						Date myDate = formatter.parse(stringDate);
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						sqlDate = new java.sql.Date(myDate.getTime());

						funcionario.setDt_admissao(sqlDate);

						parseDateContadorDeErros = 0;

					} catch (ParseException e1) {
						//e1.printStackTrace();
						parseDateContadorDeErros += 1;
						System.out.println("Data inválida!");
					}
				}while(parseDateContadorDeErros > 0);
				
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


					opcaoMenuListagem = sc.nextLine().toString().toUpperCase();

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
							System.out.println("Informe o ID conforme apresentado na tela anterior: _");
							idParaPesquisa = Integer.parseInt(sc.nextLine());
	
							funcionario = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
	
							imprimirHeader();	

							System.out.println("FUNCIONARIO NAO ENCONTRADO!");
							
						}while(funcionario.getId_funcionario() == null);
						
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

						opcaoMenuDetalheFuncionario = sc.nextLine().toString().toUpperCase();

						if(opcaoMenuDetalheFuncionario.equals("V")) {
							opcaoMenuListagem = "V";

						}
						break;
						
					case "A":

						//TODO: FAZER MENU PARA ESCOLHER CAMPO A SER ALTERADO 

						imprimirHeader();	

						System.out.println("ATUALIZAR FUNCIONARIO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");


						idParaPesquisa = Integer.parseInt(sc.nextLine());

						System.out.println("Editar");


						Funcionario funcionarioAtual = new Funcionario();
						Funcionario funcionarioNovo = new Funcionario();

						funcionarioAtual = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
						funcionarioNovo = funcionarioAtual;
						
						

						
						//Menu para escolher o que alterar
						String opcaoMenuAtualizarFuncionario = "X";
						do {
							opcaoMenuAtualizarFuncionario = sc.nextLine().toString().toUpperCase();
							
							switch(opcaoMenuAtualizarFuncionario) {
							case "EN":
								funcionarioNovo.setEndereco(sc.nextLine().toString());
								break;

							case "TE":
								funcionarioNovo.setTelefone(sc.nextLine().toString());
								break;

							case "CP":
								funcionarioNovo.setCpf(sc.nextLine().toString());
								break;

							case "NO":
								funcionarioNovo.setNome(sc.nextLine().toString());
								break;

							case "DN":
								parseDateContadorDeErros = 0;

								do {
									 
									System.out.println("Data de nascimento (dd-MM-yyyy): ");
									String stringDate = sc.nextLine().toString();

									try {
										DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
										Date myDate = formatter.parse(stringDate);
										java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
										sqlDate = new java.sql.Date(myDate.getTime());

										funcionarioNovo.setDt_nascimento(sqlDate);

										parseDateContadorDeErros = 0;

									} catch (ParseException e1) {
										//e1.printStackTrace();
										parseDateContadorDeErros += 1;
										System.out.println("Data inválida!");
									}
								}while(parseDateContadorDeErros > 0);
								
								break;

							case "CA":
								funcionarioNovo.setCargo(sc.nextLine().toString());
								break;

							case "DE":
								funcionarioNovo.setDepartamento(sc.nextLine().toString());
								break;

							case "SA":
								funcionarioNovo.setSalario(Float.parseFloat(sc.nextLine()));
								break;

							case "DA":
								parseDateContadorDeErros = 0;

								do {
									 
									System.out.println("Admissao (dd-MM-yyyy): ");
									String stringDate = sc.nextLine().toString();

									try {
										DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
										Date myDate = formatter.parse(stringDate);
										java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
										sqlDate = new java.sql.Date(myDate.getTime());

										funcionarioNovo.setDt_admissao(sqlDate);

										parseDateContadorDeErros = 0;

									} catch (ParseException e1) {
										//e1.printStackTrace();
										parseDateContadorDeErros += 1;
										System.out.println("Data inválida!");
									}
								}while(parseDateContadorDeErros > 0);
								break;

								
							}
						} while(!opcaoMenuAtualizarFuncionario.equals("X"));

						break;

					case "X":

						imprimirHeader();	

						System.out.println("APAGAR FUNCIONARIO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");

						funcionarioDAO.deletarFuncionario(Integer.parseInt(sc.nextLine()));

						break;

					}


				} while(!opcaoMenuListagem.equals("V"));

				imprimirBottom();
				break;
			}


		} while((opcaoMenuInicial.equalsIgnoreCase("I")) || (opcaoMenuInicial.equalsIgnoreCase("L")));

		sc.close();
		imprimirBottom();
	}


	static public void imprimirHeader() {
		for (int i = 0; i < 50; ++i) System.out.println();

		System.out.println("====================================================================");
		System.out.println("                  SUPER SISFARMA PREMIUM 2022");
		System.out.println("====================================================================");
		System.out.println("\n\n");
	}


	static public void imprimirBottom() {
		System.out.println("\n\n");
		System.out.println("====================================================================");
		System.out.println("\n\n\n\n");
	}

	static public void imprimirMenuTelaInicialFuncionario() {

		imprimirHeader();	

		System.out.println("FUNCIONARIOS");
		System.out.println("\n\n");
		System.out.println("Escolha uma opção:");
		System.out.println("(L) Listar todos os funcionarios");
		System.out.println("(I) Inserir novo funcionario");
	}
}
