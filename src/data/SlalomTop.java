package data;

public class SlalomTop extends People{
	
	int won;
	
	public SlalomTop() {
		
	}
	
	public SlalomTop(int num, String nam, int win) {
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
