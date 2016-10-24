package dev.zero.trainSimulator;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * 
 */

/**
 * @author Priyabrata
 *
 */
public class ProgressBar extends JInternalFrame{
	JProgressBar jProgressBar1;
	JLabel jLabel1;
	JDialog frm;
	ProgressBar(){
		//frm= new JDialog();
		jProgressBar1 = new JProgressBar();
		jLabel1 = new JLabel();
		//this.setAlwaysOnTop(true);
		this.getContentPane().setLayout(null);

		//frm.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(40, 80, 290, 16);

        jLabel1.setText("Please Wait while Sscheduling is done...");
        this.getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 40, 290, 14);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width-395)/2, (screenSize.height-143)/2, 395, 143);
		
			
	}
	public void status(boolean value){
		jProgressBar1.setIndeterminate(value);
		if(value == true){
			this.setVisible(true);
		}
		else this.setVisible(false);
	}
	public static void main(String args[]){
		ProgressBar pb = new ProgressBar();
		pb.status(true);
		for(int i=0; i< 1000000; i++){
			System.out.println("hello");
		}
		pb.status(false);
		
	}
	

}
