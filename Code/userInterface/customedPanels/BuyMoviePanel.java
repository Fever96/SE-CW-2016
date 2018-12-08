package userInterface.customedPanels;

import java.awt.Color;
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
import cinemaArrange.Movie;
import cinemaArrange.Show;
import userInterface.MainInterface;
import userInterface.ScreenInterface;

public class BuyMoviePanel extends JPanel{
	

	private JComboBox<String> dayBox=new JComboBox<String>();
	private JComboBox<String> movieBox=new JComboBox<String>(); 
	private JComboBox<String> timeBox=new JComboBox<String>(); 
	private JPanel controlPanel=new JPanel();
	private JPanel moviePanel=new JPanel();
	private Arrange todayArran;
	private JButton btn=new JButton("Confirm");
	private MainInterface mi;

	public BuyMoviePanel(MainInterface m){
		this.mi=m;
		go();
	}
	private void go(){
		
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		
		dayBox.addItemListener(new DaySelectedListener());
		movieBox.addItemListener(new MovieSelectedListener());
		
		for(int i=0;i<cd.difDayArran.size();i++){
			String day=""+(i+1);
			dayBox.addItem(day);
		}
		
		
		dayBox.setSize(100, 40);
		
		JLabel jl1=new JLabel("Date:");
		JLabel jl2=new JLabel("Movie");
		JLabel jl3=new JLabel("Time");
		btn.addActionListener(new ConfirmListener());
		
		this.controlPanel.add(jl1);
		this.controlPanel.add(dayBox);
		this.controlPanel.add(jl2);
		this.controlPanel.add(movieBox);
		this.controlPanel.add(jl3);
		this.controlPanel.add(timeBox);
		this.controlPanel.add(btn);
		controlPanel.setBounds(0, 0, (int)(MainInterface.WIDTH), (MainInterface.HEIGHT/8));
		
		moviePanel.setBounds(0, (MainInterface.HEIGHT/8),(int)(8*MainInterface.WIDTH/8), (7*MainInterface.HEIGHT/8));
		
		this.add(controlPanel);
		this.add(moviePanel);
		this.setLayout(null);
		controlPanel.setBackground(Color.white);
		moviePanel.setBackground(Color.white);
		this.setBackground(Color.white);
		
		
	}
	
	class DaySelectedListener implements ItemListener{

		

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				btn.setEnabled(true);
				movieBox.removeAllItems();
				timeBox.removeAllItems();
				movieBox.setEnabled(true);
				
				CinemaDirector cd=CinemaDirector.getUniqueDirector();
				int daySelected=Integer.parseInt((String)dayBox.getSelectedItem())-1;
				
				System.out.println("Day selected"+daySelected);
				
				todayArran=cd.difDayArran.get(daySelected);
				System.out.println(todayArran.movieToday.isEmpty());
				if(todayArran.movieToday.isEmpty()){
					
					movieBox.setSelectedItem("NONE");
					movieBox.setEnabled(false);
					timeBox.setSelectedItem("NONE");
					timeBox.setEnabled(false);
					moviePanel.removeAll();
					btn.setEnabled(false);
					mi.mainFrame.setSize(MainInterface.WIDTH+1, MainInterface.HEIGHT+1);
					mi.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
					
					return;
				}
				ArrayList<String> movieNames=new ArrayList<String>();
				String temp=null;
				for(Movie m:todayArran.movieToday){
					if(movieNames.isEmpty()){
						movieBox.addItem(m.name);
						movieNames.add(m.name);
						System.out.println("adddddd");
					}else{
						for(int i=0;i<movieNames.size();i++){
							String str=movieNames.get(i);
							if(m.name.equals(str)){
								System.out.println(str);
								break;
							}else{
								if(i==movieNames.size()-1){
									System.out.println(str);
									movieBox.addItem(m.name);
									temp=m.name;
								}
							}
							
						}
						if(!(null==temp)){
							movieNames.add(temp);
							temp=null;
						}
						
					}
					
						
					
				}
			}
			
			
		}
		
	}
	
	class MovieSelectedListener implements ItemListener{

		private void JlabelSetText(JLabel jLabel, String longString)  {
	        StringBuilder builder = new StringBuilder("<html>");
	        char[] chars = longString.toCharArray();
	        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
	        int start = 0;
	        int len = 0;
	        while (start + len < longString.length()) {
	            while (true) {
	                len++;
	                if (start + len > longString.length())break;
	                if (fontMetrics.charsWidth(chars, start, len) 
	                        > jLabel.getWidth()) {
	                    break;
	                }
	            }
	            builder.append(chars, start, len-1).append("<br/>");
	            start = start + len - 1;
	            len = 0;
	        }
	        builder.append(chars, start, longString.length()-start);
	        builder.append("</html>");
	        jLabel.setText(builder.toString());
	    }

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				moviePanel.removeAll();
				timeBox.removeAllItems();
				timeBox.setEnabled(true);
				System.out.println("Movie selected");
				
				String movieName=(String) movieBox.getSelectedItem();
				ArrayList<Show> showsToday=todayArran.showToday;
				System.out.println(showsToday.isEmpty());
				if(showsToday.isEmpty()){
					timeBox.setSelectedItem("NONE");
					timeBox.setEnabled(false);
					mi.mainFrame.setSize(MainInterface.WIDTH+1, MainInterface.HEIGHT+1);
					mi.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
					
					return;
				}
				Movie myMovie=null;
				for(Show s:showsToday){
					if(movieName.equals(s.movie.name)){
						if(s.minute==0){
							timeBox.addItem(s.hour+":"+"00");
						}
						else{
							timeBox.addItem(s.hour+":"+s.minute);
						}
						myMovie=s.movie;
					}
				}
				
				String str="Movie:"+myMovie.name+"\nMins:"+myMovie.timeUsed+"\nSynopsis:\n"+myMovie.info;
				JLabel movieString=new JLabel();
				movieString.setSize(400, 300);
				JlabelSetText(movieString,str);
				//TODO
				
				JTextArea textAreaOutput = new JTextArea(str, 30, 30);
				textAreaOutput.setSelectedTextColor(Color.black);
				textAreaOutput.setLineWrap(true);        //activates the function of word wrapping
				textAreaOutput.setWrapStyleWord(true);            // activates the function that spacing at rows not words
				textAreaOutput.setEditable(false);
				
				
				JLabel BackPicture = new JLabel(myMovie.betterImg);   
			    JPanel picPanel=new JPanel();
			    picPanel.add(BackPicture);
			    picPanel.setLayout(new GridLayout(1,1));
			    picPanel.setBounds(60, 0, 300, 450);
			    textAreaOutput.setBounds(390, 0, 350, 400);
			    moviePanel.add(picPanel);
				moviePanel.add(textAreaOutput);
				moviePanel.setLayout(null);
				moviePanel.repaint();
				mi.mainFrame.setSize(MainInterface.WIDTH+1, MainInterface.HEIGHT+1);
				mi.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
				
			}
			
			
			
			
			
			
		}
		
	}
	class ConfirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int daySelected=Integer.parseInt((String)dayBox.getSelectedItem())-1;
			String time=(String) timeBox.getSelectedItem();
			String[] times=time.split(":");
			int hour=Integer.parseInt(times[0]);
			int min=Integer.parseInt(times[1]);
			
			
			if(daySelected==0){
				Calendar c = Calendar.getInstance();
				int nowHour = c.get(Calendar.HOUR_OF_DAY); 
				int nowMin = c.get(Calendar.MINUTE); 
				if(nowHour>hour){
					JOptionPane.showMessageDialog(null, "Time passed, can't buy!", "", JOptionPane.INFORMATION_MESSAGE);	
					return;
				}else if(nowHour==hour&&nowMin>min){
					JOptionPane.showMessageDialog(null, "Time passed, can't buy!", "", JOptionPane.INFORMATION_MESSAGE);	
					return;
				}
			}
			
			Show myShow=null;
			for(Show show:todayArran.showToday){
				if(show.hour==hour&&show.minute==min){
					myShow=show;
				}
			}
			
			new ScreenInterface(myShow,daySelected);
			
		}
		
	}
	
	
	
	
}
