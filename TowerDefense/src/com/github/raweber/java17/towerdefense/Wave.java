package com.github.raweber.java17.towerdefense;

import java.util.ArrayList;
import java.util.Random;

public class Wave {

	private int waveNumber=0;
	private int currentPoints;
	private int pointsPerWave=10;
	
	private boolean waveSpawning=false;
	
	private int currentDelay=0;
	private int spawnRate=25;
	
	public void nextWave(){
		Screen.speed=1;
		waveNumber++;
		currentPoints=0;
		currentDelay=25;
		setWaveSpawning(true);
		
		System.out.println("Wave "+waveNumber+" incoming!");
		
	}
	
	public void spawnEnemies(){
		if(currentPoints<waveNumber*pointsPerWave){
			if(currentDelay<spawnRate){
				currentDelay++;
			}else{
				currentDelay=0;
				ArrayList<Integer> spawnableIDs = new ArrayList<Integer>();
				for(int i=0;i<Enemy.enemyList.length;i++){
					if(Enemy.enemyList[i]!=null){
						if(Enemy.enemyList[i].getId()*2<=waveNumber-1){
							if(Enemy.enemyList[i].getValue()+currentPoints<=waveNumber*pointsPerWave){
							spawnableIDs.add(Enemy.enemyList[i].getId());
							}
						}
					}
				}

				spawnEnemy(spawnableIDs.get(new Random().nextInt(spawnableIDs.size())));
				
			}
		}else{
			setWaveSpawning(false);
		}
	}
	
	private void spawnEnemy(int id){
		Screen.enemyMap.add(new EnemyMove(Enemy.enemyList[id],Screen.level.getSpawnPoint()));
		currentPoints+=Enemy.enemyList[id].getValue();
		System.out.println(Enemy.enemyList[id].getType()+" spawned!");
	}

	public boolean isWaveSpawning() {
		return waveSpawning;
	}

	public void setWaveSpawning(boolean waveSpawning) {
		this.waveSpawning = waveSpawning;
	}
}