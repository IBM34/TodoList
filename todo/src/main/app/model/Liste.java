package main.app.model;

import java.io.Serializable;
import java.util.Vector;

public class Liste implements Serializable {

	private static final long serialVersionUID = 7969040661436061229L;
	private Vector<Tache> liste; // liste "principale" des tâches
	private Vector<Tache> realise; // liste des tâches réalisées
	private ListeCategories lcat = new ListeCategories(); // liste des catégories

	public Liste() {
		setListe(new Vector<Tache>());
		setRealise(new Vector<Tache>());
	}
	/* ------- Fonction qui actualise la liste des tâches -------*/
	public void actualise() {
		getListe().removeAllElements();
		for (int i = 0; i < getLcat().getCategories().size(); i++) {  // parcours des catégories
			for (int j = 0; j < getLcat().getCategories().elementAt(i).getTachescategorie().size(); j++) { // parcours des vecteurs de chaque catégories
				// Si tâche réalisée -> ajout dans vecteur liste tâche réalisées
				if (getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j)
						.getStatut() == "Réalisée") {
					getRealise().add(getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j));
					getLcat().getCategories().elementAt(i).getTachescategorie().remove(j);
				} else // sinon ajout dans liste des tâches
					getListe().add(getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j));
			}
		}
	}

	public ListeCategories getLcat() {
		return lcat;
	}

	public void setLcat(ListeCategories lcat) {
		this.lcat = lcat;
	}

	public Vector<Tache> getListe() {
		return liste;
	}

	public void setListe(Vector<Tache> liste) {
		this.liste = liste;
	}

	public Vector<Tache> getRealise() {
		return realise;
	}

	public void setRealise(Vector<Tache> realise) {
		this.realise = realise;
	}

}
