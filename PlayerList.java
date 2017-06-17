
public class PlayerList {

	private Player first;
	private Player lastAdded;
	
	public PlayerList() {
		first = null;
		lastAdded = null;
	}
	
	public void addPlayer(Player newPlayer) {
		if(first == null) {
			first = newPlayer;
			lastAdded = first;
		}
		else {
			lastAdded.setNextPlayer(newPlayer);
			lastAdded = newPlayer;
		}
		lastAdded.setNextPlayer(first);
	}
	
	public Player getFirstPlayer() {
		return first;
	}
}
