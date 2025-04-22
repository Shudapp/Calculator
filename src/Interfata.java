import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interfata extends JFrame implements ActionListener {
    //JFrame frame = new JFrame();
    //JPanel panel = new JPanel();
    String currentop, num1, num2;
    double actualnum1, actualnum2;
    double doubleResult;
    int intResult;
    JTextField display = new JTextField(16);
    boolean isDoubleFirst = false, isDoubleSecond = false;
    public Interfata() {
        //frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        //frame.setLayout(new FlowLayout());

        //display field
        display.setEditable(true);
        display.setHorizontalAlignment(JTextField.LEFT);
        display.setFont(display.getFont().deriveFont(24f));
        display.setPreferredSize(new Dimension(25, 50));


        //butoane
        JPanel buttons = new JPanel(new GridLayout(5,4,3,3));
        String[][] labels = {
        {"AC", "^", "%", "÷"},
        {"7" , "8", "9", "*"},
        {"4" , "5", "6", "-"},
        {"1" , "2", "3", "+"},
        {""  , "0", ".", "="}
        };

        for(int i = 0; i < labels.length; i++){
            for(int j = 0; j < labels[0].length; j++){
                String text = labels[i][j];
                JButton b = new JButton(text);
                b.addActionListener(this);
                b.setFont(new Font("Arial",Font.PLAIN,22));
                buttons.add(b);
            }
        }
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        display.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttons);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = e.getActionCommand();
        if(word.equals("%") || word.equals("+") || word.equals("-") || word.equals("*") || word.equals("÷") || word.equals("^")){
            currentop = word;
            if(num2 == null){
                num2 = "0";
            }
        }else if(word.equals("AC")){
            display.setText(null);
            num1 = num2 = null;
        }else if(word.equals("=")){
            //currentop = "=";
            if(num2 == null) {
                num2 = "0";
            }
            actualnum2 = Double.parseDouble(num2);
            actualnum1 = Double.parseDouble(num1);

            boolean isOk = true;
            if(actualnum1 % 1 == 0 && actualnum2 % 1 == 0 && currentop != "÷") {
                isOk = false;
                if(currentop.equals("+")) {
                    intResult = (int) actualnum1 + (int) actualnum2;
                } else if(currentop.equals("-")){
                    intResult = (int) actualnum1 - (int) actualnum2;
                } else if(currentop.equals("^")) {
                    intResult = (int) Math.pow(actualnum1, actualnum2);
                } else if(currentop.equals("%")) {
                    intResult = (int) actualnum1 % (int) actualnum2;
                }
                display.setText(Integer.toString(intResult));
                num1 = Integer.toString(intResult);
            }
<<<<<<< HEAD
            result = Double.toString(resultnum);
            if(isDoubleSecond == true){
                isDoubleFirst = true;
            }
            isDoubleSecond = false;
            num1 = result;
=======
            else {
                if(currentop.equals("+")) {
                    doubleResult = actualnum1 + actualnum2;
                } else if(currentop.equals("-")) {
                    doubleResult = actualnum1 - actualnum2;
                } else if(currentop.equals("*")) {
                    doubleResult = actualnum1 * actualnum2;
                } else if(currentop.equals("^")) {
                    doubleResult = Math.pow(actualnum1, actualnum2);
                } else if(currentop.equals("÷")) {
                    if(actualnum2 != 0){
                        doubleResult = actualnum1 / actualnum2;
                    }
                    else {
                        display.setText("Error");
                        isOk = false;
                    }
                } else if(currentop.equals("%")) {
                    doubleResult = actualnum1 % actualnum2;
                }
            }

>>>>>>> a555a0f (aoeu)
            num2 = null;
            if(isOk) {
                display.setText(Double.toString(doubleResult));
                num1 = Double.toString(doubleResult);
            }
        }else {
        if(num2 == null){
            if(num1 == null) {
                if(word == "."){
                    num1 = "0";
                }
                else num1 = word;
            }
            else {
                num1 += word;
            }
            display.setText(num1);
        } else {
<<<<<<< HEAD
            if(num2 == null){
                if(word == "."&& !isDoubleFirst){
                    isDoubleFirst = true;
                    num1 += word;
                } else if(word != "."){
                    if(num1 == null){
                        num1 = word;
                    } else {
                        num1 += word;
                    }
                }
                display.setText(num1);
            } else {
                if(word == "." && !isDoubleSecond){
                    isDoubleSecond = true;
                    num2 += word;
                } else if(word != "."){
                    if(num2 == "0"){
                        num2 = "";
                    }
                    num2+=word;
                }
                display.setText(num2);
=======
            if(num2 == "0"){
                num2 = "";
>>>>>>> a555a0f (aoeu)
            }
            num2 += word;
            display.setText(num2);
        }
    }
}
}
