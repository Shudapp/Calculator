//String car = "$$$+789-456*123/$0.=";
import javax.swing.*;
import java.awt.*;

public class Interfata {
    //JFrame frame = new JFrame();
    //JPanel panel = new JPanel();
    public Interfata() {
         //frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");

        //display field
        JTextField display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(display.getFont().deriveFont(20f));
        display.setPreferredSize(new Dimension(25,  50));


        //butoane
        JPanel buttons = new JPanel(new GridLayout(4,4,5,5));
        String[] labels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "/" , "0", ".", "="};
        for(String text : labels){
            buttons.add(new JButton(text));
        }
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
