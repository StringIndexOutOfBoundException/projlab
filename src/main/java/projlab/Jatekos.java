package projlab;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A jatekos az amit a játékos irányíthat, ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private int maxHatizsakKapacitas;
	private Mezo helyzet;
	private List<Pumpa> pumpaHatizsak;
	private List<Cso> csoHatizsak;

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
			sc.nextLine(); //Ez a sor abban segít hogy linuxon is jól működjön a beolvasás
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
	 Jatekos helyzetenek beallitasa
	 @param m Ez lesz a jatekos helyzete
	 */
	public void setHelyzet(Mezo m) {
		helyzet = m;
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
