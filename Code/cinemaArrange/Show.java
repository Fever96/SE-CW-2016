package cinemaArrange;

import java.io.Serializable;
/**
 * duration, seat position and contents of a movie
 * @author 
 *
 */
public class Show implements Serializable{

	/**
	 * date
	 */
	public int date;
	/**
	 * beginning time-minute
	 */
	public int minute;
	/**
	 * beginning time-hour
	 */
	public int hour;
	/**
	 * screen
	 */
	public Screen screen;
	/**
	 * movie that on show
	 */
	public Movie movie;
	/**
	 * 
	 * @param mmovie
	 * @param s screen
	 * @param h hour
	 * @param min minute
	 * @param date date
	 */
	public Show(Movie m,Screen s ,int h,int min,int date){
		this.movie=m;
		this.screen=s;
		this.hour=h;
		this.minute=min;
		this.date=date;
		
	}
	
}
