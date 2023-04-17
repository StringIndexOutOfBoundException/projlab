package projlab;

import java.util.List;
import java.util.ArrayList;

/**
 * A szerelők tartják karban a csőhálózatot. Ők javítják meg az elromlott
 * pumpákat, ők állítják át a pumpákat, hogy mindig a lehető legtöbb víz tudjon
 * áthaladni a hálózaton, és ha egy cső kilyukad, az ő dolguk a cső megfoltozása
 * is. A szerelők dolga a ciszternáknál lévő szabad csövekkel a hálózat
 * kapacitásának növelése.
 */
public class Szerelo extends Jatekos {

/**
 * A szerelo egy pumpat akar felvenni annal a ciszternanal ahol eppen tartozkodik
 * Eloszor megnezi, hogy van-e eleg hely a pumpaHatizsakjaban, majd megnezi a ciszternanal talalhato-e pumpa
 * Ha a feltetelek teljesulnek, a ciszterna termeltpumpak kollekciobol kiveszi az utolso pumpat es belerakja a hatizsakjaba.
 */
public void PumpatFelvesz() {
	//a szerelo helyzet attributuma  egy ciszterna kell legyen, hogy ez a fuggveny ertelmes eredmenyt adjon
	//mikor megtelt a pumpaHatizsak
	if(this.getPumpaHatizsak().size()>=this.getMaxHatizsakKapacitas()) {
		System.out.println("Nem tud pumpat berakni a szerelo a hatizsakjaba");
	}
	//mikor nincs pumpa a ciszternanal
	else if(this.getHelyzet().getTermeltPumpak().size()==0) {
		System.out.println("A ciszternanal nincs pumpa");
	}
    
	//mikor tudunk felvenni pumpat
	else {
		System.out.println("A szerelo fel tudja venni a pumpat,"
						+ "\n Ha a sikertelenseget akarja tesztelni irjon be mas adatot!");
		//szerelo berakja a hatizsakba a ciszterna termeltpumpak listajaban talalhato utolso pumpat
		ArrayList<Pumpa> ciszterna_pumpai=this.getHelyzet().getTermeltPumpak();
		int ciszterna_pumpai_meret=ciszterna_pumpai.size();
		this.getPumpaHatizsak().add(ciszterna_pumpai.get(ciszterna_pumpai_meret-1));
		//ciszterna eltavolitja azt a pumpat amit felvett a szerelo
		this.getHelyzet().PumpaEltavolit();
	}
}

   /**
	 * A szerelő megjavít egy csövet, amin éppen áll
	 */
	public void Javit() {
		System.out.println("Függvényhívás: " + this + ".Javit()");
		Mezo helyzet = super.getHelyzet();
		helyzet.Megjavit();
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
	


/**
 *Ha a szerelo uj pumpat akar helyezni a csorendszerbe, ezt a fuggvenyt hasznalja
 *A szerelonek egy csovon kell allnia, ennek a csonek hivja meg a PumpaEpit fuggvenyet
 *A szerelo pumpaHatizsak kollekciojabol ki is torli a pumpat, amit elhelyezett.
 */
public void PumpatEpit() {
	//Szerelo egy csovon all, ennek a csonek meghivja a PumpaEpit fuggvenyet
	this.getHelyzet().PumpaEpit();
	//szerelo pumpaHatizsakjabol torlodik a pumpa amit elhelyez, azaz a pumpahatizsak kollekcio utolso pumpaja
	Pumpa torlodo=this.getPumpaHatizsak().get(this.getPumpaHatizsak().size()-1);
	this.getPumpaHatizsak().remove(torlodo);
	
}
}
