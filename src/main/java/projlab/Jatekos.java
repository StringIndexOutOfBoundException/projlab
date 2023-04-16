package projlab;
import java.util.List;


/**
 * A jatekos az amit a játékos irányíthat, ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private int maxHatizsakKapacitas;
	private Mezo helyzet;
	private List<Pumpa> pumpaHatizsak;
	private List<Cso> csoHatizsak;

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

	//Jatekos helyzetenek beallitasa
	public void setHelyzet(Mezo m) {
		helyzet = m;
	}
}
