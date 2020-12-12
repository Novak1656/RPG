package RPG;

import  javax.swing.JFrame;

public class RPGexe {                                                   // Окно игры
  public static void main(String args[]) {                                  
	  JFrame display = new JFrame("RPGv1");
	  Main main = new Main();
	  display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              // Операция выхода
//	  display.setExtendedState(JFrame.MAXIMIZED_BOTH);                     // Размер окна игры
	  display.setResizable(true);                                          // изменение размера можно
//	  display.setUndecorated(true);                                        // Удаление верхней панели окна(fullscrean)
      display.setContentPane(main);
      display.pack();
      display.setLocationRelativeTo(display);
	  display.setVisible(true);                                            // Визуализация окна игры
	  main.start();
  } 
}
