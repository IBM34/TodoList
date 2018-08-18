package main.app.view;

import main.app.model.Categorie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class ModifCategorieDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = -602805562741607121L;
    private final JTextField titre = new JTextField(" ", 20);
    private final String text1 = "Entrez le titre";
    private final String text2 = "Selectionnez la catégorie à renommer";
    private final String text3 = "Selectionnez la catégorie à supprimer";
    private final JPanel titrePanel = new JPanel();
    private final JPanel renomPanel = new JPanel();
    private final JPanel supPanel = new JPanel();
    private final JPanel choix = new JPanel();
    int ind;
    String titrecat;
    boolean bool;
    private JButton ok;
    private JButton annuler;
    private JComboBox<String> combo;


    public ModifCategorieDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        setBackground(Color.WHITE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        bool = false;
        this.addWindowStateListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bool = false;
                setVisible(false);
            }
        });
    }

    public void initAjouter() {
        titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
        titrePanel.setPreferredSize(new Dimension(330, 60));
        titrePanel.add(titre);
        add(titrePanel);
        annuler = new JButton("Annuler");
        choix.setLayout(new FlowLayout());
        choix.add(annuler);
        annuler.addActionListener(arg0 -> {
            bool = false;
            setVisible(false);
        });
        ok = new JButton("OK");
        choix.add(ok);
        ok.addActionListener(arg0 -> {
            titrecat = titre.getText();
            bool = true;
            setVisible(false);
        });
        add(choix);
        this.pack();
        setVisible(true);

    }

    public void initRenommer(Vector<Categorie> c) {
        combo = new JComboBox<>();
        for (int i = 1; i < c.size(); i++)
            combo.addItem(c.elementAt(i).getTitre());
        renomPanel.setBorder(BorderFactory.createTitledBorder(text2));
        titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
        renomPanel.setPreferredSize(new Dimension(330, 60));
        titrePanel.setPreferredSize(new Dimension(330, 60));
        renomPanel.add(combo);
        titrePanel.add(titre);
        add(renomPanel);
        add(titrePanel);

        annuler = new JButton("Annuler");
        choix.setLayout(new FlowLayout());
        choix.add(annuler);
        annuler.addActionListener(arg0 -> {
            bool = false;
            setVisible(false);
        });
        ok = new JButton("OK");
        choix.add(ok);

        ok.addActionListener(arg0 -> {
            ind = combo.getSelectedIndex() + 1;
            titrecat = titre.getText();
            setVisible(false);
            bool = true;
        });
        add(choix);
        this.pack();
        setVisible(true);
    }

    public void initSupprimer(Vector<Categorie> c) {
        combo = new JComboBox<>();
        for (int i = 1; i < c.size(); i++)
            combo.addItem(c.elementAt(i).getTitre());
        supPanel.setBorder(BorderFactory.createTitledBorder(text3));
        supPanel.setPreferredSize(new Dimension(330, 60));
        supPanel.add(combo);
        add(supPanel);
        annuler = new JButton("Annuler");
        choix.setLayout(new FlowLayout());
        choix.add(annuler);
        annuler.addActionListener(arg0 -> {
            bool = false;
            setVisible(false);
        });
        ok = new JButton("OK");
        choix.add(ok);

        ok.addActionListener(arg0 -> {
            ind = combo.getSelectedIndex() + 1;
            setVisible(false);
            bool = true;
        });
        add(choix);
        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}
