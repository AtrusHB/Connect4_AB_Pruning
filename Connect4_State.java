
public interface Connect4_State {
	
	public final static char EMPTY = '.';
	public final static char CHECKER0 = 'A';
	public final static char CHECKER1 = 'B';
	public final static char CHECKER2 = 'C';
	public final static char CHECKER3 = 'D';
	public final static char [] CHECKERS = {CHECKER0, CHECKER1, CHECKER2, CHECKER3};
	
	public char [][] getBoard();
	
	public PlayerList getPlayers();
	
	public Player getPlayerToMove();
	
	public boolean moveIsValid(int col);
	
	public void addChecker(char checker, int col);
	
	public boolean boardIsFull();
}