package projlab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Grafika {
    private ParancsErtelmezoView pe;
    private ParancsErtelmezo p;
    private boolean darkMode = false;
    private boolean Fear = false;

    private boolean alwaysdebug = false;

    ArrayList<ObjectView> views = new ArrayList<ObjectView>();

    public Grafika(ParancsErtelmezoView _pe, ParancsErtelmezo _p) {
        pe = _pe;
        p = _p;

    }

    void draw() {
        JFrame frame = new JFrame("Arakis");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel drawPanel = new JPanel(new BorderLayout()) {
            public void paint(Graphics g) {
				ObjectView.DrawAllViews(g, darkMode);
            }
        };


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
        newGame.setBackground(Color.WHITE);
        newGame.addActionListener(e -> {
            pe.EnableDebugMode(true);
            pe.SendToPE("torol");
            pe.EnableDebugMode(false);
            if(alwaysdebug) {
                pe.EnableDebugMode(true);
            }});
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        cantSee.add(newGame, constraints);

        JButton newUniqueGame = new JButton("Új játék egyedi pályával");
        newUniqueGame.setBackground(Color.WHITE);
        newUniqueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String path = JOptionPane.showInputDialog(frame, "Path: ");
                p.EnableDebugMode(true);
                p.OutputToView(false);
                p.runFromString("torol");
                p.runFromFile(path);
                p.EnableDebugMode(false);
                p.OutputToView(true);
                drawPanel.repaint();
                if(alwaysdebug) {
                    pe.EnableDebugMode(true);
                }

            }
        });

        constraints.gridx = 1;
        constraints.gridy = 0;
        cantSee.add(newUniqueGame, constraints);


        JButton help = new JButton("Súgó");
        help.setBackground(Color.WHITE);
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String output = "letrehoz <”cso”/”pumpa”/”ciszterna”/”forras”/”szerelo”/”szabotor”> <nev> \n" +
                        "lep <jatekos> <mezo> \n" +
                        "osszekot <mezo1> <mezo2> \n" +
                        "szerel <szerelo> \n" +
                        "lyukaszt <jatekos> \n" +
                        "allit <jatekos> <bemenet_cso> <kimenet_cso> \n" +
                        "frissit \n" +
                        "epit <szerelo> <”cso”/”pumpa”> \n" +
                        "felvesz <szerelo> <”cso”/”pumpa”> [cso_nev] [egesz/fel] \n" +
                        "allapot <objektum> <objektum_attributum> [filenév]  \n" +
                        "tolt <fajlnev> \n" +
                        "csuszik <szabotor> \n" +
                        "ragad <jatekos> \n" +
                        "veletlen <”be”/”ki”> \n" +
                        "elront <pumpa/cso> \n" +
                        "termel <ciszterna> <”pumpa”/”cso”> \n" +
                        "csofelulet <cso> <\"csuszos\"/\"ragados\"> " +
                        "vizmennyiseg <pumpa/cso> <mennyiseg> ";
                if (Fear) {
                    output = "“I must not fear.\n" +
                            "Fear is the mind-killer.\n" +
                            "Fear is the little-death that brings total obliteration.\n" +
                            "I will face my fear.\n" +
                            "I will permit it to pass over me and through me.\n" +
                            "And when it has gone past I will turn the inner eye to see its path.\n" +
                            "Where the fear has gone there will be nothing. Only I will remain.”\n -- Bene Gesserit Litany";
                }
                JOptionPane.showMessageDialog(frame, output);
            }
        });
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
            else if (inp.equals("Dune")) {
                Fear = true;
            }
            else if (inp.equals("alwaysdebug"))
            {
                alwaysdebug = true;
                p.EnableDebugMode(true);
            }
            else
            {
                pe.SendToPE(inp);
            }
            input.setText("");
        });
        //Ez azért kell, hogy az enterrel is lehessen küldeni parancsot
        ActionListener enterAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send.doClick(); // ha megnyomjuk az entert, akkor a "gombra kattintunk"
            }
        };
        input.addActionListener(enterAction);
        //Fölfelé nyomáskor a legutóbbi parancsot írja be
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    input.setText(p.getLastFullCommand());
                    input.setCaretPosition(input.getText().length());
                }
            }
        };

// Add the KeyListener to the text field
        input.addKeyListener(keyListener);


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
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
