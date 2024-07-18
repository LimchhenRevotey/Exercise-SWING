package com.Rupp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends JFrame implements ActionListener {
    private JTextField num1Field, num2Field, outputField;
    private JButton addBtn, subBtn, mulBtn, divBtn, modBtn, clrBtn;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        JLabel num1Label = new JLabel("Enter First Number:");
        JLabel num2Label = new JLabel("Enter Second Number:");
        JLabel resultLabel = new JLabel("Result:");

        num1Field = new JTextField();
        num2Field = new JTextField();
        outputField = new JTextField();
        outputField.setEditable(false);

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        modBtn = new JButton("%");
        clrBtn = new JButton("Clear");

        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        divBtn.addActionListener(this);
        modBtn.addActionListener(this);
        clrBtn.addActionListener(this);

        add(num1Label);
        add(num1Field);
        add(num2Label);
        add(num2Field);
        add(resultLabel);
        add(outputField);
        add(addBtn);
        add(subBtn);
        add(mulBtn);
        add(divBtn);
        add(modBtn);
        add(clrBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double number1 = Double.parseDouble(num1Field.getText());
            double number2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            if (e.getSource() == addBtn) {
                result = number1 + number2;
            } else if (e.getSource() == subBtn) {
                result = number1 - number2;
            } else if (e.getSource() == mulBtn) {
                result = number1 * number2;
            } else if (e.getSource() == divBtn) {
                result = number1 / number2;
            } else if (e.getSource() == modBtn) {
                result = number1 % number2;
            } else if (e.getSource() == clrBtn) {
                num1Field.setText("");
                num2Field.setText("");
                outputField.setText("");
                return;
            }

            outputField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}

