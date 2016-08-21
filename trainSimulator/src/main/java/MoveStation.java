import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class MoveStation extends JPanel implements ItemListener {// ,ActionListener
	// {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnl1 = new JPanel();
	// private JButton btn=new JButton("start");
	private GraphPanel panel;
	private JCheckBox editNode = new JCheckBox("Check to move Node");

	MoveStation(GraphPanel pnl) {

		setLayout(new FlowLayout());
		panel = pnl;
		pnl1.add(editNode);
		// pnl1.add(btn);
		add(pnl1);
		editNode.addItemListener(this);
		// btn.addActionListener(this);
		// setSize(250,400);
		// setLocation(600,100);
		setVisible(true);
	}

	/*
	 * public void actionPerformed(ActionEvent e) { Object src = e.getSource();
	 * 
	 * if (src == btn) {
	 * 
	 * for (int i = 0; i < panel.nnodes; i++) {
	 * 
	 * panel.nodes[i].fixed = false; } } }
	 */

	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		boolean on = e.getStateChange() == ItemEvent.SELECTED;
		if (src == editNode) {

			for (int i = 0; i < panel.nnodes; i++) {
				if (on == true) {
					panel.nodes[i].fixed = false;
				} else {
					panel.nodes[i].x = panel.nodes[i].tempX;
					panel.nodes[i].y = panel.nodes[i].tempY;
					panel.nodes[i].fixed = true;
					// System.out.println(panel.nodes[i].lbl+ ",
					// "+panel.nodes[i].x + ";"+ panel.nodes[i].y );
					repaint();
				}
			}

		}
	}

}