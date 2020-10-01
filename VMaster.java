import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class VMaster  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Id,Name,Distance;	
	JTextField T3;
	JComboBox Combo1,Combo2;
	JButton Save,Search,Update,Delete,New,Cancel;
	Connection con;
	Statement stmt;
	ResultSet rs;
	int flag=1;
	
	public VMaster()
	{
		F=new JFrame();
		F.setVisible(true);
		F.setBounds(10,70,1200,600);
		F.setTitle("Village Master");
		//F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		Save=new JButton("Save");			Save.setBounds(90,500,100,40);			F.add(Save);
		
	
				
		
		Id= new JLabel("Enter Village Id");
		Name= new JLabel("Enter Village Name");
		Distance= new JLabel("Enter Village Distance");				
		
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
			
		
		
		Id.setBounds(90,50,200,80);				
		Name.setBounds(90,150,200,80);			T3=new JTextField();
		Distance.setBounds(90,300,200,80);		T3.setBounds(310,320,150,50);
			
		Font font1=new Font("Arial",Font.PLAIN,20);
		
		Id.setFont(font1);     
		Name.setFont(font1);	
		Distance.setFont(font1);	T3.setFont(font1);						
		/*
		model=new DefaultTableModel();
		table=new JTable(model);

		model.addColumn("Village Id");
		model.addColumn("Village Name");
		model.addColumn("Village Distance");

		jsp=new JScrollPane(table);
		//jsp.setSize(300,100);
		//jsp.setLocation(600,100);
		F.add(jsp);
		*/
		//setTitle("Ass2SetA1");
		//setSize(500,500);
		//setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		
		
					
		//
		F.add(Id);
		F.add(Name);
		F.add(Distance);							
		
		F.add(T3);

		Search=new JButton("Search");		Search.setBounds(300,500,100,40);			F.add(Search);	
		Update=new JButton("Update");		Update.setBounds(500,500,100,40);		F.add(Update);
		Delete=new JButton("Delete");		Delete.setBounds(700,500,100,40);		F.add(Delete);
		New=new JButton("New");		New.setBounds(900,500,100,40);		F.add(New);
		
		
		Save.addActionListener(this);	Search.addActionListener(this);	Update.addActionListener(this);
		Delete.addActionListener(this);New.addActionListener(this);
		
		
		try{
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=CollegeDB.mdb;DriverID=22;READONLY=true}" ,"","");
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Oracle in OraHome90}","scott","tiger");

			Class.forName("org.postgresql.Driver");
			
			//con = DriverManager.getConnection("jdbc:postgresql://192.168.100.10/tybcs16/VMaster","tybcs16","");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
			
			//Class.forName("com.mysql.jdbc.Driver"); //mysql-connector.jar file required
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/collegedb","root","root");
			
			if(con==null)
			{
				JOptionPane.showMessageDialog(null,"Unable to Connect");
			}
			stmt=con.createStatement();
			
			
			
			
			
			
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
					rs=stmt.executeQuery("select max(Vid) from VMaster"); 
					while(rs.next())
					{	flag=0;
						int p=(rs.getInt(1));p=p+1;
						Combo1.addItem(p);
					}
						flag=1;
						Combo2.setSelectedIndex(-1);
					T3.setText("");
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
						rs=stmt.executeQuery("select * from VMaster where Vid="+temp);
						if(rs.next())
						{flag=0;
							T3.setText(rs.getString(3)+"");
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
						rs=stmt.executeQuery("select * from VMaster where Vname='"+i+"'");
						
						if(rs.next())
						{flag=0;
							
							T3.setText(rs.getString(3)+"");
							Combo1.setSelectedIndex(id);
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
				{Combo2.removeAllItems();Combo1.removeAllItems();
					rs=stmt.executeQuery("select max(Vid) from VMaster"); 
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
		}
		
			
			
			
		if (evt.getSource()==Search)
		{	 int r=0;
			  String z=Search.getText();
			
			if( z.equals("Search"))	
			{
				try
			{
				//	stmt=con.createStatement();
				rs=stmt.executeQuery("select Vid,Vname from VMaster");
					Combo1.removeAllItems();
					Combo2.removeAllItems();
					//Combo1.addItem("");
				while(rs.next())
				{	flag=0;
					Combo1.addItem(rs.getInt(1));
					Combo2.addItem(rs.getString(2));
				}
					flag=1;
					Combo1.setSelectedIndex(-1);
					Combo2.setSelectedIndex(-1);
					T3.setText("");	
				
			}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

				}
			}		 
		}
			
			
			else if(evt.getSource()==Save)
			{
			int r=0;
			String z=Save.getText();
			if( z.equals("Save"))	
			{
				try{	
						String U1=Combo1.getSelectedItem().toString().trim();
						String U2=Combo2.getSelectedItem().toString().trim();
						String U3=T3.getText(); 
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);
						if(U1.length()>0 && U2.length()>0 && U3.length()>0)
						{
						//stmt=con.createStatement();
						int r1=stmt.executeUpdate("insert into VMaster values("+temp1+",'"+U2+"',"+temp2+")");
						}	
						Combo1.setSelectedIndex(-1);
						Combo2.setSelectedIndex(-1);
						T3.setText("");	
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
							int r1=stmt.executeUpdate("Delete from VMaster where Vid="+temp1);
						}
					Combo1.setSelectedIndex(-1);
					Combo2.setSelectedIndex(-1);
					T3.setText("");	
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
						String U3=T3.getText(); 
						int temp1=Integer.parseInt(U1);int temp2=Integer.parseInt(U3);
						if(U1.length()>0&&U2.length()>0&&U3.length()>0)
						{
						stmt=con.createStatement();
	int r1=stmt.executeUpdate("Update VMaster Set Vname='"+U2+"',Vdistance="+temp2+"  where Vid="+temp1);
						}
					Combo1.setSelectedIndex(-1);
					Combo2.setSelectedIndex(-1);
					T3.setText("");	
				}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
			
		}
}	}

class VMastertest
{
		public static void main(String args[])
		{
			VMaster v=new VMaster();
			
		}	
}	