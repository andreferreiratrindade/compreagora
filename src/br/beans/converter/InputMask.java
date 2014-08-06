package br.beans.converter;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class InputMask {

	private InputMask inputMask;
	
	private InputMask(){
		
	}
	
	public InputMask getInstace(){
		if(inputMask == null){
			inputMask = new InputMask();
		}
		return inputMask;
	}
	
	public static final String CNPJ = "##.###.###/####-##";
	public static final String TELEFONE = "(##) ####-####";
	public static final String CEP = "#####-###";

	public static String stringToMask(String mask, String value) {

		MaskFormatter formatter = null;
		String st = "";
		try {
			formatter = new MaskFormatter(mask);
			formatter.setValueContainsLiteralCharacters(false);
			st = formatter.valueToString(value);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;
	}
}
