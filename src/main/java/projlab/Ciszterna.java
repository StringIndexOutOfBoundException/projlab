package projlab;

import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


/**
 * A Forrás a pálya aktív eleme.
 * Azért felelős, hogy vizet nyeljen el, amit a csőhálózaton keresztűl kap.
 * Továbbá Pumpákat és Csöveket termel.
 */
public class Ciszterna extends AktivElem {
	//private int maxJatekosok = Integer.MAX_VALUE;

	private  static int MAXVIZ = 1;
	private ArrayList<Pumpa> termeltpumpak;
	
	public Ciszterna() {
		termeltpumpak = new ArrayList<Pumpa>();
	}

	
	/**
	 * Torli a termeltpumpak kollekcio utolso pumpajat,
	 *  ezt a pumpat veszi fel a szerelo a PumpatFelvesz fuggvenyben
	 */
	public void PumpaEltavolit() {
		termeltpumpak.remove(termeltpumpak.size()-1);
	}
	/**
	 * A ciszternak ezzel a fuggvennyel keszitenek uj pumpakat minden hivas utan 0-2 szam intervallumban
	 */
	public void PumpatKeszit() {
		//Random darab uj pumpat rak bele a termeltpumpakba 0-2 kozott
		Random rand=new Random();
		int randomNum= rand.nextInt(3);
		for(int i=0; i<randomNum;++i) {
			Pumpa p=new Pumpa();
			termeltpumpak.add(p);
			p.SzomszedHozzaad(this);
			this.SzomszedHozzaad(p);
		}
	}
	public void CsovetKeszit() {
		Cso ujcso = new Cso();
		SzomszedFelcsatol(ujcso);
		ujcso.SzomszedFelcsatol(this);
	}
	/*
	 * getter a termeltpumpakra
	 */
	public ArrayList<Pumpa> getTermeltPumpak() {
		return this.termeltpumpak;
	}

	/**
	 * Frissít függvény a víz folyásáért felelős
	 * Itt azt csinálja, hogy a Ciszterna elnyeli vizet
	 * @Override
	 * @since 0.1
	 */
	@Override
	public void Frissit() {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		ArrayList<Mezo> szomszedok = GetLeszedhetoSzomszedok();
		for (var cso : szomszedok) {
			try {
				cso.VizetCsokkent(MAXVIZ);
			}
			catch (Exception e){/* Hmm ez nem szép itt ;) */}
		}
	}
	
	/**
	 * Egy mező szomszédaihoz hozzáad egy új mezőt
	 * @param m a hozzáadandó mező
	 */
	@Override
	public void SzomszedHozzaad(Mezo m) {
		ArrayList<Mezo> szomszedok = super.GetSzomszedok();
		szomszedok.add(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedHozzaad("+m+")");
	}
}
