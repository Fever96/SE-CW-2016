package cinemaArrange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import ticket.SimEmailResult;
import ticket.Ticket;





/**
 * a static class that is responsible for IO
 * @author 
 *
 */

public class CinemaIO {

	/**
	 * stores the theater manager unit to file
	 */
	public static void storeAllArrange(){
        File file =new File("All_Arrangement.txt");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(CinemaDirector.getUniqueDirector());
            objOut.flush();
            objOut.close();
            System.out.println("Write all cinema arrangement");
        } catch (IOException e) {
            System.out.println("Write cinema arrangement failed");
            e.printStackTrace();
        }
	}
	/**
	 * read theater manager unit from file
	 * @see CinemaDirector#setUniqueDirector(CinemaDirector)
	 */
	public static void readAllArrange(){
		
		
		Object temp=null;
        File file =new File("All_Arrangement.txt");
        FileInputStream in;
		 try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            temp=objIn.readObject();
	            objIn.close();
	            System.out.println("Read all arrangenent ");
	            
	        } catch (Exception e) {
	            System.out.println("Read all arrangenent failed"); 
	            e.printStackTrace();
	        }
		CinemaDirector.setUniqueDirector((CinemaDirector) temp);
		 
		
		
	}
	/**
	 * print a ticket
	 * @param t ticket that will be printed
	 */
	public static void printTicket(Ticket t) {
		String name="Ticket_"+t.ID;
        File file =new File(name+".txt");
        
        try {
        	PrintWriter pw=new PrintWriter(new FileWriter(file)); 
        	pw.println(t.toString());
        	pw.flush();
        	pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * print the report for the current day
	 * @see CinemaDirector#NextDay()
	 */
	public static void printReport() {
		
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		Arrange myArrange=cd.getArrange(0);
		ArrayList<Ticket> todayTickets=myArrange.ticketToday;
		ArrayList<Movie> todayMovies=myArrange.movieToday;
        File file =new File("DailyReport.txt");
        int adult=0;
        int child=0;
        int senior=0;
        int student=0;
        
        try {
        	PrintWriter pw=new PrintWriter(new FileWriter(file)); 
        	ArrayList<Integer> howMany=new ArrayList<Integer>();
        	Double incom=0.0;
        	for(Ticket t:todayTickets){
        		if(t.type.equals("ADULT")){
        			adult++;
        		}
        		if(t.type.equals("CHILD")){
        			child++;
        		}
        		if(t.type.equals("SENIOR")){
        			senior++;
        		}
        		if(t.type.equals("STUDENT")){
        			student++;
        		}
        		incom+=t.discount*16.0;
        		
        	}
        	for(Movie m:todayMovies){
        		String movieName=m.name;
        		int i=0;
        		for(Ticket t:todayTickets){
            		if(t.movieName.equals(movieName)){
            			i++;
            		}
            		
            	}
        		howMany.add(i);
        	}
        	for(int i=0;i<todayMovies.size();i++){
        		pw.println(todayMovies.get(i).name+": "+howMany.get(i));
        	}
        	pw.println("Adult ticket: "+adult);
        	pw.println("Child ticket: "+child);
        	pw.println("Senior ticket: "+senior);
        	pw.println("Student ticket: "+student);
        	pw.println("Income: "+incom);
        	
        	
        	
        	
        	pw.flush();
        	pw.close();
        	new SimEmailResult().email();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	
	
	
	
	
}
