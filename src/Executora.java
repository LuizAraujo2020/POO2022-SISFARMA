// SYSFARMA - DEVER 3
//DUPLA: SARAH DI FÃ�TIMA & LUIZ CARLOS

import br.ucb.poo.controles.ControleFuncionario;
import br.ucb.poo.controles.ControleMedicamento;
import br.ucb.poo.controles.Leitora;
//bão2?

public class Executora {
	static ControleMedicamento controleMedicamento;
	static ControleFuncionario controleFuncionario;
	
	public static void main(String args[]) {
		String opcaoMenuPrincipal = "A";
		
		do {
			imprimirHeader();
			imprimirMenuTelaInicial();

			opcaoMenuPrincipal = Leitora.getInstance().leTexto("").toUpperCase();
			
			System.out.println("");

			
			switch(opcaoMenuPrincipal) {
				case "M":
					controleMedicamento = new ControleMedicamento();
					controleMedicamento.gerenciarMedicamento();
					break;
	
				case "F":
					controleFuncionario = new ControleFuncionario();
					controleFuncionario.gerenciarFuncionario();
					break;
			}

		}while(!opcaoMenuPrincipal.equals("X"));
	}
	



	static public void imprimirEspacos() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}	


	static public void imprimirHeader() {
		imprimirEspacos();

		System.out.println("====================================================================");
		System.out.println("                  SUPER SISFARMA PREMIUM 2022");
		System.out.println("====================================================================");
		System.out.println("\n\n");
	}	
	
	static public void imprimirMenuTelaInicial() {

		System.out.println("MENU PRINCIPAL");
		System.out.println("\n\n");
		System.out.println("Escolha uma opÃ§Ã£o:");
		System.out.println("(M) Gerenciar medicamentos");
		System.out.println("(F) Gerenciar funcionarios");
		System.out.println("(X) Encerrar programa");
	}
}

