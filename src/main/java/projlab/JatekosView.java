package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class JatekosView extends ObjectView {
	/**
	 * Ha több játékos van a mezőn akkor mennyire legyenek eltolva a középponttól.
	 */
	private static final int KOZEPPONTTOL_ELTOLAS = 30;

	/**
	 * Játékos állapotát tároló változók.
	 */
	protected Color szin; // Játékos színe
	private Mezo helyzet; // Játékos előző helye

	@Override
	public void Notify(Jatekos j) {
		Mezo ujHelyzet = j.getHelyzet();

		// Mát ottlévő játékosok frissítése
		if (ujHelyzet != helyzet) {
			helyzet = ujHelyzet;
			for (Jatekos jSzomszed : helyzet.getJatekosok()) {
				jSzomszed.getView().Notify(jSzomszed);
			}
		}

		// Saját pozició frissítése
		ObjectView helyzetView = helyzet.getView();
		int hanyadikJatekos = helyzet.getJatekosok().indexOf(j);
		int osszesJatekos = helyzet.getJatekosok().size();

		double deg = hanyadikJatekos * (Math.PI * 2 / osszesJatekos);
		int dX = (int) (KOZEPPONTTOL_ELTOLAS * Math.cos(deg));
		int dY = (int) (KOZEPPONTTOL_ELTOLAS * Math.sin(deg));
		x = helyzetView.getKozepX() + dX;
		y = helyzetView.getKozepY() + dY;
	}

	@Override
	public void Draw(ArrayList<Graphics> layers) {
		Graphics g = layers.get(2);

		// Háromszög
		int haromszogX = x;
		int haromszogY = y - 10;
		g.setColor(szin);
		g.fillPolygon(new int[] { haromszogX - 15, haromszogX, haromszogX + 15 },
				new int[] { haromszogY, haromszogY + 25, haromszogY }, 3);

		// Név a háromszög közepére
		DrawName(g, x, y);
	}
}
