package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Render extends JPanel {

	private static final long serialVersionUID = 1L;
	private int field[][];
	private boolean over;
	private boolean win;

	Render(int field[][]) {
		super();
		this.field = field;
		setSize(ResourceManager.getFieldImage().getWidth(), ResourceManager.getFieldImage().getHeight());
	}

	public void renderImage(int field[][], boolean over, boolean win) {
		this.field = field;
		this.over = over;
		this.win = win;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(ResourceManager.getFieldImage(), 0, 0, null);
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if (field[j][i] > 0) {
					g2d.drawImage(ResourceManager.getNumberImage(field[j][i]), ResourceManager.getCoordinate(i),
							ResourceManager.getCoordinate(j), null);
				}
			}
		}
		if (over) {
			g2d.setColor(new Color(235, 195, 20, 120));
			g2d.fillRect(0, 0, getSize().width, getSize().height);
			g2d.setColor(new Color(240, 90, 50, 255));
			g2d.setFont(new Font("Arial", Font.BOLD, 50));
			String text = "Game Over";
			int a = g2d.getFontMetrics().getAscent();
			int w = g2d.getFontMetrics().stringWidth(text);
			int h = g2d.getFontMetrics().getHeight();
			g2d.drawString(text, (getSize().width-w)/2, (getSize().height-h)/2+a);
		} else if (win) {
			g2d.setColor(new Color(235, 195, 20, 120));
			g2d.fillRect(0, 0, getSize().width, getSize().height);
			g2d.setColor(new Color(120, 110, 101, 255));
			g2d.setFont(new Font("Arial", Font.BOLD, 50));
			String text = "You Win";
			int a = g2d.getFontMetrics().getAscent();
			int w = g2d.getFontMetrics().stringWidth(text);
			int h = g2d.getFontMetrics().getHeight();
			g2d.drawString(text, (getSize().width-w)/2, (getSize().height-h)/2+a);
		}
	}
}