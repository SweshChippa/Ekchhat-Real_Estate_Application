import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class agentAccount {

    JFrame frame;
    JPanel panel;
    static JTable table;
    static Connection con;
    static String agentEmail;
    static int agentId;
    // public static void main(String[] args) {
    // new agentAccount();
    // }

    public agentAccount(Connection connection, String email) {
        con = connection;
        agentEmail = email;
        try {
            Statement st_1 = con.createStatement();
            ResultSet rs_1 = st_1.executeQuery("select agent_id from agents where email='" + agentEmail + "'");
            rs_1.next();
            agentId = rs_1.getInt(1);
        } catch (Exception e1) {
            System.out.println(e1);
        }

        // agentId =
        // create frame and panel
        frame = new JFrame("#EkChhat");
        ImageIcon image = new ImageIcon("D:\\SWESH\\Programing\\RealEstate\\src\\bg_img.jpg");
        frame.setIconImage(image.getImage());

        // adding panel to frame
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 550);
        panel.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(0, 0, 600, 550);
        scrollPane1.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Logout button

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

        JLabel label = new JLabel("<html><font size='5' color= #D3D3D3>---Property for Sale---</font></html>");
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
            String sql = "select * from house_for_sale natural join property where Agent_Id=" + agentId;
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

        JLabel label2 = new JLabel("<html><font size='5' color= #D3D3D3>---Property for Rent---</font></html>");
        label2.setBounds(30, 650, 200, 20);
        panel.add(label2);

        // adding scrollpane2
        JScrollPane scrollPane2 = new JScrollPane();
        JPanel innerPanel2 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(innerPanel2, BoxLayout.Y_AXIS);
        innerPanel2.setLayout(boxLayout2);

        scrollPane2.setBounds(30, 720, 850, 500);
        scrollPane2.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            String sql = "select * from house_for_rent natural join property where Agent_Id=" + agentId;
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
                innerPanel2.add(a.getViewPannel());
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
                    innerPanel2.add(a.getViewPannel());
                }
            } else {

                JLabel label3 = new JLabel("<html><font size='5' color= RED>!!!NO PROPERTIES LISTED!!!</font></html>");
                label3.setBounds(300, 80, 400, 50);
                innerPanel2.add(label3);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int panelWidth2 = 0;
        int panelHeight2 = 0;
        Component[] components2 = innerPanel2.getComponents();
        for (Component component2 : components2) {
            Dimension size = component2.getPreferredSize();
            panelWidth2 = Math.max(panelWidth2, size.width);
            panelHeight2 += size.height;
        }
        // Set the width and height of the panel
        innerPanel2.setPreferredSize(new Dimension(panelWidth2, panelHeight2));

        scrollPane2.setViewportView(innerPanel2);
        panel.add(scrollPane2);

        JLabel label4 = new JLabel("<html><font size='5' color= #D3D3D3>---Property Sold---</font></html>");
        label4.setBounds(30, 1300, 200, 20);
        panel.add(label4);

        // adding scrollpane3
        JScrollPane scrollPane3 = new JScrollPane();
        JPanel innerPanel3 = new JPanel();
        BoxLayout boxLayout3 = new BoxLayout(innerPanel3, BoxLayout.Y_AXIS);
        innerPanel3.setLayout(boxLayout3);

        scrollPane3.setBounds(30, 1370, 850, 500);
        scrollPane3.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            String sql = "select * from house_rented natural join property where agent_id=" + agentId;
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
                innerPanel3.add(a.getViewPannel());
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
                    innerPanel3.add(a.getViewPannel());
                }

            } else {

                JLabel label5 = new JLabel("<html><font size='5' color= RED>!!!NO PROPERTIES Sold!!!</font></html>");
                label5.setBounds(300, 80, 400, 50);
                innerPanel3.add(label5);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int panelWidth3 = 0;
        int panelHeight3 = 0;
        Component[] components3 = innerPanel3.getComponents();
        for (Component component3 : components3) {
            Dimension size = component3.getPreferredSize();
            panelWidth3 = Math.max(panelWidth3, size.width);
            panelHeight3 += size.height;
        }
        // Set the width and height of the panel
        innerPanel3.setPreferredSize(new Dimension(panelWidth3, panelHeight3));

        scrollPane3.setViewportView(innerPanel3);
        panel.add(scrollPane3);

        JLabel label6 = new JLabel("<html><font size='5' color= #D3D3D3>---Property Rented---</font></html>");
        label6.setBounds(30, 1950, 200, 20);
        panel.add(label6);

        // adding scrollpane4
        JScrollPane scrollPane4 = new JScrollPane();
        JPanel innerPanel4 = new JPanel();
        BoxLayout boxLayout4 = new BoxLayout(innerPanel4, BoxLayout.Y_AXIS);
        innerPanel4.setLayout(boxLayout4);

        scrollPane4.setBounds(30, 2020, 850, 500);
        scrollPane4.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            String sql = "select * from house_sold natural join property where agent_id=" + agentId;
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
                innerPanel4.add(a.getViewPannel());
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
                    innerPanel4.add(a.getViewPannel());
                }

            } else {

                JLabel label7 = new JLabel("<html><font size='5' color= RED>!!!NO PROPERTIES Rented!!!</font></html>");
                label7.setBounds(300, 80, 400, 50);
                innerPanel4.add(label7);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        int panelWidth4 = 0;
        int panelHeight4 = 0;
        Component[] components4 = innerPanel4.getComponents();
        for (Component component4 : components4) {
            Dimension size = component4.getPreferredSize();
            panelWidth4 = Math.max(panelWidth4, size.width);
            panelHeight4 += size.height;
        }
        // Set the width and height of the panel
        innerPanel4.setPreferredSize(new Dimension(panelWidth4, panelHeight4));

        scrollPane4.setViewportView(innerPanel4);
        panel.add(scrollPane4);

        panel.setPreferredSize(new Dimension(600, 2600));
        // adding outer panel tp frame
        scrollPane1.setViewportView(panel);
        frame.add(scrollPane1);

        frame.setBounds(320, 20, 930, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
