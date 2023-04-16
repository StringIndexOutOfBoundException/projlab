package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * A pumpa a p�lya akt�v eleme.
 * Az�rt felel�s, hogy az egyik cs�b�l m�sik cs�be jutassa a vizet.
 * Tov�bb� a pump�ban van egy tart�ly, amibe vizet t�rolhat.
 * V�g�l a pumpa random (a sivatagban lev� k�ls� er�k miatt) el tud romlani, ilyenkor nem m�k�dik.
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
	 * �t�ll�tja a pumpa �llapot�t m�k�d�re.
	 * @Overrride
	 */
	@Override
	public void Megjavit() {
		setMukodik(true);
	}

	/**
	 * A f�ggv�ny az�rt felel�s, hogy a pumpa a vizet pump�lja.
	 * @Override
	 */
	@Override
	public void Frissit() {
		System.out.println("F�ggv�nyh�v�s: " + this +": Frissit() ");
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
