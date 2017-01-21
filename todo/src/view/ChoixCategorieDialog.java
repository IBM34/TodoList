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

import model.Categorie;

public class ChoixCategorieDialog extends JDialog implements ActionListener {

	protected JComboBox<String> combo, combo2;
	protected JButton ok,annuler;
	protected String text1 = "Choisissez une catégorie";
	protected String text2 = "Choisissez un type de tâche";
	protected int ind;
	protected int type;
	protected boolean bool;
	protected JPanel catPanel=new JPanel();
	protected JPanel typePanel=new JPanel();
	private static final long serialVersionUID = 8372653868523225118L;
	protected JPanel choix =new JPanel();

	public ChoixCategorieDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		ind = -1;
		type = -1;
		bool=false;
		this.addWindowStateListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	           bool=false;
	           setVisible(false);
	        }
	    });
		setLocationRelativeTo(null);		
	}

	public void init(Vector<Categorie> c) {

		combo = new JComboBox<String>();
		combo2 = new JComboBox<String>();
		for (int i = 1; i < c.size(); i++)
			combo.addItem(c.elementAt(i).getTitre());
		combo2.addItem("Ponctuelle");
		combo2.addItem("Long cours");
		catPanel.setBorder(BorderFactory.createTitledBorder(text1));
		typePanel.setBorder(BorderFactory.createTitledBorder(text2));			
		catPanel.setPreferredSize(new Dimension(330,60));
		typePanel.setPreferredSize(new Dimension(330,60));
		catPanel.add(combo);
		typePanel.add(combo2);
		add(catPanel);
		add(typePanel);
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
				type = combo2.getSelectedIndex();
				bool=true;
				setVisible(false);
			}
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
