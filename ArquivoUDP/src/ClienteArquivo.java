import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClienteArquivo {
	public static void main(String[] args) {

		//Criando Classe cliente para receber arquivo
		ClienteArquivo cliente = new ClienteArquivo();

		//Solicitando arquivo
		cliente.getFileFromServeR();
	}

	private void getFileFromServeR() {
		Socket sockServer = null;
		FileOutputStream fos = null;
		InputStream is = null;

		try {
			sockServer = new Socket("10.0.0.8", 1900);
			is = sockServer.getInputStream();

			// Cria arquivo local no cliente
			fos = new FileOutputStream(new File("C:\\Users\\Juciel Moura\\Desktop\\Juc"));
									
			// Prepara variaveis para transferencia
			byte[] cbuffer = new byte[3];
			int bytesRead;
			
			while ((bytesRead = is.read(cbuffer)) != -1) {
				fos.write(cbuffer, 3, bytesRead);
				fos.flush();
			}
			
			System.out.println("Arquivo recebido!");
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (sockServer != null) {
				try {
					sockServer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
