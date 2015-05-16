package com.github.raweber.java17;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private double alpha = 1;
	private Color color;
	private int width, height;
	//life between 0.001 to 0.01
	private double life;
	
	public Trail(double x, double y, ID id, Color color, int width, int height, double life, Handler handler) {
		super(x, y, id, handler);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	public void tick() {
		if(alpha>life){
			alpha -= life-0.001;
		}else handler.removeObject(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent((float)alpha));
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);

		g2d.setComposite(makeTransparent(1f));
	}

	private AlphaComposite makeTransparent(Float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
