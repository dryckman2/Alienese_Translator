package org.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SymbolSet {
    List<Character> ENGLISH_CHARS = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    List<Character> NUMBER_CHARS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    List<NonStandardChar> OTHER_CHARS = List.of(new NonStandardChar("space", ' '),new NonStandardChar("colon", ':'));


    Map<Character, Image> symbolSet = new HashMap<>();


    public ImageView getSymbol(char c, int size) {
        ImageView view = new ImageView(symbolSet.get(c));
        view.setFitHeight(size * 1.5);
        view.setFitWidth(size);
        return view;

    }

    public ImageView getSymbol(char c) {
        return getSymbol(c, 30);
    }


}
