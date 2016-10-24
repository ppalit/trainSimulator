package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AddEdgeManager {
	String dbURL = "jdbc:odbc:TSA";
	Connection con = null;
	Statement st = null;
	public ResultSet rs = null;
	String query1;
	String station1, station2;
	String arr[][] = new String[300][300];
	int counter;
	int noTracks[] = new int[300];

	public AddEdgeManager() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception e1) {
			System.out.println("Can't load driver:");
		}
		try {

			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();

			query1 = "select * from Edges";

			System.out.println(query1);
			rs = st.executeQuery(query1);
			System.out.println("after execute");
			while (rs.next()) {
				arr[counter][0] = rs.getString("station1");
				arr[counter][1] = rs.getString("station2");
				noTracks[counter] = (int) rs.getFloat("noOfTrack");
				// System.out.println(noTracks[counter]);
				counter++;
			}

		} catch (Exception e) {
			System.out.println("Errors in reading " + e);

		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				System.out
						.println("error in closing datatabase in AddEdgeMAnager");
			}
		}
	}

	public boolean checkPresent(String station1, String station2) {
		for (int i = 0; i < counter; i++) {
			// JOptionPane.showMessageDialog(this,station1);
			// System.out.println(station1+","+"");

			if (station1.equals(arr[i][0]) && station2.equals(arr[i][1]))
				return true;
			if (station2.equals(arr[i][0]) && station1.equals(arr[i][1]))
				return true;
		}
		return false;

	}

	public int getNoOfTrack(String station1, String station2) {
		for (int i = 0; i < counter; i++) {
			if (station1.equals(arr[i][0]) && station2.equals(arr[i][1]))
				return noTracks[i];
			if (station2.equals(arr[i][0]) && station1.equals(arr[i][1]))
				return noTracks[i];
		}
		return 0;
	}

	public void setTrack(String station1, String station2) {

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception e1) {
			System.out.println("Can't load driver:");
		}
		try {

			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();

			Object[] possibleValues = {"1", "2", "3"};
			Object selectedValue = JOptionPane.showInputDialog(null,
					"Select the no. of tracks in the connection:\n " + station1
							+ "---" + station2, "Input",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
					possibleValues[0]);

			query1 = "insert into Edges values('" + station1 + "','" + station2
					+ "'," + Integer.parseInt(selectedValue.toString()) + ")";

			System.out.println(query1);
			rs = st.executeQuery(query1);
			System.out.println("after execute");

		} catch (Exception e) {
			System.out.println("Errors in reading " + e);

		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				System.out
						.println("error in closing datatabase in AddEdgeMAnager");
			}
		}

	}

}
