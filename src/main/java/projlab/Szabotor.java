package projlab;


/**
 * A szobotőr a játékban az egyik játszható klassz, a feladatuk, hogy megakadályozzák, azt hogy a víz a ciszternába érjen,
 * ezt úgy tudják elérni, ha a víz kifolyik a sivatagba.
 */
public class Szabotor extends Jatekos {
	/**
	 * A függvényt maga a felhasználó fogja meghívni, és ez kezdeményezi a cső{@link Cso}-től, hogy lyukadjon ki
	 */
	public void Lyukaszt() {
		System.out.println("Függvényhívás: " + this + "Lyukaszt()");
		System.out.println();
		Mezo helyzet = super.getHelyzet();
		helyzet.Kilyukaszt();
	}
}
