
public abstract class Player {

	private String playerName;
	private char checkerSymbol;
	private Player nextPlayer;
	
	
	
	public Player() {
		
	}
	
	public Player(String name) {
		playerName = name;
	}
	
	public Player(String name, char symbol) {
		playerName = name;
		checkerSymbol = symbol;
	}
	
	public Player getSelfReference() {
		return this;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String name) {
		playerName = name;
	}
	
	public char getChecker() {
		return checkerSymbol;
	}
	
	public void setChecker(char symbol) {
		checkerSymbol = symbol;
	}
	
	public Player getNextPlayer() {
		return nextPlayer;
	}
	
	public void setNextPlayer(Player next) {
		nextPlayer = next;
	}
}
