package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import util.Criptografica;

public class Cliente {

	public static void main(String[] args) {
		
		Criptografica cpt = new Criptografica();
		String msgOut, msgIn;
		
		try {
			Socket socket = new Socket("localhost", 9001);
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());


			msgOut = JOptionPane.showInputDialog(null, "Digite um usuario: ");
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			msgOut = JOptionPane.showInputDialog(null, "Digite a Senha: ");
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			msgOut = JOptionPane.showInputDialog(null, "Digite uma equação ");
			msgOut = cpt.criptografiaBase64Encoder(msgOut);
			out.writeUTF(msgOut);
			out.flush();
			
			msgIn = in.readUTF();
			System.out.println(msgIn);
			
			
		
			in.close();
			out.close();
			socket.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
