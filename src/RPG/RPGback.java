package RPG;

import java.awt.*;


public class RPGback extends Abstract{
	//�������
	private Color location;

	//�����������
	public RPGback() {
		location = Color.BLACK;
	}
	
	//�������
    public void Update() {
		
	}
    
    public void draw(Graphics2D g) {
    	g.setColor(location);
    	g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
    }
	

}
