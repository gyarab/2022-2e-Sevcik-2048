import javafx.scene.Node;
import javafx.scene.control.Button;

public class Pieces extends Node implements Comparable<Pieces> {
    Button btn;
    int X;
    int Y;
    int number;

    public Pieces(int x, int y, int num) {

        String s = String.valueOf(num);
        number = num;
        btn = new Button(s);
        btn.setPrefSize(76.25,76.25);
        X = x;
        Y = y;
    }
    public Button button(){
        return btn;
    }
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public void setX(int x){
        X = x;
    }
    public void setY(int y){
        Y = y;
    }
    public void merge(){
        number = number * 2;
        btn.setText(String.valueOf(number));
    }



    @Override
    public int compareTo(Pieces o) {
        if (X > o.X) {
            return 1;
        } else if (X < o.X){
            return -1;
        }
        return 0;
    }

}
