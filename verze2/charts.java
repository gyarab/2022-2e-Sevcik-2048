package com.example.herewegoagain;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
public class charts{
    int[][] arr = new int[4][4];
    tools tool = new tools();
    int counter = 0;

    public charts(){

    }

    public void add(int X, int Y, int v){
        arr[X][Y] = v;
        counter++;
    }
    //TODO pred ni vzdy Pole.getChildren().clear()
    public void printo(Pane Pole){

        int k = 1;
        for (int g = 0; g < 4; g++) {
            for(int i = 0; i < 4; i++) {
                if (arr[g][i] != 0) {
                    Button b = new Button(String.valueOf(arr[g][i]));
                    b.setMinSize(76.25,76.25);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g));
                    k++;
                }
            }
        }
    }


}
