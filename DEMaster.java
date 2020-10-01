import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;

public class DEMaster  extends JFrame implements ActionListener
{
	JFrame F;
	JLabel Id,Amount,Isactive,Remark,Season;	
	
	JTextField T1,T2,T3;
	
	JComboBox Combo1,Combo2;
	
	JButton Save,Search,Update,Close,New;
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	public DEMaster()
	{
		F=new JFrame();
		F.setVisible(true);
		F.setBounds(10,10,1200,900);
		F.setTitle("Distance Rate Operator");
		F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		Season= new JLabel("Season");
		Id= new JLabel("Deduction id");
		Amount= new JLabel("Amount");
		Isactive= new JLabel("Isactive");			    	T1=new JTextField();
		Remark= new JLabel("Remark");						T2=new JTextField();
															T3=new JTextField();
		
		
		// combobox setup
		
		
		Combo1= new JComboBox();
		Combo1.setEditable(true);
		//Combo.addActionListner(this);
		Combo1.setBounds(310,70,200,40);
		F.add(Combo1);
		
		
		Combo2=new JComboBox();
		Combo2.setEditable(true);
	
		
		
		
		Season.setBounds(90,50,200,80);
		Id.setBounds(600,50,200,80);
		Amount.setBounds(90,300,300,80);	T1.setBounds(450,320,150,50); 
 		Isactive.setBounds(650,300,300,80);	T2.setBounds(1010,320,150,50); 
		Remark.setBounds(90,400,300,80);	T3.setBounds(450,420,150,50); 		
		//setting font size
		
		Font font1=new Font("Arial",Font.PLAIN,20);
		Season.setFont(font1);						
		Id.setFont(font1);						
		Amount.setFont(font1);							T1.setFont(font1);
		Isactive.setFont(font1);						T2.setFont(font1);
		Remark.setFont(font1);							T3.setFont(font1);
		
		//
		F.add(Season);												
		F.add(Id);								
		F.add(Amount);								F.add(T1);
		F.add(Isactive);							F.add(T2);
		F.add(Remark);								F.add(T3);
	
	
		Save=new JButton("Save");			Save.setBounds(100,500,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,500,100,40);			F.add(Search);
		Update=new JButton("Update");		Update.setBounds(500,500,100,40);			F.add(Update);
		Close=new JButton("Close");			Close.setBounds(700,500,100,40);			F.add(Close);
		
		
		
		
		Save.addActionListener(this);	Search.addActionListener(this);	Update.addActionListener(this);
		Close.addActionListener(this);	
		//Referesh frame
		
		
		try{
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}; DBQ=CollegeDB.mdb;DriverID=22;READONLY=true}" ,"","");
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//con=DriverManager.getConnection("jdbc:odbc:Driver={Oracle in OraHome90}","scott","tiger");

			Class.forName("org.postgresql.Driver");
			
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
			
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
		
		
		
		F.revalidate();
		
	}
	
	
	
		public void actionPerformed(ActionEvent evt)
		{
				if(evt.getSource()==Save)
			{
			
			 String z=Save.getText();
			 if( z.equals("Save"))
				 Save.setText("New");
			 else
				Save.setText("Save");
			
			}
			
			else if (evt.getSource()==Search)
			{
			
			 String z=Search.getText();
			 if( z.equals("Search"))
				 Search.setText("Cancel");
			 else
				Search.setText("Search");
			
			}
			
			else if(evt.getSource()==Close)
			{
			
			 String z=Close.getText();
			 if( z.equals("Close"))
				Close.setText("Delete");
			 else
				Close.setText("Close");
			
			}
		
			else if(evt.getSource()==Update)
			{
				
			}
		}
	
}

class DEMastertest
{
		public static void main(String args[])
		{
			DEMaster D=new DEMaster();
			
		}	
}	