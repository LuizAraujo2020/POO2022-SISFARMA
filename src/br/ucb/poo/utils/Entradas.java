package br.ucb.poo.utils;

import java.util.Scanner;

public class Entradas {
	
	static public String receberTexto() {
		String texto = "";
		Scanner sc = new Scanner(System.in);
		
		texto = sc.nextLine();
		System.out.println(" ");
		System.out.println("asdasdasdasda " + texto);
		
		sc.close();
		
		return texto;
	}
	
	static public Integer receberInteiro() {
		Integer inteiro;
		Scanner sc = new Scanner(System.in);
		
		inteiro = sc.nextInt();
		System.out.println(" ");
		
		sc.close();
		
		return inteiro;
	}
	
	static public Float receberFloat() {
		Float decimal;
		Scanner sc = new Scanner(System.in);
		
		decimal = sc.nextFloat();
		System.out.println(" ");
		
		sc.close();
		
		return decimal;
	}
}
