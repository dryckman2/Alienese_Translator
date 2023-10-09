package org.example;


import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlieneseOne {
    private static final Map<String, Image> symbolSet = new HashMap<>();

    public static final List<String> ENGLISH_SET = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    public static final List<String> NON_STANDARD_SET = List.of("space");

    static {
        //Initialize Code
        try {
            for (String c : ENGLISH_SET) {
                symbolSet.put(String.valueOf(c), new Image(new FileInputStream("data/AlieneseOne/" + c + ".png")));
            }
            for (String s : NON_STANDARD_SET) {
                symbolSet.put(s, new Image(new FileInputStream("data/AlieneseOne/" + s + ".png")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ImageView getSymbol(String i) {
        return getSymbol(i, 30);
    }

    public static ImageView getSymbol(String i, int size) {
        if (i.equals(" ")) {
            i = "space";
        }
        ImageView view = new ImageView(symbolSet.get(i));
        view.setFitHeight(size * 1.5);
        view.setFitWidth(size);
        return view;
    }

    private static String enteredText;

    private static final HBox displayLine = new HBox();
    private static final TextField tf = new TextField();


    public static Node display() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        int i = 0;
        for (String s : ENGLISH_SET) {
            addSymbolButtonAtIndex(s, gridPane, i % 10, i / 10);
            i++;
        }
        i = 0;
        for (String s : NON_STANDARD_SET) {
            addSymbolButtonAtIndex(s, gridPane, i % 10, 4);
            i++;
        }

        vBox.getChildren().add(gridPane);

        enteredText = "Enter Text here";
        updateDisplayLine();
        vBox.getChildren().add(displayLine);

        tf.setText(enteredText);
        tf.setOnAction((event) -> {
            TextField t = (TextField) event.getSource();
            enteredText = t.getText();
            updateDisplayLine();
        });
        vBox.getChildren().add(tf);

        stackPane.getChildren().add(vBox);
        return stackPane;
    }

    public static void updateDisplayLine() {
        displayLine.getChildren().clear();
        for (char c : enteredText.toLowerCase().toCharArray()) {
            displayLine.getChildren().add(getSymbol(String.valueOf(c), 20));
        }
        tf.setText(enteredText);
    }

    private static void addSymbolButtonAtIndex(String c, GridPane gridPane, int x, int y) {
        Button button = new Button(String.valueOf(c));
        button.setGraphic(AlieneseOne.getSymbol(c));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setOnAction((action) -> {
            Button s = (Button) action.getSource();
            if (s.getText().equals("space")) {
                enteredText += ' ';
            } else {
                enteredText += s.getText();
            }
            updateDisplayLine();
        });
        gridPane.add(button, x, y);
    }

}
