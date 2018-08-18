package main.app.view;

import main.app.model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ModifTacheDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1681710815612778506L;
	protected JTextField titre;
	protected JComboBox<String> combo;
	protected JButton ok = new JButton("OK");
	protected JButton annuler;
	protected String text1 = "Entrez le titre de la tâche";
	protected String text2 ="Choisissez la date d'écheance";
	protected String text3 = "Choisissez la catégorie";
	protected String text4 = "Indiquez le pourcentage d'avancement";
	protected JLabel label = new JLabel();
	protected UtilDateModel Model;
	protected Properties p = new Properties();
	protected JDatePanelImpl datePanel;
	protected JDatePickerImpl datePicker;
	protected Tache tache;
	protected int ind;
	protected int pourcentage;
	protected JSlider slide;
	protected Boolean bool;
	protected JLabel error=new JLabel("La date d'échéance doit être postérieure à celle de création");
	protected JLabel error2=new JLabel("La date de début doit être antérieure à l’échéance");
	protected JPanel titrePanel=new JPanel();
	protected JPanel echeancePanel=new JPanel();
	protected JPanel comboPanel=new JPanel();
	protected JPanel pourcentagePanel=new JPanel();
	protected JPanel choix =new JPanel();
	protected JPanel errorPan=new JPanel();
	protected JPanel labelPan=new JPanel();

	public ModifTacheDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		bool=false;
		this.addWindowStateListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	           bool=false;
	           setVisible(false);
	        }
	    });
	}

	public void init(final Tache t, Vector<Categorie> c) throws ParseException {
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
		combo = new JComboBox<String>();
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
			slide.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent event) {
					label.setText("Pourcentage actuel d'avancement: " + ((JSlider) event.getSource()).getValue() + "%");
				}
			});
			pourcentagePanel.setBorder(BorderFactory.createTitledBorder(text4));	
			pourcentagePanel.add(slide);
			add(pourcentagePanel);
			labelPan.add(label);
			add(labelPan);
		}
		annuler = new JButton("Annuler");
		choix.setLayout(new FlowLayout());
		choix.add(annuler);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bool = false;
				setVisible(false);
			}
		});
		if (tache.getType()==1)
		{
			error.setForeground(Color.red);	
			errorPan.add(error);	
			error.setVisible(false);		
			errorPan.setPreferredSize(new Dimension(430,30));	
			add(errorPan);
		}
		if (tache.getType()==2)
		{
			error2.setForeground(Color.red);		
			errorPan.add(error2);			
			error2.setVisible(false);
			errorPan.setPreferredSize(new Dimension(400,30));	
			add(errorPan);
		}
		choix.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				ind = combo.getSelectedIndex() + 1;
				tache.setTitre(titre.getText());
				tache.setEcheance((Date) datePicker.getModel().getValue());
				if (tache.getType() == 2)
				{
					pourcentage = slide.getValue();
					if (t.getDateDebutDate().before(tache.getEcheance()))
					{
						setVisible(false);
						bool=true;
					}
					else
					{					
						error2.setVisible(true);
					}
				}
				else
				{
					if (t.getDateDebutDate().before(tache.getEcheance()))
					{
						setVisible(false);
						bool=true;
					}
					else
					{					
						error.setVisible(true);
					}
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
