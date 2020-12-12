package RPG;

import java.awt.event.*;
import java.io.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener, Serializable {
	
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
		
		if(key == KeyEvent.VK_1) {
			main = new Main();
            try{
                FileOutputStream fos = new FileOutputStream("data.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(main);
                oos.close();
            } catch(Exception EXC) {
                EXC.printStackTrace();
            }
        }
        if(key == KeyEvent.VK_2) {
        	main = new Main();
            try {
                FileInputStream fis = new FileInputStream("data.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                main = (Main) ois.readObject();
                ois.close();
            } catch (Exception EXC) {
                EXC.printStackTrace();
            }
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
			Main.player.Explosion = true;	
		}
		
	}

	
	
	


	public void mouseReleased(MouseEvent m) {
		if(m.getButton() == MouseEvent.BUTTON1) {
			Main.clic = false;
	   }
		
		if(m.getButton() == MouseEvent.BUTTON3) {
			Main.player.Explosion = false;
	   }
	}
}
