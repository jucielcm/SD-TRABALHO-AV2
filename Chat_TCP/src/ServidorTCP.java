import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(64601);
			System.out.println("ServidorTCP Online!");
			
			while(true) {
				Socket cliente = servidor.accept();
				new GerenciadorDeCliente(cliente);
			}
		}catch(IOException e) {
			try {
				if(servidor != null) {
					servidor.close();
				}
			}catch(IOException err) {
				
			}
			System.err.println("Erro");
			e.printStackTrace();
		}
	}
}