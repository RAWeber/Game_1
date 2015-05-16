package com.github.raweber.java17;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected double x, y;
	protected ID id;
	protected double velX, velY;
	protected Handler handler;

	public GameObject(double x, double y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}
}
