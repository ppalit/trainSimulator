package dev.zero.trainSimulator.model;
public class TrainTimeEntity {

	
	
	
	String station;
	String train;
	String arrival;
	String departure;
	String day;
	int count;
	int train_no;
	public String getStation() {
		return station;
	}

	public String getTrain() {
		return train;
	}

	
	public String getArrival() {
		return arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDay_val() {
		return day;
	}

	
	public int getSize(){
		return count;
	}	
	
	public int  getTrainNo(){
		return train_no;
	}
		
	
	
	
	public void setStation(String ds) {
		station= ds;
	}
	public void setTrain(String ds) {
		train= ds;
	}

	public void setArrival(String rn) {
		arrival = rn;
	}

	public void setDeparture(String dp) {
		departure = dp;
	}
	
	public void setDay_val(String dy) {
		day = dy;
	}

	public void setSize(int sz){
		count=sz;
	}
	
	public void setTrainNo(int tn){
		train_no=tn;
	}
		

}