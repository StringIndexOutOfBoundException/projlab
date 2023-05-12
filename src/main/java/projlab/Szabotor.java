package projlab;


/**
 * Az osztély a szabotőr típusú játékos által elvégezhető cselekvéseket megvalósító metódusok, változókat tartalmazza.
 */
public class Szabotor extends Jatekos {
	/**
	 * A szabotőr csúszóssá teszi a mezőt, amin éppen áll
	 */
	public void CsuszossaTesz(){
		Mezo helyzet = super.getHelyzet();
		helyzet.Csuszik();
	}
}
