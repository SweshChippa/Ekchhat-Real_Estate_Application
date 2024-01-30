import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.*;

public class Main implements ActionListener {
    JFrame f;
    JButton btn1,btn2,btn3;
    static Connection conn;
    public static void main(String[] args){
       new Main();
    }
    Main(){
      try {
        conn = (new db_Connection()).getConnection();
      } catch (Exception e) {
        System.out.println(e);
      }
      f = new JFrame("HOME");
      Toolkit tk = f.getToolkit();
      Dimension dm = tk.getScreenSize();
      int width = dm.width;
      int height = dm.height;
      f.setBounds(width*2/10,height*2/10,width*6/10,height*6/10);
      
      width = width*6/10;
      height = height*6/10;
     
      JPanel jp = new JPanel(); 
      TitledBorder title;
      Border loweredbevel = BorderFactory.createLoweredBevelBorder();
      title = BorderFactory.createTitledBorder(
                       loweredbevel, "Role");
      title.setTitlePosition(TitledBorder.ABOVE_TOP);
      jp.setBorder(title);
      jp.setBounds(257,104,356,306);
      
      jp.setLayout(null);
     
      jp.setBackground(new Color(91, 190, 215));
      btn1 = new JButton("AGENT");
      btn1.setBounds(25,50,width/3-30,30);
      btn2 = new JButton("BUYER");
      btn2.setBounds(25,110,width/3-30,30);
      btn3 = new JButton("SELLER");
      btn3.setBounds(25,170,width/3-30,30);
      btn1.addActionListener(this);
      btn2.addActionListener(this);
      btn3.addActionListener(this);
      jp.add(btn1);
      jp.add(btn2);
      jp.add(btn3);

      f.getContentPane().add(jp);
      
      JButton btnNewButton = new JButton("ADMIN");
      btnNewButton.setBounds(25, 231, 277, 30);
      jp.add(btnNewButton);
      btnNewButton.addActionListener(this);
      f.getContentPane().setLayout(null);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        f.dispose();
        try{
          if(e.getSource().equals(btn1))
          new login("agents",conn);
          else if(e.getSource().equals(btn2)) new login("buyers",conn);
          else if(e.getSource().equals(btn3))new login("sellers",conn);
          else new admin(conn);
        }catch(Exception err){
          System.out.println(err);
        }
    }    
}
