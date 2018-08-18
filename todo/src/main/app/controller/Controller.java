package main.app.controller;

import main.app.model.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

public class Controller {
    private final ListeSimple lsimple = new ListeSimple();
    private final ListeIntercale lintercale = new ListeIntercale();
    private final ListeImportance limportance = new ListeImportance();
    // Instances des différentes listes de tâches
    private Liste l = new Liste();

    /* ------ Fonction  qui s'occupe de la sérialisation de la partie main.app.model------*/
    public void Serializer() throws IOException, URISyntaxException {
        // ouverture d'un flux sur un fichier
        URL resourceUrl = getClass().getResource("/resources/todo.ser");
        File file = new File(resourceUrl.toURI());
        OutputStream output = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(output);

        // sérialization de l'objet
        oos.writeObject(l);
        oos.close();
    }

    /* ------ Fonction qui s'occupe de la désérialisation de la partie main.app.model ------*/
    public void Deserializer() throws IOException, ClassNotFoundException, URISyntaxException {
        URL resourceUrl = getClass().getResource("/resources/todo.ser");
        File file = new File(resourceUrl.toURI());
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        l = (Liste) ois.readObject();
        ois.close();
    }

    /* ------ Fonction qui retourne la liste des catégories------*/
    public Vector<Categorie> ListeCat() {
        return l.getLcat().getCategories();
    }

    /* ------ Fonction qui apelle une methode du main.app.model pour créé une tâche en fonction de la catégorie choisie------*/
    public void ChoixCat(int ind, int type, String titre, Date echeance, Date debut, int importance) throws ParseException {
        l.getLcat().choixCategorie(ind, type, titre, echeance, debut, importance);
    }

    /* ------ Fonction qui permet d'ajouter une catégorie------*/
    public void AddCat(String titre) {
        l.getLcat().addCategorie(titre);
    }

    /* ------ Fonction qui permet de renommer une catégorie ------*/
    public void RenameCat(int ind, String titre) {
        l.getLcat().renameCategorie(ind, titre);
    }

    /* ------ Fonction qui permet de supprimer une catégorie ------*/
    public void DeleteCat(int ind) {
        l.getLcat().deleteCategorie(ind);
    }

    /* ------ Fonction qui retourne la liste "principale" des tâches ------*/
    public Vector<Tache> getListe() throws ParseException {
        Real();
        l.actualise();
        return l.getListe();
    }

    /* ------ Fonction qui retourne la liste simple des tâches (triée par échéances croissantes) ------*/
    public Vector<Tache> getListeSimple() throws ParseException {
        Real();
        l.actualise();
        lsimple.setListe(l.getListe());
        lsimple.getListeTriee().removeAllElements();
        lsimple.triTaches();
        return lsimple.getListeTriee();
    }

    /* ------ Fonction  qui retourne la liste intercalée des tâches (triée par échéances intermédiaires croissantes)------*/
    public Vector<Tache> getListeIntercale() throws ParseException {
        Real();
        l.actualise();
        lintercale.setListe(l.getListe());
        lintercale.getListeTriee().removeAllElements();
        lintercale.triTaches();
        return lintercale.getListeTriee();
    }

    /* ------ Fonction  qui retourne la liste triée par degrée d'importance ------*/
    public Vector<Tache> getListeImportance() throws ParseException {
        Real();
        l.actualise();
        limportance.setListe(l.getListe());
        limportance.getListeTriee().removeAllElements();
        limportance.triTaches();
        return limportance.getListeTriee();
    }

    /* ------ Fonction qui retourne la liste des tâches réalisées ------*/
    public Vector<Tache> getListeReal() {
        return l.getRealise();
    }

    /* ------ Fonction qui retourne une tâche en fonction de son type de liste et de son indice------*/
    public Tache getTache(int type, int ind) {
        if (type == 0)
            return l.getListe().elementAt(ind);
        else if (type == 1)
            return lsimple.getListeTriee().elementAt(ind);
        else if (type == 2)
            return lintercale.getListeTriee().elementAt(ind);
        else if (type == 3)
            return limportance.getListeTriee().elementAt(ind);
        else return l.getRealise().elementAt(ind);
    }

    /* ------ Fonction qui permet de modifier une tâche ------*/
    public void ModifTache(Tache t, int ind, int p) throws ParseException {
        l.getLcat().modifTache(t, ind, p);
    }

    /* ------ Fonction qui met le statut d'une tâche à "réalisée" ------*/
    public void ModifTacheStatut(Tache t) {
        l.getLcat().modifTacheStatut(t);
        l.actualise();
    }

    /* ------ Fonction qui permet de supprimer une tâche ------*/
    public void deleteTache(Tache t) {
        l.getLcat().deleteTache(t);
        l.actualise();
    }

    /* ------ Fonction qui permet de supprimer une tâche réalisée ------*/
    public void deleteTacheReal(int indice) {
        l.getRealise().remove(indice);
        l.actualise();
    }

    /* ------ Fonction qui vérifie si une tâche est en retard ------*/
    public void Retard() throws ParseException {
        for (int i = 0; i < l.getLcat().getCategories().size(); i++) {
            for (int j = 0; j < l.getLcat().getCategories().elementAt(i).getTachescategorie().size(); j++) {
                l.getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j).Retard();
            }
        }
    }

    /* ------ Fonction qui vérifie si une tâche est réalisée------*/
    private void Real() {
        for (int i = 0; i < l.getLcat().getCategories().size(); i++) {
            for (int j = 0; j < l.getLcat().getCategories().elementAt(i).getTachescategorie().size(); j++) {
                if (l.getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j).getType() == 2)
                    l.getLcat().getCategories().elementAt(i).getTachescategorie().elementAt(j).Realisee();
            }
        }
    }

    /* ------ Fonction qui permet de crée un bilan en focntion de des dates de début et de fin fournies par l'utilisateur ------*/
    public Bilan CreerBilan(Date deb, Date fin) throws ParseException {
        Bilan newBilan = new Bilan(getListe(), getListeReal(), deb, fin);
        newBilan.actualisePourcentages();

        return newBilan;
    }
}
