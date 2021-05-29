package br.com.poli.lpooMemoryGame.jogo;

public class CoordenadaException extends Exception{
	
	private static final long serialVersionUID = -3157131723541031993L;

	public CoordenadaException(){
		super();
	}
	
	@Override 
	public String getMessage() {
		return "Coordenada repetida, jogar de novo!";
	}
}
