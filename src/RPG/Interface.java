package RPG;
import java.awt.*;

public class Interface extends Abstract {
	private double x, y, HP, Stamina, Mana;
	public static Player pStats;
	public String stat1, stat2, stat3, str1, str2, str3; 
	
	public Interface() {
		pStats = new Player();
		x = 0;
		y = 0;
		HP = pStats.getHP();
		Stamina = pStats.getStamina();
		Mana = pStats.getMana();
		stat1 = "[" + "HP : " + HP + "]"; 
		stat2 = "[" + "Stamina : " + Stamina + "]";
		stat3 = "[" + "Mana : " + Mana + "]";

	}
	
	public void Update() {
		HP = pStats.getHP();
		Stamina = pStats.getStamina();
		Mana = pStats.getMana();
		stat1 = "[" + "HP : " + HP + "]"; 
		stat2 = "[" + "STAMINA : " + Stamina + "]";
		stat3 = "[" + "MANA : " + Mana + "]";
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		Font font = new Font("Arial",Font.ITALIC,20);
		g.setFont(font);
		str1 = stat1;
		g.drawString(stat1, (int)x+5, (int)y+20);
		
		g.setColor(Color.blue.brighter());
		str3 = stat3;
		g.drawString(stat3, (int)x+5, (int)y + 45);
		
		g.setColor(Color.WHITE);
		str2 = stat2;
		g.drawString(stat2, (int)x+5, (int)y + 70);
	} 

}
