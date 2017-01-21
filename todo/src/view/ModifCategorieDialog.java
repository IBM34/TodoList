package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Categorie;

public class ModifCategorieDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -602805562741607121L;
	protected JTextField titre = new JTextField(" ", 20);
	protected JButton ok, annuler;
	protected String text1 = "Entrez le titre";
	protected String text2 = "Selectionnez la catégorie à renommer";
	protected String text3 = "Selectionnez la catégorie à supprimer";
	protected JComboBox<String> combo;
	protected int ind;
	protected String titrecat;
	protected boolean bool;
	protected JPanel titrePanel=new JPanel();
	protected JPanel renomPanel=new JPanel();
	protected JPanel supPanel=new JPanel();
	protected JPanel choix =new JPanel();
	

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
	           bool=false;
	           setVisible(false);
	        }
	    });
	}

	public void initAjouter() {
		titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
		titrePanel.setPreferredSize(new Dimension(330,60));
		titrePanel.add(titre);
		add(titrePanel);
		annuler = new JButton("Annuler");
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
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				titrecat = titre.getText();
				bool = true;
				setVisible(false);
			}
		});
		add(choix);
		this.pack();
		setVisible(true);

	}

	public void initRenommer(Vector<Categorie> c) {
		combo = new JComboBox<String>();
		for (int i = 1; i < c.size(); i++)
			combo.addItem(c.elementAt(i).getTitre());
		renomPanel.setBorder(BorderFactory.createTitledBorder(text2));
		titrePanel.setBorder(BorderFactory.createTitledBorder(text1));
		renomPanel.setPreferredSize(new Dimension(330,60));
		titrePanel.setPreferredSize(new Dimension(330,60));
		renomPanel.add(combo);
		titrePanel.add(titre);
		add(renomPanel);
		add(titrePanel);
	
		annuler = new JButton("Annuler");
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

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ind = combo.getSelectedIndex() + 1;
				titrecat = titre.getText();
				setVisible(false);
				bool=true;
			}
		});
		add(choix);
		this.pack();
		setVisible(true);
	}

	public void initSupprimer(Vector<Categorie> c) {
		combo = new JComboBox<String>();
		for (int i = 1; i < c.size(); i++)
			combo.addItem(c.elementAt(i).getTitre());
		supPanel.setBorder(BorderFactory.createTitledBorder(text3));
		supPanel.setPreferredSize(new Dimension(330,60));
		supPanel.add(combo);
		add(supPanel);
		annuler = new JButton("Annuler");
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

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ind = combo.getSelectedIndex() + 1;
				setVisible(false);
				bool=true;
			}
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
