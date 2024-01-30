import java.awt.*;
// import java.awt.Dimension;
// import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JPasswordField;
// import javax.swing.JTextField;

public class buyerRegistration implements ActionListener{
    JFrame frame;
    JTextField tfId;
    JPanel panel;
    JLabel buyerName;
    JTextField tfName;
    JLabel buyerPhone;
    JTextField tfPhone;
    JLabel buyerEmail;
    JTextField tfEmail;
    JLabel buyerLocality;
    JTextField tfLocality;
    JLabel buyerPassword;
    JPasswordField tfPassword;
    JButton btn;
    JLabel success;

    public static void main(String[] args) {
        
    }
    buyerRegistration()
    {
        
        panel= new JPanel();
        panel.setBounds(50,50 , 500, 400);
        frame = new JFrame("BUYER REGISTRATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(50, 50, 500, 400);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Component panel;
        frame.add(panel);
        panel.setLayout(null);

        JLabel buyerID = new JLabel("ID:");
        buyerID.setBounds(1,0,80,20);
        panel.add(buyerID);

        tfId = new JTextField();
        tfId.setBounds(80,0,180,20);
        panel.add(tfId);

        buyerName = new JLabel("Name");
        buyerName.setBounds(1,25,80,20);
        panel.add(buyerName);

        tfName =new JTextField();
        tfName.setBounds(80,25,180,20);
        panel.add(tfName);

        buyerPhone = new JLabel("Ph. NO.");
        buyerPhone.setBounds(1,50,80,20);
        panel.add(buyerPhone);

        tfPhone =new JTextField();
        tfPhone.setBounds(80,50,180,20);
        panel.add(tfPhone);

        buyerEmail = new JLabel("Email");
        buyerEmail.setBounds(1,75,80,20);
        panel.add(buyerEmail);

        tfEmail =new JTextField();
        tfEmail.setBounds(80,75,180,20);
        panel.add(tfEmail);
        
        buyerLocality = new JLabel("Locality");
        buyerLocality.setBounds(1,100,80,20);
        panel.add(buyerLocality);

        tfLocality =new JTextField();
        tfLocality.setBounds(80,100,180,20);
        panel.add(tfLocality);
        
        buyerPassword = new JLabel("Password");
        buyerPassword.setBounds(1,125,80,20);
        panel.add(buyerPassword);

        tfPassword =new JPasswordField();
        tfPassword.setBounds(80,125,180,20);
        panel.add(tfPassword);

        btn = new JButton("Register");
        btn.setBounds(100,180,90,20);
        Color c = new Color(114, 171, 145);
        btn.setBackground(c);
        btn.addActionListener(this);
        panel.add(btn);

        success= new JLabel("");
        success.setBounds(100, 210, 400, 25);
        panel.add(success);


        frame.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("Button Clicked");
        String id=tfId.getText();
        String name=tfName.getText();
        String phone=tfPhone.getText();
        String email=tfEmail.getText();
        String locality=tfLocality.getText();
        String password=tfPassword.getText();

        System.out.println(id+"\n"+name+"\n"+phone+"\n"+email+"\n"+locality+"\n"+password);
        success.setText("Successfully Registered!!!");
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}