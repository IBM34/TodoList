package main.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CreerTacheDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1681710815612778506L;
	protected JTextField titre = new JTextField(" ", 20);
	protected JButton ok = new JButton("OK");
	protected JButton annuler = new JButton("Annuler");
	protected String textImportance = "Choisissez le degré d'importance";
	protected String text1 = "Entrez le titre de la tâche";
	protected String text2 = "Choisissez la date d'écheance";
	protected String text3 = "Choisissez la date de debut :";
	protected UtilDateModel Model, DebutModel;
	protected Properties p = new Properties();
	protected JDatePanelImpl datePanel, dateDebutPanel;
	protected JDatePickerImpl datePicker, dateDebutPicker;
	protected String titretache = " ";
	protected int importance;
	protected Date echeance = new Date();
	protected Date debut = new Date();
	protected Boolean bool;
	protected JLabel error = new JLabel("La date d'écheance doit être postérieure à aujourd'hui");
	protected JLabel error2 = new JLabel("La date de début doit être antérieure à l’échéance");
	protected JPanel titrePanel = new JPanel();
	protected JPanel importancePanel = new JPanel();
	protected JPanel echeancePanel = new JPanel();
	protected JPanel comboPanel = new JPanel();
	protected JPanel debutPanel = new JPanel();
	protected JPanel choix = new JPanel();
	protected JPanel errorPan = new JPanel();
	protected JComboBox<String> combo = new JComboBox<String>();

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
	           bool=false;
	           setVisible(false);
	        }
	    });
		init(type);
	}

	public void init(final int type) {
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
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bool = false;
				setVisible(false);
			}
		});
		ok = new JButton("OK");
		choix.add(ok);
		add(choix);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
