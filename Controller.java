package com.example.herewegoagain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class Controller implements Initializable {
    @FXML
    Button Restart;
    @FXML
    Button Undo;
    @FXML
    Pane Pole;
    @FXML
    Label score;
    @FXML
    VBox vbox;
    int scorenumber = 0;
    charts board = new charts();
    Random r = new Random();
    int[][] temp = new int[4][4];
    int tempscore;
    File file = new File("pane.dat");
    File file1 = new File("score.dat");
    Stage stage;

    public Controller() {}

    public void Random(){
        int x = r.nextInt(0,4);
        int y = r.nextInt(0,4);
        board.add(x,y,2);

        int x1 = r.nextInt(0,4);
        int y1 = r.nextInt(0,4);
        while(x1 == x && y1 == y){
            x1 = r.nextInt(0,4);
            y1 = r.nextInt(0,4);
        }
        int r4 = r.nextInt(0, 6);
        if (r4 == 5){
            board.add(x1, y1, 4);
        } else {
            board.add(x1, y1, 2);
        }

        board.printo(Pole);
    }


    /**Nahoru*/
    public void Up() throws IOException {
        undosave();
        int moved = 0;

        for(int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[g][0] > 0){
            }
            /**druhy*/
            if (board.arr[g][1] > 0 && board.arr[g][0] == 0){
                board.add(g,0,board.arr[g][1]);
                board.remove(g,1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]){
                scorenumber = scorenumber + board.arr[g][1]*2;
                board.add(g,0,board.arr[g][1]*2);
                board.remove(g,1);
                moved++;
            }
            /**treti*/
            if (board.arr[g][2] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0){
                board.add(g,0,board.arr[g][2]);
                board.remove(g,2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] != board.arr[g][0] && board.arr[g][1] == 0){
                board.add(g,1,board.arr[g][2]);
                board.remove(g,2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] == board.arr[g][0] && board.arr[g][1] == 0){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,0,board.arr[g][2]*2);
                board.remove(g,2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == board.arr[g][1]){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,1,board.arr[g][2]*2);
                board.remove(g,2);
                moved++;
            }
            /**ctvrty*/
            if (board.arr[g][3] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0){
                board.add(g,0,board.arr[g][3]);
                board.remove(g,3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][0]){
                board.add(g,1,board.arr[g][3]);
                board.remove(g,3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][1]){
                board.add(g,2,board.arr[g][3]);
                board.remove(g,3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][0]){
                scorenumber = scorenumber + board.arr[g][3]*2;
                board.add(g,0,board.arr[g][3]*2);
                board.remove(g,3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][1]){
                scorenumber = scorenumber + board.arr[g][3]*2;
                board.add(g,1,board.arr[g][3]*2);
                board.remove(g,3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]){
                scorenumber = scorenumber + board.arr[g][3]*2;
                board.add(g,2,board.arr[g][3]*2);
                board.remove(g,3);
                moved++;
            }

        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if(moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }

    /**Dolu*/
    public void Down() throws IOException {
        undosave();
        int moved = 0;

        for(int g = 3; g >= 0; g--) {
            /**prvni*/
            if (board.arr[g][3] > 0) {

            }

            /**druhy*/
            if (board.arr[g][2] > 0 && board.arr[g][3] == 0){
                board.add(g,3,board.arr[g][2]);
                board.remove(g,2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,3,board.arr[g][2]*2);
                board.remove(g,2);
                moved++;
            }
            /**Treti*/
            if (board.arr[g][1] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0){
                board.add(g,3,board.arr[g][1]);
                board.remove(g,1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] != board.arr[g][3] && board.arr[g][2] == 0){
                board.add(g,2,board.arr[g][1]);
                board.remove(g,1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] == board.arr[g][3] && board.arr[g][2] == 0){
                scorenumber = scorenumber + board.arr[g][1]*2;
                board.add(g,3,board.arr[g][1]*2);
                board.remove(g,1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == board.arr[g][2]){
                scorenumber = scorenumber + board.arr[g][1]*2;
                board.add(g,2,board.arr[g][1]*2);
                board.remove(g,1);
                moved++;
            }
            /**Ctvrty*/
            if (board.arr[g][0] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0){
                board.add(g,3,board.arr[g][0]);
                board.remove(g,0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][3]){
                board.add(g,2,board.arr[g][0]);
                board.remove(g,0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][2]){
                board.add(g,1,board.arr[g][0]);
                board.remove(g,0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][3]){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,3,board.arr[g][0]*2);
                board.remove(g,0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][2]){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,2,board.arr[g][0]*2);
                board.remove(g,0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]){
                scorenumber = scorenumber + board.arr[g][2]*2;
                board.add(g,1,board.arr[g][0]*2);
                board.remove(g,0);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if(moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }

    /**Doleva*/
    public void Left() throws IOException {
        undosave();
        int moved = 0;

        for(int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[0][g] > 0){
            }
            /**druhy*/
            if (board.arr[1][g] > 0 && board.arr[0][g] == 0){
                board.add(0,g,board.arr[1][g]);
                board.remove(1,g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]){
                scorenumber = scorenumber + board.arr[1][g]*2;
                board.add(0,g,board.arr[1][g]*2);
                board.remove(1,g);
                moved++;
            }
            /**treti*/
            if (board.arr[2][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0){
                board.add(0,g,board.arr[2][g]);
                board.remove(2,g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] != board.arr[0][g] && board.arr[1][g] == 0){
                board.add(1,g,board.arr[2][g]);
                board.remove(2,g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] == board.arr[0][g] && board.arr[1][g] == 0){
                scorenumber = scorenumber + board.arr[2][g]*2;
                board.add(0,g,board.arr[2][g]*2);
                board.remove(2,g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == board.arr[1][g]){
                scorenumber = scorenumber + board.arr[2][g]*2;
                board.add(1,g,board.arr[2][g]*2);
                board.remove(2,g);
                moved++;
            }
            /**ctvrty*/
            if (board.arr[3][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0){
                board.add(0,g,board.arr[3][g]);
                board.remove(3,g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[0][g]){
                board.add(1,g,board.arr[3][g]);
                board.remove(3,g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[1][g]){
                board.add(2,g,board.arr[3][g]);
                board.remove(3,g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[0][g]){
                scorenumber = scorenumber + board.arr[3][g]*2;
                board.add(0,g,board.arr[3][g]*2);
                board.remove(3,g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[1][g]){
                scorenumber = scorenumber + board.arr[3][g]*2;
                board.add(1,g,board.arr[3][g]*2);
                board.remove(3,g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]){
                scorenumber = scorenumber + board.arr[3][g]*2;
                board.add(2,g,board.arr[3][g]*2);
                board.remove(3,g);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if(moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }
    /**Doprava*/
    public void Right() throws IOException {
        undosave();
        int moved = 0;

        for(int g = 3; g >= 0; g--) {
            /**prvni*/

            /**druhy*/
            if (board.arr[2][g] > 0 && board.arr[3][g] == 0){
                board.add(3,g,board.arr[2][g]);
                board.remove(2,g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]){
                scorenumber = scorenumber + board.arr[2][g]*2;
                board.add(3,g,board.arr[2][g]*2);
                board.remove(2,g);
                moved++;
            }
            /**Treti*/
            if (board.arr[1][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0){
                board.add(3,g,board.arr[1][g]);
                board.remove(1,g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] != board.arr[3][g] && board.arr[2][g] == 0){
                board.add(2,g,board.arr[1][g]);
                board.remove(1,g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] == board.arr[3][g] && board.arr[2][g] == 0){
                scorenumber = scorenumber + board.arr[1][g]*2;
                board.add(3,g,board.arr[1][g]*2);
                board.remove(1,g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == board.arr[2][g]){
                scorenumber = scorenumber + board.arr[1][g]*2;
                board.add(2,g,board.arr[1][g]*2);
                board.remove(1,g);
                moved++;
            }
            /**Ctvrty*/
            if (board.arr[0][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0){
                board.add(3,g,board.arr[0][g]);
                board.remove(0,g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[3][g]){
                board.add(2,g,board.arr[0][g]);
                board.remove(0,g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[2][g]){
                board.add(1,g,board.arr[0][g]);
                board.remove(0,g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[3][g]){
                scorenumber = scorenumber + board.arr[0][g]*2;
                board.add(3,g,board.arr[0][g]*2);
                board.remove(0,g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[2][g]){
                scorenumber = scorenumber + board.arr[0][g]*2;
                board.add(2,g,board.arr[0][g]*2);
                board.remove(0,g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]){
                scorenumber = scorenumber + board.arr[0][g]*2;
                board.add(1,g,board.arr[0][g]*2);
                board.remove(0,g);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if(moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }

    public void spawn(){

        int x = 0;
        int y = 0;
        boolean yes = true;

        while(yes){
            x = r.nextInt(0,4);
            y = r.nextInt(0,4);
            for(int c = 0; c < 4; c++){
                for(int k = 0; k < 4; k++){
                    if (board.arr[x][y] == 0){
                        yes = false;
                        break;
                    }
                }
            }
        }
        int o = r.nextInt(0,5);
        if (o == 3){
            board.add(x,y,4);
        } else {
            board.add(x,y,2);
        }
        Pole.getChildren().clear();
        board.printo(Pole);

        for (int u = 0; u < 4; u++){//test print
            for (int k = 0; k < 4; k++){
                System.out.print(board.arr[k][u]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public void autosave() throws IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(board.arr);

        FileOutputStream k = new FileOutputStream(file1);
        ObjectOutputStream out1 = new ObjectOutputStream(k);
        out1.writeObject(scorenumber);
    }

    @FXML
    protected void Restart() throws IOException {
        for (int u = 0; u < 4; u++){
            for (int k = 0; k < 4; k++){
                board.remove(u,k);
            }
        }
        Pole.getChildren().clear();
        Random();
        score.setText("0");
        scorenumber = 0;
        autosave();
    }

    @FXML
    protected void Undo() throws IOException {
        for (int j = 0; j < 4; j++){
            for (int v = 0; v < 4; v++){
                board.arr[j][v] = 0;
            }
        }
        for (int j = 0; j < 4; j++){
            System.arraycopy(temp[j], 0, board.arr[j], 0, 4);
        }
        scorenumber = tempscore;
        score.setText(String.valueOf(scorenumber));

        Pole.getChildren().clear();
        board.printo(Pole);
        autosave();
    }

    public void undosave(){
        tempscore = scorenumber;
        for (int j = 0; j < 4; j++){
            System.arraycopy(board.arr[j], 0, temp[j], 0, 4);
        }
    }

    public void checkLost() throws IOException {
        if (checkUp() && checkRight() && checkLeft() && checkDown()){
            System.out.println("lost");
            lost();
        }
    }

    private void lost() throws IOException {
        Restart();
        file.delete();
        stage = (Stage) vbox.getScene().getWindow();
        stage.close();
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(requireNonNull(getClass().getResource("Lost.fxml"))));
        stage.setTitle("Lost");
        stage.setScene(scene);
        stage.show();
    }

    public void checkWin() throws IOException {
        for (int i = 0; i < 4; i++){
            for (int h = 0; h < 4; h++){
                if (board.arr[i][h] > 8192){
                    win();
                }
            }
        }
    }

    public void win() throws IOException {
        Restart();
        file.delete();
        System.out.println("win");
        stage = (Stage) vbox.getScene().getWindow();
        stage.close();
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(requireNonNull(getClass().getResource("Win.fxml"))));
        stage.setTitle("Won");
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkRight(){
        for(int g = 3; g >= 0; g--) {

            if (board.arr[2][g] > 0 && board.arr[3][g] == 0){
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]){
                return false;
            }

            if (board.arr[1][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0){
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] != board.arr[3][g] && board.arr[2][g] == 0){
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] == board.arr[3][g] && board.arr[2][g] == 0){
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == board.arr[2][g]){
                return false;
            }

            if (board.arr[0][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0){
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[3][g]){
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[2][g]){
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[3][g]){
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[2][g]){
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]){
                return false;
            }
        }
        return true;
    }
    public boolean checkLeft(){
        for(int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[0][g] > 0){
            }
            /**druhy*/
            if (board.arr[1][g] > 0 && board.arr[0][g] == 0){
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]){
                return false;
            }
            /**treti*/
            if (board.arr[2][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0){
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] != board.arr[0][g] && board.arr[1][g] == 0){
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] == board.arr[0][g] && board.arr[1][g] == 0){
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == board.arr[1][g]){
                return false;
            }
            /**ctvrty*/
            if (board.arr[3][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0){
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[0][g]){
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[1][g]){
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[0][g]){
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[1][g]){
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]){
                return false;
            }
        }
        return true;
    }
    public boolean checkDown(){
        for(int g = 3; g >= 0; g--) {
            /**prvni*/
            if (board.arr[g][3] > 0) {

            }
            /**druhy*/
            if (board.arr[g][2] > 0 && board.arr[g][3] == 0){
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]){
                return false;
            }
            /**Treti*/
            if (board.arr[g][1] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0){
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] != board.arr[g][3] && board.arr[g][2] == 0){
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] == board.arr[g][3] && board.arr[g][2] == 0){
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == board.arr[g][2]){
                return false;
            }
            /**Ctvrty*/
            if (board.arr[g][0] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0){
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][3]){
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][2]){
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][3]){
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][2]){
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]){
                return false;
            }
        }
        return true;
    }
    public boolean checkUp(){
        for(int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[g][0] > 0){
            }
            /**druhy*/
            if (board.arr[g][1] > 0 && board.arr[g][0] == 0){
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]){
                return false;
            }
            /**treti*/
            if (board.arr[g][2] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0){
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] != board.arr[g][0] && board.arr[g][1] == 0){
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] == board.arr[g][0] && board.arr[g][1] == 0){
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == board.arr[g][1]){
                return false;
            }
            /**ctvrty*/
            if (board.arr[g][3] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0){
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][0]){
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][1]){
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][0]){
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][1]){
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]){
                return false;
            }
        }
        return true;
    }

    public void fileload() throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(f);
        int[][] arr = (int[][]) in.readObject();

        FileInputStream c = new FileInputStream(file1);
        ObjectInputStream in1 = new ObjectInputStream(c);
        int s = (Integer) in1.readObject();
        score.setText(String.valueOf(s));

        scorenumber = s;

        for (int h = 0; h < 4; h++){
            for (int k = 0; k < 4; k++){
                board.arr[h][k] = arr[h][k];
            }
        }
        board.printo(Pole);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ImageView img = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\undo.png");
        img.setFitWidth(50);
        img.setFitHeight(50);
        Undo.setGraphic(img);

        ImageView img1 = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\restart.png");
        img1.setFitWidth(45);
        img1.setFitHeight(45);
        Restart.setGraphic(img1);

        if (file.exists()) {
            try {
                fileload();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Random();
        }
    }
}