package projlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UseCase16 {
	/**
	 * Teszteléshez segédfüggvény: a felhasználótól bekér egy választ
	 * 
	 * @param elemek
	 * @param prompt
	 * @return A felhasználó által választott szomszéd
	 */
	public static Mezo elemKivalaszt(ArrayList<Mezo> elemek, String prompt) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int valasz = -1;

		// Kiírjuk a lehetőségeket
		System.out.println(prompt);
		for (int i = 0; i < elemek.size(); i++) {
			Mezo m = elemek.get(i);
			System.out.println(" " + i + ": " + m);
		}

		// Bekérdezzük a választott sorszámot
		while (valasz < 0 || valasz > elemek.size() - 1) {
			System.out.print("\nVálasztott elem sorszáma: ");
			try {
				String line = br.readLine();
				valasz = Integer.parseInt(line);
			} catch (Exception e) {
				System.out.println("Rossz bemenet!");
			}
		}

		return elemek.get(valasz);

	}

	/**
	 * Teszteléshez segédfüggvény: Bekérdez a felhasználótól egy boolt.
	 * 
	 * @param kerdes - Kiírt kérdés
	 * @return Bool ami a felhasználó válasza
	 */
	public static boolean igenNemKerdes(String kerdes) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String valasz = "";

		System.out.print("\n" + kerdes + " [i/n] : ");

		// Bekérdezzük a választott sorszámot
		while (!valasz.contains("i") && !valasz.contains("n")) {
			try {
				valasz = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return valasz.contains("i");

	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * A szerelő a hátizsákjából egy kiválasztott csövet felcsatlakoztat egy aktív
	 * elemre, amin éppen áll.
	 */
	public static void csovetFelcsatolTest() {
		Cso cs1 = new Cso();
		Szerelo sz1 = new Szerelo();
		Pumpa p1 = new Pumpa();

		// Inicializálás
		sz1.setHelyzet(p1);
		sz1.getCsoHatizsak().add(cs1);

		// Futtatás
		p1.setMaxCso(igenNemKerdes("A pumpán legyen már maximális cső?") ? 0 : 1);
		sz1.CsovetFelcsatol();
	}

	/**
	 * A szerelő lecsatol egy kiválasztott csövet egy aktív elemről, amin éppen áll.
	 */
	public static void csovetLecsatolTest() {
		Cso cs1 = new Cso();
		Szerelo sz1 = new Szerelo();
		Pumpa p1 = new Pumpa();

		// Inicializálás
		System.out.println("--- Teszt inicializálása:");
		p1.SzomszedHozzaad(cs1);
		cs1.SzomszedHozzaad(p1);
		sz1.setHelyzet(p1);
		sz1.setMaxHatizsakKapacitas(1);

		// Futtatás
		System.out.println("--- Teszt futtatása:");
		sz1.CsovetLecsatol();
	}

	/**
	 * A szerelő megpróbál egy csövet felcsatlakoztatni egy csőre, amin éppen áll.
	 */
	public static void csoreProbalFelcsatolni() {
		Cso cs1 = new Cso();
		Cso cs2 = new Cso();
		Szerelo sz1 = new Szerelo();

		// Inicializálás
		sz1.setHelyzet(cs1);
		sz1.getCsoHatizsak().add(cs2);

		// Futtatás
		sz1.CsovetFelcsatol();
	}

	/**
	 * A szerelő megpróbál lecsatlakoztatni valamit egy csőről, amin éppen áll.
	 */
	public static void csorolProbalLecsatolniTest() {
		Cso cs1 = new Cso();
		Szerelo sz1 = new Szerelo();
		sz1.setHelyzet(cs1);
		sz1.setMaxHatizsakKapacitas(1);

		// Futtatás
		sz1.CsovetLecsatol();
	}
}
