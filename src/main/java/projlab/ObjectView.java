package projlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Egy absztrakt ősosztály, amiből az összes konkrét objektumnak a játékban lesz
 * egy "view"-ja, ami felelős a grafikus megjelenítéséért.
 */
public abstract class ObjectView {
	/**
	 * A grafikus felületen az objektum középpontjának x és y koordinátáját mutatja
	 */
	protected int x, y;
	protected String nev;

	/**
	 * Az objektum megjelenéséért felelős változók default értékekkel.
	 */
	protected Color color = Color.LIGHT_GRAY;
	private Font font = new Font("Arial", Font.PLAIN, 12);

	/**
	 * Amikor a hozzá tartozó objektum változott, akkor átadja magát a hozzá tartozó
	 * view-nak, ami ez alapján tudja frissíteni a grafikáját.
	 * @param m - Az objektum ami szól a viewnak hogy frissítsen.
	 */
	public abstract void Notify(Mezo m);

	/**
	 * A viewhez tartozó grafika rárajzolása az adott bufferre.
	 * @param g - A buffer amire rajzolni kell.
	 */
	public abstract void Draw(Graphics g);

	/**
	 * Beállítja az objektum megjelenítésekor használt nevét.
	 * @param n - Objektum neve
	 */
	public void SetNev(String n) {
		nev = n;
	}

	/**
	 * A leszármazottaknak segédfüggvény, ami segítségével kirajzolhatják a nevüket
	 * stringként az adott graphics bufferre.
	 * 
	 * @param g    - A graphics amire rajzol
	 * @param posX - A szöveg közepének a koordinátája
	 * @param posY - A szöveg közepének a koordinátája
	 */
	protected void DrawName(Graphics g, int posX, int posY) {
		// Font metrikái alapján a szöveg helyének kiszámítása
		FontMetrics metrics = g.getFontMetrics(font);
		int textX = posX - (metrics.stringWidth(nev) / 2);
		int textY = posY + (metrics.getAscent() - metrics.getDescent()) / 2;

		g.setFont(font);
		g.setColor(Color.black);

		// Körvonal kirajzolása
		int outlineSize = 3;
		for (int dx = -outlineSize; dx <= outlineSize; dx++) {
			for (int dy = -outlineSize; dy <= outlineSize; dy++) {
				g.drawString(nev, textX + dx, textY + dy);
			}
		}

		// Szöveg kirajzolása
		g.setColor(Color.white);
		g.drawString(nev, textX, textY);
	}

}
