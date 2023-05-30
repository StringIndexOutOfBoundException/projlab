package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class CiszternaView extends MezoView {
	private static final Color szin = new Color(245, 154, 35); // Narancs: #f59a23
	private static final int oldalmeret = 50; // Négyzet oldalmérete

	public CiszternaView() {
		int MIN_DISTANCE_BETWEEN = oldalmeret + 50;

		// Ciszterna a pálya utolsó negyedén lehet
		int minX = CANVAS_WIDTH / 4 * 3;
		int maxX = CANVAS_WIDTH - oldalmeret;
		int minY = oldalmeret;
		int maxY = CANVAS_HEIGHT - oldalmeret;

		GenerateXYPlacement(minX, maxX, minY, maxY, MIN_DISTANCE_BETWEEN);
	}

	/**
	 * Mivel a ciszterna állapota egyelőre sehogyan sem változhat grafikusan (a
	 * helye sem), ezért egyelőre üres
	 */
	@Override
	public void Notify(Mezo m) {
	}

	/**
	 * A viewhez tartozó grafika rárajzolása az adott bufferre.
	 * @param layers - A bufferek amikre rajzolni kell.
	 */
	@Override
	public void Draw(ArrayList<Graphics> layers) {
		if (!lathato) {
			return;
		}

		Graphics g = layers.get(1);

		// Négyzet kirajzolása (xy a közepe)
		g.setColor(szin);
		g.fillRect(x - oldalmeret / 2, y - oldalmeret / 2, oldalmeret, oldalmeret);

		// Név a négyzet közepén
		DrawName(g, x, y);
	}

}
