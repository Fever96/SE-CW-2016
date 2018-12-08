package ticket;

import java.io.Serializable;

import cinemaArrange.*;

public class Ticket implements Serializable{
	
	public static double SENIOR=0.8;
	public static double STUDENT=0.85;
	public static double CHILD=0.5;
	public static double ADULT=1.0;
	public static int price=16;
	public Seat seat;
	public Show show;
	public String ID;
	public String studentID="";
	public String type;
	public String screenID;
	public String position;
	public String movieName;
	public String time;
	public String howLong;
	public String date;
	public double discount;
	
	
	
	
	@Override
	public String toString(){
		if(studentID.equals("")){
			return type+" Ticket ID "+ID+"\nScreen"+screenID+" \n"+movieName+"\n"+howLong+" minutes\nSeat"+position+"\nDate "+date+"\nTime "+ time;
			
		}
		return type+" Ticket ID "+ID+"\nStudent ID "+studentID+"\nScreen"+screenID+" \n"+movieName+"\n"+howLong+" minutes \nSeat"+position+"\nDate "+date+"\nTime "+ time;
	}
}
