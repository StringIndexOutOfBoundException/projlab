package projlab;

import java.nio.BufferUnderflowException;
import java.util.List;

/**
 * A Forrás a pálya aktív eleme.
 * Azért felelős, hogy vizet nyeljen el, amit a csőhálózaton keresztűl kap.
 * Továbbá Pumpákat és Csöveket termel.
 */
public class Ciszterna extends AktivElem {
	private  static int MAXVIZ = 1;
	private List <Pumpa> termeltpumpak;

	public Ciszterna() {
		 termeltpumpak=new ArrayList <Pumpa> ();
	}
	//utolso pumpat torli a listabol
	public void PumpaEltavolit() {
		termeltpumpak.remove(termeltpumpak.size()-1);
	}
	//getter a termeltpumpaknak
	public List <Pumpa> ciszternaTermeltPumpak() {
		return this.termeltpumpak;
	}
	//termeltpumpak meretet adja vissza
	public int CiszternaPumpakSzama() {
		return this.termeltpumpak.size();
	}
	public void PumpatKeszit() {
			//Random darab uj pumpat rak bele a termeltpumpakba 0-2 kozott
		Random rand=new Random();
		int randomNum= rand.nextInt((2-0)+1)+0;
		for(int i=0; i<randomNum;++i) {
			Pumpa p=new Pumpa();
			termeltpumpak.add(p);
			p.SzomszedHozzaad(this);
			this.SzomszedHozzaad(p);
		}
	}

	public void CsovetKeszit() {
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
		List<Mezo> szomszedok = GetLeszedhetoSzomszedok();
		for (var cso : szomszedok) {
			try {
				cso.VizetCsokkent(MAXVIZ);
			}
			catch (BufferUnderflowException e){/* Hmm ez nem szép itt ;) */}
		}
	}
}
