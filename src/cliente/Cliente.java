package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import util.Criptografica;

public class Cliente {

	public static void main(String[] args) {
		
		Criptografica cpt = new Criptografica();
		String msgOut, msgIn, equacao;
		
		try {
			Socket socket = new Socket("localhost", 9001);
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			// envia usuario
		    System.out.println("Digite o usuario:");
			msgOut  = teclado.readLine();
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			//envia senha
			System.out.println("Digite a senha:");
			msgOut = teclado.readLine();
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			//envia equacao
			System.out.println("Digite uma equação");
			msgOut = teclado.readLine();
			equacao = msgOut;
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			//recebe validação usuario
			msgIn = in.readUTF();
			System.out.println(msgIn);
			
			//Recebe chave
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			System.out.println("chave "+msgIn);
			
			
			//recebe resultado equacao
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			System.out.println(equacao + " = " + msgIn);
		
			in.close();
			out.close();
			socket.close();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
