package br.com.poli.lpooMemoryGame.player;

public class Jogador extends Pessoa {

	// ATRIBUTOS

	//atributos para identificar se é o jogador 1 ou jogador 2
	//começando pelo player 1
	private int identJogador;

	//atributo score(verificar a exata funcionalidade deste)
	private int score;

	// CONSTRUTOR
	public Jogador(String nome, int identJogador) {
		super(nome);
		this.identJogador = identJogador;
		//score nao vai ser efetivamente utilizado neste momento
		this.score = 0;
	}


	//GETTERS E SETTERS
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getIdentJogador() {
		return identJogador;
	}
	public void setIdentJogador(int identJogador) {
		this.identJogador = identJogador;
	}

	//adiconar toString, Adicionando o número dele concatenando com a chamada do toString da classe. a saída do metodo deve ser:
	//"1- O nome do jogador eh "+nome de acordo com os atributos da classe pessoa e da classe jogador
	
	
	@Override
	public String toString() {
		return "" + getIdentJogador()+"-"+" O nome do jogador " + getIdentJogador() + " eh " + getNome();
		
	}
}
