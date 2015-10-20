import java.util.Arrays;
import java.util.Scanner;

public class Board {
	
	
	int manDistance = 0;

	int numMisplaced = 0;
	
	//initial game board
	int[][] gameboard;
	
	//goal board
	int[][] goalBoard = new int[3][3];
	
	
	//Constructor
	public Board(int[][] gameboard){
		this.gameboard = gameboard;
	}
	

	
	public void initializeGoalState(){
		int goalNumber = 1;
		for ( int i = 0; i < 3; ++i)
		{
			for ( int j =0; j < 3; ++j)
			{
				goalBoard[i][j] = goalNumber;
				goalNumber++;
			}
		}
		// set last tile to 0
		goalBoard[2][2] = 0;
	}
	
	
	
	
	public void printBoard() {
		// print the board
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(gameboard[i][j] + " ");
			}
			System.out.print("\n");
		}

	}
	public void printGoalBoard() {
		// print the board
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(goalBoard[i][j] + " ");
			}
			System.out.print("\n");
		}

	}
	
	public void checkMisplacedTiles(){
		for (int i = 0; i < 3; ++i) 
		{
			for (int j = 0; j < 3; ++j)
			{
				if(gameboard[i][j] != goalBoard[i][j]){
					numMisplaced++;
				} 
			}
		}
		
	}

	public boolean checkEqualsGoal(){
		
		return Arrays.deepEquals( gameboard, goalBoard);
	}
	
	// return numbers of misplaced tiles
	public int getNumMisplaced(){
		return numMisplaced;
	}
	//return manhattan distance
	public int getManDistance() {
		return manDistance;
	}
	

	


}
