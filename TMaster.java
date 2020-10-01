import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TMaster  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Season,Id,Name,VId,Addr,Contact,Shares,Rate,Joindate,TDate,S;		
	JTextField T1,T2,T3,T5,T4;
	JComboBox Combo1,Combo2,Combo3;
	JButton Save,Search,Update,Delete,New;
	Connection con;
	Statement stmt;
	ResultSet rs;
	int flag=1;
	
	public TMaster()
	{
		F=new JFrame();
		F.setVisible(true);
	//	F.setBounds(10,10,1000,1000);
			F.setBounds(10,70,1200,650);
		F.setTitle("Transporter Operator");
		//F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		Season=new JLabel("Season");
		Id= new JLabel("Transporter id");
		Name= new JLabel("Transporter Name");								T4=new JTextField();			
		VId= new JLabel("Transporter Village Id");						T3=new JTextField();		
		Contact= new JLabel("Transporter Contact info");			    	T1=new JTextField();
		Shares= new JLabel("Transporter Number");				T2=new JTextField();
		Rate= new JLabel("Share rate");									T5=new JTextField();
      	Joindate= new JLabel("Transporter Joindate");						
		Addr=new JLabel("Transporter Address");
		TDate=new JLabel("(YY-MM-DD)");
		S=new JLabel("of Shares");
		
		// combobox setup
		Combo1= new JComboBox();
		Combo1.setEditable(true);
		Combo1.addActionListener(this);
		Combo1.addItemListener(this);
		Combo1.setBounds(310,70,150,50);
		F.add(Combo1);
	
		Combo2=new JComboBox();
		Combo2.setEditable(true);
		Combo2.addActionListener(this);
		Combo2.addItemListener(this);
		Combo2.setBounds(310,170,150,50);
		F.add(Combo2);
			
		Combo3=new JComboBox();
		Combo3.setEditable(true);
		Combo3.addActionListener(this);
		Combo3.addItemListener(this);
		Combo3.setBounds(310,170,150,50);
		F.add(Combo3);
		
		Id.setBounds(90,50,200,80);				Combo1.setBounds(310,70,200,40);
		Name.setBounds(600,50,200,80);			Combo2.setBounds(820,70,200,40);
		VId.setBounds(90,150,300,80);			Combo3.setBounds(310,170,200,40);
		Addr.setBounds(90,250,300,80);			T1.setBounds(310,270,150,50); 
		Contact.setBounds(600,250,350,80);		T2.setBounds(870,270,150,50); 
		Shares.setBounds(90,350,300,80);	 S.setBounds(120,370,300,80);	T3.setBounds(310,370,150,50); 		
		Rate.setBounds(600,350,300,80);			T4.setBounds(820,370,150,50); 		
		Joindate.setBounds(90,450,300,80);		T5.setBounds(310,470,150,50);
		TDate.setBounds( 120,470,300,80);
		//setting font size
		
		Font font1=new Font("Arial",Font.PLAIN,20);
		Name.setFont(font1);			T1.setFont(font1);T5.setFont(font1);
		Contact.setFont(font1);			T2.setFont(font1);T4.setFont(font1);
		Shares.setFont(font1);			T3.setFont(font1);
		Rate.setFont(font1);			
		Id.setFont(font1);		
		VId.setFont(font1); 
		S.setFont(font1);TDate.setFont(font1);		
		Joindate.setFont(font1);
		Addr.setFont(font1);
		
	
		//Adding in Frame
		F.add(Name);		F.add(Rate);			F.add(T2);		F.add(Combo1);				
		F.add(Id);			F.add(T3);		F.add(Combo2);
		F.add(VId);			F.add(Joindate);		F.add(T4);		F.add(Combo3);
		F.add(Contact);		F.add(Addr);			F.add(T5);		F.add(Season);
		F.add(Shares);		F.add(T1);	F.add(TDate);	F.add(S);				
	
		
		Save=new JButton("Save");			Save.setBounds(100,550,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,550,100,40);			F.add(Search);
		Update=new JButton("Update");		Update.setBounds(500,550,100,40);			F.add(Update);
		Delete=new JButton("Delete");			Delete.setBounds(700,550,100,40);			F.add(Delete);
		New=new JButton("New");				New.setBounds(900,550,100,40);			F.add(New);
		
		Save.addActionListener(this);	
		Search.addActionListener(this);
		Update.addActionListener(this);
		Delete.addActionListener(this);	
		New.addActionListener(this);	
		
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T5.setText(sdf.format(date));	
		
		try{
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=CollegeDB.mdb;DriverID=22;READONLY=true}" ,"","");
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Oracle in OraHome90}","scott","tiger");

			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
			
			//Class.forName("com.mysql.jdbc.Driver"); //mysql-connector.jar file required
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/collegedb","root","root");
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
		
		
		
		
			try
				{Combo2.removeAllItems();Combo1.removeAllItems();
					rs=stmt.executeQuery("select max(Tid) from TMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						
					T3.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
				try
				{
				
					rs=stmt.executeQuery("select VId from VMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo3.addItem(p);Combo3.setSelectedIndex(-1);
					}
						flag=1;
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
		
		
		
		
		
		
		
		
		
		
		
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
						rs=stmt.executeQuery("select * from TMaster where Tid="+temp);
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+"");
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							Combo3.setSelectedIndex(id);
							Combo2.setSelectedIndex(id);
						}flag=1;
						}
					}	
					
				else if(evt.getSource()==Combo2 && evt.getStateChange()==ItemEvent.SELECTED  && flag==1)
					{	
						String i= Combo2.getSelectedItem().toString().trim();
						id=Combo2.getSelectedIndex();
						if(i.length()>0)
						{stmt=con.createStatement();
						rs=stmt.executeQuery("select * from TMaster where Tname='"+i+"'");
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+"");
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							Combo3.setSelectedIndex(id);Combo1.setSelectedIndex(id);
						}flag=1;
						}
					}	
				else if(evt.getSource()==Combo3 && evt.getStateChange()==ItemEvent.SELECTED  && flag==1)
					{	
						String i= Combo3.getSelectedItem().toString().trim();
						id=Combo3.getSelectedIndex();
						if(i.length()>0)
						{stmt=con.createStatement();
						rs=stmt.executeQuery("select * from TMaster where TVid='"+i+"'");
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+"");
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							Combo2.setSelectedIndex(id);Combo1.setSelectedIndex(id);
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




			if(evt.getSource()==New)
			{
			try
				{Combo2.removeAllItems();Combo1.removeAllItems();Combo3.removeAllItems();
				T1.setText("");		T2.setText("");	T3.setText("");	T4.setText("");	T5.setText("");
					rs=stmt.executeQuery("select max(Tid) from TMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T5.setText(sdf.format(date));
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
				
				
				try
				{
				
					rs=stmt.executeQuery("select VId from VMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo3.addItem(p);Combo3.setSelectedIndex(-1);
					}
						flag=1;
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}











		if (evt.getSource()==Search)
		{	 int r=0; String z=Search.getText();
			  if( z.equals("Search"))	
			{
				try
				{	
					rs=stmt.executeQuery("select Tid,Tname,TVid from TMaster"); 
					
					Combo1.removeAllItems();Combo2.removeAllItems();Combo3.removeAllItems();
					while(rs.next())
					{	flag=0;
						Combo1.addItem(rs.getInt(1));
						Combo2.addItem(rs.getString(2));
						Combo3.addItem(rs.getInt(3));
						}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}		 
		}
	
		if(evt.getSource()==Save)
			{int r=0; String z=Save.getText();
						if( z.equals("Save"))	
						{
							try	{	
									String U1=Combo1.getSelectedItem().toString().trim();
									String U2=Combo2.getSelectedItem().toString().trim();String U3=Combo3.getSelectedItem().toString().trim();
					String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); String TX5=T5.getText(); 
								 int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);//float temp6=Float.parseFloat(TX5);
								int temp3=Integer.parseInt(TX2);int temp4=Integer.parseInt(TX3);int temp5=Integer.parseInt(TX4);
						if(TX2.length()==10)
									{	
									if(U1.length()>0 && U2.length()>0 && U3.length()>0)
										{
	int r1=stmt.executeUpdate("insert into TMaster values("+temp1+",'"+U2+"',"+temp2+",'"+TX1+"',"+temp3+","+temp4+","+temp5+",'"+TX5+"')");
	if(r1>0)	JOptionPane.showMessageDialog(null,"Record Inserted");									
					Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
										}
								
								else JOptionPane.showMessageDialog(null,"Enter 10 digit contact");
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
					
						int temp1=Integer.parseInt(U1);
						if(U1.length()>0)
						{
							stmt=con.createStatement();
							int r1=stmt.executeUpdate("Delete from TMaster where Tid="+temp1);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
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
				try{
								String U1=Combo1.getSelectedItem().toString().trim();
								String U2=Combo2.getSelectedItem().toString().trim();
								String U3=Combo3.getSelectedItem().toString().trim();
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); 
								String TX5=T5.getText(); 
								int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);//float temp6=Float.parseFloat(TX5);
								int temp3=Integer.parseInt(TX2);int temp4=Integer.parseInt(TX3);int temp5=Integer.parseInt(TX4);
								
								if(TX2.length()==10)
								{
						if(U1.length()>0&&U2.length()>0&&U3.length()>0)
						{
						stmt=con.createStatement();
	//int r1=stmt.executeUpdate("Update MMaster Set Mname='"+U2+"',MVid="+temp2+"  where Mid="+temp1);
		int r1=stmt.executeUpdate("Update TMaster Set Tname='"+U2+"',TVid="+temp2+" ,TAddr='"+TX1+"',TContact='"+temp3+"',TNShares='"+temp4+"',TSRate="+temp5+", TRTDate='"+TX5+"' where Tid="+temp1);
	if(r1>0) JOptionPane.showMessageDialog(null,"Record Inserted");
	//int r1=stmt.executeUpdate("insert into MMaster values("+temp1+",'"+U2+"',"+temp2+",'"+TX1+"',"+temp3+","+temp4+","+temp5+","+temp6+",'"+TX6+"')");
	//(Mid int, Mname varchar(30),MVid int,MAddr text,MContact char(10), MNShares int ,MSRate int,MFarmArea float,MRTDate date);					
						}
					Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
							}
						else JOptionPane.showMessageDialog(null,"Enter 10 digit contact");			
					}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
			
		}

	}	
}

	
		

class TMastertest
{
		public static void main(String args[])
		{
			TMaster M=new TMaster();
			
		}	
}	
