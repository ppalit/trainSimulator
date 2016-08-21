import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DisplayTrainTimeManager {

	public TrainTimeEntity[] display(TrainTimeEntity tte) {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String query;
		TrainTimeEntity obj[] = new TrainTimeEntity[200];
		String table_name=""+tte.getTrainNo();
		String array_days[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun"}; 
		String array_days_running[]=new String[7];
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

			query = "select * from runningdays where train_no="+tte.getTrainNo();
			System.out.println(query);
			rs1 = st.executeQuery(query);
			while (rs1.next()){
			System.out.println("ok_________________");
				array_days_running[0]=rs1.getString("sun");
				array_days_running[1]=rs1.getString("mon");
				array_days_running[2]=rs1.getString("tue");
				array_days_running[3]=rs1.getString("wed");
				array_days_running[4]=rs1.getString("thu");
				array_days_running[5]=rs1.getString("fri");
				array_days_running[6]=rs1.getString("sat");
							 }		
			String list_of_days= "";
			String list_of_days_next="";
			for(int i=0;i<7;i++)
			{System.out.println("day is "+array_days_running[i]);
			if(array_days_running[i].equals("1"))
			{
				list_of_days+=" "+array_days[i];
				
				list_of_days_next+=" "+array_days[i+1];
			}
			}
			System.out.println("List of Days is_________________"+list_of_days);
			System.out.println("List of Days is_________________"+list_of_days_next);
			
			
			
			query = "select * from "+table_name;
			rs = st.executeQuery(query);
			System.out.println(query);
			int i = 0;
			String val="";
			while (rs.next()) {

				obj[i].setStation(rs.getString("station_name"));
				obj[i].setArrival(rs.getString("arrival").substring(2));
				val=rs.getString("departure");
				obj[i].setDeparture(val.substring(2));
				if(val.substring(0,1).equals("0"))
				{
				obj[i].setDay_val(list_of_days);
				}
				else
				{
				obj[i].setDay_val(list_of_days_next);
				}
				
				/*String days="";
				
				System.out.println("ok1_________________");
				if(val.substring(0,1).equals("0"))
				{
				
				for(int j=0;j<7;j++)
				{
				System.out.println("ok1_______"+i+"_________");
				if(array_days_running[i].equals("1"))
					{
						days=days+","+array_days[i];
					}
				}
				obj[i].setDay_val(days);
				
				}*/
				i++;  }
			obj[0].setSize(i);
			st.close();
			con.close();

		}
		
		catch (Exception e) {
			System.out.println("Errors in reading " + e);
			JOptionPane.showMessageDialog(null, "Train No. "+table_name+" is not Present", "alert", JOptionPane.ERROR_MESSAGE); 
			
		}
		return obj;

	}
}
