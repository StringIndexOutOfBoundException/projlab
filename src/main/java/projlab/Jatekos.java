package projlab;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

	/**
	 * Amikor a játékos lépni akar, akkor ezt a függvényt hívja meg.
	 * A szomszédos mezők közül választhat a felhasználó, és a kiválasztott mezőre lép.
	 */
	public void Lep() {
		System.out.println("Függvényhívás:" + helyzet + ": GetSzomszedok()");
		List<Mezo> szomszedok = helyzet.GetSzomszedok();
		System.out.println("Visszatérés:" + helyzet + " mező szomszédjai:");
		Mezo eredetiHelyzet = helyzet;
		//Kiírni a szomszédokat
		for (int i = 0; i < szomszedok.size(); i++) {
			System.out.println((i+1) + ". " + szomszedok.get(i));
		}
		//Kérdés a felhasználótól
		System.out.print("Válaszd ki melyik mezőre lépsz: ");
		Scanner sc = new Scanner(System.in);
		int valasz;
		try
		{
			valasz = sc.nextInt();

			if (valasz < 1 || valasz > szomszedok.size()) {
				throw new Exception();
			}
		}
		//Ha nem számot adott meg, vagy nem létező szomszédot választott, akkor sikertelen a lépés
		catch (Exception e)
		{
			System.out.println("Nem megfelelő bemenet!");
			return;
		}

		//Lépés
		Mezo cel = szomszedok.get(valasz-1);
		//Ha sikeresen lépett, akkor eltávolítja magát a régi mezőről
		System.out.println("Függvényhívás: " + cel + ": JatekosElfogad(" + this +")");
		//Megpróbáljuk, hogy rá tud-e lépni a játékos a kiválasztott mezőre
		boolean sikeresLepes = cel.JatekosElfogad(this);
		System.out.println("Visszatérés: " + sikeresLepes);
		//Ha sikeres volt a lépés, akkor eltávolítjuk a játékost a régi mezőről
		if (sikeresLepes) {
			System.out.println("Függvényhívás: " + eredetiHelyzet + ": JatekosEltavolit(" + this +")");
			eredetiHelyzet.JatekosEltavolit(this);
		}

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
