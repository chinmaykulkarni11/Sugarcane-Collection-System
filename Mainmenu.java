
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;

public class Mainmenu extends JFrame implements ActionListener
{
	
	
	JMenuBar M ;
	JMenuItem 	VMaster,MMaster,HMaster,TMaster,DeMaster,DiMaster,RMaster,MD,TD,HD,W,BH,
				BHD,MDR,HDR,TDR,s1,s2,s3,s4,exit;	
	JMenu Master,Transactions,Reports,Exit;
	
	JTextField t;
	JFrame F;
	
	Connection con;
	Statement stmt;
	ResultSet rs;	
		
public	Mainmenu()
	
	{
		 M = new JMenuBar();
		
		 Master=new JMenu("Master         ");
		 Transactions=new JMenu("Bill Preparations      ");
		 Reports=new JMenu("Reports        ");
		 Exit=new JMenu("Exit           ");	
		
		VMaster=new JMenuItem("\nVillage Master\n");
		MMaster=new JMenuItem("Member Master");
		HMaster=new JMenuItem("Harvester Master");
		TMaster=new JMenuItem("Transporter Master");
		
		RMaster=new JMenuItem("Rate Master");
		DiMaster=new JMenuItem("Distance Master");
		
		MD=new JMenuItem("Member and Harvester Bill");
		TD=new JMenuItem("Transporter Bill");

		
		
		s1=new JMenuItem("Datevise Member and Harvester Bill");
		s2=new JMenuItem("Datevise Transporter Bill");
		
		exit=new JMenuItem("Exit");
		
				Font font1=new Font("Arial",Font.PLAIN,40);
				M.setFont(font1);
		M.add(Master);  //master menu added
		Master.add(VMaster); 					VMaster.addActionListener(this);
		Master.add(MMaster); 					MMaster.addActionListener(this);	
		Master.add(TMaster); 					TMaster.addActionListener(this);	
		Master.add(HMaster);					HMaster.addActionListener(this);
		Master.add(DiMaster);					DiMaster.addActionListener(this);	
		Master.add(RMaster);					RMaster.addActionListener(this);
		
		M.add(Transactions);  //Transactions menu added
		Transactions.add(MD);													MD.addActionListener(this);
		Transactions.add(TD);													TD.addActionListener(this);

	
		M.add(Reports);
		Reports.add(s1);														s1.addActionListener(this);
		Reports.add(s2);														s2.addActionListener(this);
		M.add(Exit);//  Exit added 
		Exit.add(exit);															exit.addActionListener(this);
		
		F=new JFrame();
		F.setJMenuBar(M); //MENU ADDED IN FRAME
		F.setBounds(10,10,500,500);
		F.setVisible(true);
		F.setTitle("Menu");
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
	ImageIcon img=new ImageIcon(ClassLoader.getSystemResource ("cane.jpg"));
	JLabel image = new JLabel(img);image.setBounds(0,00,480,480);
		F.add(image);	
		/*
	ImageIcon img=new ImageIcon(ClassLoader.getSystemResource ("M.jpg"));
	JLabel image = new JLabel(img);image.setBounds(0,00,1300,1300);
		F.add(image);
		//*/
		
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==VMaster)
		{
			new VMaster();
		
		}	
		
        
		else if(evt.getSource()==MMaster  )
		{
			new MMaster();
		}

        
		else if(evt.getSource()==TMaster   )
		{
			new TMaster();
		}
		
		 else if(evt.getSource()==HMaster   )
		
		{
             new HMaster();
		}

        
		
		
		 
		else if(evt.getSource()== DiMaster  )
		{
                                     
			 new DRMaster();
		}

    
    else if(evt.getSource()==RMaster  )
		{
                                     
			new RMaster();
		}

		
		else if(evt.getSource()==MD   )
		{
                       new MHbill();
			             
			 
		}

         else if(evt.getSource()==TD   )
		{
                                     
		new Transporterbill();	
		}
        
		 else if(evt.getSource()==s1 )
		{
                                     
			new MHReport();
			
		}
		
		 else if(evt.getSource()==s2)
		{
                                     
			new TReport();
		}
		
		
                       
		else if(evt.getSource()==exit   )
		{
                                    System.exit(0); 
		
		}
	}
	

	
}


 class Menutest
{
	public static void main(String args[])
	
	{
		Mainmenu M=new Mainmenu(); //object creation and constructor called
	}	
			
}