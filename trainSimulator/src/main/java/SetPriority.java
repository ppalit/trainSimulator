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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



	public class SetPriority extends javax.swing.JFrame implements ActionListener {//,ItemListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		int priority=0;
		private ButtonGroup buttonGroup1;
		private JLabel hourLbl;
		private JComboBox jComboBox1;
		private JComboBox jComboBox2;
		private JLabel jLabel3;

		private JPanel jPanel1;

		private JPanel jPanel2;
		private JPanel jPanel3;
		private JLabel minLbl;
		private JButton nextBut;
		private JRadioButton opt1;
		private JRadioButton opt2;
		private JRadioButton opt3;
		private JRadioButton opt4;
		private ButtonGroup group;
		private int optionSelected;
		
		private int hr=0;
		private int minuite=0;
		private boolean randomTime=true;// End of variables declaration
		//public DisplayAddTrain dis ;//= new DisplayAddTrain(1);
		//Display

		TrainEntity te1;

		public SetPriority(TrainEntity te) {
			
			te1= new TrainEntity();

			te1=te;
			buttonGroup1 = new javax.swing.ButtonGroup();
			jPanel1 = new javax.swing.JPanel();
			opt1 = new javax.swing.JRadioButton();
			opt2 = new javax.swing.JRadioButton();
			opt3 = new javax.swing.JRadioButton();
			opt4 = new javax.swing.JRadioButton();
			jLabel3 = new javax.swing.JLabel();
			jPanel2 = new javax.swing.JPanel();
			hourLbl = new javax.swing.JLabel();
			minLbl = new javax.swing.JLabel();
			jComboBox1 = new javax.swing.JComboBox();
			jComboBox2 = new javax.swing.JComboBox();
			jPanel3 = new javax.swing.JPanel();
			nextBut = new javax.swing.JButton();
			group = new ButtonGroup();

			group.add(opt1);
			group.add(opt2);
			group.add(opt3);
			group.add(opt4);

			
			 
			getContentPane().setLayout(null);

			for (int i = 0; i < 12; i++) {
				jComboBox1.addItem("" + i);
			}
			for (int i = 0; i < 60; i++) {
				jComboBox2.addItem("" + i);
			}

			setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
			setTitle("Scheding Options");
			setAlwaysOnTop(true);
			setLocationByPlatform(true);
			setName("ScheduleOption");
			jPanel1.setLayout(null);

			opt1.setText("Schedule Train Without Disturbing Existing TimeTable");
			opt1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
			opt1.setMargin(new java.awt.Insets(0, 0, 0, 0));
			opt1.setSelected(true);
			jPanel1.add(opt1);
			opt1.setBounds(20, 90, 320, 15);

			opt2.setText("Schedule Train With Highest Priority");
			opt2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
			opt2.setMargin(new java.awt.Insets(0, 0, 0, 0));
			jPanel1.add(opt2);
			opt2.setBounds(20, 120, 290, 15);

			opt3.setText("Schedule With Nornal Priority of Train");
			opt3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
			opt3.setMargin(new java.awt.Insets(0, 0, 0, 0));
			jPanel1.add(opt3);
			opt3.setBounds(20, 150, 270, 15);

			opt4.setText("Schedule With Custom Time");
			opt4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
			opt4.setMargin(new java.awt.Insets(0, 0, 0, 0));
			jPanel1.add(opt4);
			opt4.setBounds(20, 180, 200, 15);

			jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel3.setText("Scheduling Options");
			jPanel1.add(jLabel3);
			jLabel3.setBounds(114, 20, 280, 30);

			getContentPane().add(jPanel1);
			jPanel1.setBounds(0, 0, 490, 210);

			jPanel2.setLayout(null);

			hourLbl.setText("Hours");
			jPanel2.add(hourLbl);
			hourLbl.setBounds(50, 40, 28, 14);

			minLbl.setText("Min");
			jPanel2.add(minLbl);
			minLbl.setBounds(150, 40, 34, 14);

			// jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]
			// { "Item 1", "Item 2", "Item 3", "Item 4" }));
			jPanel2.add(jComboBox1);
			jComboBox1.setBounds(80, 40, 57, 22);

			// jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[]
			// { "Item 1", "Item 2", "Item 3", "Item 4" }));
			jPanel2.add(jComboBox2);
			jComboBox2.setBounds(170, 40, 57, 22);

			getContentPane().add(jPanel2);
			jPanel2.setBounds(20, 230, 460, 100);

			jPanel3.setLayout(null);

			nextBut.setText("Next >");
			nextBut.addActionListener(this);
			jPanel3.add(nextBut);
			nextBut.setBounds(30, 3, 100, 30);

			getContentPane().add(jPanel3);
			jPanel3.setBounds(220, 350, 260, 40);

			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
					.getScreenSize();
			setBounds((screenSize.width - 506) / 2, (screenSize.height - 430) / 2,
					506, 430);
			this.setVisible(false);
		}
		public void actionPerformed(ActionEvent evt) {

		
			if (evt.getSource() == nextBut)
			{
			
			
			
			if(opt1.isSelected()==true)
			{	this.dispose();
				//updateDisp();
				priority = 1;
				te1.priority = this.priority;
				te1.randomTime = this.randomTime;
				te1.hr = this.hr;
				te1.min = this.minuite;
				System.out.println(" in action performed "+ priority);
			}else if(opt2.isSelected()==true)
			{	this.dispose();
				//updateDisp();
				priority = 9;
				te1.priority = this.priority;
				te1.randomTime = this.randomTime;
				te1.hr = this.hr;
				te1.min = this.minuite;
				System.out.println(" in action performed "+ priority);
			}	else if(opt3.isSelected()==true)
			{	this.dispose();
				//updateDisp();
				if (te1.getTrain_speed() == "superfast") {
					priority = 5;
				} else if (te1.getTrain_speed() == "express") {
					priority = 3;
				} else if (te1.getTrain_speed() == "passenger") {
					priority = 1;
				} else if (te1.getTrain_speed() == "Rajdhani") {
					priority = 7;
				} else {
					priority = 7;
						}
						
				te1.priority = this.priority;
				te1.randomTime = this.randomTime;
				te1.hr = this.hr;
				te1.min = this.minuite;
				System.out.println(" in action performed "+ priority);
			}
			else if(opt4.isSelected()==true)
			{	this.dispose();
				//updateDisp();
				priority = 9;
				hr = jComboBox1.getSelectedIndex();
				minuite = jComboBox2.getSelectedIndex();
				randomTime  = false;
				te1.priority = this.priority;
				te1.randomTime = this.randomTime;
				te1.hr = this.hr;
				te1.min = this.minuite;
				System.out.println(" in action performed "+ priority);
			}else
			{	this.dispose();
				//updateDisp();
				priority = 1;
				te1.priority = this.priority;
				te1.randomTime = this.randomTime;
				te1.hr = this.hr;
				te1.min = this.minuite;
				System.out.println(" in action performed "+ priority);
			}
					
			
				try {
					new AddTrainManager(te1);

				} catch (Exception ee) {
					System.out.println("Error" + ee);
				}
			}
			}
		/*public void itemStateChanged(ItemEvent evt) {
		 if (evt.getSource() == opt1){
				optionSelected= 1;
			}
			else if (evt.getSource() == opt2){
				optionSelected= 2;
			}
			else if (evt.getSource() == opt3){
				optionSelected= 3;
			}
			else if (evt.getSource() == opt4){
				optionSelected= 4;
				hr = jComboBox2.getSelectedIndex();
				minuite = jComboBox2.getSelectedIndex();
				randomTime  = false;
			}
			
		//	DisplayStationTimeTable obj=new DisplayStationTimeTable(station_Name);
	//JOptionPane.showMessageDialog(null, "Station Name is.................."+station_Name, "alert", JOptionPane.ERROR_MESSAGE); 
		
		}
		*/
		
		/**
		 * @return the hr
		 */
		public int getHr() {
			return hr;
		}
		/**
		 * @return the minuite
		 */
		public int getMinuite() {
			return minuite;
		}
		public int getOptionSelected() {
			return optionSelected;
		}

		/**
		 * @param hr
		 *            the hr to set
		 */
		public void setHr(int hr) {
			this.hr = hr;
		}
		/**
		 * @param minuite
		 *            the minuite to set
		 */
		public void setMinuite(int minuite) {
			this.minuite = minuite;
		}
		/**
		 * @param optionSelected
		 *            the optionSelected to set
		 */
	/*	public void setOptionSelected(int optionSelected) {
			this.optionSelected = optionSelected;
		}
	
	
	public void updateDisp()
	{
	
	System.out.println("switch case***************************************loves****"+this.optionSelected);
	switch (this.optionSelected) {
			case 1 :
				priority = 1;
				break;
			case 2 :
				priority = 9;
				break;
			case 3 :
				if (te1.getTrain_speed() == "superfast") {
					priority = 5;
				} else if (te1.getTrain_speed() == "express") {
					priority = 3;
				} else if (te1.getTrain_speed() == "passenger") {
					priority = 1;
				} else if (te1.getTrain_speed() == "Rajdhani") {
					priority = 7;
				} else {
					priority = 7;
				}
			case 4 :
				priority = 9;
				break;
			default :
				priority = 1;

		}
		}    */
	
	
	
	}
	
	
