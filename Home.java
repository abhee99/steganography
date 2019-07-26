import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener
{
	private JButton b1,b2;
	Home()
	{
		super("Steganography");
		getContentPane();
		setLayout(null);
		
		b1=new JButton("Compose");
		b2=new JButton("Break");
		b1.setBounds(150,275,200,80);
		b1.addActionListener(this);
		b2.setBounds(450,275,200,80);
		b2.addActionListener(this);
		add(b1);
		add(b2);
		
		
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==b1)
		{
			//this.dispose();
			Compose cp=new Compose();
			cp.setSize(800,800);
			cp.setVisible(true);
			cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		if(a.getSource()==b2)
		{
			Break b=new Break();
			b.setSize(800,800);
			b.setVisible(true);
			b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
			
			
	}
	
	public static void main(String arg[])
	{
		Home h=new Home();
		h.setSize(800,800);
		h.setVisible(true);
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}