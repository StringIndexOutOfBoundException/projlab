package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PumpaView extends ObjectView {
	private static final Color szin = new Color(113, 113, 113); // Szürke: #717171
	private static final int atlo = 55; // Kör átlója

	private Boolean mukodik = true;

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
