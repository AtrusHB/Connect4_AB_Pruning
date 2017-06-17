import java.util.Scanner;

public class Connect4View_Text implements Connect4View {

	private Scanner input;
	
	public Connect4View_Text() {
		input = new Scanner(System.in);
	}
	
	public void display(Connect4_State state) {
		String boardText = "";
		char[][] board = state.getBoard();
		
		for (int r = Connect4_Game.ROWS-1; r >= 0; r--) {
			for (int c = 0; c <= Connect4_Game.COLS-1; c++) {
				boardText += board[r][c];
			}
			boardText += "\n";
		}
		
		System.out.println(boardText);
	}

	public int getInt(String message) {
		System.out.print(message);
		int answer = input.nextInt();
		input.nextLine();
		// TODO finish writing getInt
		return answer;
	}

	public String getString(String nameQuery) {
		System.out.print(nameQuery);
		String answer = input.nextLine();
		// TODO finish writing getString
		return answer;
	}
	
	
}
