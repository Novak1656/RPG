package RPG;

import java.util.ArrayList;

public class Cont {
	public static RPGback background;
	public static Player player;
	public static ArrayList<Magic> magic;
	public static ArrayList<Enemy> enemies;
	public static Spawnmobs spawn;
	public static Interface intface;
	public static MainMenu menu;
	public static GameOver gameover;
	
	public Cont() {
		background = new RPGback();
		player = new Player();
		magic = new ArrayList<Magic>();
		enemies = new ArrayList<Enemy>();
		spawn = new Spawnmobs();
		intface = new Interface();
		menu = new MainMenu();
		gameover = new GameOver();
	}
}
