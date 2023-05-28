package projlab;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Main osztály
 */
public class Main {
    public static void main(String[] args) {

        //VIEW-OS PARANCSÉRTELMEZO PÉLDA:

        //Először view létrehozása
        ParancsErtelmezoView pev = new ParancsErtelmezoView();

        //Aztán jön a ParancsErtelmezo.
        //A parancsértelmezőnek átadjuk a view-t konstruktorban, hogy tudja, hogy hova küldje a kimenetet. Ezzel a viewban is beállítódik a parancsértelmező automatikusan.
        //Tehát mást nem kell állítgatni, csak a view-t létrehozni, és a parancsértelmezőt létrehozni, és konstruktorban átadni neki a view-t.
        //Alternatív megoldás, hogy a parancsértelmezőt létrehozod, és a viewt létrehozod, majd a ParancsErtelmezo SetView(...) függvényével beállítod neki a viewt.
        //Ez is kölcsönös, tehát a view itt is beállítja a parancsértelmezőt magának automatikusan.
        ParancsErtelmezo pe2 = new ParancsErtelmezo(pev);

        //A parancsértlmezőben most már van olyan hogy debug mód. Ilyenkor minden parancs elérthető. A debug mód alapból be van kapcsolva
        //Amikor pályát inicializálsz, akkor muszáj hogy be legyen kapcsolva, mert a hozzá szükséges parancsok debug módosak.
        pe2.runFromFile("commandfiles/alap"); //Alap pálya inicializálása mondjuk.

        //Miután inicializáltad a pályát, kapcsold ki a debug módot, hogy ne lehessen elérni a debug parancsokat játék közben.
        pe2.EnableDebugMode(false);

        //Alapból a parancsértelmező a standard kimenetre írja a kimenetet. Ha azt akarjuk hogy a view kapja meg az output-ot, akkor azt be kell állítani így:
        pe2.OutputToView(true);

        //Ezután már a view fogja megkapni a kimenetet, és a viewnak kell majd továbbítania a kimenetet a megfelelő TextBox-nak. (Lásd: ParancsErtelmezoView.ReceiveFromPE)

        //A view is tud természetesen parancsokat küldeni a parancsértelmezőnek.
        //Ezt kell majd hivogatni amikor az Input TextBox-ba beírnak egy parancsot és megnyoomják a gombot hogy ok
        pev.SendToPE("allapot pcs1 vizmennyiseg"); //Példa parancs küldésre a viewból a parancsértelmezőnek. Nyilván valójában a TextBox-ból jön majd a parancs.

        JFrame frame = new JFrame("Arakis");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel cantSee = new JPanel(new GridBagLayout());
        cantSee.setPreferredSize(new Dimension(990, 27));
        cantSee.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(cantSee, constraints);

        JButton newGame = new JButton("Új játék");
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx=1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        cantSee.add(newGame, constraints);

        JButton newUniqueGame = new JButton("Új játék egyedi pályával");
        constraints.gridx = 1;
        constraints.gridy = 0;
        cantSee.add(newUniqueGame, constraints);


        JButton help = new JButton("Súgó");
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 2;
        constraints.gridy = 0;
        cantSee.add(help, constraints);

        JPanel cantSee2 = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 2000;
        constraints.gridx = 3;
        constraints.gridy = 0;
        cantSee.add(cantSee2, constraints);

		ArrayList<ObjectView> views = new ArrayList<ObjectView>();

		// =========================================================================
		// Teszt pálya 1 (rajzolás teszteléshez)
		// forrás1 - cső1 - forrás2
		// =========================================================================
		Forras f1 = new Forras();
		Forras f2 = new Forras();

		ObjectView fv1 = f1.getView();
		ObjectView fv2 = f2.getView();

		fv1.SetNev("Forras1");
		fv2.SetNev("Forras2");

		fv1.x = 100;
		fv1.y = 100;
		fv2.x = 300;
		fv2.y = 150;

		Cso cs1 = new Cso();
		ObjectView csv1 = cs1.getView();
		csv1.SetNev("Cso1");

		cs1.SzomszedHozzaad(f1);
		f1.SzomszedHozzaad(cs1);
		cs1.SzomszedHozzaad(f2);
		f2.SzomszedHozzaad(cs1);

		views.add(fv1);
		views.add(fv2);
		views.add(csv1);
		// =========================================================================

		ArrayList<BufferedImage> layers = new ArrayList<>();
		ArrayList<Graphics> layerGraphics = new ArrayList<>();

		// Bufferek (layerek) létrehozása
		for (int i = 0; i < 3; i++) {
			layers.add(new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB));
		}
		for (int i = 0; i < 3; i++) {
			layerGraphics.add(layers.get(i).getGraphics());
		}

        JPanel drawPanel = new JPanel(new BorderLayout()){
            public void paint(Graphics g){
				// Bufferekbe rajzolás
				for (ObjectView view : views) {
					view.Draw(layerGraphics);
                }

				// Bufferek rajzolása a panelre
				for (BufferedImage layer : layers) {
					g.drawImage(layer, 0, 0, null);
				}
            }
        };

        drawPanel.setPreferredSize(new Dimension(980, 740));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.weightx = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(drawPanel, constraints);


        TextArea output = new TextArea("");
        output.setEditable(false);
        output.setPreferredSize(new Dimension(880, 156));
        constraints.insets = new Insets(10, 10, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(output, constraints);

        JButton send = new JButton("O.k.");
        send.setPreferredSize(new Dimension(75, 27));
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(send, constraints);

        TextField input = new TextField("");
        constraints.gridwidth = 2;
        input.setPreferredSize(new Dimension(980, 20));
        constraints.insets = new Insets(0, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(input, constraints);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().add(panel);
		frame.pack();
        frame.setVisible(true);
    }
}
