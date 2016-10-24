package dev.zero.trainSimulator.view;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dev.zero.trainSimulator.model.TrainEntity;

public class DisplayTrainTable extends JScrollPane {// implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String header[] = {"Train Number", "Train Name", "Speed Type",
			"Train route", "source", "destination"};
	private Object data[][] = new Object[50][50];
	private JTable table = new JTable(data, header);
	//private JScrollPane jsp = new JScrollPane(table);

	public DisplayTrainTable() {

		// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// table.setSize(10,10, 100,100);
		// table.setRowHeight(20, 5);
		TrainEntity te[] = new TrainEntity[200];
		for (int i = 0; i < 200; i++) {
			te[i] = new TrainEntity();
		}
		DisplayTrainManager dtm = new DisplayTrainManager();
		te = dtm.insertSM();

		// System.out.println("size of the route in GraphInterface
		// is"+re[0].getRoute_size());

		for (int i = 0; i < te[0].getNo_rows(); i++) {
			// System.out.println("inside table
			// loop**************************");
			data[i][0] = te[i].getTrain_no();
			data[i][1] = te[i].getTrain_name();
			data[i][2] = te[i].getTrain_speed();
		//	data[i][3] = te[i].getTrain_type();
			data[i][3] = te[i].getRoute_number();
			data[i][4] = te[i].getSource_station();
			data[i][5] = te[i].getDest_station();
			// data[i][3]=

			// System.out.println("no of rows="+i);
		}
		this.setViewportView(table);

		//add(jsp);
	}

}