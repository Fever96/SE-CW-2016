package userInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cinemaArrange.ArrangeTest;
import cinemaArrange.CinemaDirector;
import cinemaArrange.CinemaIO;
import cinemaArrange.Screen;
import userInterface.customedPanels.AllMoviePanel;
import userInterface.customedPanels.ArrangeManagePanel;
import userInterface.customedPanels.BuyMoviePanel;
import userInterface.customedPanels.MovieManagePanel;
import userInterface.customedPanels.WelcomePanel;

/**
 * main interface
 * @author 
 *
 */
public class MainInterface {
	/**
	 * main interface
	 */
	public JFrame mainFrame=new JFrame();
	/**
	 * width of main interface
	 */
	public static int WIDTH=800;
	/**
	 * height of main interface
	 */
	public static int HEIGHT=600;
	
	public static void main(String args[]){
		CinemaIO.readAllArrange();
		new MainInterface().buildMain();
	}
	
	public void buildMain(){
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar bar = new JMenuBar();
		
		JMenu viewMenu = new JMenu("View");
		JMenu buyMenu = new JMenu("Ticket");
		JMenu manageMenu = new JMenu("Manage");
		
		bar.add(viewMenu);
		bar.add(buyMenu);
		bar.add(manageMenu);
		
		JMenuItem viewMovie = new JMenuItem("View all movies");
		JMenuItem viewScreen = new JMenuItem("View all screens");
		JMenuItem buyTicket = new JMenuItem("Buy tickets");
		JMenuItem cancelTicket = new JMenuItem("Cancel my ticket");
		JMenuItem manageArrange = new JMenuItem("Manage arrangement");
		JMenuItem manageSeat = new JMenuItem("Manage seat");
		JMenuItem manageMovie = new JMenuItem("Manage movie");
		JMenuItem goNext = new JMenuItem("Next day");
		JMenuItem reset = new JMenuItem("Reset all");
		
		buyTicket.addActionListener(new BuyTicketListener());
		cancelTicket.addActionListener(new CancelListener());
		goNext.addActionListener(new NextDayListener());
		manageMovie.addActionListener(new ManageMovieListener());
		manageArrange.addActionListener(new ManageArranListener());
		manageSeat.addActionListener(new ManageSeatListener());
		viewMovie.addActionListener(new ShowMovieListener());
		viewScreen.addActionListener(new ViewSeatListener());
		reset.addActionListener(new ResetListener());
		
		viewMenu.add(viewMovie);
		viewMenu.add(viewScreen);
		buyMenu.add(buyTicket);
		buyMenu.add(cancelTicket);
		manageMenu.add(manageArrange);
		manageMenu.add(manageMovie);
		manageMenu.add(manageSeat);
		manageMenu.add(goNext);
		manageMenu.add(reset);
		
		mainFrame.setJMenuBar(bar);
		mainFrame.getContentPane().add(new WelcomePanel());
		mainFrame.getContentPane().setBackground(Color.WHITE);
		
		mainFrame.setSize(WIDTH,HEIGHT);
		mainFrame.setVisible(true);
	}
	class ShowMovieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new AllMoviePanel());
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class BuyTicketListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new BuyMoviePanel(MainInterface.this));
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class NextDayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			cd.NextDay();
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new WelcomePanel());
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class ManageMovieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new MovieManagePanel(MainInterface.this));
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class ManageArranListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new ArrangeManagePanel(MainInterface.this));
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String tNum = JOptionPane.showInputDialog("Please enter your ticket No.");
			if(null==tNum){
				return;
			}
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			boolean isCanceled=cd.cancel(tNum);
			if(!isCanceled){
				JOptionPane.showMessageDialog(null, "Please enter correct ticket ID", "Sorry", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Canceled!", "Yes!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new WelcomePanel());
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}
	class ManageSeatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Screen s=null;
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			Object[] screenValues = new Object[cd.allScreen.size()];
			for(int i=0;i<cd.allScreen.size();i++){
				String sss=cd.allScreen.get(i).id;
				screenValues[i]=sss;
			}
			
			Object selectedValue = JOptionPane.showInputDialog(null, "Select a screen to modify. Press cancel to creat new.", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			screenValues, screenValues[0]);
			if(selectedValue!=null){
				String scrStr=(String)selectedValue;
				
				for(int i=0;i<cd.allScreen.size();i++){
					if(scrStr.equals(cd.allScreen.get(i).id)){
						s=cd.allScreen.get(i);
					}
					
				}
				new ScreenSettingInterface(s);
			}
			else{
				Object[] rowValues = new Object[9]; 
				for(int i=4;i<13;i++){
					rowValues[i-4]=""+i;
				}
				
				selectedValue = JOptionPane.showInputDialog(null, "How many rows?", "Please choose", 
				JOptionPane.INFORMATION_MESSAGE, null, 
				rowValues, rowValues[8]);
				String hourStr=(String)selectedValue;
				if(hourStr==null){
					return;
				}
				int row=Integer.parseInt(hourStr);
				
				Object[] calValues = new Object[9]; 
				for(int i=4;i<13;i++){
					calValues[i-4]=""+i;
				}
				
				selectedValue = JOptionPane.showInputDialog(null, "How many calums?", "Please choose", 
				JOptionPane.INFORMATION_MESSAGE, null, 
				calValues, calValues[8]);
				String minStr=(String)selectedValue;
				int cal=Integer.parseInt(minStr);
				new ScreenSettingInterface(cal,row);
				
			}
			mainFrame.getContentPane().add(new WelcomePanel());
			
			
			
		
		}
		
	}
	class ViewSeatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Screen s=null;
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			Object[] screenValues = new Object[cd.allScreen.size()];
			for(int i=0;i<cd.allScreen.size();i++){
				String sss=cd.allScreen.get(i).id;
				screenValues[i]=sss;
			}
			
			Object selectedValue = JOptionPane.showInputDialog(null, "Select a screen to view.", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			screenValues, screenValues[0]);
			if(selectedValue!=null){
				String scrStr=(String)selectedValue;
				
				for(int i=0;i<cd.allScreen.size();i++){
					if(scrStr.equals(cd.allScreen.get(i).id)){
						s=cd.allScreen.get(i);
					}
					
				}
				new ScreenInterface(s);
			}
			
			mainFrame.getContentPane().add(new WelcomePanel());
			
			
			
		
		}
		
	}
	
	class ResetListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new ArrangeTest().resetAll();
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(new WelcomePanel());
			mainFrame.getContentPane().setLayout(new GridLayout(1,1));
			
			mainFrame.repaint();
			mainFrame.setSize(WIDTH,HEIGHT);
			mainFrame.setVisible(true);
		}
		
	}

}
