package Mapa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bloco{
	private BufferedImage imagem;
	
	public Bloco() {
		imagem = null;
	}
	
	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}
	
	public void draw(Graphics2D g2, int x, int y, int tileSize) {
		g2.drawImage(imagem, x, y, tileSize, tileSize, null);
	}
}
