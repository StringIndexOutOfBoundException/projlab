package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * A cs� oszt�ly a p�lya egy passz�v eleme.
 * � felel�s a v�z sz�ll�t�s��rt.
 * Ha kijukad akkor a benne lev� v�z kifolyik �s a sivatagban elveszik.
 */
public class Cso extends Mezo {
	//private int maxJatekosok = 1;

	/**
	 * A cs� konstruktora
	 * Be�ll�tja a maxJatekosok v�ltoz�t 1-re
	 */
	public Cso() {
		super(1);
	}
	private int vizmennyiseg;

	public void Megjavit() {
	}

	public void PumpaEpit() {
	}

	/**
	 * A f�ggv�ny kilyukasztja a cs�vet, azaz �t�lll�tja a mukodik v�ltozo�t arra, hogy nem m�k�dik (false)
	 * @Override
	 */
	@Override
	public void Kilyukaszt() {
		System.out.println("F�ggv�nyh�v�s: " + this + "Kilyukaszt()");
		super.setMukodik(true);
	}

	/**
	 * <p>A f�ggv�ny megn�veli a cs�be lev� v�z mennyis�g�t megadott �rt�kkel (nem n�vekedhet MAXV�Z fel�).</p>
	 * Ezt a Pumpa {@link Pumpa}, Forr�s {@link Forras}  fogja megh�vni
	 * @param meret a v�z mennys�ge amit a cs�be pump�lunk
	 * @override
	 */
	@Override
	public void VizetNovel(int meret) {
		System.out.println("F�ggv�nyh�v�s: " + this +": VizetNovel( " + meret + ") ");
		if(meret + vizmennyiseg > 1){	//MAXVIZ
			throw new BufferOverflowException();
		}
		else {
			vizmennyiseg += meret;
		}
	}

	/**
	 * <p>A f�ggv�ny cs�kkenti a cs�be lev� v�z mennyis�g�t megadott �rt�kkel (nem cs�kkenhet 0 al�).</p>
	 * Ezt a Pumpa {@link Pumpa}, Forr�s {@link Ciszterna}  fogja megh�vni
	 * @param meret a v�z mennys�ge amit a cs�b�l kisz�vunk.
	 */
	@Override
	public void VizetCsokkent(int meret) {
		System.out.println("F�ggv�nyh�v�s: " + this +": VizetCsokkent( " + meret + ") ");

		if(meret < vizmennyiseg) {
			vizmennyiseg -= meret;
		}
		else{ throw new BufferUnderflowException();}

	}

	/**
	 * Ez a vizmennyis�g v�ltoz�hoz egy getter, visszaadja a cs�ben lev� v�z �rtk�t
	 * @return int t�pus�, azt adja vissza, hogy mennyi v�z van a cs�ben �ppen.
	 */
	public int getVizmennyiseg(){
		return vizmennyiseg;
	}

}
