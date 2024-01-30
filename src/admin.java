import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class admin {
    private JFrame frame;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private Connection connection;

    public admin(Connection conn){
        connection =conn;
        System.out.println("connection established");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 744, 501);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(253, 249, 237));
        panel.setBounds(10, 10, 710, 444);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Admin Id :");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(82, 60, 196, 32);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password :");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel_1.setBounds(129, 130, 117, 25);
        panel.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
        textField.setBounds(276, 63, 190, 32);
        panel.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(276, 129, 190, 32);
        panel.add(passwordField);

        JButton btnNewButton = new JButton("Log in");
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(new Color(207, 227, 94));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(359, 218, 85, 32);
        panel.add(btnNewButton);

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Execute SQL query to fetch table data
                Statement statement;
                try {
                    statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "select password from admin where admin.admin_id='" + textField.getText() + "'");
                    if(resultSet.next()) {
                        if (resultSet.getString("password").equals(new String(passwordField.getPassword()))) {
                            new adminLogin(conn);
                            // frame.dispose();
                        }else{
                            textField.setText("");
                            passwordField.setText("");
                        }
                    }else{
                        textField.setText("");
                        passwordField.setText("");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        frame.setVisible(true);
    }

}
