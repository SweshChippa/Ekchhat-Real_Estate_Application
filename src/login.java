import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class login implements ActionListener{
   JFrame frame;
   JTextField tf;
   JPasswordField pf;
   int calledBY;

   login(String calledby) {
      frame = new JFrame("M_RealEstate");
      ImageIcon icon = new ImageIcon("icon_image.png");
      frame.setIconImage(icon.getImage());
      JLabel lb;
      if(calledby.equals("AGENT")){
         lb = new JLabel("Agent id:");
         calledBY = 0;
      }  
      else if(calledby.equals("BUYER")){
         lb = new JLabel("Buyer id:");
         calledBY=1;
      } 
      else{
         lb = new JLabel("Seller id:");
         calledBY=2;
      } 
      lb.setBounds(30,50,60,20);
      frame.add(lb);

      tf = new JTextField();
      tf.setBounds(100,50,180,20);
      frame.add(tf);

      JLabel lb2 = new JLabel("password:");
      lb2.setBounds(30,100,60,20);
      frame.add(lb2);

      pf = new JPasswordField();
      pf.setBounds(100,100,180,20);
      frame.add(pf);

      JButton btn = new JButton("Log in");
      btn.setBounds(100,130,90,20);
      Color c = new Color(114, 171, 145);
      btn.setBackground(c);
      btn.addActionListener(this);
      frame.add(btn);

      JLabel hyperlink = new JLabel("Haven't Registered?");
      hyperlink.setForeground(Color.BLUE.darker());
      hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      hyperlink.addMouseListener(new MouseAdapter() {
 
         @Override
         public void mouseClicked(MouseEvent e) {
             // the user clicks on the label
             frame.dispose();
             switch(calledBY){
               case 0:
                new AgentRegistration();
                break;
               case 1:
                new buyerRegistration();
                break;
               case 2:
                new sellerRegistration();
                break;
             }
         }
      
         @Override
         public void mouseEntered(MouseEvent e) {
             // the mouse has entered the label
         }
      
         @Override
         public void mouseExited(MouseEvent e) {
             // the mouse has exited the label
         }
     });
      hyperlink.setBounds(145,170,200,20);
      frame.add(hyperlink);

      frame.setLayout(null);
      frame.setBounds(50, 50, 500, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
   //   String info = tf.getText();
   //   char []passcode = pf.getPassword();
   //   tf.setText("");
   //   pf.setText("");
   //   if(info.equals("") && passcode.toString().equals("")){
        
   //   }
   //   else if(info.equals("")){

   //   }
   //   else if(passcode.toString().equals("")){

   //   }
   //   if(calledBY==0){

   //   }else if(calledBY==1){

   //   }else{
      
   //   }
   }
}