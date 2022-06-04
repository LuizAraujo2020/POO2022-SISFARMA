package br.ucb.poo.controles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ucb.poo.beans.Medicamento;
import br.ucb.poo.dao.MedicamentoDAO;
import br.ucb.poo.utils.Endereco;
import br.ucb.poo.utils.EnderecoDAO;
import br.ucb.poo.utils.Laboratorio;
import br.ucb.poo.utils.LaboratorioDAO;
import br.ucb.poo.utils.Telefones;
import br.ucb.poo.utils.TelefonesDAO;

public class ControleMedicamento {

	public static void gerenciarMedicamento() {

		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		Medicamento medicamento = new Medicamento();

		Scanner sc = new Scanner(System.in);

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {

			imprimirMenuTelaInicialMedicamento();
			System.out.println(" ");
			opcaoMenuInicial = sc.nextLine().toString().toUpperCase();

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

				System.out.println("Insira o nome do Laboratório: ");
				nomeLaboratorio = sc.nextLine().toString();



				//				System.out.println("Telefone: ");
				//				medicamento.setTelefone(sc.nextLine().toString());



				System.out.println("Nome do Medicamento:");
				medicamento.setNome(sc.nextLine().toString());


				System.out.println("Valor do medicamento: R$ ");
				medicamento.setPreco(Float.parseFloat(sc.nextLine()));

				Integer parseDateContadorDeErros = 0;

				do {

					System.out.println("Vencimento (yyyy-MM-dd): ");
					String stringDate = sc.nextLine().toString();

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


				System.out.println("Quantidade em estoque: ");
				medicamento.setQtd_estoque(Integer.parseInt(sc.nextLine()));

				medicamentoDAO.addMedicamento(medicamento);
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
						System.out.println(med.getId_medicamento() + " - " + med.getNome());
					}

					System.out.println("\n");

					System.out.println("Escolha uma opção:");
					System.out.println("(V) Voltar para tela principal");
					System.out.println("(E) Exibir um medicamento pelo - ID");
					System.out.println("(A) Atualizar medicamento - ID");
					System.out.println("(X) Apagar medicamento - ID");


					opcaoMenuListagem = sc.nextLine().toString().toUpperCase();

					switch(opcaoMenuListagem) {
					case "V":
						opcaoMenuListagem = "V";
						break;

					case "E":
						Integer idParaPesquisa = 0;
						String opcaoMenuDetalheMedicamento = "";

						Integer idLaboratorioParaImprimir = 0;

						medicamento = new Medicamento();

						imprimirHeader();	

						System.out.println("DETALHES DO MEDICAMENTO");
						System.out.println("\n\n");
						System.out.println("Informe o ID conforme apresentado na tela anterior: _");


						idParaPesquisa = Integer.parseInt(sc.nextLine());

						medicamento = medicamentoDAO.retrieveMedicamentoFromId(idParaPesquisa);

						imprimirHeader();	

						if(medicamento == null) {
							System.out.println("MEDICAMENTO NAO ENCONTRADO!");
							break;
							
						}
						System.out.println("DETALHES DO MEDICAMENTO ID: " + medicamento.getId_medicamento());
						System.out.println("\n\n");
						System.out.println("Nome: " + medicamento.getNome());
						System.out.println("Laboratório: " + medicamento.getLaboratorio());
						System.out.println("Preço: R$ " + medicamento.getPreco());
						System.out.println("Estoque: " + medicamento.getQtd_estoque());

						System.out.println("\nEscolha uma opção:");
						System.out.println("(V) Voltar para tela principal");
						System.out.println("(L) Voltar para listagem de medicamentos");

						opcaoMenuDetalheMedicamento = sc.nextLine().toString().toUpperCase();

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


						idParaPesquisa = Integer.parseInt(sc.nextLine());


						System.out.println("Editar");

						Medicamento medicamentoAtual = new Medicamento();
						Medicamento medicamentoNovo = new Medicamento();

						medicamentoAtual = medicamentoDAO.retrieveMedicamentoFromId(idParaPesquisa);
						medicamentoNovo = medicamentoAtual;

						System.out.println("Nome atual: " + medicamentoAtual.getNome());
						medicamentoNovo.setNome(sc.nextLine().toString());

						System.out.println("Preço atual: " + medicamentoAtual.getPreco());
						medicamentoNovo.setPreco(Float.parseFloat(sc.nextLine()));


						parseDateContadorDeErros = 0;

						do {
							System.out.println("Vencimento (yyyy-MM-dd): ");
							String stringDate = sc.nextLine().toString();

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
						medicamentoNovo.setQtd_estoque(Integer.parseInt(sc.nextLine()));



						System.out.println("Deseja confirmar a alteracao?");
						System.out.println("Digite 's' para SALVAR:");
						String salvar = sc.next();
						if(salvar.equals("s") || salvar.equals("S")) {
							medicamentoDAO.updateMedicamento(medicamentoNovo);
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
