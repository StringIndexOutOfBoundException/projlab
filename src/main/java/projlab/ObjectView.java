package projlab;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Egy absztrakt ősosztály, amiből az összes konkrét objektumnak a játékban lesz
 * egy "view"-ja, ami felelős a grafikus megjelenítéséért.
 */
public abstract class ObjectView {
	/**
	 * Az eddig létrehozott összes view-t tartalmazó statikus lista.
	 */
	private static ArrayList<ObjectView> allViews = new ArrayList<>();

	/**
	 * A view-k kirajzolásához használt bufferek (layerek z-index szerint) és a
	 * hozzájuk tartozó grafikák.
	 */
	private static ArrayList<BufferedImage> layers = new ArrayList<>();
	private static ArrayList<Graphics> layerGraphics = new ArrayList<>();
	private static int scale = 2;

	/**
	 * Statikus bufferek(layerek) létrehozása
	 */
	static {
		for (int i = 0; i < 4; i++) {
			layers.add(new BufferedImage(1000 * scale, 1000 * scale, BufferedImage.TYPE_INT_ARGB));
		}
		for (int i = 0; i < 4; i++) {
			Graphics2D ig = layers.get(i).createGraphics();
			ig.scale(scale, scale);
			ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			ig.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			ig.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			layerGraphics.add(ig);
		}
	}

	/**
	 * A grafikus felületen az objektum középpontjának x és y koordinátáját mutatja
	 */
	protected int x = 0, y = 0;
	protected String nev = "?";
	protected Color nevSzin = Color.white;
	protected Boolean lathato = true;

	/**
	 * Az objektum megjelenéséért felelős változók default értékekkel.
	 */
	private static final Font font = new Font("Arial", Font.PLAIN, 14);

	public ObjectView() {
		allViews.add(this);
	}

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
	 * Beállítja hogy milyen koordinátán rajzolódjon ki a nézet
	 */
	public void setLathato(boolean l) {
		lathato = l;
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
	 * Visszadaja az összes eddig léterhozott nézetet.
	 * @return Az összes eddig létrehozott nézet listája.
	 */
	static public ArrayList<ObjectView> GetAllViews() {
		return allViews;
	}

	/**
	 * Törli az összes eddig léterhozott nézetet.
	 */
	static public void RemoveAllViews() {
		allViews.clear();
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
		g.setColor(nevSzin);
		g.drawString(nev, textX, textY);
	}

	/**
	 * Kirajzol egy adott Graphics-ra minden view-t.
	 * @param g        - A Graphics amire a kirajzolás történik
	 * @param darkMode - A téma színéhez illő háttért állítja be rajzolásnál.
	 */
	public static void DrawAllViews(Graphics g, Boolean darkMode) {
		Graphics2D panelg = (Graphics2D) g.create();
		panelg.scale(1.0d / scale, 1.0d / scale);

		// Előző kép törlése
		if (darkMode) {
			panelg.setColor(new Color(130, 130, 130));
			panelg.fillRect(0, 0, 1000 * scale, 1000 * scale);
		} else {
			panelg.clearRect(0, 0, 1000 * scale, 1000 * scale);
		}

		// Bufferekbe rajzolás
		for (ObjectView view : allViews) {
			view.Draw(layerGraphics);
		}

		// Bufferek rajzolása a panelre
		for (BufferedImage layer : layers) {
			panelg.drawImage(layer, 0, 0, null);

			// Buffer törlése rajzolás után
			Graphics2D lg2 = layer.createGraphics();
			lg2.setComposite(AlphaComposite.Clear);
			lg2.fillRect(0, 0, layer.getWidth(), layer.getHeight());
		}
	}
}
