package projlab;

import java.util.ArrayList;
import java.util.List;


/**
 * A jatekos az amit a játékos irányíthat, ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private Mezo helyzet;

	private int maxHatizsakKapacitas;

	private ArrayList<Pumpa> pumpaHatizsak;
	private ArrayList<Mezo> csoHatizsak;

	public Jatekos() {
		pumpaHatizsak = new ArrayList<>();
		csoHatizsak = new ArrayList<>();
	}

	public void Lep() {
	}

	public void Allit(Mezo kimenet, Mezo bemenet) {

	}

	/**
	 * Ez egy getter ami a helyzet értékeét adja vissza, a helyzet az a mező, amin éppen a játékos áll
	 * @return Mezo típusú, és azt adja vissza, hogy hol van a játékos
	 */
	public Mezo getHelyzet(){
		return helyzet;
	}

	/**
	 * Beállítja a játékos helyzetét, azt a mezőt, amelyen az adott játékos éppen
	 * tartózkodik
	 * 
	 * @param m - Az új helyzet
	 */
	public void setHelyzet(Mezo m) {
		helyzet = m;
	}

	/**
	 * Kívülről beállítja a játékosra jellemző, a hátizsákban tárolható maximális
	 * elemek számát
	 * @param c - Az új kapacitás értéke
	 */
	public void setMaxHatizsakKapacitas(int c) {
		maxHatizsakKapacitas = c;
	}

	/**
	 * Kívülről beállítja a játékosra jellemző, a hátizsákban tárolható maximális
	 * elemek számát
	 * 
	 */
	public int getMaxHatizsakKapacitas() {
		return maxHatizsakKapacitas;
	}

	public ArrayList<Mezo> getCsoHatizsak() {
		return csoHatizsak;
	}

}
