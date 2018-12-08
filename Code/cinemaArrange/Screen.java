package cinemaArrange;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author 
 *
 */
public class Screen implements Serializable{
	/**
	 * number of rows
	 */
	public int row;
	/**
	 * number of columns
	 */
	public int cal;
	/**
	 * the matrix in which shows the structure of screen. 1 represents seat available, 0 for unavailable
	 */
	public int[][] layout;
	/**
	 * number of the screen
	 */
	public String id;
	/**
	 * seats in the screen
	 */
	public ArrayList<Seat> seatList=new ArrayList<Seat>();
	/**
	 * 
	 * @param layout screen structure matrix, which generate the seat object based on the matrix
	 * @param str number of the theater
	 */
	public Screen(int[][] layout,String str){
		this.layout=layout;
		this.id=str;
		this.row=layout.length;
		this.cal=layout[0].length;
		for(int i=0;i<this.row;i++){
			int k=0;
			for(int j=0;j<this.cal;j++){
				if(layout[i][j]==1){
					k++;
					Seat s = new Seat(i+1,k);
					seatList.add(s);
					}
				
			}
		}
		
		
		
	}
	
	

}
