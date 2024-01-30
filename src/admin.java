import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class admin {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			admin window = new admin();
			window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 904, 712);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to Admin Portal");
		lblNewLabel.setBounds(277, 41, 226, 20);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Type SQLQuery");
		lblNewLabel_1.setBounds(98, 99, 139, 27);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(183, 132, 464, 135);
		JTextPane textPane = new JTextPane();
		textPane.setToolTipText("Type your query here");
		textPane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textPane.setBounds(183, 132, 464, 135);
    scrollPane1.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setViewportView(textPane);
        panel.add(scrollPane1);

		JButton btnNewButton = new JButton("Execute");
		btnNewButton.setBounds(510, 277, 127, 37);
		btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 15));
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 324, 717, 304);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table_1 = new JTable();
		scrollPane.setColumnHeaderView(table_1);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printTable();
			}

			private void printTable() {
				try {
					// Establish database connection
					String url = "jdbc:mysql://localhost:3306/estate";
					String user = "root";
					String password = "@iiitg123";
					Connection connection = DriverManager.getConnection(url, user, password);
					System.out.println("connection established");
					// Execute SQL query to fetch table data
					String sql = textPane.getText().toString();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(sql);
					System.out.println("printing tables");
					// Create a TableModel to hold table data
					TableModel tableModel = new DefaultTableModel();
					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
						((DefaultTableModel) tableModel).addColumn(resultSet.getMetaData().getColumnName(i));
					}
					// Add more columns as needed

					// Populate the TableModel with fetched data
					while (resultSet.next()) {
						Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
						for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
							row[i - 1] = resultSet.getObject(i);
						}
						// Add more rows as needed
						((DefaultTableModel) tableModel).addRow(row);
					}

					// Set the TableModel to the JTable
					table.setModel(tableModel);
                    table_1.setModel(tableModel);
					// Close database resources
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}