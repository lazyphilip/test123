import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Manhattan {
	//h_n = number of misplaced tiles
	//g_n = the depth
	// f_n = h_n + g_n
	
	public Manhattan(){
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void performManHattan(Board gameboard){
		
	int openlist_maxsz = 0;
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
		
		//2. Check current board if goal state

		Board curboard = openlist.poll();
		//System.out.println("Here is your current board");
		//curboard.print_heuristic();
		//curboard.printBoard();
		
		if( curboard.checkEqualsGoal()){
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
		
		// total # of nodes expanded
		int children_sz = children.size();
		Board.setTotalNodesExpanded(children_sz);
		
		
		
		for ( int i =0; i < children.size(); i++){
			children.get(i).setParent(curboard);
			children.get(i).calculateManDistance();
			// keep track of previous boards
			

		}
		
		
		
		//4. Move current board if not goal state to closed list
		closedlist.add(curboard);
		
		//5. if any of the children appear in open/closed list remove them ? Check if less distance


		//6. Calculate f(n) then Sort
		// add children to open list
		// current problem is that it adds in duplicates

		//new
		Iterator<Board> it = openlist.iterator();
		while ( it.hasNext()){
			Board tmp = it.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it.remove();
				}
			}
		}
//		//new
		Iterator<Board> it2 = closedlist.iterator();
		while ( it2.hasNext()){
			Board tmp = it2.next();
			for ( int i =0; i < children.size(); ++i){
				if ( children.get(i).checkEqualTwoArray(tmp)){
					it2.remove();
				}
			}
		}
		for ( int i =0; i < children.size(); ++i){
			openlist.add(children.get(i));
		}
		if ( openlist_maxsz < openlist.size()){
			openlist_maxsz = openlist.size();
		}


	}
		
	}



}
