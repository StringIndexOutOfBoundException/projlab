package projlab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CsoView extends MezoView {
	private static final Color szin = Color.BLACK; // Fekete
	private static final Color vizSzin = new Color(99, 168, 252); // Kék
	private static final int vastag = 10; // Kirajzolt cső vastagsága
	private static final BasicStroke stroke = new BasicStroke(vastag);

	/**
	 * A cső egyik vége az ObjectView-ből örökölt x, y. A cső másik vége pedig ez a
	 * két változó.
	 */
	private int x2 = 0, y2 = 0;

	/**
	 * Cső állapotát jelentő változók (keep for now)
	 */
	private Boolean csuszik = false;
	private Boolean ragad = false;
	private Boolean mukodik = true;

	@Override
	public int getKozepX() {
		return (x + x2) / 2; // Cső közepe a két pont átlaga
	}

	@Override
	public int getKozepY() {
		return (y + y2) / 2; // Cső közepe a két pont átlaga
	}

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
			lathato = false;
			return;
		}
		
		lathato = true;

		// Cső végeinek beállítása
		ObjectView v1 = szomszedok.get(0).getView();
		ObjectView v2 = szomszedok.get(1).getView();
		x = v1.x;
		y = v1.y;
		x2 = v2.x;
		y2 = v2.y;

		// Cső modifierek
		csuszik = m.getCsuszos() > 0;
		ragad = m.getRagados() > 0;
		mukodik = m.getMukodik();

		// Víz
		nevSzin = m.getVizmennyiseg() == 0 ? Color.WHITE : vizSzin;
	}

	private float animValue = 0;

	@Override
	public void Animate() {
		if (!mukodik)
			animValue += 0.1;
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

		// Stroke miatt 2D graphics kell
		Graphics g = layers.get(0);
		Graphics2D g2 = (Graphics2D) g;

		// Modifierek
		if (csuszik || ragad) {
			g2.setStroke(
					new BasicStroke(vastag + 4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			// Csuszósságnak nagyobb a precedenciája -- ez alapján dől el a modifier szine
			g2.setColor(csuszik ? Color.CYAN : Color.GREEN);
			g2.drawLine(x, y, x2, y2);
		}

		// Cső rajzolása
		g2.setStroke(stroke);
		g2.setColor(szin);
		g2.drawLine(x, y, x2, y2);

		// Hibajelzés
		if (!mukodik) {
			int warnX = getKozepX();
			int warnY = getKozepY() - 20 - (int) (Math.sin(animValue) * 5);
			g.setColor(Color.RED);
			g.fillPolygon(new int[] { warnX - 9, warnX, warnX + 9 }, new int[] { warnY, warnY - 17, warnY }, 3);
			g.setColor(Color.WHITE);
			g.drawString("!", warnX - 2, warnY - 2);

			g2.setStroke(
					new BasicStroke(vastag, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 3 }, 0));
			g2.setColor(new Color(255, 0, 0, 100));
			g2.drawLine(x, y, x2, y2);
		}

		// Név a cső közepén
		DrawName(g, getKozepX(), getKozepY());
	}

}
