import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author Priyabrata
 * 
 */
public class SetTrainTime {

	String dbURL = "jdbc:odbc:TSA";
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	String query, query1, query2, query3;
	int countStops;
	int i = 0;
	String dep_time = "";
	/** Stores the Train speed */
	int train_speed_value;
	String train_name;
	String train_type;
	String source_station;
	String destination_station;
	int train_route;
	String stopage[] = new String[200];
	String allStations[] = new String[200];
	int stationCounter = 0;
	int stationType[] = new int[200];
	int distance[] = new int[200];
	/** Stores station names */
	public String stationName[] = new String[200];
	int stopNoStop[] = new int[200];
	int stationNo = 0;
	int trainNo;

	SetTrainTime(int trainNo) {
		this.trainNo = trainNo;
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
			query = "select train_speed from train where train_no=" + trainNo;
			rs = st.executeQuery(query);
			// System.out.println(rs);

			while (rs.next()) {
				train_speed_value = Integer.parseInt(rs
						.getString("train_speed"));

			}

		} catch (Exception e) {
			System.out.println("Errors in reading1 ");
		}
		try {
			// con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			// st = con.createStatement();
			query = "select * from station_details";
			rs = st.executeQuery(query);
			while (rs.next()) {
				allStations[stationCounter] = rs.getString("station_name");
				stationType[stationCounter] = Integer.parseInt(rs
						.getString("station_type"));
				stationCounter++;
			}
			con.close();
			st.close();

		} catch (Exception e) {
			System.out.println("Error in reading from station_detatils");
		}
		try {
			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();
			String train = "" + trainNo;
			query = "select * from " + train;
			rs1 = st.executeQuery(query);
			System.out.println(query);
			while (rs1.next()) {
				distance[stationNo] = rs1.getInt("distance");
				stationName[stationNo] = rs1.getString("station_name");
				System.out.println("bsmdfbsdfbmsdfmb-------"
						+ stationName[stationNo]);
				stopNoStop[stationNo] = rs1.getInt("stop_nostop");
				stationNo++;

			}

		} catch (Exception e) {
			System.out.println("Errors in reading2");
		}

	}

	public void setInitialTime(TimeCal cld) throws SQLException {

		TimeCal arrival = new TimeCal();
		arrival = cld;
		TimeCal departure_obj = new TimeCal();
		departure_obj = cld;
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
			// format and execute query

			int int_time = 0;
			int int_minute = 0;
			int type = 0;
			String array[] = new String[stationNo];
			String array_1[] = new String[stationCounter];
			for (int i = 0; i < stationNo; i++) {
				array[i] = stationName[i];

			}
			for (int i = 0; i < stationCounter; i++) {

				array_1[i] = allStations[i];

			}

			for (int i = 0; i < stationNo; i++) {
				query = "update " + trainNo + " set arrival = '"
						+ arrival.convertDateToString() + "'," + "departure= '"
						+ departure_obj.convertDateToString()
						+ "' where stationIndex = " + i;

				System.out.println(query);

				st.executeUpdate(query);

				int_time = (distance[i + 1] - distance[i]) / train_speed_value;
				int_minute = (distance[i + 1] - distance[i])
						% train_speed_value;

				int_minute = (int_minute * 60) / train_speed_value;
				departure_obj.addTime(int_time, int_minute, 0);
				arrival = departure_obj;
				String str1 = null;

				str1 = array[i + 1];
				String query2 = "select station_type from station_details where station_name='"
						+ str1 + "'";
				System.out.println(query2);

				rs1 = st.executeQuery(query2);
				while (rs1.next()) {
					type = Integer.parseInt(rs1.getString("station_type"));

				}

				if (stopNoStop[i + 1] == 1) {
					if (type == 1) {
						departure_obj = arrival.addTime(0, 5);
					} else if (type == 2) {
						departure_obj = arrival.addTime(0, 10);
					} else if (type == 3) {
						departure_obj = arrival.addTime(0, 15);
					}
				} else {
					departure_obj = arrival;
				}
			}

		}

		catch (Exception e) {
			System.out.println("Train timings set " + e);

		} finally {

			st.close();
			con.close();

		}
	}
}
