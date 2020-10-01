
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class MHReport extends JFrame implements ActionListener
{
	DefaultTableModel model;	JTable table;	JScrollPane jsp;
	JButton btn;	Connection con;		Statement stmt;		ResultSet rs;
	JLabel M1,M2,H1;
	JTextField T1,T2; JButton Search;

	public MHReport()
	{	
	
		M1= new JLabel("From Date");			T1=new JTextField();	
		M2= new JLabel("To Date");			T2=new JTextField();			
		H1= new JLabel("(YY/MM/DD)"); 
		
		
		
		M1.setBounds(90,10,200,80);			T1.setBounds(300,30,150,40);
		H1.setBounds(90,60,200,40);		
		M2.setBounds(90,100,200,80);		T2.setBounds(300,120,150,40);
		

		
		//setting font size
		Font font1=new Font("Arial",Font.PLAIN,20);
				T1.setFont(font1);
		M1.setFont(font1);			T2.setFont(font1);
		M2.setFont(font1);			H1.setFont(font1);
		
		
		
	
		//Adding in Frame
		add(H1);	add(T1);add(T2);			
		add(M1);			
		add(M2);						
		
		Search=new JButton("Search");		Search.setBounds(300,250,100,40);			add(Search);
		Search.addActionListener(this);	
			
	try
		{
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
		
		setLayout(null);

		model=new DefaultTableModel();
		table=new JTable(model);
		setBounds(10,10,1200,800);setTitle("MHReport");
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evt)
		{		
		
		if (evt.getSource()==Search)
		{		
	
				

		//create table MHbill(Billid int,Mid int,Hid int,Season int,Billno int,Tonnage float,Date date,MA float,HA float);
		model.addColumn("Billid");model.addColumn("Member id");model.addColumn("Harvester id");model.addColumn("Season");model.addColumn("Billno");
		model.addColumn("Tonnage");model.addColumn("Date");model.addColumn("Member payment");model.addColumn("Harvester payment");
		
			jsp=new JScrollPane(table);
			jsp.setBounds(100,300,1000,300);
			add(jsp);
			
				String temp1=T1.getText();  //System.out.println(temp1);
				String temp2=T2.getText();		//System.out.println(temp2);
			try{
				//to clear teable's contents-Record
				int r=model.getRowCount();
				
				while(r>0)
				{
					model.removeRow(0);
					r=model.getRowCount();
				}
				model.getDataVector().removeAllElements();
				revalidate();
				
				if(temp1.length()>0&&temp2.length()>0)
				{
					//create table MHbill(Billid int,Mid int,Hid int,Season int,Billno int,Tonnage float,Date date,MA float,HA float);
				//rs=stmt.executeQuery("select * from MHbill where (Date BETWEEN	'2018-1-1' and '2018-4-4')	; ");
					rs=stmt.executeQuery("select * from MHbill where (Date BETWEEN '"+temp1+"' and '"+temp2+"')	; ");
				while(rs.next())
					{
					String Billid=rs.getString(1);String  Mid=rs.getString(2);String  Hid=rs.getString(3);String  Season=rs.getString(4);
					String Billno =rs.getString(5);
					String  Tonnage=rs.getString(6);String date =rs.getString(7);String  MA=rs.getString(8);String  HA=rs.getString(9);
					
					model.addRow(new String[]{Billid,Mid,Hid,Season,Billno,Tonnage,date,MA,HA});
					temp1=null; temp2=null; 
					}
				}
				
				else
				{
					//create table MHbill(Billid int,Mid int,Hid int,Season int,Billno int,Tonnage float,Date date,MA float,HA float);
				//rs=stmt.executeQuery("select * from MHbill where (Date BETWEEN	'2018-1-1' and '2018-4-4')	; ");
					rs=stmt.executeQuery("select * from MHbill ");
				while(rs.next())
					{
					String Billid=rs.getString(1);String  Mid=rs.getString(2);String  Hid=rs.getString(3);String  Season=rs.getString(4);
					String Billno =rs.getString(5);
					String  Tonnage=rs.getString(6);String date =rs.getString(7);String  MA=rs.getString(8);String  HA=rs.getString(9);
					
					model.addRow(new String[]{Billid,Mid,Hid,Season,Billno,Tonnage,date,MA,HA});
					}
				}	
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

			}
		}
	}		
	
	public static void main(String s[])
	{
		MHReport a=new MHReport();
	}
}
