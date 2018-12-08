package ticket;
/**
 * interface of bank payment
 * @author 
 *
 */
public interface BankPaySystem {

	/**
	 * payment to the bank
	 * @return whether the payment is success
	 */
	public boolean Pay(Double d);
}
