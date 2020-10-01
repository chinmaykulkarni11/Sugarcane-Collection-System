
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
class TReport extends JFrame implements ActionListener
{
	DefaultTableModel model;	JTable table;	JScrollPane jsp;
	JButton btn;	Connection con;		Statement stmt;		ResultSet rs;
	JLabel M1,M2,H1;
	JTextField T1,T2; JButton Search;

	public TReport()
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
		setBounds(10,10,1200,800);setTitle("TReport");
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evt)
		{		
		
		if (evt.getSource()==Search)
		{		
	
				

	//	Transporterbill(Billid int,Mid int,tid int,Season int,Truck float,Tractor float,Bullock float,Date date,TotalT int,TotalA int); 
		model.addColumn("Billid");model.addColumn("Member id");model.addColumn("Trnsporter Id");model.addColumn("Season");model.addColumn("Truck");
		model.addColumn("Tractor"); model.addColumn("Bullock");model.addColumn("Date");model.addColumn("Total Tons");model.addColumn("Amount");
		
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
				//Transporterbill(Billid int,Mid int,tid int,Season int,Truck float,Tractor float,Bullock float,Date date,TotalT int,TotalA int); 
				//rs=stmt.executeQuery("select * from MHbill where (Date BETWEEN	'2018-1-1' and '2018-4-4')	; ");
					rs=stmt.executeQuery("select * from Transporterbill where (Date BETWEEN '"+temp1+"' and '"+temp2+"')	; ");
				while(rs.next())
					{
					String Billid=rs.getString(1);String  Mid=rs.getString(2);String  Tid=rs.getString(3);String  Season=rs.getString(4);
					String Truck =rs.getString(5);
					String  Tractor=rs.getString(6);   String Bullock =rs.getString(7); String date =rs.getString(8);String  TotalT=rs.getString(9);String  TotalA=rs.getString(10);
					
					model.addRow(new String[]{Billid,Mid,Tid,Season,Tractor,Truck,Bullock,date,TotalT,TotalA});
					temp1=null; temp2=null; 
					}
				}
				
				else
				{
			
			
			rs=stmt.executeQuery("select * from Transporterbill ; ");
				while(rs.next())
					{
					String Billid=rs.getString(1);String  Mid=rs.getString(2);String  Tid=rs.getString(3);String  Season=rs.getString(4);
					String Truck =rs.getString(5);
					String  Tractor=rs.getString(6);   String Bullock =rs.getString(7); String date =rs.getString(8);String  TotalT=rs.getString(9);String  TotalA=rs.getString(10);
					
					model.addRow(new String[]{Billid,Mid,Tid,Season,Tractor,Truck,Bullock,date,TotalT,TotalA});
					temp1=null; temp2=null; 
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
		TReport a=new TReport();
	}
}
