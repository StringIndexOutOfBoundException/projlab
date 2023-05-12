package projlab;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import projlab_iros.Mezo;

/**
*A pumpák a víz folyását szabályozzák. Minden pumpához adott számú csövet(maxCso) lehet csatlakoztatni,
*de ezekből mindig 1 kiválasztott cső a bemeneti cső(bemenet), amiből a pumpa magába tud szívni vizet 
*és mindig 1 cső a kimeneti cső(kimenet),amibe a pumpa vizet tud pumpálni.
*A pumpák véletlen időközönként elromlanak. Ilyenkor se vizet nem tudnak magukba szívni, se kipumpálni.
*A pumpákban található víz (vízmennyiség) akkor nő meg ha bemenetéről szívja a vizet,
*illetve akkor csökken, ha kimenetére kipumpálja a vizet (MAXVIZ attribútumnyit szív és kipumpál).
*Maximum 5 egységnyi víz lehet egy pumpában
*Ha a pumpa nem tud vizet átadni a kimenetére (mert a kimenet tele van vízzel),
*akkor a víz a pumpában marad. A pumpa csak akkor tud vizet szívni és kipumpálni, ha működik(Mezo ososztaly mukodik attributuma). 
*A pumpálásnak van felsőkorlátja(MAXVIZ), maximum ennyi vizet szívhat, illetve kipumpálhat a csövekből.
*   
*/
public class Pumpa extends Mezo {

	private  static int MAXVIZ;
	private int maxCso;
	private int vizmennyiseg;
	private Mezo bemenet;
	private Mezo kimenet;
	
	public Pumpa(){
		super(Integer.MAX_VALUE);
		MAXVIZ=1;
		maxCso=5;
		vizmennyiseg=0;
		bemenet=null;
		kimenet=null;
	}
	/**
	 * Egy mező szomszédaihoz hozzáad egy új mezőt
	 * A maxCso attribútum határozza meg mennyi szomszédja(rácsatlakotatt csövek) lehet egy pumpának.
	 * A szomszédok listáját a Mezo ősosztályból örökli.
	 * @param m a hozzáadandó mező
	 * @Override
	 */
	public void SzomszedHozzaad(Mezo m) {
		ArrayList<Mezo> szomszedok = super.GetSzomszedok(); //Mezo osztaly attributuma
		if(szomszedok.size() < maxCso)
			szomszedok.add(m);
	}

	/**
	 *Egy játékos átállítja egy pumpa be és kimenetét.
	 *Ha valamelyik paraméterben kapott referencia nem szerepel a pumpa szomszédai között, nem történik meg az átállítás
	 *Ha a paraméterek szerepelnek a szomszédok között,akkor a pumpa bemenetét átállítja a paraméterként kapott bemenetre
	 *és a kimenetét is a paraméterben kapott kimenetre állítja. 
	 * @param kimenet amire át lesz állítva a kimenet
	 * @param bemenet amire át lesz állítva a bemenet
	 * @Override
	 */

	public void Atallit(Mezo kimenet, Mezo bemenet) {
		//megnezzuk szomszedok-e
		ArrayList<Mezo> szomszedok = super.GetSzomszedok(); //Mezo osztaly attributuma
		for(int i=0; i<szomszedok.size();++i) {
			if(bemenet==szomszedok.get(i)) {
				this.bemenet = bemenet;
			}
			if(kimenet==szomszedok.get(i)) {
				this.kimenet = kimenet;
			}
		}
	}

	/**
	 * Átállítja a mukodik attributumot true-ra.
	 * @Override
	 */

	public void Megjavit() {
		setMukodik(true);
	}

	/**
	 *Felüldefiniált metódus a Mezo osztályból.
	 *A pumpa e függvény hatására (determinisztikusan vagy véletlenszerűen (az aktivitásdiagrammon 50% esély van erre))
	 *elromlik (mukodik attributum értékét false-ra állítja), ekkor nem tud vizet szívni, kipumpálni. 
	 *Ha a pumpa működik (mukodik attributum értéke true) a bemenetéről először beszívja a vizet a tartályába
	 *(ha van víz maximum egy egységnyivel nő a vizmennyiseg attribútum értéke, mivel a csövek maximum egy egységnyi vizet tárolhatnak).
	 *Ezután kipumpálja a vizet a kimenetére (ha van víz akkor maximum eggyel csökken a vízmennyiségattribútum).
	 *Ha a kimenet tele van vízzel, akkor a tartályban marad a víz.
	 *Ha a bemenetéről nem tud vizet szívni, akkor a tartályában lévő vizet adja át a kimenetnek (ha a tartályban nincs víz nem kap a kimenet vizet) 
	 * @Override
	 */
	public void Frissit() {
		Random rand = new Random();
		if(rand.nextDouble(1) > 0.5){
			if(doRandomThings) //ha determinisztikus a mukodes
			this.setMukodik(false);
		}
		//ha mukodik akkor pumpalhat
		if(this.mukodik) {
		//ha a pumpa tartalya ures vagy nincs tele	
		if(vizmennyiseg<5) {
			try {
				
				if(bemenet!=null) {
					bemenet.VizetCsokkent(MAXVIZ);
					vizmennyiseg+=MAXVIZ;
				}
			}
			//ha nincs benne viz
			catch (Exception e){}
			//vizet átad kimenetnek, ha van viz
			if(vizmennyiseg>=1) {	
			try {
				kimenet.VizetNovel(MAXVIZ);
				vizmennyiseg-=MAXVIZ;
				//ha nem fér kimenetnek víz
			} catch (Exception e) {}
		}
		}
		
		
		//ha tele van a pumpa
		 if(vizmennyiseg==5) {
			
			//eloszor megprobal kimenetre kipumpalni
			try {
				kimenet.VizetNovel(MAXVIZ);
				vizmennyiseg-=MAXVIZ;	
			}
			//ha nem fér kimenetnek víz
			catch (Exception e) {
			}
			//ha sikerult atadni a vizet a kimenetnek a bemenettol kaphat vizet
			 if(vizmennyiseg!=5) {
				try {
				//bemenet.VizetCsokkent(befolyoviz);
				if(bemenet!=null) {
					bemenet.VizetCsokkent(MAXVIZ);
					vizmennyiseg+=MAXVIZ;
				}
				}
			//ha nincs benne viz
			catch (Exception e){}
			}
		}
		}
	}

	/**
	 * getter a bemenetre
	 */
	public Mezo getBemenet() {
	return this.bemenet;
}
	/**
	 * getter a Kimenetre
	 * Hasznalva van
	 */
public Mezo getKimenet() {
	return this.kimenet;
}
/**
 * Mennyi víz legyen a pumpában (max 5 lehet es 0-nal tobb)
 * @param meret: viz mennyisege
 */
public void setVizmennyiseg(int meret) {
	//max 5 viz lehet benne
	if(meret>5) {
		this.vizmennyiseg=5;
	}
	else if(meret<0) {}
	else
	this.vizmennyiseg=meret;
}
}