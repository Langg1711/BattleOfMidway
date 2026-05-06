package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.PlayManager;

public class Dasher extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public boolean gameover = false;

	public Dasher() {
		setDefaultValues();
		
	}
	public void setDefaultValues() {
		codename = "Dasher";
		x = GamePanel.WIDTH;
		y = (new Random().nextInt(GamePanel.HEIGHT));
		if (y >= GamePanel.HEIGHT-10) {
			y = y - 10;
		}
		if (y <= 10) {
			y = y + 10;
		}
		speed = 6;
	}
	public void checkMovementCollision() {
		
		bulletCollision = false;
		planeCollision = false;
		ceilingCollision = false;
		
		if (x - speed <= 0) {
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
		hitbox = new int[]{x-5, y, x+115, y+40};
		
		checkMovementCollision();
		
		if( !bulletCollision && !planeCollision && !ceilingCollision) {			
			// Move Plane 
			x = x - speed;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		try {
			g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Dasher.png")),x,y, PlayManager.observer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
