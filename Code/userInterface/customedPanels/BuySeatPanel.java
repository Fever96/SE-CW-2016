package userInterface.customedPanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;


public class BuySeatPanel extends JPanel {

	
	private static int GRID=50;
	
	public BuySeatPanel(){
		go();
	}
	
	public void go(){
		GridBagLayout layout=new GridBagLayout();
		GridBagConstraints sbc= new GridBagConstraints();
		sbc.fill = GridBagConstraints.BOTH; 
		sbc.weightx = 0;
		sbc.weighty=100;
		sbc.insets=new Insets(0,0,0,0);
		
		sbc.gridx=0;
		sbc.gridy=0;
		sbc.gridwidth=4;
		sbc.gridheight=6;
		sbc.ipadx=GRID*4;
		sbc.ipadx=GRID*6;
		
		JPanel moviePicPanel=new JPanel();
		moviePicPanel.add(new JButton("111111"));
		moviePicPanel.setBackground(Color.BLACK);
		this.add(moviePicPanel);
		layout.setConstraints(moviePicPanel, sbc);
		
		JPanel movieInfoPanel=new JPanel();
		this.add(movieInfoPanel);
		movieInfoPanel.setBackground(Color.GRAY);
		sbc.gridx=4;
		sbc.gridy=0;
		sbc.gridwidth=2;
		sbc.gridheight=6;
		sbc.ipadx=GRID*2;
		sbc.ipadx=GRID*6;
		layout.setConstraints(movieInfoPanel, sbc);
		
		JPanel ticketPanel=new JPanel();
		for(int i=0;i<6;i++){
			JButton b=new JButton(""+i);
			ticketPanel.add(b);
		}
		ticketPanel.setLayout(new GridLayout(1,6));
		this.add(ticketPanel);
		sbc.weightx = 100;
		sbc.weighty=0;
		sbc.gridx=0;
		sbc.gridy=6;
		sbc.gridwidth=6;
		sbc.gridheight=1;
		sbc.ipadx=GRID*6;
		sbc.ipadx=GRID*1;
		layout.setConstraints(ticketPanel, sbc);
		
		
		this.setLayout(layout);
	}
}
