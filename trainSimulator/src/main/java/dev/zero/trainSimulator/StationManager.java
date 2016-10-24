package dev.zero.trainSimulator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.StationEntity;

public class StationManager {
	/*
	 * public static void main(String args[]) { try{ testConnection(); }
	 * catch(Exception t) { System.out.println("Error:"+t); } }
	 */

	public StationEntity[] insertSM() {
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		StationEntity obj[] = new StationEntity[200];
		for (int i = 0; i < 200; i++) {
			obj[i] = new StationEntity();
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
			query = "select * from station_details";
			rs = st.executeQuery(query);
			System.out.println(rs);
			int i = 0;
			while (rs.next()) {

				int xcod_value = Integer.parseInt(rs.getString("xcod"));
				int ycod_value = Integer.parseInt(rs.getString("ycod"));
				String st_name = rs.getString("station_name");
				String connected_station1 = rs.getString("conn_st_1");
				String connected_station2 = rs.getString("conn_st_2");
				String connected_station3 = rs.getString("conn_st_3");
				int st_type = Integer.parseInt(rs.getString("station_type"));

				obj[i].setXcod(xcod_value);
				obj[i].setYcod(ycod_value);
				obj[i].setStation_name(st_name);
				obj[i].setConn_station1(connected_station1);
				obj[i].setConn_station2(connected_station2);
				obj[i].setConn_station3(connected_station3);
				obj[i].setStation_type(st_type);
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
