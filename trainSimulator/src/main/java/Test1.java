import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDialog frm = new JDialog();
	    JProgressBar jProgressBar1 = new JProgressBar();
        JLabel jLabel1 = new javax.swing.JLabel();
        frm.setAlwaysOnTop(true);
        frm.getContentPane().setLayout(null);

      //  frm.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frm.getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(40, 80, 290, 16);

        jLabel1.setText("Please Wait while Sscheduling is done...");
        frm.getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 40, 290, 14);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frm.setBounds((screenSize.width-395)/2, (screenSize.height-143)/2, 395, 143);
        frm.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	
	
	frm.setVisible(true);
	jProgressBar1.setIndeterminate(true);
	
	 for(int i=0; i< 10000000; i++){
		 System.out.println("hello");
	 }
	 jProgressBar1.setIndeterminate(false);
	 frm.dispose();
	}

}
