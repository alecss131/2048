package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class CircleButton extends JButton  implements MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean onMouse = false;
	private boolean onClick = false;
	private int s = 12;
	private Color color;
	private String type;
	
	CircleButton(String type) {
		setFocusable(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorder(null);
		setBorderPainted(false);
		addMouseListener(this);
		setSize(s, s);
		this.type = type;
		switch (type) {
		case "x": {
			color = new Color(246, 94, 56, 255);
			break;
		}
		case "-": {
			color = new Color(250, 248, 0, 255);
			break;
		}
		default: {
			color = new Color(0, 240, 0, 255);
		}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, s, s);
		g2d.setColor(color);
		g2d.fillOval(0, 0, s, s);
		if (onMouse) {
			g2d.setColor(new Color(50, 50, 50, 100));
			if (type.equals("-")) {
				g2d.setFont(new Font("Arial", Font.BOLD, 15));
			} 
			g2d.drawString(type, 3, 10);
		}
		if (onClick) {
			g2d.setColor(new Color(104, 83, 62, 120));
			g2d.fillOval(0, 0, s, s);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		onClick = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		onClick = false;
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		onMouse = true;
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		onMouse = false;
		repaint();
	}
}