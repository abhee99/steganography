import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.awt.image.*;

public class Compose extends JFrame implements ActionListener
{
	
	private JLabel info, image;
	private JTextField tinfo, timage;
	private JButton hide, select;
	FileDialog fd;
	String filepath="";
	static Compose com;
	
	
	int iw,ih;
	int []pix,t;
	int key;
	int offset=0;
	
	
	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	Compose(){
		super("Compose");
		getContentPane();
		setLayout(null);
		info = new JLabel("Secret Information");
		image = new JLabel("Image Path");
		tinfo = new JTextField();
		timage = new JTextField();
		hide = new JButton("HIDE");
		select = new JButton("CHOOSE");
		info.setBounds(50, 200, 200, 30);
		image.setBounds(50, 300, 200, 30);
		tinfo.setBounds(280, 200, 200, 30);
		timage.setBounds(280, 300, 200, 30);
		hide.setBounds(275, 400, 100, 50);
		select.setBounds(520, 300, 100, 30);
		select.addActionListener(this);
		hide.addActionListener(this);
		add(info);
		add(tinfo);
		add(image);
		add(timage);
		add(hide);
		add(select);
		fd=new FileDialog(new JFrame());
		
	}
	class MyException extends Exception
	{
		public MyException(String s)
		{
			super(s);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==select)
		{
			fd.setVisible(true);
			filepath=fd.getDirectory()+fd.getFile();
			timage.setText(filepath);
			if(!filepath.equals("nullnull"))
				System.out.println(filepath);
			else
				timage.setText("");
		}
		else if(e.getSource()==hide)
		{
			
			try
			{
				if(tinfo.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Please Enter the Secret Information");
					throw (new MyException("Enter the Secret Information"));
				}
				if(timage.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Please Select Image");
					throw (new MyException("Select The Image"));
				}
				String s=randomAlphaNumeric(10);
				String sec_info=tinfo.getText();
				sec_info=s+sec_info;
				byte []secret=s.getBytes();
				
				JOptionPane.showMessageDialog(null,"your secret code: "+s+"");
				String filepath1=timage.getText();
				File f=new File(filepath1);
				BufferedImage imgOrigin=ImageIO.read(f);
				BufferedImage img=new BufferedImage(imgOrigin.getWidth(),imgOrigin.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
				//Graphics2D gr=img.createGraphics();
				//gr.drawRenderedImage(img,null);
				//gr.dispose();
				
				
				WritableRaster rs=img.getRaster();
				DataBufferByte bf=(DataBufferByte)rs.getDataBuffer();
				
				byte []img_byte=bf.getData();
					System.out.println(img_byte.length);
				if(secret.length+offset>img_byte.length)
					throw new IllegalArgumentException("File Not Long Enough");
				for(int i=0;i<secret.length;i++)
				{
					int a=secret[i];
					for(int bit=7;bit>=0;bit--,offset++)
					{
						int b=(a>>>bit)&1;
						//img_byte[offset]=(byte)((img_byte[offset]&0xFE)|b);
					}
				}
				System.out.println("Before Image");
				//System.out.println ("Img" + img_byte);
				BufferedImage saveBI= ImageIO.read(new ByteArrayInputStream(img_byte));
				System.out.println (saveBI);
				ImageIO.write(saveBI,"png",new File("D:\\"));
				System.out.println("Image Written");
				
				
				
			

			
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			
		}
		
		
	}
	 public static String randomAlphaNumeric(int count)
    {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0)
        {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
	public static void main(String args[])
	{
		com = new Compose();
		com.setSize(800, 800);
		com.setVisible(true);
		com.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

	