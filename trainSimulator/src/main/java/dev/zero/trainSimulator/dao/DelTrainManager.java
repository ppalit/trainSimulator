package dev.zero.trainSimulator.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DelTrainManager {

	public DelTrainManager(int trainno) throws SQLException {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		// ResultSet rs1 = null;
		// ResultSet rs2 = null;
		String query, query1;
		String query_st1 = null;
		String query_st2 = null;
		int trainNo = trainno;
		
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

			
			int train1=0;
			int train2=0;
			if(trainNo%2==0)
				{
					train1=trainNo;
					train2=trainNo+1;
				}
			else{
					train1=trainNo-1;
					train2=trainNo;
				}

			String trainString1=""+train1;
			String trainString2=""+train2;
			// String station_array[]= new String[size];
			System.out.println("Train nos in manager is" + train1+"and"+train2 );

			query = "delete from train where train_no=" + train1;
			st.executeUpdate(query);
			query = "delete from train where train_no=" + train2;
			st.executeUpdate(query);
			
			query = "delete from runningdays where train_no=" + train1;
			st.executeUpdate(query);
			query = "delete from runningdays where train_no=" + train2;
			st.executeUpdate(query);
			
			query = " DROP TABLE "+trainString1;
			st.execute(query);
			
			query = " DROP TABLE "+trainString2;
			st.execute(query);
			
			JOptionPane.showMessageDialog(null, "Train " + train1+"and"+train2 +"deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE); 
			
		//	st.close();
			//con.close();
			
			} catch (Exception e) {
			System.out.println("Errors in reading " + e);
			JOptionPane.showMessageDialog(null, "Train No. "+trainNo+" is not Present", "alert", JOptionPane.ERROR_MESSAGE); 

		}
		finally {
			// rs.close();
			st.close();
			con.close();

		}
		}
		}
			