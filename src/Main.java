import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Main implements ActionListener {
    JFrame f;
    JButton btn1,btn2,btn3;
    public static void main(String[] args){
    new Main();
      // new login();
      // new buyerRegistration();
      // new AgentRegistration();
      // new sellerRegistration();
      // new buyerAccount();
    }
    Main(){
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
      jp.setBounds(width/3-50,height/2 -155,width/3 +40,240);
      
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

      f.add(jp);
      f.setLayout(null);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        f.dispose();
        if(e.getSource().equals(btn1))
        new login("AGENT");
        else if(e.getSource().equals(btn2)) new login("BUYER");
        else new login("SELLER");
    }    
}
