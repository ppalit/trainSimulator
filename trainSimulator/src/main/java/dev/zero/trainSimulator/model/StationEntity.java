package dev.zero.trainSimulator.model;
public class StationEntity {
	int xcod;
	String station_code;
	String conn_station1;
	String conn_station2;
	String conn_station3;
	int ycod;
	public int count;
	public int station_type;
	public String station_name;

	public String getConn_station1() {
		return conn_station1;
	}

	public String getConn_station2() {
		return conn_station2;
	}

	public String getConn_station3() {
		return conn_station3;
	}

	public String getStation_code() {
		return station_code;
	}

	public String getStation_name() {
		return station_name;
	}

	public int getStation_type() {
		return station_type;
	}

	public int getXcod() {
		return xcod;
	}

	public int getYcod() {
		return ycod;
	}

	public void setConn_station1(String p) {
		conn_station1 = p;
	}

	public void setConn_station2(String p) {
		conn_station2 = p;
	}

	public void setConn_station3(String p) {
		conn_station3 = p;
	}

	public void setStation_code(String sn) {
		station_code = sn;
	}

	public void setStation_name(String sn) {
		station_name = sn;
	}

	public void setStation_type(int i) {
		station_type = i;
	}

	public void setXcod(int i) {
		xcod = i;
	}

	public void setYcod(int s) {
		ycod = s;
	}

}
