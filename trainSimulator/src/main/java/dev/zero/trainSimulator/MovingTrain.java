package dev.zero.trainSimulator;

import dev.zero.trainSimulator.model.Node;

public class MovingTrain extends Thread {
	/**
	 * it will create a train moving between 2 nodes
	 */
	private static final long serialVersionUID = 1L;
	GraphPanel panel;
	int x1;
	int x2;
	int x3 = 0;
	int y1;
	int y2;
	int y3 = 0;
	double dx;
	double dy;
	int length;
	int flag = 0;
	int i = 0;
	int speed;
	int train;
	String station_through[];
	String station_stop[];
	Node stations[];
	String stationNames[];
	int trainIndex;
	double sineTheta;
	double tanTheta;
	double cosTheta;
	int l=0;
	double multiplyingfactor;
	
	public MovingTrain(Node n1, Node n2, GraphPanel pnl, int trainIndex,
			int prior) {
		System.out.println("in moving train constructor");
		this.trainIndex = trainIndex;
		panel = pnl;
		speed = prior;
		x1 = (int) n1.x;
		y1 = (int) n1.y;
		x2 = (int) n2.x;
		y2 = (int) n2.y;
		length = Math.abs(x2 - x1);
		if (Math.abs(y2 - y1) > length) {
			length = Math.abs(y2 - y1);
		}
		dx = (double) (x2 - x1) / (double) length;
		dy = (double) (y2 - y1) / (double) length;

		multiplyingfactor = Math.sqrt((dx * dx) + (dy * dy));
		System.out.println("x2=" + x2 + "y2=" + y2 + "x1=" + x1 + "y1=" + y1);
		System.out.println("dx=" + dx + "dy=" + dy);
		this.start();

	}

	public void nodeToNodeTraversal() {
		// System.out.println("in movin train nodeToNodeTraversal method x3=" +
		// x3 + "y3=" + y3);
		x3 = (int) (x1 + (i * dx) + 0.5);
		y3 = (int) (y1 + (i * dy) + 0.5);
		// JOptionPane.showMessageDialog(panel,"in nodeToNodeTraversal \n
		// x3,y3="+x3+","+y3);
		panel.x3[trainIndex] = x3;
		panel.y3[trainIndex] = y3;
		l= panel.timeEnt[trainIndex].pointer;
		panel.timeEnt[trainIndex].xPos[l] = x3 ;
		panel.timeEnt[trainIndex].yPos[l] = y3 ;
	//	(panel.timeEnt[trainIndex]).timeArr[l] = (panel.timeEnt[trainIndex]).startTime.convertDateToString();
	//	System.out.println("-----"+ panel.timeEnt[trainIndex].startTime.convertDateToString()+ "++++++++"+ panel.timeEnt[trainIndex].timeArr[l]);
		
		panel.timeEnt[trainIndex].pointer++;
		System.out.println(" Train Index= "+trainIndex +" Time="+panel.timeEnt[trainIndex].timeArr[l]+" pointer pos="+ l);
		
		//panel.repaint();
	}
	
	@Override
	public void run() {
		// Thread train= Thread.currentThread();
		System.out.println("in moving train run length=" + length);
		while (i < length) {
			nodeToNodeTraversal();
			try {
				if (speed == 80) {
					//panel.timeEntity[trainIndex].startTime.addTime(0, 0, 45,0);
					int sleepTime= (int)(multiplyingfactor * 300 + 0.5);
					int timeAdded=(int)(0.15 * sleepTime + 0.5);
					panel.timeEnt[trainIndex].startTime.addTime(0, 0, timeAdded, 0);
					(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime.convertDateToString();
				//	System.out.println(" Train Index= "+trainIndex +" Time="+panel.timeEnt[trainIndex].startTime.convertDateToString());
					//System.out.println("----------------------------%%%%%%%%%%%%%%5");
					
					Thread.sleep(sleepTime);
					// the actual speed is multiplied by a factor of 150
					
				}
				if (speed == 100) {
					int sleepTime= (int)(multiplyingfactor * 240 + 0.5);
					int timeAdded=(int)(0.15 * sleepTime + 0.5);
					panel.timeEnt[trainIndex].startTime.addTime(0, 0, timeAdded, 0);
					(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime.convertDateToString();
			//		System.out.println(" Train Index= "+trainIndex +" Time="+panel.timeEnt[trainIndex].startTime.convertDateToString());
					//panel.testVar++;
					
					Thread.sleep(sleepTime);
					}
				if (speed == 120) {
					int sleepTime= (int)(multiplyingfactor * 200 + 0.5);
					int timeAdded=(int)(0.15 * sleepTime + 0.5);
					panel.timeEnt[trainIndex].startTime.addTime(0, 0, timeAdded, 0);
				//	System.out.println(" Train Index= "+trainIndex +" Time="+panel.timeEnt[trainIndex].startTime.convertDateToString());
				//	panel.timeEnt[trainIndex].startTime.convertDateToString();
				//	panel.testVar++;
					(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime.convertDateToString();
					
					Thread.sleep(sleepTime);
					}
				if (speed == 140) {
					int sleepTime= (int)(multiplyingfactor * 171 + 0.5);
					int timeAdded=(int)(0.15 * sleepTime + 0.5);
			  		panel.timeEnt[trainIndex].startTime.addTime(0, 0, timeAdded, 0);
			  		(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime.convertDateToString();
			  //		System.out.println(" Train Index= "+trainIndex +" Time="+panel.timeEnt[trainIndex].startTime.convertDateToString());
				//	panel.timeEnt[trainIndex].startTime.convertDateToString();
			  	//	panel.testVar++;
			  		//int sleepTime= (int)(multiplyingfactor * 171 + 0.5);
					Thread.sleep(sleepTime);
					
				}

			} catch (Exception e) {
			}
			i++;
		}
	}

}