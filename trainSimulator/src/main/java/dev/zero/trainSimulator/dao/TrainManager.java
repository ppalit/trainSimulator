package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.TrainEntity;

public class TrainManager {
	/*
	 * public static void main(String args[]) { try{ testConnection(); }
	 * catch(Exception t) { System.out.println("Error:"+t); } }
	 */

	int count = 0;
	String stopage[];

	public TrainEntity[] insertTM() {
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		System.out.println("=====================================================================================");
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
			query = "select * from runningdays";
			System.out.println(query);
			rs = st.executeQuery(query);
			int runDays[][] = new int[200][7];
			int j=0;
			while (rs.next()) {
				
				System.out.println("hasjabd");
				runDays[j][0] = rs.getInt("mon");//getInt();
				System.out.println(runDays[j][0]);
				runDays[j][1] = rs.getInt("tue");
				System.out.println(runDays[j][0]);
				runDays[j][2] = rs.getInt("wed");
				System.out.println(runDays[j][0]);
				runDays[j][3] = rs.getInt("thu");
				System.out.println(runDays[j][0]);
				runDays[j][4] = rs.getInt("fri");
				System.out.println(runDays[j][0]);
				runDays[j][5] = rs.getInt("sat");
				System.out.println(runDays[j][0]);
				runDays[j][6] = rs.getInt("sun");
			//	System.out.println("gdfghbjhbygby" + rs.getInt("sun"));
				j++;
								
			}
			query = "select * from train";
			System.out.println("in Train  MAnager-------------------------------------------------------------------" +query);
			
			rs = st.executeQuery(query);
			int i = 0;
			while (rs.next()) {

				int train_no_value = Integer.parseInt(rs.getString("train_no"));

				int train_speed_value = Integer.parseInt(rs
						.getString("train_speed"));
				String train_name = rs.getString("train_name");
				String train_type = rs.getString("train_type");

				String source_station = rs.getString("source_station");
				String destination_station = rs.getString("dest_station");

				int train_route = Integer.parseInt(rs
						.getString("train_route_no"));
				count = Integer.parseInt(rs.getString("no_of_stops"));
				String stopage[] = new String[200];
				// count=3;
				for (int k = 0; k < count; k++) {
					stopage[k] = rs.getString("stopage" + k);
				}
				

				obj[i].setRoute_number(train_route);
				obj[i].setTrain_name(train_name);
				obj[i].setTrain_no(train_no_value);
				obj[i].setTrain_speed_no(train_speed_value);
				obj[i].setTrain_type(train_type);
				obj[i].setStopage(stopage);
				obj[i].setNo_of_stops(count);
				obj[i].setSource_station(source_station);
				obj[i].setDest_station(destination_station);
				obj[i].setDays_of_run(runDays[i]);
				i++;
			}
			
			
			obj[0].size = i;
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Errors in reading ");
		}
		

		return obj;
	}

}
