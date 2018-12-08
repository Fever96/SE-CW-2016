package userInterface.customedPanels;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import cinemaArrange.Arrange;
import cinemaArrange.CinemaDirector;
import cinemaArrange.CinemaIO;
import cinemaArrange.Movie;
import cinemaArrange.Show;
import userInterface.MainInterface;

public class MovieManagePanel extends JPanel {
	
	private JComboBox<String> movieBox=new JComboBox<String>(); 
	private JPanel moviePanel=new JPanel();
	private MainInterface m;
	private String picAddress;
	JLabel BackPicture;
	JPanel controlPanel=new JPanel();
	JButton btn=new JButton("Modify existing movie");
	JButton btn1=new JButton("Creat new");
	JButton btn2=new JButton("Delete");
	Movie myMovie;
	JTextField tf1=new JTextField(60);
	JTextField tf2=new JTextField(60);
	JTextArea textAreaOutput;
	
	public MovieManagePanel(MainInterface m){
		this.m=m;
		
		go();
	}
	
	private void go(){
		CinemaDirector cd=CinemaDirector.getUniqueDirector();
		for(Movie m:cd.allMovies){
			movieBox.addItem(m.name);
		}
		btn.addActionListener(new ModifyListener());
		btn1.addActionListener(new CreatListener());
		btn2.addActionListener(new DeleteListener());
		controlPanel.add(movieBox);
		controlPanel.add(btn);
		controlPanel.add(btn1);
		controlPanel.add(btn2);
		controlPanel.setBounds(0, 0, (int)(MainInterface.WIDTH), (MainInterface.HEIGHT/10));
		
		moviePanel.setBounds(0, (int)(MainInterface.HEIGHT/10),MainInterface.WIDTH, (7*MainInterface.HEIGHT/8));
		
		this.add(controlPanel);
		this.add(moviePanel);
		this.setLayout(null);
		
	}
	public void creatNew(){
		picAddress="";
		moviePanel.removeAll();
		
		

		
		
		
		
		textAreaOutput = new JTextArea("", 300, 300);
		textAreaOutput.setSelectedTextColor(Color.RED);
		textAreaOutput.setLineWrap(true);        //activates the function of word wrapping
		textAreaOutput.setWrapStyleWord(true);            // activates the function that spacing at rows not words
		
		
		ImageIcon imge = new ImageIcon("default.jpg");  
		Image image=imge.getImage();
		Image scaledImage=image. getScaledInstance(100,150, Image.SCALE_DEFAULT);
		scaledImage=image. getScaledInstance(300,450, Image.SCALE_DEFAULT);
		ImageIcon betterImg=new ImageIcon(scaledImage);
		
		BackPicture = new JLabel(betterImg);   
	    JPanel picPanel=new JPanel();
	    
	    JLabel jl1=new JLabel("Name:");
	    
	    tf1.setText("");
	    jl1.setBounds(450, 8, 40, 10);
	    tf1.setBounds(500,0,100,30);
	    moviePanel.add(jl1);
	    moviePanel.add(tf1);
	    
	    JLabel jl2=new JLabel("Time:");
	    
	    tf2.setText("");
	    jl2.setBounds(450, 48, 40, 10);
	    tf2.setBounds(500,40,100,30);
	    moviePanel.add(jl2);
	    moviePanel.add(tf2);
	    
	    JLabel jl3=new JLabel("Movie info:");
	    jl3.setBounds(450, 78, 90, 10);
	    moviePanel.add(jl3);
	    
	    JButton okBtn=new JButton("Confirm");
	    okBtn.setBounds(480, 410, 100, 35);
	    okBtn.addActionListener(new CreatYesListener());
	    
	    moviePanel.add(okBtn);
	    JButton choosePicBtn=new JButton("Choose pic");
	    choosePicBtn.setBounds(600, 410, 100, 35);
	    choosePicBtn.addActionListener(new ChoosePicListener());
	    moviePanel.add(choosePicBtn);
	    
	    picPanel.add(BackPicture);
	    picPanel.setLayout(new GridLayout(1,1));
	    picPanel.setBounds(100, 0, 300, 450);
	    textAreaOutput.setBounds(450, 100, 300, 300);
	    moviePanel.add(picPanel);
		moviePanel.add(textAreaOutput);
		moviePanel.setLayout(null);
		moviePanel.repaint();
		moviePanel.setSize(MainInterface.WIDTH, (7*MainInterface.HEIGHT/8));
		m.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT+1);
		m.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
		
		
	}
	
	public void refresh(){
		picAddress="";
		moviePanel.removeAll();
		String name=(String) movieBox.getSelectedItem();
		CinemaDirector cd=CinemaDirector.getUniqueDirector();

		for(Movie m:cd.allMovies){
			if(m.name.equals(name)){
				myMovie=m;
				break;
			}
		}
		
		
		
		textAreaOutput = new JTextArea(myMovie.info, 300, 300);
		textAreaOutput.setSelectedTextColor(Color.RED);
		textAreaOutput.setLineWrap(true);        //activates the function of word wrapping
		textAreaOutput.setWrapStyleWord(true);            // activates the function that spacing at rows not words
		
		

		BackPicture = new JLabel(myMovie.betterImg);   
	    JPanel picPanel=new JPanel();
	    
	    JLabel jl1=new JLabel("Name:");
	    
	    tf1.setText(myMovie.name);
	    jl1.setBounds(450, 8, 40, 10);
	    tf1.setBounds(500,0,100,30);
	    moviePanel.add(jl1);
	    moviePanel.add(tf1);
	    
	    JLabel jl2=new JLabel("Time:");
	    
	    tf2.setText(myMovie.timeUsed+"");
	    jl2.setBounds(450, 48, 40, 10);
	    tf2.setBounds(500,40,100,30);
	    moviePanel.add(jl2);
	    moviePanel.add(tf2);
	    
	    JLabel jl3=new JLabel("Movie info:");
	    jl3.setBounds(450, 78, 90, 10);
	    moviePanel.add(jl3);
	    JButton okBtn=new JButton("Confirm");
	    okBtn.setBounds(480, 410, 100, 35);
	    okBtn.addActionListener(new ModifyYesListener());
	    moviePanel.add(okBtn);
	    JButton choosePicBtn=new JButton("Choose pic");
	    choosePicBtn.setBounds(600, 410, 100, 35);
	    choosePicBtn.addActionListener(new ChoosePicListener());
	    moviePanel.add(choosePicBtn);
	    
	    picPanel.add(BackPicture);
	    picPanel.setLayout(new GridLayout(1,1));
	    picPanel.setBounds(100, 0, 300, 450);
	    textAreaOutput.setBounds(450, 100, 300, 300);
	    moviePanel.add(picPanel);
		moviePanel.add(textAreaOutput);
		moviePanel.setLayout(null);
		moviePanel.repaint();
		moviePanel.setSize(MainInterface.WIDTH, (7*MainInterface.HEIGHT/8));
		m.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT+1);
		m.mainFrame.setSize(MainInterface.WIDTH, MainInterface.HEIGHT);
	}
	class ModifyListener implements ActionListener{

		

		@Override
		public void actionPerformed(ActionEvent e) {
			refresh();
		}
		
	}
	
	class CreatListener implements ActionListener{

		

		@Override
		public void actionPerformed(ActionEvent e) {
			creatNew();
		}
		
	}
	
	class ChoosePicListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileFilter(new ImageFilter());
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
			jfc.showDialog(new JLabel(), "choose");
			File file=jfc.getSelectedFile();
			picAddress=file.getAbsolutePath();
			JOptionPane.showMessageDialog(null, "Picture selected!", "", JOptionPane.INFORMATION_MESSAGE);	
			
			}
		
		class ImageFilter extends FileFilter
	    {
	 
	        public boolean accept(File f)
	        {
	            if (f.isDirectory())
	            {
	                return true;
	            }
	 
	            String extension = f.getName().substring(f.getName().lastIndexOf(".")+1);
	            if (extension != null)
	            {
	                if (extension.equals("gif") || extension.equals("jpeg")
	                        || extension.equals("jpg") || extension.equals("png"))
	                {
	                    return true;
	                }
	                else
	                {
	                    return false;
	                }
	            }
	            return false;
	        }
	 
	        public String getDescription()
	        {
	            return "picture file(*.jpg, *.jpeg, *.gif, *.png)";
	        }
	    }
		}
	
	
	class ModifyYesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!picAddress.equals("")){
				myMovie.setImg(picAddress);
			}
			
			
			myMovie.name=tf1.getText();
			myMovie.timeUsed=Integer.parseInt(tf2.getText());
			myMovie.info=textAreaOutput.getText();
			
			
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			
			movieBox.removeAllItems();
			for(Movie m:cd.allMovies){
				movieBox.addItem(m.name);
				if(m.name.equals(myMovie.name)){
					movieBox.setSelectedItem(m.name);
				}
			}
			CinemaIO.storeAllArrange();
			refresh();
			JOptionPane.showMessageDialog(null, "Modify success!", "", JOptionPane.INFORMATION_MESSAGE);	
		}
		
	}
	
	class CreatYesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(tf1.getText().equals("")||tf2.getText().equals("")||textAreaOutput.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please fill in all info!", "", JOptionPane.INFORMATION_MESSAGE);	
				return;
			}
			if(picAddress.equals("")){
				JOptionPane.showMessageDialog(null, "Please select a picture!", "", JOptionPane.INFORMATION_MESSAGE);	
				return;
			}
			myMovie=new Movie(tf1.getText());
			myMovie.setImg(picAddress);
			myMovie.timeUsed=Integer.parseInt(tf2.getText());
			myMovie.info=textAreaOutput.getText();
			
			movieBox.removeAllItems();
			CinemaDirector cd=CinemaDirector.getUniqueDirector();
			cd.allMovies.add(myMovie);
			for(Movie m:cd.allMovies){
				movieBox.addItem(m.name);
				if(m.name.equals(myMovie.name)){
					movieBox.setSelectedItem(m.name);
				}
			}
			CinemaIO.storeAllArrange();
			refresh();
			
		}
		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			refresh();
			int res=JOptionPane.showConfirmDialog(null, "Delete a movie will delete all the show of that movie! Continue?", "???", JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION){ 
				moviePanel.removeAll();
				String name=(String) movieBox.getSelectedItem();
				CinemaDirector cd=CinemaDirector.getUniqueDirector();

				for(Movie m:cd.allMovies){//find related movie
					if(m.name.equals(name)){
						myMovie=m;
						break;
					}
				}
				for(Arrange arran:cd.difDayArran){//delete all the shows of this particular movie	
					ArrayList<Show> showsToDelete=new ArrayList<Show>();
					ArrayList<Movie> movieToDelete=new ArrayList<Movie>();
					for(Show show:arran.showToday){
						if(show.movie.name.equals(myMovie.name)){
							showsToDelete.add(show);
						}
					}
					for(Movie mov:arran.movieToday){
						if(mov.name.equals(myMovie.name)){
							movieToDelete.add(mov);
						}
					}
					for(Show deletedShow:showsToDelete){
						arran.showToday.remove(deletedShow);
					}
					for(Movie deletedMovie:movieToDelete){
						arran.movieToday.remove(deletedMovie);
					}
				}
				cd.allMovies.remove(myMovie);
				movieBox.removeAllItems();
				for(Movie m:cd.allMovies){//reconstruct the box after deleting
					movieBox.addItem(m.name);
				}
				JOptionPane.showMessageDialog(null, "Movie deleted!", "", JOptionPane.INFORMATION_MESSAGE);	
				CinemaIO.storeAllArrange();
				refresh();
			}else{
				return;
			} 
			
		}
		
	}
}
