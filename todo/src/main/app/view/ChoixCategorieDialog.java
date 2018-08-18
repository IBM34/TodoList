package main.app.view;

import main.app.model.Categorie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class ChoixCategorieDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 8372653868523225118L;
    private final String text1 = "Choisissez une catégorie";
    private final String text2 = "Choisissez un type de tâche";
    private final JPanel catPanel = new JPanel();
    private final JPanel typePanel = new JPanel();
    private final JPanel choix = new JPanel();
    int ind;
    int type;
    boolean bool;
    private JComboBox<String> combo;
    private JComboBox<String> combo2;
    private JButton ok;
    private JButton annuler;

    public ChoixCategorieDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        setBackground(Color.WHITE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        ind = -1;
        type = -1;
        bool = false;
        this.addWindowStateListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bool = false;
                setVisible(false);
            }
        });
        setLocationRelativeTo(null);
    }

    public void init(Vector<Categorie> c) {

        combo = new JComboBox<>();
        combo2 = new JComboBox<>();
        for (int i = 1; i < c.size(); i++)
            combo.addItem(c.elementAt(i).getTitre());
        combo2.addItem("Ponctuelle");
        combo2.addItem("Long cours");
        catPanel.setBorder(BorderFactory.createTitledBorder(text1));
        typePanel.setBorder(BorderFactory.createTitledBorder(text2));
        catPanel.setPreferredSize(new Dimension(330, 60));
        typePanel.setPreferredSize(new Dimension(330, 60));
        catPanel.add(combo);
        typePanel.add(combo2);
        add(catPanel);
        add(typePanel);
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
            type = combo2.getSelectedIndex();
            bool = true;
            setVisible(false);
        });
        add(choix);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
