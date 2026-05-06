package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity{
	public int x, y;
	public int speed;
	public int hitpoint;
	public String codename;
	public boolean bulletCollision, planeCollision,ceilingCollision;
	public int[] hitbox;
	
	public Entity() {
	}
	public void update() {
	}
	public void draw(Graphics2D g2) {
	}
}
