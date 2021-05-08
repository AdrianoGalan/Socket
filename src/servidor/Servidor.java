package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.Criptografica;

public class Servidor {

	public static void main(String[] args) {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		Criptografica cpt = new Criptografica();
		String msgIn , msgOut, equacao;

		for (int i = 0; i < 100; i++) {

			Usuario u = new Usuario("Usuario " + i, "Senha" + i);
			usuarios.add(u);

		}

		try {
			ServerSocket server = new ServerSocket(9001);
			Socket socket = server.accept();

			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			Usuario usuario = new Usuario();
			
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			usuario.setUsuario(msgIn);
			
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			usuario.setSenha(msgIn);
			
			equacao = cpt.descriptografiaBase64Decode(in.readUTF());
			
			
			if(usuarios.contains(usuario)) {
				
				msgOut = "usuario valido";
				
			}else {
				msgOut = "usuario invalido";
			}
			
			
			out.writeUTF(msgOut);
			out.flush();
			
			
			

		//	while (!msgIn.equals("parar")) {

		//		msgIn = in.readUTF();
		//		System.out.println("o Cliente falou " + msgIn);

		//		msgOut = entradaTeclado.readLine();
		//		out.writeUTF(msgOut);
				out.flush();

	//		}
			in.close();
			out.close();
			server.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
