package RPG;

import java.awt.*;

public class Enemy extends Abstract {
	
	private double x, y, HP;
	private int r;
	private Color color;
	private int type, rank;
	private double speed, dx, dy;
	
	public Enemy(int type, int rank) {
		this.type = type;
		this.rank = rank;
		
		switch(type) {
		     case(1):color = Color.GREEN;
		      switch(rank) {
		       case(1):
		    	   x = Math.random() * Main.WIDTH;
		           y = Math.random() * Main.HEIGHT; //if y = 0 -> emeny comming with up;
		           speed = 1;
		           r = 9;
		           HP = 2;
		           dx = Math.sin(Math.toRadians(Math.random()*360)) * speed;
		           dy = Math.cos(Math.toRadians(Math.random()*360)) * speed;	           
		     }
		}
	}
	public double getX() {return x;}
	public double getY() {return y;}
	public int getR() {return r;}
	
	public boolean death() {
		if(HP <= 0) {
			return true;
		}
		return false;
	}
	
	public void damage() {
		HP--;
	}
	
	public void Update(){
		x += dx;
		y += dy;
		if(x < 0 && dx < 0) dx = -dx;
		if(x > Main.WIDTH && dx > 0) dx = -dx;
		if(y < 0 && dy < 0) dy = -dy;
		if(y > Main.HEIGHT && dy > 0) dy = -dy;
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(x-r), (int)(y-r), 2*r , 2*r);
		g.setStroke(new BasicStroke(4));
		g.setColor(color.darker());
		g.drawOval((int)(x-r), (int)(y-r), 2*r , 2*r);
		g.setStroke(new BasicStroke(1));
	}

}

