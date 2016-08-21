/**
 * Specifies individual stations
 * 
 * @author Priyabrata
 * @version 1.2.1
 */
public class Node extends StationEntity {
	/**
	 * X coordinate of the node
	 */
	double x;
	/**
	 * Y coordinate of the node
	 */
	double y;

	double tempX;
	double tempY;
	int fontWidth;
	int fontHeight;

	boolean fixed;
	boolean selected;
	boolean moved;

	boolean inSimulation;
	boolean showTable;

	/**
	 * mode in which the node is being accessed 1 if node is being added 2 if it
	 * is being searched 3 if it is candidate for selection 4 if it is selected
	 * for a route
	 */
	int mode;
	/**
	 * Name of the node
	 */
	String lbl;

	int type;
	/**
	 * type = 1 for junction type = 2 for commercial stop type = 3 for halt
	 */
}