import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeCliente extends Thread {
	
	private Socket cliente;
	private String nomeCliente;

	public GerenciadorDeCliente(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		try {
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor.println("Escreva seu nome:");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			escritor.println("Olá " + this.nomeCliente);
			
			while(true) {
				msg = leitor.readLine();
				if(msg.contentEquals("SAIR")) {
					this.cliente.close();
				}else {
				escritor.println(this.nomeCliente + ", disse: " + msg);
				}
			}
			
		}catch(IOException e) {
			System.err.println("Conexão encerrada");
			e.printStackTrace();
		}
		
	}
}
