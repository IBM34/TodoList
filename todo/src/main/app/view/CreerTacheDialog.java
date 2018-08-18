package main.app.view;

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

public class CreerTacheDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1681710815612778506L;
    private final JTextField titre = new JTextField(" ", 20);
    private final JButton annuler = new JButton("Annuler");
    private final String textImportance = "Choisissez le degré d'importance";
    private final String text1 = "Entrez le titre de la tâche";
    private final String text2 = "Choisissez la date d'écheance";
    private final String text3 = "Choisissez la date de debut :";
    private final Properties p = new Properties();
    private final JLabel error = new JLabel("La date d'écheance doit être postérieure à aujourd'hui");
    private final JLabel error2 = new JLabel("La date de début doit être antérieure à l’échéance");
    private final JPanel titrePanel = new JPanel();
    private final JPanel importancePanel = new JPanel();
    private final JPanel echeancePanel = new JPanel();
    private final JPanel debutPanel = new JPanel();
    private final JPanel choix = new JPanel();
    private final JPanel errorPan = new JPanel();
    private final JComboBox<String> combo = new JComboBox<>();
    protected JPanel comboPanel = new JPanel();
    String titretache = " ";
    int importance;
    Date echeance = new Date();
    Date debut = new Date();
    Boolean bool;
    private JButton ok = new JButton("OK");
    private UtilDateModel Model;
    private UtilDateModel DebutModel;
    private JDatePanelImpl datePanel;
    private JDatePanelImpl dateDebutPanel;
    private JDatePickerImpl datePicker;
    private JDatePickerImpl dateDebutPicker;

    public CreerTacheDialog(JFrame parent, String title, boolean modal, int type) {
        super(parent, title, modal);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
        init(type);
    }

    private void init(final int type) {
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        Model = new UtilDateModel();
        Model.setValue(new Date());
        DebutModel = new UtilDateModel();
        DebutModel.setValue(new Date());
        datePanel = new JDatePanelImpl(Model, p);
        dateDebutPanel = new JDatePanelImpl(DebutModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateDebutPicker = new JDatePickerImpl(dateDebutPanel, new DateLabelFormatter());
        combo.addItem("importante");
        combo.addItem("moyenne");
        combo.addItem("petite");
        importancePanel.add(combo);
        titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
        importancePanel.setBorder(BorderFactory.createTitledBorder(textImportance));
        echeancePanel.setBorder(BorderFactory.createTitledBorder(text2));
        titrePanel.add(titre);
        echeancePanel.add(datePicker);
        add(titrePanel);
        add(importancePanel);
        add(echeancePanel);
        if (type == 1) {
            debutPanel.setBorder(BorderFactory.createTitledBorder(text3));
            debutPanel.add(dateDebutPicker);
            add(debutPanel);
        }
        error.setForeground(Color.red);
        error2.setForeground(Color.red);
        errorPan.add(error);
        errorPan.add(error2);
        error.setVisible(false);
        error2.setVisible(false);
        if (type == 0)
            errorPan.setPreferredSize(new Dimension(400, 20));
        else
            errorPan.setPreferredSize(new Dimension(400, 40));
        add(errorPan);
        choix.setLayout(new FlowLayout());
        choix.add(annuler);
        annuler.addActionListener(arg0 -> {
            bool = false;
            setVisible(false);
        });
        ok = new JButton("OK");
        choix.add(ok);
        add(choix);
        ok.addActionListener(arg0 -> {
            titretache = titre.getText();
            importance = combo.getSelectedIndex() + 1;
            echeance = (Date) datePicker.getModel().getValue();
            if (type == 1) {
                debut = (Date) dateDebutPicker.getModel().getValue();
                if (debut.before(echeance) && echeance.after(new Date())) {
                    setVisible(false);
                    bool = true;
                } else {
                    if (echeance.before(new Date())) {
                        error.setVisible(true);
                        revalidate();
                    }
                    if (debut.after(echeance)) {
                        error2.setVisible(true);
                        revalidate();
                    }
                }
            } else {
                if (echeance.after(new Date())) {
                    setVisible(false);
                    bool = true;
                } else {
                    error.setVisible(true);
                    revalidate();
                }
            }
        });
        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
