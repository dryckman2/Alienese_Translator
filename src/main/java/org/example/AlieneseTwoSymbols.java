package org.example;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AlieneseTwoSymbols extends SymbolSet {

    public AlieneseTwoSymbols() { // Initialize Code, runs on startup
        super();
        try {
            for (char c : ENGLISH_CHARS) {
                symbolSet.put(c, new Image(new FileInputStream("data/AlieneseTwo/alpha/" + c + ".png")));
            }
            // for (char c : NUMBER_CHARS) {
            // symbolSet.put(c, new Image(new FileInputStream("data/AlieneseTwo/num/" + c +
            // ".png")));
            // }
            // for (NonStandardChar nsc : OTHER_CHARS) {
            // symbolSet.put(nsc.charRep(), new Image(new
            // FileInputStream("data/AlieneseTwo/" + nsc.stringRep() + ".png")));
            // }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
