package ticket;

import javax.swing.JOptionPane;
/**
 * simulated payment class
 * @author 
 *
 */
public class SimBankPaySystem implements BankPaySystem {


	@Override
	public boolean Pay(Double d) {
		int res=JOptionPane.showConfirmDialog(null, "You have a "+d+" ponds bill.\nPress yes to pay for the bill", "Banking System", JOptionPane.YES_NO_OPTION);
		if(res==JOptionPane.YES_OPTION){ 
			return true;
		}else{
			return false;
		} 
	}

}
