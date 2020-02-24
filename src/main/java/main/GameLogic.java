package main;

import java.util.Random;

public class GameLogic {
	private int field[][];
	private int score;
	private boolean over;
	private int width;
	private int height;
	private int field2[][];
	private boolean win;
	private boolean cont;
	private Render render;
	
	GameLogic() {
		this(4, 4);
	}
	
	GameLogic(int height, int width) {
		this.height = height;
		this.width = width;
		field = new int[height][width];
		render = new Render(field);
		addNumber();
		addNumber();
	}
	
	public Render getField() {
		return render;
	}
	
	public boolean isGameOver() {
		return over;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isWin() {
		return win;
	}
	
	public void moveLeft() {
		field2 = copy2dArray(field);
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				for (int k=0; k<width-1; k++) {
					if (field[i][k]==0) {
						field[i][k] = field[i][k+1];
						field[i][k+1] = 0;
					}
				}
			}
			for (int j=1; j<width; j++) {
				if (field[i][j-1]==field[i][j]) {
					field[i][j-1] = field[i][j-1] + field[i][j];
					score += field[i][j-1];
					field[i][j] = 0;
				}
			}
			for (int j=0; j<width; j++) {
				for (int k=0; k<width-1; k++) {
					if (field[i][k]==0) {
						field[i][k] = field[i][k+1];
						field[i][k+1] = 0;
					}
				}
			}
		}
		endMove();
	}
	
	public void moveRight() {
		field2 = copy2dArray(field);
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				for (int k=width-1; k>0; k--) {
					if (field[i][k]==0) {
						field[i][k] = field[i][k-1];
						field[i][k-1] = 0;
					}
				}
			}
			for (int j=width-1; j>0; j--) {
				if (field[i][j-1]==field[i][j]) {
					field[i][j] = field[i][j-1] + field[i][j];
					score += field[i][j];
					field[i][j-1] = 0;
				}
			}
			for (int j=0; j<width; j++) {
				for (int k=width-1; k>0; k--) {
					if (field[i][k]==0) {
						field[i][k] = field[i][k-1];
						field[i][k-1] = 0;
					}
				}
			}
		}
		endMove();
	}
	
	public void moveUp() {
		field2 = copy2dArray(field);
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				for (int k=0; k<width-1; k++) {
					if (field[k][i]==0) {
						field[k][i] = field[k+1][i];
						field[k+1][i] = 0;
					}
				}
			}
			for (int j=1; j<width; j++) {
				if (field[j-1][i]==field[j][i]) {
					field[j-1][i] = field[j-1][i] + field[j][i];
					score += field[j-1][i];
					field[j][i] = 0;
				}
			}
			for (int j=0; j<width; j++) {
				for (int k=0; k<width-1; k++) {
					if (field[k][i]==0) {
						field[k][i] = field[k+1][i];
						field[k+1][i] = 0;
					}
				}
			}
		}
		endMove();
	}
	
	public void moveDown() {
		field2 = copy2dArray(field);
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				for (int k=width-1; k>0; k--) {
					if (field[k][i]==0) {
						field[k][i] = field[k-1][i];
						field[k-1][i] = 0;
					}
				}
			}
			for (int j=width-1; j>0; j--) {
				if (field[j-1][i]==field[j][i]) {
					field[j][i] = field[j-1][i] + field[j][i];
					score += field[j][i];
					field[j-1][i] = 0;
				}
			}
			for (int j=0; j<width; j++) {
				for (int k=width-1; k>0; k--) {
					if (field[k][i]==0) {
						field[k][i] = field[k-1][i];
						field[k-1][i] = 0;
					}
				}
			}
		}
		endMove();
	}
	
	public void newGame() {
		score = 0;
		field = new int[height][width];
		win = false;
		over = false;
		cont = false;
		addNumber();
		addNumber();
		render.renderImage(field, over, win);
	}
	
	private void endMove() {
		if (isChanged()) {
			checkWin();
			addNumber();
			checkGameOver();
			render.renderImage(field, over, win);
		}
	}
	
	public void continueGame() {
		win = false;
		addNumber();
		checkGameOver();
		render.renderImage(field, over, win);
	}
	
	private void addNumber() {
		if (!over && hasEmptyCells() && !win) {
			int number = getNumber();
			int x, y;
			do {
			x = new Random().nextInt(height);
			y = new Random().nextInt(width);
			if (field[x][y] == 0) {
				field[x][y] = number;
				break;
			}
			} while (field[x][y] != 0);
		}
	}
	
	private int getNumber() {
		switch (new Random().nextInt(100)%4) {
		case 0 : {
			return 2;
		}
		case 1 : { 
			return 4;
		}
		case 2 : {
			return 2;
		}
		case 3 : {
			return 2;
		}
		default :
			return 8;
		}
	}
	
	private void checkGameOver() {
		if (!hasEmptyCells()) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width - 1; j++) {
					if ((field[i][j] == field[i][j + 1]) || (field[j][i] == field[j + 1][i])) {
						return;
					}
				}
			}
			over = true;
		}
	}
	
	private boolean hasEmptyCells() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (field[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int[][] copy2dArray(int[][] in) {
		int out[][] = new int[height][width];
		for (int i=0; i<height; i++) {
			out[i] = in[i].clone();
		}
		return out;
	}
	
	private boolean isChanged() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (field[i][j] != field2[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void checkWin() {
		if (!cont) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (field[i][j] == 2048) {
						win = true;
						cont = true;
					}
				}
			}
		}
	}
}