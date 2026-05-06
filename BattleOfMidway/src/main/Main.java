package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Battle Of Midway");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		// Add GamePanel to window
		GamePanel gp = new GamePanel();
		window.add(gp);
//		JPanel panel = new JPanel();
//        JTextField textField = new JTextField(20); // Create a text field with 20 columns
//        panel.add(textField);
//
//        window.add(panel);
//        window.setVisible(true);
		window.pack();
	
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.initiateApp();
	}
}
