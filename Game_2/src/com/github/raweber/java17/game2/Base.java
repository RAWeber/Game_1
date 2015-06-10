package com.github.raweber.java17.game2;

public class Base {

	private int xPos;
	private int yPos;
	
	public Base(int xPos, int yPos){
		this.setXPos(xPos);
		this.setYPos(yPos);
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
}
