package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

public class Hornet extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public boolean gameover = false;
	String movement = "up";
	int counterBullet;

	public Hornet() {
		setDefaultValues();
		
	}
	public void setDefaultValues() {
		codename = "Hornet";
		x = GamePanel.WIDTH;
		y = (new Random().nextInt(GamePanel.HEIGHT));
		if (y >= GamePanel.HEIGHT-10) {
			y = y - 10;
		}
		if (y <= 10) {
			y = y + 10;
		}
		speed = 4;
	}
	public void checkMovementCollision() {
		
		bulletCollision = false;
		planeCollision = false;
		ceilingCollision = false;
		
		if (x - speed <= -100) {
			ceilingCollision = true;
		}
		for (int i = 0; i < PlayManager.playerBullet.size(); i++) {
			if (PlayManager.playerBullet.get(i).x >= hitbox[0] && (PlayManager.playerBullet.get(i).y >= hitbox[1] && PlayManager.playerBullet.get(i).y <= hitbox[3])) {
				bulletCollision = true;
			}
		}
		
	}
	public void update() {
		// Assign Hitbox
		hitbox = new int[]{x-5, y-5, x+120, y+40};
		
		checkMovementCollision();
		
		if( !bulletCollision && !planeCollision && !ceilingCollision) {
			
			// Fire a bullet
			if (counterBullet == 30) {
				counterBullet=0;
				PlayManager.enemyBullet.add(new Bullet(x, y+15, false));
			} else {
				counterBullet =counterBullet+1;
			}
			
			if (y-speed < 0 && y-speed<50) {
				movement = "down";
			}
			
			if (y+speed > GamePanel.HEIGHT-150 && y+speed>GamePanel.HEIGHT-100) {
				movement = "up";
			}
			
			if(movement == "up") {
				y = y - speed;
			}else {
				y = y + speed;
			}
			
			// Move Plane 
			x = x - speed;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Hornet.png")),x,y, PlayManager.observer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
