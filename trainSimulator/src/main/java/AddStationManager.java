import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class AddStationManager {

	public AddStationManager(StationEntity se, int noc) throws SQLException {

		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		String query;
		ResultSet rs=null;
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
			String str1 = se.getStation_name();
			String str2 = se.getStation_code();
			int num1 = se.getXcod();
			int num2 = se.getYcod();
			int num3 = se.getStation_type();
			String str3 = se.getConn_station1();
			String str4 = se.getConn_station2();
			String str5 = se.getConn_station3();
			String arr_stations[] = new String[100];
			query = "Select * from Station_details";
			rs = st.executeQuery(query);
			int x=0;int y=0;int z=0;int name=0; int code=0;
			int counter=0;
			while (rs.next()) {
			arr_stations[counter]=rs.getString("station_name");
			System.out.println("_________in add station manager__________________________________________");
			if(str2.equals(rs.getString("station_code")))
			{
			code++;
			}
			else if(str1.equals(arr_stations[counter]))
			{
			name++;
			}
			counter++;
			}
			if(noc==1)
				{
				for(int i=0;i<counter;i++)
					{
					if(str3.equals(arr_stations[i]))
					{x++;} 
					}
				}
			else if(noc==2)
				{
				for(int i=0;i<counter;i++)
					{
					if(str3.equals(arr_stations[i]))
					{x++;} 
					}
				for(int i=0;i<counter;i++)
					{
					if(str4.equals(arr_stations[i]))
					{y++;} 
					}
			//	if(str3.equals(rs.getString("station_name"))) 	{ x++; }
			//else if(str4.equals(rs.getString("station_name")))  {y++;}
				}
			else if(noc==3)
			{
			for(int i=0;i<counter;i++)
					{
					if(str3.equals(arr_stations[i]))
					{x++;} 
					}
				for(int i=0;i<counter;i++)
					{
					if(str4.equals(arr_stations[i]))
					{y++;} 
					}
				for(int i=0;i<counter;i++)
					{
					if(str5.equals(arr_stations[i]))
					{z++;} 
					}
	//		if(str3.equals(rs.getString("station_name"))) 	{ x++; }
		//	else if(str4.equals(rs.getString("station_name")))  {y++;}
			//else if(str5.equals(rs.getString("station_name")))  {z++;}   
			
			} 
			int flag=0;
			System.out.println("___________________"+code+"__________"+name+"____________"+counter);
			if(code>0)
			{JOptionPane.showMessageDialog(null, "Station code is already present", "Error", JOptionPane.ERROR_MESSAGE);  flag=1;}
			else
			if(name>0)
			{JOptionPane.showMessageDialog(null, "Station name is already present", "Error", JOptionPane.ERROR_MESSAGE);flag=1; }
			
			else if(noc==1)
			{
			if(x==0)
			{JOptionPane.showMessageDialog(null, "connected station "+str3+" is not present", "Error", JOptionPane.ERROR_MESSAGE); flag=1;}
			}
			else if(noc==2)
			{
			if(x==0)
			{JOptionPane.showMessageDialog(null, "connected station "+str3+" is not present", "Error", JOptionPane.ERROR_MESSAGE);flag=1;}
			if(y==0) 
			{JOptionPane.showMessageDialog(null, "connected station "+str4+" is not present", "Error", JOptionPane.ERROR_MESSAGE);flag=1;}
			}
			else if(noc==3)
			{
			if(x==0)
			{JOptionPane.showMessageDialog(null, "connected station "+str3+" is not present", "Error", JOptionPane.ERROR_MESSAGE);flag=1;}
			if(y==0)
			{JOptionPane.showMessageDialog(null, "connected station "+str4+" is not present", "Error", JOptionPane.ERROR_MESSAGE);flag=1;}
			if(z==0)
			{JOptionPane.showMessageDialog(null, "connected station "+str5+" is not present", "Error", JOptionPane.ERROR_MESSAGE);flag=1;}
			}
			
			if(flag==0)			{
			
			query = "insert into station_details(station_code,station_name,xcod,ycod,station_type,conn_st_1,conn_st_2,conn_st_3) values ('"
					+ str2
					+ "','"
					+ str1
					+ "','"
					+ num1
					+ "','"
					+ num2
					+ "','"
					+ num3 + "','" + str3 + "','" + str4 + "','" + str5 + "')";
			System.out.println(query);
			int value = st.executeUpdate(query);
			System.out.println("after execute No of rows updated" + value);
				
				JOptionPane.showMessageDialog(null, "Station "+str1+" added successfully", "Information", JOptionPane.INFORMATION_MESSAGE); 
				
				}
		} catch (Exception e) {
			System.out.println("Errors in reading " + e);

		} finally {
			st.close();
			con.close();
		}
	}
}
