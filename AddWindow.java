import javax.swing.*;
import java .awt.*;
import java.awt.event.*;
import java.sql.*;

class AddWindow extends JFrame
{
	Container c;
	JLabel	 labRno ,labName , labS1, labS2  , labS3;
       JTextField  txtRno,txtName,txtS1,txtS2,txtS3;		
	JButton	btnSave,  btnBack;
		
	AddWindow()
	{
	c=getContentPane();
	c.setLayout(null);
	
	labRno=new JLabel("roll no");
	labName=new JLabel("Name");
	labS1=new JLabel("S1");
	labS2=new JLabel("S2");
	labS3=new JLabel("S3");
	txtRno=new JTextField(10);
	txtName=new JTextField(2);
	txtS1=new JTextField(10);
	txtS2=new JTextField(10);
	txtS3=new JTextField(10);
	btnSave=new JButton("Save");
	btnBack=new JButton("Back");

	Font f=new Font("Arial",Font.BOLD,30);

	labRno.setFont(f);
	labName.setFont(f);
	labS1.setFont(f);
	labS2.setFont(f);
	labS3.setFont(f);
	txtRno.setFont(f);
	txtName.setFont(f);
	txtS1.setFont(f);
	txtS2.setFont(f);
	txtS3.setFont(f);
	btnSave.setFont(f);
	btnBack.setFont(f);

	labRno.setBounds(120,10,200,40);	
	txtRno.setBounds(100,50,200,40);
	labName.setBounds(120,100,200,40);
	txtName.setBounds(100,140,200,40);
	labS1.setBounds(120,190,200,40);
	txtS1.setBounds(100,240,200,40);
	labS2.setBounds(120,280,200,40);
	txtS2.setBounds(100,330,200,40);
	labS3.setBounds(120,370,200,40);
	txtS3.setBounds(100,420,200,40);
	
	btnSave.setBounds(120,500,200,40);
	btnBack.setBounds(120,550,200,40);


	c.add(labRno);
	c.add(labName);
	c.add(labS1);
	c.add(labS2);
	c.add(labS3);
	c.add(txtRno);
	c.add(txtName);
	c.add(txtS1);
	c.add(txtS2);
	c.add(txtS3);
	c.add(btnSave);
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
	String sql="insert into student values(?,?,?,?,?)";
	PreparedStatement pst=con.prepareStatement(sql);
	int rno=Integer.parseInt(txtRno.getText());
	String name=txtName.getText();
	int s1=Integer.parseInt(txtS1.getText());
	int s2=Integer.parseInt(txtS2.getText());
	int s3=Integer.parseInt(txtS2.getText());
	pst.setInt(1,rno);
	pst.setString(2,name);
	pst.setInt(3,s1);
	pst.setInt(4,s2);
	pst.setInt(5,s3);
	pst.executeUpdate();
	 JOptionPane.showMessageDialog(c,"record saved");
	con.close();
	txtRno.setText("");
	txtName.setText("");
	txtS1.setText("");
	txtS2.setText("");
	txtS3.setText("");
	txtRno.requestFocus();
	}
	catch(SQLException e)
	{
	JOptionPane.showMessageDialog(c,"issue"+e);
	}
	};
btnSave.addActionListener(a2);

	setTitle("Add Record");
	setSize(400,650);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	
	}
}