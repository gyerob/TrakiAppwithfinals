package data;

public class Drag {
	String name;
	int number;
	int MP1;
	int MP2;
	int MS1;
	int MS2;
	boolean jobb;
	String ido1;
	String ido2;
	String legjobb;
	private String tort1, tort2;

	public String getTort1() {
		return tort1;
	}

	public void setTort1(String tort) {
		this.tort1 = tort;
	}
	
	public String getTort2() {
		return tort2;
	}

	public void setTort2(String tort) {
		this.tort2 = tort;
	}

	public Drag(String n, int nu, int mp1, int mp2, int ms1, int ms2) {
		name = n;
		number = nu;
		MP1 = mp1;
		MP2 = mp2;
		MS1 = ms1;
		MS2 = ms2;
		tort1 = "Ezred";
		tort2 = "Ezred";

		setTimes();
	}

	public Drag(String n, int nu, String i1, String i2) {
		name = n;
		number = nu;
		String temp1[] = i1.split(":");
		String temp2[] = i2.split(":");
		MP1 = Integer.parseInt(temp1[0]);
		MS1 = Integer.parseInt(temp1[1]);
		MP2 = Integer.parseInt(temp2[0]);
		MS2 = Integer.parseInt(temp2[1]);
		tort1 = "Ezred";
		tort2 = "Ezred";
		
		if (temp1[1].length() == 2) {
			tort1 = "Század";
		} else if (temp1[1].length() == 3) {
			tort1 = "Ezred";
		} else {
			tort1 = "Tized";
		}
		if (temp2[1].length() == 2) {
			tort2 = "Század";
		} else if (temp2[1].length() == 3) {
			tort2 = "Ezred";
		} else {
			tort2 = "Tized";
		}
		
		setTimes();
	}

	public Drag() {

	}

	public void setTimes() {
		String mp, ms;
		if (MP1 < 10) {
			mp = new String("0" + MP1);
		} else
			mp = Integer.toString(MP1);
		if (tort1.equals("Tized")) {
			if (MS1 < 10) {
				ms = new String(MS1 + "00");
			} else if (MS1 < 100) {
				ms = new String(MS1 + "0");
			} else
				ms = Integer.toString(MS1);
		} else if (tort1.equals("Század")) {
			if (MS1 < 10) {
				ms = new String("0" + MS1 + "0");
			} else
				ms = new String(MS1 + "0");
		} else {
			if (MS1 < 10) {
				ms = new String("00" + MS1);
			} else if (MS1 < 100) {
				ms = new String("0" + MS1);
			} else
				ms = Integer.toString(MS1);
		}
		ido1 = new String(mp + ":" + ms);

		if (MP2 < 10) {
			mp = new String("0" + MP2);
		} else
			mp = Integer.toString(MP2);
		if (tort2.equals("Tized")) {
			if (MS2 < 10) {
				ms = new String(MS2 + "00");
			} else if (MS2 < 100) {
				ms = new String(MS2 + "0");
			} else
				ms = Integer.toString(MS2);
		} else if (tort2.equals("Század")) {
			if (MS2 < 10) {
				ms = new String("0" + MS2 + "0");
			} else
				ms = new String(MS2 + "0");
		} else {
			if (MS2 < 10) {
				ms = new String("00" + MS2);
			} else if (MS2 < 100) {
				ms = new String("0" + MS2);
			} else
				ms = Integer.toString(MS2);
		}
		ido2 = new String(mp + ":" + ms);

		int hasonlit = ido1.compareTo(ido2);
		if (hasonlit > 0)
			legjobb = ido2;
		else
			legjobb = ido1;
	}

	public String getIdo1() {
		return ido1;
	}

	public String getIdo2() {
		return ido2;
	}

	public String getLegjobbido() {
		return legjobb;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setMP1(int mP1) {
		MP1 = mP1;
	}

	public void setMP2(int mP2) {
		MP2 = mP2;
	}

	public void setMS1(int mS1) {
		MS1 = mS1;
	}

	public void setMS2(int mS2) {
		MS2 = mS2;
	}

	public void setJobb(boolean jobb) {
		this.jobb = jobb;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getMP1() {
		return MP1;
	}

	public int getMP2() {
		return MP2;
	}

	public int getMS1() {
		return MS1;
	}

	public int getMS2() {
		return MS2;
	}

	public boolean isJobb() {
		return jobb;
	}
}
