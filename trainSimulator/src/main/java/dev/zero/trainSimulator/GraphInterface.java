package dev.zero.trainSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import dev.zero.trainSimulator.view.DisplayAddRoute;
import dev.zero.trainSimulator.view.DisplayAddStation;
import dev.zero.trainSimulator.view.DisplayAddTrain;
import dev.zero.trainSimulator.view.DisplayDeleteTrain;
import dev.zero.trainSimulator.view.DisplayShowTrain;
import dev.zero.trainSimulator.view.DisplayStationTimePanel;
import dev.zero.trainSimulator.view.DisplayTablePane;

public class GraphInterface extends JFrame implements ActionListener {// ,ItemListener
																		// {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scroller;
	private GraphPanel pnl;
	private JTabbedPane tabpan;

	private JPanel jPanel1, jPanel3, jPanel4, jPanel5, jPanel6, jPanel7, jPanel8, jPanel9;
	int usertype = 0;
	private JPanel refresh_panel = new JPanel();
	private JPanel logout_panel = new JPanel();
	// private JPanel radio_panel =new JPanel();

	private Rule columnView;
	private Rule rowView;
	private JButton refresh = new JButton("Refresh");
	private JButton logout = new JButton("Logout");

	GraphInterface(int user) {
		usertype = user;

		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");

		} catch (Exception e) {
		}

		pnl = new GraphPanel();
		scroller = new JScrollPane(pnl);

		tabpan = new javax.swing.JTabbedPane();

		jPanel1 = new DisplayAddStation(pnl);

		jPanel3 = new DisplayAddRoute(pnl);
		jPanel4 = new MoveStation(pnl);
		jPanel5 = new SimulateTrain(pnl);
		jPanel6 = new DisplayAddTrain();
		jPanel7 = new DisplayShowTrain();

		jPanel8 = new DisplayStationTimePanel(pnl);
		jPanel9 = new DisplayDeleteTrain();
		// System.out.println("_____________________________________station
		// name______"+pnl.station_name);
		columnView = new Rule(Rule.HORIZONTAL, false);
		rowView = new Rule(Rule.VERTICAL, false);
		columnView.setVisible(true);

		scroller.setColumnHeaderView(columnView);
		scroller.setRowHeaderView(rowView);
		rowView.setPreferredHeight(1000);
		// rowView.setPreferredWidth(30);
		columnView.setPreferredWidth(1000);
		// columnView.setPreferredHeight(30);
		System.out.println("-------------Row header==" + rowView);
		// scroller.setRowHeader(scroller.setRowHeaderView(rowView));

		getContentPane().setLayout(null);
		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("GraphInterface");
		setForeground(new java.awt.Color(0, 102, 204));
		setName("GraphInterface");
		scroller.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 3));
		scroller.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setToolTipText("Railway Map");
		scroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pnl.setLayout(null);

		pnl.setPreferredSize(new java.awt.Dimension(900, 800));
		scroller.setViewportView(pnl);

		getContentPane().add(scroller);
		scroller.setBounds(20, 10, 720, 330);

		DisplayTablePane obj = new DisplayTablePane();
		// tabpan1.addTab("Routes ", new DisplayRouteTable());
		// tabpan1.addTab("demo ", new DisplayRouteTable());]
		// add(obj);

		getContentPane().add(obj);
		obj.setBounds(20, 350, 640, 110);
		refresh_panel.add(refresh);
		getContentPane().add(refresh_panel);
		refresh.addActionListener(this);
		refresh_panel.setBounds(100, 350, 1210, 70);

		logout_panel.add(logout);
		getContentPane().add(logout_panel);
		logout.addActionListener(this);
		logout_panel.setBounds(100, 390, 1210, 70);


		tabpan.addTab("Add New Station", null, jPanel1, "click to add new station");

		// jPanel3.setLayout(null);

		tabpan.addTab("Add New Route", null, jPanel3, "click to add new route");

		// jPanel4.setLayout(null);

		tabpan.addTab("Move Station", null, jPanel4, "click to move stations on map");

		// jPanel5.setLayout(null);

		tabpan.addTab("Simulate", null, jPanel5, "click to simulate trains");

		tabpan.addTab("Add New  Train", null, jPanel6, "click to add new trains");

		tabpan.addTab("show Train Time Table", null, jPanel7, "click to show time tables of trains");

		tabpan.addTab("show Station Time Table", null, jPanel8, "click to show time tables of stations ");

		tabpan.addTab("Delete train", null, jPanel9, "click to delete train");

		getContentPane().add(tabpan);
		tabpan.setBounds(20, 470, 720, 170);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 764) / 2, (screenSize.height - 697) / 2, 764, 697);
		setVisible(true);

		if (user == 2) {
			tabpan.setEnabledAt(0, false);
			tabpan.setEnabledAt(1, false);
			tabpan.setEnabledAt(4, false);
			tabpan.setEnabledAt(7, false);
			// tabpan.setEnabledAt(2,false);
			// tabpan.setEnabledAt(2,false);
		}

		// jProgressBar1.setIndeterminate(false);
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refresh) {
			this.setVisible(false);
			this.dispose();
			GraphInterface gi = new GraphInterface(usertype);
		}

		if (e.getSource() == logout) {
			this.setVisible(false);
			this.dispose();
			Logon start = new Logon();
		}

	}

}
