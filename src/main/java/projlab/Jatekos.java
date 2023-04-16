package projlab;
import java.util.ArrayList;
import java.util.List;


/**
 * A jatekos az amit a játékos irányíthat, ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private int maxHatizsakKapacitas;
	private Mezo helyzet;
	private List<Pumpa> pumpaHatizsak;
	private List<Cso> csoHatizsak;

	public Jatekos() {
		pumpaHatizsak=new ArrayList<Pumpa>();
		csoHatizsak=new ArrayList<Cso>();
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
	 * Jatekos helyzetenek beallitasa
	 */
	public void setHelyzet(Mezo m) {
		helyzet = m;
	}
	/*
	 * getter a jatekos pumpaHatizsakjra
	 */
	public List<Pumpa> getPumpaHatizsak(){
		return this.pumpaHatizsak;
	}
	
	/**
	 * getter a maxHatizsakKapacitasra
	 */ 
	public int getMaxHatizsakKapacitas() {
		return this.maxHatizsakKapacitas;
	}
	
	/**
	 * setter a jatekos HatizsakKapacitasara
	 */
	public void setMaxHatizsakKapacitas(int value) {
		this.maxHatizsakKapacitas=value;
	}
}
