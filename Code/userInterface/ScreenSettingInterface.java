package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

import cinemaArrange.CinemaDirector;
import cinemaArrange.CinemaIO;
import cinemaArrange.Screen;



/**
 * theater modify class
 * @author 
 *
 */
public class ScreenSettingInterface {

	private Screen scrn;
	private int row;
	private int cal;
	public JFrame screenSettingFrame=new JFrame();
	public JPanel placePanel=new JPanel();
	public JPanel controlPanel=new JPanel();
	public JPanel dragSeatPanel;
	public ArrayList<JPanel> panelList=new ArrayList<JPanel>();
	public ArrayList<JPanel> seatPanelList=new ArrayList<JPanel>();
	/**
	 * the one-one relation between the number of the block and the block coordinates
	 */
	public HashMap<Integer,Integer[]> gridPlace=new HashMap<Integer,Integer[]>();
	/**
	 * whether the one-one relation between the block number and block itself is based on the seat condition
	 */
	public HashMap<Integer,Boolean> seatPlace=new HashMap<Integer,Boolean>();
	private static int ONE_GRID=50;
	
	/**
	 * build a new screen
	 * @param x number of columns
	 * @param y number of rows
	 */
	public ScreenSettingInterface(int x,int y){
		this.cal=x;
		this.row=y;
		build();
	}
	/**
	 * modify an old screen
	 * @param s screen waiting to be modify
	 */
	public ScreenSettingInterface(Screen s){
		this.scrn=s;
		this.cal=s.layout[0].length;
		this.row=s.layout.length;
		build();
	}
	
	public void build(){
		
		
		
		placePanel.setBounds(0, 0, cal*ONE_GRID, row*ONE_GRID);
		placePanel.setBackground(Color.gray);
		
		JButton addBtn=new JButton("ADD SEAT");
		JButton rmvBtn=new JButton("REMOVE SEAT");
		JButton genBtn=new JButton("APPLY CHANGES");
		addBtn.addActionListener(new AddSeatListener());
		genBtn.addActionListener(new GenerateListener());
		rmvBtn.addActionListener(new RemoveSeatListener());
		controlPanel.add(addBtn);
		controlPanel.add(rmvBtn);
		controlPanel.add(genBtn);
	
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setBounds(0, row*ONE_GRID, cal*ONE_GRID, 2*ONE_GRID);
		controlPanel.setLayout(new FlowLayout());
		int count=0;
		for(int i=0;i<row;i++){//generates the block with different colors based on rows and columns
			
			for(int j=0;j<cal;j++){
				JPanel tempPanel = new JPanel();
				if((i+j)%2==1){//is odd
					tempPanel.setBackground(Color.gray);
				}
				else{
					tempPanel.setBackground(Color.LIGHT_GRAY);
				}
				panelList.add(tempPanel);
				placePanel.add(tempPanel);
				
				Integer[] xy={j*ONE_GRID,i*ONE_GRID};
				
				
				
				
				gridPlace.put(count, xy);
				seatPlace.put(count, false);
				
				
				count++;
			}
		}
			
		placePanel.setLayout(new GridLayout(row,cal+2));
		
		
		
		screenSettingFrame.getLayeredPane().setLayout(null);
		screenSettingFrame.getLayeredPane().add(placePanel, 1);
		screenSettingFrame.getLayeredPane().add(controlPanel,1);
		
		if(null==scrn){//When new screen has been built, put seats in 70% position so that the manager can modify easily
			for(int i=0;i<row;i++){
				
				for(int j=0;j<cal;j++){
					if(Math.random()>0.3){
						dragSeatPanel = new JPanel();
						dragSeatPanel.setBackground(Color.WHITE);
						dragSeatPanel.setBounds(j*ONE_GRID, i*ONE_GRID, ONE_GRID, ONE_GRID);
						screenSettingFrame.getLayeredPane().add(dragSeatPanel,0);
						dragSeatPanel.setOpaque(false);
						ImageIcon img = new ImageIcon("seat.jpg");  //picture of the chairs
						Image image=img.getImage();
						Image scaledImage=image. getScaledInstance(ONE_GRID,ONE_GRID, Image.SCALE_DEFAULT);
						img=new ImageIcon(scaledImage);
					    JLabel BackPicture = new JLabel(img);   
					    dragSeatPanel.add(BackPicture);
					    dragSeatPanel.setLayout(new GridLayout(1,1));
						
						MyListener m = new MyListener();
						dragSeatPanel.addMouseListener(m);
						dragSeatPanel.addMouseMotionListener(m);
						this.seatPanelList.add(dragSeatPanel);
					}
					
				}
			}
		}
		else{//If the manager wants to modify the olf screen, then display all the seats
			
			for(int i=0;i<row;i++){
				
				for(int j=0;j<cal;j++){
					if(scrn.layout[i][j]==1){
						dragSeatPanel = new JPanel();
						dragSeatPanel.setBackground(Color.blue);
						
						dragSeatPanel.setBounds(j*ONE_GRID, i*ONE_GRID, ONE_GRID, ONE_GRID);
						
						dragSeatPanel.setOpaque(false);
						ImageIcon img = new ImageIcon("seat.jpg");  //picture of chairs
						Image image=img.getImage();
						Image scaledImage=image. getScaledInstance(ONE_GRID,ONE_GRID, Image.SCALE_DEFAULT);
						img=new ImageIcon(scaledImage);
					    JLabel BackPicture = new JLabel(img);   
					    dragSeatPanel.add(BackPicture);
					    dragSeatPanel.setLayout(new GridLayout(1,1));
					    screenSettingFrame.getLayeredPane().add(dragSeatPanel,0);
						MyListener m = new MyListener();
						dragSeatPanel.addMouseListener(m);
						dragSeatPanel.addMouseMotionListener(m);
						this.seatPanelList.add(dragSeatPanel);
					}
					
				}
			}
		}
		
		
		screenSettingFrame.setSize(cal*ONE_GRID, row*ONE_GRID+3*ONE_GRID);
		screenSettingFrame.setVisible(true);
		
	}
	

	
	class MyListener extends MouseAdapter{

		//these two coordinates are the position where your mouse click and drag
		int newX,newY,oldX,oldY;
		//these two coordinates are the current position of units
		int startX,startY;
		
		int realX,realY;
		@Override
		public void mousePressed(MouseEvent e) {
		//source of event unit
		Component cp = (Component)e.getSource();
		//record the current position of mouse and current coordinates when the mouse is clicked
		startX = cp.getX();
		startY = cp.getY();
		oldX = e.getXOnScreen();
		oldY = e.getYOnScreen();
		}


		@Override
		public void mouseDragged(MouseEvent e) {
		Component cp = (Component)e.getSource();
		//record new coordinates when dragging
		newX = e.getXOnScreen();
		newY = e.getYOnScreen();
		//set up bounds to add the distance of dragging to the beginning coordinates
		realX=startX+(newX - oldX);
		realY=startY+(newY - oldY);
		
		cp.setBounds(realX, realY, cp.getWidth(), cp.getHeight());
		}
		
		
		@Override
		public void mouseReleased(MouseEvent e){
			
			Component cp = (Component)e.getSource();
			int[] magXY=magnaticXY(realX,realY);
			int magX=magXY[0];
			int magY=magXY[1];
			cp.setBounds(magX, magY, cp.getWidth(), cp.getHeight());
		}

		private int[] magnaticXY(int x,int y){//determine which block the mouse moves to by calculating the distance of seats position
			HashMap<Integer,Integer[]> gridXY =ScreenSettingInterface.this.gridPlace;
			HashMap<Integer,Integer> diffXY =new HashMap<Integer,Integer>();
			
			
			Iterator iter = gridXY.entrySet().iterator();
        	while (iter.hasNext()) {
        		Map.Entry entry = (Map.Entry) iter.next();
        		Object key = entry.getKey();
        		Object val = entry.getValue();
        		int num=(int)key;
        		Integer[] XY=(Integer[])val;
        		int magnaticX=XY[0];
        		int magnaticY=XY[1];
        		int  xyDif=Math.abs(magnaticX-x)+Math.abs(+magnaticY-y);//gain the distance from all blocks
        		diffXY.put(xyDif, num);
        		
        		
        	}
        	
        	Set<Integer> set = diffXY.keySet();
        	Object[] obj = set.toArray();
        	Arrays.sort(obj);
        	int minDiff=(int)obj[0];
        	int numKey=diffXY.get(minDiff);
        	
        	Integer[] XY=(Integer[])gridXY.get(numKey);
        	int magnaticX=XY[0];
    		int magnaticY=XY[1];
    		int[] finalXY={magnaticX,magnaticY};//the block it decides to choose
    		
    		for(JPanel seatPanel:ScreenSettingInterface.this.seatPanelList){//if the block has already be attached to, then go back to beginning position
				int existX=seatPanel.getX();
				int existY=seatPanel.getY();
				if(magnaticX==existX&&magnaticY==existY){
					int[] start={0,(row+1)*ONE_GRID};
					return start;
					
				}
			}
    		
    		return finalXY;
        	
        	
			
			
		}

	}
	
	class AddSeatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {//add new chairs
			
			dragSeatPanel = new JPanel();
			dragSeatPanel.setBackground(Color.WHITE);
			dragSeatPanel.setBounds((int)(60*Math.random()), (int)(50+30*Math.random()+(row)*ONE_GRID), ONE_GRID, ONE_GRID);
			
			dragSeatPanel.setOpaque(false);
			ImageIcon img = new ImageIcon("seat.jpg");  //picture of chairs
			Image image=img.getImage();
			Image scaledImage=image. getScaledInstance(ONE_GRID,ONE_GRID, Image.SCALE_DEFAULT);
			img=new ImageIcon(scaledImage);
		    JLabel BackPicture = new JLabel(img);   
		    dragSeatPanel.add(BackPicture);
		    dragSeatPanel.setLayout(new GridLayout(1,1));
		    screenSettingFrame.getLayeredPane().add(dragSeatPanel,0);
		    
			MyListener m = new MyListener();
			dragSeatPanel.addMouseListener(m);
			dragSeatPanel.addMouseMotionListener(m);
			ScreenSettingInterface.this.seatPanelList.add(dragSeatPanel);
			
		}
		
	}
	
	class RemoveSeatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {//remove the last chair that being added
			
			dragSeatPanel=ScreenSettingInterface.this.seatPanelList.get(ScreenSettingInterface.this.seatPanelList.size()-1);
			screenSettingFrame.getLayeredPane().remove(dragSeatPanel);
			ScreenSettingInterface.this.seatPanelList.remove(dragSeatPanel);
			screenSettingFrame.repaint();
			
			
		}
		
	}
	
	class GenerateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for(JPanel seatPanel:ScreenSettingInterface.this.seatPanelList){
				int x=seatPanel.getX();
				int y=seatPanel.getY();
				HashMap<Integer,Integer[]> gridXY =ScreenSettingInterface.this.gridPlace;
				
				Iterator iter = gridXY.entrySet().iterator();
	        	while (iter.hasNext()) {
	        		Map.Entry entry = (Map.Entry) iter.next();
	        		Object key = entry.getKey();
	        		Object val = entry.getValue();
	        		int numKey=(int)key;
	        		Integer[] XY=(Integer[])val;
	        		int magnaticX=XY[0];
	        		int magnaticY=XY[1];
	        		if(magnaticX==x&&magnaticY==y){
	        			ScreenSettingInterface.this.seatPlace.remove(numKey);
	                	ScreenSettingInterface.this.seatPlace.put(numKey, true);
	        		}
	        		
	        		
	        		
	        	}
			}
			int[][] screen = new int[row][cal];
		
			for(int i=0;i<row;i++){
				for(int j=0;j<cal;j++){
					if(ScreenSettingInterface.this.seatPlace.get(i*cal+j)){
						screen[i][j]=1;
					}
					else{
						screen[i][j]=0;
					}
				}
			}
			
			
			for(int i=0;i<row;i++){
				
				for(int j=0;j<cal;j++){
					ScreenSettingInterface.this.seatPlace.remove(i*cal+j);
                	ScreenSettingInterface.this.seatPlace.put(i*cal+j, false);
					
				}
			
			}
			
			if(null==ScreenSettingInterface.this.scrn){//determine whether the screen is going to be establish
				CinemaDirector cd=CinemaDirector.getUniqueDirector();
				String id=""+(cd.allScreen.size()+1);
				Screen scr=new Screen(screen,id);
				cd.allScreen.add(scr);
				System.out.println("new screen");
				CinemaIO.storeAllArrange();
				new ScreenInterface(scr);
			}
			else{//modify current screen
				scrn.layout=screen;
				CinemaIO.storeAllArrange();
				new ScreenInterface(scrn);
			}
			
			
			
			
			
			
		}
		
	}
	
	
	
}
