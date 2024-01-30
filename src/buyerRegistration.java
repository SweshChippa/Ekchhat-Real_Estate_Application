import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;

public class buyerRegistration implements ActionListener {
    JFrame frame;
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
    static Connection connection;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JTextField textField_1;

    buyerRegistration(Connection conn) {
        connection = conn;
        panel = new JPanel();
        panel.setBounds(45, 30, 372, 310);
        frame = new JFrame("BUYER REGISTRATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBounds(50, 50, 500, 400);

        frame.getContentPane().add(panel);
        panel.setLayout(null);

        buyerName = new JLabel("Name");
        buyerName.setBounds(1, 12, 80, 20);
        panel.add(buyerName);

        tfName = new JTextField();
        tfName.setBounds(80, 11, 180, 19);
        panel.add(tfName);

        buyerPhone = new JLabel("Ph. NO.");
        buyerPhone.setBounds(1, 44, 80, 20);
        panel.add(buyerPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(80, 41, 180, 19);
        panel.add(tfPhone);

        tfPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) || tfPhone.getText().length() >= 10) {
                    e.consume();
                }
            }
        });

        buyerEmail = new JLabel("Email");
        buyerEmail.setBounds(1, 76, 80, 20);
        panel.add(buyerEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(80, 71, 180, 19);
        panel.add(tfEmail);

        buyerLocality = new JLabel("Locality");
        buyerLocality.setBounds(1, 108, 80, 20);
        panel.add(buyerLocality);

        tfLocality = new JTextField();
        tfLocality.setBounds(80, 101, 180, 19);
        panel.add(tfLocality);

        buyerPassword = new JLabel("Password");
        buyerPassword.setBounds(1, 190, 80, 20);
        panel.add(buyerPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(80, 191, 180, 19);
        panel.add(tfPassword);

        btn = new JButton("Register");
        btn.setBounds(152, 224, 90, 20);
        Color c = new Color(114, 171, 145);
        btn.setBackground(c);
        btn.addActionListener(this);
        panel.add(btn);

        success = new JLabel("");
        success.setBounds(140, 264, 188, 25);
        panel.add(success);

        lblNewLabel = new JLabel("age");
        lblNewLabel.setBounds(1, 140, 45, 13);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(80, 131, 180, 19);
        panel.add(textField);
        textField.setColumns(10);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) || textField.getText().length() >= 2) {
                    e.consume();
                }
            }
        });

        lblNewLabel_1 = new JLabel("adhar");
        lblNewLabel_1.setBounds(1, 165, 45, 13);
        panel.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(79, 161, 181, 19);
        panel.add(textField_1);
        textField_1.setColumns(10);

        textField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) || textField_1.getText().length() >= 10) {
                    e.consume();
                }
            }
        });

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String locality = tfLocality.getText();
        String adharString = textField_1.getText();
        String password = new String(tfPassword.getPassword());
        int age = Integer.parseInt(textField.getText());
        if (!name.equals("") && !phone.equals("") && !email.equals("") && !locality.equals("")
                && !password.equals("") && !adharString.equals("") && age > 0) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(
                        "Insert into buyers(name,contact,email,password,aadhaar_pan,age,address) values(?,?,?,?,?,?,?)");
                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                pstmt.setString(3, email);
                pstmt.setString(4, password);
                pstmt.setString(5, adharString);
                pstmt.setInt(6, age);
                pstmt.setString(7, locality);
                pstmt.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }

            success.setText("Successfully Registered!!!");
            frame.dispose();
            new login("buyers", connection);
        }
    }
}