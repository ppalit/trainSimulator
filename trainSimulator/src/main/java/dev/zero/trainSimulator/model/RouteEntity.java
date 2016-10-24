package dev.zero.trainSimulator.model;
public class RouteEntity {

	public int route_number;
	String stations[] = new String[200];
	// stations = ;
	public int size;
	String souce_station;
	String destination_station;
	String via_station_1;
	String via_station_2;

	public int count;
	public String getDest_station() {
		return destination_station;
	}

	public int getRoute_number() {
		return route_number;
	}

	public int getRoute_size() {
		return size;
	}

	public String[] getRoute_stations() {
		return stations;
	}

	public String getSource_station() {
		return souce_station;
	}

	public String getVia_1_station() {
		return via_station_1;
	}

	public String getVia_2_station() {
		return via_station_2;
	}

	public void setDest_station(String ds) {
		destination_station = ds;
	}

	public void setRoute_number(int rn) {
		route_number = rn;
	}

	public void setRoute_size(int rn) {
		size = rn;
	}

	public void setRoute_stations(String arr[]) {
		stations = arr;
	}

	public void setRoute_stations(String arr[], int count) {
		size = count;
		for (int i = 0; i < count; i++) {
			stations[i] = arr[i];
		}

	}
	public void setSource_station(String ss) {
		souce_station = ss;
	}

	public void setVia_1_station(String vs) {
		via_station_1 = vs;
	}

	public void setVia_2_station(String vs) {
		via_station_2 = vs;
	}

}