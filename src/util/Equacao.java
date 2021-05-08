package util;

import bsh.EvalError;
import bsh.Interpreter;

public class Equacao {
	
	Interpreter bsh = new Interpreter();
	
	public int resultado(String equacao) throws EvalError {
		
		bsh.eval("Resultado = "+ equacao);
		
		int res = (int) bsh.get("Resultado");
		
		return res;
	}

}
