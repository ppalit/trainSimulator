import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class DisplayTrainTimeTable extends JFrame {// implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String header[] = {"slno", "station", "Arrival", "Departure","Day"};
	private Object data[][] = new Object[50][50];
	private JTable table = new JTable(data, header);
	
	
	
	// table.setFillsViewportHeight(true);
	private JScrollPane jsp = new JScrollPane(table,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public DisplayTrainTimeTable(int train_no) {
		// jsp.setSize(200,200);
		super("Train no:"+train_no);
	
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		//setTitle("GraphInterface");
		jsp.setWheelScrollingEnabled(true);
		TrainTimeEntity t_entity = new TrainTimeEntity();
		t_entity.setTrainNo(train_no);
		
		TrainTimeEntity tte[] = new TrainTimeEntity[200];
		for (int i = 0; i < 200; i++) {
			tte[i] = new TrainTimeEntity();
		}
		DisplayTrainTimeManager dtt = new DisplayTrainTimeManager();
		tte = dtt.display(t_entity);

		for (int i = 0; i < tte[0].getSize(); i++) {
			data[i][0] = i+1;
			data[i][1] = (tte[i].getStation()).trim();
			data[i][2] = (tte[i].getArrival()).trim();
			data[i][3] = (tte[i].getDeparture()).trim();
			data[i][4] = (tte[i].getDay_val()).trim();
			System.out.println("no of rows=" + i);
		}
		data[0][2]="--";
		data[tte[0].getSize()-1][3]="--";
		jsp.setViewportView(table);

		add(jsp);
		setSize(650,400);
		setVisible(true);
	}

}