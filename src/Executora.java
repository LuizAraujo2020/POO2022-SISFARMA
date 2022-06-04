// SYSFARMA - DEVER 3
//DUPLA: SARAH DI FÁTIMA & LUIZ CARLOS

import java.util.Scanner;

import br.ucb.poo.controles.ControleFuncionario;
import br.ucb.poo.controles.ControleMedicamento;

public class Executora {
	static ControleMedicamento controleMedicamento;
	static ControleFuncionario controleFuncionario;
	
	public static void main(String args[]) {
		String opcaoMenuPrincipal = "A";
		
		do {
			imprimirHeader();
			imprimirMenuTelaInicial();
			
			Scanner sc = new Scanner(System.in);
			opcaoMenuPrincipal = sc.next().toUpperCase();
			
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
			controleFuncionario = null;
			controleMedicamento = null;
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
		System.out.println("Escolha uma opção:");
		System.out.println("(M) Gerenciar medicamentos");
		System.out.println("(F) Gerenciar funcionarios");
		System.out.println("(X) Encerrar programa");
	}
}

