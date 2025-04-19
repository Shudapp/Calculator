//String car = "$$$+789-456*123/$0.=";
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfata extends JFrame implements ActionListener {
    //JFrame frame = new JFrame();
    //JPanel panel = new JPanel();
    String currentop;
    String num1, num2;
    double actualnum1, actualnum2;
    double resultnum;
    String result;
    JTextField display = new JTextField(16);
    boolean isDoubleFirst = false;
    boolean isDoubleSecond = false;
    public Interfata() {
         //frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        //frame.setLayout(new FlowLayout());

        //display field
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(display.getFont().deriveFont(20f));
        display.setPreferredSize(new Dimension(25,  50));


        //butoane
        JPanel buttons = new JPanel(new GridLayout(4,4,5,5));
        String[] labels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "/" , "0", ".", "="};
        for(String text : labels){
            JButton b = new JButton(text);
            b.addActionListener(this);
            buttons.add(b);
        }
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttons, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = e.getActionCommand();
        if(word.equals("+") || word.equals("-") || word.equals("*") || word.equals("/")){
            currentop = word;
            if(num2 == null){
                num2 = "0";
            }
        } else if(word.equals("=")){
            //currentop = "=";
            if(num2 == null) {
                num2 = "0";
            }
            actualnum2 = Double.parseDouble(num2);
            actualnum1 = Double.parseDouble(num1);
            if(currentop.equals("+")) {
                resultnum = actualnum1 + actualnum2;
            } else if(currentop.equals("-")) {
                resultnum = actualnum1 - actualnum2;
            } else if(currentop.equals("*")) {
                resultnum = actualnum1 * actualnum2;
            } else if(currentop.equals("/")) {
                resultnum = actualnum1 / actualnum2;
            }
            result = Double.toString(resultnum);
            num1 = result;
            num2 = null;
            display.setText(num1);
        } else {
            if(num2 == null){
                if(word =="."){
                    isDoubleFirst = true;
                    if(num1 == null){
                        num1 = "0";
                    }
                    num1+=word;
                } else if(num1 == null){
                    num1 = word;
                } else {
                    num1 = num1 + word;
                }
                display.setText(num1);
            } else {
                if(word == "."){
                    isDoubleSecond = true;
                } else if(num2 =="0"){
                    num2="";
                }
                num2 = num2 + word;
                display.setText(num2);
            }
        }
        //System.out.println(num1 + " " + num2 + '\n');
    }
}
