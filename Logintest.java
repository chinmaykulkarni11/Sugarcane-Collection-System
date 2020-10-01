import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logintest  extends JFrame implements ActionListener
{
	JFrame F;
	JLabel Username,pw,login,reg;	
	JTextField T1;
	JButton Login,Register,Enter;
	Connection con;
	Statement stmt;
	ResultSet rs;
	JPasswordField p;
	int flag=0;
	
	public Logintest()
	{
		F=new JFrame();
		F.setVisible(true);
		F.setBounds(300,100,700,600);
		F.setTitle("Login");
		F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setLayout(null);
	
		T1=new JTextField();p=new JPasswordField();	
		Login=new JButton("Login");				Login.setBounds(100,100,200,30);
		Register=new JButton("Register");		Register.setBounds(350,100,200,30);
		Username= new JLabel("Enter UserName"); Username.setBounds(100,200,200,30);T1.setBounds(350,200,200,30);
		pw= new JLabel("Enter 8 digit Password");pw.setBounds(100,300,200,30);p.setBounds(350,300,200,30);
		Enter=new JButton("Enter");				Enter.setBounds(350,400,200,30);
		
		
		Font font1=new Font("Arial",Font.PLAIN,20);
		
		Login.setFont(font1);     T1.setFont(font1);     p.setFont(font1);  Enter.setFont(font1);        
		Register.setFont(font1);	Username.setFont(font1);pw.setFont(font1);         
	
		
		
					
	
		F.add(Login);F.add(Register);
	//	F.add(T1);F.add(Username);F.add(pw);F.add(T2);
	//	F.add(Enter);F.add(Back);
		

		
		
		Login.addActionListener(this);	Register.addActionListener(this);Enter.addActionListener(this);
		
		try{
			
			
			Class.forName("org.postgresql.Driver");
			
			//con = DriverManager.getConnection("jdbc:postgresql://192.168.100.10/tybcs16);
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5433/Sugarcane_collection","postgres","csk");
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
	
		
		
}
	
	
		
		public void actionPerformed(ActionEvent evt)
{
		if (evt.getSource()==Login)	
		{	
				
			F.add(T1);F.add(Username);F.add(pw);F.add(p);
			Enter.setText("Login"); p.setText("");
			F.add(Enter);
		
			flag=1;
			
			
		}
			
			
			
		if (evt.getSource()==Register)
		{	 
				
			F.add(T1);F.add(Username);F.add(pw);F.add(p);
			Enter.setText("Register Details");
			F.add(Enter); 
		
			flag=2;
					
		}
			
		if (evt.getSource()==Enter)
		{	 
			if(flag==2)
			{		
				
				String TX1=T1.getText();	
				String TX2=p.getText();
				
			try
			{
				if(TX2.length()>8&&TX1.length()>0)
				{
					int r1=stmt.executeUpdate("insert into  login values('"+TX1+"','"+TX2+"')");
					if(r1>0) 	 JOptionPane.showMessageDialog(null," Login and Password inserted\n");
				}
				else JOptionPane.showMessageDialog(null,"Enter atleat 8 digit Password\n");
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);

			}
				
			}	
			
			else if(flag==1)
			{		
				
				String TX1=T1.getText();	
				String TX2=p.getText();
				if(TX2.length()>8&&TX1.length()>0)
				{	
				
			
				try
					{	
					rs=stmt.executeQuery("select Password from login where Username='"+TX1+"'");
					
					while(rs.next())
						{	flag=0;
						String o=rs.getString(1);
						
						if(TX2.equals(o))
						new Mainmenu();	
						else JOptionPane.showMessageDialog(null,"Incorrect UserName or PassWord");
						}	
					}
					catch(Exception e)
					{
					JOptionPane.showMessageDialog(null,"Problem while fetching result "+e);
					}
				}		 
			}	else JOptionPane.showMessageDialog(null,"Incorrect UserName or PassWord");
				
		}
				
				
					
			}	
			
		
		
		
		
			

	


		public static void main(String args[])
		{
			Logintest v=new Logintest();
			
		}	

}