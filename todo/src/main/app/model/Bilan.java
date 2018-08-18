package main.app.model;

import java.util.Date;
import java.util.Vector;

public class Bilan {
	private Date dateDebutBilan;
	private Date dateFinBilan;
	private float pourcentageReal1; // pourcentage tache réalisées dans les temps
	private float pourcentageReal2; // pourcenatge tache réalisées en retard
	private float pourcentageNonReal; // pourcentage tâche non réalisées alors que
									// dues sur periode.
	private Vector<Tache> tachesNonRealBilan = new Vector<Tache>();
	private Vector<Tache> tachesRealBilan = new Vector<Tache>();

	public Bilan(Vector<Tache> tachesNonReal, Vector<Tache> tachesReal, Date debut, Date fin) {
		dateDebutBilan = debut;
		dateFinBilan = fin;
		for (int i = 0; i < tachesNonReal.size(); i++) {
			if (tachesNonReal.elementAt(i).getEcheance().before(dateFinBilan)) {
				if (tachesNonReal.elementAt(i).getEcheance().after(dateDebutBilan)) {
					tachesNonRealBilan.addElement(tachesNonReal.elementAt(i));
				}
			}
		}
		for (int j = 0; j < tachesReal.size(); j++) {
			if (tachesReal.elementAt(j).getEcheance().before(dateFinBilan)) {
				if (tachesReal.elementAt(j).getEcheance().after(dateDebutBilan)) {
					tachesRealBilan.addElement(tachesReal.elementAt(j));
				}
			}
		}
	}
	public void actualisePourcentages() {
		setPourcentageNonReal(((float) tachesNonRealBilan.size()/(tachesNonRealBilan.size()+tachesRealBilan.size()))*100);
		int cpt = 0;
		int cpt2 = 0;
		for (int i = 0; i < tachesRealBilan.size(); i++) {
			if (tachesRealBilan.elementAt(i).isRealiseEnRetard() == true)
				cpt++;
		}
		setPourcentageReal2(((float)cpt / tachesRealBilan.size()) * 100);
		for (int i = 0; i < tachesRealBilan.size(); i++) {
			if (tachesRealBilan.elementAt(i).isRealiseEnRetard() == false)
				cpt2++;
		}
		setPourcentageReal1(((float)cpt2 / tachesRealBilan.size())* 100);
	}

	public float getPourcentageReal1() {
		return pourcentageReal1;
	}

	public void setPourcentageReal1(float pourcentageReal1) {
		this.pourcentageReal1 = pourcentageReal1;
	}

	public float getPourcentageReal2() {
		return pourcentageReal2;
	}

	public void setPourcentageReal2(float pourcentageReal2) {
		this.pourcentageReal2 = pourcentageReal2;
	}

	public float getPourcentageNonReal() {
		return pourcentageNonReal;
	}

	public void setPourcentageNonReal(float f) {
		this.pourcentageNonReal = f;
	}
	public Vector<Tache> getTachesNonRealBilan() {
		return tachesNonRealBilan;
	}

	public void setTachesNonRealBilan(Vector<Tache> tachesNonRealBilan) {
		this.tachesNonRealBilan = tachesNonRealBilan;
	}
}
