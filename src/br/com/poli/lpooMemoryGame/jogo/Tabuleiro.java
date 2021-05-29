package br.com.poli.lpooMemoryGame.jogo;

import java.util.Random;

public class Tabuleiro {

	//Atributos
	private String[][] cards ;
	protected boolean upDown[][];
	protected static int dificuldade;
    

	//Construtor
	public Tabuleiro(int dificuldade) {
		Tabuleiro.dificuldade=dificuldade;
		this.setCards(new String[dificuldade][dificuldade]);
		this.setUpDown(new boolean[dificuldade][dificuldade]);
		setup(); // Iniciando o setup do jogo => Configurando o jogo
	}

	//Metodos
	public void setup() {
		for (int i = 0; i < getDificuldade(); i++) {
			for (int a = 0; a < getDificuldade(); a++) {
				getUpDown()[i][a] = false; //atribuindo o bool false para as pecas do tabuleiro, nao estao reveladas
			}
		}
		setCards(randomizar()); // embaralha cartas       
	}

	public static void tabuleiro(boolean[][] upDown, String[][] cards2) { // Printa o tabuleiro 
		
		String numColTabuleiro = null;
		if(getDificuldade() == 4) {
			numColTabuleiro = "     1 2 3 4 ";
		}else if(getDificuldade() == 6) {
			numColTabuleiro = "     1 2 3 4 5 6 ";
		}else if(getDificuldade() == 8) {
			numColTabuleiro = "     1 2 3 4 5 6 7 8 ";
		}
		
		System.out.println(numColTabuleiro);
		for (int i = 0; i < getDificuldade(); i++) { //percorre linhas
			//imprimindo a numeracao das linhas
			System.out.print(" " + (i + 1) + " | ");
			for (int a = 0; a < getDificuldade(); a++) { //percorre colunas
				if (upDown[i][a]) { 
					System.out.print(cards2[i][a]); //mostra as cartas que estavam escondidas 
					System.out.print(" ");
				}
				else
					System.out.print("* ");
					//System.out.print(cards2[i][a]); //-> usado caso queira-se mostrar o tabuleiro com todas as pecas reveladas no inicio do jogo
			}
			System.out.println();
		}

		System.out.println(); 
	}

	public String[][] randomizar() {

		String letras[] = null;

		//if's para dificuldade atrelada a quantidade de letras
		if(getDificuldade() == 4) { //dificuldade facil
			letras = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "A", "B", "C", "D", "E", "F", "G", "H"};
		}else if(getDificuldade() == 6) { //dificuldade media
			letras = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "O", "P", "Q", "R", "S" };
		}else if(getDificuldade() == 8) { //dificuldade dificil
			letras = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","!","@","#","$","&","%","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "@","#", "$", "&", "%" };
		}
		//quantidade de cartas sendo como o tamanho total do tabuleiro, ex: 4*4 = 16 cartas
		String[][] cards = new String[getDificuldade()][getDificuldade()];
		Random random = new Random();
		String temp;
		int t;

		for (int j = 0; j <= (getDificuldade()*getDificuldade())+getDificuldade(); j++) {
			for (int x = 0; x < getDificuldade()*getDificuldade(); x++) { // Randomiza a ordem das cartas
				//pegar letras maiusculas da tabela ASCII
				t = random.nextInt(1000) % 15;
				//estrutura para passar valor de uma variavel para outra
				temp = letras[x];
				letras[x] = letras[t];
				letras[t] = temp;

			}
			t = 0;
			for (int r = 0; r < getDificuldade(); r++) // Cartas sao atribuidas as letras
			{
				for (int s = 0; s < getDificuldade(); s++) {
					cards[r][s] = letras[t]; //-> aqui
					t = t + 1;
				}
			}
		}
		return cards;

	}

	// Embaralha as cartas
	public  int[][] embaralha() {

		int start[] = null;

		if(getDificuldade() == 4) { //dificuldade facil
			start = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
		}else if(getDificuldade() == 6) { //dificuldade media
			start = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
		}else if(getDificuldade() == 8) { //dificuldade dificil
			start = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
		} 
		
		//numero de linhas e colunas de acordo com a dificuldade
		int cards[][] = new int[getDificuldade()][getDificuldade()];
		Random ran = new Random();
		
		int tmp, i;
		
		for (int s = 0; s <= (getDificuldade()*getDificuldade())+getDificuldade(); s++) {
			for (int x = 0; x < getDificuldade()*getDificuldade(); x++) //randomiza a posicao das cartas
			{
				i = ran.nextInt(100000) % 15;
				tmp = start[x];
				start[x] = start[i];
				start[i] = tmp;
			}
		}
		i = 0;

		for (int r = 0; r < getDificuldade(); r++) // poe valores nas cartas aqui
		{
			for (int c = 0; c < getDificuldade(); c++) {
				cards[r][c] = start[i];
				i = i + 1;
			}
		}
		return cards;

	}

	//get e set
	public static int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		Tabuleiro.dificuldade = dificuldade;
	}

	public String[][] getCards() {
		return cards;
	}

	public void setCards(String[][] cards) {
		this.cards = cards;
	}

	public boolean[][] getUpDown() {
		return upDown;
	}

	public void setUpDown(boolean upDown[][]) {
		this.upDown = upDown;
	}
	
	





}
