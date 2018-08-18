package main.app.model;

import java.util.Vector;

public class ListeIntercale extends Liste {

	private static final long serialVersionUID = -6191004876909405687L;
	private Vector<Tache> listeTriee = new Vector<Tache>(); // contient la liste triée des tâches
	Tache tacheTemp; // variable de tâche temporaire
	int indice;
	public ListeIntercale() {
		super();
	}
	/* ------- Fonction qui tri les tâches par ordre d'échéances intermédiares croissantes -------*/
	public void triTaches() {
		while (!(getListe().isEmpty())) {
			tacheTemp = getListe().elementAt(0);
			indice = 0;
			for (int i = 1; i < getListe().size(); i++) {
				if ((getListe().elementAt(i).getEcheanceInter()).before(tacheTemp.getEcheanceInter())) {
					tacheTemp = getListe().elementAt(i);
					indice = i;
				}
			}

			getListeTriee().addElement(tacheTemp);
			getListe().removeElementAt(indice);
		}
	}

	public Vector<Tache> getListeTriee() {
		return listeTriee;
	}

	public void setListeTriee(Vector<Tache> listeTriee) {
		this.listeTriee = listeTriee;
	}

}
