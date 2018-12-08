package ticket;

import javax.swing.JOptionPane;
/**
 * simulated email sending class
 * @author 
 *
 */
public class SimEmailResult implements EmailResult {

	@Override
	public void email() {
		JOptionPane.showMessageDialog(null, "Email has been sent!", "", JOptionPane.INFORMATION_MESSAGE);
		
		
	}

}
