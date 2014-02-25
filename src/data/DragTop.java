package data;

public class DragTop extends People{
	
	int won;
	
	public DragTop() {
		
	}
	
	public DragTop(int num, String nam, int win) {
		won = win;
		number = num;
		name = nam;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}
}
