public class TrainEntity {

	int route_number;
	int size;
	String train_name;
	String train_speed;
	int train_speed_no;
	String train_type;
	int train_no;
	String stopage[];
	int no_of_stops;
	String dest_station;
	String source_station;
	int priority;
	int days_of_run[];
	boolean randomTime;
	int hr;
	int min;

	
	public String getDest_station() {
		return dest_station;
	}
	public int getNo_of_stops() {
		return no_of_stops;
	}
	public int getNo_rows() {
		return size;
	}

	public int getRoute_number() {
		return route_number;
	}

	public String getSource_station() {
		return source_station;
	}

	public String[] getStopage() {
		return stopage;
	}
	public String getTrain_name() {
		return train_name;
	}

	public int getTrain_no() {
		return train_no;
	}

	public String getTrain_speed() {
		return train_speed;
	}
	public int getTrain_speed_no() {
		return train_speed_no;
	}
	public String getTrain_type() {
		return train_type;
	}

	/**
	 * @return the days_of_run
	 */
	public int[] getDays_of_run() {
		return days_of_run;
	}
	/**
	 * @param days_of_run the days_of_run to set
	 */
	public void setDays_of_run(int[] days_of_run) {
		this.days_of_run = days_of_run;
	}
	

	public void setDest_station(String dest_station) {
		this.dest_station = dest_station;
	}

	public void setNo_of_stops(int no_of_stops) {
		this.no_of_stops = no_of_stops;
	}
	public void setNo_rows(int size) {
		this.size = size;
	}

	public void setRoute_number(int rn) {
		route_number = rn;
	}
	public void setSource_station(String source_station) {
		this.source_station = source_station;
	}
	public void setStopage(String[] st) {
		stopage = st;
		// System.out.println("stopage 1= "+stopage[0]+"stopage 2 ="+stopage[1]
		// );
	}
	public void setTrain_name(String ss) {
		train_name = ss;
		// System.out.println("train name="+train_name );

	}
	public void setTrain_no(int tn) {
		train_no = tn;
	}
	public void setTrain_speed(String ds) {
		train_speed = ds;
	}
	public void setTrain_speed_no(int ds) {
		train_speed_no = ds;
	}
	public void setTrain_type(String tt) {
		train_type = tt;
	}

}