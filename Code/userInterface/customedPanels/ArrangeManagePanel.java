package userInterface.customedPanels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import cinemaArrange.Arrange;
import cinemaArrange.CinemaDirector;
import cinemaArrange.CinemaIO;
import cinemaArrange.Movie;
import cinemaArrange.Screen;
import cinemaArrange.Show;
import userInterface.MainInterface;
import userInterface.ScreenInterface;

public class ArrangeManagePanel extends JPanel{
	

	private JComboBox<String> dayBox=new JComboBox<String>();
	private JComboBox<String> showBox=new JComboBox<String>(); 
	private MainInterface mainInt;

	private Arrange todayArran;
	private JButton btn1=new JButton("Delete");
	private JButton btn2=new JButton("Add");
	private JPanel showPanel=new JPanel();
	
	public ArrangeManagePanel(MainInterface m){
		this.mainInt=m;
		this.setBackground(Color.WHITE);
		go();
		
	}
	private void go(){
		
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		
		dayBox.addItemListener(new DaySelectedListener());
		showBox.addItemListener(new ShowSelectedListener());
		
		for(int i=0;i<cd.difDayArran.size();i++){
			String day=""+(i+1);
			dayBox.addItem(day);
		}
		
		
		dayBox.setSize(100, 40);
		
		btn1.addActionListener(new DeleteListener());
		btn2.addActionListener(new AddListener());
		
		JLabel jl1=new JLabel("Choose a show to delete!   Date:");
		JLabel jl2=new JLabel("Show:");
		
		
		this.add(jl1);
		this.add(dayBox);
		this.add(jl2);
		this.add(showBox);
		jl1.setBounds(30, 30, 250, 40);
		dayBox.setBounds(240, 30, 60, 40);
		jl2.setBounds(320, 30, 60, 40);
		showBox.setBounds(365, 30, 70, 40);
		btn1.setBounds(440, 25, 80, 50);
		btn2.setBounds(550, 25, 80, 50);
		showPanel.setBounds(470, 150, 100, 100);
		
		this.add(btn1);
		this.add(btn2);
		this.add(showPanel);
		showPanel.setLayout(new GridLayout(1,1));
		
		this.setLayout(null);
		
		
	}
	
	class DaySelectedListener implements ItemListener{

		

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				
				showBox.removeAllItems();
				
				showBox.setEnabled(true);
				
				CinemaDirector cd=CinemaDirector.getUniqueDirector();
				int daySelected=Integer.parseInt((String)dayBox.getSelectedItem())-1;
				
				System.out.println("Day selected"+daySelected);
				
				todayArran=cd.difDayArran.get(daySelected);
				System.out.println(todayArran.movieToday.isEmpty());
				if(todayArran.showToday.isEmpty()){
					
					
					
					showBox.setSelectedItem("NONE");
					btn1.setEnabled(false);
					
					return;
				}
				for(int i=0;i<todayArran.showToday.size();i++){
					showBox.addItem(""+(i+1));
				}
				btn1.setEnabled(true);
			}
			
			
		}
		
	}
	
	class ShowSelectedListener implements ItemListener{

		

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				showPanel.removeAll();
				System.out.println("sdasdasda");
				showPanel.setBackground(Color.CYAN);
				ArrayList<Show> showsToday=todayArran.showToday;
				int index=Integer.parseInt((String)showBox.getSelectedItem())-1;
				Show myShow=showsToday.get(index);
				
				String str="<html>Movie:"+myShow.movie.name+"<br>Place:Screen "+myShow.screen.id+"<br>Time:"+myShow.hour+":"+myShow.minute+"</html>";
				System.out.println(str);
				JLabel movieString=new JLabel();
				movieString.setSize(400, 300);
				movieString.setText(str);
				
				
			   
			    //movieString.setBounds(420, 0, 400, 300);
				showPanel.add(movieString);
				
				showPanel.repaint();
				
				mainInt.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT+1);
				mainInt.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
			}
			
			
			
			
			
			
		}
		
	}
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String index=(String) showBox.getSelectedItem();
			int ind=Integer.parseInt(index)-1;
			ArrayList<Show> showsToday=todayArran.showToday;
			showsToday.remove(ind);
			CinemaIO.storeAllArrange();
			showBox.removeAllItems();
			
			for(int i=0;i<todayArran.showToday.size();i++){
				showBox.addItem(""+(i+1));
			}
			if(todayArran.showToday.isEmpty()){
				btn1.setEnabled(false);
			}
			ArrangeManagePanel.this.repaint();
			
		}
		
	}
	class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<Show> showsToday=todayArran.showToday;
			String index=(String) dayBox.getSelectedItem();
			int day=Integer.parseInt(index);
			Movie mov=null;
			Screen scr=null;
			int min=59;
			int hour=23;
			
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			Object[] movieValues = new Object[cd.allMovies.size()]; 
			for(int i=0;i<cd.allMovies.size();i++){
				movieValues[i]=""+cd.allMovies.get(i).name;
			}
			
			Object selectedValue = JOptionPane.showInputDialog(null, "Select a movie", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			movieValues, movieValues[0]);
			String movStr=(String)selectedValue;
			for(int i=0;i<cd.allMovies.size();i++){
				if(movStr.equals(cd.allMovies.get(i).name)){
					mov=cd.allMovies.get(i);
				}
				
			}
			
			Object[] screenValues = new Object[cd.allScreen.size()];
			for(int i=0;i<cd.allScreen.size();i++){
				String sss=cd.allScreen.get(i).id;
				System.out.println("sd "+sss);
				screenValues[i]=sss;
			}
			
			selectedValue = JOptionPane.showInputDialog(null, "Select a screen", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			screenValues, screenValues[0]);
			String scrStr=(String)selectedValue;
			for(int i=0;i<cd.allScreen.size();i++){
				if(scrStr.equals(cd.allScreen.get(i).id)){
					scr=cd.getTheScreen(i);
				}
				
			}
			
			Object[] hourValues = new Object[24]; 
			for(int i=1;i<24;i++){
				hourValues[i]=""+i;
			}
			
			selectedValue = JOptionPane.showInputDialog(null, "Which hour?", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			hourValues, hourValues[10]);
			String hourStr=(String)selectedValue;
			hour=Integer.parseInt(hourStr);
			
			Object[] minValues = new Object[60]; 
			for(int i=0;i<60;i++){
				minValues[i]=""+i;
			}
			
			selectedValue = JOptionPane.showInputDialog(null, "Which minute?", "Please choose", 
			JOptionPane.INFORMATION_MESSAGE, null, 
			minValues, minValues[10]);
			String minStr=(String)selectedValue;
			min=Integer.parseInt(minStr);
			
			todayArran.movieToday.add(mov);
			
			showsToday.add(new Show(mov, scr, hour, min, day));
			CinemaIO.storeAllArrange();
			showBox.removeAllItems();
			
			for(int i=0;i<todayArran.showToday.size();i++){
				showBox.addItem(""+(i+1));
			}
			if(todayArran.showToday.isEmpty()){
				btn1.setEnabled(false);
			}
			ArrangeManagePanel.this.repaint();
			
		}
		
	}
	
	
	
	
}
