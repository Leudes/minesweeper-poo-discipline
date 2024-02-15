package Game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu{
	private JFrame janela;
	private ArrayList<JButton> botoes;
	private Game game;
	
	public Menu() throws IOException {
		game = new Game(this);
		janela = new JFrame();
		initJanela();
		botoes = new ArrayList<JButton>();
		GridBagConstraints grid = new GridBagConstraints();
		for(int i = 0; i < 4; i++) {
			botoes.add(new JButton());
			grid.gridy = i+1;
			botoes.get(i).setPreferredSize(new Dimension(120, 30));
			janela.add(botoes.get(i), grid);
		}
		nameButtons();
		addListenerButtons();
	}
	
	private void initJanela() {
		janela.setLayout(new GridBagLayout());
		janela.setTitle("Campo Minado");
		janela.setSize(400, 400);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
	
	private void nameButtons() {
		botoes.get(0).setText("Iniciante");
		botoes.get(1).setText("Facil");
		botoes.get(2).setText("Intermediario");
		botoes.get(3).setText("Perito");
	}
	
	private void addListenerButtons() {
		botoes.get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.startTela(10, 8, 40, 7, "Iniciante");
				janela.setVisible(false);
			}
		});
		botoes.get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.startTela(14, 9, 35, 15, "Fácil");
				janela.setVisible(false);
			}
		});
		botoes.get(2).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.startTela(20, 15, 30, 40, "Intermediário");
				janela.setVisible(false);
			}
		});
		botoes.get(3).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.startTela(26, 19, 30, 99, "Perito");
				janela.setVisible(false);
			}
		});
	}
	
	public JFrame getJanela() {
		return janela;
	}
}
