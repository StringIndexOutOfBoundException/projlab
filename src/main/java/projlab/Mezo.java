package projlab;//

import java.util.ArrayList;
import java.util.List;

/**
 * A Mező egy absztrakt osztály, ami a csőrendszer összes elemét reprezentálja.
 * Mivel absztrakt az osztály ezért példányosítani nem lehet. Mező lehet egy cső
 * vagy egy aktívelem.
 */
public abstract class Mezo {
	private int vizmennyiseg;
	private Boolean mukodik;
	//Azt tárolja, hogy hány játékos állhat egy mezőn. Ez felül lesz írva a leszármazottak konstruktoraiban.
	private int maxJatekosok = 0;

	private ArrayList <Jatekos> jatekosok;
	private ArrayList <Mezo> szomszedok;

	/**
	 * Default konstruktor
	 */
	public Mezo() {
		szomszedok = new ArrayList<Mezo>();
	}

	/**
	 * Létrehoz egy Mezőt a mezőre jellemző maximum játékosok számával.
	 * @param maxJatekosok - Maximum ennyi játékos állhat rajta
	 */
	public Mezo(int maxJatekosok) {
		mukodik = true;
		jatekosok = new ArrayList<Jatekos>();
		szomszedok = new ArrayList<Mezo>();
		this.maxJatekosok = maxJatekosok;
	}

  /**
	 * Adott mezo szomszedjait kerdezi le
	 * @return Adott mezo szomszedjai egy kollekcioban
	 */
	public ArrayList<Mezo> GetSzomszedok() {
		return szomszedok;
	}

	/**
	 * A függvény hozzáadja a játékost a mezőhöz.
	 * @param j A hozzáadandó játékos.
	 * @return Igaz, ha sikerült a hozzáadás. Akkor sikerül, ha a mezőn még nincs a maximális játékosok száma.
	 */
	public Boolean JatekosElfogad(Jatekos j) {
		if (jatekosok.size() < maxJatekosok) {
			jatekosok.add(j);
			j.setHelyzet(this);
			//System.out.println("Játékos hozzáadva a mezőhöz: " + this);
			return true;
		}
		//System.out.println("A mezőn már nincs hely a játékosnak: " + this);
		return false;
	}

	/**
	 * Egy játékos eltávolítása egy mezőről
	 * @param j a játékos referenciája
	 */
	public void JatekosEltavolit(Jatekos j) {
		jatekosok.remove(j);
		System.out.println("Függvényhívás: "+ this +".JatekosEltavolit()");
	}

	/**
	 * Egy játékos átállítja egy pumpa be és kimenetét
	 * @param kimenet amire át lesz állítva a kimenet
	 * @param bemenet amire át lesz állítva a bemenet
	 */
	public void Atallit(Mezo kimenet, Mezo bemenet) {
	}

	/**
	 * A szerelő megjavít egy elemet, amin éppen áll. Default implementációja üres.
	 */
	public void Megjavit() {
	}

	/**
	 * A szabotőr kilyukaszt egy csövet. Default implementációja üres.
	 */
	public void Kilyukaszt() {
	}

	/**
	 * A szerelő lehelyez egy új pumpát arra a csőre, amin éppen áll, azaz
	 * kettévágja és új pumpát rak a kettő közé. A szerelőknél lévő pumpák száma
	 * csökken. Default implementációja üres.
	 */
	public void PumpaEpit() {
	}

	/**
	 *  Az érintett mezőnek hozzáadja a paraméterként átadott mezőt a szomszédok kollekciójához.
	 * @param m - Hozzáadandó szomszéd
	 */
	public void SzomszedHozzaad(Mezo m) {
		szomszedok.add(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedHozzaad(" + m + ")");
	}

	/**
	 * Egy mezőt leválasztottunk egy másik mezőről. Az adott mezőnek a szomszédok kollekciójából törli az m mezőt.
	 * @param m Törlendő szomszéd
	 */
	public void SzomszedTorol(Mezo m) {
		szomszedok.remove(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedTorol(" + m + ")");
	}

	/**
	 * A Játékosok ezen a függvényen keresztül csatolhatnak fel elemeket a mezőkhöz.
	 * Minden aktívelemnél ugyanazt csinálja mint a SzomszedHozzaad, viszont a Cső
	 * osztály felüldefiniálja. Default implementációként nem enged felcsatolni.
	 * @param m - Felcsatolandó
	 * @return Sikerült-e a felcsatolás
	 */
	public Boolean SzomszedFelcsatol(Mezo m) {
		System.out.println("Függvényhívás: " + this + ".SzomszedFelcsatol(" + m + ")");
		return false;
	}

	/**
	 * Működése hasonló a GetSzomszedokhoz, viszont csak a játékosok által lecsatlakoztatható
	 * szomszédokkal tér vissza.
	 * Default implementációként üres listával tér vissza.
	 * @return lecsatlakoztatható szomszédok
	 */
	public ArrayList<Mezo> GetLeszedhetoSzomszedok() {
		System.out.println("Függvényhívás: " + this + ".GetLeszedhetoSzomszedok()");
		System.out.println("Az elemről nem engedett a lecsatlakoztatás, leszedhető szomszédok száma: 0");
		return new ArrayList<>();
	}

	/**
	 * Beállítja a mező működő státuszát.
	 * @param status - Új állapot
	 */
	public void setMukodik(boolean status){
		mukodik = status;
	}

	public void VizetCsokkent(int meret) throws Exception {};
	public void VizetNovel(int meret) throws Exception {};
	public int getVizmennyiseg(){return vizmennyiseg;}

	
	/*
	 * getter a jatekosok attributumra
	 */
	public List<Jatekos> getJatekosok() {
		return this.jatekosok;
	}

	/*
	 * Csak ciszterma osztalyon ertelmezett getter a ciszterna termeltpumpak
	 * attributumra
	 */
	public ArrayList<Pumpa> getTermeltPumpak() {
		return null;
	}

	/*
	 * Csak ciszterma osztalyon ertelmezett szerelo hivja meg ciszternara. Default
	 * implementációja üres.
	 */
	public void PumpaEltavolit() {

	}

	/*
	 * Csak Pumpa osztalyon ertelmezett Visszaadja adott pumpa kimeneti csovet
	 */
	public Mezo getKimenet() {
		return null;
	}
	
	/*
	 * Csak Pumpa osztalyon ertelmezett Visszaadja adott pumpa bemeneti csovet
	 */
	public Mezo getBemenet() {
		return null;
	}

	/**
	 * Visszaadja hogy maximum hány játékos lehet a mezőn.
	 * @return maxJatekosok
	 */
	public int getMaxJatekosok() {
		return maxJatekosok;
	}

}
