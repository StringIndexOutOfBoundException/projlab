package projlab;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import static projlab.Main.pontsz;

/**
 * A Forrás a pálya aktív eleme. Azért felelős, hogy vizet nyeljen el, amit a
 * csőhálózaton keresztűl kap. Továbbá Pumpákat és Csöveket termel. Összesen
 * maximum 20 hozzácsatlakozott csöve lehet egy csiszternának és bármennyi pumpát termelhet.
 */
public class Ciszterna extends Mezo {

	private ArrayList<Mezo> termeltpumpak;

	public Ciszterna() {
		super(Integer.MAX_VALUE);
		view = new CiszternaView();
		termeltpumpak = new ArrayList<Mezo>();
		maxSzomszedok = 20;
	}

	/**
	 * Ha a termeltpumpák elemszáma 0, akkor nincs felvehető pumpa.
	 * Ha a termeltpumpák elemszáma nagyobb mint 0, akkor az termeltpumpak listaban
	 * az utolso pumpa eltávolításra kerül.
	 */
	public void PumpaEltavolit() {
		// ha van pumpa a ciszterna korul
		if (termeltpumpak.size() >= 1)
			termeltpumpak.remove(termeltpumpak.size() - 1);
	}

	/**
	 * A ciszternak ezzel a fuggvennyel keszitenek uj pumpakat minden hivas utan 0-2
	 * szam intervallumban ha determinisztikus. Ha a randomizálás ki van kapcsolva akkor 1 pumpát termel.
	 */
	public void PumpaKeszit() {
		// Random darab uj pumpat rak bele a termeltpumpakba 0-2 kozott
		Random rand = new Random();
		int randomNum = 1;
		if (doRandomThings) // ha determinisztikus a mukodes
			randomNum = rand.nextInt(3);
		for (int i = 0; i < randomNum; ++i) {
			Pumpa p = new Pumpa(false);
			termeltpumpak.add(p);
		}

	}

	/**
	 * A ciszterna egy magához kapcsolódó szabad végű csövet hoz létre.
	 */
	public void CsovetKeszit() {
		Random rand = new Random();
		int randomNum = 1;
		if (doRandomThings) // ha determinisztikus a mukodes
			randomNum = rand.nextInt(3);
		for (int i = 0; i < randomNum; ++i) {
			Cso ujcso = new Cso();
			ujcso.SzomszedHozzaad(this);
			this.SzomszedHozzaad(ujcso);
		}
	}

	/**
	 * getter a termeltpumpakra
	 */
	public List<Mezo> getTermeltPumpak() {
		return this.termeltpumpak;
	}

	/**
	 * A szomszédok listájában mindenkire meghívja a VizetCsokkent függvényt. Azaz
	 * minden rá csatlakoztatott csőtől elveszi a benne található vizet. (ha nincs
	 * víz a csőben, nem kap vizet) Meghívja a PumpatKeszit, CsovetKeszit
	 * függvényeket is.
	 * @Override
	 */
	public void Frissit() {
		ArrayList<Mezo> szomszedok = GetSzomszedok(); // GetLeszedhetoSzomszedok lesz
		for (var cso : szomszedok) {
			try {
				int menny1 = cso.getVizmennyiseg();
				cso.VizetCsokkent(1);
				int menny2 = cso.getVizmennyiseg();

				//Todo: szereloPontotNovel();
				if(menny1 > menny2)
					pontsz.szereloPontotNovel(1);
			} catch (Exception e) {
				/* Hmm ez nem szép itt ;) */}
		}
		this.CsovetKeszit();
		this.PumpaKeszit();
	}
}