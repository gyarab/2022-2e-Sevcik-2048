package com.example.herewegoagain;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("2048.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Controller controller = loader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case W:
                        try {
                            controller.Up();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case S:
                        try {
                            controller.Down();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case A:
                        try {
                            controller.Left();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case D:
                        try {
                            controller.Right();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
        stage.setScene(scene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\2048.jpg")));
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}