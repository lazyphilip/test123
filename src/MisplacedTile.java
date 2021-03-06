import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class MisplacedTile {
	//h_n = number of misplaced tiles
	//g_n = the depth
	// f_n = h_n + g_n

	public MisplacedTile(){
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void MisplacedTilePerform(Board gameboard){
	
	int openlist_maxsz = 0;
	// creates a priority queue to store in all the possible moves of the board, no duplicates
	PriorityQueue<Board> openlist = new PriorityQueue<Board>(10000,new Comparator<Board>(){

		@Override
		public int compare(Board o1, Board o2) {
			return o1.f_n - o2.f_n;
		}
		
	});
	
	ArrayList<Board> closedlist = new ArrayList<>();
	
	//1. Add the originial board to open list
	openlist.add(gameboard);
	while(true){
		
		Board curboard = openlist.poll();
		
		//2. Check current board if goal state
		if( curboard.checkEqualsGoal()){
			// creates a stack to display the trace to the goal state
			Stack<Board> path = new Stack<Board>();
			path.push(curboard);
			curboard = curboard.getParent(curboard);
			while ( curboard.getParent(curboard) != null)
			{
				path.push(curboard);
				curboard = curboard.getParent(curboard);
			}
			path.push(curboard);
			
			int stacksize = path.size();
			System.out.println("path size: " + path.size() );
			for ( int i =0; i < stacksize; i++)
			{
				System.out.println("i" + i);
				curboard = path.pop();
				curboard.print_heuristic();
				curboard.printBoard();
				System.out.println("\n");
			}
			
			System.out.println("You have reached the goal state!");
			System.out.println("Total number of nodes expanded: "+ Board.getTotalNodesExpanded());
			System.out.println("Max size in the queue:" + openlist_maxsz);
			return;
		}
		
		
		//3. Get the children of current board and put it all on open list
		ArrayList<Board> children = new ArrayList<>();
		children = curboard.getchildren();
		
		// total number of nodes expanded
		int children_sz = children.size();
		Board.setTotalNodesExpanded(children_sz);
		
		
		for ( int i =0; i < children.size(); i++){
			//keep track of previous boards to trace to the goal state
			children.get(i).setParent(curboard);
			// calculates misplaced tiles for each board
			children.get(i).calculateMisplacedTile();

		}
		
		
		
		//4. Move current board if not goal state to closed list
		closedlist.add(curboard);
		

		// remove duplicates from openlist
		Iterator<Board> it = openlist.iterator();
		while ( it.hasNext()){
			Board tmp = it.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it.remove();
				}
			}
		}
		//remove duplicates from closedlist
		Iterator<Board> it2 = closedlist.iterator();
		while ( it2.hasNext()){
			Board tmp = it2.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it2.remove();
				}
			}
		}
		// add the children to openlist board into the priority queue
		for ( int i =0; i < children.size(); ++i){
			openlist.add(children.get(i));
		}
		
		if ( openlist_maxsz < openlist.size()){
			openlist_maxsz = openlist.size();
		}
		
		

	}
		
	}



}
