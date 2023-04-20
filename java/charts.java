package com.example.herewegoagain;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class charts{
    int[][] arr = new int[4][4];
    tools tool = new tools();

    public charts(){
    }

    public void add(int X, int Y, int v){
        arr[X][Y] = v;
    }

    public void remove(int X, int Y){
        arr[X][Y] = 0;
    }

    public void printo(Pane Pole){
        ImageView grid = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\2048-grid.png");
        grid.setFitWidth(300);
        grid.setFitHeight(300);
        Pole.getChildren().add(grid);


        int k = 1;
        for (int g = 0; g < 4; g++) {
            for(int i = 0; i < 4; i++) {
                if (arr[g][i] == 2) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\2.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 4) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\4.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 8) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\8.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 16) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\16.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 32) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\32.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 64) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\64.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 128) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\128.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 256) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\256.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 512) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\512.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 1024) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\1024.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 2048) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\2048.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 4096) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\4096.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                } else if (arr[g][i] == 8192) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\8192.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i,""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g,"x"));
                    k++;
                }
            }
        }
    }


}
