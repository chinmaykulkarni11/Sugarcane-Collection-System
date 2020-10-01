import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Transporterbill  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Billid,Mid,MVid,Tid,Season,Truck,Tractor,Bullock,TotalT,TotalA,Bdate;	
	JTextField T1,T2,T3,T5,T4,T6;
	JComboBox Combo1,Combo2,Combo3,Combo4,Combo5,Combo6;
	JButton Save,Search,New,Delete,Calculate;
	Connection con;
	Statement stmt;
	ResultSet rs;String[] S1={"2017","2018","2019","2020","2021"};
	int flag=1;
	int d1,r1,r2,r3;
							float tons,amount;
	
	public Transporterbill()
	{
		F=new JFrame();
		F.setVisible(true);
		F.setBounds(10,70,1200,1000);
		F.setTitle("Transporter Bill");
		F.setLayout(null);
		
		Billid=new JLabel("Bill Id");
		Mid= new JLabel("Member Id");
		MVid= new JLabel("Village Id");					T1=new JTextField();T5=new JTextField();		
		Tid= new JLabel(" Transporter Id");				T2=new JTextField();		
		Truck= new JLabel("Truck Tonnage");			    T3=new JTextField();T6=new JTextField();
		Tractor=new JLabel("Tractor Tonnage");			T4=new JTextField();
		Season= new JLabel("Season");
		Bullock= new JLabel("Bullock Cart Tonnage");				
		Bdate= new JLabel("TDate");
		TotalA=new JLabel("Total Amount");
		TotalT=new JLabel("Total ton");
		
		
		// combobox setup0
		Combo1= new JComboBox();
		Combo1.setEditable(true);
		Combo1.addActionListener(this);
		Combo1.addItemListener(this);
		F.add(Combo1);
	
		Combo2=new JComboBox();
		Combo2.setEditable(true);
		Combo2.addActionListener(this);
		Combo2.addItemListener(this);
		F.add(Combo2);
			
		Combo3=new JComboBox();
		Combo3.setEditable(true);
		Combo3.addActionListener(this);
		Combo3.addItemListener(this);
		F.add(Combo3);
			
		Combo4=new JComboBox(S1);
		Combo4.setEditable(false);
		Combo4.addActionListener(this);
		Combo4.addItemListener(this);
		F.add(Combo4);
			

		
	
//		JLabel Billid,Mid,MVid,Tid,Season,Truck,Tractor,Bullock,TotalT,TotalA,Bdate;		
		Billid.setBounds(90,50,200,80);			Combo1.setBounds(310,70,200,40);
		Mid.setBounds(600,50,200,80);			Combo2.setBounds(820,70,200,40);
		Tid.setBounds(90,150,200,80);			Combo3.setBounds(310,170,200,40);
		Season.setBounds(600,150,200,80);		Combo4.setBounds(820,170,200,40); 
		Truck.setBounds(90,250,200,80);			T1.setBounds(310,270,200,40);
		Tractor.setBounds(600,250,200,80);		T2.setBounds(820,270,150,40);
		Bullock.setBounds(90,350,200,80);		T3.setBounds(310,370,150,40);		
		Bdate.setBounds(600,350,200,80);		T4.setBounds(820,370,150,40);		
		TotalT.setBounds(90,450,200,80);		T5.setBounds(310,470,150,40);		
		TotalA.setBounds(600,450,200,80);		T6.setBounds(820,470,150,40);
		
		//setting font size
		
		Font font1=new Font("Arial",Font.PLAIN,20);
		Billid.setFont(font1);
		Mid.setFont(font1);			T1.setFont(font1);T5.setFont(font1);
		MVid.setFont(font1);			T2.setFont(font1);T4.setFont(font1);T6.setFont(font1);
		Bullock.setFont(font1);			T3.setFont(font1);
		Tid.setFont(font1);				TotalT.setFont(font1);			TotalA.setFont(font1);			
		Truck.setFont(font1);		
		Tractor.setFont(font1); 
		Bdate.setFont(font1); 
		Season.setFont(font1);

		//Adding in Frame
		F.add(Billid);F.add(Mid);F.add(Tid);F.add(Truck);F.add(Tractor);F.add(Bdate);F.add(Season);
        F.add(T1);F.add(T2);F.add(T3);F.add(T4);/*F.add(T5);F.add(T6);*/F.add(Bullock);F.add(TotalT);F.add(TotalA);
	
		//
		Save=new JButton("Save");			Save.setBounds(100,550,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,550,100,40);			F.add(Search);
		Calculate=new JButton("Calculate");	Calculate.setBounds(500,550,100,40);		F.add(Calculate);
		Delete=new JButton("Delete");		Delete.setBounds(700,550,100,40);			F.add(Delete);
		New=new JButton("New");		New.setBounds(900,550,100,40);			F.add(New);
						
						/*String pattern="dd/MM/yyyy";
		     		SimpleTDateFormat format=new SimpleTDateFormat(pattern);		
						TDate date1=format.parse("31/12/2018");
		     			String N=format.format(new TDate());
		     			WsTDate.setText(N + "");
			*/
			
			
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T4.setText(sdf.format(date));
		Save.addActionListener(this);	
		Search.addActionListener(this);
		New.addActionListener(this);
		Delete.addActionListener(this);	
		Calculate.addActionListener(this);
	
	try{
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=CollegeDB.mdb;DriverID=22;READONLY=true}" ,"","");
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Oracle in OraHome90}","scott","tiger");

			Class.forName("org.postgresql.Driver");
		
			//con = DriverManager.getConnection("jdbc:postgresql://192.168.100.10/tybcs16/MMaster","tybcs16","");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
			
			stmt=con.createStatement();
			//Class.forName("com.mysql.jdbc.Driver"); //mysql-connector.jar file required
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/collegedb","root","root");
			
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
	
		
		
		
		
		
				try
				{
					
					rs=stmt.executeQuery("select Mid from MMaster"); 
					while(rs.next())
					{	flag=0;
						Combo2.addItem(rs.getInt(1));
					
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");T6.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
		
				try
				{
					rs=stmt.executeQuery("select Tid from TMaster"); 
					while(rs.next())
					{	flag=0;
						Combo3.addItem(rs.getInt(1));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
		
			try
				{
					rs=stmt.executeQuery("select max(Billid) from Transporterbill"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
			
			
				
		
		
	}
	public void itemStateChanged(ItemEvent evt) 
	
	{	int id=-1;
			try{
				if(evt.getSource()==Combo1 && evt.getStateChange()==ItemEvent.SELECTED  && flag==1)
					{	
						String i= Combo1.getSelectedItem().toString().trim();
						id=Combo1.getSelectedIndex();
						if(i.length()>0)
						{
						int temp=Integer.parseInt(i);
						stmt=con.createStatement();
						rs=stmt.executeQuery("select * from Transporterbill where Billid="+temp);
		//create table Transporterbill(Wsid int,Mid int ,Vid int ,Hid int ,Tid int,Tonnage float,Season int ,Billno int ,Wsdate date);
						if(rs.next())
						{flag=0;
							Combo2.setSelectedIndex(id);
							Combo3.setSelectedIndex(id);
							Combo4.setSelectedIndex(id);
							T1.setText(rs.getString(5)+"");
							T2.setText(rs.getString(6)+"");
							T3.setText(rs.getString(7)+"");
							T4.setText(rs.getString(8)+"");
							T5.setText(rs.getString(9)+"");T6.setText(rs.getString(10)+"");
							F.add(T5);F.add(T6);
				
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
		
		if (evt.getSource()==Search)
		{	 int r=0; String z=Search.getText();
			  if( z.equals("Search"))	
			{
				try
				{
				
					rs=stmt.executeQuery("select Billid,Mid,Tid,Season from Transporterbill");
						//Transporterbill(Billid int,Mid int,tid int,Season int,Truck float,Tractor float,Bullock int,TDate date,TotalT int,TotalA int); 
					Combo1.removeAllItems();
					Combo2.removeAllItems();
					Combo3.removeAllItems();
					
					while(rs.next())
					{	flag=0;
						Combo1.addItem(rs.getInt(1));Combo2.addItem(rs.getInt(2));Combo3.addItem(rs.getInt(3));Combo4.addItem(rs.getInt(4));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
			T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}		 
		}
		

		if(evt.getSource()==Save)
		{	int r=0; String z=Search.getText();
				try
				{	String U1=Combo1.getSelectedItem().toString().trim();String U2=Combo2.getSelectedItem().toString().trim();
						String U3=Combo3.getSelectedItem().toString().trim();String U4=Combo4.getSelectedItem().toString().trim();
						if(U1.length()>0&&U2.length()>0&&U3.length()>0&&U4.length()>0)
					{	
						String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText();
						String TX5=T5.getText();String TX6=T6.getText();
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U2);
						int temp3=Integer.parseInt(U3);int temp4=Integer.parseInt(U4);
						float temp6=Float.parseFloat(TX1);float temp7=Float.parseFloat(TX2);float temp8=Float.parseFloat(TX3);
						float temp9=Float.parseFloat(TX5);float temp10=Float.parseFloat(TX6);
		
		//Transporterbill(Billid int,Mid int,tid int,Season int,Truck float,Tractor float,Bullock int,TDate date,TotalT int,TotalA int); 
					int r1=stmt.executeUpdate("insert into Transporterbill values("+temp1+","+temp2+","+temp3+","+temp4+","+temp6+","+temp7+","+temp8+",'"+TX4+"',"+temp9+","+temp10+")");
	if(r1>0)	JOptionPane.showMessageDialog(null," Record Inserted ");
									}
				
									Combo1.removeAllItems();Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
									T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
						
						
				try
				{
					rs=stmt.executeQuery("select max(Billid) from Transporterbill"); 
					while(rs.next())
					{	flag=0;int p=(rs.getInt(1));p=p+1;Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
				}
					catch(Exception e)
					{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
					}
		
					
			}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		}	
		

			
			else if(evt.getSource()==New)
			{
					Combo1.removeAllItems();Combo2.removeAllItems();Combo3.removeAllItems();Combo4.removeAllItems();Combo4.addItem(2017);
					T5.setText("");T6.setText("");
			try
				{
					
					rs=stmt.executeQuery("select Mid from MMaster"); 
					while(rs.next())
					{	flag=0;
						Combo2.addItem(rs.getInt(1));
					
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");T6.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
		
				try
				{
					rs=stmt.executeQuery("select Tid from TMaster"); 
					while(rs.next())
					{	flag=0;
						Combo3.addItem(rs.getInt(1));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
		
			try
				{
					rs=stmt.executeQuery("select max(Billid) from Transporterbill"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T5.setText("");
						
						
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T4.setText(sdf.format(date));	
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
			
				Combo4.addItem(2018);Combo4.addItem(2019);Combo4.addItem(2020);Combo4.addItem(2021);
				
		
			}
		
		
		
		
		
		
		
		else if(evt.getSource()==Delete)
			{
				try{
						String U1=Combo1.getSelectedItem().toString().trim();
					
						int temp1=Integer.parseInt(U1);
						if(U1.length()>0)
						{
							stmt=con.createStatement();
							int r1=stmt.executeUpdate("Delete from Transporterbill where Billid="+temp1);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);Combo4.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
						}

				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

				}
			}
				 
		
		if(evt.getSource()==Calculate)
			
		{			System.out.println("1");
		F.remove(T5);F.remove(T6);System.out.println("2");
			int r=0;  String z=Search.getText();
			
				try{	String U1=Combo1.getSelectedItem().toString().trim();String U2=Combo2.getSelectedItem().toString().trim();
						String U3=Combo3.getSelectedItem().toString().trim();String U4=Combo4.getSelectedItem().toString().trim();
						if(U1.length()>0&&U2.length()>0&&U3.length()>0&&U4.length()>0)
					{	
						String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText();
						
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U2);
						int temp3=Integer.parseInt(U3);int temp4=Integer.parseInt(U4);
						float temp6=Float.parseFloat(TX1);float temp7=Float.parseFloat(TX2);float temp8=Float.parseFloat(TX3);
						rs=stmt.executeQuery("select MVid from MMaster where Mid="+temp2);
						if(rs.next())
							{ int a=Integer.parseInt(rs.getString(1)); 
								//	create table VMaster(Vid int,Vname varchar(20),Vdistance float);
							rs=stmt.executeQuery("select Vdistance from VMaster where Vid="+a);
								if(rs.next())
								{d1=Integer.parseInt(rs.getString(1));System.out.println("3");
								}
							}
						//create table DRMaster(Season int,Km int,Truck int,Tractor int,Bullock int);
						rs=stmt.executeQuery("select Truck,Tractor,Bullock from DRMaster where Season="+temp4);
						if(rs.next())
						{  r1=Integer.parseInt(rs.getString(2));  r2=Integer.parseInt(rs.getString(2));System.out.println("4");
						   r3=Integer.parseInt(rs.getString(3));System.out.println("41");
							tons=temp6+temp7+temp8;	System.out.println("5");
							amount=(temp6*r1)+(temp7*r2)+(temp8*r3); amount=(amount*d1);	System.out.println("1");
							String c1=Float.toString(tons);String c2=Float.toString(amount);
							T5.setText(c1); T6.setText(c2);F.add(T5);F.add(T6);System.out.println("9");
						}
						
					}	
				}		
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		}
	}
}


	
		

class TBtest
{
		public static void main(String args[])
		{
			  Transporterbill m=new Transporterbill();
			
		}	
}	
