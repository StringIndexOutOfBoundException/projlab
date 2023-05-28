package projlab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CsoView extends ObjectView {
	private final Color szin = Color.BLACK; // Fekete
	private final BasicStroke vastagsag = new BasicStroke(10); // Kirajzolt cső vastagsága

	/**
	 * A cső egyik vége az ObjectView-ből örökölt x, y. A cső másik vége pedig ez a
	 * két változó.
	 */
	private int x2, y2;

	private Boolean visible = false;

	/**
	 * Amikor a cső változott, akkor átadja magát a hozzá tartozó view-nak, ami ez
	 * alapján tudja frissíteni a grafikáját. Változhat például hogy működike vagy
	 * sem, és az, hogy van-e benne víz vagy sem.
	 * @param m - A meghívó Cso
	 */
	@Override
	public void Notify(Mezo m) {
		ArrayList<Mezo> szomszedok = m.GetSzomszedok();

		// Cső nem látható ha nincs két szomszédja
		if (szomszedok.size() < 2) {
			visible = false;
			return;
		}
		
		visible = true;

		// Cső végeinek beállítása
		ObjectView v1 = szomszedok.get(0).getView();
		ObjectView v2 = szomszedok.get(1).getView();
		x = v1.x;
		y = v1.y;
		x2 = v2.x;
		y2 = v2.y;
	}

	/**
	 * A forrás nézetének rárajzolása az átadott graphics bufferre.
	 * @param g - A graphics buffer amire rajzol.
	 */
	@Override
	public void Draw(ArrayList<Graphics> layers) {
		if (!visible)
			return;

		// Stroke miatt 2D graphics kell
		Graphics g = layers.get(0);
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(vastagsag);
		g2.setColor(szin);
		g2.drawLine(x, y, x2, y2);

		DrawName(g, (x + x2) / 2, (y + y2) / 2);
	}

}
