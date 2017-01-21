package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TachePonctuelle extends Tache {
	private Date dateCreation;
	private static final long serialVersionUID = -9166031938657356747L;

	public TachePonctuelle(String t, Date e, String s, int i) {
		super(t, e, s, 1, i);
		setDateCreation(new Date());
	}
	/* --------- Fonction qui vérifie si la tâche est en retard et actualise le	statut de la tache --------*/
	public void Retard()  
	{
		Date currentdate = new Date();
		if (currentdate.after(getEcheance()))
			setStatut("En retard");
		else
			setStatut("En cours");
	}
	/* ------- Fonction qui vérifie si la tâche est réalisée ------*/
	public void Realisee() 
	{
		setStatut("Réalisée");
		if ((new Date()).after(getEcheance()))
			setRealiseEnRetard(true);
		else
			setRealiseEnRetard(false);
	}

	public Date getEcheanceInter() {
		return getEcheance();
	}

	public String getDateDebutString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(dateCreation);
	}
	public Date getDateDebutDate(){
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
}
