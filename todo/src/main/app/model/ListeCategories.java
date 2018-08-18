package main.app.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

public class ListeCategories implements Serializable {

	private static final long serialVersionUID = 8073333124472456114L;

	private Vector<Categorie> categories; // Vecteur contenant les catégories
	// Création de 3 catégories par défaut ("sans", "travail","personnel")
	private Categorie c1;
	private Categorie c2;
	private Categorie c3;

	public ListeCategories() {
		setCategories(new Vector<Categorie>());
		c1 = new Categorie("Sans");
		c2 = new Categorie("Travail");
		c3 = new Categorie("Personnel");
		getCategories().add(c1);
		getCategories().add(c2);
		getCategories().add(c3);
	}
 /* ------- Fonction qui crée une tâche (ponctuelle ou long cours) en fontion de la catégorie choisie -----*/
	public void choixCategorie(int ind, int type, String titre, Date echeance, Date debut,int importance) throws ParseException {

		if (type == 0) { // si la catégire choisie est "ponctuelle"
			// On créé la tâche
			getCategories().elementAt(ind).getTachescategorie().add(new TachePonctuelle(titre, echeance, "En cours", importance));
			// on remplie le nom de la categorie choisit dans la variable categorie de la tâche
			getCategories().elementAt(ind).getTachescategorie()
					.elementAt((getCategories().elementAt(ind).getTachescategorie().size()) - 1)
					.setCategorie(getCategories().elementAt(ind).getTitre());
			
		} else if (type == 1) { // si la catégire choisie est "long cours"
			// On créé la tâche
			getCategories().elementAt(ind).getTachescategorie()
					.add(new TacheLongCours(titre, echeance, "En cours", debut, importance));
			// on remplie le nom de la categorie choisit dans la variable categorie de la tâche
			getCategories().elementAt(ind).getTachescategorie()
					.elementAt((getCategories().elementAt(ind).getTachescategorie().size()) - 1)
					.setCategorie(getCategories().elementAt(ind).getTitre());
		}

	}
	/* ----- Fonction qui permet d'ajouter une catégorie -----*/
	public void addCategorie(String titre) {
		getCategories().add(new Categorie(titre));
	}
	/* ----- Fonction qui permet de renommer une catégorie -----*/
	public void renameCategorie(int ind, String titre) {
		// On change le titre de la catégorie
		getCategories().elementAt(ind).setTitre(titre); 
		// On actualise le nom de la categorie dans la variable categorie chaque tâches appartenant à cette catégorie.
		for (int i = 0; i < getCategories().elementAt(ind).getTachescategorie().size(); i++) {
			getCategories().elementAt(ind).getTachescategorie().elementAt(i).setCategorie(titre);
		}
	}
	/* ----- Fonction qui permet de supprimer une catégorie -----*/
	public void deleteCategorie(int ind) {
		// On actualise la variable categorie de chaque tache appartenant a la categorie que l'on va supprimer avec le titre "sans"
		for (int i = 0; i < getCategories().elementAt(ind).getTachescategorie().size(); i++) {
			getCategories().elementAt(ind).getTachescategorie().elementAt(i).setCategorie("Sans");
		}
		// On deplace toute les taches de la categorie que l'on va supprimer dans la categorie "Sans"
		getCategories().elementAt(0).getTachescategorie().addAll(getCategories().elementAt(ind).getTachescategorie());
		getCategories().remove(ind);
	}
	/* ------ Fonction qui permet de modifier une tâche -------*/
	public void modifTache(Tache t, int ind, int p) throws ParseException {
		int indice = 0;
		boolean bool = false;
		int i = 0;
		while (bool == false && i < getCategories().size()) { // On recherche à quelle catégorie appartient la tâche passée en paramètre.
			if ((getCategories().elementAt(i).getTitre()) == t.getCategorie()) {
				bool = true;
				indice = i;
			}
			i++;
		}
		int index = getCategories().elementAt(indice).getTachescategorie().indexOf(t); // On stocke l'indice de la tâche du vecteur.
		// On actualise le titre et l'écheance
		getCategories().elementAt(indice).getTachescategorie().elementAt(index).setTitre(t.getTitre());
		getCategories().elementAt(indice).getTachescategorie().elementAt(index).setEcheance(t.getEcheance());

		if (t.getType() == 2) // Si c'est une tâche long cours, on actualise le pourcentage
			getCategories().elementAt(indice).getTachescategorie().elementAt(index).modifPourcentage(p);
		
		// On actualise enfin la catégorie de la tâche
		getCategories().elementAt(ind).getTachescategorie().addElement(t);
		getCategories().elementAt(indice).getTachescategorie().remove(t);
		int index2 = getCategories().elementAt(ind).getTachescategorie().indexOf(t);
		getCategories().elementAt(ind).getTachescategorie().elementAt(index2)
				.setCategorie(getCategories().elementAt(ind).getTitre());

	}
	/*--------- Fonction qui met le statut d'une tâche à "Réalisée"-----*/
	public void modifTacheStatut(Tache t)
	{
		int indice = 0;
		boolean bool = false;
		int i = 0;
		while (bool == false && i < getCategories().size()) { // On recherche à quelle catégorie appartient la tâche passée en paramètre.
			if ((getCategories().elementAt(i).getTitre()) == t.getCategorie()) {
				bool = true;
				indice = i;
			}
			i++;
		}
		int index = getCategories().elementAt(indice).getTachescategorie().indexOf(t); // On stocke l'indice de la tâche du vecteur.
		getCategories().elementAt(indice).getTachescategorie().elementAt(index).Realisee(); 
	}
	/* ------- Fonction supprime une tâche -----*/
	public void deleteTache(Tache t)
	{
		int indice = 0;
		boolean bool = false;
		int i = 0;
		while (bool == false && i < getCategories().size()) { // On recherche à quelle catégorie appartient la tâche passée en paramètre.
			if ((getCategories().elementAt(i).getTitre()) == t.getCategorie()) {
				bool = true;
				indice = i;
			}
			i++;
		}
		int index = getCategories().elementAt(indice).getTachescategorie().indexOf(t); // On stocke l'indice de la tâche du vecteur.
		// On supprime la tâche
		getCategories().elementAt(indice).getTachescategorie().remove(getCategories().elementAt(indice).getTachescategorie().elementAt(index));
	}
	public Vector<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Vector<Categorie> categories) {
		this.categories = categories;
	}
}
