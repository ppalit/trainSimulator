
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class DisplayShowTrain extends JPanel implements ActionListener{

	
	
	private JPanel pnl1 = new JPanel();
	private JPanel pnl2 = new JPanel();
	private JPanel pnl3 = new JPanel();
	
	private JLabel train_no_lbl = new JLabel("Enter Train No.");
	private JTextField trainNo = new JTextField(6);
	
	private JButton stop = new JButton("Reset");
	private JButton submit = new JButton("Show");
	int train_no=0;
	
	public DisplayShowTrain() {

		//setLayout(new GridLayout(3, 3));
		setLayout(new FlowLayout());
		pnl1.add(train_no_lbl);
		pnl1.add(trainNo);
		add(pnl1);

		pnl2.add(submit);
		
		submit.addActionListener(this);
		pnl2.add(stop);
		stop.addActionListener(this);
		add(pnl2);
		
		
		
		}
		
	
		
	public void actionPerformed(ActionEvent e) {
	try{
		if (e.getSource() == submit) {
		
				if(trainNo.getText().equals(""))
				{
				JOptionPane.showMessageDialog(null, "Please enter train no.", "alert", JOptionPane.ERROR_MESSAGE); 
				}else if(Integer.parseInt(trainNo.getText())>9999 )
				{
				JOptionPane.showMessageDialog(null, "Not a valid input", "alert", JOptionPane.ERROR_MESSAGE); 
				}
				else 
				    {
					train_no=Integer.parseInt(trainNo.getText());
					DisplayTrainTimeTable obj=new DisplayTrainTimeTable(train_no);
					}
			
		}
		}catch (Exception et)
				{
				JOptionPane.showMessageDialog(null, "Not a valid input", "alert", JOptionPane.ERROR_MESSAGE); 
				}     
		
		if (e.getSource() == stop) {
	
			trainNo.setText("");
			
		}
		

		}
	}

