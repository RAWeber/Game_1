package com.github.raweber.java17.game2;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Frame extends JFrame {

	private static final long serialVersionUID = -7535567184411948193L;

	public static final int WIDTH=screenSize().width, HEIGHT=screenSize().height;
	//public static final int WIDTH=1200, HEIGHT=WIDTH*9/16;
	//public static final int WIDTH=1200, HEIGHT=1200;

	
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
		this.setUndecorated(true);
		
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());
		this.addMouseMotionListener(new MouseInput());
		
		Screen screen = new Screen(this.getWidth());
		this.add(screen);
		
		this.setVisible(true);
	}
	
	private static Dimension screenSize(){
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    return d;
	}
	
	
	public static void main(String[] args){
		new Frame();
	}
}
