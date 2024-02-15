
package Game;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;

public class Game implements Runnable{

    private Tela tela;
    private Thread gameThread;
    private int fps = 120;
    private int ups = 200;
    private JFrame janela;
    private Menu menu;
    private boolean startThread = true;
    private MiniMenu miniMenu;
    
    public Game(Menu menu) throws IOException {
    	this.menu = menu;  
    	gameThread = new Thread(this);
    }
    
    public void startTela(int linhas, int colunas, int tileSize, int bombs, String titulo) {
    	janela = new JFrame();
        janela.setTitle(titulo);
        janela.setLayout(new BorderLayout());
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.setResizable(false);
    	try {
			tela = new Tela(linhas, colunas, tileSize, bombs, titulo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	miniMenu = new MiniMenu(tela);
    	
    	janela.add(miniMenu, BorderLayout.NORTH);
    	
    	janela.add(tela, BorderLayout.SOUTH);
    	janela.pack();
    	janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				janela.setVisible(false);
				menu.getJanela().setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        if(startThread) {
        	gameThread.start();
        	startThread = false;
        }
    }
    
    private void update() {
    	tela.update();
    	miniMenu.update();
    }
    
    @Override
    public void run() {
        double intervaloFps = 1000000000.0/fps;
        double intervaloUps = 1000000000.0/ups;
        long prevTime = System.nanoTime();
        double deltaF = 0;
        double deltaU = 0;
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        while(true){
        	long currentTime = System.nanoTime();
        	
        	deltaF += (currentTime - prevTime)/ intervaloFps;
        	deltaU += (currentTime - prevTime)/ intervaloUps;
        	prevTime = currentTime;
        	
        	if(deltaU >= 1) {
        		update();
        		updates++;
        		deltaU--;
        	}
        	if(deltaF >= 1) {
        		tela.repaint();
        		frames++;
        		deltaF--;
        	}
        	
        	if(System.currentTimeMillis() - lastCheck >= 1000) {
        		lastCheck = System.currentTimeMillis();
        		System.out.println("Fps: " + frames + "| Ups: " + updates);
        		frames = 0;
        		updates = 0;
        		if(tela.getStart()) {
        			miniMenu.upTime();
        		}
        	}
            
        }
    }
    
    public JFrame getJanela() {
    	return janela;
    }
    
    public Menu getMenu() {
    	return menu;
    }
    
}
