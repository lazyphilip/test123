import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class UniformCostSearch {
	//g_n = the depth
	// f_n = h_n + g_n
	
	public UniformCostSearch(){
		
	}
	
	@SuppressWarnings("unchecked")
	public static void uniformCostSearchPerform(Board gameboard){
		
	int openlist_maxsz = 0;	
	// creates a priority queue to store in all the possible moves of the board, no duplicates
	PriorityQueue<Board> openlist = new PriorityQueue<Board>(10000,new Comparator<Board>(){

		@Override
		public int compare(Board o1, Board o2) {
			return o1.f_n - o2.f_n;
		}
		
	});
	//List of boards that have been visited
	ArrayList<Board> closedlist = new ArrayList<>();
	
	//1. Add the originial board to open list
	openlist.add(gameboard);
	while(true){
		
		
		//pop open the first board on openlist assign it as current board you are on
		Board curboard = openlist.poll();
		
		//check to see if goal state
		if( curboard.checkEqualsGoal()){
			//if you are then trace back the steps from original board to the goal board
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
		
		
		for ( int i =0; i < children.size(); i++){
			children.get(i).setParent(curboard);
			children.get(i).calculateUniformCost();
			

		}
		
		
		//4. Move current board if not goal state to closed list
		closedlist.add(curboard);
		
		//remove any duplicates from openlist and closed list
		Iterator<Board> it = openlist.iterator();
		while ( it.hasNext()){
			Board tmp = it.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it.remove();
				}
			}
		}
		Iterator<Board> it2 = closedlist.iterator();
		while ( it2.hasNext()){
			Board tmp = it2.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it2.remove();
				}
			}
		}
		//add all the possible moves to openlist 
		for ( int i =0; i < children.size(); ++i){
			openlist.add(children.get(i));
		}
		if ( openlist_maxsz < openlist.size()){
			openlist_maxsz = openlist.size();
		}

	}
		
	}



}
