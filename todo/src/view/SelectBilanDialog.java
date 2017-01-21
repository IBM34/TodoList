package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class SelectBilanDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 4449810492318687617L;
	protected JButton ok, annuler;
	protected String text1 = "Selectionnez la date de début ";
	protected String text2 = "Selectionnez la date de fin";
	protected JLabel error=new JLabel("La date de début doit être antérieure à celle de fin");
	protected boolean bool;
	protected JPanel dateDebPanel = new JPanel();
	protected JPanel dateFinPanel = new JPanel();
	protected JPanel choix = new JPanel();
	protected UtilDateModel debModel, finModel;
	protected Properties p = new Properties();
	protected JDatePanelImpl dateDebPan, dateFinPan;
	protected JDatePickerImpl dateDebPicker, dateFinPicker;
	protected JPanel errorPan=new JPanel();
	protected Date debut;
	protected Date fin;

	public SelectBilanDialog(JFrame parent, String title, boolean modal) {
			super(parent, title, modal);
			setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			this.setResizable(false);
			setBackground(Color.WHITE);
			getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
			bool=false;
			this.addWindowStateListener(new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		           bool=false;
		           setVisible(false);
		        }
		    });
			setLocationRelativeTo(null);			
		}

	public void init() {
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		debModel = new UtilDateModel();
		debModel.setValue(new Date());
		finModel = new UtilDateModel();
		finModel.setValue(new Date());
		dateDebPan = new JDatePanelImpl(debModel, p);
		dateFinPan = new JDatePanelImpl(finModel, p);
		dateDebPicker = new JDatePickerImpl(dateDebPan, new DateLabelFormatter());
		dateFinPicker = new JDatePickerImpl(dateFinPan, new DateLabelFormatter());
		dateDebPanel.setBorder(BorderFactory.createTitledBorder(text1));
		dateFinPanel.setBorder(BorderFactory.createTitledBorder(text2));
		dateDebPanel.setPreferredSize(new Dimension(330, 60));
		dateFinPanel.setPreferredSize(new Dimension(330, 60));
		dateDebPanel.add(dateDebPicker);
		dateFinPanel.add(dateFinPicker);
		add(dateDebPanel);
		add(dateFinPanel);
		error.setForeground(Color.red);		
		errorPan.add(error);	
		error.setVisible(false);		
		errorPan.setPreferredSize(new Dimension(400,20));	
		add(errorPan);
		annuler = new JButton("Annuler");
		choix.setLayout(new FlowLayout());
		choix.add(annuler);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bool = false;
				setVisible(false);
			}
		});
		ok = new JButton("OK");
		choix.add(ok);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				debut= (Date) dateDebPicker.getModel().getValue();
				fin=(Date) dateFinPicker.getModel().getValue();
				if (debut.after(fin))
				{
					error.setVisible(true);
					revalidate();
				}
				else
				{
				bool = true;
				setVisible(false);
				}
			}
		});
		add(choix);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
