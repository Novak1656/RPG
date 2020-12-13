package RPG;

import java.awt.*;

public class Player extends Abstract {
	
	private double x, y; 
	public static double HP, Stamina, Mana;
	private double dx, dy;
	private int r;
	private Color color1, color2;

	
	public static boolean up, down, right, left, Explosion, shift;
	private int step;
	
	//Конструктор
	public Player() {
		x = Main.WIDTH/2;
		y = Main.HEIGHT/2;
		r = 10;
		dx = 0;
		dy = 0;
		color1 = Color.LIGHT_GRAY;
		HP = 100;
		Stamina = 100;
		Mana = 100;
		up = false;
		down = false;
		right = false;
		left = false;
		Explosion = false;
		shift = false;
		
		step = 2;
		

	}
	
	//Геттеры
	public double getX() {return x;}
	
	public double getY() {return y;}
	
	public int getR() {return r;}
	
	public static double getHP() {return HP;}
	
	public static double getStamina() {return Stamina;}
	
	public static double getMana() {return Mana;}
	
	
	//Обновление информации о персонаже
	public boolean death() {
		if(HP <= 0) {
			return true;
		}
		return false;
	}
	
	public void damage() {
		HP--;
	}
	
	public void Update() {
		if (up && y > r) {
			dy = -step;
			if(shift == false && Stamina != 100) {
			       Stamina += 1;
			}
		}
		
		if (down && y < Main.HEIGHT - r) {
			dy = step;
			if(shift == false && Stamina != 100) {
				   Stamina += 1;
			}
		}
		
		if (left && x > r) {
			dx = -step;
			if(shift == false && Stamina != 100) {
				   Stamina += 1;
			}
		}
		
		if (right && x < Main.WIDTH - r) {
			dx = step;
			if(shift == false && Stamina != 100) {
				   Stamina += 1;
			}
		}
		
		if (up && left || up && right || down && left || down && right) {
			dy = dy * Math.sin(Math.toRadians(45));
			dx = dx * Math.cos(Math.toRadians(45));
		}
		
		y += dy;
		x += dx;
				
		dy = 0;
		dx = 0;
		
		if(Explosion && Mana >= 5) {
			Cont.magic.add(new Magic());
			Mana = Mana - 5;
		}else Explosion = false;
		
		if(shift == true && Stamina >= 1) {
			step = 5;
			Stamina--;
		}else{ shift = false;}
		
		if(shift == false) {
			step = 2;
		}	
		
	}
	
	public void draw(Graphics2D g) {
		if(HP == 0) {
			color1 = Color.BLACK;
		}	
		g.setColor(color1);
		g.fillOval((int)(x-r),(int)(y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(4));
		g.setColor(color1.darker());
		g.drawOval((int)(x-r),(int)(y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(2));
	}

}
