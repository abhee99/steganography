import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Break extends JFrame implements ActionListener
{
	private JLabel key, image;
	private JTextField tkey, timage;
	private JButton reveal, select;
	FileDialog fd;
	String filepath="";
	Break()
	{
		super("Break");
		getContentPane();
		setLayout(null);
		key = new JLabel("Secret Code");
		image = new JLabel("Image Path");
		tkey = new JTextField();
		timage = new JTextField();
		reveal = new JButton("Display");
		select = new JButton("CHOOSE");
		key.setBounds(50, 200, 200, 30);
		image.setBounds(50, 300, 200, 30);
		tkey.setBounds(280, 200, 200, 30);
		timage.setBounds(280, 300, 200, 30);
		reveal.setBounds(275, 400, 100, 50);
		select.setBounds(520, 300, 100, 30);
		select.addActionListener(this);
		add(key);
		add(tkey);
		add(image);
		add(timage);
		add(reveal);
		add(select);
		fd=new FileDialog(new JFrame());
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==select)
		{
			fd.setVisible(true);
			filepath+=fd.getDirectory()+fd.getFile();
			timage.setText(filepath);
		}
	}
	public static void main(String args[])
	{
		Break br = new Break();
		br.setSize(800, 800);
		br.setVisible(true);
		br.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}