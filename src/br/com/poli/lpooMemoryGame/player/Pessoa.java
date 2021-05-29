package br.com.poli.lpooMemoryGame.player;

public class Pessoa {
	
	//atributo
	protected String nome;
	
	//construtor
	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}
	
	//get e set
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
