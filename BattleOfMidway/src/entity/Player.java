package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public boolean gameover = false;
	public int[] hitbox;

	public Player() {
		setDefaultValues();
		
	}
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}
	public void checkMovementCollision() {
		
		bulletCollision = false;
		planeCollision = false;
		ceilingCollision = false;
		
		for (int i = 0; i < PlayManager.enemyBullet.size(); i++) {
			if ((PlayManager.enemyBullet.get(i).x >= hitbox[0] && PlayManager.enemyBullet.get(i).x <= hitbox[2]) && (PlayManager.enemyBullet.get(i).y >= hitbox[1] && PlayManager.enemyBullet.get(i).y <= hitbox[3])) {
				bulletCollision = true;
			}
		}
		for (int i = 0; i < PlayManager.enemyList.size(); i++) {
			if ((PlayManager.enemyList.get(i).x > hitbox[0] && PlayManager.enemyList.get(i).x < hitbox[2]) && (PlayManager.enemyList.get(i).y >= hitbox[1] && PlayManager.enemyList.get(i).y <= hitbox[3])) {
				planeCollision = true;
			}
		}
		
		if (x - speed < 0 && KeyHandler.leftPressed ) {
			ceilingCollision = true;
		}
		if (y - speed < 0 && KeyHandler.upPressed) {
			ceilingCollision = true;
		}
		if (x + speed > 900 && KeyHandler.rightPressed) {
			ceilingCollision = true;
		}
		if (y + speed > 720 && KeyHandler.downPressed) {
			ceilingCollision = true;
		}
		
					
	}
	public void update() {
		// Assign Hitbox
		hitbox = new int[]{x, y, x+122, y+40};
	
		checkMovementCollision();
		
		if( !bulletCollision && !planeCollision && !ceilingCollision) {			
			// Move Plane 
			if (KeyHandler.upPressed) {
				y = y-speed;
			}			
			if (KeyHandler.downPressed) {
				y = y+speed;
			}
			if (KeyHandler.leftPressed) {
				x = x - speed;
			}
			if (KeyHandler.rightPressed) {
				x = x + speed;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/PlayerAircraft.png")),x,y, PlayManager.observer);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
