package projlab;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A cső osztály a pálya egy passzív eleme.
 * Ő felelős a víz szállításáért.
 * Ha kijukad akkor a benne levő víz kifolyik és a sivatagban elveszik.
 */
public class Cso extends Mezo {
	//private int maxJatekosok = 1;

	/**
	 * A cső konstruktora
	 * Beállítja a maxJatekosok változót 1-re
	 */
	public Cso() {
		super(1);
	}
	private int vizmennyiseg;

	/**
	 * A szerelĹ megjavĂ­t egy elemet, amin ĂŠppen ĂĄll
	 */
	@Override
	public void Megjavit() {
		System.out.println("FĂźggvĂŠnyhĂ­vĂĄs: " + this + ".Megjavit()");
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
		System.out.println("Függvényhívás: " + this + " Kilyukaszt()");
		setMukodik(false);
	}

	/**
	 * <p>A függvény megnöveli a csőbe levő víz mennyiségét megadott értékkel (nem növekedhet MAXVÍZ felé).</p>
	 * Ezt a Pumpa {@link Pumpa}, Forrás {@link Forras}  fogja meghívni
	 * @param meret a víz mennysége amit a csőbe pumpálunk
	 * @override
	 */
	@Override
	public void VizetNovel(int meret) throws Exception {
		System.out.println("Függvényhívás:" + this +": VizetNovel( " + meret + " ) ");
		if(meret + vizmennyiseg > 1){	//MAXVIZ
			int tulfolyas = meret+vizmennyiseg-1;
			vizmennyiseg += meret - tulfolyas;
			throw new Exception(String.valueOf(tulfolyas));
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
	public void VizetCsokkent(int meret) throws Exception {
		System.out.println("Függvényhívás: " + this +": VizetCsokkent( " + meret + " ) ");

		if(meret <= vizmennyiseg) {
			vizmennyiseg -= meret;
		}
		else{
			int ki = vizmennyiseg;
			vizmennyiseg = 0;
			throw new Exception(String.valueOf(ki));
		}

	}

	/**
	 * Ez a vizmennyiség változóhoz egy getter, visszaadja a csőben levő víz értkét
	 * @return int típusú, azt adja vissza, hogy mennyi víz van a csőben éppen.
	 */
	public int getVizmennyiseg(){
		return vizmennyiseg;
	}
	
	/**
	 * Egy mező szomszádaihoz hozzáad egy új mezőt
	 * @param m a hozzaadnado mező
	 */
	@Override
	public void SzomszedHozzaad(Mezo m) {
		ArrayList<Mezo> szomszedok = GetSzomszedok();
		if(szomszedok.size() < 2)
			szomszedok.add(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedHozzaad("+m+")");
	}

	public void setVizmennyiseg(int viz){vizmennyiseg = viz;}
}
