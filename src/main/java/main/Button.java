package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Button extends JButton  implements MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean onMouse = false;
	private boolean onClick = false;
	
	Button(String text) {
		super(text);
		setFocusable(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorder(null);
		setBorderPainted(false);
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(new Color(144, 123, 102, 255));
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
		g2d.setFont(new Font("Arial", Font.BOLD, 15));
		g2d.setColor(Color.white);
		String text = getText();
		int a = g2d.getFontMetrics().getAscent();
		int w = g2d.getFontMetrics().stringWidth(text);
		int h = g2d.getFontMetrics().getHeight();
		g2d.drawString(text, (getSize().width-w)/2, (getSize().height-h)/2+a);
		if (onMouse) {
			g2d.setColor(new Color(184, 163, 142, 120));
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
		}
		if (onClick) {
			g2d.setColor(new Color(104, 83, 62, 120));
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
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