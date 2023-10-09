package org.example;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
        view.setFitHeight(size * 1.5);
        view.setFitWidth(size);
        return view;
    }

    public static Node display() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        for (char i = 'a'; i <= 'z'; i++) {
            addSymbolButtonAtIndex(i, gridPane, (i - 'a') % 10, (i - 'a') / 10);
        }
        for (char i = '0'; i <= '9'; i++) {
            addSymbolButtonAtIndex(i, gridPane, (i - '0') % 10, 3);
        }

        vBox.getChildren().add(gridPane);

        vBox.getChildren().add(new Label("this is a test"));

        stackPane.getChildren().add(vBox);
        return stackPane;
    }

    private static void addSymbolButtonAtIndex(char c, GridPane gridPane, int x, int y) {
        Button button = new Button(String.valueOf(c));
        button.setGraphic(AlieneseOne.getSymbol(c));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setOnAction((action) -> {
            Button s = (Button) action.getSource();
            System.out.println(s.getText());
        });
        gridPane.add(button, x, y);
    }

}
