package main;


import java.util.Comparator;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	final int FPS = 60;
	public static int level;
	Thread gameThread;
	public static PlayManager pm;
	BufferedImage bgImg;
	
	public GamePanel() {
		try{
	        bgImg = ImageIO.read(getClass().getResource("/assets/Menu.png"));
        }catch(IOException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
		// Panel Setup
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		
		// Adding Key Listener
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
		this.addMouseListener(new MouseHandler());
		
		pm = new PlayManager();
	}
	
	public void initiateApp() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		//Game loop
		double drawInterval = 1000000000/FPS;
		double delta = 10;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	private void update() {
		if (KeyHandler.pausePressed == false && pm.gameover == false ) {	
			pm.update();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Toolkit.getDefaultToolkit().sync();
		try {
			switch (pm.menu) {
				case 0:
					g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Menu.png")),0,0,pm.observer);
					break;
				case 1:
					pm.draw(g2);
					break;
				case 2:
					g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Highscore.png")),0,0,pm.observer);
					int y = 335;
					Collections.sort(pm.scoreBoard, new Comparator<Integer>() {
			            @Override
			            public int compare(Integer o1, Integer o2) {
			                return o2.compareTo(o1);
			            }
			        });
					if(pm.scoreBoard.size() > 0) {
						for (int i = 0; i < (pm.scoreBoard.size() <5 ? pm.scoreBoard.size() : 5); i++) {
							g2.setFont(new Font("Arial", Font.BOLD, 45));
							g2.setColor(Color.DARK_GRAY);
							g2.drawString("Player", 92, y);
							g2.drawString(pm.scoreBoard.get(i).toString(), 722, y);
							g2.setFont(new Font("Arial", Font.BOLD, 45));
							g2.setColor(Color.white);
							g2.drawString("Player", 90, y);
							g2.drawString(pm.scoreBoard.get(i).toString(), 720, y);
							y = y + 50;
						}
					}
					break;
				case 3:
					g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/Credit.png")),0,0,pm.observer);
					g2.setFont(new Font("Arial", Font.BOLD, 35));
					g2.setColor(Color.DARK_GRAY);
					g2.drawString("M. Irfan Rauf Ismail Putra", 92, 335);
					g2.drawString("M. Gilang Ramadhan", 92, 435);
					g2.drawString("Salsabilla Herliana Putri", 92, 535);
					g2.drawString("2250081087", 722, 335);
					g2.drawString("2250081100", 722, 435);
					g2.drawString("2250081102", 722, 535);
					g2.setFont(new Font("Arial", Font.BOLD, 35));
					g2.setColor(Color.white);
					g2.drawString("M. Irfan Rauf Ismail Putra", 90, 335);
					g2.drawString("M. Gilang Ramadhan", 90, 435);
					g2.drawString("Salsabilla Herliana Putri", 90, 535);
					g2.drawString("2250081087", 720, 335);
					g2.drawString("2250081100", 720, 435);
					g2.drawString("2250081102", 720, 535);
					break;
				default:
					break;
			}
		Toolkit.getDefaultToolkit().sync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
