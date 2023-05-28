package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class PumpaView extends MezoView {
	private static final Color szin = new Color(113, 113, 113); // Szürke: #717171
	private static final int atlo = 55; // Kör átlója

	private Boolean mukodik = true;

	public PumpaView() {
		int MIN_DISTANCE_BETWEEN = atlo * 2 + 50;

		// Pumpa a pálya középső részén lehet
		int minX = 250 + atlo;
		int maxX = 750 - atlo;
		int minY = atlo + 20;
		int maxY = 500;

		GenerateXYPlacement(minX, maxX, minY, maxY, MIN_DISTANCE_BETWEEN);
	}

	/**
	 * Amikor a pumpa változott, akkor átadja magát a hozzá tartozó view-nak, ami ez
	 * alapján tudja frissíteni a grafikáját. Változhat például hogy működik-e vagy
	 * sem, és az, hogy van-e benne víz vagy sem.
	 */
	@Override
	public void Notify(Mezo m) {
		mukodik = m.getMukodik();
	}

	/**
	 * A viewhez tartozó grafika rárajzolása az adott bufferre.
	 * @param layers - A bufferek amikre rajzolni kell.
	 */
	@Override
	public void Draw(ArrayList<Graphics> layers) {
		Graphics g = layers.get(1);

		// Kör kirajzolása (xy a közepe)
		g.setColor(szin);
		g.fillOval(x - atlo / 2, y - atlo / 2, atlo, atlo);

		// Hibajelzés
		if (!mukodik) {
			int warnX = x;
			int warnY = y - atlo / 2 - 3;

			g.setColor(Color.RED);
			g.fillPolygon(new int[] { warnX - 9, warnX, warnX + 9 }, new int[] { warnY, warnY - 17, warnY }, 3);
			g.setColor(Color.WHITE);
			g.drawString("!", warnX - 2, warnY - 2);
		}

		// Név a kör közepén
		DrawName(g, x, y);
	}

}
