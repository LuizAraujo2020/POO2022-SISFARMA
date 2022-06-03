package br.ucb.poo.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ucb.poo.beans.Endereco;
import br.ucb.poo.beans.Laboratorio;
import br.ucb.poo.beans.Medicamento;
import br.ucb.poo.beans.Telefones;
import br.ucb.poo.dao.EnderecoDAO;
import br.ucb.poo.dao.LaboratorioDAO;
import br.ucb.poo.dao.MedicamentoDAO;
import br.ucb.poo.dao.TelefonesDAO;

public class ControleFuncionario {


	public static void gerenciarFuncionario() {

		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		TelefonesDAO telefonesDAO = new TelefonesDAO();

		Medicamento medicamento = new Medicamento();

		Scanner sc = new Scanner(System.in);

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {
			/// Limpar Mem Buffer
			sc = new Scanner(System.in);

			imprimirMenuTelaInicialMedicamento();
			System.out.println(" ");
			opcaoMenuInicial = sc.nextLine().toUpperCase();

			//MARK: - TELA INSERIR NOVO MEDICAMENTO
			switch(opcaoMenuInicial) {
			case "I":
				
				medicamento = new Medicamento();

				Laboratorio laboratorio = new Laboratorio();
				Endereco endereco = new Endereco();
				Telefones telefones = new Telefones();

				Integer idLaboratorio;
				String nomeLaboratorio = "";

				imprimirHeader();	

				System.out.println("CADASTRO DE MEDICAMENTO");

				System.out.println("\n\n");

				sc = new Scanner(System.in);

				System.out.println("Insira o nome do Laboratório: ");
				nomeLaboratorio = sc.next();

				idLaboratorio = laboratorioDAO.retrieveIdLaboratorioDeNome(nomeLaboratorio);


				if(idLaboratorio >= 0) {
					medicamento.setId_laboratorio(idLaboratorio);

				} else {
					System.out.println("Laboratório ainda não cadastrado!");
					System.out.println("Por favor insira: ");

					laboratorio = new Laboratorio();

					// Pega o nome fornecido anteriormente.
					laboratorio.setNome(nomeLaboratorio);

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

					// Por o Endereco e o Telefones no Laboratorio.
					laboratorio.setEndereco(endereco);
					laboratorio.setTelefones(telefones);

					laboratorioDAO.addLaboratorio(laboratorio);

					medicamento.setId_laboratorio(laboratorioDAO.retrieveIdLaboratorioDeNome(laboratorio.getNome()));

				}


				// Limpar Buffer
				sc = new Scanner(System.in);
				System.out.println("Nome do Medicamento:");
				medicamento.setNome_medicamento(sc.nextLine());


				sc = new Scanner(System.in);
				System.out.println("Valor do medicamento: R$ ");
				medicamento.setPreco(sc.nextFloat());

				Integer parseDateContadorDeErros = 0;

				do {
					sc = new Scanner(System.in);
					System.out.println("Vencimento (yyyy-MM-dd): ");
					String stringDate = sc.nextLine();

					try {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date myDate = formatter.parse(stringDate);
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						sqlDate = new java.sql.Date(myDate.getTime());

						medicamento.setDt_vencimento(sqlDate);

						parseDateContadorDeErros = 0;

					} catch (ParseException e1) {
						//						e1.printStackTrace();
						parseDateContadorDeErros += 1;
						System.out.println("Data inválida!");
					}
				}while(parseDateContadorDeErros > 0);

				sc = new Scanner(System.in);
				System.out.println("Quantidade em estoque: ");
				medicamento.setQtd_estoque(sc.nextInt());
				
				medicamentoDAO.addMedicamento(medicamento, nomeLaboratorio);
				break;

				//MARK: - TELA LISTAGEM
			case "L":
				do {
					medicamento = new Medicamento();

					imprimirHeader();	

					System.out.println("LISTAGEM DE MEDICAMENTOS");

					System.out.println("ID - Nome");

					System.out.println("--------------------------");
					for(Medicamento med : medicamentoDAO.retrieveMedicamento() ){
						System.out.println(med.getId_medicamento() + " - " + med.getNome_medicamento());
					}

					System.out.println("\n");

					System.out.println("Escolha uma opção:");
					System.out.println("(V) Voltar para tela principal");
					System.out.println("(E) Exibir um medicamento pelo - ID");
					System.out.println("(A) Atualizar medicamento - ID");
					System.out.println("(X) Apagar medicamento - ID");

					/// Limpar Mem Buffer
					sc = new Scanner(System.in);

					opcaoMenuListagem = sc.nextLine().toUpperCase();

					switch(opcaoMenuListagem) {
					case "V":
						opcaoMenuListagem = "V";
						break;

					case "E":
						Integer idParaPesquisa = 0;
						String opcaoMenuDetalheMedicamento = "";

						String nomeLaboratorioParaImprimir = "";
						Integer idLaboratorioParaImprimir = 0;

						medicamento = new Medicamento();

						imprimirHeader();	

						System.out.println("DETALHES DO MEDICAMENTO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");

						/// Limpar Mem Buffer
						sc = new Scanner(System.in);
						idParaPesquisa = sc.nextInt();

						medicamento = medicamentoDAO.retrieveMedicamentoFromId(idParaPesquisa);
						laboratorio = laboratorioDAO.retrieveLaboratorioDeID(medicamento.getId_laboratorio());

						imprimirHeader();	

						if(medicamento.getId_medicamento() == null) {
							System.out.println("MEDICAMENTO NAO ENCONTRADO!");
							break;
						}
						System.out.println("DETALHES DO MEDICAMENTO ID: " + medicamento.getId_medicamento());
						System.out.println("\n\n");
						System.out.println("Nome: " + medicamento.getNome_medicamento());
						System.out.println("Laboratório: " + laboratorio.getId_laboratorio() + " - " + laboratorio.getNome());
						System.out.println("Preço: R$ " + medicamento.getPreco());
						System.out.println("Estoque: " + medicamento.getQtd_estoque());

						System.out.println("\nEscolha uma opção:");
						System.out.println("(V) Voltar para tela principal");
						System.out.println("(L) Voltar para listagem de medicamentos");

						/// Limpar Mem Buffer
						sc = new Scanner(System.in);

						opcaoMenuDetalheMedicamento = sc.nextLine().toUpperCase();

						if(opcaoMenuDetalheMedicamento.equals("V")) {
							opcaoMenuListagem = "V";

						}
						break;
						
					case "A":

						//TODO: FAZER MENU PARA ESCOLHER CAMPO A SER ALTERADO 

						imprimirHeader();	

						System.out.println("ATUALIZAR MEDICAMENTO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");


						/// Limpar Mem Buffer
						sc = new Scanner(System.in);
						idParaPesquisa = sc.nextInt();



						System.out.println("Editar");


						Medicamento medicamentoAtual = new Medicamento();
						Medicamento medicamentoNovo = new Medicamento();

						medicamentoAtual = medicamentoDAO.retrieveMedicamentoFromId(idParaPesquisa);
						medicamentoNovo = medicamentoAtual;



						/// Limpar Mem Buffer
						sc = new Scanner(System.in);

						System.out.println("Nome atual: " + medicamentoAtual.getNome_medicamento());
						medicamentoNovo.setNome_medicamento(sc.nextLine());

						System.out.println("Preço atual: " + medicamentoAtual.getPreco());
						medicamentoNovo.setPreco(sc.nextFloat());


						parseDateContadorDeErros = 0;

						do {
							sc = new Scanner(System.in);
							System.out.println("Vencimento (yyyy-MM-dd): ");
							String stringDate = sc.nextLine();

							try {
								DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								Date myDate = formatter.parse(stringDate);
								java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
								sqlDate = new java.sql.Date(myDate.getTime());

								medicamentoNovo.setDt_vencimento(sqlDate);

								parseDateContadorDeErros = 0;

							} catch (ParseException e1) {
								//    								e1.printStackTrace();
								parseDateContadorDeErros += 1;
								System.out.println("Data invÃ¡lida!");
							}
						}while(parseDateContadorDeErros > 0);


						System.out.println("Quantidade em estoque atual: " + medicamentoAtual.getQtd_estoque());
						medicamentoNovo.setQtd_estoque(sc.nextInt());



						System.out.println("Deseja confirmar a alteracao?");
						System.out.println("Digite 's' para SALVAR:");
						String salvar = sc.next();
						if(salvar.equals("s") || salvar.equals("S")) {
							medicamentoDAO.updateMedicamento(medicamentoNovo, medicamentoAtual.getId_medicamento());
						}

						break;

					case "X":

						imprimirHeader();	

						System.out.println("APAGAR MEDICAMENTO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");

						medicamentoDAO.deletarMedicamento(sc.nextInt());

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

	static public void imprimirMenuTelaInicialMedicamento() {

		imprimirHeader();	

		System.out.println("MEDICAMENTOS");
		System.out.println("\n\n");
		System.out.println("Escolha uma opção:");
		System.out.println("(L) Listar todos os medicamentos");
		System.out.println("(I) Inserir novo medicamento");
	}
}
