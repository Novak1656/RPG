package RPG;

import java.awt.*;

public class Skills extends Abstract {
	
	private int buttonW, buttonH, trp;
	private Color color1, color2, color3;
	private String st1, st2, st3, ext;
	private double HP, Stamina, Mana;
	
	
	public Skills() {
		buttonW = 90;
		buttonH = 35;
		
		color1 = Color.GREEN;
		color2 = Color.PINK;
		color3 = Color.WHITE;
		
		st1 = "HP:";
		st2 = "MANA:";
		st3 = "STAMINA:";
		ext = "[X]";
		
		HP = Main.player.getMaxHP();
		Stamina = Main.player.getMaxStamina();
		Mana = Main.player.getMaxMana();
	}
	
	public void Update() {
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.drawRect(Main.WIDTH*1/3 - buttonW/2, Main.HEIGHT/2 - buttonH/2, buttonW, buttonH);
		g.setColor(new Color(255,255,255,trp));
		g.fillRect(Main.WIDTH / 2 - buttonW / 2, Main.HEIGHT / 2 - buttonH / 2, buttonW, buttonH);
		g.setStroke(new BasicStroke(1));
		g.setColor(color1);
		Font font1 = new Font("Arial",Font.BOLD,50);
		g.setFont(font1);
		long length1 = (int)g.getFontMetrics().getStringBounds(st1,g).getWidth();
		g.drawString(st1, Main.WIDTH / 2 - (int) length1 / 2, Main.HEIGHT / 4);
	}

}
