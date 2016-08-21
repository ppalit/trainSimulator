//import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class DisplayAddTrain extends JPanel implements ActionListener {

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

	private JLabel train_no_lbl = new JLabel("Enter Train No.");
	private JLabel train_name_lbl = new JLabel("Enter Train Name");
	private JLabel train_speed_lbl = new JLabel("Select Train type");
	private JLabel train_type_lbl = new JLabel("Days of run");
	private JLabel selectroute_lbl = new JLabel("Enter Route No.");
	private JTextField trainNo = new JTextField(null, 6);
	private JTextField trainName = new JTextField(null, 10);
	private JTextField routeNo = new JTextField(null, 6);
	private JComboBox trainspeed_combobox = new JComboBox();
	// private JComboBox traintype_combobox = new JComboBox();

	private JCheckBox day0 = new JCheckBox("Sunday");
	private JCheckBox day1 = new JCheckBox("Monday");
	private JCheckBox day2 = new JCheckBox("Tuesday");
	private JCheckBox day3 = new JCheckBox("Wednesday");
	private JCheckBox day4 = new JCheckBox("Thursday");
	private JCheckBox day5 = new JCheckBox("Friday");
	private JCheckBox day6 = new JCheckBox("Saturday");

	private JButton stop = new JButton("Reset");
	private JButton submit = new JButton("submit");

	private int days_of_run[] = new int[7];
	private boolean days_of_run_bool[] = new boolean[7];
	ValidateAddTrainManager varm = new ValidateAddTrainManager();
	int optionSelected;
	int priority;
	public int hr;
	public int minuite;
	boolean randomTime;
	
	TrainEntity te;

	public DisplayAddTrain(int x){
		System.out.println(" in integer constructor of DisplayAdd Train");
	}
	public DisplayAddTrain() {

		optionSelected = 0;
		priority = 0;
		hr = 0;
		minuite = 0;
		randomTime = true;

		// setLayout(new GridLayout(3, 3));
		setLayout(new FlowLayout());
		pnl1.add(train_no_lbl);
		pnl1.add(trainNo);
		add(pnl1);

		pnl2.add(train_name_lbl);
		pnl2.add(trainName);
		add(pnl2);

		pnl3.add(train_speed_lbl);
		pnl3.add(trainspeed_combobox);
		trainspeed_combobox.setEditable(false);
		trainspeed_combobox.addItem("superfast");
		trainspeed_combobox.addItem("express");
		trainspeed_combobox.addItem("passenger");
		trainspeed_combobox.addItem("Rajdhani");
		trainspeed_combobox.addItem("janSatabdhi");
		add(pnl3);

		pnl4.add(train_type_lbl);
		pnl4.add(day0);
		pnl4.add(day1);
		pnl4.add(day2);
		pnl4.add(day3);
		pnl4.add(day4);
		pnl4.add(day5);
		pnl4.add(day6);
		// pnl4.add(traintype_combobox);
		// traintype_combobox.setEditable(false);
		// traintype_combobox.addItem("Weekly");
		// traintype_combobox.addItem("Quaterly");
		// traintype_combobox.addItem("Daily");
		// traintype_combobox.addItem("Rajdhani");
		// traintype_combobox.addItem("janSatabdhi");
		add(pnl4);

		pnl5.add(selectroute_lbl);
		pnl5.add(routeNo);
		add(pnl5);

		pnl6.add(submit);
		submit.addActionListener(this);
		pnl6.add(stop);

		stop.addActionListener(this);

		add(pnl6);
		
	

	

		
	
	}
		public void actionPerformed(ActionEvent e) {
		
				
		if (e.getSource() == submit) {
			try {

			if (trainNo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill Train No",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(trainNo.getText()) > 9999) {
				JOptionPane.showMessageDialog(null,
						"Train Number should below 5 digit:: "
								+ trainNo.getText(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(trainNo.getText()) % 2 != 0) {
				JOptionPane.showMessageDialog(null,
						" UP Train Number should be a even no", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (routeNo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill Route No",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			else if (trainName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill Train Name",
						"alert", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(routeNo.getText()) > 99999
					|| Integer.parseInt(routeNo.getText()) < 10000) {
				JOptionPane.showMessageDialog(null,
						"Route Number should be of 5 digit:: "
								+ routeNo.getText(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if ((varm.valid(Integer.parseInt(routeNo.getText()), Integer.parseInt(trainNo.getText()))) == 0)
			{
					te= new TrainEntity();

				te.setRoute_number(Integer.parseInt(routeNo.getText()));
				te.setTrain_no(Integer.parseInt(trainNo.getText()));
				te.setTrain_name(trainName.getText());
				String trainspeed = (String) trainspeed_combobox
						.getSelectedItem();
				te.setTrain_speed(trainspeed);
				//te.priority = this.priority;
				//te.randomTime = this.randomTime;
				//te.hr = this.hr;
				//te.min = this.minuite;
				days_of_run_bool[0] = day0.isSelected();
				days_of_run_bool[1] = day1.isSelected();
				days_of_run_bool[2] = day2.isSelected();
				days_of_run_bool[3] = day3.isSelected();
				days_of_run_bool[4] = day4.isSelected();
				days_of_run_bool[5] = day5.isSelected();
				days_of_run_bool[6] = day6.isSelected();

				for (int i = 0; i < 7; i++) {
					if (days_of_run_bool[i] == true) {
						days_of_run[i] = 1;
					} else {
						days_of_run[i] = 0;
					}

					System.out.println("value of i========================"
							+ days_of_run_bool[i]);
				}
				te.setDays_of_run(days_of_run);

				// String traintype = (String)
				// traintype_combobox.getSelectedItem();
				// te.setTrain_type(traintype);
				System.out.println("selected train speed"
						+ trainspeed_combobox.getSelectedItem());
				/*try {
					new AddTrainManager(te);

				} catch (Exception ee) {
					System.out.println("Error" + ee);
				}*/
			SetPriority sp = new SetPriority(te);
			sp.setVisible(true);
			}
			
		} catch (Exception eet) {
			System.out.println("Error" + eet);
			JOptionPane.showMessageDialog(null, "Error in Input data format",
					"alert", JOptionPane.ERROR_MESSAGE);
		}
		
		
			
			
		}
		if (e.getSource() == stop) {
			routeNo.setText("");
			trainNo.setText("");
			trainName.setText("");
		}

	}
		/*
		 * if (e.getSource() == schedule) { try{ SetTrainTime stt =new
		 * SetTrainTime(Integer.parseInt(trainNo.getText()));
		 * stt.setInitialTime(new TimeCal(11,00,0)); }catch (Exception ee) {
		 * System.out.println("Error" + ee); } }
		 */

	

}