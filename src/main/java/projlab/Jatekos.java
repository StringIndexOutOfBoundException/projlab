package projlab;

import java.util.ArrayList;
import java.util.List;


/**
 * A jatekos az amit a játékos irányíthat, ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private Mezo helyzet;

	protected int maxHatizsakKapacitas;

	protected List<Pumpa> pumpaHatizsak;
	protected ArrayList<Mezo> csoHatizsak;

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
	 * @param h - Az új helyzet
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

}
