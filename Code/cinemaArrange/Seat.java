package cinemaArrange;

import java.io.Serializable;

public class Seat implements Serializable{
	
	
	/**
	 * position of the seat, which contains two characters, one for row and one for column
	 */
	public String position;
	/**
	 * whether the seat is available
	 */
	public boolean avaliable=true;

	
	/**
	 * 
	 * @param a row
	 * @param b column
	 * @see Screen#Screen(int[][], String)
	 */
	public Seat(int a,int b) {
		this.position=(char)(a+64)+""+b;
	}
}
