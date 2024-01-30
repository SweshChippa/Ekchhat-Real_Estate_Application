import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class addProperty implements ActionListener {

    JFrame frame;
    JPanel panel;

    // JFrame frame;
    JTextField propId;
    // JPanel panel;
    JLabel area;
    JTextField propArea;
    JLabel rooms;
    JTextField propRooms;
    JLabel year;

    JComboBox condate;
    JComboBox conmonth;
    JComboBox conyear;

    JLabel address;
    JTextArea propAddress;
    JLabel furnished;
    JTextField propFurnished;
    JLabel type;
    JTextField propType;
    JLabel agentId;
    JComboBox propAgentId;
    JLabel amount;
    JTextField propAmount;
    JLabel date;
    JComboBox dealdate;
    JComboBox dealmonth;
    JComboBox dealyear;
    
    JLabel For;
    JComboBox propFor;
    JLabel success;

String dates[]
    = { "01", "02", "03", "04", "05",
        "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25",
        "26", "27", "28", "29", "30",
        "31" };
String months[]
    = { "01", "02", "03", "04",
        "05", "06", "07", "08",
        "09", "10", "11", "12" };
String years[]
    = { "1995", "1996", "1997", "1998",
        "1999", "2000", "2001", "2002",
        "2003", "2004", "2005", "2006",
        "2007", "2008", "2009", "2010",
        "2011", "2012", "2013", "2014",
        "2015", "2016", "2017", "2018",
        "2019", "2020", "2021", "2022",
        "2023"};

String perpose[]
    = { "Rent", "Sale"};

    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/estate";
    String userName = "root";
    String password = "@iiitg123";

    public static void main(String[] args) {
       new addProperty();
    }

    public addProperty() {

    
        // create frame and panel
    frame = new JFrame("#EkChhat");
    // ImageIcon image = new ImageIcon("D:\\SWESH\\Programing\\RealEstate\\src\\bg_img.jpg");
    // frame.setIconImage(image.getImage());
    
    // adding panel to frame
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,600,550);
    panel.setBackground(Color.DARK_GRAY);
    frame.add(panel);

    
    
    try {
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(url, userName, password);
       
        Statement st=con.createStatement();
        // PreparedStatement ps = con.prepareStatement(sql);
        

        String query = "select count(*) from agents";
      //Executing the query
      ResultSet rs1 = st.executeQuery(query);
      //Retrieving the result
       rs1.next();
       int count = rs1.getInt(1);
       String[] agent = new String[count];
       String sql = "select Agent_Id from agents";
       ResultSet rs = st.executeQuery(sql);
      

        // int rowCount = rs.last() ? rs.getRow() : 0;
        
        int i=0;
        while(rs.next())
        {
            agent[i]=rs.getString("Agent_Id");
            i++;
        }
        JLabel label= new JLabel("<html><font size='5' color= #D3D3D3>---Enter Your Information---</font></html>");
    label.setBounds(30, 30, 500, 20);
    panel.add(label);


    JLabel ID = new JLabel("<html><font size='5' color= White> Property ID : </font></html>");
    ID.setBounds(300,100,200,30);
    panel.add(ID);

    propId = new JTextField();
    propId.setBounds(500,100,200,30);
    panel.add(propId);


    JLabel area = new JLabel("<html><font size='5' color= White> Carpet Area(sqft) :</font></html>");
    area.setBounds(300,140,200,30);
    panel.add(area);

    propArea = new JTextField();
    propArea.setBounds(500,140,200,30);
    panel.add(propArea);


        rooms = new JLabel("<html><font size='5' color= White> No of Bedrooms :</font></html>");
        rooms.setBounds(300,180,200,30);
        panel.add(rooms);

        propRooms = new JTextField();
        propRooms.setBounds(500,180,200,30);
        panel.add(propRooms);


    year= new JLabel("<html><font size='5' color= White> Construction Year :</font></html>");
    year.setBounds(300,220,200,30);
    panel.add(year);

    condate = new JComboBox(dates);
    condate.setFont(new Font("Arial", Font.PLAIN, 15));
    condate.setSize(50, 30);
    condate.setLocation(500, 220);
    panel.add(condate);
 
    conmonth = new JComboBox(months);
    conmonth.setFont(new Font("Arial", Font.PLAIN, 15));
    conmonth.setSize(60, 30);
    conmonth.setLocation(570, 220);
    panel.add(conmonth);
 
    conyear = new JComboBox(years);
    conyear.setFont(new Font("Arial", Font.PLAIN, 15));
    conyear.setSize(60, 30);
    conyear.setLocation(640, 220);
    panel.add(conyear);

    address = new JLabel("<html><font size='5' color= White> Address :</font></html>");
    address.setBounds(300,260,260,30);
    panel.add(address);

    propAddress = new JTextArea();
    propAddress.setBounds(500,260,260,60);
    panel.add(propAddress);

    furnished = new JLabel("<html><font size='5' color= White> Furnished :</font></html>");
    furnished.setBounds(300,330,200,30);
    panel.add(furnished);

    propFurnished = new JTextField();
    propFurnished.setBounds(500,330,200,30);
    panel.add(propFurnished);

    type = new JLabel("<html><font size='5' color= White> Property Type :</font></html>");
    type.setBounds(300,370,200,30);
    panel.add(type);

    propType = new JTextField();
    propType.setBounds(500,370,200,30);
    panel.add(propType);

    agentId = new JLabel("<html><font size='5' color= White> Agent ID :</font></html>");
    agentId.setBounds(300,410,200,30);
    panel.add(agentId);

    propAgentId = new JComboBox(agent);
    propAgentId.setFont(new Font("Arial", Font.PLAIN, 15));
    propAgentId.setSize(100, 30);
    propAgentId.setLocation(500, 410);
    panel.add(propAgentId);

    year= new JLabel("<html><font size='5' color= White> Date :</font></html>");
    year.setBounds(300,450,200,30);
    panel.add(year);

    dealdate = new JComboBox(dates);
    dealdate.setFont(new Font("Arial", Font.PLAIN, 15));
    dealdate.setSize(50, 30);
    dealdate.setLocation(500, 450);
    panel.add(dealdate);
 
    dealmonth = new JComboBox(months);
    dealmonth.setFont(new Font("Arial", Font.PLAIN, 15));
    dealmonth.setSize(60, 30);
    dealmonth.setLocation(570, 450);
    panel.add(dealmonth);
 
    dealyear = new JComboBox(years);
    dealyear.setFont(new Font("Arial", Font.PLAIN, 15));
    dealyear.setSize(60, 30);
    dealyear.setLocation(640, 450);
    panel.add(dealyear);

    For = new JLabel("<html><font size='5' color= White> Property For :</font></html>");
    For.setBounds(300,490,200,30);
    panel.add(For);

    propFor = new JComboBox(perpose);
    propFor.setFont(new Font("Arial", Font.PLAIN, 15));
    propFor.setSize(100, 30);
    propFor.setLocation(500, 490);
    panel.add(propFor);

    amount = new JLabel("<html><font size='5' color= White> Rent/Price :</font></html>");
    amount.setBounds(300,530,200,30);
    panel.add(amount);

    propAmount = new JTextField();
    propAmount.setBounds(500,530,200,30);
    panel.add(propAmount);
    


    JButton submit = new JButton("<html><font size='5' color= DarkGrey> Submit </font></html>");
    submit.setBounds(390, 600, 100, 30); 
    submit.addActionListener((ActionListener) this);
    // submit.setActionCommand("Open");
    submit.setBackground(Color.WHITE);
    panel.add(submit);

    frame.setBounds(320,20,930,700);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  catch (Exception e) {
        System.out.println(e);
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String Construction_Year,dealDate;
        Construction_Year=conyear.getSelectedItem()+"-"+conmonth.getSelectedItem()+"-"+condate.getSelectedItem();
        dealDate=dealyear.getSelectedItem()+"-"+dealmonth.getSelectedItem()+"-"+dealdate.getSelectedItem();
        String agentid=(String)(propAgentId.getSelectedItem());
        // DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD"); 
        // try {
        //     Date date = (Date)formatter.parse(Construction_Year);
        //     Date date1 = (Date)formatter.parse(dealDate);
        // } catch (ParseException e1) {
        //     // TODO Auto-generated catch block
        //     e1.printStackTrace();
        // }
        
        Connection con;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = con.prepareStatement("insert into property values(?,?,?,?,?,?,?)");
            ps.setInt(1,Integer.parseInt(propId.getText()));
            ps.setInt(2,Integer.parseInt(propArea.getText()));
            ps.setInt(3, Integer.parseInt(propRooms.getText()));
            ps.setString(4, Construction_Year);
            ps.setString(5, propAddress.getText());
            if(propFurnished.getText()=="1")
            {
                ps.setInt(6, 1);
            }
            else{
                ps.setInt(6, 0);
            }
            ps.setString(7, propType.getText());
            int flag=ps.executeUpdate();
            if(propFor.getSelectedItem().equals("Rent"))
            {
                PreparedStatement ps1 = con.prepareStatement("insert into house_for_rent values(?,?,?,?)");
                ps1.setInt(1,Integer.parseInt(propId.getText()));
                ps1.setInt(2, Integer.parseInt(agentid));
                ps1.setString(3, dealDate);
                ps1.setLong(4, Long.parseLong(propAmount.getText()));
                ps1.executeUpdate();
            }
            else{
                PreparedStatement ps1 = con.prepareStatement("insert into house_for_sale values(?,?,?,?)");
                ps1.setInt(1,Integer.parseInt(propId.getText()));
                ps1.setInt(2, Integer.parseInt(agentid));
                ps1.setString(3, dealDate);
                ps1.setLong(4, Long.parseLong(propAmount.getText()));
                ps1.executeUpdate();
            }
            if(flag==1)
            {
                JOptionPane.showInputDialog( "Property Succesfully listed!!!", "!!!Congratulation!!!");

                System.out.println("Success");
            }
            else{
                JOptionPane.showInputDialog(null,"Try Again!!!", "!!!Error!!!");
                System.out.println("mot success");
                
            }

        } 
        catch (Exception ee) {
            System.out.println(ee);
        }
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

