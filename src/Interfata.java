import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Interfata extends JFrame implements ActionListener {
    String currentop, num1, num2;
    DecimalFormat df = new DecimalFormat("#,###.######");
    double actualnum1, actualnum2;
    double doubleResult;
    int intResult;
    JTextField display = new JTextField();
    Boolean FirstSquared = false;
    Boolean SecondSquared = false;
    public Interfata() {
        //frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");

        //display field
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.LEFT);
        display.setFont(display.getFont().deriveFont(24f));
        display.setPreferredSize(new Dimension(25, 50));

        //butoane
        JPanel buttons = new JPanel(new GridLayout(5,4,3,3));
        String[][] labels = {
        {"√"  , "^", "%", "÷"},
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
                b.setFocusable(false);
                b.setFont(new Font("Arial",Font.BOLD,22));
                buttons.add(b);
            }
        }
        buttons.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
        display.setBorder(BorderFactory.createEmptyBorder(10,20,10,0));

        add(display, BorderLayout.NORTH);
        add(buttons);
        //clear si delete
        JButton clrButton = new JButton("Clear");
        JButton delButton = new JButton("Delete");
        clrButton.addActionListener(this);
        delButton.addActionListener(this);
        clrButton.setFocusable(false);
        delButton.setFocusable(false);
        JPanel delclr = new JPanel();
        delclr.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        delclr.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        delclr.add(delButton);
        delclr.add(clrButton);
        add(delclr,BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isOk = true;
        String word = e.getActionCommand();
        if(word.equals("Delete")){
            if(num2 == null) {
                num1 = num1.substring(0,num1.length() - 1);
                display.setText(df.format(Double.parseDouble(num1)));
            }
            else {
                num2 = num2.substring(0,num2.length() - 1);
                display.setText(df.format(Double.parseDouble(num2)));

            }
        }else if(word.equals("√")) {
            if(num1 == null && num2 == null){
                FirstSquared = true;
            } else if(num2 == null && num1 != null) {
                if(currentop==null) {
                    if (Math.floor(Math.sqrt(Double.parseDouble(num1))) == Math.ceil(Math.sqrt(Double.parseDouble(num1)))) {
                        display.setText(Integer.toString((int) Math.round(Math.sqrt(Integer.parseInt(num1)))));
                    } else {
                        display.setText(Double.toString(Math.sqrt(Double.parseDouble(num1))));
                    }
                    num1 = Double.toString(Math.sqrt(Double.parseDouble(num1)));
                }
            } else if(num2 == "0" && num1 != null) {
                SecondSquared = true;
            }
            else if(num1 == null) {
                display.setText(word);
            }
        }
        else if(word.equals("%") || word.equals("+") || word.equals("-") || word.equals("*") || word.equals("÷") || word.equals("^")){
            currentop = word;
            display.setText(currentop);
            if(num1 != null && num2 == null){
                num2 = "0";
            }
        }else if(word.equals("Clear")){
            display.setText(null);
            currentop = null;
            num1 = num2 = null;
        }else if(word.equals("=")){
            if(num2 == null) {
                num2 = "0";
            }
            actualnum2 = Double.parseDouble(num2);
            actualnum1 = Double.parseDouble(num1);
            if(FirstSquared == true){
                actualnum1 = Math.sqrt(actualnum1);
            }
            if(SecondSquared == true){
                actualnum2 = Math.sqrt(actualnum2);
            }
            FirstSquared = false;
            SecondSquared = false;
            try {
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
                    }else if(currentop.equals("*")) {
                        intResult = (int) actualnum1 * (int) actualnum2;
                    }
                    String resultFormat = df.format(intResult);
                    display.setText(resultFormat);
                    num1 = Integer.toString(intResult);
                }
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
                            isOk = false;
                            throw new ArithmeticException();
                        }
                    } else if(currentop.equals("%")) {
                        doubleResult = actualnum1 % actualnum2;
                    }
                }

                num2 = null;
                if(isOk) {
                    String resultFormat = df.format(doubleResult);
                    display.setText(resultFormat);
                    num1 = Double.toString(doubleResult);
                }
            }
            catch (ArithmeticException ex) {
                display.setText("Undefined");
            }
            currentop = null;
        }else {
            if(num2 == null){
                if(num1 == null) {
                    if(word.equals(".")){
                        num1 = "0.";
                        display.setText(num1);
                    }
                    else if(word.equals("√")){
                        FirstSquared = true;
                    } else {
                        num1 = word;
                        display.setText(num1);
                    }
                }
                else {
                    if(word.equals(".") && num1.contains(".")){

                    }
                    else {
                        num1 += word;
                        if(!num1.endsWith(".")) {
                            display.setText(df.format(Double.parseDouble(num1)));
                        }
                        else {
                            num1 = num1.substring(0,num1.length() - 1);
                            display.setText(df.format(Double.parseDouble(num1)) + ".");
                            num1 += ".";
                        }
                    }
                }
            } else {
                if(word.equals(".")){
                    num2= "0.";
                    display.setText((df.format(Double.parseDouble(num2))));
                } else if(word.equals("√")){
                    SecondSquared = true;
                }
                else {
                    num2 += word;
                    if(!num2.endsWith(".")) {
                        display.setText(df.format(Double.parseDouble(num2)));
                    }
                    else {
                        num2 = num2.substring(0,num2.length() - 1);
                        display.setText(df.format(Double.parseDouble(num2)) + ".");
                        num2 += ".";
                    }
                }
            }
        }
    }
}
