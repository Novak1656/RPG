package RPG;

import java.awt.*; 

public class Magic extends Abstract {
	private double x, y, magicDX, magicDY, distX, distY, dist;
	private int r;
	private Color color1;
	private int speed;
	
	//�����������
	public Magic() {
		x = Cont.player.getX();
		y = Cont.player.getY();
		r = 8;
		speed = 7;
		
		color1 = Color.RED;

		distX = Main.MouseX - x;
		distY = Main.MouseY - y;
		dist = Math.sqrt(distX*distX + distY*distY);
		
		magicDX = (distX / dist) * speed;
		magicDY = (distY / dist) * speed;
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public int getR() {return r;}
	
	public boolean ClearList() {
		if(y < 0 || x < 0 || x > Main.WIDTH || y > Main.HEIGHT) {
			return true;
		}
		return false;
	}
	
	//���������� ���������� � �����
	public void Update() {
		y += magicDY;
		x += magicDX;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.fillOval((int)(x),(int)(y), r*2, r*2);
	}

}
