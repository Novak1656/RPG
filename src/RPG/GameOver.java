package RPG;

import java.awt.*;

public class GameOver extends Abstract {
	private int buttonW;
	private int buttonH;
	private Color color, colortext, colortitle;
	private String str, title;
	private int trp;
	
	public GameOver() {
		buttonW = 300;
		buttonH = 85;
		color = Color.WHITE;
		colortext = Color.GREEN;
		colortitle = Color.RED;
		str = "Exit";
		title = "<GAME OVER>";
	}
	
	public void Update() {
		if(Main.MouseX > Main.WIDTH / 2 - buttonW / 2 && Main.MouseX < Main.WIDTH / 2 + buttonW / 2 
				&& Main.MouseY > Main.HEIGHT / 2 - buttonH / 2 && Main.MouseY < Main.HEIGHT / 2 + buttonH / 2) {
			trp = 50;
			if(Main.clic) {System.exit(0);}
				} else trp = 0; 
	}
	
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawRect(Main.WIDTH / 2 - buttonW / 2, Main.HEIGHT / 2 - buttonH / 2, buttonW, buttonH);
		g.setColor(new Color(255,255,255,trp));
		g.fillRect(Main.WIDTH / 2 - buttonW / 2, Main.HEIGHT / 2 - buttonH / 2, buttonW, buttonH);
		g.setStroke(new BasicStroke(1));
		
		g.setColor(colortitle);
		Font font1 = new Font("Arial",Font.BOLD,50);
		g.setFont(font1);
		long length1 = (int)g.getFontMetrics().getStringBounds(title,g).getWidth();
		g.drawString(title, Main.WIDTH / 2 - (int) length1 / 2, Main.HEIGHT / 4);
		
		g.setColor(colortext);
		Font font = new Font("Arial",Font.ITALIC,50);
		g.setFont(font);
		long length = (int)g.getFontMetrics().getStringBounds(str,g).getWidth();
		g.drawString(str, Main.WIDTH / 2 - (int) length / 2, Main.HEIGHT/2 + buttonH/4);
	}

}


