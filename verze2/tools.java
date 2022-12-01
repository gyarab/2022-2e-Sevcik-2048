package com.example.herewegoagain;

import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class tools {
    public double decode(int x){
        if (x == 0) {
            return 0;
        } else if (x == 1) {
            return 75;
        } else if (x == 2) {
            return 150;
        } else if (x == 3) {
            return 225;
        }
    return 0;

    }
    public void drawGridPane(Pane Pole){
        Path tah = new Path();
        MoveTo move1 = new MoveTo(75, 0);
        LineTo line1 = new LineTo(75, 300);
        MoveTo move2 = new MoveTo(150, 0);
        LineTo line2 = new LineTo(150, 300);
        MoveTo move3 = new MoveTo(225, 0);
        LineTo line3 = new LineTo(225, 300);
        MoveTo move4 = new MoveTo(0, 0);
        LineTo line4 = new LineTo(300, 0);
        MoveTo move5 = new MoveTo(0, 75);
        LineTo line5 = new LineTo(300, 75);
        MoveTo move6 = new MoveTo(0, 150);
        LineTo line6 = new LineTo(300, 150);
        MoveTo move7 = new MoveTo(0, 225);
        LineTo line7 = new LineTo(300, 225);
        tah.getElements().addAll(
                move1,
                line1,
                move2,
                line2,
                move3,
                line3,
                move4,
                line4,
                move5,
                line5,
                move6,
                line6,
                move7,
                line7
        );
        Pole.getChildren().add(tah);
    }
}
