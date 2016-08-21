import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author Priyabrata
 * 
 */
public class TimeEntity {
	public int trainNo;
	public int daysRun[];
	public String timeStart;
	public TimeCal startTime;
	public String timeEnd;
	public TimeCal endTime;
	public int size;
	public int pointerPos;
	public int xPos[];
	public int yPos[];
	public String timeArr[]=new String[100000];
	public int pointer;
	
	
	public TimeEntity(int tNo){
		String dbURL = "jdbc:odbc:TSA";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		this.trainNo= tNo;
		
		this.xPos= new int[100000];
		this.yPos= new int[100000];
		//this.timeArr = new TimeCal[100000];
		this.pointer = 0;
		
		
		for(int i=0; i< 10000; i++){
			this.timeArr[i]= "";
			this.xPos[i]=-10;
			this.yPos[i]=-10;
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
			query = "select arrival, departure from "+tNo+" where stationIndex=0";
			System.out.println(query);
			rs = st.executeQuery(query);
			String str1, str2;
			while (rs.next()) {
				System.out.println("hello........>>>>>>>>>>>>>>>>");
				str1=rs.getString("departure");
				System.out.println(str1);
				str1=str1.trim();
				this.setTimeStart(str1);
				str2= rs.getString("arrival");
				str2=str2.trim();
			//	System.out.println("arrival time="+str2+";");
				this.setStartTime(new TimeCal(str2));
				//System.out.println("start time is="+startTime.convertDateToString()+";");

			}
			st.close();
			con.close();
		}
	 catch(Exception e) {
		System.out.println("Errors in reading ");
	}
}
	public TimeEntity(){
		
	}
public String getTimeStart() {
	return timeStart;
}
/**
 * @return the startTime
 */
public TimeCal getStartTime() {
	return startTime;
}
/**
 * @return the timeEnd
 */
public String getTimeEnd() {
	return timeEnd;
}
/**
 * @return the endTime
 */
public TimeCal getEndTime() {
	return endTime;
}
/**
 * @return the trainNo
 */
public int getTrainNo() {
	return trainNo;
}
/**
 * @return the daysRun
 */
public int[] getDaysRun() {
	return daysRun;
}
/**
 * @param trainNo
 *            the trainNo to set
 */
public void setTrainNo(int trainNo) {
	this.trainNo = trainNo;
}
/**
 * @param daysRun
 *            the daysRun to set
 */
public void setDaysRun(int[] daysRun) {
	this.daysRun = daysRun;
}
/**
 * @param timeStart
 *            the timeStart to set
 */
public void setTimeStart(String timeStart) {
	this.timeStart = timeStart;
}
/**
 * @param startTime
 *            the startTime to set
 */
public void setStartTime(TimeCal startTime) {
	this.startTime = startTime;
}
/**
 * @param timeEnd
 *            the timeEnd to set
 */
public void setTimeEnd(String timeEnd) {
	this.timeEnd = timeEnd;
}
/**
 * @param endTime
 *            the endTime to set
 */
public void setEndTime(TimeCal endTime) {
	this.endTime = endTime;
}

}
