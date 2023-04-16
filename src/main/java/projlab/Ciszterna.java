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

	public void PumpaEltavolit() {
	}

	public void PumpatKeszit() {
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
			catch (Exception e){/* Hmm ez nem szép itt ;) */}
		}
	}
}
