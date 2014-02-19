package data;

public class Finals {
	private String name;
	private int number;
	private int won;

	public Finals() {

	}

	public Finals(String n, int nu, int w) {
		name = n;
		number = nu;
		won = w;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}
}
