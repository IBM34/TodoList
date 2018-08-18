package main.app.view;

import main.app.model.Tache;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TachePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    final JLabel statut;
    final JPanel pourcentage;
    final int type;
    private final JLabel titre;
    private final JLabel echeance;
    private final JLabel categorie;
    private final JLabel importance;
    private final JLabel debut;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final Font font = new Font("ubuntu", Font.BOLD, 14);
    private JProgressBar progressBar;

    public TachePanel(Tache t, int typelist) {
        type = t.getType();
        setPreferredSize(new Dimension(910, 45));
        setBackground(Color.LIGHT_GRAY);
        titre = new JLabel(t.getTitre());
        statut = new JLabel(t.getStatut());
        if (typelist == 2)
            echeance = new JLabel(dateFormat.format(t.getEcheanceInter()));
        else
            echeance = new JLabel(dateFormat.format(t.getEcheance()));
        categorie = new JLabel(t.getCategorie());
        if (t.getImportance() == 1)
            importance = new JLabel("Importante");
        else if (t.getImportance() == 2)
            importance = new JLabel("Moyenne");
        else
            importance = new JLabel("Petite");
        debut = new JLabel(t.getDateDebutString());
        pourcentage = new JPanel();
        titre.setPreferredSize(new Dimension(150, 35));
        statut.setPreferredSize(new Dimension(85, 35));
        echeance.setPreferredSize(new Dimension(100, 35));
        categorie.setPreferredSize(new Dimension(90, 35));
        importance.setPreferredSize(new Dimension(90, 35));
        debut.setPreferredSize(new Dimension(95, 35));
        pourcentage.setPreferredSize(new Dimension(165, 35));
        pourcentage.setBackground(Color.LIGHT_GRAY);
        titre.setFont(font);
        statut.setFont(font);
        debut.setFont(font);
        echeance.setFont(font);
        categorie.setFont(font);
        importance.setFont(font);
        add(titre);
        add(statut);
        add(debut);
        add(echeance);
        add(categorie);
        add(importance);

        if (t.getPourcentage() != -1) // tâche long cours
        {
            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(t.getPourcentage());
            progressBar.setStringPainted(true);
            progressBar.setPreferredSize(new Dimension(125, 22));
            pourcentage.add(progressBar);
            add(pourcentage);
        } else
            add(pourcentage);
    }

}
