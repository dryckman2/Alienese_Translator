package org.example;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlieneseOne {
    private static final Map<Character, Image> symbolSet = new HashMap<>();

    public static final Set<Character> ENGLISH_SET = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    static {
        //Initialize Code
        try {
            for (char c : ENGLISH_SET) {
                symbolSet.put(c, new Image(new FileInputStream("data/AlieneseOne/" + c + ".png")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ImageView getSymbol(char i) {
        return getSymbol(i, 30);
    }

    public static ImageView getSymbol(char i, int size) {
        ImageView view = new ImageView(symbolSet.get(i));
        view.setFitHeight(size*1.5);
        view.setFitWidth(size);
        return view;
    }

}
