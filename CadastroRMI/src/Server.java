import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


	public class Server {

		public Server() throws RemoteException {
	        try {
	           LocateRegistry.createRegistry(1900);
	           Naming.rebind("rmi://127.0.0.1:1900/Server", new Controle());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static void main(String[] args) throws RemoteException {
	       new Server();
	    }
		
	}