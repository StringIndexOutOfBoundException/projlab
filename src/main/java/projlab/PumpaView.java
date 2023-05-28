package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PumpaView extends ObjectView {
	private final Color szin = new Color(113, 113, 113); // Szürke: #717171
	private final int atlo = 40; // Kör átlója

	@Override
	public void Notify(Mezo m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Draw(ArrayList<Graphics> layers) {
		Graphics g = layers.get(1);

		// Kör kirajzolása (xy a közepe)
		g.setColor(szin);
		g.drawOval(x - atlo / 2, y - atlo / 2, atlo, atlo);

		// Név a kör közepén
		DrawName(g, x, y);
	}

}
