package RPG;

import  javax.swing.JFrame;

public class RPGexe {                                                   // ���� ����
  public static void main(String args[]) {                                  
	  JFrame display = new JFrame("RPGv1");
	  Main main = new Main();
	  display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              // �������� ������
//	  display.setExtendedState(JFrame.MAXIMIZED_BOTH);                     // ������ ���� ����
	  display.setResizable(true);                                          // ��������� ������� �����
//	  display.setUndecorated(true);                                        // �������� ������� ������ ����(fullscrean)
      display.setContentPane(main);
      display.pack();
      display.setLocationRelativeTo(display);
	  display.setVisible(true);                                            // ������������ ���� ����
	  main.start();
  } 
}
