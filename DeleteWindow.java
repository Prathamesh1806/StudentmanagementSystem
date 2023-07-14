import javax.swing.*;
import java .awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteWindow extends JFrame
{
	Container c;
	JLabel	 labRno ;
       JTextField  txtRno;		
	JButton	 btnDelete,  btnBack;
		
	DeleteWindow()
	{
	c=getContentPane();
	c.setLayout(null);
	
	labRno=new JLabel("Roll no");
	txtRno=new JTextField(10);
	btnDelete=new JButton("Delete");
	btnBack=new JButton("Back");

	Font f=new Font("Arial",Font.BOLD,30);

	labRno.setFont(f);
	txtRno.setFont(f);
	btnDelete.setFont(f);
	btnBack.setFont(f);

	labRno.setBounds(100,10,200,40);	
	txtRno.setBounds(80,50,200,40);
	
	btnDelete.setBounds(80,100,200,40);
	btnBack.setBounds(80,150,200,40);

	c.add(labRno);
	c.add(txtRno);
	c.add(btnDelete);
	c.add(btnBack);

ActionListener a1=(ae)->{
	MainWindow a=new MainWindow();
	dispose();
};

btnBack.addActionListener(a1);

ActionListener a2=(ae)->{
	try{
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	String url="jdbc:mysql://localhost:3306/sms_15jan23";
	Connection con=DriverManager.getConnection(url,"root","abc123");
	String sql="delete from student  where rno=?";
	PreparedStatement pst=con.prepareStatement(sql);
	int rno=Integer.parseInt(txtRno.getText());
	pst.setInt(1,rno);
	long r=pst.executeUpdate();
	if(r==0){	
		JOptionPane.showMessageDialog(c, "Record not found.");}
	elseif(rno<1){	
		JOptionPane.showMessageDialog(c, "only positive integers allowed ");}
	else{
	 JOptionPane.showMessageDialog(c,"record Deleted");}
	con.close();
	txtRno.setText("");
	txtRno.requestFocus();
	}
	catch(SQLException e)
	{
	JOptionPane.showMessageDialog(c,"issue"+e);
	}
	catch(NumberFormatException e)
	{
	JOptionPane.showMessageDialog(c,"Integers only");
	}

};

btnDelete.addActionListener(a2);

	setTitle("Delete Record");
	setSize(400,300);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	
	}
}