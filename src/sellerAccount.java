import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;

public class sellerAccount implements ActionListener {
    JFrame frame;
    JPanel panel;
    static JTable table;
    static Connection con;
    static String sellerEmail;
    static int sellerId;

    public void showTableData() {

        // create frame and panel
        frame = new JFrame("#EkChhat");
        ImageIcon image = new ImageIcon("bg_img.jpg");
        frame.setIconImage(image.getImage());

        // adding panel to frame
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 550);
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        JLabel logout = new JLabel("<html><font size='5' color= RED>Log Out</font></html>");
        logout.setForeground(Color.BLUE.darker());
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label
                frame.dispose();
                new Main();
            }
        });
        logout.setBounds(800, 10, 100, 30);
        panel.add(logout);

        JLabel label = new JLabel("<html><font size='5' color= #D3D3D3>---Properties Lisited---</font></html>");
        label.setBounds(30, 30, 200, 20);
        panel.add(label);

        // adding scrollpane
        JScrollPane scrollPane = new JScrollPane();
        JPanel innerPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(innerPanel, BoxLayout.Y_AXIS);
        innerPanel.setLayout(boxLayout);

        scrollPane.setBounds(30, 80, 850, 500);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            String sql = "select * from owns natural join property where seller_id =" + sellerId;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Integer prop_id = rs.getInt("prop_id");
                Double area = rs.getDouble("Area_sq_ft");
                Integer rooms = rs.getInt("Bedrooms");
                Date year = rs.getDate("Construction_Year");
                String address = rs.getString("Address");
                Integer furnished = rs.getInt("Furnished");
                String prop_type = rs.getString("Property_Type");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                view a = new view(Integer.toString(prop_id), Double.toString(area), Integer.toString(rooms),
                        dateFormat.format(year), address, Integer.toString(furnished), prop_type);
                innerPanel.add(a.getViewPannel());
                while (rs.next()) {
                    prop_id = rs.getInt("prop_id");
                    area = rs.getDouble("Area_sq_ft");
                    rooms = rs.getInt("Bedrooms");
                    year = rs.getDate("Construction_Year");
                    address = rs.getString("Address");
                    furnished = rs.getInt("Furnished");
                    prop_type = rs.getString("Property_Type");
                    dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                    a = new view(Integer.toString(prop_id), Double.toString(area), Integer.toString(rooms),
                            dateFormat.format(year), address, Integer.toString(furnished), prop_type);
                    innerPanel.add(a.getViewPannel());
                }
                con.close();
            } else {

                JLabel label1 = new JLabel("<html><font size='5' color= RED>!!!NO PROPERTIES LISTED!!!</font></html>");
                label1.setBounds(300, 80, 400, 50);
                innerPanel.add(label1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int panelWidth = 0;
        int panelHeight = 0;
        Component[] components = innerPanel.getComponents();
        for (Component component : components) {
            Dimension size = component.getPreferredSize();
            panelWidth = Math.max(panelWidth, size.width);
            panelHeight += size.height;
        }
        // Set the width and height of the panel
        innerPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        scrollPane.setViewportView(innerPanel);
        panel.add(scrollPane);

        // add button

        JButton add = new JButton("<html><font size='5' color= DarkGrey> ADD + </font></html>");
        add.setBounds(390, 600, 100, 30);
        add.addActionListener(this);
        add.setBackground(Color.WHITE);
        panel.add(add);

        frame.setBounds(320, 20, 930, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public sellerAccount(Connection connection, String email) {
        con = connection;
        sellerEmail = email;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select seller_id from sellers where email='" + sellerEmail + "'");
            rs.next();
            sellerId = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.showTableData();
    }

    public sellerAccount(Connection connection, int id) {
        con = connection;
        sellerId = id;
        this.showTableData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new addProperty(con, sellerId);
    }
}