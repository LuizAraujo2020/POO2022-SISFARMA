package br.ucb.poo.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ucb.poo.beans.Cargo;
import br.ucb.poo.beans.Departamento;
import br.ucb.poo.beans.Endereco;
import br.ucb.poo.beans.Funcionario;
import br.ucb.poo.beans.Telefones;
import br.ucb.poo.dao.CargoDAO;
import br.ucb.poo.dao.DepartamentoDAO;
import br.ucb.poo.dao.EnderecoDAO;
import br.ucb.poo.dao.FuncionarioDAO;
import br.ucb.poo.dao.TelefonesDAO;

public class ControleFuncionario {


	public static void gerenciarFuncionario() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		TelefonesDAO telefonesDAO = new TelefonesDAO();
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		CargoDAO cargoDAO = new CargoDAO();
		

		Funcionario funcionario = new Funcionario();

		Scanner sc = new Scanner(System.in);

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {
			/// Limpar Mem Buffer
//			sc = new Scanner(System.in);

			imprimirMenuTelaInicialFuncionario();
			System.out.println(" ");
			opcaoMenuInicial = sc.nextLine().toUpperCase();

			//MARK: - TELA INSERIR NOVO FUNCIONARIO
			switch(opcaoMenuInicial) {
			case "I":
				
				funcionario = new Funcionario();

				
				Endereco endereco = new Endereco();
				Telefones telefones = new Telefones();
				Cargo cargo = new Cargo();
				Departamento departamento = new Departamento();
				

				imprimirHeader();	

				System.out.println("CADASTRO DE FUNCIONARIO");

				System.out.println("\n\n");

//				sc = new Scanner(System.in);

				System.out.println("Insira o nome do Funcionario: ");
				funcionario.setNome(sc.next());
				
				System.out.println("Insira o CPF do Funcionario: ");
				funcionario.setCpf(sc.nextLine());
				
				Integer parseDateContadorDeErros = 0;

				do {
					sc = new Scanner(System.in);
					System.out.println("Nascimento (yyyy-MM-dd): ");
					String stringDate = sc.nextLine();

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

				sc = new Scanner(System.in);
				System.out.println("\n# Endereço");
				System.out.println("Insira o CEP: ");
				endereco.setCep(sc.nextLine());


				sc = new Scanner(System.in);

				System.out.println("Insira o número do lote/casa: ");
				endereco.setNumero(sc.nextInt());

				sc = new Scanner(System.in);

				System.out.println("\n# Contato");
				System.out.println("Tel. Celular: ");
				telefones.setCelular(sc.nextLine());


				sc = new Scanner(System.in);
				System.out.println("Tel. Comercial: ");
				telefones.setComercial(sc.nextLine());

				telefones.setResidencial("00000-0000");


				enderecoDAO.addEndereco(endereco);
				telefonesDAO.addTelefones(telefones);

				// Conectar os ID do Telefones e do Endereco como o Laboratorio.
				endereco.setId_endereco(enderecoDAO.retrieveIdEndereco(endereco));
				telefones.setId_telefones(telefonesDAO.retrieveIdDeTelefones(telefones));

				
				enderecoDAO.addEndereco(endereco);
				telefonesDAO.addTelefones(telefones);
				
				System.out.println("Insira o Departamento do Funcionario: ");
				departamento.setNome(sc.nextLine());
				
				System.out.println("Insira o Cargo do Funcionario: ");
				cargo.setNome(sc.nextLine());
				
				
				
				cargo.setId_cargo(cargoDAO.retrieveIdCargoDeNome(cargo.getNome()));
				departamento.setId_departamento(departamentoDAO.retrieveIdDepartamentoDeNome(departamento.getNome()));
				
				funcionario.setId_cargo(cargo.getId_cargo());
				funcionario.setId_departamento(departamento.getId_departamento());
				
				System.out.println("Insira o Salario do Funcionario: ");
				funcionario.setSalario(sc.nextFloat());
				
				System.out.println("Insira a Data de admissao do Funcionario: ");
				
				parseDateContadorDeErros = 0;

				do {
					sc = new Scanner(System.in);
					System.out.println("Admissao (yyyy-MM-dd): ");
					String stringDate = sc.nextLine();

					try {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date myDate = formatter.parse(stringDate);
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						sqlDate = new java.sql.Date(myDate.getTime());

						funcionario.setDt_adimissao(sqlDate);

						parseDateContadorDeErros = 0;

					} catch (ParseException e1) {
						//						e1.printStackTrace();
						parseDateContadorDeErros += 1;
						System.out.println("Data inválida!");
					}
				}while(parseDateContadorDeErros > 0);
				
				
//				private String cpf;
//				private String nome;
//				private Date dt_nascimento;
//				private Endereco endereco;
//				private Telefones telefones;
//				
//				private Integer id_funcionario;
//				private Integer id_cargo;
//				private Integer id_departamento;
//				private Float salario;
//				private Date dt_adimissao;



				if(funcionario.getId_cargo() == -1) {
					cargoDAO.addCargo(cargo);
				}

				if(funcionario.getId_departamento() == -1) {
					departamentoDAO.addDepartamento(departamento);
				}
				
				funcionario.setId_cargo(cargoDAO.retrieveIdCargoDeNome(cargo.getNome()));
				funcionario.setId_departamento(departamentoDAO.retrieveIdDepartamentoDeNome(departamento.getNome()));

				
				
				funcionarioDAO.addFuncionario(funcionario);
				break;

				//MARK: - TELA LISTAGEM
			case "L":
				do {
					funcionario = new Funcionario();

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

					/// Limpar Mem Buffer
					sc = new Scanner(System.in);

					opcaoMenuListagem = sc.nextLine().toUpperCase();

					switch(opcaoMenuListagem) {
					case "V":
						opcaoMenuListagem = "V";
						break;

					case "E":
						Integer idParaPesquisa = 0;
						String opcaoMenuDetalheFuncionario = "";

						String nomeLaboratorioParaImprimir = "";
						Integer idLaboratorioParaImprimir = 0;

						funcionario = new Funcionario();

						imprimirHeader();	

						System.out.println("DETALHES DO FUNCIONARIO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");

						/// Limpar Mem Buffer
						sc = new Scanner(System.in);
						idParaPesquisa = sc.nextInt();

						funcionario = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
						endereco = enderecoDAO.retrieveEnderecoDeId(funcionario.getPessoa().getId_endereco());
						telefones = telefonesDAO.retrieveTelefonesDeId(funcionario.getPessoa().getId_telefones());
						cargo = cargoDAO.retrieveCargoDeId(funcionario.getId_cargo());
						departamento = departamentoDAO.retrieveDepartamentoDeId(funcionario.getId_departamento());

						imprimirHeader();	

						if(funcionario.getId_funcionario() == null) {
							System.out.println("FUNCIONARIO NAO ENCONTRADO!");
							break;
						}
						System.out.println("DETALHES DO FUNCIONARIO ID: " + funcionario.getId_funcionario());
						System.out.println("\n\n");
						System.out.println("Nome: " + funcionario.getNome());
						System.out.println("Departamento: " + departamento.getNome());
						System.out.println("Cargo: " + cargo.getNome());
						System.out.println("\nEscolha uma opção:");
						System.out.println("(V) Voltar para tela principal");
						System.out.println("(L) Voltar para listagem de funcionarios");

						/// Limpar Mem Buffer
						sc = new Scanner(System.in);

						opcaoMenuDetalheFuncionario = sc.nextLine().toUpperCase();

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


						/// Limpar Mem Buffer
						sc = new Scanner(System.in);
						idParaPesquisa = sc.nextInt();



						System.out.println("Editar");


						Funcionario funcionarioAtual = new Funcionario();
						Funcionario funcionarioNovo = new Funcionario();

						funcionarioAtual = funcionarioDAO.retrieveFuncionarioFromId(idParaPesquisa);
						funcionarioNovo = funcionarioAtual;

						break;

					case "X":

						imprimirHeader();	

						System.out.println("APAGAR FUNCIONARIO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");

						funcionarioDAO.deletarFuncionario(sc.nextInt());

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
