package com.github.raweber.java.towerdefense;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -5593065721855705769L;

	public static final int WIDTH = 1200, HEIGHT = WIDTH / 16 * 9;

	private Thread thread;
	private boolean running = false;
	
	public enum STATE {
		Menu, Game, End
	}
	
	public static STATE state = STATE.Menu;
	
	public Game(){
		new Window(WIDTH, HEIGHT, "Tower Defense", this);
		this.requestFocus();
		this.addKeyListener(new KeyInput());
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		
		int w=WIDTH/32;
		int h=HEIGHT/18;
		
		for(int x=0;x<24;x++){
			for(int y=0;y<12;y++){
				g.drawRect(w+(x*w), h+(y*h), w, h);
			}
		}
		
		g.dispose();
		bs.show();
	}
	
	public static double clamp(double var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var < min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
