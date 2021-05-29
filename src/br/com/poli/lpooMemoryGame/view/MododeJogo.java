package br.com.poli.lpooMemoryGame.view;

import br.com.poli.lpooMemoryGame.jogo.CoordenadaException;
import br.com.poli.lpooMemoryGame.jogo.Partida;
import br.com.poli.lpooMemoryGame.jogo.Tabuleiro;
import br.com.poli.lpooMemoryGame.player.Jogador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MododeJogo extends JFrame {

	private static final long serialVersionUID = 5798016191783487569L;
	private JPanel contentPane;

	//metodo de classe, nenhuma outra classe precisa ter acesso
	private int auxiliarTabParametro() {
		
		int dificuldadeAuxi = 0;
		 while(true) {
			 String dificuldadeString = JOptionPane.showInputDialog("facil = 1 | medio = 2| dificil = 3 \nInsira o modo de jogo: ");
			 try {
				 dificuldadeAuxi = Integer.parseInt(dificuldadeString);
				 
				 if(dificuldadeAuxi == 1 ) {
					 dificuldadeAuxi = 4; //facil
						break;
					}else if(dificuldadeAuxi == 2 ) {
						dificuldadeAuxi = 6; //medio
						break;
					}else if(dificuldadeAuxi == 3 ) {
						dificuldadeAuxi = 8; //dificil
						break;
					}
					else { //caso nao se digite nada
						JOptionPane.showConfirmDialog(null,"Modo de jogo invalido, tente novamente");
					}
			 }catch(java.lang.NumberFormatException e) { //caso seja digitado uma letra
				 JOptionPane.showConfirmDialog(null,"Modo de jogo invalido, tente novamente");
			 }
		 }
		 return dificuldadeAuxi; //usado como retorno para inciar o nivel do jogo(dificuldade do tabuleiro)
	}
	
	public MododeJogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escolha  modo de jogo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(138, 11, 162, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnPvP = new JButton("player vs player");
		btnPvP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				String nome1 = JOptionPane.showInputDialog("Informe o nome do jogador 1: ");
				String nome2 = JOptionPane.showInputDialog("Informe o nome do jogador 2: ");
				
				Jogador player1 = new Jogador(nome1,1);
				Jogador player2 = new Jogador(nome2,2);
				
				new Tabuleiro(auxiliarTabParametro());
				try {
					new Partida(player1, player2, 1); //comeca de fato o jogo
				}catch(CoordenadaException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPvP.setBounds(135, 71, 165, 23);
		contentPane.add(btnPvP);
		
		JButton btnPvCpu = new JButton("player vs cpu");
		btnPvCpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String nome = JOptionPane.showInputDialog("Informe o nome do jogador 1: ");
				
				Jogador player1 = new Jogador(nome,1);
				Jogador player2 = new Jogador("CPU",2);
				
				new Tabuleiro(auxiliarTabParametro()); //tabuleiro com sua dificuldade escolhida
				try {
					new Partida(player1, player2, 2); //comeca de fato o jogo //modo 2
				} catch (CoordenadaException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnPvCpu.setBounds(138, 105, 162, 23);
		contentPane.add(btnPvCpu);
		
		JButton btnNewButton_2 = new JButton("Voltar  menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Jprincipal janela = new Jprincipal();
				janela.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(151, 202, 120, 23);
		contentPane.add(btnNewButton_2);
	}

}
