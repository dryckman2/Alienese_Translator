package org.example;


import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class AlieneseOne {

    private static final SymbolSet symbols = new AlieneseOneSymbols();
    private static String enteredText;
    private static final HBox displayLine = new HBox();
    private static final TextField tf = new TextField();


    public static void updateDisplayLine() {
        displayLine.getChildren().clear();
        for (char c : enteredText.toLowerCase().toCharArray()) {
            displayLine.getChildren().add(symbols.getSymbol(c));
        }
        tf.setText(enteredText);
    }

    static List<ButtonFunctions> COMMAND_BUTTONS = List.of(new ButtonFunctions("clear", () -> {
        enteredText = "";
        updateDisplayLine();
    }));


    public static Node display() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        //TODO: Change this to be less shit
        int i = 0;
        for (char c : symbols.ENGLISH_CHARS) {
            addSymbolButtonAtIndex(c, gridPane, i % 10, i / 10);
            i++;
        }
        i = 0;
        for (char c : symbols.NUMBER_CHARS) {
            addSymbolButtonAtIndex(c, gridPane, i % 10, 4);
            i++;
        }
        i = 0;
        for (NonStandardChar c : symbols.OTHER_CHARS) {
            addSymbolButtonAtIndex(c.charRep(), gridPane, i % 10, 5);
            i++;
        }
        i = 0;
        for (ButtonFunctions bf : COMMAND_BUTTONS) {
            addFunctionButton(bf, gridPane, i % 10, 6);
            i++;
        }


        vBox.getChildren().add(gridPane);

        enteredText = "Enter Text here";

        tf.setText(enteredText);
        tf.setOnAction((event) -> {
            TextField t = (TextField) event.getSource();
            enteredText = t.getText();
            updateDisplayLine();
        });
        vBox.getChildren().add(tf);

        updateDisplayLine();
        vBox.getChildren().add(displayLine);

        stackPane.getChildren().add(vBox);
        return stackPane;
    }


    private static void addSymbolButtonAtIndex(char c, GridPane gridPane, int x, int y) {
        Button button = new Button(String.valueOf(c));
        button.setMinSize(50, 50);
        button.setGraphic(symbols.getSymbol(c));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setOnAction((action) -> {
            Button s = (Button) action.getSource();
            enteredText += s.getText();
            updateDisplayLine();
        });
        gridPane.add(button, x, y);
    }

    private static void addFunctionButton(ButtonFunctions bf, GridPane gridPane, int x, int y) {
        Button button = new Button(bf.text());
        button.setMinSize(50, 50);
        button.setOnAction((action) -> {
            bf.function().run();
            updateDisplayLine();
        });
        gridPane.add(button, x, y);
    }


}
