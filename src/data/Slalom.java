package data;


public class Slalom {
	String name;
	int number;
	int P;
	int MP;
	int MS;
	int hiba;
	int VP;
	int VMP;
	int VMS;
	String ido;
	String vido;
	private String tort;

	public String getTort() {
		return tort;
	}

	public void setTort(String tort) {
		this.tort = tort;
	}

	public Slalom(String n, int nu, int p, int mp, int ms, int h) {
		name = n;
		number = nu;
		P = p;
		MP = mp;
		MS = ms;
		hiba = h;
		VP = P;
		VMP = MP;
		VMS = MS;
		tort = "Ezred";

		setTimes();
	}

	public Slalom(String n, int nu, String i, int h) {
		name = n;
		number = nu;
		hiba = h;
		String temp[] = i.split(":");
		P = Integer.parseInt(temp[0]);
		MP = Integer.parseInt(temp[1]);
		MS = Integer.parseInt(temp[2]);

		if (temp[2].length() == 2) {
			tort = "Század";
		} else if (temp[2].length() == 3) {
			tort = "Ezred";
		} else {
			tort = "Tized";
		}
		
		setTimes();
	}

	public Slalom() {

	}

	public void setTimes() {
		String p, mp, ms;
		if (P < 10)
			p = new String("0" + P);
		else
			p = Integer.toString(P);
		if (MP < 10)
			mp = new String("0" + MP);
		else
			mp = Integer.toString(MP);
		if (tort.equals("Tized")) {
			if (MS < 10) {
				ms = new String(MS + "00");
			} else if (MS < 100) {
				ms = new String(MS + "0");
			} else
				ms = Integer.toString(MS);
		} else if (tort.equals("Század")) {
			if (MS < 10) {
				ms = new String("0" + MS + "0");
			} else
				ms = new String(MS + "0");
		} else {
			if (MS < 10) {
				ms = new String("00" + MS);
			} else if (MS < 100) {
				ms = new String("0" + MS);
			} else
				ms = Integer.toString(MS);
		}

		ido = new String(p + ":" + mp + ":" + ms);

		VP = P;
		VMP = MP;
		VMS = MS;

		VMP += hiba * 5;
		while (VMP > 59) {
			VP++;
			VMP -= 60;
		}

		if (VP < 10)
			p = new String("0" + VP);
		else
			p = Integer.toString(VP);
		if (VMP < 10)
			mp = new String("0" + VMP);
		else
			mp = Integer.toString(VMP);

		vido = new String(p + ":" + mp + ":" + ms);
	}

	public String getIdo() {
		return ido;
	}

	public String getVido() {
		return vido;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setP(int p) {
		P = p;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public void setMS(int mS) {
		MS = mS;
	}

	public void setHiba(int hiba) {
		this.hiba = hiba;
	}

	public void setVP(int vP) {
		VP = vP;
	}

	public void setVMP(int vMP) {
		VMP = vMP;
	}

	public void setVMS(int vMS) {
		VMS = vMS;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getP() {
		return P;
	}

	public int getMP() {
		return MP;
	}

	public int getMS() {
		return MS;
	}

	public int getHiba() {
		return hiba;
	}

	public int getVP() {
		return VP;
	}

	public int getVMP() {
		return VMP;
	}

	public int getVMS() {
		return VMS;
	}
}
