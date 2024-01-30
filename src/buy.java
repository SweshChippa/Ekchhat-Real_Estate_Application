import java.awt.*;
import javax.swing.*;

public class buy {
    JFrame frame;
    JPanel panel;
    public static void main(String [] args){
        new buy();
    }
    buy(){
    frame = new JFrame("#EkChhat");
    ImageIcon image = new ImageIcon("bg_img.jpeg");
    frame.setIconImage(image.getImage());
    
    // adding panel to frame
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(0,0,700,550);
    panel.setBackground(Color.YELLOW);
    frame.add(panel);

    // adding dropdown to panel
    String[] items = {"--Sort According--","Price", "location", "property-type"};
    JComboBox<String> comboBox = new JComboBox<>(items);
    comboBox.setBounds(100, 30, 160, 20);
    comboBox.setSelectedIndex(0);
    panel.add(comboBox);
    
    // adding scrollpane
    JScrollPane scrollPane = new JScrollPane();
    JPanel innerPanel = new JPanel();
    BoxLayout boxLayout =new BoxLayout(innerPanel, BoxLayout.Y_AXIS);
    innerPanel.setLayout(boxLayout);

    scrollPane.setBounds(30, 60, 820, 700);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


    for (int i = 1; i <= 100; i++) {
        // get data from database and add into the view holder
        view  a = new view("1001","Apartment","$500");
        innerPanel.add(a.getViewPannel());
    }

    int preferredHeight = 0;
    Component[] components = innerPanel.getComponents();
    for (Component component : components) {
    preferredHeight += component.getPreferredSize().height;
    }

    innerPanel.setPreferredSize(new Dimension(800, preferredHeight));
    
    scrollPane.setViewportView(innerPanel);
    panel.add(scrollPane);    

    frame.setBounds(320,20,900,800);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
