package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class ScoreBoard extends JLabel {

	private static final long serialVersionUID = 1L;
	private String label;
	
	ScoreBoard(String label) {
		super("0");
		this.label = label;
		setSize(100, 50);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(new Color(188, 175, 161, 250));
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
		g2d.setColor(new Color(255, 255, 255, 255));
		g2d.setFont(new Font("Arial", Font.BOLD, 10));
		int w = g2d.getFontMetrics().stringWidth(label);
		g2d.drawString(label, (getSize().width-w)/2, 15);
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		w = g2d.getFontMetrics().stringWidth(getText());
		g2d.drawString(getText(), (getSize().width-w)/2, 40);
	}
}