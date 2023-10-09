package org.example;


import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

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

    static final List<ButtonFunctions> COMMAND_BUTTONS = List.of(new ButtonFunctions("clear", () -> {
        enteredText = "";
        updateDisplayLine();
    }), new ButtonFunctions("back", () -> {
        if (!enteredText.isEmpty()) {
            enteredText = enteredText.substring(0, enteredText.length() - 1);
        }
        updateDisplayLine();
    }));


    public static Node display() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();

        GridPane gridPane = new GridPane();

        int row = 0, column = 0;
        for (char c : symbols.ENGLISH_CHARS) {
            addSymbolButtonAtIndex(c, gridPane, (row % 10), column + (row / 10));
            row++;
        }
        column += (row / 10) + 1;
        row = 0;
        for (char c : symbols.NUMBER_CHARS) {
            addSymbolButtonAtIndex(c, gridPane, (row % 10), column + (row / 10));
            row++;
        }
        column += (row / 10) + 1;
        row = 0;
        for (NonStandardChar c : symbols.OTHER_CHARS) {
            addSymbolButtonAtIndex(c.charRep(), gridPane, (row % 10), column + (row / 10));
            row++;
        }
        column += (row / 10) + 1;
        row = 0;
        for (ButtonFunctions bf : COMMAND_BUTTONS) {
            addFunctionButton(bf, gridPane, (row % 10), column + (row / 10));
            row++;
        }


        vBox.getChildren().add(gridPane);

        enteredText = "Enter Text here";

        tf.setText(enteredText);
        tf.setMaxHeight(25);
        tf.setFont(new Font(25));
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
        Button button = new Button(String.valueOf(c).toUpperCase());
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
