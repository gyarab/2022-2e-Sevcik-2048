
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller extends KeyAdapter implements Initializable {
    FileWriter writer = new FileWriter("SaveFile.txt");
    //Trida nastroje
    Tools tool = new Tools();
    @FXML
    Button Load;
    @FXML
    Button Save;
    @FXML
    Pane Pole;
    @FXML
    Label score;
    //list Buttonu/kousku
    ArrayList<Pieces> list = new ArrayList<>();
    //muj random
    Random r = new Random();
    //Pocet Buttonu v poli
    int numberOfButton;

    public Controller() throws IOException {

    }

    public void drawGridPane() {
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

    public void random() {

        int x1 = r.nextInt(0, 4);
        int y1 = r.nextInt(0, 4);

        Pieces pc1 = new Pieces(x1, y1, 2);
        numberOfButton = 1;
        list.add(pc1);
        Pole.getChildren().add(pc1.button());
        Pole.getChildren().get(numberOfButton).setLayoutX(tool.decode(x1, "x"));
        Pole.getChildren().get(numberOfButton).setLayoutY(tool.decode(y1, ""));
        numberOfButton++;

        int x2 = r.nextInt(3);
        while (x2 == x1) {
            x2 = r.nextInt(3);
        }

        int y2 = r.nextInt(3);
        while (y2 == y1) {
            y2 = r.nextInt(3);
        }
        Pieces pc2 = new Pieces(x2, y2, 2);
        list.add(pc2);
        Pole.getChildren().add(pc2.button());
        Pole.getChildren().get(numberOfButton).setLayoutX(tool.decode(x2, "x"));
        Pole.getChildren().get(numberOfButton).setLayoutY(tool.decode(y2, ""));
        numberOfButton++;
    }


    public void Down() {

    }

    public void Left() {

    }

    public void Right() {

    }

    public void Up() {
        int[] arr1 = new int[4];
        for (int h = 0; h < 4; h++) {
            arr1[h] = 0;
        }
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).getX() == 0) {
                arr1[0]++;
            } else if (list.get(x).getX() == 1) {
                arr1[1]++;
            } else if (list.get(x).getX() == 2) {
                arr1[2]++;
            } else if (list.get(x).getX() == 3) {
                arr1[3]++;
            }
        }

        if (arr1[0] > 1) {
            problemUp(0);
        } else {
            easyUp(0);
        }
        if (arr1[1] > 1) {
            problemUp(1);
        } else {
            easyUp(1);
        }
        if (arr1[2] > 1) {
            problemUp(2);
        } else {
            easyUp(2);
        }
        if (arr1[3] > 1) {
            problemUp(3);
        } else {
            easyUp(3);
        }
        for (int h = 0; h < 4; h++) {
            arr1[h] = 0;
        }
    }

    public void easyUp(int x) {
        int h = 0;
        for (int u = 0; u < list.size(); u++) {
            if (list.get(u).getX() == x) {
                h = u;
            }
        }
        Pole.getChildren().get(h + 1).setLayoutY(tool.decode(0, ""));
        list.get(h).setY(0);
        newRandom();
    }

    public void problemUp(int x) {

    }

    public void newRandom() {
        int y = newRandomY();
        int x = newRandomX();
        newRandomX();
        Pieces pc = new Pieces(x, y, 2);
        list.add(pc);
        Pole.getChildren().add(pc.button());
        Pole.getChildren().get(numberOfButton).setLayoutX(tool.decode(x,"x"));
        Pole.getChildren().get(numberOfButton).setLayoutY(tool.decode(y, ""));
        numberOfButton++;
    }
    public int newRandomY() {
        int c = list.size() - 1;
        int y = r.nextInt(0,4);
        while (c > 1){
            if (y != list.get(c).getY()){
                c--;
            } else {
                y = r.nextInt(0,4);
                c = list.size() - 1;
            }
        }
        return y;
    }
    public int newRandomX() {
        int c = list.size() - 1;
        int x = r.nextInt(0,4);
        while (c > 1){
            if (x != list.get(c).getX()){
                c--;
            } else {
                x = r.nextInt(0,4);
                c = list.size() - 1;
            }
        }
        return x;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawGridPane();
        random();
    }

    @FXML
    //TODO
    //Dodělat načítání (Zkoro hotovo)
    protected void Load() throws IOException {
        /*for(int u = 0; u < list.size(); u++){
            Pole.getChildren().remove(list.get(u).button());
        }
        FileReader sc = new FileReader("SaveFile.txt");
        while(true){

            int u = sc.read();
            int b = sc.read();
            int k = sc.read();
            Pieces p = new Pieces(u,b,k);
            list.add(p);
        }
        for(int x = 0; x < list.size(); x++) {
            tool.add(list.get(x).button(), Pole);
        }*/

    }

    @FXML
    protected void Save() throws IOException {
        for(int x = 0; x < list.size(); x++){
            writer.write(list.get(x).getX() + " " + list.get(x).getY() + " " + list.get(x).number + '\n');
        }
        writer.close();
    }
}
