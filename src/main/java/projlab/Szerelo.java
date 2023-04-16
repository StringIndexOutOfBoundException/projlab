package projlab;//

import java.util.ArrayList;

//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.Szerelo.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

public class Szerelo extends Jatekos {
	
	//a szerelo megjavitja a csovet vagy a pumpat, amin eppen all
	public void Javit() {
		System.out.println("Fuggvenyhivas: Javit()");
		Mezo helyzet = super.getHelyzet();	//helyzet lekérése 
		helyzet.Megjavit();					//cso vagy pumpa megjavitasa
	}

	public void PumpatFelvesz() {
	}

	/**
	 * Egy szerelő lecsatol egy csövet egy aktívelemről (amin éppen áll), ez a
	 * Csohatizsak-ba kerül.
	 */
	public void CsovetLecsatol() {
		System.out.println("Függvényhívás: " + this + ".CsovetLecsatol()");

		// Lekérdezzük a lecsatlakoztatható elemeket
		Mezo helyzet = getHelyzet();
		ArrayList<Mezo> lecsatlakoztathatok = helyzet.GetLeszedhetoSzomszedok();

		// Ha nincs ilyen, vagy nincs táska hely, semmi sem történik
		if (lecsatlakoztathatok.size() == 0 || getCsoHatizsak().size() > getMaxHatizsakKapacitas()) {
			return;
		}

		// Kiválasztunk egyet
		Mezo kivalasztott = UseCase16.elemKivalaszt(lecsatlakoztathatok, "Válassz egy lecsatolandó elemet!");

		// Eltávolítjuk a referenciákat
		helyzet.SzomszedTorol(kivalasztott);
		kivalasztott.SzomszedTorol(helyzet);

		// Eltároljuk a hátizsákba a lecsatlakoztatott elemet
		getCsoHatizsak().add(kivalasztott);
	}

	/**
	 * Egy szerelő kivesz egy csövet a hátizsákjából és felcsatolja az aktívelemre
	 * amin éppen áll.
	 */
	public void CsovetFelcsatol() {
		System.out.println("Függvényhívás: " + this + ".CsovetFelcsatol()");

		// Üres hátizsák esetén nem történik semmi
		if (getCsoHatizsak().size() == 0) {
			return;
		}

		// Kiválasztunk egy felcsatolandót a hátizákból
		Mezo felcsatolando = UseCase16.elemKivalaszt(getCsoHatizsak(), "Válassz egy elemet a hátizsákból!");

		// Megpróbáljuk felcsatolni
		Mezo helyzet = getHelyzet();
		Boolean sikerult = helyzet.SzomszedFelcsatol(felcsatolando);
		System.out.println("Visszatérés: " + sikerult);

		// Ha sikerült frissítjük a referenciákat
		if (sikerult) {
			felcsatolando.SzomszedHozzaad(helyzet);
			getCsoHatizsak().remove(felcsatolando);
		}

	}

	public void PumpatEpit() {
	}
}
