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

    public Controller() {
    }

    /**
     * Náhodné číslo z intervalu 0 až 4 je přidané do Integeru x a y.
     * Dále je zavolána metoda add pomocí instance board třídy charts s parametry x, y a 2.
     * Do hodnot Integeru x1, y1 jsou přidána čísla z intervalu 0 až 4 pomocí třídy Random.
     * Následovně je spuštěn while cyklus, který běží dokud mají x1 a x a y1 a y stejnou hodnotu.
     * Ve while cyklu jsou do Integru x1 a y1 přidávány hodnoty z intervalu 0 až 4.
     * Metoda dále přidá do Integeru r4 hodnotu z intervalu 0 až 6 pomocí instance třídy Random.
     * Podmínka následně zkontroluje zda hodnota r4 je 5.
     * Pokud ano tak metoda zavolá přes instanci board třídy charts metodu add() s parametry x1, y1 a 4.
     * Pokud ne zavolá metoda přes instanci board třídy charts metodu add() s parametry x1, y1 a 2.
     * Nakonec metoda zavolá přes instanci board třídy charts metodu printo().
     */
    public void Random() {
        int x = r.nextInt(0, 4);
        int y = r.nextInt(0, 4);
        board.add(x, y, 2);

        int x1 = r.nextInt(0, 4);
        int y1 = r.nextInt(0, 4);
        while (x1 == x && y1 == y) {
            x1 = r.nextInt(0, 4);
            y1 = r.nextInt(0, 4);
        }
        int r4 = r.nextInt(0, 6);
        if (r4 == 5) {
            board.add(x1, y1, 4);
        } else {
            board.add(x1, y1, 2);
        }

        board.printo(Pole);
    }

    /**
     * @throws IOException
     * Metoda nejprve zavolá metodu undosave() bez parametru.
     * Následovně vytvoří Integer moved a nastaví ho na nula.
     * Dále je zopakováno čtyřikrát několik podmínek.
     * Pokud některá z podmínek projde je přes instanci board třídy charts zavolána metoda add() a remove().
     * Také je k Integeru moved přičteno 1.
     * Po čtyřech opakování je zavolána metoda checkWin().
     * Následovně je vymazán celý Pane.
     * Dále je zavolána metoda printo() s parametrem Pole pomocé instance board třídy charts.
     * Přes podmínku je následně skontrolována hodnota Integeru moved a pokud je větší než nula je zavolána metoda spawn().
     * Nakonec jsou zavolány metody checkLost() a autosave() obě bez parametru.
     */
    public void Up() throws IOException {
        undosave();
        int moved = 0;

        for (int g = 0; g < 4; g++) {
            /**druhy*/
            if (board.arr[g][1] > 0 && board.arr[g][0] == 0) {
                board.add(g, 0, board.arr[g][1]);
                board.remove(g, 1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]) {
                scorenumber = scorenumber + board.arr[g][1] * 2;
                board.add(g, 0, board.arr[g][1] * 2);
                board.remove(g, 1);
                moved++;
            }
            /**treti*/
            if (board.arr[g][2] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0) {
                board.add(g, 0, board.arr[g][2]);
                board.remove(g, 2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] != board.arr[g][0] && board.arr[g][1] == 0) {
                board.add(g, 1, board.arr[g][2]);
                board.remove(g, 2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] == board.arr[g][0] && board.arr[g][1] == 0) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 0, board.arr[g][2] * 2);
                board.remove(g, 2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == board.arr[g][1]) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 1, board.arr[g][2] * 2);
                board.remove(g, 2);
                moved++;
            }
            /**ctvrty*/
            if (board.arr[g][3] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0) {
                board.add(g, 0, board.arr[g][3]);
                board.remove(g, 3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][0]) {
                board.add(g, 1, board.arr[g][3]);
                board.remove(g, 3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][1]) {
                board.add(g, 2, board.arr[g][3]);
                board.remove(g, 3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][0]) {
                scorenumber = scorenumber + board.arr[g][3] * 2;
                board.add(g, 0, board.arr[g][3] * 2);
                board.remove(g, 3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][1]) {
                scorenumber = scorenumber + board.arr[g][3] * 2;
                board.add(g, 1, board.arr[g][3] * 2);
                board.remove(g, 3);
                moved++;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]) {
                scorenumber = scorenumber + board.arr[g][3] * 2;
                board.add(g, 2, board.arr[g][3] * 2);
                board.remove(g, 3);
                moved++;
            }

        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if (moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }


    /**
     * @throws IOException
     * Metoda nejprve zavolá metodu undosave() bez parametru.
     * Následovně vytvoří Integer moved a nastaví ho na nula.
     * Dále je zopakováno čtyřikrát několik podmínek.
     * Pokud některá z podmínek projde je přes instanci board třídy charts zavolána metoda add() a remove().
     * Také je k Integeru moved přičteno 1.
     * Po čtyřech opakování je zavolána metoda checkWin().
     * Následovně je vymazán celý Pane.
     * Dále je zavolána metoda printo() s parametrem Pole pomocé instance board třídy charts.
     * Přes podmínku je následně skontrolována hodnota Integeru moved a pokud je větší než nula je zavolána metoda spawn().
     * Nakonec jsou zavolány metody checkLost() a autosave() obě bez parametru.
     */
    public void Down() throws IOException {
        undosave();
        int moved = 0;

        for (int g = 3; g >= 0; g--) {
            /**druhy*/
            if (board.arr[g][2] > 0 && board.arr[g][3] == 0) {
                board.add(g, 3, board.arr[g][2]);
                board.remove(g, 2);
                moved++;
            } else if (board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 3, board.arr[g][2] * 2);
                board.remove(g, 2);
                moved++;
            }
            /**Treti*/
            if (board.arr[g][1] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0) {
                board.add(g, 3, board.arr[g][1]);
                board.remove(g, 1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] != board.arr[g][3] && board.arr[g][2] == 0) {
                board.add(g, 2, board.arr[g][1]);
                board.remove(g, 1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] == board.arr[g][3] && board.arr[g][2] == 0) {
                scorenumber = scorenumber + board.arr[g][1] * 2;
                board.add(g, 3, board.arr[g][1] * 2);
                board.remove(g, 1);
                moved++;
            } else if (board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == board.arr[g][2]) {
                scorenumber = scorenumber + board.arr[g][1] * 2;
                board.add(g, 2, board.arr[g][1] * 2);
                board.remove(g, 1);
                moved++;
            }
            /**Ctvrty*/
            if (board.arr[g][0] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0) {
                board.add(g, 3, board.arr[g][0]);
                board.remove(g, 0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][3]) {
                board.add(g, 2, board.arr[g][0]);
                board.remove(g, 0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][2]) {
                board.add(g, 1, board.arr[g][0]);
                board.remove(g, 0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][3]) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 3, board.arr[g][0] * 2);
                board.remove(g, 0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][2]) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 2, board.arr[g][0] * 2);
                board.remove(g, 0);
                moved++;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]) {
                scorenumber = scorenumber + board.arr[g][2] * 2;
                board.add(g, 1, board.arr[g][0] * 2);
                board.remove(g, 0);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if (moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }


    /**
     * @throws IOException
     * Metoda nejprve zavolá metodu undosave() bez parametru.
     * Následovně vytvoří Integer moved a nastaví ho na nula.
     * Dále je zopakováno čtyřikrát několik podmínek.
     * Pokud některá z podmínek projde je přes instanci board třídy charts zavolána metoda add() a remove().
     * Také je k Integeru moved přičteno 1.
     * Po čtyřech opakování je zavolána metoda checkWin().
     * Následovně je vymazán celý Pane.
     * Dále je zavolána metoda printo() s parametrem Pole pomocé instance board třídy charts.
     * Přes podmínku je následně skontrolována hodnota Integeru moved a pokud je větší než nula je zavolána metoda spawn().
     * Nakonec jsou zavolány metody checkLost() a autosave() obě bez parametru.
     */
    public void Left() throws IOException {
        undosave();
        int moved = 0;

        for (int g = 0; g < 4; g++) {
            /**druhy*/
            if (board.arr[1][g] > 0 && board.arr[0][g] == 0) {
                board.add(0, g, board.arr[1][g]);
                board.remove(1, g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]) {
                scorenumber = scorenumber + board.arr[1][g] * 2;
                board.add(0, g, board.arr[1][g] * 2);
                board.remove(1, g);
                moved++;
            }
            /**treti*/
            if (board.arr[2][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0) {
                board.add(0, g, board.arr[2][g]);
                board.remove(2, g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] != board.arr[0][g] && board.arr[1][g] == 0) {
                board.add(1, g, board.arr[2][g]);
                board.remove(2, g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] == board.arr[0][g] && board.arr[1][g] == 0) {
                scorenumber = scorenumber + board.arr[2][g] * 2;
                board.add(0, g, board.arr[2][g] * 2);
                board.remove(2, g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == board.arr[1][g]) {
                scorenumber = scorenumber + board.arr[2][g] * 2;
                board.add(1, g, board.arr[2][g] * 2);
                board.remove(2, g);
                moved++;
            }
            /**ctvrty*/
            if (board.arr[3][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0) {
                board.add(0, g, board.arr[3][g]);
                board.remove(3, g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[0][g]) {
                board.add(1, g, board.arr[3][g]);
                board.remove(3, g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[1][g]) {
                board.add(2, g, board.arr[3][g]);
                board.remove(3, g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[0][g]) {
                scorenumber = scorenumber + board.arr[3][g] * 2;
                board.add(0, g, board.arr[3][g] * 2);
                board.remove(3, g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[1][g]) {
                scorenumber = scorenumber + board.arr[3][g] * 2;
                board.add(1, g, board.arr[3][g] * 2);
                board.remove(3, g);
                moved++;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]) {
                scorenumber = scorenumber + board.arr[3][g] * 2;
                board.add(2, g, board.arr[3][g] * 2);
                board.remove(3, g);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if (moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }

    /**
     * @throws IOException
     * Metoda nejprve zavolá metodu undosave() bez parametru.
     * Následovně vytvoří Integer moved a nastaví ho na nula.
     * Dále je zopakováno čtyřikrát několik podmínek.
     * Pokud některá z podmínek projde je přes instanci board třídy charts zavolána metoda add() a remove().
     * Také je k Integeru moved přičteno 1.
     * Po čtyřech opakování je zavolána metoda checkWin().
     * Následovně je vymazán celý Pane.
     * Dále je zavolána metoda printo() s parametrem Pole pomocé instance board třídy charts.
     * Přes podmínku je následně skontrolována hodnota Integeru moved a pokud je větší než nula je zavolána metoda spawn().
     * Nakonec jsou zavolány metody checkLost() a autosave() obě bez parametru.
     */
    public void Right() throws IOException {
        undosave();
        int moved = 0;

        for (int g = 3; g >= 0; g--) {
            /**druhy*/
            if (board.arr[2][g] > 0 && board.arr[3][g] == 0) {
                board.add(3, g, board.arr[2][g]);
                board.remove(2, g);
                moved++;
            } else if (board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]) {
                scorenumber = scorenumber + board.arr[2][g] * 2;
                board.add(3, g, board.arr[2][g] * 2);
                board.remove(2, g);
                moved++;
            }
            /**Treti*/
            if (board.arr[1][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0) {
                board.add(3, g, board.arr[1][g]);
                board.remove(1, g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] != board.arr[3][g] && board.arr[2][g] == 0) {
                board.add(2, g, board.arr[1][g]);
                board.remove(1, g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] == board.arr[3][g] && board.arr[2][g] == 0) {
                scorenumber = scorenumber + board.arr[1][g] * 2;
                board.add(3, g, board.arr[1][g] * 2);
                board.remove(1, g);
                moved++;
            } else if (board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == board.arr[2][g]) {
                scorenumber = scorenumber + board.arr[1][g] * 2;
                board.add(2, g, board.arr[1][g] * 2);
                board.remove(1, g);
                moved++;
            }
            /**Ctvrty*/
            if (board.arr[0][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0) {
                board.add(3, g, board.arr[0][g]);
                board.remove(0, g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[3][g]) {
                board.add(2, g, board.arr[0][g]);
                board.remove(0, g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[2][g]) {
                board.add(1, g, board.arr[0][g]);
                board.remove(0, g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[3][g]) {
                scorenumber = scorenumber + board.arr[0][g] * 2;
                board.add(3, g, board.arr[0][g] * 2);
                board.remove(0, g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[2][g]) {
                scorenumber = scorenumber + board.arr[0][g] * 2;
                board.add(2, g, board.arr[0][g] * 2);
                board.remove(0, g);
                moved++;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]) {
                scorenumber = scorenumber + board.arr[0][g] * 2;
                board.add(1, g, board.arr[0][g] * 2);
                board.remove(0, g);
                moved++;
            }
        }
        checkWin();
        Pole.getChildren().clear();
        board.printo(Pole);
        score.setText(String.valueOf(scorenumber));
        if (moved > 0) {
            spawn();
        }
        autosave();
        checkLost();
    }

    /**
     * Metoda nejprve vytvoří Integery x a y a do nich nastaví hodnotu 0.
     * Následovně je založen boolean yes a je nastaven na true.
     * Následovně začína cyklus while, který běží dokud je boolean yes nastaven na true.
     * Zde je do hodnot x a y pomocí instance r třídy Random nastaveno náhodné číslo z intervalu od 0 do 4
     * Dále jsou spuštěny dva cykly a oba se zopakují čtyřikrát.
     * Uvnitř druhého cyklu je podmínka, která kontroluje zda je hodnota pole arr[][] 0.
     * Pokud ano je boolean yes nastaven na false (končí while cyklus) a pomocí příkazu break je zastaven druhý cyklus.
     * Po proběhnutí while cyklu je do nově vytvořeného Integru o přidána přes instanci r třídy Random náhodná hodnota od 0 do 6.
     * Podmínka následovně zda je hodnata Integeru o je 3.
     * Pokud ano zavolá metoda metodu add() přes instanci board třídy charts s parametry x, y, 4.
     * Pokud ne zavolá metoda metodu add() přes instanci board třídy charts s parametry x, y, 2.
     * Následovně je vymazán obsah Panu Pole.
     * Nakonec metoda zavolá metodu printo() přes instanci board třídy charts s parametrem Pole.
     */
    public void spawn() {

        int x = 0;
        int y = 0;
        boolean yes = true;

        while (yes) {
            x = r.nextInt(0, 4);
            y = r.nextInt(0, 4);
            for (int c = 0; c < 4; c++) {
                for (int k = 0; k < 4; k++) {
                    if (board.arr[x][y] == 0) {
                        yes = false;
                        break;
                    }
                }
            }
        }
        int o = r.nextInt(0, 6);
        if (o == 3) {
            board.add(x, y, 4);
        } else {
            board.add(x, y, 2);
        }
        Pole.getChildren().clear();
        board.printo(Pole);

        for (int u = 0; u < 4; u++) {//test print
            for (int k = 0; k < 4; k++) {
                System.out.print(board.arr[k][u]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");

    }

    /**
     * @throws IOException
     * Nejprve metoda vytvoří instanci f třídy FileOutputStream.
     * Do konstruktoru dá instanci file třídy File.
     * Následovně třída vytvoří instanci out třídy ObjectOutputStream.
     * Do konstruktoru dá instanci f třídy FileOutputStream.
     * Dále třída zavolá přes instanci out třídy ObjectOutputStream metodu writeObject().
     * Druhá část metody je stejná akorát s jinými jmény
     */
    public void autosave() throws IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(board.arr);

        FileOutputStream k = new FileOutputStream(file1);
        ObjectOutputStream out1 = new ObjectOutputStream(k);
        out1.writeObject(scorenumber);
    }

    /**
     * @throws IOException
     * Metoda nejprve pustí dva for cykly.
     * V druhém cyklu metoda zavolá pomocí instance board třídy charts remove() s parametry u a k.
     * Navíc v druhém cyklu metoda nastaví do dvourozměrného pole temp[][] na souřadnice u a k 0.
     * Následovně je vymazán obsah Panu Pole.
     * Dále metoda zavolá metodu Random().
     * Poté Label score nastaví skóre na 0.
     * Následovně jsou nastaveny hodnoty Integerů scorenumber a tempscore na 0.
     * Nakonec metoda zavolá metodu autosave().
     */
    @FXML
    protected void Restart() throws IOException {
        for (int u = 0; u < 4; u++) {
            for (int k = 0; k < 4; k++) {
                board.remove(u, k);
                temp[u][k] = 0;
            }
        }
        Pole.getChildren().clear();
        Random();
        score.setText("0");
        scorenumber = 0;
        tempscore = 0;
        autosave();
    }

    /**
     * @throws IOException
     * Nejprve je vytvořen Integer h do kterého je dána hodnota 0.
     * Dále se spustí dva cykly a v druhém se k Integeru h přičítají hodnoty z dvourozměrného pole temp[][] na souřadnice x a y.
     * Metoda následovně zkontroluje zda je Integer h větší než nula.
     * Pokud je hodnota větší než nula stane se následující:
     * Nejprve se spustí dva cykly a v druhém se do dvourozměrného pole arr[][] přidá 0 na souřadnice x a y.
     * Dále se překopírují ve for cyklu hodnoty z dvourozměrného pole temp[][] do pole arr[][].
     * Následuje přpsání hodnoty Integeru scorenumber na hodnotu Integeru tempscore.
     * Metoda poté tuto nově nastavenou hodnotu přidá do Labelu score.
     * Následovně je vymazán obsah Panu Pole a je zavolána metoda printo() pomocí instance board třídy charts.
     * Nakonec už program pouze zavolá metodu autosave.
     */
    @FXML
    protected void Undo() throws IOException {
        int h = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                h += temp[x][y];
            }
        }
        if (h > 0) {
            for (int j = 0; j < 4; j++) {
                for (int v = 0; v < 4; v++) {
                    board.arr[j][v] = 0;
                }
            }
            for (int j = 0; j < 4; j++) {
                System.arraycopy(temp[j], 0, board.arr[j], 0, 4);
            }
            scorenumber = tempscore;
            score.setText(String.valueOf(scorenumber));

            Pole.getChildren().clear();
            board.printo(Pole);
            autosave();
        }
    }

    /**
     * Nejprve je do Integeru tempscore přepsána hodnota z Integeru scorenumber.
     * Následuje přepsání dvourozměrného pole arr[][] do pole temp[][].
     */
    public void undosave() {
        tempscore = scorenumber;
        for (int j = 0; j < 4; j++) {
            System.arraycopy(board.arr[j], 0, temp[j], 0, 4);
        }
    }

    /**
     * @throws IOException
     * Metoda zavolá metody checkUp(), checkRight(), checkLeft() a checkDown().
     * Pokud všechny tyto metody vrátí true zavolá metoda metodu lost().
     */
    public void checkLost() throws IOException {
        if (checkUp() && checkRight() && checkLeft() && checkDown()) {
            lost();
        }
    }

    /**
     * @throws IOException
     * Nejprve metoda zavolá metodu Restart() a smaže File file.
     * Následuje zavření FXML souboru "2048.fxml" a následovně je otevřen FXML soubor "Lost.fxml".
     */
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

    /**
     * @throws IOException
     * Tato metoda nejprve spustí dva cykly.
     * Ve druhém cyklu prochází dvourozměrné pole arr[][] a hledá v něm číslo 8192.
     * Pokud metoda číslo najde zavolá metodu win().
     */
    public void checkWin() throws IOException {
        for (int i = 0; i < 4; i++) {
            for (int h = 0; h < 4; h++) {
                if (board.arr[i][h] > 8192) {
                    win();
                }
            }
        }
    }

    /**
     * @throws IOException
     * Nejprve metoda zavolá metodu Restart() a smaže File file.
     * Následuje zavření FXML souboru "2048.fxml" a následovně je otevřen FXML soubor "Win.fxml".
     */
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

    /**
     * @return
     * Metoda spustí cyklus, který jde od hodnoty 3 do hodnoty 0.
     * V tomto cyklu projde metoda řadu podmínek a pokud projde jediná z nich vrátí false.
     * Pokud neprojde jediná podmínka skončí cyklus a metoda vrátí true.
     */
    public boolean checkRight() {
        for (int g = 3; g >= 0; g--) {

            if (board.arr[2][g] > 0 && board.arr[3][g] == 0) {
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]) {
                return false;
            }

            if (board.arr[1][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0) {
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] != board.arr[3][g] && board.arr[2][g] == 0) {
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[3][g] > 0 && board.arr[1][g] == board.arr[3][g] && board.arr[2][g] == 0) {
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == board.arr[2][g]) {
                return false;
            }

            if (board.arr[0][g] > 0 && board.arr[3][g] == 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0) {
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[3][g]) {
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] != board.arr[2][g]) {
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] == 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[3][g]) {
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] == 0 && board.arr[0][g] == board.arr[2][g]) {
                return false;
            } else if (board.arr[0][g] > 0 && board.arr[3][g] > 0 && board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     * Metoda spustí cyklus, který jde od hodnoty 0 do hodnoty 4.
     * V tomto cyklu projde metoda řadu podmínek a pokud projde jediná z nich vrátí false.
     * Pokud neprojde jediná podmínka skončí cyklus a metoda vrátí true.
     */
    public boolean checkLeft() {
        for (int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[0][g] > 0) {
            }
            /**druhy*/
            if (board.arr[1][g] > 0 && board.arr[0][g] == 0) {
                return false;
            } else if (board.arr[1][g] > 0 && board.arr[0][g] == board.arr[1][g]) {
                return false;
            }
            /**treti*/
            if (board.arr[2][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0) {
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] != board.arr[0][g] && board.arr[1][g] == 0) {
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[0][g] > 0 && board.arr[2][g] == board.arr[0][g] && board.arr[1][g] == 0) {
                return false;
            } else if (board.arr[2][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == board.arr[1][g]) {
                return false;
            }
            /**ctvrty*/
            if (board.arr[3][g] > 0 && board.arr[0][g] == 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0) {
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[0][g]) {
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] != board.arr[1][g]) {
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] == 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[0][g]) {
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] == 0 && board.arr[3][g] == board.arr[1][g]) {
                return false;
            } else if (board.arr[3][g] > 0 && board.arr[0][g] > 0 && board.arr[1][g] > 0 && board.arr[2][g] > 0 && board.arr[3][g] == board.arr[2][g]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     * Metoda spustí cyklus, který jde od hodnoty 3 do hodnoty 0.
     * V tomto cyklu projde metoda řadu podmínek a pokud projde jediná z nich vrátí false.
     * Pokud neprojde jediná podmínka skončí cyklus a metoda vrátí true.
     */
    public boolean checkDown() {
        for (int g = 3; g >= 0; g--) {
            /**prvni*/
            if (board.arr[g][3] > 0) {

            }
            /**druhy*/
            if (board.arr[g][2] > 0 && board.arr[g][3] == 0) {
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]) {
                return false;
            }
            /**Treti*/
            if (board.arr[g][1] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0) {
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] != board.arr[g][3] && board.arr[g][2] == 0) {
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][3] > 0 && board.arr[g][1] == board.arr[g][3] && board.arr[g][2] == 0) {
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == board.arr[g][2]) {
                return false;
            }
            /**Ctvrty*/
            if (board.arr[g][0] > 0 && board.arr[g][3] == 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0) {
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][3]) {
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] != board.arr[g][2]) {
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] == 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][3]) {
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] == 0 && board.arr[g][0] == board.arr[g][2]) {
                return false;
            } else if (board.arr[g][0] > 0 && board.arr[g][3] > 0 && board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     * Metoda spustí cyklus, který jde od hodnoty 0 do hodnoty 4.
     * V tomto cyklu projde metoda řadu podmínek a pokud projde jediná z nich vrátí false.
     * Pokud neprojde jediná podmínka skončí cyklus a metoda vrátí true.
     */
    public boolean checkUp() {
        for (int g = 0; g < 4; g++) {
            /**prvni*/
            if (board.arr[g][0] > 0) {
            }
            /**druhy*/
            if (board.arr[g][1] > 0 && board.arr[g][0] == 0) {
                return false;
            } else if (board.arr[g][1] > 0 && board.arr[g][0] == board.arr[g][1]) {
                return false;
            }
            /**treti*/
            if (board.arr[g][2] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0) {
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] != board.arr[g][0] && board.arr[g][1] == 0) {
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][0] > 0 && board.arr[g][2] == board.arr[g][0] && board.arr[g][1] == 0) {
                return false;
            } else if (board.arr[g][2] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == board.arr[g][1]) {
                return false;
            }
            /**ctvrty*/
            if (board.arr[g][3] > 0 && board.arr[g][0] == 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0) {
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][0]) {
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] != board.arr[g][1]) {
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] == 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][0]) {
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] == 0 && board.arr[g][3] == board.arr[g][1]) {
                return false;
            } else if (board.arr[g][3] > 0 && board.arr[g][0] > 0 && board.arr[g][1] > 0 && board.arr[g][2] > 0 && board.arr[g][3] == board.arr[g][2]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @throws IOException
     * @throws ClassNotFoundException
     * Tato metoda nejprve vytvoří novou instanci f třídy FileInputStream().
     * Do konstruktoru této třídy dá program File file.
     * Následovně vytvoří metoda novou instanci in třídy ObjectInputStream a do konstruktoru dá FileInputStream f.
     * Metoda poté nahraje do dvourozměrného pole arr[][] pomocí instance in třídy ObjectInputStream, která zavolá metodu readObject(), obsah File file.
     * Dále metoda vytvoří novou instanci c třídy FileInputStream().
     * Do konstruktoru této třídy dá program File file1.
     * Následovně vytvoří metoda novou instanci in třídy ObjectInputStream a do konstruktoru dá FileInputStream c.
     * Metoda poté nahraje do Integeru s pomocí instance in třídy ObjectInputStream, která zavolá metodu readObject(), obsah File file1.
     * Dále jsou spuštěny dva for cykly a v druhém je obsah pole arr[][] nahrán do pole board.arr[][].
     * Nakonec program zavolá přes instanci board třídy charts metodu printo().
     */
    public void fileload() throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(f);
        int[][] arr = (int[][]) in.readObject();

        FileInputStream c = new FileInputStream(file1);
        ObjectInputStream in1 = new ObjectInputStream(c);
        int s = (Integer) in1.readObject();
        score.setText(String.valueOf(s));
        scorenumber = s;

        for (int h = 0; h < 4; h++) {
            for (int k = 0; k < 4; k++) {
                board.arr[h][k] = arr[h][k];
            }
        }
        board.printo(Pole);
    }

    /**
     * @param location
     * @param resources
     * Nejprve je založen ImageView img a do konstruktoru je dán obrázek "undo.png".
     * Tomuto obrázku je následovně nastavena výška 50 a šířka 50 a je přidán na Button Undo.
     * Následovně je založen další ImageView img1 a do konstruktoru je dán obrázek "restart.png".
     * Obrázku je následovně nastavena výška 45 a šířka 45 a je přidán na Button Undo.
     * Nakonec program zkontroluje
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ImageView img = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\left-arrow-key.png");
        img.setFitWidth(50);
        img.setFitHeight(50);
        Undo.setGraphic(img);

        ImageView img1 = new ImageView("C:\\Users\\janse\\IdeaProjects\\Here we go again\\src\\main\\resources\\com\\example\\herewegoagain\\undo.png");
        img1.setFitWidth(50);
        img1.setFitHeight(50);
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