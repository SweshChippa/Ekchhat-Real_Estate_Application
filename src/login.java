import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.sql.*;
import javax.swing.*;

public class login implements ActionListener {
   JFrame frame;
   JTextField tf;
   JPasswordField pf;
   int calledBY;
   String user = "";
   static Connection conn;
   static ResultSet rs;

   login(String calledby,Connection connection) {
      user = calledby;
      conn= connection;
      frame = new JFrame("M_RealEstate");
      ImageIcon icon = new ImageIcon("icon_image.png");
      frame.setIconImage(icon.getImage());
      JLabel lb= new JLabel("Agent Email id:");
      lb.setBounds(30, 50, 87, 20);
      
      if (calledby.equals("agents")) {
         calledBY = 0;
      } else if (calledby.equals("buyers")) {
         lb.setText("Buyer Email id:");
         calledBY = 1;
      } else {
    	  lb.setText("Seller Email id:");
         calledBY = 2;
      }
      frame.getContentPane().add(lb);

      tf = new JTextField();
      tf.setBounds(127, 51, 180, 20);
      frame.getContentPane().add(tf);

      JLabel lb2 = new JLabel("password:");
      lb2.setBounds(45, 100, 60, 20);
      frame.getContentPane().add(lb2);

      pf = new JPasswordField();
      pf.setBounds(127, 101, 180, 20);
      frame.getContentPane().add(pf);

      JButton btn = new JButton("Log in");
      btn.setBounds(195, 140, 90, 20);
      Color c = new Color(114, 171, 145);
      btn.setBackground(c);
      btn.addActionListener(this);
      frame.getContentPane().add(btn);

      JLabel hyperlink = new JLabel("Haven't Registered?");
      hyperlink.setForeground(Color.BLUE.darker());
      hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      hyperlink.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseClicked(MouseEvent e) {
            // the user clicks on the label
            frame.dispose();
            switch (calledBY) {
               case 0:
                  new AgentRegistration(conn); 
                  break;
               case 1:
                  new buyerRegistration(conn); 
                  break;
               case 2:
                  new sellerRegistration(conn); 
                  break;
            }
         }
      });
      hyperlink.setBounds(228, 170, 200, 20);
      frame.getContentPane().add(hyperlink);

      frame.getContentPane().setLayout(null);
      frame.setBounds(50, 50, 500, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String info = tf.getText();
      String passcode = new String(pf.getPassword());

      tf.setText("");
      pf.setText("");
      try {
         if (info.equals("") || passcode.toString().equals("")) {
            // re-enter valid credentials.
            JOptionPane.showMessageDialog(null, "Invalid Credentials!!!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid credentials");
         } else if (verifyCredential(info, passcode, user)) {
            frame.dispose();
            if(calledBY==0) new agentAccount(conn,info);
            else if(calledBY==1) new BuyerPortal(conn,info);//need to be improved
            else new sellerAccount(conn, info);
         }else{
            // incorrect password
            System.out.println("Password missmatch");
            JOptionPane.showMessageDialog(frame, "Invalid Credentials!!!", "Error", JOptionPane.ERROR_MESSAGE);
         }
      } catch (Exception ex) {
         System.out.println(ex);
      }
   }

   public static boolean verifyCredential(String email, String password, String table_name) throws Exception {
      String query = "select email,password from " + table_name;
      PreparedStatement pst = conn.prepareStatement(query);
      rs = pst.executeQuery();
      while (rs.next()) {
         if (email.equals(rs.getString("Email")) && password.equals(rs.getString("Password"))) {
            return true;
         }
      }
      rs.close();
      pst.close();
      return false;
   }
}
