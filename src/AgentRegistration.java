import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgentRegistration implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel agentname;
    JTextField tfName;
    JLabel agentPhone;
    JTextField tfPhone;
    JLabel agentEmail;
    JTextField tfEmail;
    JLabel agentLocality;
    JTextField tfLocality;
    JLabel agentPassword;
    JPasswordField tfPassword;
    JButton btn;
    JLabel success;
    JComboBox<String> comboBox;
    static Connection connection;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JLabel lblNewLabel_2;
    private JTextField textField_1;

    AgentRegistration(Connection conn) {
        connection = conn;
        panel = new JPanel();
        panel.setBounds(37, 32, 358, 321);
        frame = new JFrame("agentS REGISTRATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBounds(50, 50, 500, 400);

        frame.getContentPane().add(panel);
        panel.setLayout(null);

        agentname = new JLabel("Name");
        agentname.setBounds(1, 25, 80, 20);
        panel.add(agentname);

        tfName = new JTextField();
        tfName.setBounds(80, 25, 180, 20);
        panel.add(tfName);

        agentPhone = new JLabel("Ph. NO.");
        agentPhone.setBounds(1, 50, 80, 20);
        panel.add(agentPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(80, 50, 180, 20);
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

        agentEmail = new JLabel("Email");
        agentEmail.setBounds(1, 75, 80, 20);
        panel.add(agentEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(80, 75, 180, 20);
        panel.add(tfEmail);

        agentLocality = new JLabel("Locality");
        agentLocality.setBounds(1, 100, 80, 20);
        panel.add(agentLocality);

        tfLocality = new JTextField();
        tfLocality.setBounds(80, 100, 180, 20);
        panel.add(tfLocality);

        agentPassword = new JLabel("Password");
        agentPassword.setBounds(1, 208, 80, 20);
        panel.add(agentPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(80, 209, 180, 19);
        panel.add(tfPassword);

        btn = new JButton("Register");
        btn.setBounds(182, 245, 90, 20);
        Color c = new Color(114, 171, 145);
        btn.setBackground(c);
        btn.addActionListener(this);
        panel.add(btn);

        success = new JLabel("");
        success.setBounds(117, 275, 231, 25);
        panel.add(success);

        lblNewLabel = new JLabel("Adhar ");
        lblNewLabel.setBounds(0, 133, 53, 13);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(80, 126, 180, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) || textField.getText().length() >= 12) {
                    e.consume();
                }
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Age");
        lblNewLabel_1.setBounds(0, 159, 45, 13);
        panel.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) || textField_1.getText().length() >= 2) {
                    e.consume();
                }
            }
        });
        textField_1.setBounds(80, 156, 180, 19);
        panel.add(textField_1);
        textField_1.setColumns(10);

        lblNewLabel_2 = new JLabel("Gender");
        lblNewLabel_2.setBounds(1, 185, 45, 13);
        panel.add(lblNewLabel_2);

        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female", "Others" }));
        comboBox.setBounds(80, 182, 80, 21);
        panel.add(comboBox);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String locality = tfLocality.getText();
        String password = new String(tfPassword.getPassword());
        String adharString = textField.getText();
        int age = Integer.parseInt(textField_1.getText());
        String Gender = comboBox.getSelectedItem().toString();
        if (!name.equals("") && !phone.equals("") && !email.equals("") && !locality.equals("")
                && !password.equals("") && !adharString.equals("") && age > 0) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(
                        "Insert into agents(agent_name,contact,email,password,aadhaar_pan,age,gender,address) values(?,?,?,?,?,?,?,?)");
                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                pstmt.setString(3, email);
                pstmt.setString(4, password);
                pstmt.setString(5, adharString);
                pstmt.setInt(6, age);
                pstmt.setString(7, Gender);
                pstmt.setString(8, locality);
                pstmt.executeUpdate();
            } catch (Exception err) {
                System.out.println(err);
            }

            success.setText("Successfully Registered!!!");
            frame.dispose();
            new login("agents", connection);
        }
    }
}