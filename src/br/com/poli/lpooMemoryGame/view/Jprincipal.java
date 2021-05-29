package br.com.poli.lpooMemoryGame.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jprincipal extends JFrame {

	private static final long serialVersionUID = -446234343327332204L;
	private JPanel Pane;


	public Jprincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 450, 300);
		
		Pane = new JPanel();
		Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Pane);
		Pane.setLayout(null);
		
		JLabel labelMenu = new JLabel("Menu modo");
		labelMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelMenu.setBounds(146, 11, 115, 32);
		Pane.add(labelMenu);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				//janela escolher modo de  jogo
				MododeJogo modoDeJogo = new MododeJogo();
				modoDeJogo.setVisible(true);
				//janela primeira some
			}
		});
		btnJogar.setBounds(58, 57, 89, 23);
		Pane.add(btnJogar);
		
		JButton btnSair = new JButton("sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(248, 54, 89, 23);
		Pane.add(btnSair);
		
	}
}
