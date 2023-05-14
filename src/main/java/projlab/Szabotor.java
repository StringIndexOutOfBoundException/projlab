package projlab;


/**
 * Az osztély a szabotőr típusú játékos által elvégezhető cselekvéseket megvalósító metódusok, változókat tartalmazza.
 */
public class Szabotor extends Jatekos {
	public  Szabotor(){
		maxHatizsakKapacitas = 5;
	}
	/**
	 * A szabotőr csúszóssá teszi a mezőt, amin éppen áll
	 */
	@Override
	public void CsuszossaTesz(){
		Mezo helyzet = super.getHelyzet();
		helyzet.Csuszik();
	}
}
