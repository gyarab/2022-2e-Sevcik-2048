package com.example.herewegoagain;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class charts {
    int[][] arr = new int[4][4];
    tools tool = new tools();

    public charts() {
    }

    /**
     * @param X
     * @param Y
     * @param v
     * Tato metoda vezme hodnoty X, Y a v.
     * Následovně přidá na souřadnice X a Y v poli arr[][] hodnotu Integeru v.
     */
    public void add(int X, int Y, int v) {
        arr[X][Y] = v;
    }

    /**
     * @param X
     * @param Y
     * Tato metoda vezme hodnoty X a Y.
     * Následovně přidá na souřadnice X a Y v poli arr[][] hodnotu 0.
     */
    public void remove(int X, int Y) {
        arr[X][Y] = 0;
    }

    /**
     * @param Pole
     * Nejprve metoda vytvoří ImageView grid.
     * Do konstruktoru třídy ImageView dá metoda obrázek "2048-grid.png".
     * Následně je obrázku nastavena výška 300 a šířka 300.
     * Poté je obrázek přidán na Pane Pole.
     * Následovně metoda vytvoří Integer k a do něj nastaví hodnotu 1.
     * Dále jsou spuštěny dva for cykly.
     * V druhém cyklu je několik podmínek a každá kontroluje zda v dvourozměrném poli arr[][] je hodnota:
     * 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096 nebo 8192.
     * Podle toho jaké najde číslo přidá do nové instance b třídy ImageView obrázek.
     * Obrázku je následovně nastavena výška 65 s šířka 65.
     * ImageView b je poté přidán na Pane Pole.
     * Tomuto ImageView jsou následovně nastaveny souřadnice X a Y.
     * Souřadnice jsou nastaveny pomocí instance tool třídy tools, která zavolá metodu decode().
     * Nakonec je k Integeru k přičteno 1.
     */
    public void printo(Pane Pole) {
        ImageView grid = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\2048-grid.png");
        grid.setFitWidth(300);
        grid.setFitHeight(300);
        Pole.getChildren().add(grid);


        int k = 1;
        for (int g = 0; g < 4; g++) {
            for (int i = 0; i < 4; i++) {
                if (arr[g][i] == 2) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\2.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 4) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\4.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 8) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\8.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 16) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\16.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 32) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\32.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 64) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\64.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 128) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\128.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 256) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\256.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 512) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\512.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 1024) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\1024.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 2048) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\2048.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 4096) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\4096.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                } else if (arr[g][i] == 8192) {
                    ImageView b = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\pieces\\8192.jpg");
                    b.setFitHeight(65);
                    b.setFitWidth(65);
                    Pole.getChildren().add(b);
                    Pole.getChildren().get(k).setLayoutY(tool.decode(i, ""));
                    Pole.getChildren().get(k).setLayoutX(tool.decode(g, "x"));
                    k++;
                }
            }
        }
    }


}
