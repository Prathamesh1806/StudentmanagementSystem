import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewWindow extends JFrame
{
Container c;
TextArea taData;
JButton btnBack;

ViewWindow()
{
c=getContentPane();
c.setLayout(new FlowLayout());

taData=new TextArea (10,30);
btnBack=new JButton("Back");

Font f=new Font("Arial",Font.BOLD,30);

taData.setFont(f);
btnBack.setFont(f);

c.add(taData);
c.add(btnBack);

ActionListener a1=(ae)->{
	MainWindow a=new MainWindow();
	dispose();
};

btnBack.addActionListener(a1);
taData.setEditable(false);
try{
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url="jdbc:mysql://localhost:3306/sms_15jan23";
	String username="root";
	String password="abc123";
	Connection con=DriverManager.getConnection(url,username,password);
	String sql="select * from student";
	PreparedStatement pst=con.prepareStatement(sql);
	ResultSet rs=pst.executeQuery();
	StringBuffer info=new StringBuffer();
	while(rs.next())
		info.append("rno="+rs.getInt(1)+" name= "+ rs.getString(2) +" S1=" +rs.getInt(3) +"  S2=" + rs.getInt(4) +" S3=" +rs.getInt(5)+"\n")	;	
	taData.setText(info.toString());
	con.close();
	}
catch(SQLException e)	
{
	JOptionPane.showMessageDialog(c,"issues"+e);
}	
	
setTitle("View Window");
setSize(1000,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

	}
}