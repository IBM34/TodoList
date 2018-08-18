package main.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TacheLongCours extends Tache {

    private static final long serialVersionUID = -250009031518368983L;
    private final Date dateDebut;
    private int pourcentage;
    private Date echeanceInter; // échéance intermédiaire

    public TacheLongCours(String t, Date e, String s, Date d, int i) {
        super(t, e, s, 2, i);
        dateDebut = d;
        pourcentage = 0;
    }

    /* ------ Fonction qui vérifie si une tâche est en retard et qui met à jour les échéances intermédiaires -------*/
    public void Retard() {
        TimeZone.setDefault(new SimpleTimeZone(60 * 60 * 1000, "CET"));
        Date currentdate = new Date();  // Donne la date courrante.
        long diff = getEcheance().getTime() - dateDebut.getTime();// différence entre début et échéance ( en millisecondes)
        int nbrjour = (int) (diff / (1000 * 60 * 60 * 24)); // différence entre début et échéance ( en nombre de jours)
        GregorianCalendar c1 = new GregorianCalendar();
        GregorianCalendar c2 = new GregorianCalendar();
        GregorianCalendar c3 = new GregorianCalendar();
        GregorianCalendar quart = new GregorianCalendar();
        GregorianCalendar demi = new GregorianCalendar();
        GregorianCalendar troisQuart = new GregorianCalendar();
        c1.setTime(dateDebut);
        c2.setTime(currentdate);
        c3.setTime(getEcheance());
        quart.setTime(dateDebut);
        demi.setTime(dateDebut);
        troisQuart.setTime(dateDebut);
        // Variables qui contiennent les dates d'échéance intermédiaires :
        quart.add(Calendar.DAY_OF_MONTH, nbrjour / 4);
        demi.add(Calendar.DAY_OF_MONTH, nbrjour / 2);
        troisQuart.add(Calendar.DAY_OF_MONTH, 3 * (nbrjour / 4));

        // Actualisation du statut en fonction de l'avancement en % et en jours.

        if (((c2.getTime().after(quart.getTime())) || (c2.getTime().equals(quart.getTime())))
                && ((c2.getTime().before(demi.getTime())) || (c2.getTime().equals(demi.getTime())))) {
            if (pourcentage < 25)
                setStatut("En retard");
            else
                setStatut("En cours");
        } else if (((c2.getTime().after(demi.getTime())) || (c2.getTime().equals(demi.getTime())))
                && ((c2.getTime().before(troisQuart.getTime())) || (c2.getTime().equals(troisQuart.getTime())))) {
            if (pourcentage < 50)
                setStatut("En retard");
            else
                setStatut("En cours");
        } else if (((c2.getTime().after(troisQuart.getTime())) || (c2.getTime().equals(troisQuart.getTime())))
                && ((c2.getTime().before(c3.getTime())) || (c2.getTime().equals(c3.getTime())))) {
            if (pourcentage < 75)
                setStatut("En retard");
            else
                setStatut("En cours");
        } else if (c2.getTime().after(c3.getTime())) {
            if (pourcentage < 100)
                setStatut("En retard");
            else
                setStatut("En cours");
        }
        // Mise à jour des échéances intermédiaires
        if (getPourcentage() < 100)
            setEcheanceInter(getEcheance());
        if (getPourcentage() < 75)
            setEcheanceInter(troisQuart.getTime());
        if (getPourcentage() < 50)
            setEcheanceInter(demi.getTime());
        if (getPourcentage() < 25)
            setEcheanceInter(quart.getTime());
    }

    /* ------- Fonction qui vérifie si la tâche est réalisée ------*/
    public void Realisee() {
        if (pourcentage == 100) {
            setStatut("Réalisée");
            if ((new Date()).after(getEcheance()))
                setRealiseEnRetard(true);
            else
                setRealiseEnRetard(false);
        }
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void modifPourcentage(int p) {
        pourcentage = p;
    }

    public String getDateDebutString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dateDebut);
    }

    public Date getDateDebutDate() {
        return dateDebut;
    }

    public Date getEcheanceInter() {
        return echeanceInter;
    }

    private void setEcheanceInter(Date echeanceInter) {
        this.echeanceInter = echeanceInter;
    }
}
