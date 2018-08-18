package main.app.view;

import main.app.model.Categorie;
import main.app.model.Tache;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

public class ModifTacheDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1681710815612778506L;
    private final JButton ok = new JButton("OK");
    private final String text1 = "Entrez le titre de la tâche";
    private final String text2 = "Choisissez la date d'écheance";
    private final String text3 = "Choisissez la catégorie";
    private final String text4 = "Indiquez le pourcentage d'avancement";
    private final JLabel label = new JLabel();
    private final Properties p = new Properties();
    private final JLabel error = new JLabel("La date d'échéance doit être postérieure à celle de création");
    private final JLabel error2 = new JLabel("La date de début doit être antérieure à l’échéance");
    private final JPanel titrePanel = new JPanel();
    private final JPanel echeancePanel = new JPanel();
    private final JPanel comboPanel = new JPanel();
    private final JPanel pourcentagePanel = new JPanel();
    private final JPanel choix = new JPanel();
    private final JPanel errorPan = new JPanel();
    private final JPanel labelPan = new JPanel();
    Tache tache;
    int ind;
    int pourcentage;
    Boolean bool;
    private JTextField titre;
    private JComboBox<String> combo;
    private JButton annuler;
    private UtilDateModel Model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JSlider slide;

    public ModifTacheDialog(JFrame parent, String title, boolean modal) {
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

    public void init(final Tache t, Vector<Categorie> c) {
        tache = t;
        titre = new JTextField(t.getTitre(), 20);
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        Model = new UtilDateModel();
        Model.setValue(t.getEcheance());
        Model.setSelected(true);
        datePanel = new JDatePanelImpl(Model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        combo = new JComboBox<>();
        for (int i = 1; i < c.size(); i++)
            combo.addItem(c.elementAt(i).getTitre());
        combo.setSelectedItem(t.getCategorie());
        titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
        echeancePanel.setBorder(BorderFactory.createTitledBorder(text2));
        comboPanel.setBorder(BorderFactory.createTitledBorder(text3));
        titrePanel.add(titre);
        echeancePanel.add(datePicker);
        comboPanel.add(combo);
        add(titrePanel);
        add(echeancePanel);
        add(comboPanel);


        if (t.getType() == 2) {
            slide = new JSlider();
            slide.setMaximum(100);
            slide.setMinimum(t.getPourcentage());
            slide.setValue(t.getPourcentage());
            label.setText("Pourcentage actuel d'avancement: " + t.getPourcentage() + "%");
            slide.setPaintTicks(true);
            slide.setPaintLabels(true);
            slide.setMinorTickSpacing(10);
            slide.setMajorTickSpacing(20);
            slide.addChangeListener(event -> label.setText("Pourcentage actuel d'avancement: " + ((JSlider) event.getSource()).getValue() + "%"));
            pourcentagePanel.setBorder(BorderFactory.createTitledBorder(text4));
            pourcentagePanel.add(slide);
            add(pourcentagePanel);
            labelPan.add(label);
            add(labelPan);
        }
        annuler = new JButton("Annuler");
        choix.setLayout(new FlowLayout());
        choix.add(annuler);
        annuler.addActionListener(arg0 -> {
            bool = false;
            setVisible(false);
        });
        if (tache.getType() == 1) {
            error.setForeground(Color.red);
            errorPan.add(error);
            error.setVisible(false);
            errorPan.setPreferredSize(new Dimension(430, 30));
            add(errorPan);
        }
        if (tache.getType() == 2) {
            error2.setForeground(Color.red);
            errorPan.add(error2);
            error2.setVisible(false);
            errorPan.setPreferredSize(new Dimension(400, 30));
            add(errorPan);
        }
        choix.add(ok);
        ok.addActionListener(arg0 -> {
            ind = combo.getSelectedIndex() + 1;
            tache.setTitre(titre.getText());
            tache.setEcheance((Date) datePicker.getModel().getValue());
            if (tache.getType() == 2) {
                pourcentage = slide.getValue();
                if (t.getDateDebutDate().before(tache.getEcheance())) {
                    setVisible(false);
                    bool = true;
                } else {
                    error2.setVisible(true);
                }
            } else {
                if (t.getDateDebutDate().before(tache.getEcheance())) {
                    setVisible(false);
                    bool = true;
                } else {
                    error.setVisible(true);
                }
            }
        });
        add(choix);
        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
