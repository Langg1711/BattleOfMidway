package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		switch (GamePanel.pm.menu) {
		case 0:
			// Main Menu Input
			if ((e.getX() >= 439 && e.getX() <= 587) && (e.getY() >= 340 && e.getY() <= 380)) {
				GamePanel.pm.menu = 1;
			}
			if ((e.getX() >= 375 && e.getX() <= 649) && (e.getY() >= 436 && e.getY() <= 476)) {
//				System.out.println("2");
				GamePanel.pm.menu = 2;
			}
			if ((e.getX() >= 431 && e.getX() <= 595) && (e.getY() >= 541 && e.getY() <= 575)) {
//				System.out.println("3");
				GamePanel.pm.menu = 3;
			}
			break;
		case 1:
			if ((e.getX() >= 0 && e.getX() <= GamePanel.WIDTH) && (e.getY() >= 0 && e.getY() <= GamePanel.WIDTH)) {
//				System.out.println("3");
				KeyHandler.pausePressed =true;
			}
			break;
		default:
//			System.out.println("x "+e.getX());
//			System.out.println("y "+e.getY());
			GamePanel.pm.menu = 0;
			break;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
