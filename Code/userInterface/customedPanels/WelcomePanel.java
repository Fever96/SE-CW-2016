package userInterface.customedPanels;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userInterface.MainInterface;
/**
 * default interface when entering this system
 * @author 
 *
 */
public class WelcomePanel extends JPanel {

	
	public WelcomePanel(){
		ImageIcon img=new ImageIcon("background.jpg");
		Image image=img.getImage();
		Image scaledImage=image. getScaledInstance(MainInterface.WIDTH,MainInterface.HEIGHT, Image.SCALE_DEFAULT);
		img=new ImageIcon(scaledImage);
		JLabel picLabel=new JLabel(img);
		this.add(picLabel);
		
	}
	
}
