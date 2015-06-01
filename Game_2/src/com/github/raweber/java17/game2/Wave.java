package com.github.raweber.java17.game2;

public class Wave {

	private int waveNumber=0;
	private int waveEnemies=0;
	private int enemiesPerWave=10;
	private long lastSpawnTime=System.currentTimeMillis();
	
	private boolean waveSpawning=false;
	
	public void nextWave(){
		waveNumber++;
		waveEnemies=0;
		setWaveSpawning(true);
		
		System.out.println("Wave "+waveNumber+" incoming!");
		
		for(int i = 0; i < Screen.enemyMap.length;i++){
			Screen.enemyMap[i]=null;
		}
	}
	
	public void spawnEnemies(){
		if(waveEnemies<waveNumber*enemiesPerWave){
			if(System.currentTimeMillis()-1000>=lastSpawnTime){
				lastSpawnTime=System.currentTimeMillis();
				waveEnemies++;
				spawnEnemy();
				System.out.println("Enemy spawned!");
			}
		}else{
			setWaveSpawning(false);
		}
	}
	
	private void spawnEnemy(){
		for(int i = 0; i<Screen.enemyMap.length;i++){
			if(Screen.enemyMap[i]==null){
				Screen.enemyMap[i]=new EnemyMove(Enemy.enemyList[0],Screen.level.getSpawnPoint());
				break;
			}
		}
	}

	public boolean isWaveSpawning() {
		return waveSpawning;
	}

	public void setWaveSpawning(boolean waveSpawning) {
		this.waveSpawning = waveSpawning;
	}
}
