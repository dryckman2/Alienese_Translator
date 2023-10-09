package org.example;

import java.awt.*;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        for (char i = 'a'; i <= 'z'; i++) {
            JButton button = new JButton(AlieneseOne.getSymbol(i).getIcon());
            button.setText(String.valueOf(i));
            button.setVerticalTextPosition(AbstractButton.BOTTOM);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setSize(45,45);
            frame.add(button);
        }
        for (char i = '0'; i <= '9'; i++) {
            JButton button = new JButton(AlieneseOne.getSymbol(i).getIcon());
            button.setText(String.valueOf(i));
            button.setVerticalTextPosition(AbstractButton.BOTTOM);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setSize(45,45);
            frame.add(button);
        }
        frame.setLayout(new GridLayout(4, 10));
        frame.setVisible(true);
    }
}
