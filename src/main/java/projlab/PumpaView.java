package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PumpaView extends ObjectView {
	private static final Color szin = new Color(113, 113, 113); // Szürke: #717171
	private static final int atlo = 55; // Kör átlója

	/**
	 * Mivel a pumpa állapota egyelőre sehogyan sem változhat grafikusan (a helye
	 * sem), ezért egyelőre üres
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
		Graphics g = layers.get(1);

		// Kör kirajzolása (xy a közepe)
		g.setColor(szin);
		g.fillOval(x - atlo / 2, y - atlo / 2, atlo, atlo);

		// Név a kör közepén
		DrawName(g, x, y);
	}

}
