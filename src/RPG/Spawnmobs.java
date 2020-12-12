package RPG;
import java.awt.*;

public class Spawnmobs extends Abstract {
	private int spawnWaveNum, spawnEnemyCompl;
	private long spawnTimer, spawnDelay, spawnTimerDiff;
	private String spawnText1, spawnText2;
	
	public Spawnmobs() {
		spawnWaveNum = 1;
		spawnEnemyCompl = 5;
		spawnTimer = 0;
		spawnDelay = 5000;
		spawnTimerDiff = 0;
		spawnText1 = "����:";
		spawnText2 ="���� �������� ������������!!!"; 
	}
	
	public void spawnEnemies() {
		int enemyCount = spawnWaveNum * spawnEnemyCompl;
		
		if(spawnWaveNum < 10) {
			while(enemyCount > 0) {
				int type = 1;
				int rank = 1;
				
				Main.enemies.add(new Enemy(type,rank));
				enemyCount -= type*rank;
			}
			
		}
		spawnWaveNum++;
		if(Main.player.HP <= 25)Main.player.HP += 25;
	}
	
	public void Update() {
		if(Main.enemies.size() == 0 && spawnTimer == 0) {
			spawnTimer = System.nanoTime();	
		}
		
		if (spawnTimer > 0){
			spawnTimerDiff += System.nanoTime()/1000000;
			spawnTimer = System.nanoTime();
		}
		
		if(spawnTimerDiff > spawnDelay) {
			spawnEnemies();
			spawnTimer = 0;
			spawnTimerDiff = 0;
		}
	}
	
	public void draw(Graphics2D g) {
		g.setFont(new Font("consolas", Font.PLAIN, 30));
		g.setColor(Color.RED);
		String str ="[" + spawnText1 + (spawnWaveNum-1) + "]";
		long length= (int)g.getFontMetrics().getStringBounds(str,g).getWidth(); 
		g.drawString(str,Main.WIDTH-(int) length, 30);
	}

}
