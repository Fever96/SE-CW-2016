package cinemaArrange;

import java.io.Serializable;
import java.util.ArrayList;

import ticket.Ticket;
/**
 * includes all the arrangement and business of the theater in one day. Movies on show, showing arrangement
 * and tickets that been sold.
 * @author 
 *
 */
public class Arrange implements Serializable{
	/**all the movies that are on show*/
	public ArrayList<Movie> movieToday=new ArrayList<Movie>();
	/**all the arrangement of showing*/
	public ArrayList<Show> showToday=new ArrayList<Show>();
	/**all the tickets that been sold*/
	public ArrayList<Ticket> ticketToday=new ArrayList<Ticket>();
	
	/**
	 * add a new ticket
	 * @param t ticket been added
	 */
	public void addTicket(Ticket t){
		this.ticketToday.add(t);
	}

	/**
	 * cancel a ticket
	 * @param cancelT ticket that is going to be cancelled
	 */
	public void cancelTicket(Ticket cancelT) {
		System.out.println(this.ticketToday.size());
		this.ticketToday.remove(cancelT);
		System.out.println(this.ticketToday.size());
		
	}	
	

}
