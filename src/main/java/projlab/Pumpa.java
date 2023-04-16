package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * A pumpa a pálya aktív eleme.
 * Azért felelős, hogy az egyik csőből másik csőbe jutassa a vizet.
 * Továbbá a pumpában van egy tartály, amibe vizet tárolhat.
 * Végül a pumpa random (a sivatagban levő külső erők miatt) el tud romlani, ilyenkor nem működik.
 */
public class Pumpa extends AktivElem {
	private  static int MAXVIZ = 1;
	private int vizmennyiseg;
	private Cso bemenet;
	private Cso kimenet;


	/**
	 * @param kimenet
	 * @param bemenet
	 */
	public void Atallit(Cso kimenet, Cso bemenet) {
		this.bemenet = bemenet;
		this.kimenet = kimenet;
	}

	/**
	 * Átállítja a pumpa állapotát működőre.
	 * @Overrride
	 */
	@Override
	public void Megjavit() {
		setMukodik(true);
	}

	/**
	 * A függvény azért felelős, hogy a pumpa a vizet pumpálja.
	 * @Override
	 */
	@Override
	public void Frissit() {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		int befolyoviz = bemenet.getVizmennyiseg();
		vizmennyiseg += befolyoviz;
		try {
			bemenet.VizetCsokkent(befolyoviz);
		}
		catch (BufferUnderflowException e){
			//System.out.println("Nincs  eleg viz a csoben " + e);
		}


		int kifolyoviz = kimenet.getVizmennyiseg();
		kifolyoviz = MAXVIZ - kifolyoviz;
		if (vizmennyiseg < kifolyoviz){
			kifolyoviz = vizmennyiseg;
		}

		try {
			kimenet.VizetNovel(kifolyoviz);
		}
		catch (BufferOverflowException e){
			//System.out.println("Nem tudsz ennyi vezet a csobe pumpalni: " + kifolyoviz + " " + e );
		}
		vizmennyiseg -= kifolyoviz;


	}
	/**
	 * Bemenetet adja vissza
	 */
public Cso getBemenet() {
	return this.bemenet;
}
/**
  * Kimenetet adja vissza
  */
public Cso getKimenet() {
	return this.kimenet;
}
}
