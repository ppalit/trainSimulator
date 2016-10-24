package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.RouteEntity;

public class RouteManager {
	int count = 0;
	String stations[];
	public RouteEntity[] insertRM() {
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		RouteEntity obj[] = new RouteEntity[200];
		for (int i = 0; i < 200; i++) {
			obj[i] = new RouteEntity();
		}
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception e) {
			System.out.println("Can't load driver:");
		}
		try {
			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();
			query = "select * from routes";
			rs = st.executeQuery(query);
			String stations[] = new String[200];
			int i = 0;
			while (rs.next()) {

				int route_number = Integer.parseInt(rs.getString("route_no"));
				int size = Integer.parseInt(rs.getString("no_station"));

				for (int k = 0; k < size; k++) {
					stations[k] = rs.getString("st" + k);

					// count++;
				}

				obj[i].setDest_station(stations[size - 1]);
				obj[i].setRoute_number(route_number);
				obj[i].setRoute_size(size);
				obj[i].setRoute_stations(stations, size);
				obj[i].setSource_station(stations[0]);

				i++;
			}

			obj[0].count = i;
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Errors in reading ");
		}

		return obj;
	}

}
