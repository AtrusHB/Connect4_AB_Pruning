import java.util.Random;
import java.lang.*;

public class Connect4 {

	public static void main(String args[]) {
		
		Connect4View view = new Connect4View_Text();
		PlayerList players = gatherPlayers(view);
		int boardRows = view.getInt("Enter desired number of rows on the game board (between 8 and 64 inclusive): ");
		while (boardRows < 8 || boardRows > 64) {
			boardRows = view.getInt("Not a valid number.\nEnter desired number of rows on the game board (between 8 and 64 inclusive): ");
		}
		int boardColumns = view.getInt("Enter desired number of columns on the game board (between 8 and 64 inclusive): ");
		while (boardColumns < 8 || boardColumns > 64) {
			boardColumns = view.getInt("Not a valid number.\nEnter desired number of columns on the game board (between 8 and 64 inclusive): ");
		}
		
		Connect4_Game state = new Connect4_Game(boardRows, boardColumns, players);
		
		view.display(state);
		while (!state.gameIsOver()) {
			state.getTurn(view);
			view.display(state);
		}
		
	}
	
	private static PlayerList gatherPlayers(Connect4View view) {
		PlayerList list = new PlayerList();
		int numberOfPlayers, aiPlayersToMake;
		int addedPlayers = 0;
		Random makeAI = new Random();
		
		numberOfPlayers = view.getInt("Enter desired number of players (from 2 to 6 inclusive): ");
		while (numberOfPlayers < 2 || numberOfPlayers > 6) {
			numberOfPlayers = view.getInt("Not a valid number.\nEnter desired number of players (from 2 to 6 inclusive): ");
		}
		aiPlayersToMake = view.getInt("How many of these players will be computer-controlled? ");
		while (aiPlayersToMake < 0 || aiPlayersToMake > numberOfPlayers) {
			aiPlayersToMake = view.getInt("Not a valid number.\nHow many of these players will be computer-controlled? ");
		}
		
		
		for (int i = 1; i <= numberOfPlayers; i++) {
			if ((aiPlayersToMake > 0 && makeAI.nextBoolean()) || (addedPlayers + aiPlayersToMake == numberOfPlayers)) {
				list.addPlayer(makePlayer(view, i, true));
				aiPlayersToMake--;
			}
			else
				list.addPlayer(makePlayer(view, i, false));
			addedPlayers++;
		}
		
		return list;
	}

	private static Player makePlayer(Connect4View view, int playerNumber, boolean isAI) {
		Player player;
		String playerType = isAI ? "AI" : "Human";
		
		String nameQuery = String.format("Player %1d will be a(n) %2s player. Please enter the desired name: ", playerNumber, playerType);
		String playerName = view.getString(nameQuery);
		while (playerName == "") {
			playerName = view.getString("Player name cannot be empty. Please enter the desired name: ");
		}
		
		player = (playerType == "AI") ? new Player_AI(playerName, Character.forDigit(playerNumber, 10)) : (playerType == "Human") ? new Player_Human(playerName, Character.forDigit(playerNumber, 10)) : null;
		return player;
	}
}
