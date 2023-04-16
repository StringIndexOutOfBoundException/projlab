package projlab;
import java.util.List;

/**
 * A Forrás a pálya aktív eleme.
 * Azért felelős, hogy vizet készítsen, amit a csőhálózat elszállít.
 */
public class Forras extends AktivElem {
	//private int maxJatekosok = Integer.MAX_VALUE;
	private static int MAXVIZ = 1;
	/**
	 * Frissít függvény a víz folyásáért felelős
	 * Itt azt csinálja, hogy a Forrás termel vizet, majd az a hozzá kapcsolódó csőbe tölti
	 * @Override
	 * @since 0.1
	 */
	@Override
	public void Frissit() {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		List<Mezo> szomszedok = super.GetLeszedhetoSzomszedok();
		int szomszedszam = szomszedok.size();
		if(szomszedszam > 1){
			throw new IllegalArgumentException();
		} else if (szomszedszam == 1) {
			szomszedok.get(0).VizetNovel(MAXVIZ);
		}
	}

}
