package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Bomber;
import entity.Bullet;
import entity.Dasher;
import entity.Entity;
import entity.Hornet;
import entity.Player;

public class PlayManager{
	
	// Entity
	Player player;
	int bulletCounter = 0;
	int entityCounter = 2;
	int timeCounter = 0;
	public static ArrayList<Entity> enemyList = new ArrayList<>();
	public static ArrayList<Bullet> playerBullet = new ArrayList<>();
	public static ArrayList<Bullet> enemyBullet = new ArrayList<>();

	// Peripheral
	public static int menu = 0; // 0 Main Menu, 1 Gameplay, 2 Highscore, 3 Credit
	public static boolean gameover = false;
	public ClassLoader cldr = this.getClass().getClassLoader();
	public static ImageObserver observer;
	int gameoverCounter = 700;

	// Score
	int scores = 0;
	public ArrayList<Integer> scoreBoard = new ArrayList<>();

	public PlayManager() {
		player = new Player();
	}
	
	public void newGame() {
		menu = 0;
		scores = 0;
		timeCounter = 0;
		gameoverCounter = 700;
		gameover = false;
		player = new Player();
		enemyList = new ArrayList<>();
		enemyBullet = new ArrayList<>();
		playerBullet = new ArrayList<>();
	}

	public void update() {
		timeCounter++;
		int counter = 1;
		player.update();
		if (player.bulletCollision || player.planeCollision) {
			gameover = true;
		}
		for (Bullet bullet : playerBullet) {
		    bullet.update();
		    counter++;
		}
		if(enemyBullet.size()>0) {			
			for (Bullet bullet : enemyBullet) {
				bullet.update();
			}
		}
		for (Entity enemy : enemyList) {
		    enemy.update();
		}
	}

	private void checkDelete() {
		// Check is any Bullet to be deleted
		for (int i = 0; i < playerBullet.size(); i++) {
			if (playerBullet.get(i).ceilingCollision) {
				playerBullet.remove(i);
			}
		}
		if(enemyBullet.size()>0) {
			// Check is any Enemy Bullet to be deleted
			for (int i = 0; i < enemyBullet.size(); i++) {
				if (enemyBullet.get(i).ceilingCollision) {
					enemyBullet.remove(i);
				}
			}
		}
		// Check is any Enemy to be deleted and set score
		for (int i = 0; i < enemyList.size(); i++) {			
			if (enemyList.get(i).ceilingCollision || enemyList.get(i).bulletCollision) {
				switch (enemyList.get(i).codename) {
				case "Dasher":
					scores = scores + 10;
					break;
				case "Hornet":
					scores = scores + 30;
					break;
				case "Bomber":
					scores = scores + 100;
					break;
				default:
					break;
				}
				enemyList.remove(i);
			}
		}
	}
//
	public void draw(Graphics2D g2) throws IOException {
		if(gameover == false) {
			
			
			// Create Background
			g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/gameplay_bg.png")),0,0,observer);
			
			// Check is Player not null
			if (player != null) {				
				player.draw(g2);
			}

			checkDelete();
			
			// Check is bullet to be fired automatically
			if (bulletCounter == 3) {				
				Bullet tempPlayerBullet = new Bullet(player.x+120, player.y+15, true);
				playerBullet.add(tempPlayerBullet);
				bulletCounter = 0;
			} else {
				bulletCounter++;
			}
			
			// Check is Enemies entity are ready to be spawned
			if(timeCounter % 2 == 0 && enemyList.size() <= entityCounter) {
				int randEnemySeed = new Random().nextInt(100);
				System.out.println(timeCounter);
				Entity tempEnemy = null;
				
				if (randEnemySeed>=0 && randEnemySeed<=50) {
					tempEnemy = new Dasher();					
				}else
				if ((randEnemySeed>50 && randEnemySeed<=90) && timeCounter > 6000) {
					tempEnemy = new Hornet();
				}else
				if ((randEnemySeed>90 && randEnemySeed<=100) && timeCounter > 12000) {
					tempEnemy = new Bomber();
				}else {
					tempEnemy = new Dasher();
				}
				enemyList.add(tempEnemy);
			}
			
			// Draw Bullet
			for (int i = 0; i < playerBullet.size(); i++) {
				playerBullet.get(i).draw(g2);
			}
			if(enemyBullet.size()>0) {
				// Draw Enemy Bullet
				for (int i = 0; i < enemyBullet.size(); i++) {
					enemyBullet.get(i).draw(g2);
				}
			}
			
			// Draw Enemy
			for (int i = 0; i < enemyList.size(); i++) {
				enemyList.get(i).draw(g2);
			}
			
			// Check is paused?
			if (KeyHandler.pausePressed == true) {
				g2.setFont(new Font("Arial", Font.BOLD, 75));
				g2.setColor(Color.black);
				g2.drawString("PAUSED", 358, 402);
				g2.setFont(new Font("Arial", Font.BOLD, 75));
				g2.setColor(Color.white);
				g2.drawString("PAUSED", 355, 400);
			}
			
			// Draw Scores
			g2.setFont(new Font("Arial", Font.BOLD, 45));
			g2.setColor(Color.DARK_GRAY);
	        g2.drawString(String.valueOf(scores), GamePanel.WIDTH -158, 64);
	        g2.setFont(new Font("Arial", Font.BOLD, 45));
			g2.setColor(Color.white);
			g2.drawString(String.valueOf(scores), GamePanel.WIDTH - 160, 62);
		}else {
			scoreBoard.add(scores);
			newGame();			
		}

	}
}
