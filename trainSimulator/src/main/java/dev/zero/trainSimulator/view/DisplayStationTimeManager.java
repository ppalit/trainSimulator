package dev.zero.trainSimulator.view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dev.zero.trainSimulator.model.TrainTimeEntity;

public class DisplayStationTimeManager {
		
			public TrainTimeEntity[] display(TrainTimeEntity tte) {
			
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String query;
		String array_days[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun"}; 
		String array_days_running[]=new String[7];
		String train_name_arr[]=new String[100];
		int train_no[]=new  int[100];
		int train_size=0;
		TrainTimeEntity obj[] = new TrainTimeEntity[200];
		for (int i = 0; i < 200; i++) {
			obj[i] = new TrainTimeEntity();
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
			
			
			
			query= "select train_no,train_name from train";
			rs = st.executeQuery(query);
			int i=0;
			while(rs.next())
			{
			train_no[i]=Integer.parseInt(rs.getString("train_no"));
			train_name_arr[i]=rs.getString("train_name");
			i++;
			}
			train_size=i;
			String list_of_days= "";
			String list_of_days_next="";
			String list_of_days_arr[]= new String[train_size];
			String list_of_days_arr_next[]= new String[train_size];
			
			
			for(int j=0;j<train_size;j++)
			{
			query = "select * from runningdays where train_no="+train_no[j];
			System.out.println(query);
			rs1 = st.executeQuery(query);
			while (rs1.next()){
			
				array_days_running[0]=rs1.getString("sun");
				array_days_running[1]=rs1.getString("mon");
				array_days_running[2]=rs1.getString("tue");
				array_days_running[3]=rs1.getString("wed");
				array_days_running[4]=rs1.getString("thu");
				array_days_running[5]=rs1.getString("fri");
				array_days_running[6]=rs1.getString("sat");
					
			for(int k=0;k<7;k++)
			{
			System.out.println("ok_________________");
			//System.out.println("day is "+array_days_running[k]);
			if(array_days_running[k].equals("1"))
			//if(rs1.getString(array_days[k]).equals("1"))
			{
				list_of_days+=" "+array_days[k];
				list_of_days_arr[j]=list_of_days;
				list_of_days_next+=" "+array_days[k+1];
				list_of_days_arr_next[j]=list_of_days_next;
			}
			}
			list_of_days="";
			list_of_days_next="";
			}
			}
			//System.out.println("List of Days is_________________"+list_of_days);
			//System.out.println("List of Days is_________________"+list_of_days_next);
			
			
			
			
			
			String converter="";
			String val="";
			int counter=0;
			for(int j=0;j<train_size;j++)
				{
				converter=""+train_no[j];
					query="select * from "+converter+" where station_name='"+tte.getStation()+"'";
				rs = st.executeQuery(query);
				while(rs.next())
					{
					obj[j].setTrain(train_name_arr[j]);
					obj[j].setTrainNo(train_no[j]);
					System.out.println("train no:"+train_no[j]);
					String arriv=rs.getString("arrival").substring(2);
					obj[j].setArrival(arriv);
					System.out.println("arrival time"+arriv);
					val=rs.getString("departure");
					obj[j].setDeparture(val.substring(2));
					System.out.println("Departure"+val.substring(2));
				 	if(val.substring(0,1).equals("0"))
						{
						obj[j].setDay_val(list_of_days_arr[j]);
						}
						else
						{
						obj[j].setDay_val(list_of_days_arr_next[j]);
						}
					
				//	obj[j].setDay_val(val.substring(0,1));
					//System.out.println("day value"+val.substring(0,1));
					counter=j;	
						}
				}
			obj[0].setSize(counter);
			st.close();
			con.close();

			}	
				catch (Exception e) {
			System.out.println("Errors in reading " + e);
			//JOptionPane.showMessageDialog(null, "Train No. "+table_name+" is not Present", "alert", JOptionPane.ERROR_MESSAGE); 
			
		}
		return obj;

	}
}