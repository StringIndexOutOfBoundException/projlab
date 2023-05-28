package projlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

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
	private static final Font font = new Font("Arial", Font.PLAIN, 14);


	/**
	 * Amikor a hozzá tartozó objektum változott, akkor átadja magát a hozzá tartozó
	 * view-nak, ami ez alapján tudja frissíteni a grafikáját.
	 * @param m - Az objektum ami szól a viewnak hogy frissítsen.
	 */
	public void Notify(Mezo m) {};
	public void Notify(Jatekos m) {};

	/**
	 * A viewhez tartozó grafika rárajzolása az adott bufferre.
	 * @param layers - A bufferek amikre rajzolni kell.
	 */
	public abstract void Draw(ArrayList<Graphics> layers);

	/**
	 * Beállítja az objektum megjelenítésekor használt nevét.
	 * @param n - Objektum neve
	 */
	public void SetNev(String n) {
		nev = n;
	}

	/**
	 * Beállítja hogy milyen koordinátán rajzolódjon ki a nézet
	 */
	public void setHely(int posX, int posY) {
		x = posX;
		y = posY;
	}

	/**
	 * Visszaadja az objektum középpontját, ami nem feltétlenül a x vagy y. A
	 * leszármazott döntiel hogy hova teszi a középpontját (pl.: Cső)
	 * @return Középpont X koordinátája
	 */
	public int getKozepX() {
		return x;
	}

	/**
	 * Visszaadja az objektum középpontját, ami nem feltétlenül a x vagy y. A
	 * leszármazott döntiel hogy hova teszi a középpontját (pl.: Cső)
	 * @return Középpont Y koordinátája
	 */
	public int getKozepY() {
		return y;
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
		int outlineSize = 1;
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
