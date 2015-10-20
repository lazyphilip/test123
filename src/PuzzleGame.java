import java.util.Arrays;
import java.util.Scanner;

public class PuzzleGame {

	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		int[][] Board = new int[3][3];
	
		
		System.out.println("Welcome to the 8-Puzzle Solver");
		
		
		System.out.println("Enter your first row of numbers: ");
		for ( int j =0; j < 3; ++j)
		{
			Board[0][j] = input.nextInt();
		}
		
		System.out.println("Enter your second row of numbers: ");
		for ( int j =0; j < 3; ++j)
		{
			Board[1][j] = input.nextInt();
		}
		System.out.println("Enter your third row of numbers: ");
		for ( int j =0; j < 3; ++j)
		{
			Board[2][j] = input.nextInt();
		}
		
		System.out.println("Here is your current board:");
		Board game1 = new Board(Board);
		game1.initializeGoalState();
		game1.printBoard();
		
		game1.checkMisplacedTiles();
		
		System.out.println(game1.getNumMisplaced());
		
		
		

		
		
		



	}

}
