import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class view {
  JPanel viewPanel = null;
  // public static void main(String[] args){
  // JFrame tempFrame = new JFrame();
  // tempFrame.setBounds(20,40,700,500);
  // tempFrame.setLayout(new BoxLayout(tempFrame.getContentPane(),
  // BoxLayout.Y_AXIS));
  // for(int i=0;i<10;i++){
  // view a = new view("1001","Apartment","$500");
  // tempFrame.add(a.getViewPannel());
  // }
  // tempFrame.setVisible(true);
  // }

  view(String prop_id, String area, String rooms, String year, String address, String furnished, String prop_type) {
	viewPanel = new JPanel();
	viewPanel.setLayout(null);
	viewPanel.setSize(300, 100);
	try {
	  addImage(viewPanel,"bg_img.jpeg");// add image of house
	} catch (Exception e) {
	  System.out.println(e);
	}

	// labeling propid
	JLabel propID = new JLabel("Property-ID:");
	propID.setBounds(280, 20, 80, 20);
	viewPanel.add(propID);

	JLabel property_id = new JLabel(prop_id);
	property_id.setBounds(370, 20, 80, 20);
	viewPanel.add(property_id);
	// labeling area
	JLabel propArea = new JLabel("Area:");
	propArea.setBounds(450, 20, 80, 20);
	viewPanel.add(propArea);

	JLabel property_area = new JLabel(area);
	property_area.setBounds(540, 20, 80, 20);
	viewPanel.add(property_area);
	// labeling rooms

	JLabel propRooms = new JLabel("Bedrooms:");
	propRooms.setBounds(600, 20, 80, 20);
	viewPanel.add(propRooms);

	JLabel property_rooms = new JLabel(rooms);
	property_rooms.setBounds(690, 20, 80, 20);
	viewPanel.add(property_rooms);

	// labeling year
	JLabel propYear = new JLabel("Year Of Construction:");
	propYear.setBounds(280, 40, 80, 20);
	viewPanel.add(propYear);

	JLabel property_year = new JLabel(year);
	property_year.setBounds(370, 40, 80, 20);
	viewPanel.add(property_year);

	// labeling address
	JLabel propAddress = new JLabel("Address:");
	propAddress.setBounds(450, 40, 80, 20);
	viewPanel.add(propAddress);

	JLabel property_address = new JLabel(address);
	property_address.setBounds(540, 40, 300, 20);
	viewPanel.add(property_address);

	// labeling furnished
	JLabel propFurnished = new JLabel("Furnished:");
	propFurnished.setBounds(280, 60, 80, 20);
	viewPanel.add(propFurnished);

	if (furnished.equals("1")) {
	  JLabel property_furnished = new JLabel("Yes");
	  property_furnished.setBounds(370, 60, 80, 20);
	  viewPanel.add(property_furnished);
	} else {
	  JLabel property_furnished = new JLabel("No");
	  property_furnished.setBounds(370, 60, 80, 20);
	  viewPanel.add(property_furnished);
	}

	// labeling prop_type
	JLabel propType = new JLabel("Property-Type:");
	propType.setBounds(450, 60, 100, 20);
	viewPanel.add(propType);

	JLabel property_type = new JLabel(prop_type);
	property_type.setBounds(540, 60, 80, 20);
	viewPanel.add(property_type);

	int panelWidth = 0;
	int panelHeight = 250;
	Component[] components = viewPanel.getComponents();
	for (Component component : components) {
	  Dimension size = component.getPreferredSize();
	  panelWidth = Math.max(panelWidth, size.width);
	}

	// Set the width and height of the panel
	viewPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

  }

  public JPanel getViewPannel() {
	return viewPanel;
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
	Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	Graphics2D g2d = dimg.createGraphics();
	Shape clip = new RoundRectangle2D.Double(0, 0,200,200, 20, 20);
	g2d.setClip(clip);
	g2d.drawImage(tmp, 0, 0, null);
	g2d.dispose();

	return dimg;
  }

  public static void addImage(JPanel pane, String filename) throws Exception {
	BufferedImage myPicture = ImageIO.read(new File(filename.trim()));
	JLabel picLabel = new JLabel(new ImageIcon(resize(myPicture, 200, 200)));
	picLabel.setBounds(20, 20, 210, 210);
	pane.add(picLabel);
  }

  
}
