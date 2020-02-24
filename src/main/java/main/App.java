package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
	private JFrame wnd = new JFrame("2048 Game");
	private JPanel field;
	private GameLogic game;
	private JButton newGame = new Button("New Game");
	private JButton exit = new CircleButton("x");
	private JButton minimize = new CircleButton("-");
	private JButton play = new Button("Continue");
	private int bestScore = 0;
	private JLabel bScore = new ScoreBoard("BEST");
	private JLabel score = new ScoreBoard("SCORE");
	private Point mouseDownCompCoords = null;
	
	public static void main(String[] args) {
		new App();
	}
	
	App() {
		ResourceManager.init();
		game = new GameLogic();
		field = game.getField();
		newGame.setBounds(320, 102, 100, 25);
		exit.setLocation(5, 5);
		exit.addActionListener(this);
		exit.setActionCommand("Exit");
		minimize.setLocation(20, 5);
		minimize.addActionListener(this);
		minimize.setActionCommand("Minimize");
		bScore.setLocation(355, 20);
		score.setLocation(250, 20);
		field.setLocation(5, 150);
		newGame.setActionCommand("NewGame");
		newGame.addActionListener(this);
		play.setBounds(180, 270, 90, 25);
		play.setFocusable(false);
		play.setActionCommand("Continue");
		play.addActionListener(this);
		play.setVisible(false);
		field.setLayout(null);
		field.add(play);
		wnd.setUndecorated(true);
		wnd.setContentPane(new JLabel(ResourceManager.getBackGroud()));
		wnd.setBackground(new Color(0, 0, 0, 0));
		wnd.add(field);
		wnd.add(newGame);
		wnd.add(bScore);
		wnd.add(score);
		wnd.add(exit);
		wnd.add(minimize);
		wnd.setLayout(null);
		wnd.addKeyListener(this);
		wnd.addMouseListener(this);
		wnd.setOpacity(1f);
		wnd.addMouseMotionListener(this);
		wnd.setResizable(false);
		wnd.setBounds(200, 200, 460, 605);
		wnd.setIconImage(ResourceManager.getIcon());
		wnd.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (!game.isGameOver() && !game.isWin()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: {
				game.moveLeft();
				break;
			}
			case KeyEvent.VK_RIGHT: {
				game.moveRight();
				break;
			}
			case KeyEvent.VK_UP: {
				game.moveUp();
				break;
			}
			case KeyEvent.VK_DOWN: {
				game.moveDown();
				break;
			}
			}
			score.setText("" + game.getScore());
			play.setVisible(game.isWin());
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "NewGame" : {
			if (game.getScore()>bestScore && (game.isGameOver() || game.isWin())) {
				bestScore = game.getScore();
				bScore.setText("" + bestScore);
			}
			score.setText("0");
			game.newGame();
			break;
		}
		case "Continue" : {
			game.continueGame();
			play.setVisible(game.isWin());
			break;
		}
		case "Minimize" : {
			wnd.setState(JFrame.ICONIFIED);
			break;
		}
		case "Exit" : {
			System.exit(0);
		}
		}
	}

	public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        wnd.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        wnd.setOpacity(0.5f);
    }

	public void mouseMoved(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

	public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
        wnd.setOpacity(1f);
    }

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}