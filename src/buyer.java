import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class buyer {
    buyer(){
        JFrame frame = new JFrame("User Portal-BUY/RENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = frame.getToolkit();
        Dimension dm = tk.getScreenSize();
        int width = dm.width;
        int height = dm.height;
        frame.setLayout(null);
        frame.setBounds(width*2/10,height*2/10,width*27/100,height*5/10);
        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(70, 80,245, 250);
        panel.setBackground(new Color(102,178,255));
        Border border= BorderFactory.createTitledBorder("What You Want?:");
        panel.setBorder(border);
        frame.add(panel);
        frame.getContentPane().setBackground(new Color(224, 224, 224));
        
        
        // JLabel label = new JLabel("What You Want?:");
        // label.setBounds(45,20,100,30);
        // label.setFont(Font.getFont(Font.MONOSPACED)); 
        // panel.add(label);

        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(55,70,130,30);
        buyButton.setBackground(Color.LIGHT_GRAY);
        JButton rentButton = new JButton("Rent");
        rentButton.setBounds(55,140,130,30);
        rentButton.setBackground(Color.LIGHT_GRAY);
        panel.add(buyButton);
        panel.add(rentButton);

        buyButton.addActionListener(e -> {
            frame.dispose();
            // open new tab showing buy options
        });

        rentButton.addActionListener(e->{
            frame.dispose();
            // open new tab for showing rent options
        });
        frame.setVisible(true);
    }
}
