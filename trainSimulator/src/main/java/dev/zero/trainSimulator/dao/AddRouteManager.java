package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.zero.trainSimulator.model.RouteEntity;

@Component
public class AddRouteManager {
	
	@Autowired
	DataSource dataSource;
			
	public AddRouteManager(RouteEntity re) throws SQLException {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		String query;
		String train_string = new String();
		String train_string_2 = new String();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception e) {
			System.out.println("Can't load driver:");
		}
		try {

			con = dataSource.getConnection();
			System.out.println("Connected to database  successfully");
			st = con.createStatement();

			int size = re.getRoute_size();
			String station_array[] = new String[size];
			System.out.println("route no in manager is" + re.getRoute_number());
			station_array = re.getRoute_stations();
			for (int i = 0; i < size; i++) {
				// station_array[i]=se.GetRoute_size();
				System.out.println("Stations in Manager:" + station_array[i]);
			}
			for (int i = 0; i < size; i++) {
				train_string = train_string + ",st" + i;
			}
			for (int i = 0; i < size; i++) {
				train_string_2 = train_string_2 + ",\'" + station_array[i]
						+ "\'";
			}
			// System.out.println( "Stations sl no List in Manager:"
			// +train_string);
			// System.out.println( "Stations List in Manager:" +train_string_2);

			query = "insert into routes (route_no,no_station" + train_string
					+ ") values ('" + re.getRoute_number() + "','" + size + "'"
					+ train_string_2 + ")";

			System.out.println(query);
			int value = st.executeUpdate(query);
			System.out.println("after execute" + value);
			// System.out.println("After update query i nroutemanager
			// value="+value);

		} catch (Exception e) {
			System.out.println("Errors in reading " + e);

		} finally {
			st.close();
			con.close();
		}
	}
	
	
		
	
	
	
}
