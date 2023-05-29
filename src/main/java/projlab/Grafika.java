package projlab;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Grafika {
    private ParancsErtelmezoView pe;
	private boolean darkMode = false;
    ArrayList<ObjectView> views = new ArrayList<ObjectView>();
    public Grafika(ParancsErtelmezoView _pe){
        pe = _pe;

    }
    void draw(){
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
        newGame.addActionListener(e -> pe.SendToPE("torol"));
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx=1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        cantSee.add(newGame, constraints);

        JButton newUniqueGame = new JButton("Új játék egyedi pályával");
        newUniqueGame.addActionListener(e -> {} );
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


        ArrayList<BufferedImage> layers = new ArrayList<>();
        ArrayList<Graphics> layerGraphics = new ArrayList<>();

		// Bufferek (layerek) létrehozása
		int scale = 2;
		for (int i = 0; i < 3; i++) {
			layers.add(new BufferedImage(1000 * scale, 1000 * scale, BufferedImage.TYPE_INT_ARGB));
		}
		for (int i = 0; i < 3; i++) {
			Graphics2D ig = layers.get(i).createGraphics();
			ig.scale(scale, scale);
			ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			ig.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			ig.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			layerGraphics.add(ig);
		}

		JPanel drawPanel = new JPanel(new BorderLayout()) {
			public void paint(Graphics g) {
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
				for (ObjectView view : ObjectView.GetAllViews()) {
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
		};

        pe.setDrawpanel(drawPanel);
        drawPanel.setPreferredSize(new Dimension(980, 740));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.weightx = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(drawPanel, constraints);


        JTextArea output = new JTextArea("");

        pe.setOutput(output);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setPreferredSize(new Dimension(880, 156));
        constraints.insets = new Insets(10, 10, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;

        panel.add(scrollPane, constraints);

        TextField input = new TextField("");
        constraints.gridwidth = 2;
        input.setPreferredSize(new Dimension(980, 20));
        constraints.insets = new Insets(0, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(input, constraints);

        JButton send = new JButton("O.k.");
        send.addActionListener(e -> {
            String inp = input.getText();
            if (inp.equals("dark")){
                darkMode = true;
                output.setBackground(new Color(80, 80, 80));
                output.setForeground(new Color(255, 255, 255));
                panel.setBackground(new Color(150, 150, 150));
                drawPanel.repaint();
                send.setBackground(new Color(100, 100, 100));
                newGame.setBackground(new Color(100, 100, 100));
                newUniqueGame.setBackground(new Color(100, 100, 100));
                help.setBackground(new Color(100, 100, 100));
                cantSee2.setBackground(new Color(100, 100, 100));
                cantSee.setBackground(new Color(100, 100, 100));
                input.setBackground(new Color(100, 100, 100));

            }
            pe.SendToPE(inp);
        });
        send.setBackground(Color.WHITE);
        send.setPreferredSize(new Dimension(75, 27));
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(send, constraints);



        if (darkMode){
            Color bgcolor = new Color(80, 80, 80);
            frame.setBackground(bgcolor);
            output.setBackground(new Color(0, 80, 0));
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
