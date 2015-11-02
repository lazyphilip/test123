import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Board{
	
	Position zero_position = new Position();
	int manDistance = 0;
	int numMisplaced = 0;
	int h_n = 0;
	int g_n = 0;
	int f_n = 0;
	int depth = 0;
	static int total_nodes_expanded;
	Board previousBoard = null;
	
	//initial game board
	int[][] gameboard;
	public int[][] getgameboard(){
		return gameboard;
	}
	
	//2D array of the goal board
	int[][] goalBoard = {{1,2,3},{4,5,6},{7,8,0}};
	
	//Constructor
	public Board(int[][] gameboard){
		this.gameboard = gameboard;
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
		// print the goal bboard
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(goalBoard[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void checkMisplacedTiles(){
		numMisplaced =0;
		for (int i = 0; i < 3; ++i) 
		{
			for (int j = 0; j < 3; ++j)
			{
				if(gameboard[i][j] != goalBoard[i][j]){ //check current board against goal board
					numMisplaced++; // increment by 1 if it is out of placed
				} 
			}
		}
	}
	
	public void find_zero_position(){
		for (int i = 0; i < 3; ++i) 
		{
			for (int j = 0; j < 3; ++j)
			{
				// find position of the 0(x,y) on the 2D Array
				if(gameboard[i][j] == 0){
					zero_position.setposXY(i, j);
				} 
			}
		}
		
	}
	
	//function to duplicate board
	@SuppressWarnings("null")
	public int[][] duplicateBoard( int board[][]){
		int[][] newboard = new int[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				newboard[i][j] = board[i][j]; 
			}
		}
		return newboard;
	}
	
	public void moveTheIndex(int x, int y, ArrayList<Board> t){
		// make a duplicate of board
		int[][] newboard = duplicateBoard(gameboard);
		
		
		int swap_value = gameboard[x][y];
		 
		newboard[x][y] = 0;
		newboard[zero_position.getX()][zero_position.getY()] = swap_value;
		t.add(new Board(newboard));
		// a 'move' on the board, swaps the value of the board and pushes it into an array
		
	}
	

	public ArrayList<Board> getchildren(){
		
		//Array list of all the possible moves
		ArrayList<Board> list_of_boards = new ArrayList<Board>();
		
		// find the zero position of current board
		find_zero_position();
		
		if (zero_position.getY() != 0 ){
			moveTheIndex(zero_position.getX(),zero_position.getY()- 1,list_of_boards);
			//move empty space LEFT
			
		}
		if ( zero_position.getY() != 2){
			moveTheIndex(zero_position.getX(),zero_position.getY()+ 1,list_of_boards);
			//move empty space RIGHT
		}
		if ( zero_position.getX() != 0){
			//move empty space UP
			moveTheIndex(zero_position.getX() - 1, zero_position.getY(),list_of_boards);
		}
		if ( zero_position.getX() != 2)
		{
			//move empty space DOWN
			moveTheIndex(zero_position.getX() + 1, zero_position.getY(),list_of_boards);
		}
		
		return list_of_boards;
	}
	

	public boolean checkEqualsGoal(){
		//check if 2D array equals each other
		return Arrays.deepEquals( gameboard, goalBoard);
	}
	public boolean checkEqualTwoArray (Board b)
	{
		//check if 2D array equals each other
		return Arrays.deepEquals(this.gameboard,b.gameboard);
	}
	
	// return numbers of misplaced tiles
	public int getNumMisplaced(){
		return numMisplaced;
	}
	//return manhattan distance
	public void getManDistance() {
		manDistance = 0;
		for ( int x = 0; x < 3; ++x){
			for ( int y =0; y < 3; ++y)
			{
				int value = gameboard[x][y];
				if ( value != 0){
					int distanceX = ( value - 1) / 3;
					int distanceY = ( value - 1) % 3;
					int dx = x - distanceX;
					int dy = y - distanceY;
					manDistance += Math.abs(dx) + Math.abs(dy);
				}
			}
		}
		//return manDistance;
	}
	
	public void print_heuristic(){
		System.out.print("g(n):" + g_n);
		System.out.println("   h(n):" + h_n);
	}
	
	public void calculateUniformCost(){
		incrDepth();  //increment the depth
		f_n = depth;
		h_n = 0;
		g_n = depth;
	}
	
	public void calculateMisplacedTile(){
		checkMisplacedTiles(); 
		incrDepth();
		f_n = numMisplaced + depth;
		//cost
		g_n = depth;
		h_n = numMisplaced;
	}
	public void calculateManDistance(){
		getManDistance();
		incrDepth();
		f_n = manDistance + depth; 
		g_n = depth;
		h_n = manDistance;

		
	}
	
	public void incrDepth(){
		if ( previousBoard != null){
			depth = previousBoard.depth + 1;
		}
		else{
			depth++;
		}
		
	}
	public int getf_n(){
		return f_n;
	}
	public void setParent(Board a){
		this.previousBoard = a;
	}
	public Board getParent(Board a){
		return a.previousBoard;
	}
	public static void setTotalNodesExpanded(int a){
		total_nodes_expanded += a;
	}
	public static int getTotalNodesExpanded(){
		return total_nodes_expanded;
	}


}
