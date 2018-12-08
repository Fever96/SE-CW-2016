package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import cinemaArrange.*;
import ticket.*;
/**
 * displays all the seats in the screen and contains buying function
 * @author 
 *
 */
public class ScreenInterface {

	private Screen screen;
	private Show show;
	private int date;
	/**
	 * height for one block
	 */
	private static int GRID=60;
	/**
	 * width for one block
	 */
	private static int GRIDX=115;
	private Arrange arrange;
	/**
	 * the one-one relation between the storing button and seat
	 */
	private HashMap<JButton,Seat> seatMap=new HashMap<JButton,Seat>();
	/**
	 * the one-one relation between the storing button and the words been displayed
	 */
	private HashMap<JButton,String> buttonTextMap=new HashMap<JButton,String>();
	private ArrayList<JButton> btnList=new ArrayList<JButton>();
	private ArrayList<JButton> selectedButton=new ArrayList<JButton>();
	public JFrame screenFrame=new JFrame("Please choose a seat");
	/**
	 *build an interface to check the structure of theater only
	 * @param s screen
	 */
	public ScreenInterface(Screen s){
		this.screen=s;
		build();
	}
	/**
	 * build an interface for buying tickets and choosing seats
	 * @param show movie session
	 * @param d date
	 */
	public ScreenInterface(Show show,int d){
		this.screen=show.screen;
		this.show=show;
		this.date=d;
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		this.arrange=cd.getArrange(date);
		build();
	}
	
	private void build(){
		
		int[][] layout=screen.layout;
		int cal=layout[0].length;
		int row=layout.length;
		if(show!=null){//Buying interface
			for(Seat mySeat:screen.seatList){
				mySeat.avaliable=true;
			}
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			for(Ticket t:arrange.ticketToday){//find out all the seats that are sold, and set it's value to false
				String times[]=t.time.split(":");
				boolean isThisShowTicket=(show.date==Integer.parseInt(t.date))&&show.hour==Integer.parseInt(times[0])&&show.minute==Integer.parseInt(times[1]);
				String myPosition=t.position;
				for(Seat mySeat:screen.seatList){
					if(mySeat.position.equals(myPosition)&&isThisShowTicket){
						mySeat.avaliable=false;
					}
				}
			}
			
		}
		for(Seat seat:screen.seatList){//concrete every seat to visible button
			JButton tempButton=new JButton(seat.position.substring(1, 2));
			tempButton.setBackground(Color.WHITE);
			tempButton.addActionListener(new PressedListener());
			if(!seat.avaliable){
				tempButton.setEnabled(false);
				tempButton.setText("Sold");
			}
			seatMap.put(tempButton, seat);
			btnList.add(tempButton);
		}
		
		JPanel seatPanel=new JPanel();//the panel that shows all the seats

		
		
		int count=0;
		
		for(int i=0;i<row;i++){
			JLabel jl=new JLabel("    "+(char)(i+65));
			seatPanel.add(jl);
			for(int j=0;j<cal;j++){
				if(layout[i][j]==0){
					JButton empty=new JButton("545445");
					empty.setVisible(false);
					seatPanel.add(empty);
				}
				else{
					seatPanel.add(btnList.get(count));
					count++;
				}
			}
			jl=new JLabel("       "+(char)(i+65));
			seatPanel.add(jl);
		}
		
		JPanel jp=new JPanel();// upper Panel
		JLabel jl=new JLabel("Screen");
		jp.add(jl);
		jp.setBackground(Color.gray);
		screenFrame.getContentPane().add(jp);
		jp.setBounds(0, 0, GRIDX*cal, GRID/2);
		
		jp=new JPanel();// lower Panel
		jl=new JLabel("Please choose your seat");
		JButton cancelBtn=new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				screenFrame.dispose();
				
			}});
		jp.add(jl);
		jp.add(cancelBtn);
		if(show!=null){
			JButton buyBtn=new JButton("Buy");
			buyBtn.addActionListener(new BuyListener());
			jp.add(buyBtn);
			jl.setText("Please choose your seat");
			
		}else{//the viewing interface has no buying button
			for(JButton mybtn:this.btnList){
				mybtn.setEnabled(false);
			}
			jl.setText("Here is the layout");
		}
		jp.setBackground(Color.gray);
		screenFrame.getContentPane().add(jp);
		jp.setBounds(0, GRID/2+GRID*row, GRIDX*cal, GRID/2);
		
		
		
		seatPanel.setLayout(new GridLayout(row,cal));
		seatPanel.setBounds(0, GRID/2, GRIDX*cal, GRID*row);
		screenFrame.getContentPane().add(seatPanel);
		screenFrame.getContentPane().setBackground(Color.GRAY);
		screenFrame.getContentPane().setLayout(null);
		screenFrame.setSize(GRIDX*cal,(int) (GRID*row+1.5*GRID)+30);
		screenFrame.setVisible(true);
	}
	
	
	class BuyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(selectedButton.isEmpty()){
				JOptionPane.showMessageDialog(null, "Please select a seat!", "", JOptionPane.INFORMATION_MESSAGE);	
				return;
			}
			
			Object[] possibleValues = { "Adult", "Child","Senior","Student" }; 
			Object selectedValue = JOptionPane.showInputDialog(null, "Which date do you want to buy?", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			possibleValues, possibleValues[0]);
			if(selectedValue==null){
				reset();
				return;
			}
			int index=-1;
			for(int i=0;i<possibleValues.length;i++){
				if(selectedValue.equals(possibleValues[i])){
					index=i;
				}
			}
			ArrayList<TicketBuilder> builderList=new ArrayList<TicketBuilder>();
			
			String ticketType="";
			Double money=0.0;
			
			if(index==0){//according to the choice, set up a new builder for concreting tickets, then build new tickets step by step
				for(JButton myBtn:selectedButton){
					TicketBuilder tb=new AdultTicketBuilder();
					Seat mySeat=seatMap.get(myBtn);
					tb.buildSeat(mySeat);// build seat
					tb.buildShow(show);//build session
					builderList.add(tb);
					ticketType="Adult";
					money+=Ticket.price*Ticket.ADULT;
				}
			}
			if(index==1){
				for(JButton myBtn:selectedButton){
					TicketBuilder tb=new ChildTicketBuilder();
					Seat mySeat=seatMap.get(myBtn);
					tb.buildSeat(mySeat);
					tb.buildShow(show);
					builderList.add(tb);
					ticketType="Child";
					money+=Ticket.price*Ticket.CHILD;
				}
			}
			if(index==2){
				for(JButton myBtn:selectedButton){
					TicketBuilder tb=new SeniorTicketBuilder();
					Seat mySeat=seatMap.get(myBtn);
					tb.buildSeat(mySeat);
					tb.buildShow(show);
					builderList.add(tb);
					ticketType="Senior";
					money+=Ticket.price*Ticket.SENIOR;
				}
			}
			if(index==3){
				for(JButton myBtn:selectedButton){
					TicketBuilder tb=new StudentTicketBuilder();
					Seat mySeat=seatMap.get(myBtn);
					tb.buildSeat(mySeat);
					tb.buildShow(show);
					builderList.add(tb);
					ticketType="Student";
					money+=Ticket.price*Ticket.STUDENT;
				}
				for(TicketBuilder stb:builderList){
					String ss = JOptionPane.showInputDialog("Please enter your student ID");
					checkInput(ss);
					stb.buildStudentID(ss);//build student ID
				}
			}
			
			
			int howManyTicket=selectedButton.size();
			if(howManyTicket>1){
				JOptionPane.showMessageDialog(null, "You have selected "+howManyTicket+" "+ticketType+" tickets.\nPlease pay "+money+"ponds.");
			}else
			{
				JOptionPane.showMessageDialog(null, "You have selected "+howManyTicket+" "+ticketType+" ticket.\nPlease pay "+money+"ponds.");
			}
			
			
			if(new SimBankPaySystem().Pay(money)){//determine whether the payment is success
				for(TicketBuilder tb:builderList){
					
					
					String temp=IDcheck();
					tb.buildID(temp);//build ID
					Ticket t=tb.getTicket();
					arrange.addTicket(t);//confirm to generate a ticket
					CinemaIO.printTicket(t);
					CinemaIO.storeAllArrange();
					System.out.println(t.toString());
				}
				for(JButton myBtn:selectedButton){
					Seat mySeat=seatMap.get(myBtn);
					mySeat.avaliable=false;
					myBtn.setEnabled(false);
					myBtn.setText("Sold");
				}
				selectedButton.clear();
			}
			else{
				reset();
			}
			
			
			
		}
		
		private String IDcheck(){//generate distinct ticket ID by recursion
			String temp=""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4);
			for(Ticket t:arrange.ticketToday){
				if(t.ID.equals(temp)){
					temp=""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4)+""+(int)(1+Math.random()*4);
					
					return IDcheck();
				}
			}
			return temp;
		}
		
		private void reset(){//if the customer give up the payment halfway, resume the GUI
			Iterator iter = buttonTextMap.entrySet().iterator();
        	while (iter.hasNext()) {
        		Map.Entry entry = (Map.Entry) iter.next();
        		Object key = entry.getKey();
        		Object val = entry.getValue();
        		JButton btn=(JButton)key;
        		String word=(String)val;
        		btn.setText(word);	
        	}
        	buttonTextMap.clear();
        	selectedButton.clear();
		}
		
		private boolean checkInput(String s){//check whether the correct student ID is entered by recursion
			if(s==null){
				String ss = JOptionPane.showInputDialog("Please enter your student ID");
				return checkInput(ss);
			}
			return true;
		}
		
	}
	
	class PressedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn=(JButton)e.getSource();
			if(!btn.getText().equals("Selected")){
				String oldtext=btn.getText();
				buttonTextMap.put(btn, oldtext);
				selectedButton.add(btn);
				btn.setText("Selected");
				btn.setBackground(Color.RED);
			}
			else{
				String oldtext=buttonTextMap.get(btn);
				btn.setText(oldtext);
				btn.setBackground(Color.WHITE);
				buttonTextMap.remove(btn);
				selectedButton.remove(btn);
			}
			
			
		}
		
	}
}
