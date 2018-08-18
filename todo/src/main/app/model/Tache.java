package main.app.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Tache implements Serializable {

    private static final long serialVersionUID = 1500454348098702694L;

    private String titre;
    private Date echeance;
    private String categorie; // contient le titre de la catégorie auquelle appartient la tâche
    private String statut;
    private int type; // type de la tâche : 1 si ponctuelle, 2 si long cours
    private boolean realiseEnRetard; // indique si la tâche a été réalisée apres la date d'echeance
    private int importance; // Degré d'importance de la tâche : 1 si important, 2 si moyen, 3 si petit.

    Tache(String t, Date e, String s, int ty, int i) {
        setTitre(t);
        setEcheance(e);
        setStatut(s);
        setType(ty);
        setImportance(i);
    }

    public void Realisee() {
    }

    public void Retard() {
    }

    public int getPourcentage() {
        return -1;
    }

    public String getDateDebutString() {
        return "null";
    }

    public Date getDateDebutDate() {
        return null;
    }

    public void modifPourcentage(int p) {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getType() {
        return type;
    }

    private void setType(int type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getEcheanceInter() {
        return null;
    }

    public boolean isRealiseEnRetard() {
        return realiseEnRetard;
    }

    void setRealiseEnRetard(boolean realiseEnRetard) {
        this.realiseEnRetard = realiseEnRetard;
    }

    public int getImportance() {
        return importance;
    }

    private void setImportance(int importance) {
        this.importance = importance;
    }

}
