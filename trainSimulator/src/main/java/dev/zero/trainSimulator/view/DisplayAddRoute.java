package dev.zero.trainSimulator.view;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dev.zero.trainSimulator.GraphPanel;
import dev.zero.trainSimulator.dao.AddRouteManager;
import dev.zero.trainSimulator.dao.ValidateAddRouteManager;
import dev.zero.trainSimulator.model.Node;
import dev.zero.trainSimulator.model.RouteEntity;

public class DisplayAddRoute extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel pnl1 = new JPanel();
	private JPanel pnl2 = new JPanel();
	private JPanel pnl3 = new JPanel();

	private JButton stop = new JButton("Finish");
	private JButton submit = new JButton("submit");

	private JTextField trainNo = new JTextField(10);
	private JLabel trainNoLbl = new JLabel("Enter the Route No.");
	private JLabel sorcestationNameLbl = new JLabel("Enter the Source Station");
	private JTextField sourcestationName = new JTextField(10);
	GraphPanel panel;// = new GraphPanel(); // created an object of which is
	// the graph

	public DisplayAddRoute(GraphPanel pnl) {
		setLayout(new FlowLayout());

		panel = pnl;

		pnl1.add(trainNoLbl);
		pnl1.add(trainNo);
		add(pnl1);

		pnl2.add(sorcestationNameLbl);
		pnl2.add(sourcestationName);
		add(pnl2);

		pnl3.add(submit);
		submit.addActionListener(this);
		pnl3.add(stop);
		stop.addActionListener(this);
		add(pnl3);

		stop.setEnabled(true);
		// setSize(250,400);

		setVisible(true);
		stop.setEnabled(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {

		
	try{
		
		if(trainNo.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please fill Route No", "Error", JOptionPane.ERROR_MESSAGE); 
		}
		else if(Integer.parseInt(trainNo.getText())>99999 || Integer.parseInt(trainNo.getText())<10000)
		{
				JOptionPane.showMessageDialog(null, "Route Number should be of 5 digit:: "+trainNo.getText(), "Error", JOptionPane.ERROR_MESSAGE); 
		}
		else if(sourcestationName.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please fill source station Name", "Error", JOptionPane.ERROR_MESSAGE); 
		}
		else{
			stop.setEnabled(false);
			ValidateAddRouteManager varm= new ValidateAddRouteManager();
		int flag= varm.ValidateAddRouteManagerFunc(sourcestationName.getText(), Integer.parseInt(trainNo.getText()));
		if(flag==0)
		{
			int nodeNoSrc = panel.findNode(sourcestationName.getText());
			Node n = panel.nodes[nodeNoSrc];
			n.selected = true;

			n.mode = 4;

			panel.findNextNode(n.lbl);

			stop.setEnabled(true);
			submit.setEnabled(false);
			
			}
		else
			submit.setEnabled(true);
			}
			}
				catch (Exception ee) {
				System.out.println("Error" + ee);
				JOptionPane.showMessageDialog(null, "Error in Input data format!", "Error", JOptionPane.ERROR_MESSAGE); 
			}
		}
		if (e.getSource() == stop) {
			System.out.println("the route for " + trainNo.getText() + " is.");
			// System.out.println("the route for is.");
			RouteEntity re = new RouteEntity();
			re.setRoute_number(Integer.parseInt(trainNo.getText()));
			String arr[];
			arr = new String[200];
			int counter = 0;
			for (int i = 0, j = 0; i < panel.counterNodeSelection; i++, j++) {

				System.out.println(panel.nodes[panel.nodesSelected[i]].lbl);
				arr[j] = panel.nodes[panel.nodesSelected[i]].lbl;
				counter++;
			}
			re.setRoute_stations(arr, counter);
			re.setRoute_size(counter);
			try {
				new AddRouteManager(re);
			} catch (Exception ee) {
				System.out.println("Error" + ee);
			}
			for (int j = 0; j < counter; j++) {
				System.out.println("the value stored in array is :::" + arr[j]
						+ "");
			}

			submit.setEnabled(true);
			for (int i = 0; i < panel.nnodes; i++) {
				Node n1 = panel.nodes[i];
				n1.selected = false;
				n1.mode = 1;

			}
		sourcestationName.setText("");
		trainNo.setText("");
		
		
		}
	}

}