package projlab;//
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
	public void Javit() {
		System.out.println("Függvényhívás: " + this + "Javít()");
		Mezo helyzet = super.getHelyzet();
		helyzet.Megjavit();
	}

	/**
 * A szerelo egy pumpat akar felvenni annal a ciszternanal ahol eppen tartozkodik
 * Eloszor megnezi, hogy van-e eleg hely a pumpaHatizsakjaban, majd megnezi a ciszternanal talalhato-e pumpa
 * Ha a feltetelek teljesulnek, a ciszterna termeltpumpak kollekciobol kiveszi az utolso pumpat es belerakja a hatizsakjaba.
 */
public void PumpatFelvesz() {
	//a szerelo helyzet attributuma  egy ciszterna kell legyen, hogy ez a fuggveny ertelmes eredmenyt adjon
	
	//mikor megtelt a pumpaHatizsak
	if(this.getpumpaHatizsak().size()>=this.getmaxHatizsakKapacitas()) {
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
		this.getpumpaHatizsak().add(this.getHelyzet().getTermeltPumpak().get(this.getHelyzet().getTermeltPumpak().size()-1));
		//ciszterna eltavolitja azt a pumpat amit felvett a szerelo
		this.getHelyzet().PumpaEltavolit();
		
	}
	}
	

	public void CsovetLecsatol() {
	}

	public void CsovetFelcsatol() {
	}

	/**
 *Ha a szerelo uj pumpat akar helyezni a csorendszerbe, ezt a fuggvenyt hasznalja
 *A szerelonek egy csovon kell allnia, ennek a csonek hivja meg a PumpaEpit fuggvenyet
 *A szerelo pumpaHatizsak kollekciojabol ki is torli a pumpat, amit elhelyezett.
 */
public void PumpatEpit() {
	//Szerelo egy csovon all, ennek a csonek meghivja a PumpaEpit fuggvenyet
	this.getHelyzet().PumpaEpit();
	//szerelo pumpaHatizsakjabol torlodik a pumpa amit elhelyez
	this.getpumpaHatizsak().remove(this.getpumpaHatizsak().get(this.getpumpaHatizsak().size()-1));
	
}
}
