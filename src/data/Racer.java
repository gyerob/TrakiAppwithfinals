package data;

public class Racer {
	String name;
	int number;
	String town;
	boolean sex;
	boolean trailer;
	boolean slalom;
	boolean drag;

	public Racer() {

	}

	public Racer(String n, int nu, String t, String s, String tr, String sl,
			String d) {
		name = n;
		number = nu;
		town = t;
		sex = Boolean.parseBoolean(s);
		trailer = Boolean.parseBoolean(tr);
		slalom = Boolean.parseBoolean(sl);
		drag = Boolean.parseBoolean(d);
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public boolean getSex() {
		return sex;
	}

	public String getTown() {
		return town;
	}

	public boolean getTrailer() {
		return trailer;
	}

	public boolean getSlalom() {
		return slalom;
	}

	public boolean getDrag() {
		return drag;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setTrailer(boolean trailer) {
		this.trailer = trailer;
	}

	public void setSlalom(boolean slalom) {
		this.slalom = slalom;
	}

	public void setDrag(boolean drag) {
		this.drag = drag;
	}
	
	
}
