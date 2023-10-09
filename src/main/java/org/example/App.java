package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alienese Translator");


        GridPane gridPane = new GridPane();
        //TODO: Make this its own file
        for (char i = 'a'; i <= 'z'; i++) {
            addSymbolButtonAtIndex(i, gridPane, (i - 'a') % 10, (i - 'a') / 10);
        }

        for (char i = '0'; i <= '9'; i++) {
            addSymbolButtonAtIndex(i, gridPane, (i - '0') % 10, 3);

        }

        primaryStage.setScene(new Scene(gridPane, 500, 500));
        primaryStage.show();
    }

    public void addSymbolButtonAtIndex(char c, GridPane gridPane, int x, int y) {
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