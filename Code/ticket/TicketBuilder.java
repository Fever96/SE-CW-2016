package ticket;


import cinemaArrange.*;
/**
 * builder of abstract ticket
 * @author 
 *
 */
public abstract class TicketBuilder {

	/**
	 * tickets waiting to be built
	 */
	public Ticket myTicket=new Ticket();
	/**
	 * seats for the tickets
	 * @param seat
	 */
	public abstract void buildSeat(Seat seat);
	/**
	 * session for the tickets
	 * @param show
	 */
	public abstract void buildShow(Show show);
	/**
	 * student for the tickets
	 * @param sid
	 */
	public abstract void buildStudentID(String sid);
	/**
	 * withdraw tickets
	 * @return tickets that are being built or already been built
	 */
	public abstract Ticket getTicket();
	/**
	 * number of tickets being built
	 * @param ID random 8-digit number
	 */
	public abstract void buildID(String ID);
	
	
	
}
