
package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import Game.Tela;

public class MouseInput implements MouseListener, MouseMotionListener{
    private Tela tela;
    public double x = 0, y = 0;
    public boolean clicadoD = false, clicadoE = false;
    
    public MouseInput(Tela tela){
        this.tela = tela;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	x = e.getX() /(double) tela.getTileSize();
    	y = e.getY() /(double) tela.getTileSize();
    	if(SwingUtilities.isLeftMouseButton(e)) {
			clicadoE = true;
    	}else if(SwingUtilities.isRightMouseButton(e)){
    		clicadoD = true;
    	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
