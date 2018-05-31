import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorArquivo {
private static Scanner ler;

public static void main(String[] args) {
		
		String nomeArquivo = null;
		ler = new Scanner(System.in);
		
		System.out.println("Digite o nome da imagem: ");
		nomeArquivo = ler.nextLine();

		// Criando servidor
		ServidorArquivo server = new ServidorArquivo();

		// Conexao de cliente
		server.waitForClient(nomeArquivo);
		
	}

	public void waitForClient(String nomeArquivo) {
		// Checa se a transferencia foi completada com sucesso
		OutputStream socketOut = null;
		ServerSocket servsock = null;
		FileInputStream fileIn = null;

		try {
			// Abrindo porta para conexao de clientes
			servsock = new ServerSocket(1900);
			System.out.println("Conexao aberta");

			// Cliente conectado
			Socket sock = servsock.accept();
			System.out.println("Conexao recebida pelo cliente");

			byte[] cbuffer = new byte[138];
			int bytesRead;
			
			
			File file = new File("C:\\Users\\JucielMoura\\Desktop"+nomeArquivo);
			fileIn = new FileInputStream(file);
			System.out.println("Lendo arquivo...");
			
			socketOut = sock.getOutputStream();

			System.out.println("Enviando Arquivo...");
			while ((bytesRead = fileIn.read(cbuffer)) != -1) {
				socketOut.write(cbuffer, 3, bytesRead);
				socketOut.flush();
			}

			System.out.println("Arquivo Enviado!");
		} catch (Exception e) {
			System.err.println("Arquivo não encontrado!!!");
			System.exit(0);
			// Mostra erro no console
			e.printStackTrace();
		} finally {
			if (socketOut != null) {
				try {
					socketOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (servsock != null) {
				try {
					servsock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
