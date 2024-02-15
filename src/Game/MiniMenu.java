package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiniMenu extends JPanel{
	
	private Tela tela;
	private JLabel contBombs;
	private JLabel timeLabel;
	private int time = 0;
	
	public MiniMenu(Tela tela) {
        this.tela = tela;
        setPreferredSize(new Dimension(0, 60));
        setBackground(Color.LIGHT_GRAY);
        
        contBombs = new JLabel("Flags: " + String.valueOf(tela.getContBombs()));
        timeLabel = new JLabel("Tempo: 0");

        contBombs.setFont(new Font(contBombs.getFont().getName(), Font.PLAIN, 20));
        timeLabel.setFont(new Font(timeLabel.getFont().getName(), Font.PLAIN, 20));
      
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(contBombs, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(timeLabel, gbc);
    }
	
	public void update() {
		contBombs.setText("Flags: " + String.valueOf(tela.getContBombs()));
		timeLabel.setText("Tempo: " + String.valueOf(time));
	}
	
	public void upTime() {
		time++;
	}
	
}
