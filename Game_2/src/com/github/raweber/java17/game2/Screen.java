package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialVersionUID = 5115507157473931848L;

	private Thread thread = new Thread(this);
	private int fps=0;
	public enum STATE {
		Menu, Game, GameOver;
	};
	
	public static STATE gameState = STATE.Game;
	
	public static int TOWER_SIZE;
	
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static int SCREEN_BORDER;
	
	public static int speed;
	
	public static int[][] map = new int[25][15];
	public static Tower[][] towerMap = new Tower[25][15];
	private Image[] terrain = new Image[100];
	
	public static ArrayList<EnemyMove> enemyMap= new ArrayList<EnemyMove>();
	public static Wave wave;
	private EnemyAI enemyAI;
	
	public static Level level;
	private LevelFile levelFile;
	public static Player player;
	public static Tower selectedTower;
	
	private double interpolation = 0;
	
	public Screen(int w) {
		SCREEN_WIDTH=w;
		SCREEN_HEIGHT=w*9/16;
		SCREEN_BORDER=(Frame.HEIGHT-SCREEN_HEIGHT)/2;
		TOWER_SIZE=SCREEN_HEIGHT/20;
		
		speed=1;
		
		levelFile=new LevelFile();
		level=levelFile.getLevel("Level2");
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
			
			//Enemies
			for(int i=0; i<enemyMap.size();i++){
				enemyMap.get(i).render(g, (int)enemyMap.get(i).getXPos()+TOWER_SIZE, (int)enemyMap.get(i).getYPos()+TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE,TOWER_SIZE);
			}
			
			//Towers
			for(int x=0;x<25;x++){
				for(int y=0;y<15;y++){
					if(towerMap[x][y]!=null){
						towerMap[x][y].render(g, TOWER_SIZE+(x*TOWER_SIZE), TOWER_SIZE+(y*TOWER_SIZE)+SCREEN_BORDER, TOWER_SIZE, TOWER_SIZE);
						if(towerMap[x][y].getProjectiles().length==0){
							if(towerMap[x][y].getTarget()!=null){
								g.setColor(Color.red);
								g.drawLine(TOWER_SIZE*3/2+(x*TOWER_SIZE), TOWER_SIZE*3/2+(y*TOWER_SIZE)+SCREEN_BORDER, TOWER_SIZE*3/2+(int)towerMap[x][y].getTarget().getXPos(), TOWER_SIZE*3/2+(int)towerMap[x][y].getTarget().getYPos()+SCREEN_BORDER);
							}
						}else{
							Graphics2D g2d = (Graphics2D)g;
							Projectile[] projectiles=towerMap[x][y].getProjectiles();
							for(int i=0;i<projectiles.length;i++){
								if(projectiles[i]!=null){
									double rotation=Math.PI/2+projectiles[i].getDirection();
									double pX = projectiles[i].getX()+TOWER_SIZE/4;
									double pY = projectiles[i].getY()+TOWER_SIZE/4;
									g2d.rotate(rotation, pX ,pY);
									projectiles[i].render(g, (int)projectiles[i].getX(), (int)projectiles[i].getY(), TOWER_SIZE/2, TOWER_SIZE/2);
									g2d.rotate(-rotation, pX ,pY);
								}
							}

						}
						if(towerMap[x][y]==selectedTower){
							g.setColor(Color.gray);
							g.drawOval((TOWER_SIZE*(x+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2, (TOWER_SIZE*(y+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE);
							g.setColor(new Color(64,64,64,64));
							g.fillOval((TOWER_SIZE*(x+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2, (TOWER_SIZE*(y+1))-(TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE)/2+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE, TOWER_SIZE*towerMap[x][y].getRange()*2+TOWER_SIZE);
						}
					}
				}
			}
			
			//selectedTower
			g.setColor(Color.black);
			g.drawImage(terrain[map[0][0]], TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*4, null);
			if(selectedTower!=null){
				selectedTower.render(g, TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*4);
			}
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*4);
			
			//description
			g.setColor(Color.gray);
			g.fillRect(TOWER_SIZE*31,TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*10);
			g.setColor(Color.black);
			g.drawRect(TOWER_SIZE*31,TOWER_SIZE+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*10);
			
			//strategies
			g.setColor(Color.gray);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*5+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*7+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*8+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*10+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.setColor(Color.black);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*5+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*7+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*8+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*10+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE);
			
			//upgrades
			g.setColor(Color.gray);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*12+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*2);
			g.fillRect(TOWER_SIZE*31, TOWER_SIZE*12+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*2);
			g.setColor(Color.black);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*12+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*2);
			g.drawRect(TOWER_SIZE*31, TOWER_SIZE*12+SCREEN_BORDER, TOWER_SIZE*4, TOWER_SIZE*2);
			
			//Options
			
			//Startbutton
			g.setFont(new Font(g.getFont().getName(), Font.PLAIN, Screen.TOWER_SIZE*3/2));
			g.setColor(Color.cyan);
			g.fillRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*16+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*8+TOWER_SIZE/2, TOWER_SIZE*3);
			g.setColor(Color.black);
			g.drawRect(TOWER_SIZE*26+TOWER_SIZE/2, TOWER_SIZE*16+TOWER_SIZE/2+SCREEN_BORDER, TOWER_SIZE*8+TOWER_SIZE/2, TOWER_SIZE*3);
			if(enemyMap.size()==0){
				g.drawString("Next Wave", TOWER_SIZE*27, TOWER_SIZE*18+TOWER_SIZE/2+SCREEN_BORDER);
			}else if(speed==1){
				g.drawString("Speed X4", TOWER_SIZE*27, TOWER_SIZE*18+TOWER_SIZE/2+SCREEN_BORDER);
			}else{
				g.drawString("Speed X1", TOWER_SIZE*27, TOWER_SIZE*18+TOWER_SIZE/2+SCREEN_BORDER);
			}
			
			TowerStore.render(g);		
			player.render(g);
			
			if(MouseHandler.holding!=0 && TowerStore.towers[MouseHandler.holding-1]!=null){
				int i=MouseHandler.holding-1;
				TowerStore.towers[i].render(g, MouseHandler.getMouseX()-(int)(TOWER_SIZE/2), MouseHandler.getMouseY()-(int)(TOWER_SIZE/2),TOWER_SIZE,TOWER_SIZE);
				if(MouseHandler.getMouseX()>TOWER_SIZE && MouseHandler.getMouseX()<TOWER_SIZE*26 && MouseHandler.getMouseY()>TOWER_SIZE+SCREEN_BORDER && MouseHandler.getMouseY()<TOWER_SIZE*16+SCREEN_BORDER){
					g.setColor(Color.gray);
					g.drawOval((MouseHandler.getMouseX())-(TOWER_SIZE*TowerStore.towers[i].getRange()*2+TOWER_SIZE)/2, (MouseHandler.getMouseY())-(TOWER_SIZE*TowerStore.towers[i].getRange()*2+TOWER_SIZE)/2, TOWER_SIZE*TowerStore.towers[i].getRange()*2+TOWER_SIZE, TOWER_SIZE*TowerStore.towers[i].getRange()*2+TOWER_SIZE);
				}
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
		
		final int TICKS_PER_SECOND = 25;
		final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
		final int MAX_FRAMESKIP = 5;

		double next_game_tick = System.currentTimeMillis();
	    int loops;
	    
		while (true) {
			loops = 0;
			while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
				update();
				next_game_tick += SKIP_TICKS;
				loops++;
				frames++;
					
				if(System.currentTimeMillis()-1000>=lastFrame){
					fps=frames;
					frames=0;
					lastFrame=System.currentTimeMillis();
				}
			}
			interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick)/ (double) SKIP_TICKS;
			repaint();
		}
	}
	
	public void update(){
		enemyUpdate();
		towerUpdate();
		
		
		if(wave.isWaveSpawning()){
			wave.spawnEnemies();
		}
	}
	
	public void enemyUpdate(){
		for(int i=0; i<enemyMap.size();i++){
			if(!enemyMap.get(i).isAttacking()){
				EnemyAI.moveAI.move(enemyMap.get(i));
			}
			if(enemyMap.get(i).update()){
				enemyMap.remove(i);
			}
		}
	}
	
	public void towerUpdate(){
		for(int x=0;x<25;x++){
			for(int y=0;y<15;y++){
				if(towerMap[x][y]!=null){
					towerMap[x][y].towerAttack(enemyMap, x,y);
					if(towerMap[x][y].getProjectiles().length!=0){
						projectileUpdate(towerMap[x][y]);
					}
				}
			}
		}
	}
	
	public void projectileUpdate(Tower tower){
		for(int i=0;i<tower.getProjectiles().length;i++){
			if(tower.getProjectiles()[i]!=null){
				tower.getProjectiles()[i].update();
				if(tower.getProjectiles()[i].getTarget()==null){
					tower.getProjectiles()[i]=null;
				}
			}
		}
	}
}
