package dev.zero.trainSimulator.view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.TrainEntity;

public class DisplayTrainManager {

	public TrainEntity[] insertSM() {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		TrainEntity obj[] = new TrainEntity[200];
		for (int i = 0; i < 200; i++) {
			obj[i] = new TrainEntity();
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

			query = "select * from Train";

			System.out.println(query);

			rs = st.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				obj[i].setTrain_no(Integer.parseInt(rs.getString("train_no")));
				obj[i].setTrain_name(rs.getString("train_name"));
				obj[i].setTrain_speed(rs.getString("train_speed"));
				obj[i].setTrain_type(rs.getString("train_type"));
				obj[i].setRoute_number(Integer.parseInt(rs
						.getString("train_route_no")));
				obj[i].setSource_station(rs.getString("source_station"));
				obj[i].setDest_station(rs.getString("dest_station"));

				i++;
			}
			obj[0].setNo_rows(i);
			st.close();
			con.close();

		}

		catch (Exception e) {
			System.out.println("Errors in reading " + e);

		}
		return obj;

	}
}
