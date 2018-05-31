import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class Cliente {
	private static Socket cliente;

	public static void main(String[] args) {
		try {
			cliente = new Socket("10.0.0.8", 64601);
			
			//lendo mensagem do servidor
			new Thread() {
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println("ServidorTCP: " + mensagem);
						}
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}.start();
			
			//escrevendo para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			
			String mensagemTerminal = "";
			while( (mensagemTerminal = leitorTerminal.readLine()) != null ) {
				escritor.println(mensagemTerminal);
				
				if(mensagemTerminal.equalsIgnoreCase("SAIR")) {
					System.exit(0);
				}
			}
			
		}catch(UnknownException e) {
			System.out.println("O endereço passado é inválido!");
			e.printStackTrace();
		}catch(IOException err) {
			System.out.println("O servidor não encontrado");
			err.printStackTrace();
		}
	}
}
