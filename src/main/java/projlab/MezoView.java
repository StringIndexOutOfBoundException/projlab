package projlab;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public abstract class MezoView extends ObjectView {
	/**
	 * A pálya elemeinek a helygenerálásához változók amik eltárolják az eddig
	 * generált helyeket.
	 */
	private static ArrayList<Integer> elozoXHelyek = new ArrayList<>();
	private static ArrayList<Integer> elozoYHelyek = new ArrayList<>();

	/**
	 * A pálya elemeinek helyét ezzel a függvénnyel tudják a leszármazottak
	 * legenerálni.
	 */
	protected void GenerateXYPlacement(int minX, int maxX, int minY, int maxY, int minDistance) {
		final int MAX_PLACEMENT_TRIALS = 500000; // Ha nincs már hely, ennyiszer fog próbálkozni
		Random random = new Random();

		// Mező helyének legenerálása, minden ütközés esetén újragenerálással
		for (int i = 0; i < MAX_PLACEMENT_TRIALS; i++) {
			Boolean intersect = false;
			x = random.nextInt((maxX - minX) + 1) + minX;
			y = random.nextInt((maxY - minY) + 1) + minY;

			for (Integer prewX : elozoXHelyek) {
				if (Math.abs(x - prewX) < minDistance) {
					intersect = true;
				}
			}
			for (Integer prewY : elozoYHelyek) {
				if (Math.abs(y - prewY) < minDistance) {
					intersect = true;
				}
			}

			if (!intersect)
				break;
		}

		elozoXHelyek.add(x);
		elozoYHelyek.add(y);
	}
}
