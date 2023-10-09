package org.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlieneseOne {
    private static final Map<Character, BufferedImage> symbolSet = new HashMap<>();

    public static final Set<Character> ENGLISH_SET = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    static {
        //Initialize Code
        try {
            for (char c : ENGLISH_SET) {
                symbolSet.put(c, ImageIO.read(new File("data/AlieneseOne/" + c + ".png")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static JLabel getSymbol(char i) {
        return getSymbol(i, 30);
    }

    public static JLabel getSymbol(char i, int size) {
        return new JLabel(new ImageIcon(symbolSet.get(i).getScaledInstance(size, (int) (size * 1.5), Image.SCALE_DEFAULT)));
    }

}
