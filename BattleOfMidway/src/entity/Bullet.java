package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

public class Bullet extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public boolean isPlayerBullet;
	public Bullet(int x, int y, boolean isPlayer) {
		this.x = x;
		this.y = y;
		this.speed = 6;
		isPlayerBullet = isPlayer;
	}
	
public void checkMovementCollision() {
		planeCollision = false;
		ceilingCollision = false;
		
		if (x - speed < 0) {
			ceilingCollision = true;
		}
		if (x + speed > gp.WIDTH-50) {
			ceilingCollision = true;
		}
		
					
	}
public void update() {
	
		checkMovementCollision();
		
		if( !planeCollision && !ceilingCollision) {			
			// Direction Bullet
			if (isPlayerBullet) {
				x = x + speed;
			} else {
				x = x - speed;
			}
		} 
	}
	
	public void draw(Graphics2D g2) {
		try {
			if (isPlayerBullet) {				
				g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Bullet.png")),x,y, PlayManager.observer);
			} else {
				g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/EnemyBullet.png")),x,y, PlayManager.observer);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
