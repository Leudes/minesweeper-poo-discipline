
package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Inputs.MouseInput;
import Mapa.BlocoHandler;

import java.util.ArrayList;

public class Tela extends JPanel{
    private int colunas;
    private int linhas;
    private int tileSize;
    private int[][] campoEx;
    private int[][] campoIn;
    private boolean lose, win;
    private int contBombs;
    private int contBlocos;
    private int bombs;
    private boolean start = false;
    private String titulo;
    private MouseInput mouH;
    private BlocoHandler blocoH;
    private Random r;
    
    public Tela(int linhas, int colunas, int tileSize, int bombs, String titulo) throws IOException{
    	this.titulo = titulo;
    	this.contBombs = bombs;
    	this.bombs = bombs;
    	lose = false;
    	win = false;
    	contBlocos = linhas*colunas;
    	this.linhas = linhas;
    	this.colunas = colunas;
    	this.tileSize = tileSize;
    	campoEx = new int[linhas][colunas];
    	campoIn = new int[linhas][colunas];
    	r = new Random();
    	initCampoEx();
    	initCampoIn();
    	initBombas(bombs);
    	initNumbers();
    	blocoH = new BlocoHandler();
        mouH = new MouseInput(this);
        setDoubleBuffered(true);
        setBackground(Color.black);
        setPreferredSize(new Dimension(linhas*tileSize, colunas*tileSize));
        addMouseListener(mouH);
        setFocusable(true);
    }
    
    private void initCampoEx() {
    	for(int i = 0; i < linhas; i++) {
    		for(int j = 0; j < colunas; j++) {
    			campoEx[i][j] = 12;
        	}
    	}
    }
    
    private void initCampoIn() {
    	for(int i = 0; i < linhas; i++) {
    		for(int j = 0; j < colunas; j++) {
    			campoIn[i][j] = 0;
        	}
    	}
    }
    
    private void initBombas(int bombas) {
    	int i = 0;
    	while (i < bombas) {
    		int lin = r.nextInt(linhas);
    		int col = r.nextInt(colunas);
    		if(campoIn[lin][col] != 10) {
    			i++;
    		}
    		campoIn[lin][col] = 10;
    	}
    }
    
    private void initNumbers() {
    	for(int i = 0; i < linhas; i++) {
    		for(int j = 0; j < colunas; j++) {
    			if(campoIn[i][j] != 10) {
    				ArrayList<xy> array = new ArrayList<xy>();
    				getVizinhos(i, j, array);
    				for(int k = 0; k < array.size(); k++) {
    					if(campoIn[array.get(k).x][array.get(k).y] == 10) {
    						campoIn[i][j] += 1;
    					}
    				}
    			}
        	}
    	}
    }
    
    private void getVizinhos(int lin, int col, ArrayList<xy> array) {
    	for(int i=-1 ; i<=1 ; i++) {
    		for(int j=-1 ; j<=1 ; j++) {
    			if(lin + i < linhas && lin + i >= 0 && col + j < colunas && col + j >= 0) {
    				array.add(new xy(lin+i, col+j));
    			}
    		}
    	}
    }
    
    private void updateBloco() {
    	if(mouH.clicadoE) {
    		if(mouH.x < linhas && mouH.x >= 0 && mouH.y < colunas && mouH.y >= 0) {
    			if(campoEx[(int)mouH.x][(int)mouH.y] == 12) {
    				if(campoIn[(int)mouH.x][(int)mouH.y] >= 0 && campoIn[(int)mouH.x][(int)mouH.y] <= 8) {
    					updateBlocoEmpty((int)mouH.x, (int)mouH.y);
    				}else if(campoIn[(int)mouH.x][(int)mouH.y] == 10) {
    					campoEx[(int)mouH.x][(int)mouH.y] = 11;
    					lose = true;
    				}
    				start = true;
    			}	
    		}
    		mouH.clicadoE = false;
    	}else if(mouH.clicadoD) {
    		if(mouH.x < linhas && mouH.x >= 0 && mouH.y < colunas && mouH.y >= 0) {
	    		if(campoEx[(int)mouH.x][(int)mouH.y] == 12  && contBombs > 0) {
	    			campoEx[(int)mouH.x][(int)mouH.y] = 9;
	    			contBombs--;
	    			start = true;
	    		}else if(campoEx[(int)mouH.x][(int)mouH.y] == 9){
	    			campoEx[(int)mouH.x][(int)mouH.y] = 12;
	    			contBombs++;
	    		}
    		}
    		mouH.clicadoD = false;
    	}
    }
    
    private void isLose() {
    	if(lose) {
    		for(int i = 0; i < linhas; i++) {
        		for(int j = 0; j < colunas; j++) {
        			if(campoIn[i][j] == 10 && campoEx[i][j] != 11 && campoEx[i][j] != 9) {
        				campoEx[i][j] = campoIn[i][j];
        			}
            	}
        	}
    		start = false;
    		JOptionPane.showMessageDialog(null, "Você perdeu.");
    	}
    }
    
    private void isWin() {
    	if(contBlocos == bombs) {
    		win = true;
    		start = false;
    		JOptionPane.showMessageDialog(null, "Você ganhou!!!");
    	}
    }
    
    private void updateBlocoEmpty(int x, int y) {
    	if(x < 0 || x >= linhas || y < 0 || y >= colunas) {
    		return;
    	}
    	if(campoEx[x][y] == 9) {
    		return;
    	}
    	if(campoIn[x][y] >= 9 && campoIn[x][y] <= 12) {
    		return;
    	}
    	if(campoIn[x][y] >= 1 && campoIn[x][y] <= 8) {
    		campoEx[x][y] = campoIn[x][y];
    		campoIn[x][y] = 12;
    		contBlocos--;
    		return;
    	}
    	campoEx[x][y] = campoIn[x][y];
    	campoIn[x][y] = 12;
    	contBlocos--;
    	updateBlocoEmpty(x-1, y-1);
    	updateBlocoEmpty(x-1, y);
    	updateBlocoEmpty(x-1, y+1);
    	updateBlocoEmpty(x, y-1);
    	updateBlocoEmpty(x, y+1);
    	updateBlocoEmpty(x+1, y-1);
    	updateBlocoEmpty(x+1, y);
    	updateBlocoEmpty(x+1, y+1);
    }
    
    public void update(){
        //player.update();
        //inimigo.update();
    	if(!lose && !win) {
    		updateBloco();
    		isLose();
    		isWin();
    	}
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        //player.draw(g2);
        //inimigo.draw(g2);
        for(int i = 0; i < linhas; i++) {
    		for(int j = 0; j < colunas; j++) {
    			blocoH.getBloco(campoEx[i][j]).draw(g2, i*tileSize, j*tileSize, tileSize);
        	}
    	}
        g.dispose();
    }
    
    
    public int[][] getCampoIn(){
    	return campoIn;
    }
    
    public int[][] getCampoEx(){
    	return campoEx;
    }
    
    public int getTileSize() {
    	return tileSize;
    }
    
    public int getWidth() {
    	return linhas*tileSize;
    }
    
    public int getContBombs() {
    	return contBombs;
    }
    
    public boolean getStart() {
    	return start;
    }
    
    public boolean getLose() {
    	return lose;
    }
    
    public boolean getWin() {
    	return win;
    }
    
    public String getTitulo() {
    	return titulo;
    }
}

class xy{
	public int x, y;
	
	public xy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
