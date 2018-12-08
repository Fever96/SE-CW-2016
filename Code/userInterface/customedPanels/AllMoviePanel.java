package userInterface.customedPanels;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.ArrayList;


import javax.swing.*;

import cinemaArrange.CinemaDirector;
import cinemaArrange.Movie;
/**
 * panel that can view all the information of movies
 * @author 
 *
 */
public class AllMoviePanel extends JPanel{

	
	ArrayList<Movie> allMovies=new ArrayList<Movie>();
	public AllMoviePanel(){
		this.setBackground(Color.BLUE);
		go();
	}
	private void go(){
		JPanel pan=new JPanel();
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		this.allMovies=cd.allMovies;
		JScrollPane scr1=new JScrollPane(pan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		for(Movie m:allMovies){
			JPanel moviePanel=new JPanel();
			String str="Movie:"+m.name+"\nMins:"+m.timeUsed+"\nSynopsis:\n"+m.info;
			JLabel movieString=new JLabel();
			movieString.setSize(300, 300);
			JlabelSetText(movieString,str);
			System.out.println(m.timeUsed);
			
			
			JTextArea textAreaOutput = new JTextArea(str, 20, 20);
			textAreaOutput.setSelectedTextColor(Color.black);
			textAreaOutput.setLineWrap(true);        //activates the function of word wrapping
			textAreaOutput.setWrapStyleWord(true);            // activates the function of spacing at rows no words
			textAreaOutput.setEditable(false);
			
			
			
			System.out.println(movieString.getText());
			
			JLabel BackPicture = new JLabel(m.img);   
		    JPanel picPanel=new JPanel();
		    picPanel.add(BackPicture);
		    //picPanel.setLayout(new GridLayout(1,2));
		    moviePanel.add(picPanel);
			moviePanel.add(textAreaOutput);
			pan.add(moviePanel);
			pan.setBackground(Color.white);
			moviePanel.setBackground(Color.white);
			
		}
		if(allMovies.size()%2==1){
			JPanel empty=new JPanel();
			empty.setBackground(Color.WHITE);
			pan.add(empty);
			
		}
		pan.setLayout(new GridLayout(0,2));
		this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.white);
		this.add(scr1);
	}
	
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
	                if (fontMetrics.charsWidth(chars, start, len) > jLabel.getWidth()) {
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
}
