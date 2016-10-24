package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import dev.zero.trainSimulator.Schedule;
import dev.zero.trainSimulator.SetTrainTime;
import dev.zero.trainSimulator.model.TimeCal;
import dev.zero.trainSimulator.model.TrainEntity;

public class AddTrainManager {

	public AddTrainManager(TrainEntity te) throws SQLException {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		// ResultSet rs1 = null;
		// ResultSet rs2 = null;
		String query, query1;
		String query_st1 = null;
		String query_st2 = null;
		int trainNo = 0;
		int trainNo_rev = 0;
		String stop_stations = "";
		String stop_stations_rev = "";
		int no_of_stops = 0;
		int days_run[] = new int[7];
		int hour = te.hr;
		int min = te.min;
		boolean randomTime = true;
		int priority;
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

			int routeNo = te.getRoute_number();
			// String station_array[]= new String[size];
			System.out.println("route no in manager is" + routeNo);

			query = "select * from routes where route_no=" + routeNo;

			// System.out.println(query);
			rs = st.executeQuery(query);
			System.out.println("after execute in AddTrain Manager");
			// System.out.println("After update query i nroutemanager
			// value="+value);
			String source_station = "";
			String dest_station = "";
			int route_size;
			int count = 0;
			String station_array[] = new String[50];
			String station_list[] = new String[50];
			// ******************************** changing the speed to equivalent
			// number.
			int speed;
			if (te.getTrain_speed() == "superfast") {
				speed = 120;
			} else if (te.getTrain_speed() == "express") {
				speed = 100;
			} else if (te.getTrain_speed() == "passenger") {
				speed = 80;
			} else if (te.getTrain_speed() == "Rajdhani") {
				speed = 140;
			} else {
				speed = 140;
			}

			// while loop started to access the route details.

			if (te.getTrain_no() % 2 == 0) {
				trainNo = te.getTrain_no();
				trainNo_rev = te.getTrain_no() + 1;
			}

			while (rs.next()) {
				route_size = Integer.parseInt(rs.getString("no_station"));
				source_station = rs.getString("st0");
				// String station_array[]= new String[route_size-2];
				// System.out.println("**************************************");
				int size = route_size - 1;
				dest_station = rs.getString("st" + size);
				station_list[0] = source_station;
				station_list[size] = dest_station;
				for (int i = 1; i < route_size - 1; i++) {
					String j = "st" + i;
					station_array[i - 1] = rs.getString(j);
					station_list[i] = station_array[i - 1];
					count++;
					// System.out.println("**************************************inside
					// 1st loop");
				}
				/*
				 * for (int i = 0; i < route_size; i++) { String str = "st" + i;
				 * station_list[i]=rs.getString(str);
				 * System.out.println("**************************************inside
				 * 2nd loop"); }
				 */

			}

			int station_stopage[] = new int[count + 2]; // array of 1n 0 to
			// store in each train
			// table
			for (int i = 0; i < count + 2; i++) {
				station_stopage[i] = 1;
			}

			System.out.println("value from count=" + count);

			for (int i = 0; i < count; i++) {
				station_stopage[i + 1] = 0;
				if (JOptionPane.showConfirmDialog(null, station_array[i]
						+ " should be a stopage", "Chose one",
						JOptionPane.YES_NO_OPTION) == 0) {
					station_stopage[i + 1] = 1;
					no_of_stops++;
					// stop_station_array[i]=station_array[i];
					stop_stations = stop_stations + ",\'" + station_array[i]
							+ "\'";
					stop_stations_rev = ",\'" + station_array[i] + "\'"
							+ stop_stations_rev;
					System.out
							.println("station in revere_______________________________"
									+ stop_stations_rev);
				} else {
					System.out.println("rejected" + station_array[i]);
				}

			}

			// System.out.println("size of x"+x);
			// for(int j=0; j<no_of_stops;j++)
			// {System.out.println("Selected
			// stations::"+stop_station_array[j]);}
			String stops = "";

			for (int j = 0; j < no_of_stops; j++) {
				stops = stops + ",stopage" + j;
				// stop_stations=stop_stations+",\'"+stop_station_array[j]+"\'";
			}

			int xcod1 = 0;
			int xcod2 = 0;
			int ycod1 = 0;
			int ycod2 = 0;
			int len = 0;
			int edges_length[] = new int[count + 2];
			int edges_length_rev[] = new int[count + 2];
			int edges_length_re[] = new int[count + 2];
			edges_length[0] = 0;
			edges_length_rev[count + 1] = 0;
			for (int i = 0; i < count + 1; i++) {

				query_st1 = "select * from station_details where station_name='"
						+ station_list[i] + "'";
				System.out.println(query_st1);
				rs = st.executeQuery(query_st1);

				while (rs.next()) {
					xcod1 = Integer.parseInt(rs.getString("xcod"));
					ycod1 = Integer.parseInt(rs.getString("ycod"));
				}
				query_st2 = "select * from station_details where station_name='"
						+ station_list[i + 1] + "'";
				rs = st.executeQuery(query_st2);
				while (rs.next()) {
					xcod2 = Integer.parseInt(rs.getString("xcod"));
					ycod2 = Integer.parseInt(rs.getString("ycod"));
				}

				len = (int) Math.abs(Math.sqrt((xcod2 - xcod1)
						* (xcod2 - xcod1) + (ycod2 - ycod1) * (ycod2 - ycod1)));
				edges_length[i + 1] = edges_length[i] + len;

			}
			for (int i = 0; i < count + 2; i++) {
				System.out.println("stations:" + station_list[i]
						+ "distance from source=" + edges_length[i]);
			}
			for (int i = 0; i < count + 1; i++) {
				edges_length_rev[i] = edges_length[i + 1] - edges_length[i];
				System.out.println("ok..........." + edges_length_rev[i]);
			}

			for (int i = count + 1; i > 0; i--) {
				edges_length_rev[i - 1] = edges_length_rev[i - 1]
						+ edges_length_rev[i];
			}
			/*
			 * for(int i=0;i<count+1;i++) {
			 * edges_length_re[count+1-i]=edges_length_rev[i];
			 * System.out.println("ok"); }
			 */

			/*int priority;
			//SetPriority sp= new SetPriority();
			int  waitCon= 0;
			
			synchronized (this) {
						this.wait(1000);
		               this.wait();
		         
		     }*/

			//this.wait();
			//System.out.println("******************************************* Waiting for execution" + sp.getOptionSelected());
			
			/*switch(sp.getOptionSelected()){
				case 1: priority = 1;
						break;
				case 2: priority =9;
						break;
				case 3: switch(speed){
					case 80: priority=1;
							break;
					case 100: priority = 3;
							break;
					case 120 : priority = 5;
							break;
					case 140 : priority = 7;
							break;
				}
				case 4: priority = 9;
						hour= sp.getHr();
						min= sp.getMinuite();
						randomTime  = false;
						break;
				default : priority = 1;
						
			}*/
			query = "insert into train (priority,train_no,train_name,no_of_stops,train_speed,train_route_no,source_station,dest_station"
					+ stops
					+ ") values ('"
					+ te.priority
					+ "','"
					+ trainNo
					+ "','"
					+ te.getTrain_name()
					+ "','"
					+ no_of_stops
					+ "','"
					+ speed
					+ "','"
					+ te.getRoute_number()
					+ "','"
					+ source_station
					+ "','" + dest_station + "'" + stop_stations + ")";
			System.out.println(query);
			st.executeUpdate(query);
			query1 = "insert into train (priority,train_no,train_name,no_of_stops,train_speed,train_route_no,source_station,dest_station"
					+ stops
					+ ") values ('"
					+ te.priority
					+ "','"
					+ trainNo_rev
					+ "','"
					+ te.getTrain_name()
					+ "','"
					+ no_of_stops
					+ "','"
					+ speed
					+ "','"
					+ te.getRoute_number()
					+ "','"
					+ dest_station
					+ "','" + source_station + "'" + stop_stations_rev + ")";

			System.out.println(query1);
			st.executeUpdate(query1);

			String tno = "" + trainNo;
			String tno_rev = "" + trainNo_rev;
			String query2 = "create table "
					+ tno
					+ " (stationIndex number,station_name VARCHAR, arrival VARCHAR, departure VARCHAR ,stop_nostop number, distance number)";
			System.out.println("*********************" + query2);

			String query2_rev = "create table "
					+ tno_rev
					+ " (stationIndex number,station_name CHAR(255), arrival CHAR(255), departure CHAR(255) ,stop_nostop number, distance number)";

			boolean x = st.execute(query2);
			boolean xx = st.execute(query2_rev);
			System.out.println("*********************" + query2_rev);

			System.out.println("%%%%%%%%%################" + x + xx);

			for (int i = 0; i < count + 2; i++) {
				String query3 = "insert into "
						+ tno
						+ " (stationIndex,station_name,stop_nostop,distance) values ('"
						+ i + "','" + station_list[i] + "','"
						+ station_stopage[i] + "','" + edges_length[i] + "' )";
				System.out.println(query3);
				st.executeUpdate(query3);
			}
			for (int i = count + 1; i >= 0; i--) {
				String query4 = "insert into "
						+ tno_rev
						+ " (stationIndex,station_name,stop_nostop,distance) values ('"
						+ (Math.abs(i - count - 1)) + "','" + station_list[i]
						+ "','" + station_stopage[i] + "','"
						+ edges_length_rev[i] + "' )";
				System.out.println(query4);
				st.executeUpdate(query4);
			}

			days_run = te.getDays_of_run();

			String query5 = "insert into runningdays (train_no,sun,mon,tue,wed,thu,fri,sat) values ('"
					+ trainNo
					+ "','"
					+ days_run[0]
					+ "','"
					+ days_run[1]
					+ "','"
					+ days_run[2]
					+ "','"
					+ days_run[3]
					+ "','"
					+ days_run[4]
					+ "','"
					+ days_run[5]
					+ "','"
					+ days_run[6]
					+ "')";

			st.executeUpdate(query5);

			String query6 = "insert into runningdays (train_no,sun,mon,tue,wed,thu,fri,sat) values ('"
					+ trainNo_rev
					+ "','"
					+ days_run[0]
					+ "','"
					+ days_run[1]
					+ "','"
					+ days_run[2]
					+ "','"
					+ days_run[3]
					+ "','"
					+ days_run[4]
					+ "','"
					+ days_run[5]
					+ "','"
					+ days_run[6]
					+ "')";

			st.executeUpdate(query6);

			

			try {
				Random rnd = new Random();
				int day = 0;
				if(te.randomTime == true){
				hour = rnd.nextInt(24);
				min = rnd.nextInt(60);
				
				System.out
						.println("length of edges=" + edges_length[count + 1]);
				if (edges_length[count + 1] < 400) {
					while ((hour < 5) || (hour > 19)) {
						hour = rnd.nextInt(24);
					}
				}
				}
				TimeCal tc = new TimeCal(hour, min, day);

				SetTrainTime stt = new SetTrainTime(Integer.parseInt(tno));
				stt.setInitialTime(tc);
				SetTrainTime stt1 = new SetTrainTime(Integer.parseInt(tno_rev));
				stt1.setInitialTime(tc);
			} catch (Exception e) {
				System.out.println("exception in random");
				e.printStackTrace();
			}

			// SetTrainTime stt1 =new SetTrainTime(trainNo_rev);
			// stt1.setInitialTime(new TimeCal(11,55,0));
			// Schedulintg funtion is to be called here\
			priority = 0;
				JFrame frm = new JFrame("Scheduling");
			    JProgressBar jProgressBar1 = new JProgressBar();
		        JLabel jLabel1 = new javax.swing.JLabel();

		        frm.getContentPane().setLayout(null);

		        frm.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		        frm.add(jProgressBar1);
		        jProgressBar1.setBounds(40, 80, 290, 16);

		        jLabel1.setText("Please Wait while Sscheduling is done...");
		        frm.add(jLabel1);
		        jLabel1.setBounds(50, 40, 290, 14);

		        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		        frm.setBounds((screenSize.width-395)/2, (screenSize.height-143)/2, 395, 143);
		        frm.setAlwaysOnTop(true);
			
			
			
			frm.setVisible(true);
			jProgressBar1.setIndeterminate(true);
			 for(int i=0; i< 1000; i++){
				 System.out.print("W");
			 }
			 Schedule.initializer();
			 jProgressBar1.setIndeterminate(false);
			 frm.dispose();
			switch(speed){
				case 80: priority=2;
						break;
				case 100: priority = 4;
						break;
				case 120 : priority = 6;
						break;
				case 140 : priority = 8;
						break;
			}
			query = "update train set priority ="+priority+" where train_no = " + trainNo;

			System.out.println(query);

			st.executeUpdate(query);
			
			query = "update train set priority ="+priority+" where train_no = " + trainNo_rev;

			System.out.println(query);

			st.executeUpdate(query);
			
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Errors in reading " + e);

		}

		finally {
			// rs.close();
			st.close();
			con.close();

		}

	}
}
