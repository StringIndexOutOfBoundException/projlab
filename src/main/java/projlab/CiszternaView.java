package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class CiszternaView extends ObjectView {
	private final Color szin = new Color(245, 154, 35); // Narancs: #f59a23
	private final int oldalmeret = 40; // Négyzet oldalmérete

	@Override
	public void Notify(Mezo m) {
		// Mivel a ciszterna állapota egyenlőre sehogyan sem változhat grafikusan (helye
		// sem), ezért egyelőre üres
	}

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
