import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class Controle extends UnicastRemoteObject implements Interface {
	private static Interface instance = null;
	private static final long serialVersionUID = 1L;
	private List<Product> produtos;
	
	public Controle() throws RemoteException {
		this.produtos = new ArrayList<Product>();
    }
	@Override
	public boolean cadastroProduto(int id, String nome, float valor) throws RemoteException{
		for (Product p : produtos) {
			if(p.getId() == id) {
				return false;
			}
		}
		Product novoProduto = new Product(id, nome, valor);
		produtos.add(novoProduto);
		return true;
	}
	@Override
	public String pesquisaInfo(int id) throws RemoteException {
		String saida ="";
		for (Product p : produtos) {
			if(p.getId() == id) {
				saida= "Id:"+ p.getId() +"\n Nome: "+ p.getNome()+"\nValor: "+p.getValor();
				return saida;
			}
		}
		saida=  "Não encontrado!";
		return saida;
		
	}
	@Override
	public boolean pesquisa(int id) throws RemoteException{
		for(Product p : produtos) {
			if (p.getId()== id) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean removerProduto (int id) throws RemoteException{
		for (Product p : produtos) {
			if(p.getId() == id) {
				produtos.remove(p);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean atualizaProduto(int id, String nome, float valor) throws RemoteException {
		for (Product p : produtos) {
			if(p.getId()==id) {
				removerProduto(id);
				cadastroProduto(id, nome, valor);
				return true;
			}
		}
		return false;
	}
	
	public static Interface getInstance() throws RemoteException{
		if (instance == null) {
			instance = new Controle();
		}
		return instance;
	}
	
	
}