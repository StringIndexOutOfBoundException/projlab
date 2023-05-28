package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ForrasView extends ObjectView {
	private static final Color szin = new Color(0, 207, 255); // Kék: #00cfff
	private static final int oldalmeret = 50; // Négyzet oldalmérete

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
