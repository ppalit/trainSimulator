package dev.zero.trainSimulator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.springframework.stereotype.Component;
/**
 * This class is the start of the project.
 * It allows the user to logon to the system in appropriate privilege mode.
 * The main function is called from this class.
 * 
 */

/**
 * @author Priyabrata & Siddhartha
 * 
 */
@Component
public class Logon extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Logon start = new Logon();
	//start.iterate();
		

	}

	
	
	
	/*
	 JProgressBar current;
    JTextArea out;
    JButton find;
    Thread runner;
    int num = 0;
	JPanel pane = new JPanel();
	
	*/
	
	 final static int interval = 1000;
  int i;
  JLabel label;
    JProgressBar pb;
    Timer timer;
    JButton button;
	
	
	
	
	
	
	private int fl = 1;
	private JPanel pan = new JPanel();
	private JLabel lbUser, lbPass;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnOk, btnCancel;
	private JRadioButton rad, lbr;
		String arr_user[]= new String[40];
		String arr_pass[]= new String[40];

	boolean verify;

	ProgressBar progress;
	public Logon() { // constructor
		super("Train Scheduling"); // sets the frame name
		//setIconImage(getToolkit().getImage("Images/Home.gif")); // class JFrame
		setSize(275, 300);
		setResizable(false); // fixing the size of the window
		progress= new ProgressBar();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		}); // can also be done using a separate class. addWindowListener(new
		// classname());

		// Setting the Logon Form Position on User's Screen.
		setLocation(380, 250);

		// Setting the Layout of Panel.
		pan.setLayout(null);

		// Setting the Form's Radio Button's
		rad = new JRadioButton("Admin");
		rad.setBounds(80, 140, 100, 25);
		rad.addActionListener(this);

		lbr = new JRadioButton("user");
		lbr.setBounds(80, 170, 100, 25);
		lbr.addActionListener(this);


		rad.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rad);
		bg.add(lbr);
			

		// Setting the Form's Labels.
		lbUser = new JLabel("Username:");
		lbUser.setForeground(Color.black);
		lbUser.setBounds(20, 15, 75, 25);
		lbPass = new JLabel("Password:");
		lbPass.setForeground(Color.BLACK);
		lbPass.setBounds(20, 50, 75, 25);

		// Setting the Form's TextField & PasswordField.

		txtUser = new JTextField();
		txtUser.setBounds(100, 15, 150, 25);
		txtPass = new JPasswordField();
		txtPass.setBounds(100, 50, 150, 25);

		// Setting the Form's Buttons.

		btnOk = new JButton("OK");
		btnOk.setBounds(20, 90, 100, 25);
		btnOk.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(150, 90, 100, 25);
		btnCancel.addActionListener(this);

		// Adding All the Controls in Panel.
		pan.add(rad);
		pan.add(lbr);

		pan.add(lbUser);
		pan.add(lbPass);
		pan.add(txtUser);
		pan.add(txtPass);
		pan.add(btnOk);
		pan.add(btnCancel);

	/*	current = new JProgressBar(0, 2000);
		current.setBounds(100, 200, 150, 25);
		current.setValue(0);
		current.setStringPainted(true); 
		
		pan.add(current); */
		// Adding Panel to the Form.
		
		//pb = new JProgressBar(0, 20);
		//pb.setBounds(100, 200, 150, 25);
       // pb.setValue(0);
       // pb.setStringPainted(true);
		//pan.add(pb);
		getContentPane().add(pan);
		/*
		timer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        if (i == 20){
          Toolkit.getDefaultToolkit().beep();
          System.out.println("here^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		  timer.stop();
         // button.setEnabled(true);
          pb.setValue(0);
        //  String str = "<html>" + "<font color=\"#FF0000\">" + "<b>" + Downloading completed." + "</b>" + "</font>" + "</html>";
         // label.setText(str);
        }
        i = i + 1;
                pb.setValue(i);
            }
        }); */
		
		//setContentPane(pane);
		// Opening the Database.
		setVisible(true);
	}
	
	
	
	/* public void iterate() {
        while (num < 2000) {
            current.setValue(num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            num += 95;
        }
    }
  */
  
	public int testConnection(int type) //throws SQLException
	{
		String dbURL="jdbc:sqlite:/Users/priyabrata/work/traindb";	
		Connection con=null;
		Statement st=null;
		ResultSet rsDetails=null;
		String query;
		boolean flag=false;
		String table="";
		if(type==1)
			{
				table="account1";
			}
		else
			{
				table="account";
			}
	try
		{
		//org.sqlite.JDBC
		 Class.forName("org.sqlite.JDBC").newInstance();
			System.out.println("Driver present");
		}
		catch(Exception e)
		{
		 System.out.println("Can't load driver:");
		}
	try
		{
			con=DriverManager.getConnection(dbURL,"","");
			System.out.println("Connected to database  successfully");
			st=con.createStatement();
			query = "select * from "+table;
			rsDetails = st.executeQuery(query);
			
			int counter=0;
			while(rsDetails.next())
			{
			arr_user[counter]=rsDetails.getString("user_id");//System.out.println(us+us.length());System.out.println(user.getText());
			arr_pass[counter]=rsDetails.getString("password");//System.out.println(ps+ps.length());System.out.println(password.getText());
			counter++;
			}
			st.close();
	 		con.close();
			for(int k=0;k<counter;k++)
			{
			if(txtUser.getText().equals(arr_user[k]) && txtPass.getText().equals(arr_pass[k]))
			return 1;
			}
			return 0;
			
		}
	catch(Exception e)
		{
		 System.out.println("Errors in reading ");
		 return 0;
		}
	
	}
  
  
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj == btnOk) { // If OK Button Pressed.
			String password = new String(txtPass.getPassword());
			if (txtUser.getText().equals("")) { // first nested if
				JOptionPane.showMessageDialog(this,
						"Provide Username to Logon.");
				txtUser.requestFocus();
			} else if (password.equals("")) {
				txtPass.requestFocus();
				JOptionPane.showMessageDialog(null,
						"Provide Password to Logon.");
			} else {
				// String pass; // To Hold the Password.
				verify = false; // To Confirm Logon.

				if (rad.isSelected()==true) {
					/*
					 * AdministratorEntity aet = new AdministratorEntity(); aet =
					 * lm.getData7(txtUser.getText()); user = aet.getuser();
					 * //Storing UserName. pass = aet.getpassword(); //Storing
					 * Password.
					 */
					int val=testConnection(1);
					if (val==1) {
						verify = true;

						
						
						// i = 0;
			         //    timer.start();
					//	progress.show();
						
					
					/*	ProgressMonitor pm= new ProgressMonitor(Logon.this,
									"Logging on",
			                       "hello",
			                       0,
			                       1000);
						pm.setProgress(0);
						
					*/ 
					/*	progress.status(true);
						PopupFactory factory = PopupFactory.getSharedInstance();
						Popup popup = factory.getPopup(new JFrame(),progress, 500, 300);
						popup.show();
					*/	   
						  

    						new GraphInterface(1);// Show Main Form.
						// JOptionPane.showMessageDialog(this,"Correct
						// Information Provided");
						//	pm.close();
    					//	progress.status(false);
    				//		popup.hide();
						//progress.hide();
						
						setVisible(false); // Hide the Form.
						dispose(); // Free the System Resources.
						// }
					} else {
						verify = false;
						JOptionPane.showMessageDialog(this,
								"Incorrect Information Provided.");
						txtUser.setText("");
						txtPass.setText("");
						txtUser.requestFocus();
					}
				} else if (lbr.isSelected()==true) {
					/*
					 * LibrarianEntity le = new LibrarianEntity(); le =
					 * lm.getData8(txtUser.getText()); user = le.getusername();
					 * //Storing UserName. pass = le.getpass(); //Storing
					 * Password.
					 */
				int val=testConnection(2);
					if (val==1) {
						verify = true;

					/*	ProgressMonitor pm= new ProgressMonitor(new GraphInterface(2),
								"Logging on",
		                       "hello",
		                       0,
		                       1000);
						pm.setProgress(0);
						*/
						new GraphInterface(2);// Show Main Form.
						// JOptionPane.showMessageDialog(this,"Correct
						// Information Provided");
						setVisible(false); // Hide the Form.
						dispose(); // Free the System Resources.
					} else {
						verify = false;
						JOptionPane.showMessageDialog(this,
								"Incorrect Information Provided.");
						txtUser.setText("");
						txtPass.setText("");
						txtUser.requestFocus();
					}
				} 
			}
		} else if (obj == btnCancel) { // If Cancel Button Pressed Unload the
			// From.
			setVisible(false);
			dispose();
			System.exit(0);
		} 
	}

}
