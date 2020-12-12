package RPG;

import java.awt.*;


public class RPGback extends Abstract{
	//Локация
	private Color location;

	//Конструктор
	public RPGback() {
		location = Color.BLACK;
	}
	
	//Функции
    public void Update() {
		
	}
    
    public void draw(Graphics2D g) {
    	g.setColor(location);
    	g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
    }
	

}
