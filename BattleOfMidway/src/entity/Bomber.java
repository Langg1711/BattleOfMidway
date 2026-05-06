package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

public class Bomber extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public boolean gameover = false;
	String movement = "up";
	int counterBullet;

	public Bomber() {
		setDefaultValues();
		
	}
	public void setDefaultValues() {
		codename = "Bomber";
		x = GamePanel.WIDTH;
		y = (new Random().nextInt(GamePanel.HEIGHT));
		if (y >= GamePanel.HEIGHT-10) {
			y = y - 10;
		}
		if (y <= 10) {
			y = y + 10;
		}
		speed = 1;
		hitpoint = 5;
	}
	public void checkMovementCollision() {
		
		bulletCollision = false;
		planeCollision = false;
		ceilingCollision = false;
		
		if (x - speed <= (0-400)) {
			ceilingCollision = true;
		}
		for (int i = 0; i < PlayManager.playerBullet.size(); i++) {
			if (PlayManager.playerBullet.get(i).x >= hitbox[0] && (PlayManager.playerBullet.get(i).y >= hitbox[1] && PlayManager.playerBullet.get(i).y <= hitbox[3])) {
				if (hitpoint-1==0) {
					bulletCollision = true;
					PlayManager.playerBullet.remove(i);
				}else {		
					hitpoint = hitpoint - 1;
					PlayManager.playerBullet.remove(i);
					bulletCollision = false;
				}
			}
		}
		
	}
	public void update() {
		// Assign Hitbox
		hitbox = new int[]{x, y, x+340, y+100};
		
		checkMovementCollision();
		
		if( !bulletCollision && !planeCollision && !ceilingCollision) {
			
			// Fire a bullet
			if (counterBullet == 120) {
				counterBullet=0;
				PlayManager.enemyBullet.add(new Bullet(x, y-20, false));
				PlayManager.enemyBullet.add(new Bullet(x, y+10, false));
				PlayManager.enemyBullet.add(new Bullet(x, y+40, false));
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
			g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Bomber.png")),x,y, PlayManager.observer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
