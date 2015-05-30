package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;





import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 5115507157473931848L;

	private Thread thread = new Thread(this);
	private int fps=0;
	public enum STATE {
		Menu, Game;
	};
	
	public static STATE gameState = STATE.Game;
	
//	private static double ratio=(double)(SCREEN_WIDTH)/SCREEN_HEIGHT;
//	public static int TOWER_WIDTH=(int)(SCREEN_WIDTH/ratio/20);
//	public static int TOWER_HEIGHT=(int)(SCREEN_HEIGHT/20);
	
	public static int TOWER_SIZE;
	
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static int SCREEN_BORDER;
	
	public static int[][] map = new int[25][15];
	public static Tower[][] towerMap = new Tower[25][15];
	private Image[] terrain = new Image[100];
	
	public static EnemyMove[] enemyMap = new EnemyMove[100];
	private int enemies = 0;
	public static Wave wave;
	private EnemyAI enemyAI;
	
	public static Level level;
	private LevelFile levelFile;
	public static Player player;
	
	public Screen(int w) {
		SCREEN_WIDTH=w;
		SCREEN_HEIGHT=w*9/16;
		SCREEN_BORDER=(Frame.HEIGHT-SCREEN_HEIGHT)/2;
		TOWER_SIZE=SCREEN_HEIGHT/20;
		
		levelFile=new LevelFile();
		level=levelFile.getLevel("Level1");
		level.findSpawnPoint();
		map=level.getMap();
		
		wave=new Wave();
		
		player = new Player();
		ImageHandler.addImages();
		
		enemyAI=new EnemyAI(level);
		
		thread.start();		
		
		for (int y=0;y<10;y++){
			for(int x=0;x<10;x++){
				terrain[x+y*10]=createImage(new FilteredImageSource(ImageHandler.getIcon("Terrain").getSource(), new CropImageFilter(x*25,y*25,25,25)));
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		//Border
		g.setColor(Color.black);
		g.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		
		if(gameState==STATE.Menu){
			g.setColor(Color.BLACK);
			g.fillRect(0, SCREEN_BORDER, SCREEN_WIDTH, SCREEN_HEIGHT);
		}else if(gameState==STATE.Game){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, SCREEN_BORDER, SCREEN_WIDTH, SCREEN_HEIGHT);
			g.setColor(Color.black);

			//Grid
			for(int x=0;x<25;x++){
				for(int y=0;y<15;y++){
					g.drawImage(terrain[map[x][y]], TOWER_SIZE+x*TOWER_SIZE, TOWER_SIZE+y*TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE,TOWER_SIZE, null);
					g.drawRect(TOWER_SIZE+(x*TOWER_SIZE), TOWER_SIZE+(y*TOWER_SIZE)+SCREEN_BORDER, TOWER_SIZE, TOWER_SIZE);
				}
			}
			
			//Range
			for(int x=0;x<25;x++){
				for(int y=0;y<15;y++){
					if(towerMap[x][y]!=null){
						g.setColor(Color.gray);
						g.drawOval((TOWER_SIZE*(x+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2, (TOWER_SIZE*(y+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE);
						g.setColor(new Color(64,64,64,64));
						g.fillOval((TOWER_SIZE*(x+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2, (TOWER_SIZE*(y+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE);
					}
				}
			}
			
			//Towers
			for(int x=0;x<25;x++){
				for(int y=0;y<15;y++){
					if(towerMap[x][y]!=null){
						towerMap[x][y].render(g, TOWER_SIZE+(x*TOWER_SIZE), TOWER_SIZE+(y*TOWER_SIZE)+SCREEN_BORDER, TOWER_SIZE, TOWER_SIZE);
					}
				}
			}
			
			//Enemies
			for(int i=0; i<enemyMap.length;i++){
				if(enemyMap[i]!=null){
					g.drawImage(new ImageIcon("res/BasicEnemy.png").getImage(), (int)enemyMap[i].getXPos()+TOWER_SIZE, (int)enemyMap[i].getYPos()+TOWER_SIZE+ SCREEN_BORDER, TOWER_SIZE,TOWER_SIZE, null);
					//enemyMap[i].render(g, enemyMap[i].getXPos()+TOWER_SIZE, enemyMap[i].getYPos()+TOWER_SIZE+ SCREEN_BORDER, TOWER_SIZE,TOWER_SIZE);
				}
			}
			
			TowerStore.render(g);		
			player.render(g);
			
			if(MouseHandler.holding!=0 && TowerStore.towers[MouseHandler.holding-1]!=null){
				TowerStore.towers[MouseHandler.holding-1].render(g, MouseHandler.getMouseX()-(int)(TOWER_SIZE/2), MouseHandler.getMouseY()-(int)(TOWER_SIZE/2),TOWER_SIZE,TOWER_SIZE);
			}
		}
		g.setColor(Color.white);
		g.drawString(fps+"", 20, 20);
	}

	public void run() {
		System.out.println("[Success] Frame Created");
		System.out.println("Width: "+SCREEN_WIDTH+" Height: "+SCREEN_HEIGHT);
		System.out.println("Tower Size: "+TOWER_SIZE);

		long lastFrame = System.currentTimeMillis();
		int frames = 0;

		while (true) {
			repaint();
			update();
			
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
	
	public void update(){
		enemyUpdate();
		
		if(wave.isWaveSpawning()){
			wave.spawnEnemies();
		}
	}
	
	public void enemyUpdate(){
		for(int i=0; i<enemyMap.length;i++){
			if(enemyMap[i]!=null){
				if(!enemyMap[i].isAttacking()){
					EnemyAI.moveAI.move(enemyMap[i]);
				}
				enemyMap[i].update();
			}
		}
	}
	
//	private static void setSize(){
//		if(ratio<33.0/20.0){
//			TOWER_WIDTH=(int)(SCREEN_WIDTH/33);
//			TOWER_HEIGHT=(int)(SCREEN_HEIGHT*ratio/33);
//			System.out.println("Resized");
//		}
//	}
	
	//public Player getPlayer(){
		//return player;
	//}
}
