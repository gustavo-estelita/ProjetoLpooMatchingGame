package br.com.poli.lpooMemoryGame.jogo;

import java.util.Random;
import java.util.Scanner;

import br.com.poli.lpooMemoryGame.player.Jogador;

public class Partida extends Tabuleiro {

	// Atributos
	private String Jogador1;
	private String Jogador2;

	private String g1 = null, g2 = null;

	private Jogador p1;
	private Jogador p2;

	protected static boolean continuaJogada = true;

	private int checkPlayer = 0;

	private int scoreP1 = 1;
	private int scoreP2 = 1;
	private int scoreContP1;
	private int scoreContP2;

	private int g1x, g1y, g2x, g2y = 0;

	private static int modoDeJogo;

	private String[] possibilidades = null;

	private String resultadoFinal = "";

	public int noDownCards = getDificuldade() * getDificuldade();

	// SCANNER
	Scanner sc = new Scanner(System.in);

	//RANDOM
	Random rand = new Random();


	// CONSTRUTOR
	public Partida(Jogador p1, Jogador p2, int modoDeJogo) throws CoordenadaException {
		super(dificuldade);
		Partida.modoDeJogo = modoDeJogo;
		this.p1 = p1;
		this.p2 = p2;
		fazerJogada(getUpDown(), getCards()); //inicia todo o jogo
	}


	// METODOS

	public void nomesJogadores() { // Indicar quem eh o Jogador1 e o Jogador2
		p1 = new Jogador(Jogador1,1);
		p2 = new Jogador(Jogador2,2);
	}

	public void coordAtribuir(boolean[][] upDown, String[][] cards2, String g1,String g2) throws CoordenadaException{
		//substrings pra pegar o primeiro valor, primeiro caracter e atribuir como valor parametro de cards 2(usado no tabuleiro)
		//ex 11 -> vai pegar a posicao 00, devido ao -1
		g1x = Integer.valueOf(g1.substring(0, 1))-1;
		g1y = Integer.valueOf(g1.substring(1, 2))-1;
		//printa a carta da posicao escolhida
		System.out.println(cards2[g1x][g1y]);

		g2x = Integer.valueOf(g2.substring(0, 1))-1;
		g2y = Integer.valueOf(g2.substring(1, 2))-1;
		//printa a carta da posicao escolhida
		System.out.println(cards2[g2x][g2y]);
		
		if(g1x == g2x && g1y == g2y) {
            throw new CoordenadaException(); // Proibe o jogador de escolher a mesma posicao
        }
	}


	public void pedirCoordPlayer(boolean[][] upDown, String[][] cards2) {
		//possibilidades serve para pegar a coordenada escolhida pela cpu
		if(dificuldade == 4) {
			possibilidades = new String[] {"11","12","13","14","21","22","23","24","31","32","33","34","41","42","43","44"};
		}else if(dificuldade == 6) {
			possibilidades = new String[] {"11","12","13","14","15","16","21","22","23","24","25","26","31","32","33","34","35","36"
					,"41","42","43","44","45","46","51","52","53","54","55","56","61","62","63","64","65","66"};
		}else if(dificuldade == 8) {
			possibilidades = new String[] {"11","12","13","14","15","16","17","18","21","22","23","24","25","26","27","28",
					"31","32","33","34","35","36","37","38","41","42","43","44","45","46","47","48","51","52","53","54","55","56","57","58",
					"61","62","63","64","65","66","67","68","71","72","73","74","75","76","77","78","81","82","83","84","85","86","87","88"};
		}
		
		if(getCheckPlayer()== 1) { //jogada jogador 1
			System.out.printf(p1.getNome() + ", Digite a coordenada 1: ");
			g1 = sc.next();
			System.out.printf(p1.getNome() + ", Digite a coordenada 2: ");
			g2 = sc.next();
			
		}else if(getCheckPlayer()== 2) { //jogada jogador 2
			if(getModoDeJogo() == 1) { //caso seja player vs player
				System.out.printf(p2.getNome() + ", Digite a coordenada 1: ");
				g1 = sc.next();
				System.out.printf(p2.getNome() + ", Digite a coordenada 2: ");
				g2 = sc.next();
			}else if(getModoDeJogo() ==2) { //caso seja player vs cpu
				//CPU escolhendo as coordenadas
				g1 = (possibilidades[new Random().nextInt(possibilidades.length)]);
				System.out.println(p2.getNome()+" escolheu a coordenada 1: " + g1);

				g2 = (possibilidades[new Random().nextInt(possibilidades.length)]);
				System.out.println(p2.getNome()+" escolheu a coordenada 2: " + g2);
			}
		}

	}

	public void fazerJogada(boolean[][] upDown, String[][] cards2){ 
		
		// while eh pra ser usado até que todas as cartas estejam reveladas
		while(noDownCards > 0) {
			// while para fazer a jogada do jogador 1
			while(continuaJogada == true) {

				setCheckPlayer(1); // Definir qual o player da vez
				verificarJogada(upDown, cards2);
				//passa a ser false no metodo veriricarAcerto, onde passa para o proximo jogador caso o player1 nao acerte coordenada -> passa a ser false
			}
			// while para fazer a jogada do jogador 2
			while(continuaJogada == false) {

				setCheckPlayer(2); // Definir qual o player da vez
				verificarJogada(upDown, cards2);
				//mesmo vale para o player2, mediante atributo continuaJogada -> passa a ser true caso o player2 erre
			}
		}
	}

	public String verificarVencedor(boolean upDown[][], String cards2[][]) {
		tabuleiro(upDown, cards2);
		//resultadoFinal = "";

		if(noDownCards == 0) { //condicao ocorre no momento em que todas as cartas sao viradas
			if(scoreContP1 > scoreContP2) { // Caso o jogador 1 tenha feito mais pontos
				resultadoFinal = "\nFim de Jogo! \n\nO jogador 1 fez:  "+ scoreContP1 + " pontos! \nO jogador 2 fez:  "  + scoreContP2 + " pontos! \nParabéns " + p1.getNome() + "! Você venceu!!" ;
			}
			else if(scoreContP2 > scoreContP1) { // Caso o jogador 2 tenha feito mais pontos
				resultadoFinal = "\nFim de Jogo! \n\nO jogador 1 fez:  "+ scoreContP1 + " pontos! \nO jogador 2 fez:  "  + scoreContP2 + " pontos! \nParabéns " + p2.getNome() + "! Você venceu!!" ;
			}
			else if(scoreContP1 == scoreContP2) { // Em caso de empate
				resultadoFinal = "\nFim de Jogo! \n\nO jogador 1 fez:  "+ scoreContP1 + " pontos! \nO jogador 2 fez:  " +scoreContP2+" pontos! \nO jogo terminou empatado!";
			}
		}
		return resultadoFinal; //mostrar o resultado final do jogo
	}

	public int verificarAcerto(boolean upDown[][], String cards2[][]) {
		//&& upDown[g1x][g1y] != true && upDown[g2x][g2y] != true
		int checkTroca = 0;
		
		//os dois primeiros verificam pra saber se as cartas ja foram reveladas
		if(getUpDown()[g1x][g1y] == true && getUpDown()[g2x][g2y] == true && getCheckPlayer() == 1) { // jogador 1 continua jogando caso tente realizar uma jogada já revelada
            checkTroca = 1;
        } else if(getUpDown()[g1x][g1y] == true && getUpDown()[g2x][g2y] == true && getCheckPlayer() == 2) { // jogador 2 continua jogando caso tente realizar uma jogada já revelada
            checkTroca = 2;
        }else if(cards2[g1x][g1y] == cards2[g2x][g2y] ) { 
			if(getCheckPlayer() == 1) {
				checkTroca = 1;
				System.out.println(p1.getNome()+ ", voce revelou um match");
				//incrementando o score do player 1
				scoreContP1 = scoreP1++;
				upDown[g1x][g1y] = true;
				upDown[g2x][g2y] = true;
				//decrementa o numero de cartas viradas
				noDownCards -= 2;
				System.out.println(p1.getNome()+" o seu score agora eh: " + scoreContP1);
				System.out.println();

			} else if(getCheckPlayer() == 2) {
				checkTroca = 2;
				System.out.println(p2.getNome()+ ", voce revelou um match");
				//incrementando o score do player 2
				scoreContP2 = scoreP2++;
				upDown[g1x][g1y] = true;
				upDown[g2x][g2y] = true;
				noDownCards -= 2;

				System.out.println(p2.getNome()+" o seu score agora eh: " + scoreContP2);
				System.out.println();	
			}
		}
		return checkTroca;
	}


	public void verificarJogada(boolean upDown[][], String cards2[][]) {
		if(getCheckPlayer() == 1) { //sabe-se que e a vez do jogador 1
			while(continuaJogada == true) {
				Tabuleiro.tabuleiro(upDown, cards2);

				pedirCoordPlayer(upDown, cards2); //pede a coordenada do jogador mediante o checkPlayer

				if(verificarException(upDown, cards2, g1, g2) == true) { //continua o loop(jogador pode tentar a jogada novamente) caso haja erro em sua jogada
					continue;
				}

				if(verificarAcerto(upDown, cards2) == 1)
					continuaJogada = true;
				else
					continuaJogada = false; // Troca o player, pois nao achou uma combinacao
			}

			if(noDownCards == 0) { //caso acabe o jogo
				System.out.println(verificarVencedor(upDown, cards2));
				System.exit(1); //encerra o programa caso todas as cartas estejam reveladas
			}


		} else if(getCheckPlayer() == 2) { //sabe-se que e a vez do jogador 2


			while(continuaJogada == false) {
				Tabuleiro.tabuleiro(upDown, cards2);

				pedirCoordPlayer(upDown, cards2); 

				if(verificarException(upDown, cards2, g1, g2) == true) {
					continue;
				}

				if(verificarAcerto(upDown, cards2) == 2)
					continuaJogada = false;
				else
					continuaJogada = true; // Troca o player, pois nao achou uma combinacao	

				if(noDownCards == 0) {
					System.out.println(verificarVencedor(upDown, cards2));
					System.exit(1); //encerra o programa caso todas as cartas estejam reveladas
				}
			}
		}
	}

	public boolean verificarException(boolean[][] upDown, String[][] cards2, String g1, String g2) {

		boolean erro = false;
		//try para pegar erro caso a atribuicao falhe
		try {
			coordAtribuir(upDown, cards2, g1, g2);

			if(g1.equals(g2)) {
				throw new CoordenadaException(); // Proibe o jogador de escolher a mesma posicao
			}
		}
		catch(CoordenadaException c) {
			System.out.println("Coordenada repetida, tente novamente!\n EXCEPTION COORDENADA REPETIDA");
			erro = true;
		}
		catch(ArrayIndexOutOfBoundsException e){ //EXCEPTION VALOR MAIOR Q ARRAY
			System.out.println("Coordenada excede limites do tabuleiro, tente sua jogada novamente!\n EXCEPTION VALOR MAIOR Q ARRAY");
			erro = true;
		}
		catch(NumberFormatException a) {
			System.out.println("Voce digitou uma coordenada invalida, tente novamente!\n EXCEPTION STRING NA COORDENADA");
			erro = true;
		}
		catch(StringIndexOutOfBoundsException e) {
            System.out.println("Voce digitou uma coordenada invalida, tente novamente! EXCEPTION COORDENADA ERRADA");
            erro = true;
        }
		return erro; //retorna o boolean que e usado para continuar o looping while no metodo verificarJogada
	}


	// Getters e Setters
	public String getJogador1() {
		return Jogador1;
	}
	public void setJogador1(String jogador1) {
		Jogador1 = jogador1;
	}

	public String getJogador2() {
		return Jogador2;
	}
	public void setJogador2(String jogador2) {
		Jogador2 = jogador2;
	}

	public int getScoreP1() {
		return scoreP1;
	}
	public void setScoreP1(int scoreP1) {
		this.scoreP1 = scoreP1;
	}

	public int getScoreP2() {
		return scoreP2;
	}
	public void setScoreP2(int scoreP2) {
		this.scoreP2 = scoreP2;
	}

	public int getScoreContP1() {
		return scoreContP1;
	}
	public void setScoreContP1(int scoreContP1) {
		this.scoreContP1 = scoreContP1;
	}

	public int getScoreContP2() {
		return scoreContP2;
	}
	public void setScoreContP2(int scoreContP2) {
		this.scoreContP2 = scoreContP2;
	}

	public static int getModoDeJogo() {
		return modoDeJogo;
	}
	public static void setModoDeJogo(int modoDeJogo) {
		Partida.modoDeJogo = modoDeJogo;
	}

	public int getCheckPlayer() {
		return checkPlayer;
	}
	public void setCheckPlayer(int checkPlayer) {
		this.checkPlayer = checkPlayer;
	}
}