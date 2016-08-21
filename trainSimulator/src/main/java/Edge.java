public class Edge {
	public int from;
	public int to;

	double len;
	public String stationFrom;
	public String stationTo;
	/**
	 * 1 for single 2 for double track 3 for triple
	 */
	public int noOfTrack;
	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @return the len
	 */
	public double getLen() {
		return len;
	}
	/**
	 * @return the noOfTrack
	 */
	public int getNoOfTrack() {
		return noOfTrack;
	}
	/**
	 * @return the stationFrom
	 */
	public String getStationFrom() {
		return stationFrom;
	}
	/**
	 * @return the stationTo
	 */
	public String getStationTo() {
		return stationTo;
	}
	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}
	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @param len
	 *            the len to set
	 */
	public void setLen(double len) {
		this.len = len;
	}
	/**
	 * @param noOfTrack
	 *            the noOfTrack to set
	 */
	public void setNoOfTrack(int noOfTrack) {
		this.noOfTrack = noOfTrack;
	}
	/**
	 * @param stationFrom
	 *            the stationFrom to set
	 */
	public void setStationFrom(String stationFrom) {
		this.stationFrom = stationFrom;
	}
	/**
	 * @param stationTo
	 *            the stationTo to set
	 */
	public void setStationTo(String stationTo) {
		this.stationTo = stationTo;
	}
	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}

}