import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class DisplayAddStation extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnl1 = new JPanel();
	private JPanel pnl2 = new JPanel();
	private JPanel pnl3 = new JPanel();
	private JPanel pnl4 = new JPanel();
	private JPanel pnl5 = new JPanel();
	private JPanel pnl6 = new JPanel();
	private JPanel pnl7 = new JPanel();

	private JPanel pnlstation1 = new JPanel();
	private JPanel pnlstation2 = new JPanel();
	private JPanel pnlstation3 = new JPanel();
	private JButton submit = new JButton("Add Station");
	private JButton abort = new JButton("Abort");

	private JTextField stationCode = new JTextField(10);
	private JLabel stationCodeLbl = new JLabel("Station Code");
	private JLabel stationNameLbl = new JLabel("Station Name");
	private JTextField stationName = new JTextField(10);
	private JLabel locXLbl = new JLabel("   Location X");
	private JTextField locX = new JTextField(10);
	private JLabel locYLbl = new JLabel("   Location Y");
	private JTextField locY = new JTextField(10);
	private JComboBox ComboBox1 = new JComboBox();
	private JLabel type = new JLabel("		Station Type");
	private JLabel noOfConnLbl = new JLabel("No of Connecting Stations");
	private JComboBox noOfConn = new JComboBox();
	private JLabel Station1 = new JLabel("Station 1");
	private JLabel Station2 = new JLabel("Station 2");
	private JLabel Station3 = new JLabel("Station 3");

	private JTextField ConnSt1 = new JTextField(15);
	private JTextField ConnSt2 = new JTextField(15);
	private JTextField ConnSt3 = new JTextField(15);
	// private JTextField ConnSt1= new JTextField(15);
	int flag=0;
	private String connst[] = new String[3];
	int x=0; int y=0;
	// private GraphPanel pnl = new GraphPanel(); // created an object of which
	// is the graph
	private GraphPanel panel;

	public DisplayAddStation(GraphPanel pnl) {
		// super (Title, Resizable, Closable, Maximizable, Iconifiable)
		// super ("Add New Station",false,true,false,true);

		panel = pnl;

		// setLayout(new FlowLayout());
		setLayout(new GridLayout(4, 3));
		pnl1.add(stationCodeLbl);
		pnl1.add(stationCode);
		add(pnl1);

		pnl2.add(stationNameLbl);
		pnl2.add(stationName);
		add(pnl2);

		pnl3.add(locXLbl);
		pnl3.add(locX);
		add(pnl3);

		pnl4.add(locYLbl);
		pnl4.add(locY);
		add(pnl4);

		pnl5.add(type);
		pnl5.add(ComboBox1);
		ComboBox1.setEditable(false);
		ComboBox1.addItem("Junction");
		ComboBox1.addItem("Halt");
		ComboBox1.addItem("Commercial stop");

		// ComboBox1.setModel(new DefaultComboBoxModel(new String[] {
		// "Junction", "Halt", "Commercial stop" }));

		add(pnl5);

		pnl6.add(noOfConnLbl);
		// noOfConn.setModel(new DefaultComboBoxModel(new String[] {"1", "2",
		// "3", "4" }));
		pnl6.add(noOfConn);
		noOfConn.setEditable(false);
		noOfConn.addItem("1");
		noOfConn.addItem("2");
		noOfConn.addItem("3");
		add(pnl6);

		pnlstation1.add(Station1);
		pnlstation1.add(ConnSt1);
		// pnlstation1.add(ConnSt[0]);
		add(pnlstation1);

		pnlstation2.add(Station2);
		pnlstation2.add(ConnSt2);
		// pnlstation1.add(ConnSt[1]);
		add(pnlstation2);

		pnlstation3.add(Station3);
		pnlstation3.add(ConnSt3);
		// pnlstation1.add(ConnSt[2]);
		add(pnlstation3);

		pnl7.add(submit);
		submit.addActionListener(this);
		pnl7.add(abort);
		abort.addActionListener(this);
		add(pnl7);

		// setSize(250,400);
		// setLocation(600,100);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == abort) {
			stationCode.setText("");
			stationName.setText("");
			locX.setText("");
			locY.setText("");
			ConnSt1.setText("");
			ConnSt2.setText("");
			ConnSt3.setText("");
		}
		if (e.getSource() == submit) {
		try{
		 x = Integer.parseInt(locX.getText());
		 //y = Integer.parseInt(locY.getText());
		}
		catch (Exception exp){ JOptionPane.showMessageDialog(null, "Error in staion x-coordianate Input data Format ", "alert", JOptionPane.ERROR_MESSAGE); 
					locX.grabFocus();//locY.grabFocus();
		}
		try{
		 
		 y = Integer.parseInt(locY.getText());
		}
		catch (Exception exp){ JOptionPane.showMessageDialog(null, "Error in staion y-coordianate Input data Format ", "alert", JOptionPane.ERROR_MESSAGE); 
				locY.grabFocus();
		}
		try{
		
		if(stationCode.getText().equals("") )
				{	
				JOptionPane.showMessageDialog(null, "Please fill Station Code Name" , "alert", JOptionPane.ERROR_MESSAGE); 
				flag=1;}
			else if(stationName.getText().equals("") )
				{	
				JOptionPane.showMessageDialog(null, "Please fill Station  Name" , "alert", JOptionPane.ERROR_MESSAGE); 
				flag=2;} 
			else if(locX.getText().equals("") || locY.getText().equals("") )
				{	
				JOptionPane.showMessageDialog(null, "Please fill Station  Coordinates" , "alert", JOptionPane.ERROR_MESSAGE); 
				flag=3;}
			else if(Integer.parseInt(locX.getText()) >999 || Integer.parseInt(locY.getText()) >999  ) 
				{
				
				JOptionPane.showMessageDialog(null, "Please fill  proper value of Station  Coordinates" , "alert", JOptionPane.ERROR_MESSAGE); 
				} 
			else
			{
		
		
			connst[0] = ConnSt1.getText();
			connst[1] = ConnSt2.getText();
			connst[2] = ConnSt3.getText();

			//int x = Integer.parseInt(locX.getText());
			//int y = Integer.parseInt(locY.getText());
			System.out.println(x + "," + y);
			//panel.addNode(stationName.getText(), x, y);
			//panel.repaint();
			int noc = 1 + noOfConn.getSelectedIndex();
			System.out.println(x + "" + y + "" + noc);

		/*	for (int i = 0; i < noc; i++) {
				// String from = dialog.showInputDialog("enter the name of
				// connecting station");
				panel.addEdge(connst[i], stationName.getText());

			} */

			//panel.repaint();

			StationEntity se = new StationEntity();
			se.setStation_code(stationCode.getText());
			se.setStation_name(stationName.getText());
			se.setXcod(Integer.parseInt(locX.getText()));
			se.setYcod(Integer.parseInt(locY.getText()));
			// int noc = 1 + noOfConn.getSelectedIndex();
			se.setStation_type(noc);
			if (noc == 1 ) {
				if(ConnSt1.getText().equals(""))
				{	JOptionPane.showMessageDialog(null, "Please fill the first connected station" , "alert", JOptionPane.ERROR_MESSAGE); 
				}
				se.setConn_station1(ConnSt1.getText());
			} else if (noc == 2) {
				if(ConnSt1.getText().equals("") || ConnSt2.getText().equals(""))
				{	JOptionPane.showMessageDialog(null, "Please fill the first and second connected station" , "alert", JOptionPane.ERROR_MESSAGE); 
				}
				se.setConn_station1(ConnSt1.getText());
				se.setConn_station2(ConnSt2.getText());
			} else if (noc == 3) {
				
				if(ConnSt1.getText().equals("") || ConnSt2.getText().equals("") || ConnSt3.getText().equals("") )
				{	JOptionPane.showMessageDialog(null, "connected station field is missing" , "alert", JOptionPane.ERROR_MESSAGE); 
				}
				se.setConn_station1(ConnSt1.getText());
				se.setConn_station2(ConnSt2.getText());
				se.setConn_station3(ConnSt3.getText());
			}
				
					
			
			new AddStationManager(se, noc);
			panel.repaint();
			}
			}
				catch (Exception ev) {
				//System.out.println("Error: input value"+ev);	
				/*switch(flag)

				{
				case 1:break;
			case 2:break;
			case 3:break;
			case 4: JOptionPane.showMessageDialog(null, "Error in staion coordianate Input data Format ", "alert", JOptionPane.ERROR_MESSAGE); 
					locX.grabFocus();locY.grabFocus();
					break;
					case 5:break;
					case 6:break;
					default: JOptionPane.showMessageDialog(null, "Error in Input data Format ", "alert", JOptionPane.ERROR_MESSAGE); break;
				} */
			
			
			
		
			
			}
			
			// asm.addStations(se);
		

	}

}
}
/*
 * public static void main(String args[]) { DisplayAddStation obj=new
 * DisplayAddStation();
 * obj.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE); }
 */
