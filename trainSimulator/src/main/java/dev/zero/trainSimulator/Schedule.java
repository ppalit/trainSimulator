package dev.zero.trainSimulator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import dev.zero.trainSimulator.model.Edge;
import dev.zero.trainSimulator.model.ScheduleEntity;
import dev.zero.trainSimulator.model.TimeCal;

/**
 * This is the class that does all the scheduling work
 */

/**
 * @author Priyabrata
 * 
 */
public class Schedule {

	public static void initializer() throws SQLException {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;

		ResultSet rs = null;
		String query;
		TimeCal tc = new TimeCal();
		Schedule sch = new Schedule();

		System.out
				.println("----------------------------------------------------------------------------"
						+ sch.flag);
		while (sch.flag != 0) {
			SetTrainTime stt = new SetTrainTime(sch.flag);

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

				query = "SELECT no_station FROM routes WHERE route_no=(select train_route_no from train WHERE train_no="
						+ sch.flag + ")";

				System.out.println(query);
				rs = st.executeQuery(query);
				int noOfStation = 0;
				while (rs.next()) {
					noOfStation = rs.getInt("no_station");

				}
				Random rnd = new Random();
				int day = 0;
				int hour = rnd.nextInt(24);
				int min = rnd.nextInt(60);
				System.out.println("length of station=" + noOfStation);
				if (noOfStation < 400) {
					while ((hour < 5) || (hour > 19)) {
						hour = rnd.nextInt(24);
					}
				}

				tc = new TimeCal(hour, min, day);

				st.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error in main!!");
				e.printStackTrace();
				st.close();
				con.close();
			}

			stt.setInitialTime(tc);
			sch = new Schedule();
			st.close();
			con.close();

		}

	}
	public int trainArr[][] = new int[200][200];
	String dbURL = "jdbc:odbc:TSA";
	Connection con = null;
	Statement st = null;

	ResultSet rs = null;
	String query;
	int count = 0;
	int totalTrains = 0;
	int stationCount = 0;
	public String stop = "";

	public String start = "";
	ScheduleEntity schEnt[] = new ScheduleEntity[200];
	Edge[] edges = new Edge[200];
	public int flag = 0;
	int countEdges;
	
	public Schedule() throws SQLException {
		for (int i = 0; i < 200; i++) {
			schEnt[i] = new ScheduleEntity();
		}
		for (int j = 0; j < 200; j++) {
			edges[j] = new Edge();
		}
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception e) {
			System.out.println("Can't load driver:");
		}
		try {
			count = 0;
			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();

			query = "SELECT t.train_no, t.train_name, r.no_station FROM train t, routes r WHERE t.train_route_no = r.route_no ORDER BY t.priority DESC";

			rs = st.executeQuery(query);
			while (rs.next()) {

				trainArr[totalTrains][0] = rs.getInt("train_no");
				trainArr[totalTrains][1] = rs.getInt("no_station");
				totalTrains++;
			}
			// System.out.println("total trains="+trainArr[2][1]);
			query = "SELECT * FROM Edges";
			rs = st.executeQuery(query);
			countEdges = 0;
			while (rs.next()) {
				edges[countEdges].setStationFrom(rs.getString("station1"));
				// ed[countEdges].stationFrom = rs.getString("station1");
				edges[countEdges].stationTo = rs.getString("station2");
				edges[countEdges].noOfTrack = rs.getInt("noOfTrack");
				countEdges++;
			}
			for (int i = 0; i < totalTrains; i++) {
				query = "SELECT arrival, departure, stationIndex, station_name from "
						+ trainArr[i][0];

				rs = st.executeQuery(query);

				while (rs.next()) {
					schEnt[count].setArrivalTime(rs.getString("arrival"));
					schEnt[count].setDepartureTime(rs.getString("departure"));
					schEnt[count].setStationIndex(rs.getInt("stationIndex"));
					schEnt[count].setStationName(rs.getString("station_name"));
					schEnt[count].setTrainNo(trainArr[i][0]);
					// System.out.println("count="+schEnt[count].getTrainNo());
					count++;
				}
			}
		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		// System.out.println(schEnt[12].getArrivalTime());
		
		int arrIndex = 0;
		int depIndex = 0;
		String matchingSt1 = "";
		String matchingSt2 = "";
		int noOfTrackAvl = 0;

		System.out.println("+++++++++++++++++CONFIGURING START TIME++++++++++++++++++++++++");
		
		for (int i = 0; i < count - 1; i++) {
			arrIndex = this.searchTimeArrival(schEnt,
					schEnt[i].departureTime, schEnt[i + 1].arrivalTime,
					count);
			// System.out.println(i);
			if (arrIndex > 1) {
				// System.out.println(arrIndex);
				matchingSt1 = schEnt[arrIndex].stationName;
				matchingSt2 = schEnt[arrIndex - 1].stationName;
			//	noOfTrackAvl = findEdge(matchingSt1, matchingSt2, edges,
			//			countEdges);
				System.out.println("no of avl tracks="+ noOfTrackAvl);
System.out.println(" subject station = " +schEnt[i].stationName + " , "+ schEnt[i+1].stationName+" Train no"+schEnt[i].trainNo );
System.out.println(" matching station = " +matchingSt1 + " , "+ matchingSt2+" Train Match "+schEnt[arrIndex].trainNo);

				if(matchEdge(schEnt[i].stationName, schEnt[i+1].stationName, matchingSt1, matchingSt2) == true){
					flag = schEnt[arrIndex].trainNo;
				//	System.out.println("+++++++++++++++++++++++++++++++++++++++++");
					return;
				}
				System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		

		}

	 }
		System.out.println("--------CONFIGURING DEPARTURE TIME-----------------------");
		for (int i = 0; i < count - 1; i++) {
			depIndex = this.searchTimeDeparture(schEnt,
					schEnt[i].departureTime, schEnt[i + 1].arrivalTime,
					count);
			if (depIndex > 1) {
				// System.out.println(arrIndex);
				matchingSt1 = schEnt[depIndex].stationName;
				matchingSt2 = schEnt[depIndex + 1].stationName;
			//	noOfTrackAvl = findEdge(matchingSt1, matchingSt2, edges,
			//			countEdges);
				System.out.println(" subject station = " +schEnt[i].stationName + " , "+ schEnt[i+1].stationName+" Train no"+schEnt[i].trainNo );
				System.out.println(" matching station = " +matchingSt1 + " , "+ matchingSt2+" Train Match "+schEnt[depIndex].trainNo);

				if(matchEdge(schEnt[i].stationName, schEnt[i+1].stationName, matchingSt1, matchingSt2) == true){
					flag = schEnt[depIndex].trainNo;
					
					return;
					
				}
				System.out.println("-------------------------------");
				
			}

	}
		System.out.println(" Sheduling Done!!!");
		//flag=0;
	}
		public int findEdgeIndex(String to, String from, int countEdges, Edge[] edg) {

			for (int i = 0; i < countEdges; i++) {
				if ((edg[i].stationFrom.equals(from) && edg[i].stationTo.equals(to))
						|| (edg[i].stationFrom.equals(to) && edg[i].stationTo
								.equals(from))) {
					return i;
				}
			}
			return -1;
		}
		public boolean matchEdge(String str1, String str2, String str3, String str4) {
			if ((str1.equals(str3) && str2.equals(str4))
					|| (str1.equals(str4) && str2.equals(str3)))
				return true;
			return false;
		}
		public int findEdge(String from, String to, Edge[] edg, int counter) {
			int num = -1;
			for (int i = 0; i < counter; i++) {
				if ((edg[i].stationFrom.equals(from) && edg[i].stationTo.equals(to))
						|| (edg[i].stationFrom.equals(to) && edg[i].stationTo
								.equals(from))) {
					num = edg[i].noOfTrack;
				}
			}
			return num;
		}
		public int searchTimeArrival(ScheduleEntity[] ent, String start,
				String stop, int noOfEntry) {
			ScheduleEntity sch[] = ent;
			System.out.println("start=" + start + "end=" + stop + ";");
			TimeCal startTime = new TimeCal(start);
			TimeCal endTime = new TimeCal(stop);
			TimeCal checkTime;
			for (int i = 0; i < noOfEntry; i++) {
				checkTime = new TimeCal(sch[i].arrivalTime);
				if ((checkTime.compare(startTime) == -1)
						&& (checkTime.compare(endTime) == 1)) {
					// System.out.println(sch[i].stationName);
					return i;

				}
			}
			return -1;
		}
		public int searchTimeDeparture(ScheduleEntity[] ent, String start,
				String stop, int noOfEntry) {
			ScheduleEntity sch[] = ent;
			// System.out.println("start=" + start + "end=" + stop);
			TimeCal startTime = new TimeCal(start);
			TimeCal endTime = new TimeCal(stop);
			TimeCal checkTime;
			for (int i = 0; i < noOfEntry; i++) {
				checkTime = new TimeCal(sch[i].departureTime);
				if ((checkTime.compare(startTime) == -1)
						&& (checkTime.compare(endTime) == 1)) {
					// System.out.println(sch[i].stationName);
					return i;

				}
			}
			return -1;
		}

}