import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.*;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GraphInterface extends JFrame implements ActionListener{//,ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scroller;
	private GraphPanel pnl;
	private JTabbedPane tabpan;

	private JPanel jPanel1, jPanel3, jPanel4, jPanel5, jPanel6,jPanel7,jPanel8,jPanel9 ;
	int usertype=0;
	private JPanel refresh_panel =new JPanel();
	private JPanel logout_panel =new JPanel();
	//private JPanel radio_panel =new JPanel();
	
	private Rule columnView;
	private Rule rowView;
	private JButton refresh = new JButton("Refresh");
	private JButton logout = new JButton("Logout");
	
	//private JRadioButton opt1 = new JRadioButton("met",false);
	//private JRadioButton opt2 = new JRadioButton();
	//private JRadioButton opt3 = new JRadioButton();
	//private JRadioButton opt4 = new JRadioButton();
	//private ButtonGroup group = new ButtonGroup();

	GraphInterface(int user) {
	usertype=user;
	/*
	JFrame frm = new JFrame("Scheduling");
    JProgressBar jProgressBar1 = new JProgressBar();
    JLabel jLabel1 = new javax.swing.JLabel();

    frm.getContentPane().setLayout(null);

    frm.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frm.add(jProgressBar1);
    jProgressBar1.setBounds(40, 80, 290, 16);

    jLabel1.setText("Please Wait while Sscheduling is done...");
    frm.add(jLabel1);
    jLabel1.setBounds(50, 40, 290, 14);

    java.awt.Dimension screenSize1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    frm.setBounds((screenSize1.width-395)/2, (screenSize1.height-143)/2, 395, 143);
    frm.setAlwaysOnTop(true);



frm.setVisible(true);
jProgressBar1.setIndeterminate(true);
*/
	 try {
	javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
	//javax.swing.UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
	
	// com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
	//.setLiquidDecorations(true);
	 //com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");

    } catch (Exception e) {}    
/*try {
	javax.swing.UIManager.setLookAndFeel("org.freeasinspeech.kdelaf.KdeLAF");
   } catch (Exception e) {}  */
   
		
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
	//	System.out.println("_____________________________________station name______"+pnl.station_name);
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
		scroller.setBorder(javax.swing.BorderFactory.createLineBorder(
				new java.awt.Color(102, 0, 102), 3));
		scroller
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setToolTipText("Railway Map");
		scroller
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
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
		
			//group.add(opt1);
			//group.add(opt2);
			//opt1.addItemListener(this);
			//opt2.addItemListener(this);
			//radio_panel.add(opt1);
			//radio_panel.add(opt2);
			//getContentPane().add(radio_panel);
			//radio_panel.setBounds(100, 430, 1210, 70);
			
			
		// jPanel1.setLayout(null);

		tabpan.addTab("Add New Station", null, jPanel1,"click to add new station");

		// jPanel3.setLayout(null);

		tabpan.addTab("Add New Route", null, jPanel3, "click to add new route");

		// jPanel4.setLayout(null);

		tabpan.addTab("Move Station", null, jPanel4,"click to move stations on map");

		// jPanel5.setLayout(null);

		tabpan.addTab("Simulate", null, jPanel5, "click to simulate trains");

		tabpan.addTab("Add New  Train", null, jPanel6, "click to add new trains");
		
		tabpan.addTab("show Train Time Table", null, jPanel7, "click to show time tables of trains");

		tabpan.addTab("show Station Time Table", null, jPanel8, "click to show time tables of stations ");
		
		tabpan.addTab("Delete train", null, jPanel9, "click to delete train");
		
		getContentPane().add(tabpan);
		tabpan.setBounds(20, 470, 720, 170);
	
		
		
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 764) / 2, (screenSize.height - 697) / 2,
				764, 697);
		setVisible(true);
		
		if(user==2)
		{
		tabpan.setEnabledAt(0,false);
		tabpan.setEnabledAt(1,false);
		tabpan.setEnabledAt(4,false);
		tabpan.setEnabledAt(7,false);
		//tabpan.setEnabledAt(2,false);
		//tabpan.setEnabledAt(2,false);
		}
		
		//jProgressBar1.setIndeterminate(false);
	}
	
	/*public void itemStateChanged(ItemEvent er) {
		Object src = er.getSource();
		boolean on = er.getStateChange() == ItemEvent.SELECTED;
		if (src == opt1) {
			if (on == true) {
			try{
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");} catch(Exception t){}
							}
						}
			if (src == opt2) {
			if (on == true) {
			try{
				javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");} catch(Exception t){}
							}
						}
		}*/
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refresh) {
		this.setVisible(false);
		this.dispose();
		GraphInterface gi= new GraphInterface(usertype);
		}
		
		if (e.getSource() == logout) {
			this.setVisible(false);
			this.dispose();
			Logon start = new Logon();
		}
		
	
	}   

}
