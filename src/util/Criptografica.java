package util;

import java.util.Base64;

public class Criptografica {

	public String criptografiaBase64Encoder(String pValor) {
		return new String(Base64.getEncoder().encode(pValor.getBytes()));
	}

	public String descriptografiaBase64Decode(String pValor) {
		return new String(Base64.getDecoder().decode(pValor.getBytes()));
	}

}