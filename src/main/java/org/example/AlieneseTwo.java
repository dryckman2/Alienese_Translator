package org.example;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

/*
Mathematics for translation of AlienseTwo
new symbol = (symbol # - previous symbol #) mod 26
e.g., first letter = 2 = c
second letter = 16 so to find new symbol -> 16-2=14 -> O
third letter = 19 so to find new symbol -> 19-16=3 -> D

encoding
new symbol = (symbol # + previous symbol #) mod 26
 */
public class AlieneseTwo {

    private static final SymbolSet symbols = new AlieneseTwoSymbols();
    private static String enteredText;
    private static final HBox displayLine = new HBox();
    private static final TextField tf = new TextField();

    public static void updateDisplayLine_encoding() {
        displayLine.getChildren().clear();
        int count = 0;
        char previousChar = 0;
        for (char c : enteredText.toLowerCase().toCharArray()) {
            if (count == 0) {
                previousChar = (char) (c - 'a');
                displayLine.getChildren().add(symbols.getSymbol(c));
            } else {
                if (c == ' ') {
                    displayLine.getChildren().add(symbols.getSymbol(c));
                } else {
                    char x = (char) (c - 'a');
                    char newSymbol = (char) (((x + previousChar) % 26) + 'a');
                    displayLine.getChildren().add(symbols.getSymbol(newSymbol));
                    previousChar = (char) ((x + previousChar) % 26);
                }
            }
            count++;
            // new symbol = (symbol # + previous symbol #) mod 26
        }
        tf.setText(enteredText);
    }


    public static void updateDisplayLine_decoder(String adding) {
        displayLine.getChildren().clear();
        int count = 0;
        char previousChar = 0;
        for (char c : enteredText.toLowerCase().toCharArray()) {
            if (count == 0) {
                previousChar = (char) (c - 'a');
                displayLine.getChildren().add(symbols.getSymbol(c));
            } else {
                if (c == ' ') {
                    displayLine.getChildren().add(symbols.getSymbol(c));
                    previousChar = 0;
                } else {
                    char x = (char) (c - 'a');
                    char newSymbol = (char) (((x + previousChar) % 26) + 'a');
                    displayLine.getChildren().add(symbols.getSymbol(newSymbol));
                    previousChar = (char) ((x + previousChar) % 26);
                }
            }
            count++;
            // new symbol = (symbol # + previous symbol #) mod 26
        }
        displayLine.getChildren().add(symbols.getSymbol(adding.toLowerCase().charAt(0)));
        if (enteredText.isEmpty()) {
            enteredText = adding;
        } else {
            if (adding.toLowerCase().charAt(0) == ' ') {
                enteredText += " ";
            } else {
                System.out.println((int) previousChar);
                int n = ((adding.toLowerCase().charAt(0) - 'a') - previousChar);
                if (n < 0) {
                    n += 26;
                }
                enteredText += (char) (n + 'a');
            }

        }
        tf.setText(enteredText);
    }

    static List<ButtonFunctions> COMMAND_BUTTONS = List.of(new ButtonFunctions("clear", () -> {
        enteredText = "";
        updateDisplayLine_encoding();
    }), new ButtonFunctions("back", () -> {
        if (!enteredText.isEmpty()) {
            enteredText = enteredText.substring(0, enteredText.length() - 1);
        }
        updateDisplayLine_encoding();
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
        addSymbolButtonAtIndex(' ', gridPane, (row % 10), column + (row / 10));
        row++;
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
            updateDisplayLine_encoding();
        });
        vBox.getChildren().add(tf);

        updateDisplayLine_encoding();
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
//            enteredText += s.getText();
            updateDisplayLine_decoder(s.getText());
        });
        gridPane.add(button, x, y);
    }

    private static void addFunctionButton(ButtonFunctions bf, GridPane gridPane, int x, int y) {
        Button button = new Button(bf.text());
        button.setMinSize(50, 50);
        button.setOnAction((action) -> {
            bf.function().run();
            updateDisplayLine_encoding();
        });
        gridPane.add(button, x, y);
    }


}
