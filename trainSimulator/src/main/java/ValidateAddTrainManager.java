import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ValidateAddTrainManager {

	public ValidateAddTrainManager(){}	
		
		
	public int valid(int routeNo, int trainNo ) throws SQLException {
	
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		String query;
		ResultSet rs=null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			System.out.println("Driver present");
		} catch (Exception ex) {
			System.out.println("Can't load driver:");
								}
	try {

			con = DriverManager.getConnection(dbURL, "", "");
			System.out.println("Connected to database  successfully");
			st = con.createStatement();
			//validation_________________________________________________________Starts from here
			
			query = "Select * from train";
			rs = st.executeQuery(query);
			int train_check=0;
			while (rs.next()) {
			if(trainNo == Integer.parseInt(rs.getString("train_no")))
			{
			train_check++;
			}
							 }
			if(train_check>0)
			{
			JOptionPane.showMessageDialog(null, "Train having number:"+trainNo+" is already available" , "Error", JOptionPane.ERROR_MESSAGE); 
			return 1;
			}
			
			query = "Select * from routes";
			rs = st.executeQuery(query);
			int j=0;
			while (rs.next()) {
			if(routeNo == Integer.parseInt(rs.getString("route_no")))
			{
			j++;
			}
							 }
			if(j==0)
			{
			JOptionPane.showMessageDialog(null, "Route having number:"+routeNo+" is mot present", "Error", JOptionPane.ERROR_MESSAGE); 
			return 1;
			}
			
			st.close();
			con.close();
			
			
		}
		catch (Exception er) {
			System.out.println("Error in station name:"+er);
		JOptionPane.showMessageDialog(null, "Error in station name:"+er, "Error", JOptionPane.ERROR_MESSAGE); 
					}
						
				return 0;
				}
				
			}