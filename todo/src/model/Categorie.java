package model;

import java.io.Serializable;
import java.util.Vector;

public class Categorie implements Serializable {

	private static final long serialVersionUID = 1906502171193613838L;
	private String titre;
	private Vector<Tache> tachescategorie; // Liste des tâches apparteant à la catégorie.

	public Categorie(String t) {
		setTachescategorie(new Vector<Tache>());
		setTitre(t);
	}

	public Vector<Tache> getTachescategorie() {
		return tachescategorie;
	}

	public void setTachescategorie(Vector<Tache> tachescategorie) {
		this.tachescategorie = tachescategorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}
