package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;





import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 5115507157473931848L;

	private Thread thread = new Thread(this);
	private int fps=0;
	public enum STATE {
		Menu, Game;
	};
	
	public static STATE gameState = STATE.Game;
	
	private static double ratio=(double)(Frame.WIDTH)/Frame.HEIGHT;
	
	public static int TOWER_WIDTH=(int)(Frame.WIDTH/ratio/20);
	public static int TOWER_HEIGHT=(int)(Frame.HEIGHT/20);
	
	public static int[][] map = new int[25][15];
	public static Tower[][] towerMap = new Tower[25][15];
	private Image[] terrain = new Image[100];
	private String packageName="com/github/raweber/java17/game2/";
	
	private Level level;
	private LevelFile levelFile;
	public static Player player;
	//TowerStore towerStore;
	
	public Screen() {
		thread.start();
		player = new Player();
		levelFile=new LevelFile();
		ImageHandler.addImages();
		//towerStore=new TowerStore(this);
		level=levelFile.getLevel("Level1");
		level.findSpawnPoint();
		map=level.getMap();
		
		for (int y=0;y<10;y++){
			for(int x=0;x<10;x++){
				terrain[x+y*10]=createImage(new FilteredImageSource(ImageHandler.getIcon("Terrain").getSource(), new CropImageFilter(x*25,y*25,25,25)));
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		
		if(gameState==STATE.Menu){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		}else if(gameState==STATE.Game){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
			g.setColor(Color.black);

			for(int x=0;x<25;x++){
				for(int y=0;y<15;y++){
					g.drawImage(terrain[map[x][y]], TOWER_WIDTH+x*TOWER_WIDTH, TOWER_HEIGHT+y*TOWER_HEIGHT, TOWER_WIDTH,TOWER_HEIGHT, null);
					g.drawRect(TOWER_WIDTH+(x*TOWER_WIDTH), TOWER_HEIGHT+(y*TOWER_HEIGHT), TOWER_WIDTH, TOWER_HEIGHT);
					if(towerMap[x][y]!=null){
						towerMap[x][y].render(g, TOWER_WIDTH+(x*TOWER_WIDTH), TOWER_HEIGHT+(y*TOWER_HEIGHT), TOWER_WIDTH, TOWER_HEIGHT);
					}
				}
			}
			
			TowerStore.render(g);		
			player.render(g);
			
			if(MouseHandler.holding!=0 && TowerStore.towers[MouseHandler.holding-1]!=null){
				TowerStore.towers[MouseHandler.holding-1].render(g, MouseHandler.getMouseX()-(int)(TOWER_WIDTH/2), MouseHandler.getMouseY()-(int)(TOWER_HEIGHT/2),TOWER_WIDTH,TOWER_HEIGHT);
			}
		}
		g.setColor(Color.white);
		g.drawString(fps+"", 10, 15);
	}
	
	public void placeTower(int x, int y){
		int xPos=(x-TOWER_WIDTH)/TOWER_WIDTH;
		int yPos=(y-TOWER_HEIGHT)/TOWER_HEIGHT;
		
		if(xPos>25 || yPos>15){
			
		}else if(towerMap[xPos][yPos]==null && map[xPos][yPos]==0){
			player.setMoney(player.getMoney()-TowerStore.towers[x*2+y].getCost());
			towerMap[xPos][yPos]=TowerStore.towers[MouseHandler.holding-1];
		}
	}

	public void run() {
		System.out.println("[Success] Frame Created");
		System.out.println("Width: "+Frame.WIDTH+" Height: "+Frame.HEIGHT);

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
	
	//public Player getPlayer(){
		//return player;
	//}
}
