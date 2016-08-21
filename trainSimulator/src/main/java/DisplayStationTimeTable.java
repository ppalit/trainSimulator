import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class DisplayStationTimeTable extends JFrame {// implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String header[] = {"slno", "Train", "Arrival", "Departure","Day"};
	private Object data[][] = new Object[50][50];
	private JTable table = new JTable(data, header);
	
	
	
	// table.setFillsViewportHeight(true);
	private JScrollPane jsp = new JScrollPane(table,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public DisplayStationTimeTable(String station) {
		// jsp.setSize(200,200);
//	System.out.println("****************************"+station);
		super("TStation Name:"+station);
	
		jsp.setWheelScrollingEnabled(true);
		TrainTimeEntity t_entity = new TrainTimeEntity();
		t_entity.setStation(station);
		
		TrainTimeEntity tte[] = new TrainTimeEntity[200];
		for (int i = 0; i < 200; i++) {
			tte[i] = new TrainTimeEntity();
		}
		DisplayStationTimeManager dtt = new DisplayStationTimeManager();
		tte = dtt.display(t_entity);
		System.out.println("Size received________________________________"+tte[0].getSize()+1);
		
		/*for (int i = 0; i < tte[0].getSize(); i++) {
		System.out.println("***************train no"+tte[i].getTrainNo());
		System.out.println("***************arrival"+tte[i].getArrival());
		System.out.println("***************departure"+tte[i].getDeparture());
		System.out.println("***************date   "+tte[i].getDay_val());
		}*/
		
		
		int j=0;
		for (int i = 0; i < tte[0].getSize()+1;i++) {
		if(tte[i].getTrainNo()>0)
		{
			data[j][0] = tte[i].getTrainNo();
			data[j][1] = (tte[i].getTrain()).trim();
			
			data[j][2] = (tte[i].getArrival()).trim();
			
			data[j][3] = (tte[i].getDeparture()).trim();
			
			data[j][4] = (tte[i].getDay_val()).trim();
			j++;
		}	
		System.out.println("no of rows=" + j);
		}
	//	data[0][2]="--";
		//data[tte[0].getSize()-1][3]="--";
		jsp.setViewportView(table);

		add(jsp);
		setSize(400,400);
		setVisible(true);
	}

}