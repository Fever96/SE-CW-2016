package cinemaArrange;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;

import javax.swing.ImageIcon;
/**
 * movie class
 * @author 
 *
 */
public class Movie implements Serializable {
	/**
	 * name of the movie
	 */
	public String name;
	/**
	 * information about the movie
	 */
	public String info="sahsgdcysdghjsgcjgvasdcgfsjcvsdjhcvsydcvsdhjvcsdhjcvsdhjcvsjdhcvjsdhvcjhdsvcjsdhcvsdjhcvdsjhcvdsjhcvsjhcvsjhdcvsdjhcvsjhdcvsjhcvsjhcvsjhcvsjhcvsdjcvdbcvsdugfsvnbsdjkfbiuerhfgsdkjvbkushgkuewnvsdkjvbiusgfivwehbvckjsdbcviuegfuiewbscvkjadsbcfuegukesbfkjsdgfugdasdasd";
	/**
	 * duration of the movie
	 */
	public int timeUsed=100;
	/**
	 * cover of the movie
	 */
	public ImageIcon img;
	/**
	 * high quality movie cover
	 */
	public ImageIcon betterImg;
	
	/**
	 * 
	 * @param s movie name
	 */
	public Movie(String s){
		this.name=s;
		ImageIcon imge = new ImageIcon("default.jpg");  
		Image image=imge.getImage();
		Image scaledImage=image. getScaledInstance(100,150, Image.SCALE_DEFAULT);
		this.img=new ImageIcon(scaledImage);
		scaledImage=image. getScaledInstance(300,450, Image.SCALE_DEFAULT);
		this.betterImg=new ImageIcon(scaledImage);
	}

	/**
	 * set the movie cover
	 * @param string the sore address of the jpg file
	 */
	public void setImg(String string) {
		ImageIcon imge = new ImageIcon(string);
		Image image=imge.getImage();
		Image scaledImage=image. getScaledInstance(100,150, Image.SCALE_DEFAULT);
		this.img=new ImageIcon(scaledImage);
		scaledImage=image. getScaledInstance(300,450, Image.SCALE_DEFAULT);
		this.betterImg=new ImageIcon(scaledImage);
		
	}

}
