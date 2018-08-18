package main.app.model;

import java.util.Vector;

public class ListeImportance extends Liste {

    private static final long serialVersionUID = -951497650175641301L;
    Tache tacheTemp;// variable de tâche temporaire
    int indice;
    private Vector<Tache> listeTriee = new Vector<>(); // contient la liste triée des tâches

    public ListeImportance() {
        super();
    }

    /* ------- Fonction qui tri les tâches en fonction de leur degré d'importance -------*/
    public void triTaches() {
        int i = 0;
        int j = 0;
        int k = 0;
        // compteurs :
        int cpt = 0;
        int cpt2 = 0;
        int cpt3 = 0;

        while ((cpt != 1) && i < getListe().size()) {  // boucle qui recherche une tache importante (1 max)
            if (getListe().elementAt(i).getImportance() == 1) {
                getListeTriee().addElement(getListe().elementAt(i));
                cpt++;
            }
            i++;

        }
        while ((cpt2 != 3) && j < getListe().size()) { // boucle qui recherche les tâches moyennes (3 max)
            if (getListe().elementAt(j).getImportance() == 2) {
                getListeTriee().addElement(getListe().elementAt(j));
                cpt2++;
            }
            j++;
        }
        while ((cpt3 != 5) && k < getListe().size()) { // boucle qui recherche les tâches petites (5 max)
            if (getListe().elementAt(k).getImportance() == 3) {
                getListeTriee().addElement(getListe().elementAt(k));
                cpt3++;
            }
            k++;
        }

    }

    public Vector<Tache> getListeTriee() {
        return listeTriee;
    }

    public void setListeTriee(Vector<Tache> listeTriee) {
        this.listeTriee = listeTriee;
    }

}
