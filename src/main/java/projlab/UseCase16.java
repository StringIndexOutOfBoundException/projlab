package projlab;

import java.io.Console;
import java.util.ArrayList;

public class UseCase16 {
	/**
	 * Teszteléshez segédfüggvény: a felhasználótól bekér egy választ
	 * 
	 * @param szomszedok
	 * @return A felhasználó által választott szomszéd
	 */
	public static Mezo szomszedKivalaszt(ArrayList<Mezo> szomszedok) {
		Console cnsl = System.console();
		int valasz = -1;

		// Kiírjuk a lehetőségeket
		System.out.println("Válassz egy szomszédot (sorszámmal):");
		for (int i = 0; i < szomszedok.size(); i++) {
			Mezo m = szomszedok.get(i);
			System.out.print(i + ": " + m + ", ");
		}

		// Bekérdezzük a választott sorszámot
		while (valasz < 0 || valasz > szomszedok.size() - 1) {
			String str = cnsl.readLine("Választott sorszám: ");
			valasz = Integer.parseInt(str, 0);
		}

		return szomszedok.get(valasz);
	}

	/**
	 * A szerelő a hátizsákjából egy kiválasztott csövet felcsatlakoztat egy aktív
	 * elemre, amin éppen áll.
	 */
	public static void csovetFelcsatolTest() {
		Cso cs1 = new Cso();
		Szerelo sz1 = new Szerelo();
		Pumpa p1 = new Pumpa();

		System.out.println("--- Teszt inicializálása:");
		sz1.setHelyzet(p1);
		ArrayList<Mezo> opciok = sz1.getHelyzet().GetLeszedhetoSzomszedok();

		System.out.println("--- Teszt futtatása:");

		sz1.CsovetFelcsatol();

	}

	/**
	 * A szerelő lecsatol egy kiválasztott csövet egy aktív elemről, amin éppen áll.
	 */
	public static void csovetLecsatolTest() {

	}

	/**
	 * A szerelő megpróbál egy csövet felcsatlakoztatni egy csőre, amin éppen áll.
	 */
	public static void csoreProbalFelcsatolni() {

	}

	/**
	 * A szerelő megpróbál lecsatlakoztatni valamit egy csőről, amin éppen áll.
	 */
	public static void csorolProbalLecsatolniTest() {

	}
}
