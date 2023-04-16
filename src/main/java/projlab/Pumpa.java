package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * A pumpa a pálya aktív eleme.
 * Azért felelõs, hogy az egyik csõbõl másik csõbe jutassa a vizet.
 * Továbbá a pumpában van egy tartály, amibe vizet tárolhat.
 * Végül a pumpa random (a sivatagban levõ külsõ erõk miatt) el tud romlani, ilyenkor nem mûködik.
 */
public class Pumpa extends AktivElem {
	private  static int MAXVIZ = 1;
	private int vizmennyiseg;
	private Cso bemenet;
	private Cso kimenet;

	//private int maxJatekosok = Integer.MAX_VALUE;


	/**
	 * @param kimenet
	 * @param bemenet
	 */
	public void Atallit(Cso kimenet, Cso bemenet) {
		this.bemenet = bemenet;
		this.kimenet = kimenet;
	}

	/**
	 * Átállítja a pumpa állapotát mûködõre.
	 * @Overrride
	 */
	@Override
	public void Megjavit() {
		setMukodik(true);
	}

	/**
	 * A függvény azért felelõs, hogy a pumpa a vizet pumpálja.
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

}
