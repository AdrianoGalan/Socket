package teste;

import bsh.EvalError;
import bsh.Interpreter;
import util.Equacao;

public class Teste {

	public static void main(String[] args) {
		
	
		System.out.println("então ");
		
		Equacao  e = new Equacao();
		
		try {
			System.out.println(e.resultado("3 + 3"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
