package com.github.raweber.java17.game2;

import javax.swing.JFrame;


public class Frame extends JFrame {

	private static final long serialVersionUID = -7535567184411948193L;

	public static final int WIDTH = 1200, HEIGHT = WIDTH / 12 * 9;
	
	public Frame(){
		new JFrame();
		this.setSize(WIDTH,HEIGHT);
		this.setTitle("Tower Defense");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.add(componentHolder);
		//this.pack();
		this.setLocationRelativeTo(null);

		//this.setExtendedState(MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());
		
		Screen screen = new Screen();
		this.add(screen);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new Frame();
	}
}
