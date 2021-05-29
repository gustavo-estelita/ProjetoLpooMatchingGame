package br.com.poli.lpooMemoryGame.jogo;

public class TabuleiroMaluco extends Tabuleiro{

	public TabuleiroMaluco(int dificuldade) {
		super(dificuldade);
		cadaJogada(getUpDown(), getCards());
	}
	
	public void cadaJogada(boolean upDown[][], String cards2[][]) {
			
		randomizar();
		embaralha();
		
	}
}
//loop no jframe na hora de chamar o tabuleiro