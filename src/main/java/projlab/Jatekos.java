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

	public Jatekos() {
		pumpaHatizsak= new ArrayList <Pumpa> ();
		csoHatizsak= new ArrayList <Cso> ();
	}
	public void Lep() {
	}

	public void Allit(Mezo kimenet, Mezo bemenet) {

	}
	//setter helyzetnek
	public void setHelyzet(Mezo m) {
		this.helyzet=m;
	}
	//getter pumpaHatizsaknak
	public List<Pumpa> getpumpaHatizsak(){
		return this.pumpaHatizsak;
	}
	//getter maxHatizsakKapacitasnak
	public int getmaxHatizsakKapacitas(){
		return this.maxHatizsakKapacitas;
	}
		//setter maxHatizsakKapacitasnak
	public void setmaxHatizsakKapacitas(int value) {
		this.maxHatizsakKapacitas=value;
	}
	/**
	 * Ez egy getter ami a helyzet értékeét adja vissza, a helyzet az a mező, amin éppen a játékos áll
	 * @return Mezo típusú, és azt adja vissza, hogy hol van a játékos
	 */
	public Mezo getHelyzet(){
		return helyzet;
	}
}
