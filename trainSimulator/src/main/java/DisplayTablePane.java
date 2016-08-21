import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
public class DisplayTablePane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabpan = new JTabbedPane(SwingConstants.LEFT,
			JTabbedPane.SCROLL_TAB_LAYOUT);
	// DisplayRouteTable route_obj=new DisplayRouteTable();

	public DisplayTablePane() {

		// tabpan.setBackgroundAt(0, GREEN );
		this.tabPlacement = SwingConstants.LEFT;
		this.addTab("Routes ", null, new DisplayRouteTable(),
				"Shows details of routes");
		this.addTab("Trains", null, new DisplayTrainTable(),
				"Shows details of trains");
		
		// tabpan.addTab("move Station", new MoveStation(pnl));
		// tabpan.addTab("simulate", new SimulateTrain(pnl));
		// tabpan.addTab("add Train", new DisplayAddTrain());
		// tabpan.addTab("table", panel2);
		// add(tabpan);

	}
}