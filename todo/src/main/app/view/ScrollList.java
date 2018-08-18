package main.app.view;

import main.app.model.Tache;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

public class ScrollList extends JPanel {
    private static final long serialVersionUID = 1280674726919544137L;
    private final JPanel listetache = new JPanel();
    TachePanel[] taches;
    int type;
    private JScrollPane scrollpane;

    public ScrollList() {
        setPreferredSize(new Dimension(929, 380));
        scrollpane = new JScrollPane();
        add(scrollpane);
    }

    public void actualise(Vector<Tache> liste, int typeliste) {
        type = typeliste;
        remove(scrollpane);
        listetache.removeAll();
        taches = new TachePanel[liste.size()];
        for (int i = 0; i < liste.size(); i++) {
            taches[i] = new TachePanel(liste.elementAt(i), typeliste);
            taches[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            if (Objects.equals(liste.elementAt(i).getStatut(), "En retard"))
                taches[i].statut.setForeground(Color.red);
            else
                taches[i].statut.setForeground(Color.decode("#006400"));
            listetache.add(taches[i]);
        }
        if (type == -1)// taille reduite pour l'edition de bilan
            listetache.setPreferredSize(new Dimension(910, 600));
        else
            listetache.setPreferredSize(new Dimension(910, 800));
        scrollpane = new JScrollPane(listetache);
        if (type == -1) // taille reduite pour l'edition de bilan
            scrollpane.setPreferredSize(new Dimension(930, 200));
        else
            scrollpane.setPreferredSize(new Dimension(930, 371));
        scrollpane.updateUI();
        add(scrollpane);

    }

}
