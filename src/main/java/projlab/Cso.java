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
	}

	public void PumpaEpit() {
		//Teszteleskor letrehozott adatokat nezze meg
		//p1 az a 0. szomszedja a csonek
		//p az az 1. szomszedja a csonek
		//this=cs
		Pumpa ujPumpa= new Pumpa();
		Cso ujCso=new Cso();
	
		
		ujPumpa.setBemenet(this);
		ujPumpa.setKimenet(ujCso);
		this.SzomszedHozzaad(ujPumpa);
		ujCso.SzomszedHozzaad(ujPumpa);
		ujCso.SzomszedHozzaad(this.GetSzomszedok().get(1));
		ujPumpa.SzomszedHozzaad(ujCso);
		ujPumpa.SzomszedHozzaad(this);
		this.GetSzomszedok().get(1).SzomszedHozzaad(ujCso);
		this.GetSzomszedok().get(1).setBemenet(ujCso);
		this.GetSzomszedok().get(1).SzomszedTorol(this);
		this.SzomszedTorol(this.GetSzomszedok().get(1));
		//kivesszuk az utolso pumpat a jatekos pumpaHatizsakjabol
		this.getJatekosok().get(0).getpumpaHatizsak().remove(this.getJatekosok().get(0).getpumpaHatizsak().size()-1);
		
		//kette vagtuk a "cs" csovet, az elso fele "cs" a masodik pedig "ujCso" a ketto koze ujPumpat berakjuk
		//a p pumpa bemenete lett az ujCso -> a "p" pumpa mar nem a "cs" pumpaja
		//az ujPumpa bemenete "cs" kimenete "ujCso"
	}

	/**
	 * A függvény kilyukasztja a csövet, azaz átálllítja a mukodik változoót arra, hogy nem működik (false)
	 * @Override
	 */
	@Override
	public void Kilyukaszt() {
		System.out.println("Függvényhívás: " + this + "Kilyukaszt()");
		super.setMukodik(true);
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
