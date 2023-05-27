package projlab;

import java.awt.Color;
import java.awt.Graphics;

public class ForrasView extends ObjectView {
	private final int size = 10; // Négyzet oldalmérete

	public ForrasView() {
		color = new Color(0, 207, 255); // Kék: #00cfff
	}

	/**
	 * Amikor a forrás változott, akkor átadja magát a hozzá tartozó view-nak, ami
	 * ez alapján tudja frissíteni a grafikáját.
	 * @param m - A meghívó Forras
	 */
	@Override
	public void Notify(Mezo m) {
		// TODO idk yet
	}

	/**
	 * A forrás nézetének rárajzolása az átadott graphics bufferre.
	 * @param g - A graphics buffer amire rajzol.
	 */
	@Override
	public void Draw(Graphics g) {
		// Négyzet kirajzolása
		g.setColor(color);
		g.fillRect(x - size / 2, y - size / 2, size, size);

		// Név a négyzet közepén
		DrawName(g, x, y);
	}
}
