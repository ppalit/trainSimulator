package dev.zero.trainSimulator.view;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import dev.zero.trainSimulator.model.RouteEntity;

public class DisplayRouteTable extends JScrollPane {// implements ActionListener
													// {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String header[] = {"Route Number", "Source", "Via","destination"};
	private Object data[][] = new Object[50][50];
	private JTable table = new JTable(data, header);
	// table.setFillsViewportHeight(true);
	private JScrollPane jsp = new JScrollPane(table,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public DisplayRouteTable() {
		// jsp.setSize(200,200);

		this.setWheelScrollingEnabled(true);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// table.setSize(10,10, 100,100);
		// table.setRowHeight(20, 5);
		RouteEntity re[] = new RouteEntity[200];
		for (int i = 0; i < 200; i++) {
			re[i] = new RouteEntity();
		}
		DisplayRouteManager drm = new DisplayRouteManager();
		re = drm.insertSM();

		System.out.println("size of the route in GraphInterface is"
				+ re[0].getRoute_size());

		for (int i = 0; i < re[0].getRoute_size(); i++) {
			// System.out.println("inside table
			// loop**************************");
			data[i][0] = re[i].getRoute_number();
			data[i][1] = re[i].getSource_station();
			data[i][2] = re[i].getVia_1_station();
			//data[i][3] = "";
			data[i][3] = re[i].getDest_station();
			System.out.println("no of rows=" + i);
		}
		this.setViewportView(table);

		// add(jsp);
	}

}