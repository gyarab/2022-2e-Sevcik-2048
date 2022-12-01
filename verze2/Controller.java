package com.example.herewegoagain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button Load;
    @FXML
    Button Save;
    @FXML
    Pane Pole;
    @FXML
    Label score;
    charts board = new charts();
    tools tool = new tools();
    Random r = new Random();


    public Controller() {}

    public void Random(){
        int x = r.nextInt(0,3);
        int y = r.nextInt(0,3);
        board.add(x,y,2);

        int x1 = r.nextInt(0,3);
        int y1 = r.nextInt(0,3);
        while(x1 == x && y1 == y){
            x1 = r.nextInt(0,3);
            y1 = r.nextInt(0,3);
        }
        board.add(x1,y1,2);

        board.printo(Pole);
    }



    public void Up(){
        board.printo(Pole);
    }

    public void Down(){

    }

    public void Left(){

    }

    public void Right(){

    }

    @FXML
    protected void Save(){

    }
    @FXML
    protected void Load(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tool.drawGridPane(Pole);
        Random();
    }
}