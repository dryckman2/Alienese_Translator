package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alienese Translator");


        GridPane gridPane = new GridPane();

        for (char i = 'a'; i <= 'z'; i++) {
            Button button = new Button();
            button.setGraphic(AlieneseOne.getSymbol(i));
            button.setText(i + "\n");
            button.setTextAlignment(TextAlignment.CENTER);
            gridPane.add(button, (i - 'a') % 10, (i - 'a') / 10);
        }

        for (char i = '0'; i<='9';i++){
            Button button = new Button();
            button.setGraphic(AlieneseOne.getSymbol(i));
            button.setText(i + "\n");
            button.setTextAlignment(TextAlignment.CENTER);
            gridPane.add(button, (i - '0') % 10, 3);
        }

        primaryStage.setScene(new Scene(gridPane, 500,  500));
        primaryStage.show();
    }
}