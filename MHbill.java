import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;




public class MHbill  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Billid,Mid,Hid,Tonnage,Season,Billno,date,MA,HA;	
	JTextField T1,T2,T3,T5,T4,T6;
	JComboBox Combo1,Combo2,Combo3,Combo4,Combo5;
	JButton Save,Search,Delete,New,Calculate;
	Connection con;
	Statement stmt;  String[] S1={"2017","2018","2019","2020","2021"};
	ResultSet rs;	String[] S2={"1","2","3"};
	int flag=1,a,b;
	
	public MHbill()
	{
		F=new JFrame();
		F.setVisible(true);
		F.setBounds(10,70,1200,650);
		F.setTitle("Member and Harvester Bill");
		F.setLayout(null);
		
		Billid=new JLabel("Billid Id");
		Mid= new JLabel("Member Id");
		Hid= new JLabel("Harvester Id");												
		Season= new JLabel("Season");
		Billno= new JLabel("Bill Number 1/2/3");				T1=new JTextField();T2=new JTextField();T3=new JTextField();
		Tonnage= new JLabel("Total tonnage");					T4=new JTextField();
		Tonnage= new JLabel("Total tonnage");			
		date= new JLabel("MBDate");
		MA=new JLabel("Member Bill Amount");
		HA=new JLabel("Harvester Bill Amount");
		
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
			
			Combo5=new JComboBox(S2);
		Combo5.setEditable(false);
		Combo5.addActionListener(this);
		Combo5.addItemListener(this);
		F.add(Combo5);
			
	
		//JLabel Billid,Mid,Hid,Tonnage,Season,Billno,date,MA,TA;	
		Billid.setBounds(90,50,200,80);			Combo1.setBounds(310,70,200,40);
		Mid.setBounds(600,50,200,80);			Combo2.setBounds(820,70,200,40);
		Hid.setBounds(90,150,200,80);			Combo3.setBounds(310,170,200,40);
		Season.setBounds(600,150,200,80);		Combo4.setBounds(820,170,200,40); 
		Billno.setBounds(90,250,200,80);		Combo5.setBounds(310,270,200,40);
		Tonnage.setBounds(600,250,200,80);		T1.setBounds(820,270,150,50);
		date.setBounds(90,350,200,80);			T2.setBounds(310,370,150,50);	
		MA.setBounds(90,450,200,80);			T4.setBounds(310,470,150,50);		
		HA.setBounds(600,450,200,80);			T3.setBounds(820,470,150,50);	
		//setting font size
		Font font1=new Font("Arial",Font.PLAIN,20);
		Billid.setFont(font1);
		MA.setFont(font1);			T1.setFont(font1);
		Mid.setFont(font1);			T2.setFont(font1);
		Hid.setFont(font1);			T3.setFont(font1);
		HA.setFont(font1);						
		Tonnage.setFont(font1);		
		Billno.setFont(font1); 
		date.setFont(font1); 
		Season.setFont(font1);

		//Adding in Frame
		F.add(Billid);F.add(Mid);F.add(Hid);F.add(Tonnage);F.add(Billno);F.add(date);F.add(Season);F.add(MA);F.add(HA);
        F.add(T1);F.add(T2);//F.add(T3);F.add(T4);
	
		//
		
		Save=new JButton("Save");			Save.setBounds(100,550,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,550,100,40);			F.add(Search);
		Calculate=new JButton("Calculate");	Calculate.setBounds(500,550,100,40);		F.add(Calculate);
		Delete=new JButton("Delete");		Delete.setBounds(700,550,100,40);			F.add(Delete);
		New=new JButton("New");		New.setBounds(900,550,100,40);			F.add(New);					

SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T2.setText(sdf.format(date));	
						
		Save.addActionListener(this);	
		Search.addActionListener(this);
		New.addActionListener(this);
		Delete.addActionListener(this);		Calculate.addActionListener(this);	

	
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
						Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
		
				try
				{
					rs=stmt.executeQuery("select Hid from HMaster"); 
					while(rs.next())
					{	flag=0;
						Combo3.addItem(rs.getInt(1));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
				
				try
				{
					rs=stmt.executeQuery("select max(Billid) from MHbill"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
				
				
				
				
				
				
				
				
		
	}
	public void itemStateChanged(ItemEvent evt) 
	{int id=-1;
			try{
				if(evt.getSource()==Combo1 && evt.getStateChange()==ItemEvent.SELECTED  && flag==1)
					{	
						String i= Combo1.getSelectedItem().toString().trim();
						id=Combo1.getSelectedIndex();
						if(i.length()>0)
						{
						int temp=Integer.parseInt(i);
						stmt=con.createStatement();
						rs=stmt.executeQuery("select * from MHbill where Billid="+temp);
			//create table MHbill(Billid int,Mid int,Hid int,Season int,Billno int,Tonnage float,MBDate date,MA float,HA float);
						if(rs.next())
						{flag=0;
							Combo2.setSelectedIndex(id);
							Combo3.setSelectedIndex(id);
							Combo4.setSelectedIndex(id);
							Combo5.setSelectedIndex(id);
							T1.setText(rs.getString(6)+"");
							T2.setText(rs.getString(7)+"");
							T3.setText(rs.getString(8)+"");
							T4.setText(rs.getString(9)+"");
				
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
				
					rs=stmt.executeQuery("select Billid,Mid,Hid,Season,billno from MHbill");
						
					Combo1.removeAllItems();
					Combo2.removeAllItems();
					Combo3.removeAllItems();	Combo4.removeAllItems();	Combo5.removeAllItems();
					
					while(rs.next())
					{	flag=0;
						Combo1.addItem(rs.getInt(1));Combo2.addItem(rs.getInt(2));Combo3.addItem(rs.getInt(3));Combo4.addItem(rs.getInt(4));
						Combo5.addItem(rs.getInt(5));						
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
			T1.setText("");T2.setText("");T3.setText("");T4.setText("");
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
						String U5=Combo5.getSelectedItem().toString().trim();
						if(U1.length()>0&&U2.length()>0&&U3.length()>0&&U4.length()>0&&U5.length()>0)
					{	
						String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText();
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U2);
						int temp3=Integer.parseInt(U3);int temp4=Integer.parseInt(U4);int temp5=Integer.parseInt(U5); 
						float temp6=Float.parseFloat(TX1);float temp7=Float.parseFloat(TX3);
						float temp8=Float.parseFloat(TX4);
		
		
					int r1=stmt.executeUpdate("insert into MHbill values("+temp1+","+temp2+","+temp3+","+temp4+","+temp5+","+temp6+",'"+TX2+"',"+temp7+","+temp8+")");
	if(r1>0)	JOptionPane.showMessageDialog(null," Record Inserted ");
									}
				
									Combo1.removeAllItems();Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);Combo4.setSelectedIndex(-1);
									Combo5.setSelectedIndex(-1);
									T1.setText("");T2.setText("");T3.setText("");T4.setText("");
				}
				
					catch(Exception e)
					{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
					}	
				try
				{
					rs=stmt.executeQuery("select max(Billid) from MHbill"); 
					while(rs.next())
					{	flag=0;int p=(rs.getInt(1));p=p+1;Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");
				}
					catch(Exception e)
					{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
					}
		
					

		}	
		
		
		
		
		
		
		
		
		
		else if(evt.getSource()==New)
			{
					Combo1.removeAllItems();Combo2.removeAllItems();Combo3.removeAllItems();Combo4.removeAllItems();Combo5.removeAllItems();
					
					
					
					
					
						try
				{
					
					rs=stmt.executeQuery("select Mid from MMaster"); 
					while(rs.next())
					{	flag=0;
						Combo2.addItem(rs.getInt(1));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
		
				try
				{
					rs=stmt.executeQuery("select Hid from HMaster"); 
					while(rs.next())
					{	flag=0;
						Combo3.addItem(rs.getInt(1));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
				
				try
				{
					rs=stmt.executeQuery("select max(Billid) from MHbill"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						//Combo4.setSelectedIndex(-1);
						T1.setText("");T4.setText("");T3.setText("");
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T2.setText(sdf.format(date));	
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
	
				Combo4.addItem(2017);Combo4.addItem(2018);Combo4.addItem(2019);Combo4.addItem(2020);Combo4.addItem(2021);
				Combo5.addItem(1);	Combo5.addItem(2);	Combo5.addItem(3);
		
			}
				
		else if(evt.getSource()==Delete)
			{
				try{
						String U1=Combo1.getSelectedItem().toString().trim();
					
						int temp1=Integer.parseInt(U1);
						if(U1.length()>0)
						{
							stmt=con.createStatement();
							int r1=stmt.executeUpdate("Delete from MHbill where Billid="+temp1);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);Combo4.setSelectedIndex(-1);Combo5.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");
						}

				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

				}
			}
				 
		
		if(evt.getSource()==Calculate)
			
		{	
				
				F.remove(T3);F.remove(T4);
		
				int r=0;  String z=Search.getText();
			
				try{	String U1=Combo1.getSelectedItem().toString().trim();String U2=Combo2.getSelectedItem().toString().trim();
						String U3=Combo3.getSelectedItem().toString().trim();String U4=Combo4.getSelectedItem().toString().trim();
						String U5=Combo5.getSelectedItem().toString().trim();
						if(U1.length()>0&&U2.length()>0&&U3.length()>0&&U4.length()>0&&U4.length()>0)
					{	
						String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText();
						
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U2);
						int temp3=Integer.parseInt(U3);int temp4=Integer.parseInt(U4);int temp5=Integer.parseInt(U5);	
						float temp6=Float.parseFloat(TX1);
						
						//create table RMaster(Season int,M1 int,M2 int,M3 int,H1 int,H2 int ,H3 int);
		
						
						if(temp5==1)
						{	
							rs=stmt.executeQuery("select M1,H1 from RMaster where Season="+temp4);
						if(rs.next())
							{  a=Integer.parseInt(rs.getString(1)); 
							   b=Integer.parseInt(rs.getString(2)); 	
							}
						}
						
				else  if(temp5==2)
						{
							rs=stmt.executeQuery("select M2,H2 from RMaster where Season="+temp4);
						if(rs.next())
							{  a=Integer.parseInt(rs.getString(1)); 
							   b=Integer.parseInt(rs.getString(2)); 	
							
							}
						}
				else if(temp5==3)
						{
							rs=stmt.executeQuery("select M3,H3 from RMaster where Season="+temp4);
						if(rs.next())
							{  a=Integer.parseInt(rs.getString(1)); 
							   b=Integer.parseInt(rs.getString(2)); 	System.out.println(a);System.out.println(b);
							
							}
						}
					
					float	Mamount=(temp6*a);	float	Hamount=(temp6*b);			
					String C1=Float.toString(Mamount);String C2=Float.toString(Hamount);
					T3.setText(C1);T4.setText(C2);F.add(T3);F.add(T4);
						
					}	
				}		
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		}
	}
}


		
		
	class MHBtest
{
		public static void main(String args[])
		{
			  MHbill m=new MHbill();
			
		}	
}	
