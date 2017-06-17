
public class Connect4_Game implements Connect4_State {
	
	private char [][] board;
	private PlayerList players;
	private Player activePlayer;
	private Player winner = null;
	
	public static int ROWS;
	public static int COLS;
	private final static char EMPTY = '.';	
	
	public Connect4_Game(int rowSize, int colSize, PlayerList players) {
		ROWS = rowSize;
		COLS = colSize;
		
		char [][] board = new char[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				board[i][j] = EMPTY;
			}
		}
		
		this.board = board;
		this.players = players;
		activePlayer = players.getFirstPlayer();
	}
	
	
	
	public char [][] getBoard() {
		return board;
	}
	
	public PlayerList getPlayers() {
		return players;
	}
	
	public Player getPlayerToMove() {
		return activePlayer;
	}
	
	public void getTurn(Connect4View view) {
		String moveQuery = String.format("It is now %1s's turn.\nEnter the number of the column in which to insert a checker.", activePlayer.getPlayerName());
		int chosenColumn = view.getInt(moveQuery);
		while (!moveIsValid(chosenColumn)) {
			chosenColumn = view.getInt("Choose a different column: ");
		}
		addChecker(activePlayer.getChecker(), chosenColumn);
		
		activePlayer = activePlayer.getNextPlayer();
	}
	
	public boolean moveIsValid(int col) {
		return board[ROWS-1][col-1] == EMPTY ? true : false;
	}
	
	public void addChecker(char checker, int col) {
		int r = 0;
		while (board[r][col-1] != EMPTY && r < ROWS) {
			r++;
		}
		board[r][col-1] = checker;
		
		if (fourOrMore(r, col-1)) {
			winner = activePlayer;
		}
	}
	
	public boolean fourOrMore(int row, int col) {
		int c4count = 0;
		
		int bottom = (row < 4) ? 0 : row-3;			//bottom-most row to check
		int top = (row > ROWS-1-4) ? ROWS-1 : row+3;	//top-most row to check
		int left = (col < 4) ? 0 : col-3;				//left-most column to check
		int right = (col > COLS-1-4) ? COLS-1 : col+3;	//right-most column to check
		
		//horizontal
		for (int c = left; (c <= right) && (c4count < 4); c++) {
			c4count = (board[row][c] == activePlayer.getChecker()) ? ++c4count : 0;
		}
		if (c4count == 4)
			return true;
		else
			c4count = 0;
			
		//vertical
		for (int r = bottom; (r <= top) && (c4count < 4); r++) {
			c4count = (board[r][col] == activePlayer.getChecker()) ? ++c4count : 0;
		}
		if (c4count == 4)
			return true;
		else
			c4count = 0;
		
		//upward diagonal
		int bottomLeftMin = Math.min(row-bottom, col-left);
		int r = row - bottomLeftMin;
		int c = col - bottomLeftMin;
		
		while ((r <= top && c <= right) && (c4count < 4)) {
			c4count = (board[r][c] == activePlayer.getChecker()) ? ++c4count : 0;
			r++;
			c++;
		}
		if (c4count == 4)
			return true;
		else
			c4count = 0;
		
		//downward diagonal
		int topLeftMin = Math.min(top-row, col-left);
		r = row + topLeftMin;
		c = col - topLeftMin;
		
		while((r >= bottom && c <= right) && (c4count < 4)) {
			c4count = (board[r][c] == activePlayer.getChecker()) ? ++c4count : 0;
			r--;
			c++;
		}
		if (c4count == 4)
			return true;
		else
			return false;
	}
	
	public boolean boardIsFull() {
		boolean full = true;
		for (int c = 0; c < COLS; c++) {
			if (board[ROWS-1][c] == EMPTY) {
				full = false;
			}
		}
		return full;
	}
	
	public boolean gameIsOver() {
		boolean gameOver = false;
		if (boardIsFull() || winner != null) {
			gameOver = true;
		}
		return gameOver;
	}
	
	public Player getWinner() {
		return winner;
	}
}
