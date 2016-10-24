package dev.zero.trainSimulator.view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.RouteEntity;

public class DisplayRouteManager {

	public RouteEntity[] insertSM() {

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

			System.out.println(query);

			rs = st.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				obj[i].setRoute_number(Integer.parseInt(rs
						.getString("route_no")));
				obj[i].setSource_station(rs.getString("st0"));
				int size = Integer.parseInt(rs.getString("no_station")) - 1;

				if (size > 1) {
					int num = size / 2;
					String via_1 = "st" + num;
					System.out.println("via station=" + via_1);
					obj[i].setVia_1_station(rs.getString(via_1));
				}
				String dest_station = "st" + size;
				obj[i].setDest_station(rs.getString(dest_station));
				i++;
			}
			obj[0].setRoute_size(i);
			st.close();
			con.close();

		}

		catch (Exception e) {
			System.out.println("Errors in reading " + e);

		}
		return obj;

	}
}
