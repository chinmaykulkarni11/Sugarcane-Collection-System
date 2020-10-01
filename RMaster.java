import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;



public class RMaster  extends JFrame implements ActionListener,ItemListener
{
	JFrame F;
	JLabel Season,M1,H1,M2,H2,M3,H3;	
	JTextField T1,T2,T3,T5,T4,T6;
	JComboBox Combo1;
	JButton Save,Search,Update,Delete,New;
	Connection con;
	Statement stmt;
	ResultSet rs; int flag=1;
	String[] S1={"2018","2019","2020"};
	public RMaster()
	{
		F=new JFrame();
		F.setVisible(true);
		//F.setBounds(10,10,1100,1000);
			F.setBounds(10,70,1200,650);
		F.setTitle("Rate Operator");
		//F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
		
		Season=new JLabel("Season Harvsting Cycle");
		M1= new JLabel("Member First Bill Rate Per Ton");			T6=new JTextField();	
		M2= new JLabel("Member Second Bill Rate Per Ton");			T4=new JTextField();			
		M3= new JLabel("Member Third Bill Rate Per Ton");			T3=new JTextField();		
		H1= new JLabel("Harvester First Bill Rate Per Ton"); 		T1=new JTextField();
		H2= new JLabel("Harvester Second Bill Rate Per Ton");		T2=new JTextField();
		H3= new JLabel("Harvester Third Bill Rate Per Ton");		T5=new JTextField();
	
		
		// combobox setup
		///*
		Combo1= new JComboBox(S1);
		Combo1.setEditable(false);
		Combo1.addActionListener(this);
		Combo1.addItemListener(this);
		//*/
		/*
		Combo1= new JComboBox();
		Combo1.setEditable(true);
		Combo1.addActionListener(this);
		Combo1.addItemListener(this);
		*/
		
		Season.setBounds(90,10,400,80);		Combo1.setBounds(550,30,150,40);
		M1.setBounds(90,100,400,50);		T1.setBounds(550,120,150,40);	
		M2.setBounds(90,160,400,50);		T2.setBounds(550,180,150,40);
		M3.setBounds(90,220,400,50);		T3.setBounds(550,240,150,50); 
		H1.setBounds(90,280,400,50);		T4.setBounds(550,300,150,50); 
		H2.setBounds(90,340,400,50);		T5.setBounds(550,360,150,50); 		
		H3.setBounds(90,400,400,50);		T6.setBounds(550,420,150,50); 		

		
		//setting font size
		Font font1=new Font("Arial",Font.PLAIN,20);
		Season.setFont(font1);		T1.setFont(font1);T5.setFont(font1);
		M1.setFont(font1);			T2.setFont(font1);T4.setFont(font1);
		M2.setFont(font1);			T3.setFont(font1);T6.setFont(font1);
		M3.setFont(font1);H1.setFont(font1);H2.setFont(font1);H3.setFont(font1);  	
		
		
	
		//Adding in Frame
		F.add(Season);		F.add(H2);		F.add(T3);		F.add(Combo1);				
		F.add(M1);			F.add(H3);		F.add(T4);		
		F.add(M2);			F.add(T1);		F.add(T5);
		F.add(M3);			F.add(T2);		F.add(T6);
		F.add(H1);						
	
		Save=new JButton("Save");			Save.setBounds(100,500,100,40);				F.add(Save);
		Search=new JButton("Search");		Search.setBounds(300,500,100,40);			F.add(Search);
		Update=new JButton("Update");		Update.setBounds(500,500,100,40);			F.add(Update);
		Delete=new JButton("Delete");		Delete.setBounds(700,500,100,40);			F.add(Delete);
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
						{stmt=con.createStatement();
						rs=stmt.executeQuery("select * from RMaster where Season='"+i+"'");
						if(rs.next())
						{flag=0;
							T1.setText(rs.getString(2)+"");
							T2.setText(rs.getString(3)+"");
							T3.setText(rs.getString(4)+"");
							T4.setText(rs.getString(5)+"");
							T5.setText(rs.getString(6)+"");
							T6.setText(rs.getString(7)+"");
							
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
				T4.setText("");T5.setText("");T6.setText("");
				Combo1.removeAllItems(); flag=0;
				Combo1.addItem(2018);Combo1.addItem(2019);Combo1.addItem(2020);flag=1;
			}
		
		
		if (evt.getSource()==Search)
		{	 int r=0; String z=Search.getText();
			  if( z.equals("Search"))	
			{
				try
				{	
					rs=stmt.executeQuery("select Season from RMaster");
					Combo1.removeAllItems();
					while(rs.next())
					{	flag=0;
						Combo1.addItem(rs.getString(1));
					}	flag=1;	Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
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
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); 
								String TX5=T5.getText(); String TX6=T6.getText(); 
							
							int temp7=Integer.parseInt(U1);
							int temp1=Integer.parseInt(TX1);	int temp2=Integer.parseInt(TX2);	int temp3=Integer.parseInt(TX3);
							int temp4=Integer.parseInt(TX4);	int temp5=Integer.parseInt(TX5);	int temp6=Integer.parseInt(TX6);
					
									if(U1.length()>0)
										{
	int r1=stmt.executeUpdate("insert into RMaster values("+temp7+","+temp1+","+temp2+","+temp3+","+temp4+","+temp5+","+temp6+")");
	if(r1>0)	JOptionPane.showMessageDialog(null,"Record Inserted");									
						Combo1.setSelectedIndex(-1);T6.setText("");
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");
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
						int temp7=Integer.parseInt(U1);
						if(U1.length()>0)
						{
							stmt=con.createStatement();
							int r1=stmt.executeUpdate("Delete from RMaster where Season="+temp7);
							if(r1>0)	JOptionPane.showMessageDialog(null,"Record Deleted");
						}
						Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
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
								
								String TX1=T1.getText();String TX2=T2.getText();String TX3=T3.getText();String TX4=T4.getText(); 
								String TX5=T5.getText();String TX6=T6.getText();				int temp7=Integer.parseInt(U1);
								int temp1=Integer.parseInt(TX1);int temp2=Integer.parseInt(TX2);int temp6=Integer.parseInt(TX6);
								int temp3=Integer.parseInt(TX3);int temp4=Integer.parseInt(TX4);int temp5=Integer.parseInt(TX5);
								
								if(U1.length()==4)
								{
						if(U1.length()>0)
						{
			
	int r1=stmt.executeUpdate("Update RMaster Set M1="+temp1+", M2="+temp2+",M3="+temp3+", H1="+temp4+", H2="+temp5+" ,H3="+temp6+" where Season="+temp7);
	if(r1>0) JOptionPane.showMessageDialog(null,"Record Inserted");
						}
						Combo1.setSelectedIndex(-1);
						T1.setText("");T2.setText("");T3.setText("");T4.setText("");T5.setText("");T6.setText("");
							}
						else JOptionPane.showMessageDialog(null,"Enter 4 digit Season");			
					}
				catch(Exception e)
				{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
				}
			}
		}

	}	
}

		
	
	
	
	
	
	
	
	
	
class RMastertest
{
		public static void main(String args[])
		{
			RMaster M=new RMaster();
			
		}	
}	
