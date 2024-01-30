import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuyerPortal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	JComboBox<String> comboBox;
	JCheckBox chckbxNewCheckBox;
	JCheckBox chckbxNewCheckBox_1;
	JCheckBox chckbxNewCheckBox_2;
	JCheckBox chckbxNewCheckBox_3;
	ResultSet result;

	private Connection conn;
	private String buyerEmail;
	private Integer buyerId;
	private JPanel panel_3;
	private JLabel lblNewLabel_4;
	private JComboBox<String> comboBox_1;
	private ArrayList<String> agents= new ArrayList<String>();
	private ArrayList<Integer> agent_id = new ArrayList<Integer>();
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// BuyerPortal window = new BuyerPortal();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public BuyerPortal(Connection connection, String email) {
		conn = connection;
		buyerEmail = email;
		try {
			ResultSet rs1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
					.executeQuery("select buyer_id from buyers where email='" + buyerEmail + "'");
			rs1.next();
			buyerId = rs1.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(150, 150, 150));
		frame.setBounds(100, 20, 1151, 834);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		// RoundedCorneredPanel panel = new RoundedCorneredPanel();
		panel.setBounds(104, 29, 832, 143);
		panel.setBackground(new Color(171, 222, 215));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Search By:");
		lblNewLabel.setBounds(28, 21, 116, 13);
		panel.add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Rent", "Buy" }));
		comboBox.setBounds(108, 17, 69, 21);
		panel.add(comboBox);

		chckbxNewCheckBox = new JCheckBox("Location");
		chckbxNewCheckBox.setBounds(38, 44, 78, 21);
		panel.add(chckbxNewCheckBox);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(122, 45, 134, 19);
		panel.add(textField);

		chckbxNewCheckBox_1 = new JCheckBox("Price");
		chckbxNewCheckBox_1.setBounds(310, 45, 93, 21);
		panel.add(chckbxNewCheckBox_1);

		JLabel lblNewLabel_1 = new JLabel("Min price");
		lblNewLabel_1.setBounds(362, 72, 63, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Max Price");
		lblNewLabel_2.setBounds(362, 101, 63, 13);
		panel.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(435, 69, 96, 19);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(435, 98, 96, 19);
		panel.add(textField_2);

		chckbxNewCheckBox_2 = new JCheckBox("Area(sq.ft)");
		chckbxNewCheckBox_2.setBounds(590, 45, 93, 21);
		panel.add(chckbxNewCheckBox_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(703, 45, 96, 19);
		panel.add(textField_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 272, 1031, 400);
		frame.getContentPane().add(scrollPane);

		scrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel panel_1 = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel_1, BoxLayout.Y_AXIS);
		panel_1.setLayout(boxLayout);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(696, 700, 295, 87);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(72, 193, 236, 45);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		lblNewLabel_4 = new JLabel("choose agent");
		lblNewLabel_4.setBounds(10, 10, 69, 25);
		panel_3.add(lblNewLabel_4);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(89, 12, 127, 21);
		panel_3.add(comboBox_1);
		frame.setVisible(true);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// write here
				agents.clear();
				agent_id.clear();
				comboBox_1.removeAllItems();
				panel_1.removeAll();
				comboBox_1.validate();
				panel_1.validate();
				String query = "select * from property natural join";
				if(chckbxNewCheckBox.isSelected() || chckbxNewCheckBox_1.isSelected() ||chckbxNewCheckBox_2.isSelected()){
					if (comboBox.getSelectedItem().equals("Rent")) {
						query += " house_for_rent";
					} else {
						query += " house_for_sale";
					}
					query += " where ";
					if (chckbxNewCheckBox.isSelected()) {
						query += "address like '%" + textField.getText() + "%' ";
					}
					if (chckbxNewCheckBox_1.isSelected()) {
						if (chckbxNewCheckBox.isSelected())
							query += "and ";
						query += "Price_In_Rs between " + textField_1.getText() + " and " + textField_2.getText() + " ";
					}
					if (chckbxNewCheckBox_2.isSelected()) {
						if (chckbxNewCheckBox_1.isSelected() || chckbxNewCheckBox.isSelected())
							query += "and ";
						query += "area_sq_ft between " + (Double.parseDouble(textField_3.getText()) - 100) + " and "
								+ (Double.parseDouble(textField_3.getText()) + 100);
					}
				}

				try {
					Statement pStatement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = pStatement.executeQuery(query);
					result = rs;
					while (rs.next()) {
						Statement pSt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs2 = pSt.executeQuery(
								"select agent_name from agents where agent_id =" + rs.getString("agent_id"));
						if(rs2.next() && agents.indexOf(rs2.getString("agent_name"))==-1){
							agents.add(rs2.getString("agent_name"));
							agent_id.add(rs.getInt("agent_id"));
						}	
					}
				} catch (Exception err) {
					System.out.println(err+"first catch");
				}
				comboBox_1.setModel(new DefaultComboBoxModel<String>(agents.toArray(new String[0])));
				paint_scroll(panel_1, scrollPane);
			}
		});
		btnNewButton.setBounds(811, 182, 85, 21);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(171, 222, 215));
		frame.getContentPane().add(btnNewButton);

		comboBox_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Get the selected item
					paint_scroll(panel_1, scrollPane);
				}
			}

		});



		JLabel lblNewLabel_3 = new JLabel("Property Id");
		lblNewLabel_3.setBounds(24, 10, 135, 13);
		panel_2.add(lblNewLabel_3);

		textField_4 = new JTextField();
		textField_4.setBounds(164, 7, 96, 19);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		chckbxNewCheckBox_3 = new JCheckBox("Agree the agreement");
		chckbxNewCheckBox_3.setVerticalAlignment(SwingConstants.TOP);
		chckbxNewCheckBox_3.setBounds(71, 29, 153, 19);
		panel_2.add(chckbxNewCheckBox_3);

		JButton btnNewButton_1 = new JButton("GET PROPERTY");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// write here

			}
		});
		btnNewButton_1.setBounds(81, 55, 133, 21);
		panel_2.add(btnNewButton_1);

	}

	public void paint_scroll(JPanel panel_1,JScrollPane scrollPane){
		panel_1.removeAll();
		panel_1.validate();
		String selectedItem = (String) comboBox_1.getSelectedItem();
		int Agid=-1;
		if(agents.indexOf(selectedItem)!=-1)Agid = agent_id.get(agents.indexOf(selectedItem));
		ResultSet rSet = result;
		try {
			result.beforeFirst();
			if (result.next()) {
				Integer prop_id = result.getInt("prop_id");
				Double area = result.getDouble("Area_sq_ft");
				Integer rooms = result.getInt("Bedrooms");
				Date year = result.getDate("Construction_Year");
				String address = result.getString("Address");
				Integer furnished = result.getInt("Furnished");
				String prop_type = result.getString("Property_Type");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				if(result.getInt("agent_id")==Agid){
					view a = new view(Integer.toString(prop_id), Double.toString(area), Integer.toString(rooms),
						dateFormat.format(year), address, Integer.toString(furnished), prop_type);
					panel_1.add(a.getViewPannel());
				}
				
				while (result.next()) {
					prop_id = result.getInt("prop_id");
					area = result.getDouble("Area_sq_ft");
					rooms = result.getInt("Bedrooms");
					year = result.getDate("Construction_Year");
					address = result.getString("Address");
					furnished = result.getInt("Furnished");
					prop_type = result.getString("Property_Type");
					dateFormat = new SimpleDateFormat("yyyy-mm-dd");
					if(result.getInt("agent_id")==Agid){
						view a = new view(Integer.toString(prop_id), Double.toString(area), Integer.toString(rooms),
							dateFormat.format(year), address, Integer.toString(furnished), prop_type);
						panel_1.add(a.getViewPannel());
					}
				}
			} else {
				JLabel label1 = new JLabel(
						"<html><font size='5' color= RED>!!!NO PROPERTIES LISTED!!!</font></html>");
				label1.setBounds(300, 80, 400, 50);
				panel_1.add(label1);
			}
		} catch (Exception err1) {
		  System.out.println(err1+"last catch");
		}

		int panelWidth = 0;
		int panelHeight = 0;
		Component[] components = panel_1.getComponents();
		for (Component component : components) {
		Dimension size = component.getPreferredSize();
		panelWidth = Math.max(panelWidth, size.width);
		panelHeight += size.height;
		}
		// Set the width and height of the panel
		panel_1.setPreferredSize(new Dimension(panelWidth, panelHeight));
		scrollPane.setViewportView(panel_1);
		result = rSet;
	}
}

