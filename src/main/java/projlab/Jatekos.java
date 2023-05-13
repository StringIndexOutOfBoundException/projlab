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

	private ArrayList<Mezo> pumpaHatizsak;
	private ArrayList<Mezo> csoHatizsak;

	public Jatekos() {
		pumpaHatizsak = new ArrayList<>();
		csoHatizsak = new ArrayList<>();

	}

	/**
	 * NEM HASZNALANDÓ! A PARAMÉTERES VERZIÓT KELL MAJD HASZNALNI!
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

	/**
	 * A játékos lép a paraméterként kapott mezőre
	 * NINCS IMPLEMENTALVA, DE EZT A PARAMÉTEREZETT VERZIÓT KELL MAJD HASZNALNI A PARAMÉTER NÉLKÜLI LÉPÉS HELYETT!
	 */
	public void Lep(Mezo m)
	{
		System.out.println("lépés...");
	}

	/**
	 * NEM HASZNALANDO! A PARAMÉTERES VERZIÓT KELL HASZNALNI!
	 * Jatekos atallitja egy pumpa be es kimenetet
	 */
	public void Allit() {
		System.out.println("Függvényhívás: " + this + ".Allit()");
		
		ArrayList<Mezo> szomszedok = GetSzomszedok();
		
		helyzet.Atallit(szomszedok.get(0), szomszedok.get(1));
	}

	/**
	 * EZT KELL HASZNALNI, NEM A PARAMÉTER NÉLKÜLIT!
	 * Jatekos atallitja egy pumpa be es kimenetet
	 * @param be bemeneti mezo
	 * @param ki kimeneti mezo
	 */
	public void Allit(Mezo be, Mezo ki) {
		System.out.println("Függvényhívás: " + this + ".Allit()");

		helyzet.Atallit(be, ki);
	}

	public void Javit()
	{
		System.out.println("Javít...");
	}

	public void Lyukaszt()
	{
		System.out.println("Lyukaszt...");
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
	 * @param m - Az új helyzet
	 */
	public void setHelyzet(Mezo m) {
		helyzet = m;
	}

	/*
	 * getter a jatekos pumpaHatizsakjra
	 */
	public ArrayList<Mezo> getPumpaHatizsak(){
		return this.pumpaHatizsak;
	}
	
	/**
	 * getter a maxHatizsakKapacitasra
	 */ 
	public int getMaxHatizsakKapacitas() {
		return this.maxHatizsakKapacitas;
	}
	
	
	/**
	 * Jatekos helyzetenek a szomszedainak a lekerdezese
	 * @return helyzetének a szomszédai
	 */
	public ArrayList<Mezo> GetSzomszedok(){
		System.out.println("Visszatérés: a pumpa szomszédaival");
		return helyzet.GetSzomszedok();
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
	 * Kívülről lekéri a játékosra jellemző, a hátizsákban tárolható maximális
	 * elemek számát
	 * 
	 */
	public ArrayList<Mezo> getCsoHatizsak() {
		return csoHatizsak;
	}

	/**
	 * Csak hogy bármilyen játékoson hívható legyen a függvény
	 */
	public void PumpatEpit()
	{
		//A kiírást ki lehet szedni
		System.out.println("PumpatEpit...");
	}

	/**
	 * Csak hogy bármilyen játékoson hívható legyen a függvény
	 */
	public void CsovetFelcsatol()
	{
		//A kiírást ki lehet szedni
		System.out.println("CsovetFelcsatol...");
	}

	/**
	 * Csak hogy bármilyen játékoson hívható legyen a függvény
	 */
	public void PumpatFelvesz()
	{
		//A kiírást ki lehet szedni
		System.out.println("PumpatFelvesz...");
	}

	/**
	 * Ez az lesz amikor az egész csövet lecsatoljuk, de ez még sehol sincs implementálva
	 */
	public void EgeszCsovetLecsatol(Mezo m)
	{
		//A kiírást ki lehet szedni
		System.out.println("EgeszCsovetLecsatol... NINCS IMPLEMENTALVA SEHOL");
	}

	/**
	 * Csak hogy bármilyen játékoson hívható legyen a függvény
	 * KELL PARAMÉTER!
	 */
	public void CsovetLecsatol(Mezo m)
	{
		System.out.println("CsovetLecsatol...");
	}

	public void CsuszossaTesz()
	{
		System.out.println("CsuszossaTesz...");
	}

	public void RagadossaTesz()
	{
		System.out.println("RagadossaTesz...");
	}

}
