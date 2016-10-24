package dev.zero.trainSimulator.model;
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
	public double x;
	/**
	 * Y coordinate of the node
	 */
	public double y;

	public double tempX;
	public double tempY;
	public int fontWidth;
	public int fontHeight;

	public boolean fixed;
	public boolean selected;
	public boolean moved;

	public boolean inSimulation;
	public boolean showTable;

	/**
	 * mode in which the node is being accessed 1 if node is being added 2 if it
	 * is being searched 3 if it is candidate for selection 4 if it is selected
	 * for a route
	 */
	public int mode;
	/**
	 * Name of the node
	 */
	public String lbl;

	int type;
	/**
	 * type = 1 for junction type = 2 for commercial stop type = 3 for halt
	 */
}