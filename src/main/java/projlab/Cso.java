package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * A cső osztály a pálya egy passzív eleme.
 * Ő felelős a víz szállításáért.
 * Ha kijukad akkor a benne levő víz kifolyik és a sivatagban elveszik.
 */
public class Cso extends Mezo {
	private int vizmennyiseg;

	public void Megjavit() {
		setMukodik(true);
	}

	public void PumpaEpit() {
	}

	/**
	 * A függvény kilyukasztja a csövet, azaz átálllítja a mukodik változoót arra, hogy nem működik (false)
	 * @Override
	 */
	@Override
	public void Kilyukaszt() {
		setMukodik(false);
	}

	/**
	 * <p>A függvény megnöveli a csőbe levő víz mennyiségét megadott értékkel (nem növekedhet MAXVÍZ felé).</p>
	 * Ezt a Pumpa {@link Pumpa}, Forrás {@link Forras}  fogja meghívni
	 * @param meret a víz mennysége amit a csőbe pumpálunk
	 * @override
	 */
	@Override
	public void VizetNovel(int meret) {
		System.out.println("Függvényhívás: " + this +": VizetNovel( " + meret + ") ");
		if(meret + vizmennyiseg > 1){	//MAXVIZ
			throw new BufferOverflowException();
		}
		else {
			vizmennyiseg += meret;
		}
	}

	/**
	 * <p>A függvény csökkenti a csőbe levő víz mennyiségét megadott értékkel (nem csökkenhet 0 alá).</p>
	 * Ezt a Pumpa {@link Pumpa}, Forrás {@link Ciszterna}  fogja meghívni
	 * @param meret a víz mennysége amit a csőből kiszívunk.
	 */
	@Override
	public void VizetCsokkent(int meret) {
		System.out.println("Függvényhívás: " + this +": VizetCsokkent( " + meret + ") ");

		if(meret < vizmennyiseg) {
			vizmennyiseg -= meret;
		}
		else{ throw new BufferUnderflowException();}

	}

	/**
	 * Ez a vizmennyiség változóhoz egy getter, visszaadja a csőben levő víz értkét
	 * @return int típusú, azt adja vissza, hogy mennyi víz van a csőben éppen.
	 */
	public int getVizmennyiseg(){
		return vizmennyiseg;
	}

}
