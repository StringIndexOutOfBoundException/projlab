package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Random;

/**
 * A pumpa a pálya aktív eleme.
 * Azért felelős, hogy az egyik csőből másik csőbe jutassa a vizet.
 * Továbbá a pumpában van egy tartály, amibe vizet tárolhat.
 * Végül a pumpa random (a sivatagban levő külső erők miatt) el tud romlani, ilyenkor nem működik.
 */
public class Pumpa extends AktivElem {

	private  static int MAXVIZ = 1;
	private int vizmennyiseg;
	private Mezo bemenet;
	private Mezo kimenet;
	
	public Pumpa() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param kimenet
	 * @param bemenet
	 */
	public void Atallit(Mezo kimenet, Mezo bemenet) {
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
