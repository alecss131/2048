package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;

public class ResourceManager {
	private static int length;
	private static BufferedImage[] images;
	private static BufferedImage field;
	private static BufferedImage backGround;
	private static BufferedImage icon;
	private static int width;
	private static int height;
	private static int max;
	private static int numSize = 100;
	private static int space = 10;
	private static int rad = 5;
	private static Color colors[] = {
			new Color(239, 229, 219, 255),
			new Color(238, 225, 201, 255),
			new Color(242, 179, 122, 255),
			new Color(245, 150, 99, 255),
			new Color(245, 127, 96, 255),
			new Color(246, 94, 56, 255),
			new Color(238, 208, 115, 255),
			new Color(238, 205, 97, 255),
			new Color(238, 201, 79, 255),
			new Color(238, 198, 61, 255),
			new Color(237, 197, 50, 255)};
	
	public static void init() {
		init(4, 4, 8192);
	}
	
	public static void init(int maxNumber) {
		init(4, 4, maxNumber);
	}
	
	public static void init(int height, int width) {
		init(height, width, 8192);
	}
	
	public static void init(int height, int width, int maxNumber) {
		ResourceManager.height = height;
		ResourceManager.width = width;
		max = maxNumber;
		length = log2(maxNumber);
		images = new BufferedImage[length];
		createGameField();
		createNubersImages();
		createBackGround();
		createIcon();
	}
	
	
	private static int log2(int in) {
		return Integer.toBinaryString(in).length()-1;
	}
	
	public static BufferedImage getFieldImage() {
		return field;
	}
	
	public static ImageIcon getBackGroud() {
		return new ImageIcon(backGround);
	}
	
	public static Image getIcon() {
		return icon;
	}
	
	public static BufferedImage getNumberImage(int number) throws NumberFormatException {
		if (number > max) {
			throw new NumberFormatException("Out of range number exceprion");
		}
		return images[log2(number)-1];
	}
	
	public static int getCoordinate(int i) {
		return (i+1)*space+i*numSize;
	}
	
	private static void createGameField() {
		int fieldWidth = numSize * width + (width + 1) * space;
		int fieldHeight = numSize * height + (height + 1) * space;
		field = new BufferedImage(fieldWidth, fieldHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) field.getGraphics();
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, fieldWidth, fieldHeight);
		g2d.setColor(new Color(188, 175, 161, 255));
		g2d.fillRoundRect(0, 0, fieldWidth, fieldHeight, 2*rad, 2*rad);
		g2d.setColor(new Color(206, 194, 181, 255));
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				g2d.fillRoundRect(getCoordinate(i), getCoordinate(j), numSize, numSize, rad, rad);
			}
		}
	}
	
	private static void createNubersImages() {
		int fontSize = 50;
		String text;
		int w;
		int h;
		int a;
		for (int i=0; i<length; i++) {
			images[i] = new BufferedImage(numSize, numSize, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) images[i].getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(new Color(255, 255, 255, 0));
			g2d.fillRect(0, 0, numSize, numSize);
			if (i >= colors.length) {
				g2d.setColor(new Color(1, new Random().nextInt(256), new Random().nextInt(256), 
						new Random().nextInt(256)));
			} else {
				g2d.setColor(colors[i]);
			}
			g2d.fillRoundRect(0, 0, numSize, numSize, rad, rad);
			if (i<2) {
				g2d.setColor(new Color(120, 110, 101, 255));
			} else {
				g2d.setColor(new Color(249, 246, 242, 255));
			}
			if (i>5 && i<9) {
				fontSize = 45;
			} else if (i>8 && i<12) {
				fontSize = 35;
			} else if (i>11){
				fontSize = 30;
			}
			g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
			text = ((int)Math.pow(2, i+1))+"";
			a = g2d.getFontMetrics().getAscent();
			w = g2d.getFontMetrics().stringWidth(text);
			h = g2d.getFontMetrics().getHeight();
			g2d.drawString(text, (numSize-w)/2, (numSize-h)/2+a);
		}
	}
	
	private static void createBackGround() {
		int w = 460;
		int h = 605;
		int r = 15;
		backGround = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) backGround.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, w, h);
		g2d.setColor(new Color(250, 248, 255, 250));
		g2d.fillRoundRect(0, 0, w, h, r, r);
		g2d.setColor(new Color(120, 110, 101, 250));
		g2d.setFont(new Font("Arial", Font.BOLD, 80));
		g2d.drawString("2048", 20, 80);
		g2d.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
		String text = "Join the numbers and get to the";
		g2d.drawString(text, 20, 120);
		int tw = g2d.getFontMetrics().stringWidth(text);
		g2d.setFont(new Font("Arial", Font.BOLD, 15));
		g2d.drawString("2048 tile!", tw+23, 120);
	}
	
	private static void createIcon() {
		icon = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) icon.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(237, 197, 0));
		g2d.fillRect(0, 0, 128, 128);
		g2d.setColor(new Color(255, 255, 255));
		g2d.setFont(new Font("Arial", Font.BOLD, 40));
		String text = "2048";
		int a = g2d.getFontMetrics().getAscent();
		int w = g2d.getFontMetrics().stringWidth(text);
		int h = g2d.getFontMetrics().getHeight();
		g2d.drawString(text, (128-w)/2, (128-h)/2+a);
	}
}