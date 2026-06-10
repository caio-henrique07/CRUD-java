package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Carrega as fontes antes de abrir a tela

        Font.loadFont(getClass().getResourceAsStream("/fonts/big_noodle_titling.ttf"), 16);
        Font.loadFont(getClass().getResourceAsStream("/fonts/big_noodle_titling_oblique.ttf"), 16);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load(), 936, 561);

        stage.setTitle("Gerenciador de Heróis - Overwatch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}