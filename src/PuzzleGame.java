import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PuzzleGame {

	public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		int[][] Board = new int[3][3];

		System.out.println("Welcome to the 8-Puzzle Solver\n");

		System.out.println("Enter 1 to make your own board");
		System.out.println("Enter 2 to use a default board");
		int option = input.nextInt();

		if (option == 1) {
			System.out.println("Enter your first row of numbers: ");
			for (int j = 0; j < 3; ++j) {
				Board[0][j] = input.nextInt();
			}

			System.out.println("Enter your second row of numbers: ");
			for (int j = 0; j < 3; ++j) {
				Board[1][j] = input.nextInt();
			}
			System.out.println("Enter your third row of numbers: ");
			for (int j = 0; j < 3; ++j) {
				Board[2][j] = input.nextInt();
			}
		}
		else{
			Board[0][0] = 0; Board[0][1] = 1; Board[0][2] = 2;
			Board[1][0] = 4; Board[1][1] = 5; Board[1][2] = 3;
			Board[2][0] = 7; Board[2][1] = 8; Board[2][2] = 6;
		}
		
		
		System.out.println("Press 1 for Uniform Cost Search\n Press 2 for Misplaced Tile\n Press 3 for Manhattan Distance");
		int algorithm = input.nextInt();
		
		Board game1 = new Board(Board);
		long startTime =0;
		if ( algorithm == 1){
			 startTime = System.currentTimeMillis();
			 UniformCostSearch.uniformCostSearchPerform(game1);
		}
		else if ( algorithm == 2){
			startTime = System.currentTimeMillis();
			MisplacedTile.MisplacedTilePerform(game1);
		}
		else if ( algorithm == 3){
			startTime = System.currentTimeMillis();
			Manhattan.performManHattan(game1);
		}
		else{
			System.out.print("You did not input a valid option. Goodbye.");
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime);
		totalTime = TimeUnit.MILLISECONDS.toMillis(totalTime);
		System.out.println("Time: " + totalTime + "ms");
		 input.close();
		
	}

}
