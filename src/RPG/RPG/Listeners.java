package RPG;

import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {
	
	public Main main;
	

	
	public void keyPressed(KeyEvent k) {
		
		int key = k.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			Player.up = true;
		}
		
		if (key == KeyEvent.VK_S) {
			Player.down = true;
		}
		
		if (key == KeyEvent.VK_D) {
			Player.right = true;
		}
		
		if (key == KeyEvent.VK_A) {
			Player.left = true;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			Main.sword = true;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			Player.shift = true;
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			Main.state = Main.STATES.menu;
		}
		
		if(key == KeyEvent.VK_TAB) {
			Main.TAB = true;
		}
		
	}

	
	public void keyReleased(KeyEvent k) {
		
		int key = k.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			Player.up = false;
		}
		
		if (key == KeyEvent.VK_S) {
			Player.down = false;
		}
		
		if (key == KeyEvent.VK_D) {
			Player.right = false;
		}
		
		if (key == KeyEvent.VK_A) {
			Player.left = false;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			Main.sword = false;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			Player.shift = false;
		}
		
	}

	
	public void keyTyped(KeyEvent k) {
		
		
	}


	
	public void mouseDragged(MouseEvent m) {
		Main.MouseX = m.getX();
		Main.MouseY = m.getY();
	}


	
	public void mouseMoved(MouseEvent m) {
		Main.MouseX = m.getX();
		Main.MouseY = m.getY();
	}


	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}


	
	public void mousePressed(MouseEvent m) {
		if(m.getButton() == MouseEvent.BUTTON1) {
			Main.clic = true;
		}
	
		if(m.getButton() == MouseEvent.BUTTON3) {
			Cont.player.Explosion = true;	
		}
		
	}

	
	
	


	public void mouseReleased(MouseEvent m) {
		if(m.getButton() == MouseEvent.BUTTON1) {
			Main.clic = false;
	   }
		
		if(m.getButton() == MouseEvent.BUTTON3) {
			Cont.player.Explosion = false;
	   }
	}
}
