public class Product {
	public int id;
	public String nome;
	public float valor;
	
	public Product(int id, String nome, float valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}