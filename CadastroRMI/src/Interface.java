import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote{

	boolean cadastroProduto(int id, String nome, float valor) throws RemoteException;

	String pesquisaInfo(int id) throws RemoteException;

	boolean removerProduto(int id) throws RemoteException;

	boolean atualizaProduto(int id, String nome, float valor) throws RemoteException;

	boolean pesquisa(int id) throws RemoteException;		
}
