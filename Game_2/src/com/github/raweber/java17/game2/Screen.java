package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 5115507157473931848L;

	private Thread thread = new Thread(this);
	private int fps=0;
	public enum STATE {
		Menu, Game;
	};
	public static STATE gameState = STATE.Game;
	
	Player player;
	ImageHandler handler;
	
	public Screen() {
		thread.start();
		player = new Player();
		handler = new ImageHandler();

	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		int w=Frame.WIDTH/30;
		int h=(int)(Frame.HEIGHT/22.5);
		
		if(gameState==STATE.Menu){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		}else if(gameState==STATE.Game){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
			g.setColor(Color.black);

			for(int x=0;x<24;x++){
				for(int y=0;y<16;y++){
					g.drawRect(w+(x*w), h+(y*h), w, h);
				}
			}
			
			for(int x=0;x<10;x++){
				for(int y=0;y<2;y++){
					g.setColor(Color.black);
					g.drawRect(250+(x*w), Frame.HEIGHT-190+(y*h), w, h);
					if(Tower.towers[x*2+y]!=null){
						Tower.towers[x*2+y].render(g, handler, 250+(x*w), Frame.HEIGHT-190+(y*h),w,h);
						if(Tower.towers[x*2+y].getCost()>player.getMoney()){
							g.setColor(new Color(255,0,0,100));
							g.fillRect(250+(x*w), Frame.HEIGHT-190+(y*h), w, h);
						}
					}
				}
			}
			
			player.render(g);
		}
		g.setColor(Color.white);
		g.drawString(fps+"", 10, 15);
		

	}

	public void run() {
		System.out.println("[Success] Frame Greated");
		
		long lastFrame = System.currentTimeMillis();
		int frames = 0;

		while (true) {
			repaint();
			
			frames++;
			
			if(System.currentTimeMillis()-1000>=lastFrame){
				fps=frames;
				frames=0;
				lastFrame=System.currentTimeMillis();
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
