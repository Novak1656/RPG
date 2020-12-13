package RPG;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class Main extends JPanel implements Runnable {
	
	//���������� ������
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
	
	public Cont cont;
	
	
	//�����������
	public Main() {
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();      //������ ���� ��������
		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	public void start() {
		potok = new Thread(this);
		potok.start();
	}
	
	//Main �������
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
		
		cont = new Cont();
		

		
		while(true) {
			FPStimer = System.nanoTime();
			
			if(state.equals(STATES.menu)) {
				cont.background.Update();
				cont.background.draw(g);
				cont.menu.Update();
				cont.menu.draw(g);
				RPGdraw();
			}
			
			if(state.equals(STATES.play)) {
				Update();
			    Render();
			    RPGdraw();
			}
			
			if(state.equals(STATES.gameover)) {
				cont.background.Update();
				cont.background.draw(g);
				cont.gameover.Update();
				cont.gameover.draw(g);
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
	
	//���������� ��������� ������� ��������
	public void Update() {
		//���������� ����
		cont.background.Update();
		
		//���������� ������ ������
		cont.player.Update();
		
		//���������� ������ �����
		for(int i = 0; i < cont.magic.size(); i++) {
			cont.magic.get(i).Update();
			boolean ClearList = cont.magic.get(i).ClearList();
			if(ClearList) {
				cont.magic.remove(i);
				i--;
			}
		}
		
		//���������� ������ ������
		for(int i = 0; i < cont.enemies.size();i++) {
			cont.enemies.get(i).Update();
		}
		
		//��������� ������ �� �����
		for(int i = 0; i < cont.enemies.size();i++) {
			Enemy e = cont.enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			for(int j = 0; j < cont.magic.size();j++) {
				Magic m = cont.magic.get(j);
				double mx = m.getX();
				double my = m.getY();
				
				double dx = ex - mx; 
				double dy = ey - my;
				double dist = Math.sqrt(dx*dx+dy*dy);
				if ((int)(dist) < e.getR() + m.getR()) {
					e.damage();
					cont.magic.remove(j);
					j--;
					boolean death = e.death();
			        if(death) {
			        	cont.enemies.remove(i);
			        i--;
			        cont.player.Mana += 3;
					break;
				}
			}
				
			}
		}
		
		//��������� ����� ������
		for(int i = 0; i < cont.enemies.size(); i++) {
			Enemy e = cont.enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			Player p = cont.player;
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
			for(int i = 0; i < cont.enemies.size(); i++) {
				Enemy e = cont.enemies.get(i);
				double ex = e.getX();
				double ey = e.getY();
				
				Player p = cont.player;
				double px = p.getX();
				double py = p.getY();
				
				double dx = ex - px; 
				double dy = ey - py;
				double dist = Math.sqrt(dx*dx+dy*dy);
				if ((int)(dist) <= (e.getR()+1) + (p.getR()+1)) {
					e.damage();
					boolean death = e.death();
			        if(death) {
			        	cont.enemies.remove(i);
			        i--;
			        cont.player.Mana += 3;
					break;}
		}
	   }
	  }
		//���������� ���������� � ������ �����������
		cont.spawn.Update();
		
		//���������� ��������� ����������
		cont.intface.Update();
		
		if(state == STATES.gameover) {
			cont.gameover.Update();
		}
		
	}
	
	
	//���������� ����������� ��������
	public void Render() {
		
		//���������� ����
		cont.background.draw(g);
		
		//��������� ������
		cont.player.draw(g);
		
		//��������� �����
		for(int i = 0; i < cont.magic.size(); i++) {
			cont.magic.get(i).draw(g);
		}
		
		//��������� ������
		for(int i = 0; i < cont.enemies.size();i++) {
			cont.enemies.get(i).draw(g);;
		}
		
		//�������� ���������� 
		cont.spawn.draw(g);
		
		
		//��������� ����������
		cont.intface.draw(g);
		
		if(state == STATES.gameover) {
			cont.gameover.draw(g);
		}
		
	}
	
	private void RPGdraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
		
	}
		
}


