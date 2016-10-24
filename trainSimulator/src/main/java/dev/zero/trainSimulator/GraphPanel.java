package dev.zero.trainSimulator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import dev.zero.trainSimulator.dao.AddEdgeManager;
import dev.zero.trainSimulator.dao.RouteManager;
import dev.zero.trainSimulator.dao.TrainManager;
import dev.zero.trainSimulator.model.Edge;
import dev.zero.trainSimulator.model.Node;
import dev.zero.trainSimulator.model.RouteEntity;
import dev.zero.trainSimulator.model.StationEntity;
import dev.zero.trainSimulator.model.TimeCal;
import dev.zero.trainSimulator.model.TimeEntity;
import dev.zero.trainSimulator.model.TrainEntity;
import dev.zero.trainSimulator.view.DisplayStationTimeTable;

/**
 * @author Priyabrata & Siddhartha
 * @version 3.1
 */
/**
 * This class is used as the base class for other functions, it it the graphical
 * interface of the software
 */
public class GraphPanel extends JPanel
		implements
			MouseListener,
			MouseMotionListener,
			Runnable,
			ActionListener {

	private static final long serialVersionUID = 8433538089930438089L;
	/** Stores the total number of nodes present on the graph at any time */
	public int nnodes;
	/** Array of All the nodes */
	public Node nodes[] = new Node[100];
	/** Array of stations of the route for the corresponding trainIndex */
	public Node stations[][] = new Node[50][50];
	/** The thread of train which, each thread representing individual trains */
	private Thread train;
	/** Total number of edges or physical links between stations present */
	private int nedges;
	/** Array of edges */
	private Edge edges[] = new Edge[200];
	/** Array to store the nodes that are selected while creating a new route */
	public int nodesSelected[] = new int[50];
	/** To count the number of nodes selected for selecting Route */
	public int counterNodeSelection = 0;
	/** X coordinate of the nodes displayed on the Map */
	private int nodeXcod[] = new int[500];
	/** Y coordinate of the nodes displayed on the Map */
	private int nodeYcod[] = new int[500];
	/** position of train */
	public int x3[], y3[];
	/** Object of addEdgeManager class that adds tracks to the map and database */
	private AddEdgeManager addEdgeMgr = new AddEdgeManager();
	/** Shows the train names when true during simulation */
	public boolean showTrainName = false;
	/** Shows the train names when true during simulation */
	public boolean showTrainNo = false;
	/** Node that is selected from the display map */
	private Node pick;

	boolean pickfixed;

	public Image offscreen;

	Dimension offscreensize;

	Graphics offgraphics;

	Graphics2D gr2D;

	final Color background = Color.white;

	final Color fixedColor = new Color(150, 167, 237);

	final Color selectColor = Color.pink;
	final Color candidateColor = Color.blue;
	final Color selectRouteColor = new Color(185, 248, 139);
	final Color trainColor = Color.GRAY;
	final Color edgeColor = Color.pink;

	final Color nodeColor = new Color(250, 220, 100);
	final Color arcColor1 = Color.black;
	final Color arcColor2 = Color.pink;
	final Color arcColor3 = Color.red;
	// final Color nodeInSimulation1 = Color.ORANGE;
	final Color nodeInSimulation = Color.MAGENTA;
	BasicStroke stationBorder = new BasicStroke(1.0f);
	BasicStroke singleTrack = new BasicStroke(1.0f);
	BasicStroke doubleTrack = new BasicStroke(2.0f);
	BasicStroke tripleTrack = new BasicStroke(3.0f);

	StationEntity se[] = new StationEntity[200];
	TrainEntity trainEntity[] = new TrainEntity[200];
	RouteEntity routeEntity[] = new RouteEntity[200];

	StationManager sm = new StationManager();
	RouteManager routeManager = new RouteManager();
	TrainManager trainManager = new TrainManager();

	TrainEntity tr;
	RouteEntity rt;
	public TimeEntity timeEnt[];
	int factor[];
	String str = "";

	int trainNo = 0;

	int no_of_trains;
	// TimeEntity timeEntity ;//= new TimeEntity();
	int testVar;
	/** 0 for Monday 1 for Tuesday --- 6 for Sunday */
	int day = 0;

	GraphPanel() {

		addMouseListener(this);

		factor = new int[100];
		x3 = new int[100];
		y3 = new int[100];
		timeEnt = new TimeEntity[100];
		// timeEntity = new TimeEntity();
		testVar = 0;

		for (int i = 0; i < x3.length; i++) {
			x3[i] = -10;
			y3[i] = -10;
			// timeEnt[i] = new TimeEntity();

		}

		System.out.println("in constructor of GraphPanel");

		se = sm.insertSM();
		trainEntity = trainManager.insertTM();
		routeEntity = routeManager.insertRM();
		for (int i = 0; i < trainEntity[0].size; i++) {
			timeEnt[i] = new TimeEntity(trainEntity[i].train_no);
			System.out.println("train start time=" + trainEntity[i].train_no
					+ " time= " + timeEnt[i].startTime.convertDateToString()
					+ ";");
		}
		// timeEntity=timeManager.insertTime();
		if (se[0].getXcod() != 0) {
			for (int i = 0; i < se[0].count; i++) {

				System.out.println("station cordinates" + se[i].getXcod()
						+ "\n");
				this.addNode(se[i].getStation_name(), se[i].getXcod(), se[i]
						.getYcod());

			}
		}

		if (se[0].getXcod() != 0) {
			for (int i = 0; i < se[0].count; i++) {
				if (se[i].getStation_type() == 1) {
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station1());
				} else if (se[i].getStation_type() == 2) {
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station1());
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station2());
				} else if (se[i].getStation_type() == 3) {
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station1());
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station2());
					this.addEdge(se[i].getStation_name(), se[i]
							.getConn_station3());
				}

			}

		}

		setVisible(true);

		repaint();

	}

	void addEdge(String from, String to) {
		Edge e = new Edge();
		e.from = findNode(from);
		e.to = findNode(to);

		if (addEdgeMgr.checkPresent(from, to)) {
			e.noOfTrack = addEdgeMgr.getNoOfTrack(from, to);
		} else {
			addEdgeMgr.setTrack(from, to);// adds new entry of edge details to
			// database
			e.noOfTrack = addEdgeMgr.getNoOfTrack(from, to);
		}
		System.out.println("no. of tracks in the connection:" + nodes[e.to].lbl
				+ "--" + nodes[e.from].lbl + "=" + e.noOfTrack);

		e.len = (int) Math.abs(Math.sqrt((nodes[e.from].x - nodes[e.to].x)
				* (nodes[e.from].x - nodes[e.to].x)
				+ (nodes[e.from].y - nodes[e.to].y)
				* (nodes[e.from].y - nodes[e.to].y)));
		edges[nedges++] = e;

	}

	int addNode(String lbl) {

		Node n = new Node();

		n.x = nodeXcod[nnodes];
		n.y = nodeYcod[nnodes];
		n.tempX = n.x;
		n.tempY = n.y;
		n.lbl = lbl;
		n.fixed = true;
		n.moved = false;
		n.showTable = false;
		n.mode = 2;
		nodes[nnodes] = n;
		System.out.println("in int addNode" + nnodes);
		return nnodes++;
	}

	void addNode(String lbl, int xPos, int yPos) {
		Node n = new Node();
		n.x = xPos;
		n.y = yPos;

		n.tempX = n.x;
		n.tempY = n.y;

		n.lbl = lbl;
		n.fixed = true;
		n.moved = false;
		n.showTable = false;
		nodes[nnodes] = n;
		n.mode = 1;
		System.out.println("in void addNode" + nnodes);
		nodeXcod[nnodes] = (int) n.x;
		nodeYcod[nnodes] = (int) n.y;
		nnodes++;

	}

	public void findNextNode(String nodeLbl) {
		System.out.println("the present selected node is " + nodeLbl);
		int nodeSel = findNode(nodeLbl);
		System.out.println("the node number is " + nodeSel);
		nodes[nodeSel].mode = 4;
		nodesSelected[counterNodeSelection++] = nodeSel;
		System.out.println("the nodes which have already been selected..");
		for (int i = 0; i < counterNodeSelection; i++) {
			nodes[nodesSelected[i]].mode = 4;
			System.out.println(nodes[nodesSelected[i]].lbl);

		}

		for (int i = 0; i < nnodes; i++) {
			if (nodes[i].mode != 4) {
				nodes[i].mode = 2;
			}
		}
		System.out
				.println("the next possible nodes for " + nodeLbl + " are...");
		for (int i = 0; i < nedges; i++) {
			System.out.println("the edges " + edges[i].from + "--"
					+ edges[i].to);
			if (edges[i].from == nodeSel) {

				if (nodes[edges[i].to].mode != 4) {
					nodes[edges[i].to].mode = 3;
					System.out.println(nodes[edges[i].to].lbl);
				}

			}
			if (edges[i].to == nodeSel) {
				// arr[count++]= edges[i].from;
				if (nodes[edges[i].from].mode != 4) {
					nodes[edges[i].from].mode = 3;
					System.out.println(nodes[edges[i].from].lbl);
				}

			}

		}

	}

	public int findNode(String lbl) {
		for (int i = 0; i < nnodes; i++) {
			if (nodes[i].lbl.equals(lbl))
				return i;
		}
		return addNode(lbl);
	}

	int matchTrainRoute(TrainEntity trainEnt) {
		// JOptionPane.showMessageDialog(this, "train name.."+train.train_name);
		// System.out.println("no of trains="+routeEntity[0].count);
		for (int i = 0; i < routeEntity[0].count; i++) {
			// System.out.println("train route="+trainEnt.route_number +"route
			// no="+routeEntity[i].route_number);
			if (trainEnt.route_number == routeEntity[i].route_number)
				// JOptionPane.showMessageDialog(this, "train
				// name.."+train.train_name);
				return i;

		}

		return -1;
	}

	// 1.1 event handling
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		if (pick.fixed == false) {
			pick.x = e.getX();
			pick.y = e.getY();
			pick.moved = true;
		}
		repaint();
		e.consume();
	}

	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse entered");
		repaint();
		// paintTrain(Graphics g);
	}

	public void mouseExited(MouseEvent e) {
		repaint();
	}

	// public void mouseReleased(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		// numMouseButtonsDown++;
		addMouseMotionListener(this);
		// double bestdist = Double.MAX_VALUE;

		int x = e.getX();
		int y = e.getY();

		for (int i = 0; i < nnodes; i++) {
			Node n = nodes[i];

			int w = n.fontWidth;
			int h = n.fontHeight;
			// double dist = n.x-//(n.x - x) * (n.x - x) + (n.y - y) * (n.y -
			// y);
			if ((x >= (n.x - (w / 2))) && (x <= (n.x + (w / 2)))
					&& (y >= (n.y - (h / 2))) && (y <= (n.y + (h / 2)))) {

				// if (dist < bestdist) {
				// System.out.println(x+" , "+y);
				pick = n;
				pickfixed = pick.fixed;
				if(pick.showTable == true){
						new DisplayStationTimeTable(pick.lbl);
				}
				
			
				// pick.fixed = true;
				if (pick.fixed == false) {
					pick.x = x;
					pick.y = y;
					pick.moved = true;
				}
				// bestdist = dist;
			}
		}
		// System.out.println(x+" , "+y);

		repaint();
		e.consume();
	}

	public void mouseReleased(MouseEvent e) {
		// numMouseButtonsDown--;
		// System.out.println(pick.fixed);
		try {
			removeMouseMotionListener(this);
			if (pick.mode == 3) {
				this.findNextNode(pick.lbl);
				pick.mode = 4;
			}

			if (pick.fixed == false) {
				pick.fixed = pickfixed;
				pick.x = e.getX();
				pick.y = e.getY();
				pick.moved = true;
			}
			// repaint();
			// if (numMouseButtonsDown == 0) {
			// pick = null;
			// }

			repaint();
		} catch (Exception exp) {
		} finally {
			e.consume();
		}
	}

	int nodeNo(String nodeLbl) {
		for (int i = 0; i < nnodes; i++) {
			if (nodes[i].lbl.equals(nodeLbl))
				return i;
		}
		return -1;
	}

	@Override
	public synchronized void paint(Graphics g) {
		System.out.println("in paint of GraphPanel");
		Dimension d = getSize();

		if ((offscreen == null) || (d.width != offscreensize.width)
				|| (d.height != offscreensize.height)) {
			offscreen = createImage(d.width, d.height);
			offscreensize = d;
			if (offgraphics != null) {
				offgraphics.dispose();
			}

			offgraphics = offscreen.getGraphics();
			offgraphics.setFont(getFont());
		}

		offgraphics.setColor(background);// getBackground());

		offgraphics.fillRect(0, 0, d.width, d.height);
		System.out.println(nedges);

		paintTrack((Graphics2D) offgraphics);

		FontMetrics fm = offgraphics.getFontMetrics();
		for (int i = 0; i < nnodes; i++) {
			paintNode((Graphics2D) offgraphics, nodes[i], fm);
		}
		paintTrain(g);

		g.drawImage(offscreen, 0, 0, null);
	}
	public void paintNode(Graphics2D g, Node n, FontMetrics fm) {
		int x = (int) n.x;
		int y = (int) n.y;
		// System.out.println(n.lbl+","+x+","+y);

		g.setStroke(stationBorder);
		if (n.inSimulation == true) {
			g.setColor(nodeInSimulation);
		}
		if (n == pick) {
			if (n.mode == 3) {
				g.setColor(candidateColor);
			}
			if (n.mode == 4) {
				g.setColor(selectRouteColor);
			}
			if ((n.mode == 1) || (n.mode == 2)) {
				g.setColor(selectColor);
			}

		} else {
			if (n.fixed == true) {
				if (n.mode == 3) {
					g.setColor(candidateColor);
				}
				if (n.mode == 4) {
					g.setColor(selectRouteColor);
				}
				if ((n.mode == 1) || (n.mode == 2)) {
					g.setColor(fixedColor);
				}

			} else {
				if (n.mode == 3) {
					g.setColor(candidateColor);
				}
				if (n.mode == 4) {
					g.setColor(selectRouteColor);
				}
				if ((n.mode == 1) || (n.mode == 2)) {
					g.setColor(nodeColor);
				}

			}

		}

		int w = fm.stringWidth(n.lbl) + 10;
		int h = fm.getHeight() + 4;
		n.fontWidth = w;
		n.fontHeight = h;

		g.fill(new RoundRectangle2D.Double(x - w / 2, y - h / 2, w, h, 10, 10));

		// g.fillRect(x - w / 2, y - h / 2, w, h);
		g.setColor(Color.black);
		g.draw(new RoundRectangle2D.Double(x - w / 2, y - h / 2, w, h, 10, 10));
		// g.drawRect(x - w / 2, y - h / 2, w - 1, h - 1);
		g.drawString(n.lbl, x - (w - 10) / 2, (y - (h - 4) / 2)
				+ fm.getAscent());
	}
	void paintTrack(Graphics2D g2) {
		for (int i = 0; i < nedges; i++) {
			Edge e = edges[i];
			int x1 = (int) nodes[e.from].x;
			int y1 = (int) nodes[e.from].y;
			int x2 = (int) nodes[e.to].x;
			int y2 = (int) nodes[e.to].y;
			int len = (int) Math.abs(Math.sqrt((x1 - x2) * (x1 - x2)
					+ (y1 - y2) * (y1 - y2))); // - e.len);
			g2.setColor((len < 10) ? arcColor1 : (len < 20
					? arcColor2
					: arcColor3));

			switch (e.noOfTrack) {
				case 1 :
					g2.setStroke(singleTrack);
					break;
				case 2 :
					g2.setStroke(doubleTrack);
					break;
				case 3 :
					g2.setStroke(tripleTrack);
					break;
			}

			g2.setColor(edgeColor);
			g2.drawLine(x1, y1, x2, y2);

			String lbl = String.valueOf(len);

			g2.setColor(Color.BLACK);
			g2.drawString(lbl, x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2);
			// offgraphics.drawString(""+len,(int)Math.abs(x2-x1)/2,(int)Math.abs(y2-y1)/2);

		}

	}
	public TimeCal tc1 = new TimeCal(0, 0, 0, 0);
	public TimeCal tc2 = new TimeCal(20, 40, 0, 0);
	public TimeCal tc3 = new TimeCal();
	int delay = 200;
	// Timer t= new Timer(delay,taskPerformer);
	// ActionListener taskPerformer = new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		this.repaint();
		tc1.addTime(0, 1, 0, 0);
		tc2.addTime(0, 0, 1, 0);
		this.repaint();
	}
	// };
	Timer timer = new Timer(delay, this);

	void initialSetTime(TimeCal tc1, TimeCal tc2) {

		JOptionPane.showMessageDialog(this, "in initialSetTime "
				+ this.timeEnt[5].timeArr[4] + " x3=" + this.timeEnt[5].xPos[4]
				+ "--" + this.timeEnt[5].timeArr[6] + " x3="
				+ this.timeEnt[5].xPos[6]);
		for (int i = 0; i < trainEntity[0].size; i++) {
			// JOptionPane.showMessageDialog(this, "in initialSetTime "+i
			// +"size= "+timeEnt[i].pointer);
			for (int j = 0; j < timeEnt[i].pointer; j++) {
				// System.out.println("train No"+timeEnt[i].trainNo+" , j
				// ="+j +"time = "+
				// this.timeEnt[i].timeArr[j].convertDateToString()+"
				// xPos"+this.timeEnt[i].xPos[j]);
				TimeCal test = new TimeCal(timeEnt[i].timeArr[j]);
				if (test.valueOfDate() < tc1.valueOfDate())
					timeEnt[i].pointerPos = j;
			}
		}
	}

	TimeCal tc4;
	void paintTrain(Graphics g) {

		try {
			
			tc3 = new TimeCal(tc1.convertDateToString());
			tc4= new TimeCal(tc1.convertDateToString());
			int st= tc1.getDay();
			tc4= new TimeCal(tc1.hour, tc1.min,this.day + st);
			String dayStr = "";
			switch (tc4.getDay()) {
				case 0 :
					dayStr = "Mon";
					break;
				case 1 :
					dayStr = "Tue";
					break;
				case 2 :
					dayStr = "Wed";
					break;
				case 3 :
					dayStr = "Thu";
					break;
				case 4 :
					dayStr = "Fri";
					break;
				case 5 :
					dayStr = "Sat";
					break;
				case 6 :
					dayStr = "Sun";
					break;

			}

			offgraphics.setColor(Color.BLACK);
			offgraphics.fillRect(0, 0, 70, 30);
			offgraphics.setColor(Color.RED);
			offgraphics.drawString(dayStr + ":" + tc3.getHour() + " : "
					+ tc3.getMin(), 7, 20);
			// + fm.getAscent());
			// tc3.addTime(0, 1, 0, 0);
			// JOptionPane.showMessageDialog(this, " present time="
			// +tc3.convertDateToString());
			System.out.println(" present time=" + tc3.convertDateToString());
			for (int i = 0; i < trainEntity[0].size; i++) {
				if (trainEntity[i].days_of_run[tc4.getDay()] == 1) {

					this.timeEnt[i].pointerPos = 0;

					for (int j = 0; j <= timeEnt[i].pointer; j++) {
						// System.out.println(" time of train
						// No("+timeEnt[i].trainNo+") = "+
						// this.timeEnt[i].timeArr[j]);
						if ((this.timeEnt[i].timeArr[j]).equals(tc3
								.convertDateToString())) {
							this.timeEnt[i].pointerPos = j;
						}
					}
					if (trainEntity[i].train_speed_no == 120) {
						offgraphics.setColor(Color.YELLOW);
						// System.out.println("in train speed 120=");
					} else {
						if (trainEntity[i].train_speed_no == 100) {
							offgraphics.setColor(Color.GREEN);
						} else {
							if (trainEntity[i].train_speed_no == 80) {
								offgraphics.setColor(Color.RED);
							}
						}
					}
					// System.out.println("color =" + offgraphics.getColor());
					offgraphics.fillOval(
							this.timeEnt[i].xPos[this.timeEnt[i].pointerPos],
							this.timeEnt[i].yPos[this.timeEnt[i].pointerPos],
							10, 10);
					timeEnt[i].pointerPos++;
					// offgraphics.fillOval(x3[i], y3[i], 10, 10);
					if (showTrainNo == true) {
						str = "," + trainEntity[i].train_no;
					}
					if (showTrainName == true) {
						offgraphics.setColor(Color.BLACK);
						offgraphics
								.drawString(
										trainEntity[i].train_name + str,
										this.timeEnt[i].xPos[this.timeEnt[i].pointerPos]
												+ factor[i],
										this.timeEnt[i].yPos[this.timeEnt[i].pointerPos]
												+ factor[i]);
						offgraphics
								.drawLine(
										this.timeEnt[i].xPos[this.timeEnt[i].pointerPos],
										this.timeEnt[i].yPos[this.timeEnt[i].pointerPos],
										this.timeEnt[i].xPos[this.timeEnt[i].pointerPos]
												+ factor[i],
										this.timeEnt[i].yPos[this.timeEnt[i].pointerPos]
												+ factor[i]);
					}
				}
				// tc2.addTime(0, 1, 0);
				// }
			}
		} catch (NullPointerException e) {
			System.out.println("null pointer train");

		}

	}
	int numb = 0;
	public void run() {
		no_of_trains = trainEntity[0].size;
		System.out.println("No of trains++++++++++++++++++++++++++"
				+ no_of_trains);
		while (trainNo < no_of_trains) {
			tr = trainEntity[trainNo];
			System.out.println("train no++++++++++++++++++++++++++" + trainNo);

			System.out
					.println("train index------------------------------------"
							+ trainEntity[trainNo].route_number);

			rt = routeEntity[matchTrainRoute(trainEntity[trainNo])];
			factor[trainNo] = (Math.round((float) Math.random() * 100));
			train = new TrainDetails(trainEntity[trainNo],
					routeEntity[matchTrainRoute(trainEntity[trainNo])],
					trainNo, this);
			numb++;
			try {
				// train[trainNo].join();
				repaint();
				Thread.sleep(120);

			} catch (Exception e) {
			}
			// Thread.join();
			trainNo++;
			// JOptionPane.showMessageDialog(this, "thread execution over!!");
		}
	}
	void traceTrain() {

		run();

		if (numb == trainEntity[0].size) {
			//String inputValue = JOptionPane
			//		.showInputDialog("Please input a value");
			//if (inputValue.equals("yes")) {
				// initialSetTime(new TimeCal(19, 20, 0, 0), new TimeCal(22, 20,
				// 0, 0));
			
			
			JOptionPane.showMessageDialog(null, "Please wait while Simulation is done...", " Simulation",JOptionPane.INFORMATION_MESSAGE);
				timer.start();
			//}
		}

	}
	

}
