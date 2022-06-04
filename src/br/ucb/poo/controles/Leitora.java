package br.ucb.poo.controles;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Leitora {
	Scanner sc = new Scanner(System.in);
	
	public Integer leInteiro() {
		Integer result = sc.nextInt();
		System.out.println("Integer result = sc.nextInt(); " + result);
		
		return result;
	}
	
	public Integer leInteiro(String mensagem) {
		System.out.println(mensagem);
		Integer result = sc.nextInt();
		System.out.println("Integer result = sc.nextInt(); " + result);
		
		return result;
	}
	
	public String leTexto() {
		sc = new Scanner(System.in);
		
		String result = sc.nextLine().toString();
		System.out.println("String result = sc.nextLine(); " + result);
		
		return result;
	}
	
	public String leTexto(String mensagem) {
		sc = new Scanner(System.in);
		System.out.println(mensagem);
		
		String result = sc.nextLine().toString();
		System.out.println("String result = sc.nextLine(); " + result);
		
		return result;
	}
	
	public Float leFloat() {
		Float result = sc.nextFloat();
		
		return result;
	}
	
	public Float leFloat(String mensagem) {
		System.out.println(mensagem);
		Float result = sc.nextFloat();
		
		return result;
	}
	
	public Date leDate() {
		Integer parseDateContadorDeErros = 0;
		String dateFormat = "dd-MM-yyyy";
			
		do {
			String stringDate = sc.nextLine();
			
			
			try {
				DateFormat formatter = new SimpleDateFormat(dateFormat);
				java.util.Date myDate = formatter.parse(stringDate);
				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				sqlDate = new java.sql.Date(myDate.getTime());

				return sqlDate;

			} catch (ParseException e1) {
				//						e1.printStackTrace();
				parseDateContadorDeErros += 1;
				System.out.println("Data inválida!");
			}
		}while(parseDateContadorDeErros > 0);
		
		

		return null;
	}
	
	public Date leDate(String mensagem) {
		Integer parseDateContadorDeErros = 0;
		String dateFormat = "dd-MM-yyyy";
			
		do {
			System.out.println(mensagem + " (" + dateFormat + "): ");
			String stringDate = sc.nextLine();
			
			
			try {
				DateFormat formatter = new SimpleDateFormat(dateFormat);
				java.util.Date myDate = formatter.parse(stringDate);
				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				sqlDate = new java.sql.Date(myDate.getTime());

				return sqlDate;

			} catch (ParseException e1) {
				//						e1.printStackTrace();
				parseDateContadorDeErros += 1;
				System.out.println("Data inválida!");
			}
		}while(parseDateContadorDeErros > 0);
		
		

		return null;
	}
	
}
