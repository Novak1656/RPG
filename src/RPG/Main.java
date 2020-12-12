package RPG;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

public class Main extends JPanel implements Runnable, Serializable {
	
	//Разрешение экрана
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	private transient static final long serialVersionUID = 1L;
	public static int MouseX;
	public static int MouseY;
	public static boolean clic, sword, TAB;
	
	public static enum STATES{
		menu,
		play,
		gameover,
		skill
	}
	public static STATES state = STATES.menu;
	
	
	private Thread potok = new Thread(this);
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS;
	private double msecFPS;
	private long FPStimer;
	private int sleeptime;
	
	public static RPGback background;
	public static Player player;
	public static ArrayList<Magic> magic;
	public static ArrayList<Enemy> enemies;
	public static Spawnmobs spawn;
	public static Interface intface;
	public static MainMenu menu;
	public static GameOver gameover;
	public static Skills skill;
	
	
	//Конструктор
	public Main() {
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();      //Делает окно активным
		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	public void start() {
		potok = new Thread(this);
		potok.start();
	}
	
	//Main Функции
	public void run() {
		
		FPS = 30;
		msecFPS = 1000 / FPS;
		sleeptime = 0;
		clic = false;
		TAB = false;
		sword = false;
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		background = new RPGback();
		player = new Player();
		magic = new ArrayList<Magic>();
		enemies = new ArrayList<Enemy>();
		spawn = new Spawnmobs();
		intface = new Interface();
		menu = new MainMenu();
		gameover = new GameOver();
		skill = new Skills();
//		enemies.add(new Enemy(1,1));
//		enemies.add(new Enemy(1,1));
		
		while(true) {
			FPStimer = System.nanoTime();
			
			if(state.equals(STATES.menu)) {
				background.Update();
				background.draw(g);
				menu.Update();
				menu.draw(g);
				RPGdraw();
			}
			
			if(state.equals(STATES.play)) {
				Update();
			    Render();
			    RPGdraw();
			}
			
			if(state.equals(STATES.gameover)) {
				background.Update();
				background.draw(g);
				gameover.Update();
				gameover.draw(g);
				RPGdraw();
			}
			
			if(state.equals(STATES.skill)) {
				background.Update();
				background.draw(g);
				skill.Update();
				skill.draw(g);
				RPGdraw();
			}
			
			FPStimer = (System.nanoTime() - FPStimer) / 1000000;
			
			if(msecFPS > FPStimer) {
				sleeptime = (int) msecFPS - (int) FPStimer;
			}else sleeptime = 1;
			
			try {
				potok.sleep(sleeptime);  
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			FPStimer = 0;
			sleeptime = 1;
		}	
	}
	
	//Обновление состояния игровых объектов
	public void Update() {
		//Обновление фона
		background.Update();
		
		//Обновление данных игрока
		player.Update();
		
		//Обновление данных магии
		for(int i = 0; i < magic.size(); i++) {
			magic.get(i).Update();
			boolean ClearList = magic.get(i).ClearList();
			if(ClearList) {
				magic.remove(i);
				i--;
			}
		}
		
		//Обновление данных врагов
		for(int i = 0; i < enemies.size();i++) {
			enemies.get(i).Update();
		}
		
		//Попадание магией по врагу
		for(int i = 0; i < enemies.size();i++) {
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			for(int j = 0; j < magic.size();j++) {
				Magic m = magic.get(j);
				double mx = m.getX();
				double my = m.getY();
				
				double dx = ex - mx; 
				double dy = ey - my;
				double dist = Math.sqrt(dx*dx+dy*dy);
				if ((int)(dist) < e.getR() + m.getR()) {
					e.damage();
					magic.remove(j);
					j--;
					boolean death = e.death();
			        if(death) {
				    enemies.remove(i);
			        i--;
			        player.Mana += 3;
					break;
				}
			}
				
			}
		}
		
		//Нанесение урона игроку
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			Player p = player;
			double px = p.getX();
			double py = p.getY();
			
			double dx = ex - px; 
			double dy = ey - py;
			double dist = Math.sqrt(dx*dx+dy*dy);
			if ((int)(dist) <= e.getR() + p.getR()) {
				p.damage();
			}
			
			if(p.HP == 0) {state = STATES.gameover;}			
		}
		
		if(sword) {
			for(int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				double ex = e.getX();
				double ey = e.getY();
				
				Player p = player;
				double px = p.getX();
				double py = p.getY();
				
				double dx = ex - px; 
				double dy = ey - py;
				double dist = Math.sqrt(dx*dx+dy*dy);
				if ((int)(dist) <= (e.getR()+1) + (p.getR()+1)) {
					e.damage();
					boolean death = e.death();
			        if(death) {
				    enemies.remove(i);
			        i--;
			        player.Mana += 3;
					break;}
		}
	   }
	  }
		//Обновление информации о спавне противников
		spawn.Update();
		
		//Обновление элементов интерфейса
		intface.Update();
		
		if(state == STATES.gameover) {
			gameover.Update();
		}
		
		if (TAB) {skill.Update();}
	}
	
	
	//Обновление графических объектов
	public void Render() {
		
		//Прорисовка фона
		background.draw(g);
		
		//Отрисовка игрока
		player.draw(g);
		
		//Отрисовка магии
		for(int i = 0; i < magic.size(); i++) {
			magic.get(i).draw(g);
		}
		
		//Отрисовка врагов
		for(int i = 0; i < enemies.size();i++) {
			enemies.get(i).draw(g);;
		}
		
		//Отрисока оповещения 
		spawn.draw(g);
		
		
		//Отрисовка интерфейса
		intface.draw(g);
		
		if(state == STATES.gameover) {
			gameover.draw(g);
		}
		
		if(TAB){skill.draw(g);}
		
	}
	
	private void RPGdraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
		
	}
		
}


