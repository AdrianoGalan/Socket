package servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import model.Usuario;
import util.Criptografica;

public class Servidor {

	public static void main(String[] args) {
		
		ScriptEngineManager mgr = new ScriptEngineManager(null);

		ScriptEngine engine = mgr.getEngineByName("javascript");

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
			
			// recebe o usuario
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			usuario.setUsuario(msgIn);
			
			//recebe a senha
			msgIn = cpt.descriptografiaBase64Decode(in.readUTF());
			usuario.setSenha(msgIn);
			
			//recebe a equação
			equacao = cpt.descriptografiaBase64Decode(in.readUTF());
			
			//valida o usuario
			if(usuarios.contains(usuario)) {
				
				msgOut = "usuario valido";
				
			}else {
				msgOut = "usuario invalido";
			}
			
			// responde se usuario valido ou não 
			out.writeUTF(msgOut);
			out.flush();
			
			// envia chave
			msgOut = cpt.criptografiaBase64Encoder(UUID.randomUUID().toString());
			out.writeUTF(msgOut);
			out.flush();
			
			// envia resultado equação
			msgOut = cpt.criptografiaBase64Encoder(engine.eval(equacao).toString());
			out.writeUTF(msgOut);
			out.flush();
			
		
			
		
			

			in.close();
			out.close();
			server.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
