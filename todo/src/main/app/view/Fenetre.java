package main.app.view;

import main.app.controller.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5172254850261170931L;
	protected Controller control = new Controller(); //instance du main.app.controller
	protected ChoixCategorieDialog choix;
	protected CreerTacheDialog tache;
	protected ModifCategorieDialog addcat;
	protected ModifCategorieDialog RenameCat;
	protected ModifCategorieDialog DeleteCat;
	protected SelectBilanDialog newBilan;
	protected BilanDialog bilan;
	protected ScrollList listetache = new ScrollList();
	protected ScrollBoutons BoutonsListe = new ScrollBoutons();
	protected ModifTacheDialog modif;
	protected JPanel gauche, centre;
	protected JMenuBar barmenu;
	protected JMenu MenuTache;
	protected JMenu MenuCategorie;
	protected JMenu MenuBilan;
	protected JMenuItem itemCreerTache;
	protected JMenuItem itemAjouterCategorie;
	protected JMenuItem itemRenommerCategorie;
	protected JMenuItem itemSupprimerCategorie;
	protected JMenuItem itemCreerBilan;
	protected JPanel droite = new JPanel();
	protected int ind, ind2;
	protected Font font = new Font("ubuntu", Font.BOLD, 14);

	public Fenetre() throws FileNotFoundException, ClassNotFoundException, IOException, ParseException, URISyntaxException {
		setTitle("ToDoList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		control.Deserializer();
		InitMenu();
		BoutonsListe.actualise(0);
		add(BoutonsListe, "West");
		initPanel();
		initEcouteurs();
		pack();
	}
	/* ------- Initialisation de la barre de menu ------*/
	public void InitMenu() throws ParseException {
		barmenu = new JMenuBar();
		MenuTache = new JMenu("Tache");
		MenuCategorie = new JMenu("Categorie");
		MenuBilan = new JMenu("Bilan");
		itemCreerTache = new JMenuItem("Creer une tache");
		itemAjouterCategorie = new JMenuItem("Ajouter une categorie");
		itemRenommerCategorie = new JMenuItem("Renommer une categorie");
		itemSupprimerCategorie = new JMenuItem("Supprimer une categorie");
		itemCreerBilan = new JMenuItem("Creer un bilan");
		setJMenuBar(barmenu);
		barmenu.add(MenuTache);
		barmenu.add(MenuCategorie);
		barmenu.add(MenuBilan);
		MenuTache.add(itemCreerTache);
		MenuCategorie.add(itemAjouterCategorie);
		MenuCategorie.add(itemRenommerCategorie);
		MenuCategorie.add(itemSupprimerCategorie);
		MenuBilan.add(itemCreerBilan);
	}
	/* ------- Initialisation du JPanel principal ------*/
	public void initPanel() throws ParseException {
		droite.setLayout(new FlowLayout(FlowLayout.LEFT));
		droite.setPreferredSize(new Dimension(940, 410));
		JLabel titre = new JLabel();
		JLabel statut = new JLabel();
		JLabel echeance = new JLabel();
		JLabel categorie = new JLabel();
		JLabel importance = new JLabel();
		JLabel debut = new JLabel();
		JLabel pourcentage = new JLabel();
		titre.setPreferredSize(new Dimension(165, 15));
		statut.setPreferredSize(new Dimension(95, 15));
		echeance.setPreferredSize(new Dimension(90, 15));
		categorie.setPreferredSize(new Dimension(95, 15));
		importance.setPreferredSize(new Dimension(120, 15));
		debut.setPreferredSize(new Dimension(80, 15));
		pourcentage.setPreferredSize(new Dimension(200, 15));
		titre.setText("<HTML><U>Titre</U></HTML>");
		statut.setText("<HTML><U>Statut</U></HTML>");
		echeance.setText("<HTML><U>Echeance</U></HTML>");
		categorie.setText("<HTML><U>Catégorie</U></HTML>");
		importance.setText("<HTML><U>Importance</U></HTML>");
		debut.setText("<HTML><U>Début</U></HTML>");
		pourcentage.setText("<HTML><U>Pourcentage</U></HTML>");
		titre.setForeground(Color.BLUE);
		statut.setForeground(Color.BLUE);
		echeance.setForeground(Color.BLUE);
		categorie.setForeground(Color.BLUE);
		importance.setForeground(Color.BLUE);
		debut.setForeground(Color.BLUE);
		pourcentage.setForeground(Color.BLUE);
		titre.setFont(font);
		statut.setFont(font);
		echeance.setFont(font);
		categorie.setFont(font);
		importance.setFont(font);
		debut.setFont(font);
		pourcentage.setFont(font);
		droite.add(titre);
		droite.add(statut);
		droite.add(debut);
		droite.add(echeance);
		droite.add(categorie);
		droite.add(importance);
		droite.add(pourcentage);
		control.Retard();
		actualiseListe();
		droite.add(listetache);
		add(droite, "East");
	}
	/* -------- Initialisation des ecouteurs d'actions de l'utilisateur --------*/
	public void initEcouteurs() {
		// Ecouteur de l'item CreerTache du menu Tache
		itemCreerTache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				choix = new ChoixCategorieDialog(null, "Creer une tâche", true);
				choix.init(control.ListeCat()); // Actualisation du main.app.model et de la vue par le biais du main.app.controller
				if (choix.bool == true) {
					try {
						tache = new CreerTacheDialog(null, "Creer une tâche", true, choix.type);
						if (tache.bool == true) {
							// Actualisation du main.app.model et de la vue par le biais du main.app.controller
							control.ChoixCat(choix.ind, choix.type, tache.titretache, tache.echeance, tache.debut,tache.importance);
							control.Retard();
							actualiseListe();
							initSupprime();
							initReal();
							revalidate();
							initModif();
							initSurvol();
							control.Serializer(); // sérialisation
						}
					} catch (ParseException | IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		// Ecouteur de l'item AjouterCategorie du menu Catégorie
		itemAjouterCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addcat = new ModifCategorieDialog(null, "Ajouter une catégorie", true);
				addcat.initAjouter();
				if (addcat.bool == true) {
					// Actualisation du main.app.model par le biais du main.app.controller
					control.AddCat(addcat.titrecat);
				}
				try {
					control.Serializer();
				} catch (FileNotFoundException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Ecouteur de l'item RenommerCategorie du menu Catégorie
		itemRenommerCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				RenameCat = new ModifCategorieDialog(null, "Renommer une catégorie", true);
				// Actualisation du main.app.model et de la vue par le biais du main.app.controller
				RenameCat.initRenommer(control.ListeCat());
				if (RenameCat.bool == true) {
					// Actualisation du main.app.model par le biais du main.app.controller
					control.RenameCat(RenameCat.ind, RenameCat.titrecat);
				}
				try {
					actualiseListe();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initSupprime();
				initReal();
				revalidate();
				initModif();
				initSurvol();
				try {
					control.Serializer();
				} catch (FileNotFoundException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Ecouteur de l'item SupprimerCategorie du menu Catégorie
		itemSupprimerCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DeleteCat = new ModifCategorieDialog(null, "Supprimer une catégorie", true);
				DeleteCat.initSupprimer(control.ListeCat());
				if (DeleteCat.bool == true) {
					control.DeleteCat(DeleteCat.ind);
				}
				try {
					actualiseListe();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initSupprime();
				initReal();
				revalidate();
				initModif();
				initSurvol();
				try {
					control.Serializer();
				} catch (FileNotFoundException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Ecouteur de l'item CreerBilan du menu Bilan
		itemCreerBilan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newBilan = new SelectBilanDialog(null, "Creer un bilan", true);
				newBilan.init();
				if (newBilan.bool == true) {
					bilan = new BilanDialog(null, "Bilan", true);
					try {
						bilan.init(control.CreerBilan(newBilan.debut, newBilan.fin));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					control.Serializer();
				} catch (FileNotFoundException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Ecouteur du bouton "liste des tâche"
		BoutonsListe.listeBoutons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoutonsListe.actualise(0);
				try {
					listetache.actualise(control.getListe(), 0);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initModif();
				initSurvol();
				initSupprime();
				initReal();
				revalidate();
			}
		});
		// Ecouteur du bouton "liste simple"
		BoutonsListe.listeBoutons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoutonsListe.actualise(1);
				try {
					listetache.actualise(control.getListeSimple(), 1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initModif();
				initSurvol();
				initSupprime();
				initReal();
				revalidate();
			}
		});
		// Ecouteur du bouton "liste intercalée"
		BoutonsListe.listeBoutons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BoutonsListe.actualise(2);
				try {
					listetache.actualise(control.getListeIntercale(), 2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initModif();
				initSurvol();
				initSupprime();
				initReal();
				revalidate();

			}
		});
		// Ecouteur du bouton "liste importance"
		BoutonsListe.listeBoutons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					listetache.actualise(control.getListeImportance(), 3);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initModif();
				initSurvol();
				initSupprime();
				initReal();
				revalidate();
				BoutonsListe.actualise(3);

			}
		});
		// Ecouteur du bouton "tâches réalisées"
		BoutonsListe.listeBoutons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listetache.actualise(control.getListeReal(), 4);
				initSurvol();
				initSupprime();
				initReal();
				revalidate();
				BoutonsListe.actualise(4);

			}
		});

		initModif();
		initSurvol();
		initSupprime();
		initReal();

	}
	/* ------- Initialisation des ecouteurs sur les JPanel des tâches pour leurs modifications ------*/
	public void initModif() {
		for (int i = 0; i < listetache.taches.length; i++) {
			ind = i;
			listetache.taches[i].addMouseListener(new MouseAdapter() {
				protected int indice = ind;

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						modif = new ModifTacheDialog(null, "Modifier la tâche", true);
						try {
							modif.init(control.getTache(listetache.type, indice), control.ListeCat());
							if (modif.bool == true) {
								control.ModifTache(modif.tache, modif.ind, modif.pourcentage);
								control.Retard();
								actualiseListe();
							}
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						if (modif.bool == true) {
							initSupprime();
							initReal();
							revalidate();
							initSurvol();
							initModif();
							try {
								control.Serializer();
							} catch (IOException | URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			});
		}
	}
	/* ------- Initialisation de l'effet de survol des JPanel des tâches -------*/
	public void initSurvol() {
		for (int i = 0; i < listetache.taches.length; i++) {
			ind = i;
			listetache.taches[i].addMouseListener(new MouseAdapter() {
				protected int indice = ind;

				public void mouseEntered(MouseEvent e) {
					listetache.taches[indice].setBackground(Color.GRAY);
					listetache.taches[indice].pourcentage.setBackground(Color.GRAY);
				}
			});
			listetache.taches[i].addMouseListener(new MouseAdapter() {
				protected int indice = ind;

				public void mouseExited(MouseEvent e) {
					listetache.taches[indice].setBackground(Color.lightGray);
					listetache.taches[indice].pourcentage.setBackground(Color.lightGray);
				}
			});
		}
	}
	/* ------- Initialisation des boutons supprimer des JPanel des tâches -------*/
	public void initSupprime() {
		JButton tabsup[] = new JButton[listetache.taches.length];
		for (int i = 0; i < listetache.taches.length; i++) {
			ind2 = i;
			tabsup[i] = new JButton("<HTML><FONT COLOR=#FF0040>X</FONT></HTML>");
			tabsup[i].setBackground(Color.WHITE);
			listetache.taches[i].add(tabsup[i]);
			// Création d'un ActionListenner pour chaque bouton
			tabsup[i].addActionListener(new ActionListener() {
				protected int indice = ind2;

				public void actionPerformed(ActionEvent arg0) {
					if (listetache.type == 4)
						control.deleteTacheReal(indice);
					else
						control.deleteTache(control.getTache(listetache.type, indice));
					try {
						actualiseListe();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					initSupprime();
					initReal();
					revalidate();
					initSurvol();
					initModif();
					try {
						control.Serializer();
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	/* ------- Initialisation des boutons "réalisée" des JPanel des tâches (uniquement pour les tâches ponctuelles -------*/
	public void initReal() {
		if (listetache.type != 4) {
			JButton tabreal[] = new JButton[listetache.taches.length];
			for (int i = 0; i < listetache.taches.length; i++) {
				ind2 = i;
				tabreal[i] = new JButton("<HTML><FONT COLOR=#04B404>&#10004;</FONT></HTML>");
				tabreal[i].setBackground(Color.WHITE);
				if (listetache.taches[i].type == 2)
					tabreal[i].setVisible(false);
				listetache.taches[i].add(tabreal[i]);
				// Création d'un ActionListenner pour chaque bouton
				tabreal[i].addActionListener(new ActionListener() {
					protected int indice = ind2;

					public void actionPerformed(ActionEvent arg0) {
						control.ModifTacheStatut(control.getTache(listetache.type, indice));
						try {
							actualiseListe();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						initSupprime();
						initReal();
						revalidate();
						initSurvol();
						initModif();
						try {
							control.Serializer();
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}
	/* -------- Fonction qui Actualise l'affichage de la liste en fonction du type de liste --------*/
	public void actualiseListe() throws ParseException {
		if (listetache.type == 0)
			listetache.actualise(control.getListe(), 0);
		else if (listetache.type == 1)
			listetache.actualise(control.getListeSimple(), 1);
		else if (listetache.type == 2)
			listetache.actualise(control.getListeIntercale(), 2);
		else if (listetache.type == 3)
			listetache.actualise(control.getListeImportance(), 3);
		else
			listetache.actualise(control.getListeReal(), 4);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String args[])
			throws FileNotFoundException, ClassNotFoundException, IOException, ParseException, URISyntaxException {
		Fenetre f = new Fenetre();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}
