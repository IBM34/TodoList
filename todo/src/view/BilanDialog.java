package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import model.Bilan;

public class BilanDialog extends JDialog{

	private static final long serialVersionUID = 4542388734536519286L;
	protected JProgressBar progressBar1,progressBar2,progressBar3;
	protected ScrollList listebilan=new ScrollList();
	protected JLabel text0=new JLabel("Liste des tâches à réaliser :");
	protected JLabel text1=new JLabel("Pourcentage de tâches non réalisées :");
	protected JLabel text2=new JLabel("Pourcentage de tâches réalisées dans les temps :");
	protected JLabel text3=new JLabel("Pourcentage de tâches réalisées en retard :");
	protected JPanel p0,p1,p2,p3;
	
	public BilanDialog(JFrame parent, String title, boolean modal)
	{
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
	}
	public void init(Bilan b)
	{
		p0=new JPanel();
		p1 =new JPanel();
		p2 =new JPanel();
		p3 =new JPanel();
		p0.add(text0);
		add(p0);
		listebilan.actualise(b.getTachesNonRealBilan(), -1);
		listebilan.setPreferredSize(new Dimension(1000, 210));
		add(listebilan);
		p1.add(text1);
		progressBar1 = new JProgressBar(0, 100);
		progressBar1.setValue((int)b.getPourcentageNonReal());
		progressBar1.setStringPainted(true);
		progressBar1.setPreferredSize(new Dimension(150, 22));
		p1.add(progressBar1);
		p2.add(text2);
		progressBar2 = new JProgressBar(0, 100);
		progressBar2.setValue((int)b.getPourcentageReal1());
		progressBar2.setStringPainted(true);
		progressBar2.setPreferredSize(new Dimension(150, 22));
		p2.add(progressBar2);
		p3.add(text3);
		progressBar3 = new JProgressBar(0, 100);
		progressBar3.setValue((int)b.getPourcentageReal2());
		progressBar3.setStringPainted(true);
		progressBar3.setPreferredSize(new Dimension(150, 22));
		p3.add(progressBar3);
		add(p1);
		add(p2);
		add(p3);
		this.pack();
		this.setVisible(true);
	}


}
