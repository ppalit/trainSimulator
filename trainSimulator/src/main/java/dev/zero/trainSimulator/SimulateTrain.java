package dev.zero.trainSimulator;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dev.zero.trainSimulator.model.TimeCal;

public class SimulateTrain extends JPanel implements ItemListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnl1 = new JPanel();
	private GraphPanel panel;
	
	private Checkbox showTrainName = new Checkbox("Check to see Train Names");
	private Checkbox showTrainNo = new Checkbox("Check to see Train Numbers");
	private JButton start= new JButton("START");
	//private JButton stop = new JButton("STOP");
	private JComboBox day = new JComboBox();
	private JComboBox hour = new JComboBox();
	private JComboBox min = new JComboBox();
	
	SimulateTrain(GraphPanel pnl) {

		setLayout(new FlowLayout());
		panel = pnl;
	
		pnl1.add(showTrainName);
		pnl1.add(showTrainNo);
		pnl1.add(day);
		pnl1.add(hour);
		pnl1.add(min);
		pnl1.add(start);
		//pnl1.add(stop);
		
		day.setEditable(false);
		day.addItem("Monday");
		day.addItem("Tuesday");
		day.addItem("Wednesday");
		day.addItem("Thursday");
		day.addItem("Friday");
		day.addItem("Saturday");
		day.addItem("Sunday");
		
		for(int i=0; i<12;i++){
			hour.addItem(""+i);
		}
		for(int i=0; i<60;i++){
			min.addItem(""+i);
		}
		add(pnl1);

		showTrainNo.addItemListener(this);
		showTrainName.addItemListener(this);
		start.addActionListener(this);
	//	stop.addActionListener(this);
		
		setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		boolean on = e.getStateChange() == ItemEvent.SELECTED;
		
		if ((src == showTrainName) && (on == true)) {
			panel.showTrainName = true;
		}
		if ((src == showTrainNo) && (on == true)){
			panel.showTrainNo = true;
		}

	}
	public void actionPerformed(ActionEvent evnt){
		int dy= day.getSelectedIndex();
		int hr= hour.getSelectedIndex();
		int minuite= min.getSelectedIndex();
		if(evnt.getSource() == start){
			panel.tc1 = new TimeCal(hr,minuite,0);
			panel.day = dy;
			panel.traceTrain();
			//panel.timer.start();
		}
	}

}