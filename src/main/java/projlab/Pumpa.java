package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A pumpa a pálya aktív eleme.
 * Azért felelős, hogy az egyik csőből másik csőbe jutassa a vizet.
 * Továbbá a pumpában van egy tartály, amibe vizet tárolhat.
 * Végül a pumpa random (a sivatagban levő külső erők miatt) el tud romlani, ilyenkor nem működik.
 */
public class Pumpa extends AktivElem {

	private  static int MAXVIZ = 1;
	private int maxCso = 5;
	private int vizmennyiseg;
	private Mezo bemenet;
	private Mezo kimenet;
	

	/**
	 * Egy mező szomszédaihoz hozzáad egy új mezőt
	 * @param m a hozzáadandó mező
	 */
	@Override
	public void SzomszedHozzaad(Mezo m) {
		ArrayList<Mezo> szomszedok = super.GetSzomszedok();
		if(szomszedok.size() < maxCso)
			szomszedok.add(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedHozzaad("+m+")");
	}

	/**
	 * Egy játékos átállítja egy pumpa be és kimenetét
	 * @param kimenet amire át lesz állítva a kimenet
	 * @param bemenet amire át lesz állítva a bemenet
	 */
	@Override
	public void Atallit(Mezo kimenet, Mezo bemenet) {
		this.bemenet = bemenet;
		this.kimenet = kimenet;
		System.out.println("Függvényhívás:  " + this + ".Atallit("+kimenet+", "+bemenet+")");
		System.out.println("Bemenet állítása: "+ this.bemenet +":Mezo\n"
				+ "Kimenet állítása: "+ this.kimenet +":Mezo");
	}

	/**
	 * A szerelő megjavít egy elemet, amin éppen áll
	 */
	@Override
	public void Megjavit() {
		System.out.println("Függvényhívás: " + this + ".Megjavit()");
		setMukodik(true);
	}

	/**
	 * A függvény azért felelős, hogy a pumpa a vizet pumpálja.
	 * Továbbá ez felelős a random elromlásokért
	 * @Override
	 */
	@Override
	public void Frissit() {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		Random rand = new Random();
		if(rand.nextDouble(1) > 0.5){
			setMukodik(false);
		}
		int befolyoviz = MAXVIZ;
		vizmennyiseg += befolyoviz;
		try {
			bemenet.VizetCsokkent(befolyoviz);
		}
		catch (Exception e){
			vizmennyiseg -= befolyoviz-Integer.parseInt(e.getMessage());
		}


		int kifolyoviz = kimenet.getVizmennyiseg();
		kifolyoviz = MAXVIZ;

		if (kifolyoviz <= vizmennyiseg) {

			try {
				kimenet.VizetNovel(kifolyoviz);
			} catch (Exception e) {
				kifolyoviz = kifolyoviz - Integer.parseInt(e.getMessage());
			}
			vizmennyiseg -= kifolyoviz;

		}
	}

	/**
	 * getter a bemenetre
	 */
	public Mezo getBemenet() {
	return this.bemenet;
}
	/**
	 * getter a Kimenetre
	 * Hasznalva van
	 */
public Mezo getKimenet() {
	return this.kimenet;
}

}
