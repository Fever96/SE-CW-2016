package cinemaArrange;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import ticket.Ticket;
/**
 * includes all the arrangement and business of the theater in one day. Movies on show, showing arrangement
 * and tickets that been sold.
 * there's only one instance of that class in this program
 * @author 
 * @see cinemaArrange.Screen
 * @see cinemaArrange.Arrange
 * @see cinemaArrange.Movie
 */
public class CinemaDirector implements Serializable{

	
	/**unique static theater manager unit*/
	private static CinemaDirector myCinemaDirector;
	/**configuration of all actions of movie rooms*/
	public ArrayList<Screen> allScreen=new ArrayList<Screen>();
	/**arrangement for the date */
	public ArrayList<Arrange> difDayArran=new ArrayList<Arrange>();
	/**includes all the movies */
	public ArrayList<Movie> allMovies=new ArrayList<Movie>();
	
	/**
	 * private method for unit
	 */
	private CinemaDirector(){
		
	}
	/**
	 * entrance of the public to call the theater manager unit
	 * @return unique theater manager unit
	 */
	public static CinemaDirector getUniqueDirector(){
		if(myCinemaDirector==null){
			myCinemaDirector=new CinemaDirector();
		}
		return myCinemaDirector;
	}
	/**
	 * set an unique theater manager unit(not build a new one)
	 * @param cd theater manager unit
	 * @see CinemaIO#readAllArrange()
	 */
	public static void setUniqueDirector(CinemaDirector cd){
		myCinemaDirector=cd;
	}
	/**
	 * clone a new theater, in which all the seats are available and don't point the same object as the
	 * theater already stored
	 * @param index number of the new theater starts from 0
	 * @return new theater that manager wants
	 * @see cinemaArrange.Screen
	 * @see cinemaArrange.Seat
	 */
	public Screen getTheScreen(int index){
		Screen myScreen=allScreen.get(index);
		Screen yourScreen=new Screen(myScreen.layout,myScreen.id);//clone new screen
		return yourScreen;
	}
	/**
	 * gain the arrangement of a specific day
	 * @param index the number of the day the manager wants, starts form 0, then 1
	 * @return arrangement for specific day
	 */
	public Arrange getArrange(int index){
		return difDayArran.get(index);
	}
	/**
	 * simulation of entering the next day
	 */
	public void NextDay(){
		CinemaIO.printReport();
		Arrange a=difDayArran.get(0);
		a.ticketToday.clear();
		a.movieToday.clear();
		a.showToday.clear();
		difDayArran.remove(a);
		difDayArran.add(a);
		for(Arrange ar:this.difDayArran){
			for(Show show:ar.showToday){
				show.date--;
			}
		}
		CinemaIO.storeAllArrange();
	}
/**
 * cancel a ticket that already been sold
 * @param tNum he number of the ticket that is wanted to be cancelled
 * @return true:success, false: fail
 */
	public boolean cancel(String tNum) {
		Ticket cancelT=null;
		for(Arrange arr:this.difDayArran){
			for(Ticket t:arr.ticketToday){
				if(t.ID.equals(tNum)){
					cancelT=t;
				}
			}
			if(cancelT!=null){
				arr.cancelTicket(cancelT);
				String fileName="Ticket_"+cancelT.ID+".txt";
		        File file =new File(fileName);
		        if(file.exists()){
		        	file.delete();
		        }
		        CinemaIO.storeAllArrange();
				return true;
			}
		}
		return false;
	}
	
}
