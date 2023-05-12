package projlab;

import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * A Forrás a pálya aktív eleme.
 * Azért felelős, hogy vizet nyeljen el, amit a csőhálózaton keresztűl kap.
 * Továbbá Pumpákat és Csöveket termel.
 * Összesen maximum 20 pumpája és hozzácsatlakozott csöve lehet egy csiszternának
 */
public class Ciszterna extends Mezo {
	
	private ArrayList<Mezo> termeltpumpak;
	
	public Ciszterna() {
		super(Integer.MAX_VALUE);
		termeltpumpak=new ArrayList<Mezo>();
		maxSzomszedok=20;
	}
	
	/**
	 *Ha a termeltpumpák elemszáma 0, akkor visszajelzést ad, hogy nincs felvehető pumpa. 
	 *Ha a termeltpumpák elemszáma nagyobb mint 0, akkor az listaban utolso pumpaja eltávolításra kerül.
	 */
	public void PumpaEltavolit() {
		//ha van pumpa a ciszterna korul
		if(termeltpumpak.size()>=1)
		termeltpumpak.remove(termeltpumpak.size()-1);
		}
	
	/**
	 * A ciszternak ezzel a fuggvennyel keszitenek uj pumpakat minden hivas utan 0-2 szam intervallumban
	 */
	public void PumpaKeszit() {
		//Random darab uj pumpat rak bele a termeltpumpakba 0-2 kozott
		Random rand=new Random();
		int randomNum=0;
		if(doRandomThings) //ha determinisztikus a mukodes
		randomNum=rand.nextInt(3);
		for(int i=0; i<randomNum;++i) {
			Pumpa p=new Pumpa();
			termeltpumpak.add(p);
			p.SzomszedHozzaad(this);
			this.SzomszedHozzaad(p);
		}
	
	}

	/**
	 * A ciszterna egy magához kapcsolódó szabad végű csövet hoz létre.
	 */
	public void CsovetKeszit() {
		Random rand=new Random();
		int randomNum=0;
		if(doRandomThings) //ha determinisztikus a mukodes
		randomNum=rand.nextInt(3);
		for(int i=0; i<randomNum;++i) {
			Cso ujcso = new Cso();
			ujcso.SzomszedHozzaad(this);
			this.SzomszedHozzaad(ujcso);
		}
		}
		
	/**
	 * getter a termeltpumpakra
	 */
	public ArrayList<Mezo> getTermeltPumpak() {
		return this.termeltpumpak;
	}

	/**
	*A szomszédok listájában mindenkire meghívja a VizetCsokkent függvényt. Azaz minden rá csatlakoztatott csőtől elveszi a benne található vizet.
	*(ha nincs víz a csőben, nem kap vizet)
	*Meghívja a PumpatKeszit, CsovetKeszit függvényeket is. 
	*@Override
	*/
	public void Frissit() {
		ArrayList<Mezo> szomszedok = GetSzomszedok(); //GetLeszedhetoSzomszedok lesz
		for (var cso : szomszedok) {
			try {
				cso.VizetCsokkent(1);		
			}
			catch (Exception e){/* Hmm ez nem szép itt ;) */}
		}
		this.CsovetKeszit();
		this.PumpaKeszit();
	}
}