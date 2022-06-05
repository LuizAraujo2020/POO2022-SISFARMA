package br.ucb.poo.controles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;

public class Leitora {
	private static Leitora referenceToSingleInputObject = null;
	private static Scanner scannerKeyboard;
	
	private Leitora() { scannerKeyboard = new Scanner(System.in); }
	
	public static Leitora getInstance() {
		if (referenceToSingleInputObject == null)
			referenceToSingleInputObject = new Leitora();
		return referenceToSingleInputObject;
	}
	public Integer leInteiro(String mensagem) {
//
////		Integer resultado = sc.nextInt();
//		Integer resultado = Input.getInstance().getInt("");
//		
//		return resultado;

		System.out.print(mensagem);
		while ( ! scannerKeyboard.hasNextInt()) { // peek into keyboard buffer to see if next token is a legitimate int
			System.out.println("Number is required input.");
			System.out.print(mensagem);
			scannerKeyboard.nextLine(); // clear bad input data from the keyboard
		}
		return scannerKeyboard.nextInt(); 
	}
	
//	
//	public static Integer leInteiro(String mensagem) {
//		
//		System.out.println(mensagem);
//		
////		Integer resultado = sc.nextInt();
//		Integer resultado = Input.getInstance().getInt(mensagem);
//		
//		return resultado;
//	}
	
	
//	
//	public String leTexto() {
////		String resultado = "";
////
//////		resultado = sc.nextLine().toString();
////		resultado = Input.getInstance().getString("");
////
////		return resultado;
//		scannerKeyboard.useDelimiter("\r\n"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
//		String sInput = scannerKeyboard.next();
//		scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
//		return sInput;
//	}
	
	public String leTexto(String mensagem) {
//		System.out.println(mensagem);
//		
//
//		String resultado = "";
//
//		resultado = Input.getInstance().getString(mensagem);
////		resultado = sc.nextLine().toString();		
//	
//		
//		return resultado;
		
		System.out.print(mensagem);
//		scannerKeyboard.useDelimiter("\r\n"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
		String sInput = scannerKeyboard.nextLine();
		scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
		return sInput;
	}
	
//	public static Float leFloat() {
//		
////		Float resultado = sc.nextFloat();
//		Float resultado = Float.parseFloat(Input.getInstance().getString(""));
//		
//		return resultado;
//	}
	
	public Float leFloat(String mensagem) {
		
//		System.out.println(mensagem);
//
//		Float resultado = Float.parseFloat(Input.getInstance().getString(mensagem));
////		Float resultado = sc.nextFloat();
//		
//		return resultado;

		System.out.print(mensagem);
		while ( ! scannerKeyboard.hasNextFloat()) { // peek into keyboard buffer to see if next token is a legitimate int
			System.out.println("Number is required input.");
			System.out.print(mensagem);
			scannerKeyboard.nextLine(); // clear bad input data from the keyboard
		}
		return scannerKeyboard.nextFloat(); 
	}
	
//	public static Date leDate() {
//		
//		
//		Integer parseDateContadorDeErros = 0;
//		String dateFormat = "dd-MM-yyyy";
//			
//		do {
////			String stringDate = sc.nextLine();
//			String stringDate = Input.getInstance().getString("");
//			
//			
//			try {
//				DateFormat formatter = new SimpleDateFormat(dateFormat);
//				java.util.Date myDate = formatter.parse(stringDate);
//				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
//				sqlDate = new java.sql.Date(myDate.getTime());
//				
//				parseDateContadorDeErros = 0;
//				
//				return sqlDate;
//
//			} catch (ParseException e1) {
//				//						e1.printStackTrace();
//				parseDateContadorDeErros += 1;
//				System.out.println("Data inválida!");
//			}
//		}while(parseDateContadorDeErros > 0);
//
//		return null;
//	}
	
	public Date leDate(String mensagem) {
		
		Integer parseDateContadorDeErros = 0;
		String dateFormat = "dd-MM-yyyy";
			
		do {
//			System.out.println(mensagem + " (" + dateFormat + "): ");
//			String stringDate = Input.getInstance().getString(mensagem + " (" + dateFormat + "): ");
//			String stringDate = sc.nextLine();
			scannerKeyboard.useDelimiter("\r\n"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
			String stringDate = scannerKeyboard.next();
			scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
			
			
			
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
