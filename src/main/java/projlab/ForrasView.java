package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ForrasView extends MezoView {
	private static final Color szin = new Color(0, 207, 255); // Kék: #00cfff
	private static final int oldalmeret = 50; // Négyzet oldalmérete

	public ForrasView() {
		int MIN_DISTANCE_BETWEEN = oldalmeret + 50;

		// Forrás a pálya első negyedén lehet
		int minX = oldalmeret;
		int maxX = 250;
		int minY = oldalmeret;
		int maxY = 740 - oldalmeret;

		GenerateXYPlacement(minX, maxX, minY, maxY, MIN_DISTANCE_BETWEEN);
	}

	/**
	 * Mivel a forrás állapota egyelőre sehogyan sem változhat grafikusan (a helye
	 * sem), ezért egyelőre üres
	 */
	@Override
	public void Notify(Mezo m) {
	}

	/**
	 * A forrás nézetének rárajzolása az átadott graphics bufferre.
	 * @param g - A graphics buffer amire rajzol.
	 */
	@Override
	public void Draw(ArrayList<Graphics> layers) {
		Graphics g = layers.get(1);

		// Négyzet kirajzolása (xy a közepe)
		g.setColor(szin);
		g.fillRect(x - oldalmeret / 2, y - oldalmeret / 2, oldalmeret, oldalmeret);

		// Név a négyzet közepén
		DrawName(g, x, y);
	}
}
