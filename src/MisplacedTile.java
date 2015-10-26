import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MisplacedTile {

	public static void startMisplacedTile(){
		
	LinkedList<Board> openlist = new LinkedList<>();
	
	//while the List is not empty
	while (!openlist.isEmpty())
	{
		Collections.sort(openlist,new Comparator<Board>() {
			public int compare(Board c1, Board c2){
				if ( c1.numMisplaced > c2.numMisplaced) return -1;
				if ( c1.numMisplaced < c2.numMisplaced) return 1;
				return 0;
			}
		});
		
		Board curboard = openlist.removeFirst();
		
		//check if it is goal state
		if(!curboard.checkEqualsGoal()){
			
	
			
		}
		
	}
		
		
	}
	
}
