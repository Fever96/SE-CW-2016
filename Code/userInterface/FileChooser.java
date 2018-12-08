package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * interface for choosing files
 * @author 
 *
 */
public class FileChooser extends JFrame implements ActionListener{
	JButton open=null;
	
	public FileChooser(){
		open=new JButton("open");
		this.add(open);
		this.setBounds(400, 200, 100, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfc.showDialog(new JLabel(), "choose");
		File file=jfc.getSelectedFile();
		if(file.isDirectory()){
			System.out.println("package:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("file:"+file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
		
	}

}
