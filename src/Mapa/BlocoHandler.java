package Mapa;

import java.io.IOException;
import javax.imageio.ImageIO;

public class BlocoHandler{
	private Bloco[] blocos;
	
	public BlocoHandler() throws IOException {
		this.blocos = new Bloco[13];
		getImagens();
	}
	
	private void getImagens() throws IOException {
		blocos[0] = new Bloco();
		blocos[0].setImagem(ImageIO.read(getClass().getResourceAsStream("/vazio.png")));
		blocos[1] = new Bloco();
		blocos[1].setImagem(ImageIO.read(getClass().getResourceAsStream("/num1.png")));
		blocos[2] = new Bloco();
		blocos[2].setImagem(ImageIO.read(getClass().getResourceAsStream("/num2.png")));
		blocos[3] = new Bloco();
		blocos[3].setImagem(ImageIO.read(getClass().getResourceAsStream("/num3.png")));
		blocos[4] = new Bloco();
		blocos[4].setImagem(ImageIO.read(getClass().getResourceAsStream("/num4.png")));
		blocos[5] = new Bloco();
		blocos[5].setImagem(ImageIO.read(getClass().getResourceAsStream("/num5.png")));
		blocos[6] = new Bloco();
		blocos[6].setImagem(ImageIO.read(getClass().getResourceAsStream("/num6.png")));
		blocos[7] = new Bloco();
		blocos[7].setImagem(ImageIO.read(getClass().getResourceAsStream("/num7.png")));
		blocos[8] = new Bloco();
		blocos[8].setImagem(ImageIO.read(getClass().getResourceAsStream("/num8.png")));
		blocos[9] = new Bloco();
		blocos[9].setImagem(ImageIO.read(getClass().getResourceAsStream("/normalFlag.png")));
		blocos[10] = new Bloco();
		blocos[10].setImagem(ImageIO.read(getClass().getResourceAsStream("/bomba.png")));
		blocos[11] = new Bloco();
		blocos[11].setImagem(ImageIO.read(getClass().getResourceAsStream("/bombaErro.png")));
		blocos[12] = new Bloco();
		blocos[12].setImagem(ImageIO.read(getClass().getResourceAsStream("/normal.png")));
	}
	
	public Bloco getBloco(int i) {
		return blocos[i];
	}
}
