
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller extends KeyAdapter implements Initializable {
    Tools tool = new Tools();
    TranslateTransition translate = new TranslateTransition();
    @FXML
    Button Start;
    @FXML
    Button Settings;
    @FXML
    AnchorPane Pole;
    @FXML
    Label score;
    ArrayList<Pieces> list = new ArrayList<>();
    int pocetnul = 0;
    int pocetjednicek = 0;
    int pocetdvojek = 0;
    int pocettrojek = 0;
    Random r = new Random();
    int c;
    int v;
    ArrayList<Integer> y = new ArrayList<>();

    @FXML
    protected void Start(){
        random();
        //Start.setDisable(true);
    }

    @FXML
    protected void settings(){
        Up();
    }

    public void drawGridPane(){
        Path tah = new Path();
        MoveTo move1 = new MoveTo(75,0);
        LineTo line1 = new LineTo(75,300);
        MoveTo move2 = new MoveTo(150,0);
        LineTo line2 = new LineTo(150,300);
        MoveTo move3 = new MoveTo(225,0);
        LineTo line3 = new LineTo(225,300);
        MoveTo move4 = new MoveTo(0,0);
        LineTo line4 = new LineTo(300,0);
        MoveTo move5 = new MoveTo(0,75);
        LineTo line5 = new LineTo(300,75);
        MoveTo move6 = new MoveTo(0,150);
        LineTo line6 = new LineTo(300,150);
        MoveTo move7 = new MoveTo(0,225);
        LineTo line7 = new LineTo(300,225);
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

    public void random(){

        int x1 = r.nextInt(3);
        int y1 = r.nextInt(3);

        Pieces pc1 = new Pieces(x1, y1,2);
        list.add(pc1);
        Pole.getChildren().add(list.get(0).button());


        int x2 = r.nextInt(3);
        while (x2 == x1){
            x2 = r.nextInt(3);
        }

        int y2 = r.nextInt(3);
        while (y2 == y1){
            y2 = r.nextInt(3);
        }
        Pieces pc2 = new Pieces(x2, y2,2);
        list.add(pc2);
        //Pole.add(pc2.button(), x2, y2);

    }



    public void keyPressed (KeyEvent e){

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP){
            System.out.println("lol");
            score.setText("kokot");
        }
    }

    public void Down(){

    }

    public void Left(){

    }

    public void Right(){

    }

    public void Up(){
        for (int i = 0; i < list.size(); i++){
            y.add(list.get(i).getX());
        }



        for (int i = 0; i < y.size(); i++) {
            if (y.get(i) == 0) {
                pocetnul++;
            } else if (y.get(i) == 1) {
                pocetjednicek++;
            } else if (y.get(i) == 2) {
                pocetdvojek++;
            } else if (y.get(i) == 3) {
                pocettrojek++;
            }
        }
        if (pocetnul > 1 || pocetdvojek > 1 || pocettrojek > 1 || pocetjednicek > 1){
            problematic();
        } else {
            easy("up");
        }

    }

    public void easy(String s) {
        y.clear();
        pocetnul = 0;
        pocetdvojek = 0;
        pocettrojek = 0;
        pocetjednicek = 0;
        if (s.equals("up")) {
            for (int h = 0; h < list.size(); h++) {
                //Pole.getChildren().remove(list.get(h).button());
                //Pole.add(list.get(h).button(), list.get(h).getX(), 0);
                list.get(h).setY(0);
            }
            add();
        } else if (s.equals("down")){

        } else if (s.equals("left")){

        } else if (s.equals("right")){

        }

    }

    public void problematic() {
    }
    public void add(){
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            x.add(list.get(i).getX());
            y.add(list.get(i).getY());

        }
        while(x.contains(v)){
            v = r.nextInt(0,3);
        }
        while(y.contains(c)){
            c = r.nextInt(0,3);
        }

        Pieces p = new Pieces(v,c,2);
        //Pole.add(p.button(),v,c);
        x.clear();
        y.clear();
        v = 100;
        c = 100;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawGridPane();
        random();
    }
}
