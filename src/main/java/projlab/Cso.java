package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

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
	private int vizmennyiseg;

	public void Megjavit() {
		setMukodik(true);
	}

	/**
	 * Ezt a fuggveny a szerelo hivja meg azon a csovon amin all, mikor uj pumpat akar lerakni
	 * A fuggveny az eredeti csovet "kette vagja", ugy hogy 
	 * az "eredeti cso lesz az elso fele "a masodik fele pedig  egy uj cso lesz amit a fuggveny hoz letre
	 * es  a ketto koze a szerelo egy pumpat rak
	 */
	public void PumpaEpit() {
		//Teszteleskor letrehozott adatokat nezze meg
		//p1 az a 0. szomszedja a csonek
		//p az az 1. szomszedja a csonek
		//this=cs
		//ujPumpa=this.getJatekosok().get(0).getpumpaHatizsak().get(0);
		Pumpa ujPumpa=this.getJatekosok().get(0).getPumpaHatizsak().get(0);
		Jatekos sz=this.getJatekosok().get(0);
		Mezo p1=this.GetSzomszedok().get(0);
		Mezo p= this.GetSzomszedok().get(1);
		
		Cso ujCso=new Cso();
		System.out.println("Ujcso id:" + ujCso);
		
		ujPumpa.Atallit(ujCso,this);	
		this.SzomszedHozzaad(ujPumpa);
		ujCso.SzomszedHozzaad(ujPumpa);
		ujCso.SzomszedHozzaad(p);
		ujPumpa.SzomszedHozzaad(ujCso);
		ujPumpa.SzomszedHozzaad(this);
		p.SzomszedHozzaad(ujCso);
		p.Atallit(p.getKimenet(), ujCso);
		p.SzomszedTorol(this);
		this.SzomszedTorol(p);
		
		System.out.println("p bemenete:" + p.getBemenet() + 
				"\nujPumpa bemenete:" + ujPumpa.getBemenet() +
		", kimenete:" + ujPumpa.getKimenet());
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
	public void setVizmennyiseg(int viz){vizmennyiseg = viz;}
}
