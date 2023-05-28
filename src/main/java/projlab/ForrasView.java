package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ForrasView extends ObjectView {
	private final Color szin = new Color(0, 207, 255); // Kék: #00cfff
	private final int oldalmeret = 40; // Négyzet oldalmérete

	/**
	 * Amikor a forrás változott, akkor átadja magát a hozzá tartozó view-nak, ami
	 * ez alapján tudja frissíteni a grafikáját.
	 * @param m - A meghívó Forras
	 */
	@Override
	public void Notify(Mezo m) {
		// Mivel a forrás állapota egyenlőre sehogyan sem változhat grafikusan (helye
		// sem), ezért nincs értelme frissíteni a nézetet -- üres
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
