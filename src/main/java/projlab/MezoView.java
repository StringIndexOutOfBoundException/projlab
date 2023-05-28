package projlab;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public abstract class MezoView extends ObjectView {
	/**
	 * A pálya elemeinek helyét ezzel a függvénnyel tudják a leszármazottak
	 * legenerálni.
	 */
	protected void GenerateXYPlacement(int minX, int maxX, int minY, int maxY, int minDistance) {
		final int MAX_PLACEMENT_TRIALS = 500000; // Ha nincs már hely, ennyiszer fog próbálkozni

		Random random = new Random();
		ArrayList<ObjectView> views = ObjectView.GetAllViews();

		// Mező helyének legenerálása, minden ütközés esetén újragenerálással
		for (int i = 0; i < MAX_PLACEMENT_TRIALS; i++) {
			Boolean intersect = false;

			x = random.nextInt((maxX - minX) + 1) + minX;
			y = random.nextInt((maxY - minY) + 1) + minY;

			for (int j = 0; j < views.size(); j++) {
				int dx = Math.abs(x - views.get(j).getKozepX());
				int dy = Math.abs(y - views.get(j).getKozepY());
				if (dx < minDistance && dy < minDistance) {
					intersect = true;
				}
			}

			if (!intersect)
				break;
		}
	}
}
