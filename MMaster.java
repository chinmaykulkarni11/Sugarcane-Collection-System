import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MMaster  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Season,Id,Name,VId,Addr,Contact,Shares,Rate,Farmarea,Joindate,MDate,S;	
	JTextField T1,T2,T3,T5,T4,T6;
	JComboBox Combo1,Combo2,Combo3;
	JButton Save,Search,Update,Delete,New;
	Connection con;
	Statement stmt;
	ResultSet rs;
	int flag=1;
	
	public MMaster()
	{	
	
	
		
		F=new JFrame();
		F.setVisible(true);
	//	F.setBounds(10,10,1000,1000);
		F.setBounds(10,70,1200,680);
		F.setTitle("Member Operator");
		//F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		Season=new JLabel("Season");
		Id= new JLabel("Member id");
		Name= new JLabel("Member Name");								T1=new JTextField();			
		VId= new JLabel("Member Village Id");							T2=new JTextField();		
		Contact= new JLabel("Member Contact");			    		T3=new JTextField();
		Shares= new JLabel("Member Number");						T4=new JTextField();
		Rate= new JLabel("Share rate");									T5=new JTextField();
		Farmarea= new JLabel("Member Farmarea");						T6=new JTextField();
		Joindate= new JLabel("Member Joindate");						
		Addr=new JLabel("Member Address");
		MDate=new JLabel("(YY-MM-DD)");
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
		VId.setBounds(90,150,200,80);			Combo3.setBounds(310,170,200,40);
		
		Addr.setBounds(90,250,200,80);			T1.setBounds(310,270,150,50); 
		Contact.setBounds(600,250,200,80);		T2.setBounds(810,270,150,50); 
		Shares.setBounds(90,350,200,80);		S.setBounds(120,370,300,80)	;		T3.setBounds(310,370,150,50); 		
		Rate.setBounds(600,350,200,80);			T4.setBounds(820,370,150,50); 		
		Farmarea.setBounds(90,450,200,80);		T5.setBounds(310,470,150,50);
		Joindate.setBounds(600,450,200,80) ;	T6.setBounds(820,470,150,50);	MDate.setBounds(610,470,300,80)	;
	
		//setting font size
		
		Font font1=new Font("Arial",Font.PLAIN,20);
		Name.setFont(font1);			T1.setFont(font1);T5.setFont(font1);
		Contact.setFont(font1);			T2.setFont(font1);T4.setFont(font1);
		Shares.setFont(font1);			T3.setFont(font1);T6.setFont(font1);
		Rate.setFont(font1);			S.setFont(font1);MDate.setFont(font1);			
		Id.setFont(font1);		
		VId.setFont(font1); 
		Farmarea.setFont(font1); 
		Joindate.setFont(font1);
		Addr.setFont(font1);						
		
		//Adding in Frame
		F.add(Name);		F.add(Rate);			F.add(T2);		F.add(Combo1);		F.add(MDate);				
		F.add(Id);			F.add(Farmarea);		F.add(T3);		F.add(Combo2);
		F.add(VId);			F.add(Joindate);		F.add(T4);		F.add(Combo3);
		F.add(Contact);		F.add(Addr);			F.add(T5);		F.add(Season);
		F.add(Shares);		F.add(T1);				F.add(T6);		F.add(S);
	
		//
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

		JPanel jp=new JPanel();
		jp.setBackground(Color.BLUE);F.add(jp);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			Date date=new Date();
			T6.setText(sdf.format(date));

/*
	ImageIcon img=new ImageIcon(ClassLoader.getSystemResource ("cane.jpg"));
	JLabel image = new JLabel(img);image.setBounds(0,00,1000,1000);
		F.add(image);
	
		/*
		ImageIcon img=new ImageIcon("cane.jpg");
		F.setIconImage(img.getImage());
		Container c=F.getContentPane();
		c.setBackground("cane.jpg");
	*/
	
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
				
					rs=stmt.executeQuery("select max(Mid) from MMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
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
						Combo3.addItem(p);
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
						rs=stmt.executeQuery("select * from MMaster where Mid="+temp);
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+""); 
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							T6.setText(rs.getString(9)+"");
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
						rs=stmt.executeQuery("select * from MMaster where Mname='"+i+"'");
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+"");
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							T6.setText(rs.getString(9)+"");
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
						rs=stmt.executeQuery("select * from MMaster where MVid='"+i+"'");
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(4)+"");
							T2.setText(rs.getString(5)+"");
							T3.setText(rs.getString(6)+"");
							T4.setText(rs.getString(7)+"");
							T5.setText(rs.getString(8)+"");
							T6.setText(rs.getString(9)+"");
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

				if (evt.getSource()==New)
				{
					try
				{
					Combo2.removeAllItems();Combo1.removeAllItems();Combo3.removeAllItems();
					rs=stmt.executeQuery("select max(Mid) from MMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						
					T3.setText("");T1.setText("");		T2.setText("");	T6.setText("");	T4.setText("");	T5.setText("");
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
					Date date=new Date();
					T6.setText(sdf.format(date));
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
					
					rs=stmt.executeQuery("select Mid,Mname,MVid from MMaster"); 
					
					Combo1.removeAllItems();
					Combo2.removeAllItems();
					Combo3.removeAllItems();
					
					while(rs.next())
					{	flag=0;
						Combo1.addItem(rs.getInt(1));
						Combo2.addItem(rs.getString(2));
						Combo3.addItem(rs.getInt(3));
					}
						flag=1;
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
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
							
							try{	
									String U1=Combo1.getSelectedItem().toString().trim();
									String U2=Combo2.getSelectedItem().toString().trim();
									String U3=Combo3.getSelectedItem().toString().trim();
						
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); 
								String TX5=T5.getText();String TX6=T6.getText(); 
								 
								int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);float temp6=Float.parseFloat(TX5);
								int temp3=Integer.parseInt(TX2);int temp4=Integer.parseInt(TX3);int temp5=Integer.parseInt(TX4);
								
								if(TX2.length()==10)
								{
								if(U1.length()>0 && U2.length()>0 && U3.length()>0)
									{
	int r1=stmt.executeUpdate("insert into MMaster values("+temp1+",'"+U2+"',"+temp2+",'"+TX1+"',"+temp3+","+temp4+","+temp5+","+temp6+",'"+TX6+"')");
	if(r1>0)	JOptionPane.showMessageDialog(null," Record Inserted ");
									}
				
									Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
									T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
								}
								else JOptionPane.showMessageDialog(null,"Enter 10 digit contact ");
						}
							catch(Exception e)
							{
							JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
							}
						}
	
			}		

			else if(evt.getSource()==Delete)
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
							int r1=stmt.executeUpdate("Delete from MMaster where Mid="+temp1);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						
						Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
						}

				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

				}
			}
				
			}
		
		
		else if(evt.getSource()==Update)
			{
			int r=0;
			 String z=Update.getText();
			if( z.equals("Update"))	
			{
				try{
						String U1=Combo1.getSelectedItem().toString().trim();
						String U2=Combo2.getSelectedItem().toString().trim();
						String U3=Combo3.getSelectedItem().toString().trim();
						String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); 
						String TX5=T5.getText();String TX6=T6.getText(); 
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);float temp6=Float.parseFloat(TX5);
						int temp3=Integer.parseInt(TX2);int temp4=Integer.parseInt(TX3);int temp5=Integer.parseInt(TX4);
						
						if(TX2.length()==10)
								{
								if(U1.length()>0 && U2.length()>0 && U3.length()>0)
									{
	int r1=stmt.executeUpdate("Update MMaster Set Mname='"+U2+"',MVid="+temp2+" ,MAddr='"+TX1+"',MContact='"+temp3+"',MNShares='"+temp4+"',MSRate="+temp5+",MFarmArea='"+temp6+"', MRDate='"+TX6+"' where Mid="+temp1);
									}
				
									Combo1.setSelectedIndex(-1);Combo2.setSelectedIndex(-1);Combo3.setSelectedIndex(-1);
									T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
								}
								else JOptionPane.showMessageDialog(null,"Enter 10 digit contact ");
		}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
			 
		}

	}	
}

	
		

class MMastertest
{
		public static void main(String args[])
		{
			MMaster M=new MMaster();
			
		}	
}	
