/**
 * 
 */

/**
 * @author Priyabrata
 * 
 */
public class ScheduleEntity {
	public int stationIndex;
	public int trainNo;
	public String stationName;
	public String arrivalTime;
	public String departureTime;

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * @return the stationIndex
	 */
	public int getStationIndex() {
		return stationIndex;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @return the trainNo
	 */
	public int getTrainNo() {
		return trainNo;
	}

	/**
	 * @param arrivalTime
	 *            the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime.trim();
	}

	/**
	 * @param departureTime
	 *            the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime.trim();
	}

	/**
	 * @param stationIndex
	 *            the stationIndex to set
	 */
	public void setStationIndex(int stationIndex) {
		this.stationIndex = stationIndex;
	}

	/**
	 * @param stationName
	 *            the stationName to set
	 */
	public void setStationName(String stationName) {

		this.stationName = stationName.trim();
	}

	/**
	 * @param trainNo
	 *            the trainNo to set
	 */
	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

}
