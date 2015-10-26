
public class Position {
	
	private int x;
	private int y;
	


	Position(int x, int y){
		this.x=x;
		this.y=y;
	}
	Position(){
		this.x =0;
		this.y=0;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setposXY(int x, int y){
		this.x =x;
		this.y=y;
	}
}
