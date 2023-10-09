package org.example;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AlieneseOneSymbols extends SymbolSet {

    public AlieneseOneSymbols() { // Initialize Code, runs on startup
        super();
        try {
            for (char c : ENGLISH_CHARS) {
                symbolSet.put(c, new Image(new FileInputStream("data/AlieneseOne/alpha/" + c + ".png")));
            }
            for (char c : NUMBER_CHARS) {
                symbolSet.put(c, new Image(new FileInputStream("data/AlieneseOne/num/" + c + ".png")));
            }
            for (NonStandardChar nsc : OTHER_CHARS) {
                symbolSet.put(nsc.charRep(),
                        new Image(new FileInputStream("data/AlieneseOne/" + nsc.stringRep() + ".png")));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
