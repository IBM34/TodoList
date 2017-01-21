package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ScrollBoutons extends JPanel {

	private static final long serialVersionUID = -4111485753251728648L;
	protected JButton listeBoutons[] = new JButton[5];
	protected Font font = new Font("ubuntu",Font.BOLD,14);

	public ScrollBoutons() {
		setPreferredSize(new Dimension(162, 380));
		listeBoutons[0] = new JButton("Liste des tâches");
		listeBoutons[1] = new JButton("Liste Simple");
		listeBoutons[2] = new JButton("Liste Intercalée");
		listeBoutons[3] = new JButton("Liste Importance");
		listeBoutons[4] = new JButton("Tâches Réalisées");
		for (int i=0;i<5;i++)
		{
			listeBoutons[i].setPreferredSize(new Dimension(157, 75));
			listeBoutons[i].setFocusPainted(false);
			listeBoutons[i].setFont(font);
			listeBoutons[i].setBackground(Color.WHITE);
			add(listeBoutons[i]);
		}		
	}

	public void actualise(int ind) {
		listeBoutons[ind].setForeground(Color.BLUE);
		listeBoutons[ind].setBackground(Color.WHITE);
		
		for (int i = 0; i < 5; i++) {
			if (i != ind)
				listeBoutons[i].setForeground(Color.BLACK);
		}
	}
}