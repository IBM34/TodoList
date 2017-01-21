package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Tache;

public class TachePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected JLabel titre;
	protected JLabel statut;
	protected JLabel echeance;
	protected JLabel categorie;
	protected JLabel importance;
	protected JLabel debut;
	protected JPanel pourcentage;
	protected int type;
	protected JProgressBar progressBar;
	protected DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected Font font = new Font("ubuntu",Font.BOLD,14);

public TachePanel(Tache t, int typelist)
{
	type=t.getType();
	setPreferredSize(new Dimension(910,45));
	setBackground(Color.LIGHT_GRAY);
	titre=new JLabel(t.getTitre());
	statut=new JLabel(t.getStatut());
	if (typelist==2)
		echeance=new JLabel(dateFormat.format(t.getEcheanceInter()));
	else
		echeance=new JLabel(dateFormat.format(t.getEcheance()));
	categorie=new JLabel(t.getCategorie());
	if (t.getImportance()==1)
		importance=new JLabel("Importante");
	else if (t.getImportance()==2)
		importance=new JLabel("Moyenne");
	else
		importance=new JLabel("Petite");
	debut=new JLabel(t.getDateDebutString());
	pourcentage=new JPanel();
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
	
	if (t.getPourcentage()!=-1) // tâche long cours
	{		
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(t.getPourcentage());
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(125, 22));
		pourcentage.add(progressBar);
		add(pourcentage);
	}
	else
		add(pourcentage);
}

}
