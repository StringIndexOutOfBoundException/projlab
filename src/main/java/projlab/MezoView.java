package projlab;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public abstract class MezoView extends ObjectView {
	/**
	 * Maximum hány próbálkozás lehetséges a véletlen elhelyezésre. Nagyobb szám
	 * esetén ha már nincs hely, akkor tovább keres. Találat esetén nem iterál
	 * végig.
	 */
	final int MAX_PLACEMENT_TRIALS = 5000;

	/**
	 * A pálya elemeinek helyét ezzel a függvénnyel tudják a leszármazottak
	 * legenerálni. Véletlenszerű helyet generál kicsit átfedési eséllyel.
	 */
	protected void GenerateXYPlacement(int minX, int maxX, int minY, int maxY, float minDistance) {
		Random random = new Random();
		ArrayList<ObjectView> views = ObjectView.GetAllViews();

		// Mező helyének legenerálása, minden ütközés esetén újragenerálással
		for (int i = 0; i < MAX_PLACEMENT_TRIALS; i++) {
			Boolean intersect = false;

			x = random.nextInt((maxX - minX) + 1) + minX;
			y = random.nextInt((maxY - minY) + 1) + minY;

			for (int j = 0; j < views.size(); j++) {
				ObjectView tested = views.get(j);
				if (tested == this)
					continue;

				int dx = Math.abs(x - tested.getKozepX());
				int dy = Math.abs(y - tested.getKozepY());
				double d = Math.sqrt(dx * dx + dy * dy);

				System.out.println(d);

				if (d < minDistance) {
					intersect = true;
				}
			}

			if (!intersect)
				break;
		}
	}
}
