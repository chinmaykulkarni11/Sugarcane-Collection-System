import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;

public class DRMaster  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel S,K,TRC,B,TRU,L1,L2,L3,L4;	
	JTextField T1,T2,T3;  int flag=1;String[] S1={"2018","2019","2020"};
	JComboBox Combo1,Combo2,Combo3;
	JButton Save,Search,Update,Delete,New;
	
	//String[] S1={"1st","2nd","3rd","4th","5th"};
	String S2[] ={"1","2","3","4","5","6","7","8","9","10"};
	
	Connection con; Statement stmt; ResultSet rs;
	
	public DRMaster()
	{
		F=new JFrame();
		F.setVisible(true);
	//	F.setBounds(10,10,1200,900);
			F.setBounds(10,70,1200,650);
		F.setTitle("Distance Rate Operator");
		//F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		S= new JLabel("Season");
		K= new JLabel("Kilometers");
		TRC= new JLabel("Rate For Tractor ton/km");					T1=new JTextField();
		B= new JLabel("Rate For Bullock Cart ton/km ");				T2=new JTextField();
		TRU= new JLabel("Rate For Truck ton/km");					T3=new JTextField();
		
	
		// combobox setup
		Combo1= new JComboBox(S1);
		Combo1.setEditable(false);
		Combo1.addActionListener(this);
		Combo1.addItemListener(this);
		Combo1.setBounds(310,70,150,50);
		F.add(Combo1);
	
		Combo2=new JComboBox();
		Combo2.setEditable(true);
		Combo2.addActionListener(this);
		Combo2.addItemListener(this);
		Combo2.setBounds(10,70,150,50);
	//	F.add(Combo2);
			
		
		S.setBounds(90,50,200,80);				Combo1.setBounds(310,70,200,40);
		
		TRC.setBounds(90,150,350,80);			T1.setBounds(400,170,150,50);
		B.setBounds(90,250,350,80);				T2.setBounds(400,270,150,50); 
		TRU.setBounds(90,350,350,80);			T3.setBounds(400,370,150,50); 
		
	
	
	
	
	
	
	
	
	

		//setting font size
		Font font1=new Font("Arial",Font.PLAIN,20);
		S.setFont(font1);						
		K.setFont(font1);						
		TRC.setFont(font1);						T1.setFont(font1);
		B.setFont(font1);						T2.setFont(font1);
		TRU.setFont(font1);						T3.setFont(font1);
		
		//
		F.add(S);												
		F.add(TRC);								F.add(T1);
		F.add(B);								F.add(T2);
		F.add(TRU);								F.add(T3);
	
	
		Save=new JButton("Save");			Save.setBounds(100,500,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,500,100,40);			F.add(Search);
		Update=new JButton("Update");		Update.setBounds(500,500,100,40);			F.add(Update);
		Delete=new JButton("Delete");			Delete.setBounds(700,500,100,40);			F.add(Delete);
		New=new JButton("New");			New.setBounds(900,500,100,40);			F.add(New);
		
		Save.addActionListener(this);	Search.addActionListener(this);	Update.addActionListener(this);
		Delete.addActionListener(this);	New.addActionListener(this);	

		
		try{
			
			
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
			stmt=con.createStatement(); 
			
			
			if(con==null)
			{
				JOptionPane.showMessageDialog(null,"Unable to Connect");
			}
		}
		catch(ClassNotFoundException e)
	   {
			System.out.print("Not Connected....\n"+e);
	   }
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Problem in Database connection \n"+e);
		}
		


		//Referesh frame
		
		
	}
	
	
	public void itemStateChanged(ItemEvent evt)
	{		int id=-1;
			try
			{
				
				if(evt.getSource()==Combo1 && evt.getStateChange()==ItemEvent.SELECTED  && flag==1)
					{	
						String i= Combo1.getSelectedItem().toString().trim();
						id=Combo1.getSelectedIndex();
						if(i.length()>0)
						{
						int temp=Integer.parseInt(i);
						stmt=con.createStatement();
						rs=stmt.executeQuery("select * from DRMaster where Season="+temp);
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(2)+"");
							T2.setText(rs.getString(3)+"");
							T3.setText(rs.getString(4)+"");
						}flag=1;
						}
					}
			
					
			}	
				
				
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

			}
		
	}		
		
		public void actionPerformed(ActionEvent evt) 
		
	{	
			if (evt.getSource()==New)
			{		
				T1.setText("");T2.setText("");T3.setText("");
				Combo1.removeAllItems(); flag=0;
				Combo1.addItem(2018);Combo1.addItem(2019);Combo1.addItem(2020);flag=1;
			}
	
	
			
		if (evt.getSource()==Search)
		{	 int r=0; String z=Search.getText();
			  if( z.equals("Search"))	
			{
				
				try	
				{
					rs=stmt.executeQuery("select distinct Season from DRMaster"); 
				//	create table DRMaster(Season int,Km int,Truck int,Tractor int,Bullock int);
					Combo1.removeAllItems();
					
					while(rs.next())
					{	flag=0;Combo1.addItem(rs.getInt(1));
					}
						
						flag=1;Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}		 
		}
	
	
		
		if(evt.getSource()==Save)
		{
			int r=0; String z=Save.getText();
						if( z.equals("Save"))	
					{
			try	{	
								String U1=Combo1.getSelectedItem().toString().trim();
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText(); 
							
							int temp7=Integer.parseInt(U1); 
							int temp1=Integer.parseInt(TX1);	int temp2=Integer.parseInt(TX2);
							int temp3=Integer.parseInt(TX3);
					
				if(U1.length()>0)
			{  
	int r1=stmt.executeUpdate("insert into DRMaster values("+temp7+","+temp1+","+temp2+","+temp3+")");
			//create table DRMaster(Season int,Truck int,Tractor int,Bullock int);
	if(r1>0)	JOptionPane.showMessageDialog(null,"Record Inserted");									
				Combo1.setSelectedIndex(-1);
				T1.setText("");T2.setText("");T3.setText("");
			}
				}
					
		catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result ");
				}
					}	
				
		}		


		if(evt.getSource()==Delete)
		{
			String z=Delete.getText();
			if( z.equals("Delete"))	
			{
				try{
						String U1=Combo1.getSelectedItem().toString().trim();
						int temp8=Integer.parseInt(U1);
						if(U1.length()>0)
						{
							stmt=con.createStatement();
							int r1=stmt.executeUpdate("Delete from DRMaster where Season="+temp8);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");
					}

				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
		
		}


		if(evt.getSource()==Update)
		{
			int	r=0;
			String z=Update.getText();
			if( z.equals("Update"))	
			{		
				try{			String U1=Combo1.getSelectedItem().toString().trim();	
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText(); 
								 int temp7=Integer.parseInt(U1); 
								int temp1=Integer.parseInt(TX1);int temp2=Integer.parseInt(TX2);int temp3=Integer.parseInt(TX3);
								
								if(U1.length()==4)
								{
						if(U1.length()>0)
						{
			
	int r1=stmt.executeUpdate("Update DRMaster Set Truck="+temp1+", Tractor="+temp2+",Bullock="+temp3+"   where Season="+temp7);
//	create table DRMaster(Season int,Km int,Truck int,Tractor int,Bullock int);
	if(r1>0) JOptionPane.showMessageDialog(null,"Record Inserted");
						}
						Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");
							}
						else JOptionPane.showMessageDialog(null,"Enter 4 digit Season");			
					}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
		}


		
}}		



	
	
	
	
	
	
	
	
	
class DRMastertest
{
		public static void main(String args[])
		{
			DRMaster D=new DRMaster();
			
		}	
}	