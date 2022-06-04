package br.ucb.poo.controles;

import br.ucb.poo.beans.Medicamento;
import br.ucb.poo.dao.MedicamentoDAO;

public class ControleMedicamento {

	public void gerenciarMedicamento() {
		Leitora leitora = new Leitora();

		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		Medicamento medicamento = new Medicamento();

		String opcaoMenuInicial = "";
		String opcaoMenuListagem = "";

		//MARK: - MENU PRINCIPAL
		do {

			imprimirMenuTelaInicialMedicamento();
			System.out.println(" ");
			opcaoMenuInicial = leitora.leTexto("").toUpperCase();

			//MARK: - TELA INSERIR NOVO MEDICAMENTO
			switch(opcaoMenuInicial) {
			case "I":

				medicamento = new Medicamento();

				imprimirHeader();	

				System.out.println("CADASTRO DE MEDICAMENTO");
				System.out.println("\n\n");
				medicamento.setNome(leitora.leTexto("Nome do Medicamento:"));


				medicamento.setPreco(leitora.leFloat("Valor do medicamento: R$ "));


				medicamento.setDt_vencimento(leitora.leDate("Vencimento (dd-MM-yyyy): "));


				System.out.println("");
				medicamento.setQtd_estoque(leitora.leInteiro("Quantidade em estoque: "));

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
					System.out.println("(V) Voltar para Menu Gerenciar Medicamento");
					System.out.println("(E) Exibir um medicamento pelo - ID");
					System.out.println("(A) Atualizar medicamento - ID");
					System.out.println("(X) Apagar medicamento - ID");

					opcaoMenuListagem = leitora.leTexto("").toUpperCase();

					switch(opcaoMenuListagem) {
					case "V":
						opcaoMenuListagem = "V";
						break;

					case "E":
						Integer idParaPesquisa = 0;
						String opcaoMenuDetalheMedicamento = "";

						medicamento = new Medicamento();

						imprimirHeader();	

						System.out.println("DETALHES DO MEDICAMENTO");
						System.out.println("\n\n");
						idParaPesquisa = leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _");

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
						System.out.println("(V) Voltar para Menu Gerenciar Medicamento");
						System.out.println("(L) Voltar para listagem de medicamentos");
						System.out.println("(X) Voltar para listagem de medicamentos");

						opcaoMenuDetalheMedicamento = leitora.leTexto("OPCAO: ").toUpperCase();

						if(opcaoMenuDetalheMedicamento.equals("V")) {
							opcaoMenuListagem = "V";

						}
						break;

					case "A":

						//TODO: FAZER MENU PARA ESCOLHER CAMPO A SER ALTERADO 

						imprimirHeader();	

						System.out.println("ATUALIZAR MEDICAMENTO");
						System.out.println("\n\n");
						idParaPesquisa = leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _");


						System.out.println("Editar");

						Medicamento medicamentoAtual = new Medicamento();
						Medicamento medicamentoNovo = new Medicamento();

						medicamentoAtual = medicamentoDAO.retrieveMedicamentoFromId(idParaPesquisa);
						medicamentoNovo = medicamentoAtual;

						medicamentoNovo.setNome(leitora.leTexto("Nome atual: " + medicamentoAtual.getNome()));

						medicamentoNovo.setPreco(leitora.leFloat("Preço atual: " + medicamentoAtual.getPreco()));


						medicamentoNovo.setDt_vencimento(leitora.leDate("Vencimento"));

						medicamentoNovo.setQtd_estoque(leitora.leInteiro("Quantidade em estoque atual: " + medicamentoAtual.getQtd_estoque()));



						System.out.println("Deseja confirmar a alteracao?");
						String salvar = leitora.leTexto("Digite 's' para SALVAR:");
						if(salvar.equals("s") || salvar.equals("S")) {
							medicamentoDAO.updateMedicamento(medicamentoNovo);
						}

						break;

					case "X":

						imprimirHeader();	

						System.out.println("APAGAR MEDICAMENTO");
						System.out.println("\n\n");
						Integer idParaApagar = 0;
						idParaApagar = leitora.leInteiro("Informe o ID conforme apresentado na tela anterior: _");
//						System.out.println("Informe o ID conforme apresentado na tela anterior: _");
//						Integer idParaApagar = scanner.nextInt();
						medicamentoDAO.deletarMedicamento(idParaApagar);

						break;

					}


				} while(!opcaoMenuListagem.equals("V"));

				imprimirBottom();
				break;
			}


		} while((opcaoMenuInicial.equalsIgnoreCase("I")) || (opcaoMenuInicial.equalsIgnoreCase("L")));

//		scanner.close();
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


	public void imprimirMenuTelaInicialMedicamento() {

		imprimirHeader();	

		System.out.println("MEDICAMENTOS");
		System.out.println("\n\n");
		System.out.println("Escolha uma opção:");
		System.out.println("(L) Listar todos os medicamentos");
		System.out.println("(I) Inserir novo medicamento");
	}
}
