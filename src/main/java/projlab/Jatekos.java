package projlab;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A jatekos osztály egy irányítható karaktert valósít meg a játékban,
 * ez lehet Szabotőr{@link Szabotor} vagy Szerelő{@link Szerelo}
 */
public abstract class Jatekos {
	private Mezo helyzet;		//a játékos helyzete
	protected int maxHatizsakKapacitas;		//a játékos hátizsákjainak maximum kapacitása
	protected ArrayList<Pumpa> pumpaHatizsak;		//a játékos hátizsákja, ami pumpákat tartalmaz
	protected ArrayList<Mezo> csoHatizsak;		//a játékos hátizsákja, ami csöveket tartalmaz

	public Jatekos() {
		pumpaHatizsak = new ArrayList<>();
		csoHatizsak = new ArrayList<>();
	}


	/**
	 * A játékos lépést kezdeményez egy általa választott mezőre
	 * @param m a választott mező referenciája
	 */
	public void Lep(Mezo m) {
		boolean elengedve = helyzet.JatekosElenged(this);
		List<Mezo> szomszedok = helyzet.GetSzomszedok();

		if(elengedve && szomszedok.contains(m)){
			boolean elfogadva = m.JatekosElfogad(this);
			if(elfogadva)
				helyzet.JatekosEltavolit(this);
		}
	}

	/**
	 * A játékos kilyukasztja azt az elemet, amin éppen áll.
	 */
	public void Lyukaszt(){	helyzet.Kilyukaszt(); }

	/**
	 * A játékos átállítja a pumpa be és kimenetét
	 */
	public void Allit(Mezo kimenet, Mezo bemenet) {	helyzet.Atallit(kimenet, bemenet); }

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void Javit(){}

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void PumpatFelvesz(){}

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void CsovetLecsatol(){}

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void EgeszCsovetLecsatol(){}

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void CsovetFelcsatol(){}

	/**
	 * Nincs alap implementáció, a szerelő osztály felüldefiniálja
	 */
	public void PumpatEpit(){}

	/**
	 * A játékos ragadóssá teszi a mezőt, amin áll
	 */
	public void RagadossaTesz(){ helyzet.Ragad(); }

	/**
	 * Jatekos tartózkodási helyéhez tartózó szomszédok lekerdezese
	 * @return tartótkodási helyének szomszédai
	 */
	public ArrayList<Mezo> GetSzomszedok(){ return helyzet.GetSzomszedok(); }

	/**
	 * A játékos helyzetének referenciáját adja vissza.
	 * @return a játékos helyzete
	 */
	public Mezo getHelyzet(){ return helyzet; }

	/**
	 * A játékos helyzetének beállítása
	 * @param m - az új helyzet referenciája
	 */
	public void setHelyzet(Mezo m) { helyzet = m; }

	/**
	 * Visszaadja a hátizsákok maximum kapacitását
	 * @return maximum kapacitás
	 */
	public int getMaxHatizsakKapacitas() { return this.maxHatizsakKapacitas; }

	/**
	 * Beállítja a hátizsákok maximum kapacitását
	 * @param c - Az új kapacitás értéke
	 */
	public void setMaxHatizsakKapacitas(int c) { maxHatizsakKapacitas = c; }

	/**
	 * Visszaadja a pumpákat tartalmazó hátizsákot
	 * @return a hátizsákban található pumpák listája
	 */
	public ArrayList<Pumpa> getPumpaHatizsak(){	return this.pumpaHatizsak; }

	/**
	 * Visszaadja a csöveket tartalmazó hátizsákot
	 * @return a hátizsákban található csövek listája
	 */
	public ArrayList<Mezo> getCsoHatizsak() { return csoHatizsak; }

}
