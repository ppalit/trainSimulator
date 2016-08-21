public class TrainDetails extends Thread {

	private static final long serialVersionUID = 1L;
	GraphPanel panel;
	RouteEntity rt;
	TrainEntity tr;
	int x1;
	int x2;
	int x3 = 0;
	int y1;
	int y2;
	int y3 = 0;
	double dx;
	double dy;
	int length;
	int flag[];
	int i = 0;
	int priority = 1;
	int train;
	String station_through[];
	String station_stop[];
	// Node stations[];
	String stationNames[];
	int no_stations;
	MovingTrain moveTrain;
	public Node n11, n22;
	int one, two;
	int trainIndex;
	int speed;
	int sleepTime;
	public TrainDetails(TrainEntity tr, RouteEntity rt, int trainIndex,
			GraphPanel pnl) {
		panel = pnl;
		this.tr = tr;
		this.trainIndex = trainIndex;
		String stationNames[] = new String[rt.getRoute_size()];
		System.out.println("in train constructor");
		stationNames = rt.getRoute_stations();
		flag = new int[50];
		speed = tr.getTrain_speed_no();

		if ((tr.train_no) % 2 == 0) {
			System.out.println("even no train");
			for (int j = 0; j < rt.getRoute_size(); j++) {
				panel.stations[j][trainIndex] = panel.nodes[panel
						.nodeNo(stationNames[j])];

			}
		} else {
			System.out.println("Odd no train");
			int k = 0;
			for (int j = rt.getRoute_size() - 1; j >= 0; j--) {
				panel.stations[k][trainIndex] = panel.nodes[panel
						.nodeNo(stationNames[j])];
				k++;

			}
		}

		no_stations = rt.getRoute_size();

		this.start();

	}
	// adjust stopage
	@Override
	public void run() {
		System.out.println("No of stopage =" + panel.tr.getNo_of_stops());
		System.out.println("no. of stations=" + no_stations);
		// JOptionPane.showMessageDialog(panel, "");

		while (i < no_stations - 1) {
			// System.out.println("stopage no"+panel.tr.size);
			moveTrain = new MovingTrain(panel.stations[i][trainIndex],
					panel.stations[i + 1][trainIndex], panel, trainIndex, speed);
			try {
				moveTrain.join();
				for (int l = 0; l <= panel.rt.size; l++) {
					System.out.println("stopage tr=" + tr.stopage[l]
							+ " , stopage panel="
							+ panel.stations[i + 1][trainIndex].lbl);
					// JOptionPane.showMessageDialog(panel,
					// panel.se[0].station_name);
					if (tr.stopage[l]
							.equals(panel.stations[i + 1][trainIndex].lbl)) {

						for (int g = 0; g <= panel.se[0].count; g++) {
							// JOptionPane.showMessageDialog(panel,
							// panel.se[g].station_name);
							if (panel.se[g].station_name
									.equals(panel.stations[i + 1][trainIndex].lbl)) {
								// JOptionPane.showMessageDialog(panel,
								// panel.se[g].station_type);
								if (panel.se[g].station_type == 1) {
									// sleepTime= 2000;
									// Thread.sleep(2000);// Time of stopage= 5
									panel.timeEnt[trainIndex].startTime
											.addTime(0, 5, 5, 0);
									(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime
											.convertDateToString();
									Thread.sleep(2000);
									// min
								} else {
									if (panel.se[g].station_type == 2) {
										// sleepTime= 4000;
										// Thread.sleep(4000);// Time of stopage
										// =
										// 10 min
										panel.timeEnt[trainIndex].startTime
												.addTime(0, 10, 0, 0);
										(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime
												.convertDateToString();
										Thread.sleep(4000);
									} else {
										if (panel.se[g].station_type == 3) {
											// sleepTime= 6000;
											// Thread.sleep(6000);// Time of
											panel.timeEnt[trainIndex].startTime
													.addTime(0, 15, 0, 0);
											(panel.timeEnt[trainIndex]).timeArr[panel.timeEnt[trainIndex].pointer] = (panel.timeEnt[trainIndex]).startTime
													.convertDateToString();
											Thread.sleep(6000);
											// stopage = 15
											// min
										}
									}
								}
							}
						}
						// JOptionPane.showMessageDialog(panel, sleepTime);
						// Thread.sleep(sleepTime);// Time of stopage

					}

				}

			} catch (Exception e) {
			}
			i++;
		}
		return;

	}
}